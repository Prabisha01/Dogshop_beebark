package com.example.dogshop;

import com.example.dogshop.Entity.User;
import com.example.dogshop.Repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUser() {

        User user = User.builder()
                .email("prabisha@gmail.com")
                .password("12345")
                .fullname("prabisha")
                .address("Btk")
                .mobile("123")

                .build();
//
        userRepo.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getUserTest() {
        User user = userRepo.findById(1).get();
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    //
    @Test
    @Order(3)
    public void fetchAll() {
        List<User> user = userRepo.findAll();
        Assertions.assertThat(user.size()).isGreaterThan(0);
    }
    //
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        User user = userRepo.findById(1).get();
        user.setFullname("prabisha");
        User user1 = userRepo.save(user);
        Assertions.assertThat(user1.getFullname()).isEqualTo("prabisha");
    }
    //
    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        User user=userRepo.findById(1).get();
        userRepo.delete(user);
        User user1=null;
        Optional<User> userOptional=userRepo.findByEmail("prabisha@gamil.com");
        if(userOptional.isPresent()){
            user1 = userOptional.get();
        }
        User user2=userRepo.save(user);
        Assertions.assertThat(user1).isNull();
    }


}