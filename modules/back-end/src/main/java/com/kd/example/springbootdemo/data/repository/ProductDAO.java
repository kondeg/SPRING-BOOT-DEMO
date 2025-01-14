package com.kd.example.springbootdemo.data.repository;

import java.util.List;
import java.util.Optional;

import com.kd.example.springbootdemo.data.model.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductDAO extends CrudRepository<Product, String> {
    Optional<Product> findById(String id);
    List<Product> findAll();
}
