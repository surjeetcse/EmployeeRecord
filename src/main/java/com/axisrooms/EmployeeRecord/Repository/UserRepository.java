package com.axisrooms.EmployeeRecord.Repository;

import com.axisrooms.EmployeeRecord.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findBy_id(ObjectId _id);
}
