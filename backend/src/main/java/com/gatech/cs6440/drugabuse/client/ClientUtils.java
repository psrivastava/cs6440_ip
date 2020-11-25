package com.gatech.cs6440.drugabuse.client;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ClientUtils {

    // Logging
    static Logger logger = LoggerFactory.getLogger(ClientUtils.class);

    public static String round(BigDecimal value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = value.setScale(places, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(Period.between(birthDate, currentDate).getYears());
    }
    public static Address getAddress(Patient patientRecord) {
        Address address = new Address();
        try {
            address = patientRecord.getAddress().get(0);
        } catch (Exception e) {
            logger.error("Error occurred while fetching the address.");
            e.printStackTrace();
        }
        return address;
    }

    public static String getIdFromString(String idString) {
        String splitter = "/";
        if (idString.contains(splitter)) {
            idString = idString.split("/")[1];
        }
        return idString;
    }


}
