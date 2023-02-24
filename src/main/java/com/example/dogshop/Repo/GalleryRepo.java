package com.example.dogshop.Repo;


import com.example.dogshop.Entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface GalleryRepo extends JpaRepository<Gallery, Integer> {
    Optional<Gallery> findGalleryByTitle(String answer);
}
