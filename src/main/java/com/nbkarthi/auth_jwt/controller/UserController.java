package com.nbkarthi.auth_jwt.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class UserController {

    @GetMapping
    public ResponseEntity<?> sayHello() {
        Map<String, String > objectMap = new HashMap<>();
       objectMap.put("data","ResponseEntity ok");
        return ResponseEntity.ok(objectMap);
    }

    @GetMapping("/trs")
    public String trs() {
        Map<String, String > objectMap = new HashMap<>();
        objectMap.put("data","ResponseEntity ok");
        System.out.println("Its Hear .............");
        return "Okk";
    }
}
