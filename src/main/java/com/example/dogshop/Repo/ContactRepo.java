package com.example.dogshop.Repo;

import com.example.dogshop.Entity.Contact;
import com.example.dogshop.Entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
    Optional<Contact> findContactByEmail(String answer);
}
