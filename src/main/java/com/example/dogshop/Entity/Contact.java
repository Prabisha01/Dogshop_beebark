package com.example.dogshop.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @SequenceGenerator(name = "iv_book_seq_gen", sequenceName = "iv_book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "iv_book_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

}