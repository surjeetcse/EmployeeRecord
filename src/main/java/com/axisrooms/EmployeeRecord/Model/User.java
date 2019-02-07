package com.axisrooms.EmployeeRecord.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private ObjectId _id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "email")
    private String Email;
    @JsonProperty(value = "mobileNo")
    private Long MobileNo;
    @JsonProperty(value = "position")
    private String Position;

    public User(){ }

}
