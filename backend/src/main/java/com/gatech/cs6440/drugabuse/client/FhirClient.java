package com.gatech.cs6440.drugabuse.client;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import com.gatech.cs6440.drugabuse.model.UserDetails;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FhirClient {

    IGenericClient client = null;
    String fhirBase;
    int PAGES = 5;

    // Logging
    static Logger logger = LoggerFactory.getLogger(FhirClient.class);

    public FhirClient(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();

        // Timeout settings
        ctx.getRestfulClientFactory().setConnectTimeout(30 * 1000);
        ctx.getRestfulClientFactory().setSocketTimeout(30 * 1000);
        client = ctx.newRestfulGenericClient(baseUrl);
        fhirBase = baseUrl;
        logger.info("Created the client");
    }

    public HashMap<String, UserDetails> getPatientDetails(String loincCode) {

        FhirContext ctx = FhirContext.forDstu3();

        // Observation: LOINC Codes for Oximeter, Heart Rate (Resting), Respiratory Rate, Weight,

        // Condition : Diabetes, Asthma,

        // Search using LOINC code for Pulse Oximeter readings < 90
        Bundle results = client.search()
                .forResource(Observation.class)
                .where(Observation.CODE.exactly().systemAndCode("http://loinc.org", loincCode))
                .returnBundle(Bundle.class)
                .execute();

        logger.info("Made the FHIR call!");

        List<IBaseResource> observations = new ArrayList<>();

        extractAllObservations(ctx, observations, results);

        logger.info(String.valueOf(observations.size()));

        HashSet<String> patientObs = new HashSet<>();
        HashSet<String> contextObs = new HashSet<>();

        HashMap<String, UserDetails> patientDetailsList = new HashMap<>();

        logger.info(results.getEntry().toString());
        for (IBaseResource observation : observations) {

            Observation ob = (Observation) observation;
            String patient = "";
            String context = "";

            try {
                try {
                    patient = ob.getSubject().getReference();
                } catch (Exception e) {
                    logger.info("Got a null patient");
                    e.printStackTrace();
                }
                try {
                    context = ob.getContext().getReference();
                } catch (Exception e) {
                    logger.info("Got a null context");
                    e.printStackTrace();
                }

                if (patient == null) {
                    continue;
                }

                try {
                    if (!patient.contains("undefined")) {
                        if (ob.getContext().getReference() != null) {
                            patientObs.add(patient);
                            contextObs.add(context);
                            // Fetching required attributes of the Patient resource

                            // Get Patient, Observation, Encounter details
                            String patientId = ClientUtils.getIdFromString(patient);
                            String patientLink = fhirBase + "/Patient/" + patientId;
                            String encounterId = ClientUtils.getIdFromString(context);
                            String encounterLink = fhirBase + "/Encounter/" + encounterId;
                            String observationLink = ob.getId();

                            //System.out.println(observationLink);
                            String observationId = observationLink;
                            //String observationId = observationLink.split("http://hapi.fhir.org/baseDstu3/Observation")[1].split("/")[1];


                            String oximeterReading = ClientUtils.round(ob.getValueQuantity().getValue(), 2) + " " + ob.getValueQuantity().getUnit();
                            // Get patient Details
                            Patient patientRecord = client.read().resource(Patient.class).withId(patientId).execute();
                            String name = patientRecord.getNameFirstRep().getNameAsSingleString();
                            String gender = patientRecord.getGender().toString();
                            String maritalStatus = patientRecord.getMaritalStatus().getText();

                            if (maritalStatus == null) {
                                maritalStatus = "S";
                            }
                            if (maritalStatus.equals("Single")) {
                                maritalStatus = "S";
                            }
                            if (maritalStatus.equals("Married")) {
                                maritalStatus = "M";
                            }
                            // Get BirthDate and Age
                            Date birthDateOrig = patientRecord.getBirthDate();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
                            LocalDate localDate = ClientUtils.convertToLocalDateViaInstant(birthDateOrig);
                            String birthDate = localDate.format(formatter);
                            String age = ClientUtils.calculateAge(localDate);
                            // Get Address
                            Address address = ClientUtils.getAddress(patientRecord);
                            String city = address.getCity();
                            String state = address.getState();
                            String country = address.getCountry();
                            String postalCode = address.getPostalCode();
                            String addressReadable = city + ", " + state + ", " + country + ", " + postalCode;
                            // Get Encounter Details
                            //Encounter encounter = client.read().resource(Encounter.class).withId(context).execute();
                            Encounter encounter = new Encounter();

                            //String encounterStatus = encounter.getStatus().getDisplay();
                            String encounterType = "Not Provided";
                            String encounterStatus = "Completed";
                            //if (encounter.getType().size() > 0) {
                            //    encounterType = encounter.getType().get(0).getText();
                            //}
                            // Create the Object
                            UserDetails patientDetails = new UserDetails(patientId, patientLink, observationId, observationLink, encounterId, encounterLink, oximeterReading, name, gender, birthDate, age, maritalStatus, addressReadable, encounterStatus, encounterType, city, state, country, postalCode, false);
                            logger.debug(patientDetails.toString());
                            // Create a HashMap of Patients
                            patientDetailsList.put(patientId, patientDetails);
                        }
                    }
                }

                catch(NullPointerException e){
                        logger.error("Got a null patient. Moving on..");
                        e.printStackTrace();
                }
                } catch (NullPointerException | FHIRException e) {
                    logger.debug("Something went terribly wrong");
                    e.printStackTrace();
            }
            }
            return patientDetailsList;
        }

        private void extractAllObservations (FhirContext ctx, List < IBaseResource > observations, Bundle results){
            // Page 1
            observations.addAll(BundleUtil.toListOfResources(ctx, results));
            // Subsequent Pages - just 3 pages of content for now - PERFORMANCE REASONS :(
            int pageNumber = 0;
            while (results.getLink(IBaseBundle.LINK_NEXT) != null) {

                results = client
                        .loadPage()
                        .next(results)
                        .execute();
                observations.addAll(BundleUtil.toListOfResources(ctx, results));

                if (pageNumber >= PAGES) {
                    break;
                }
                pageNumber++;
            }
        }

    }
