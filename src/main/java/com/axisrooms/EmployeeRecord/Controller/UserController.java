package com.axisrooms.EmployeeRecord.Controller;

import com.axisrooms.EmployeeRecord.Model.User;
import com.axisrooms.EmployeeRecord.Repository.UserRepository;
import com.axisrooms.EmployeeRecord.UserService.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String pushStaticDumpDataToExtranet(){
        return userService.pushStaticDumpDataToExtranet();
    }
}
