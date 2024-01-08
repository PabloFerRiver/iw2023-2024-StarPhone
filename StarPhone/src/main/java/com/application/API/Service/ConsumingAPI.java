package com.application.API.Service;

import com.application.API.Entities.CallRecord;
import com.application.API.Entities.CustomerLine;
import com.application.API.Entities.DataUsageRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumingAPI {
    private static final String  url ="http://omr-simulator.us-east-1.elasticbeanstalk.com/";
    @Autowired
    private final RestTemplate restTemplate;


    public ConsumingAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public List<CustomerLine> getAllLines(String carrier){
         CustomerLine[] response= restTemplate.getForObject(url+"?carrier="+carrier, CustomerLine[].class);
        if (response != null) {
            List<CustomerLine> customerLines = Arrays.asList(response);
            // Guardar en la base de datos utilizando el repositorio
        }

        assert response != null;
        return Arrays.asList(response);

    }
    public CustomerLine getCustomerLine(UUID id,String carrier) {
        CustomerLine customerLine=restTemplate.getForObject(url+id+"?carrier="+carrier,CustomerLine.class);
        if(customerLine==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CustomerLine not found");
        }
        return customerLine;
    }
    public List<DataUsageRecord> getdataUsage(UUID id, String carrier, LocalDate startDate, LocalDate endDate){
        DataUsageRecord[] dataUsageRecords=restTemplate.getForObject(url+id+"/datausagerecords?carrier="+carrier+"&startDate="+startDate+"&endDate="+endDate,DataUsageRecord[].class);
        if (dataUsageRecords != null) {
            List<DataUsageRecord> datausagerecord = Arrays.asList(dataUsageRecords);
            // Guardar en la base de datos utilizando el repositorio

        }

        assert dataUsageRecords != null;
        return Arrays.asList(dataUsageRecords);

    }
    public List<CallRecord> getCallRecords(UUID id, String carrier, LocalDate startDate, LocalDate endDate) {
        CallRecord[] callRecords = restTemplate.getForObject(url + id +"/callrecords?carrier="+carrier+"&startDate="+startDate+"&endDate="+endDate, CallRecord[].class);
        if (callRecords != null) {
            List<CallRecord> callrecord = Arrays.asList(callRecords);
            // Guardar en la base de datos utilizando el repositorio

        }
        assert callRecords != null;
        return Arrays.asList(callRecords);

    }
        public CustomerLine getCustomerLineByPhoneNumber(String phoneNumber,String carrier) {
            CustomerLine customerLine=restTemplate.getForObject(url+"search/phonenumber/"+phoneNumber+"?carrier="+carrier,CustomerLine.class);
            if(customerLine==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CustomerLine not found");
            }
            return customerLine;
        }



    public void saveCustomerLine(CustomerLine customerLine){
        restTemplate.postForObject(url,customerLine,CustomerLine.class);




    }
    public void deleteCustomerLine(UUID id,String carrier){
        System.out.println(" deleting id: "+id);
        restTemplate.delete(url+id+"?carrier="+carrier);

    }



}



