package com.example.dogshop.Service;

import com.example.dogshop.Entity.Product;
import com.example.dogshop.Pojo.ProductPojo;

import java.io.IOException;
import java.util.List;

public interface ProductServices {


    ProductPojo saveProduct(ProductPojo productPojo) throws IOException;

    List<Product> fetchAll();
    Product fetchById(Integer id);
    void deleteById(Integer id);
}
