package com.example.SampleWebapp.config;

import static com.example.SampleWebapp.HelloWorldController.getPrettyTime;

public class MyHelper {

    public static void log(String message) {
        System.out.println("**LOG**\t" + getPrettyTime() + "\t" + message);
    }

}
