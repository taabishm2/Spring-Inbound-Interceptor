package com.example.SampleWebapp;

import com.example.SampleWebapp.config.MyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    public static String getPrettyTime() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("name", "GET Success - " + getPrettyTime());
        return "helloWorld";
    }

    @RequestMapping(value = "get200", method = RequestMethod.GET)
    public String successGet(ModelMap model) {
        model.addAttribute("name", "GET Success - " + getPrettyTime());
        return "successGet";
    }

    @RequestMapping(value = "get200WithBody", method = RequestMethod.GET)
    public String successGet(ModelMap model, @RequestBody String postPayload) {
        model.addAttribute("name", "GET with body Success - " + getPrettyTime() + "\n" + postPayload);
        return "successGetWithBody";
    }

    @RequestMapping(value = "get500", method = RequestMethod.GET)
    public String failedGet(ModelMap model) {
        throw new NullPointerException("GET Failure - " + getPrettyTime());
    }

    @RequestMapping(value = "post200", method = RequestMethod.POST)
    public String successPost(ModelMap model, @RequestBody String postPayload) {
        model.addAttribute("name", "POST Success - " + getPrettyTime() + "\n" + postPayload);
        return "successPost";
    }

    @RequestMapping(value = "post500", method = RequestMethod.POST)
    public String failedPost(ModelMap model, @RequestBody String postPayload) {
        throw new NullPointerException("GET Failure - " + getPrettyTime());
    }

    @RequestMapping(value = "paramTest", method = RequestMethod.GET)
    public String paramTest(ModelMap model, @RequestParam String locationCode) {
        MyHelper.log("Entered the paramTest controller");
        MyHelper.log("Request Param is: " + locationCode);
        return "successGet";
    }

}