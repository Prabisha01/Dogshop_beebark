package com.example.dogshop.Service;

import com.example.dogshop.Entity.Booking;
import com.example.dogshop.Entity.User;
import com.example.dogshop.Pojo.BookingPojo;

import java.util.List;

public interface BookingService {
    String save(BookingPojo bookingPojo);

    void deleteById(Integer id);

    Booking fetchById(Integer id);

    void userById(Integer id);

    List<User> fetchAllUser();

    List<Booking> fetchAll();
}
