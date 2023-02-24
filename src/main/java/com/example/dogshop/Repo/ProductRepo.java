package com.example.dogshop.Repo;
import com.example.dogshop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByName(String answer);
}
