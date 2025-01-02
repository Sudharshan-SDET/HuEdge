package com.assignment.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.assignment.api.pojos.CreateUserRequest;

public class TestDataHelper {

    // data for Create User request using Name & Job
    public static CreateUserRequest getCreateUserRequest() {
        return new CreateUserRequest("Morpheus", "Leader");
    }
    
 
    //  data for user registration
    public static Map<String, String> getRegisterRequest() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");
        return requestBody;
    }

    //  data for failed registration (missing password)
    public static Map<String, String> getFailedRegisterRequest() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("email", "sydney@fife");
        return requestBody;
    }
}
