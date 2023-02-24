package com.example.dogshop.Service;

import com.example.dogshop.Entity.Contact;
import com.example.dogshop.Entity.User;
import com.example.dogshop.Pojo.ContactPojo;
import com.example.dogshop.Pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User fetchById(Integer id);

    UserPojo save(UserPojo userPojo) throws IOException;

    User findByEmail(String email);
    List<User> fetchAll();

    String submitMsg(ContactPojo contactPojo);
//    String update(UserPojo userPojo);
    void deleteById(Integer id);

    void processPasswordResetRequest(String email);

    void sendEmail();

    String updateResetPassword(String email);

    Contact CdeleteById(Integer id);

    List<Contact> fetchAllContact();

    User userById(Integer id);

    List<User> fetchAllUser();
}
