package com.example.dogshop.Service.Impl;

import com.example.dogshop.Entity.Product;
import com.example.dogshop.Pojo.ProductPojo;
import com.example.dogshop.Repo.ProductRepo;
import com.example.dogshop.Service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {
    private final ProductRepo productRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/dogshop/";



    @Override
    public ProductPojo saveProduct(ProductPojo productPojo) throws IOException {
        Product product = new Product();
        if (productPojo.getId() != null) {
            product.setId(productPojo.getId());
        }
        product.setName(productPojo.getName());
        product.setType(productPojo.getType());
        product.setPrice(productPojo.getPrice());

        if(productPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, productPojo.getImage().getBytes());

            product.setImage(productPojo.getImage().getOriginalFilename());
        }
        productRepo.save(product);
        return new ProductPojo(product);
    }



    @Override
    public List<Product> fetchAll() {
        return productRepo.findAll();
    }

    @Override
    public Product fetchById(Integer id) {
        return productRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));

    }

    @Override
    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }
}

