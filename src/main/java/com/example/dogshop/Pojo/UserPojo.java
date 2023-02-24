package com.example.dogshop.Pojo;


import com.example.dogshop.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Integer id;
    private String email;
    private String address;
    private String password;
    private String fullname;
    private String mobile;


    public UserPojo(User user){
        this.id= user.getId();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.address=user.getAddress();
        this.fullname=user.getFullname();
        this.mobile =user.getMobile();


    }


}