package com.example.dogshop;

import com.example.dogshop.Entity.Gallery;
import com.example.dogshop.Repo.GalleryRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GalleryRepositoryTest {

    @Autowired
    private GalleryRepo galleryRepo;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveGalleryTest() {

        Gallery gallery = Gallery.builder()
                .title("beesha")
                .build();


        galleryRepo.save(gallery);

        Assertions.assertThat(gallery.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getBlogTest() {

        Gallery gallery = Gallery.builder()
                .title("Educare")
                        .build();


        galleryRepo.save(gallery);

        Gallery galleryCreated = galleryRepo.findById(gallery.getId()).get();
        Assertions.assertThat(galleryCreated.getId()).isEqualTo(gallery.getId());

    }

    @Test
    @Order(3)
    public void getListOfBlogTest(){
        Gallery gallery = Gallery.builder()

                .title("Educare")
                .build();

        galleryRepo.save(gallery);
        List<Gallery> User = galleryRepo.findAll();
        Assertions.assertThat(User.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    public void updateBlogTest(){

        Gallery gallery = Gallery.builder()


                .title("Educare")

                        .build();

        galleryRepo.save(gallery);

        Gallery gallery1  = galleryRepo.findById(gallery.getId()).get();

        gallery1.setTitle("Educare");

        Gallery galleryUpdated  = galleryRepo.save(gallery);

        Assertions.assertThat(galleryUpdated.getTitle()).isEqualTo("Educare nepal");

    }

    @Test
    @Order(5)
    public void deleteGalleryTest(){

        Gallery gallery = Gallery.builder()


                .title("Educare")

                .build();

        galleryRepo.save(gallery);


        Gallery gallery1 = galleryRepo.findById(gallery.getId()).get();
        galleryRepo.delete(gallery1);

        Gallery gallery2 = null;
        Optional<Gallery> optionalGallery = galleryRepo.findGalleryByTitle("educare");
        if(optionalGallery.isPresent()){
            gallery2 = optionalGallery.get();
        }

        Assertions.assertThat(gallery2).isNull();
    }

}