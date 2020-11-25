package com.gatech.cs6440.drugabuse.engine;

import com.gatech.cs6440.drugabuse.client.FhirClient;
import com.gatech.cs6440.drugabuse.model.UserDetails;
import com.gatech.cs6440.drugabuse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class FhirEngine {

    // Logging
    static Logger logger = LoggerFactory.getLogger(FhirEngine.class);

    //@Value("${app.fhir.external}")
    @Value("${app.fhir.hosted}")
    private String baseUrl;
    // Pulse Oximetry
    // private static String loincCode = "59408-5" ;
    // Pulse Oximetry - Latest
    private static String loincCode = "2708-6";

    @Autowired
    UserService userDetailService;

    public FhirEngine() {
    }

    public boolean fetchUserDetails(){

        logger.debug(baseUrl);
        FhirClient fhirClient = new FhirClient(baseUrl);

        List<UserDetails> userList = new ArrayList<>();

        try{
            logger.debug("Fetching details from FHIR server..");
            HashMap<String,UserDetails> userMap = fhirClient.getPatientDetails(loincCode);
            logger.debug("SUCCESS: Fetched details from FHIR server.");
            userList = new ArrayList<>(userMap.values());
        }
        catch(Exception e){
            logger.error("Something went very wrong");
            e.printStackTrace();
            return false;
        }
        userDetailService.saveAll(userList);
        userDetailService.findAll();

        return true;
    }

}
