package com.example.dogshop.Service.Impl;

import com.example.dogshop.Entity.Booking;
import com.example.dogshop.Entity.User;
import com.example.dogshop.Pojo.BookingPojo;
import com.example.dogshop.Repo.BookingRepo;
import com.example.dogshop.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;

    @Override
    public String save(BookingPojo bookingPojo) {
        Booking booking = new Booking();
        if (bookingPojo.getId() != null) {
            booking.setId(bookingPojo.getId());
        }
        booking.setFullname(bookingPojo.getFullname());
        booking.setNoofdog(bookingPojo.getNoofdog());
        booking.setMobileNo(bookingPojo.getMobile_no());
        booking.setCheckin(bookingPojo.getCheckin());
        booking.setDate(bookingPojo.getDate());
        booking.setPrice(bookingPojo.getPrice());
        booking.setAddress(bookingPojo.getAddress());
        bookingRepo.save(booking);
        return null;
    }


//    @Override
//    public Booking fetchById(Integer id) {
//        return bookingRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
//    }


//    @Override
//    public Booking deleteById(Integer id) {
//        return bookingRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
//    }

    public List<Booking> fetchAll() {
        return this.bookingRepo.findAll();
    }


    @Override
    public void deleteById(Integer id) {
        bookingRepo.deleteById(id);

    }


//    @Override
//    public void deleteById(Integer id) {
//
//    }

    @Override
    public Booking fetchById(Integer id) {

            return (Booking) this.bookingRepo.findAll();
    }


    @Override
    public void userById(Integer id) {
        bookingRepo.deleteById(id);

    }

    @Override
    public List<User> fetchAllUser() {
        return null;
    }
}



