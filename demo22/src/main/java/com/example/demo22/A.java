package com.example.demo22;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 13096 on 2022/10/28.
 */
@RestController
@RequestMapping
public class A {

    @GetMapping("/a")
    public String a(){
        return "hello word";
    }
}
