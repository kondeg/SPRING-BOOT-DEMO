package com.kd.example.springbootdemo.data.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.kd.example.springbootdemo.data.model.Product;


@Repository
public class ProductRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void setDynamoDBMapper(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    private final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

    public void saveProduct(Product product) {
        dynamoDBMapper.save(product);
    }

    public Product getProductById(Product id) {
        return dynamoDBMapper.load(Product.class, id);
    }

    public List<Product> findAll() {
        return dynamoDBMapper.scan(Product.class, scanExpression);
    }


}
