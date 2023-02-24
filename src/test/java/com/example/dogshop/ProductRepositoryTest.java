package com.example.dogshop;


import com.example.dogshop.Entity.Gallery;
import com.example.dogshop.Entity.Product;
import com.example.dogshop.Repo.ProductRepo;
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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepo productRepo;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveProduct() {

        Product product = Product.builder()
                .name("TestName")
                .type("build")
                .build();

        productRepo.save(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getProduct() {
        Product product = productRepo.findById(1).get();
        Assertions.assertThat(product.getId()).isEqualTo(1);
    }

    //
    @Test
    @Order(3)
    public void fetchAll() {
        List<Product> product = productRepo.findAll();
        Assertions.assertThat(product.size()).isGreaterThan(0);
    }
    //
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        Product product = productRepo.findById(1).get();
        product.setName("Name");
        Product product1 = productRepo.save(product);
        Assertions.assertThat(product1.getName()).isEqualTo("Name");
    }
    //
    @Test
    @org.springframework.core.annotation.Order(5)
    public void deleteProductTest(){

        Product product= Product.builder()


                .name("Educare")

                .build();

        productRepo.save(product);


        Product product1 = productRepo.findById(product.getId()).get();
        productRepo.delete(product1);

        Product product2 = null;
        Optional<Product> optionalProduct = productRepo.findProductByName("em");
        if(optionalProduct.isPresent()){
            product2 = optionalProduct.get();
        }

        Assertions.assertThat(product2).isNull();
    }

}