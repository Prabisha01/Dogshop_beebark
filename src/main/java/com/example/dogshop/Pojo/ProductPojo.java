package com.example.dogshop.Pojo;

import com.example.dogshop.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPojo {
    private Integer id;
    private  String name;
    private  String type;
    private Integer price;
    private MultipartFile image;


    public ProductPojo(Product product) {
        this.id=product.getId();
        this.name= product.getName();
        this.type=product.getType();
        this.price =product.getPrice();
    }
}
