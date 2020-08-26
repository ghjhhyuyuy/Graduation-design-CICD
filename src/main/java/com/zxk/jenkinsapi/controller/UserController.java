package com.zxk.jenkinsapi.controller;

import com.zxk.jenkinsapi.Service.UserServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by wzw on 2020/5/18
 *
 * @author wzw
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/attach")
    public void deploy() throws IOException, ParseException {
        userService.createJob();
    }
}
