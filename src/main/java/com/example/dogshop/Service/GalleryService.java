package com.example.dogshop.Service;



import com.example.dogshop.Entity.Gallery;
import com.example.dogshop.Pojo.GalleryPojo;

import java.io.IOException;
import java.util.List;

public interface GalleryService {
    GalleryPojo saveGallery(GalleryPojo galleryPojo)throws IOException;

    List<Gallery> fetchAll();

    Gallery fetchById(Integer id);
    void deleteById(Integer id);
}
