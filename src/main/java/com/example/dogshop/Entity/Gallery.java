package com.example.dogshop.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="gallery")
public class Gallery {
    @Id
    @SequenceGenerator(name = "iv_book_seq_gen", sequenceName = "iv_book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "iv_book_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column(name = "title")
    private String title;


    public String image;

    @Transient
    private String imageBase64;


}

