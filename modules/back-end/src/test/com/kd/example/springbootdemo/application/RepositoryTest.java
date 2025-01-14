package com.kd.example.springbootdemo.application;

import java.util.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.kd.example.springbootdemo.data.config.DynamoDBConfig;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kd.example.springbootdemo.data.model.Product;
import com.kd.example.springbootdemo.data.repository.ProductRepository;
import com.kd.example.springbootdemo.application.SpringBootDemoApplication;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootTest
@TestPropertySource(properties = {
  "amazon.dynamodb.endpoint=http://localhost:8000/",
  "amazon.aws.accesskey=test1",
  "amazon.aws.secretkey=test231" })
public class RepositoryTest {

    @ClassRule
    public static LocalDbCreationRule dynamoDB = new LocalDbCreationRule();

    private static DynamoDBMapper dynamoDBMapper;
    private static AmazonDynamoDB amazonDynamoDB;
    private static ProductRepository repository;
    @Value("${amazon.aws.accesskey}")
    private static String AWS_ACCESSKEY="test1";
    @Value("${amazon.aws.secretkey}")
    private static String AWS_SECRETKEY="test231";
    @Value("${amazon.dynamodb.endpoint}")
    private static String DYNAMODB_ENDPOINT="http://localhost:8000/";

    private static String region="ap-south-1";


    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);


    @Before
    public void setup() throws Exception {
        LOGGER.debug("Endpoint: "+DYNAMODB_ENDPOINT);
        LOGGER.debug("Access Key: "+AWS_ACCESSKEY);
        LOGGER.debug("Secret Key: "+AWS_SECRETKEY);

        amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(DYNAMODB_ENDPOINT, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESSKEY, AWS_SECRETKEY))).build();

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        repository = new ProductRepository();
        repository.setDynamoDBMapper(dynamoDBMapper);
    }


    @Test
    public void testProductLoad() {
        LOGGER.debug("Endpoint: "+DYNAMODB_ENDPOINT);
        LOGGER.debug("Access Key: "+AWS_ACCESSKEY);
        LOGGER.debug("Secret Key: "+AWS_SECRETKEY);


        amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(DYNAMODB_ENDPOINT, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESSKEY, AWS_SECRETKEY))).build();

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        repository = new ProductRepository();
        repository.setDynamoDBMapper(dynamoDBMapper);




        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setTitle("testing");
        product.setRatingRate(2.0f);
        product.setPrice(1.5f);
        product.setDescription("tester");
        product.setCategory("category");
        product.setImage("http://www.yahoo.com");

        product.setRatingCount(10);

        repository.saveProduct(product);

        /**

        PutItemRequest request = new PutItemRequest();

        request.setTableName("Product");


        request.setReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);


        request.setReturnValues(ReturnValue.ALL_OLD);


        Map<String, AttributeValue> map = new HashMap<>();
        map.put("productId", new AttributeValue(product.getProductId()));
        map.put("title", new AttributeValue(product.getTitle()));

        request.setItem(map);

        try {

            PutItemResult result = amazonDynamoDB.putItem(request);

            System.out.println("Status : " + result.getSdkHttpMetadata().getHttpStatusCode());

            System.out.println("Consumed Capacity : " + result.getConsumedCapacity().getCapacityUnits());


            if(result.getAttributes() != null) {
                result.getAttributes().entrySet().stream()
                        .forEach( e -> System.out.println(e.getKey() + " " + e.getValue()));
            }

        } catch (AmazonServiceException e) {

            System.out.println(e.getErrorMessage());

        } **/


        //repository.saveProduct(product);
        //List<Product> result = repository.findAll();
        //result.forEach(res -> LOGGER.debug(ReflectionToStringBuilder.toString(res, ToStringStyle.MULTI_LINE_STYLE)));
       // assertThat(result.size(), is(greaterThan(0)));
    }



    private static boolean isEmpty(String inputString) {
        return inputString == null || "".equals(inputString);
    }

}

