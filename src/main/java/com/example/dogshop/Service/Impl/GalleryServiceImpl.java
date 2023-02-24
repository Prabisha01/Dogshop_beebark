package com.example.dogshop.Service.Impl;


import com.example.dogshop.Entity.Gallery;
import com.example.dogshop.Pojo.GalleryPojo;
import com.example.dogshop.Repo.GalleryRepo;
import com.example.dogshop.Service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@RequiredArgsConstructor

public class GalleryServiceImpl implements GalleryService {
    private final GalleryRepo galleryRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/dogshop/";


    @Override
    public GalleryPojo saveGallery(GalleryPojo galleryPojo) throws IOException {
        Gallery gallery = new Gallery();
        if (galleryPojo.getId() != null) {
            gallery.setId(galleryPojo.getId());
        }
        gallery.setTitle(galleryPojo.getTitle());



        if(galleryPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, galleryPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, galleryPojo.getImage().getBytes());

            gallery.setImage(galleryPojo.getImage().getOriginalFilename());
        }
        galleryRepo.save(gallery);
        return new GalleryPojo(gallery);
    }


    @Override
    public List<Gallery> fetchAll() {
        return galleryRepo.findAll();
    }

    @Override
    public Gallery fetchById(Integer id) {
        return galleryRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));

    }

    @Override
    public void deleteById(Integer id) {
        galleryRepo.deleteById(id);
    }
}