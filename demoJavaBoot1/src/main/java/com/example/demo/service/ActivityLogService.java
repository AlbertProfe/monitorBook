package com.example.demo.service;

import com.example.demo.model.ActivityLog;
import java.util.ArrayList;


public class ActivityLogService {

    static ArrayList<ActivityLog> activityLogs = new ArrayList<>();



    // return books to controller
    // get books form list static from class and return as signature
    public ArrayList<ActivityLog> getAllBooks (){
        return activityLogs;
    }


    public boolean addActivityLog (ActivityLog activityLog){

        boolean resultAdding = activityLogs.add(activityLog);

        return resultAdding;
    }
}
