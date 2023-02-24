package com.example.dogshop.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @SequenceGenerator(name = "iv_book_seq_gen", sequenceName = "iv_book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "iv_book_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Integer price;

    public String image;

    @Transient
    private String imageBase64;


}


