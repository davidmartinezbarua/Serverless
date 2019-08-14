package com.serverless.dal;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBAdapter {


    private AmazonDynamoDB dbClient;
    private DynamoDBMapper mapper;
    private static DynamoDBAdapter db_adapter;

    private DynamoDBAdapter() {
        this.dbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }
    public static DynamoDBAdapter getInstance() {
        if (db_adapter == null)
            db_adapter = new DynamoDBAdapter();

        return db_adapter;
    }

    public DynamoDBMapper createDbMapper(DynamoDBMapperConfig mapperConfig) {
        if (this.dbClient != null)
            mapper = new DynamoDBMapper(this.dbClient, mapperConfig);

        return this.mapper;
    }


    public AmazonDynamoDB getDbClient() {
        return dbClient;
    }

    public void setDbClient(AmazonDynamoDB client) {
        this.dbClient = client;
    }


}
