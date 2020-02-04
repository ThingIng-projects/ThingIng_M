package com.thinging.project.service.dataServices;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

//@Service
public class DataControlService {

    public DataControlService(){
        System.setProperty("hadoop.home.dir", "C:/winutils");

    }

    public void createTable(String tablename){
        final SparkSession sparkSession = SparkSession.builder().appName("ThingIng").master("local").getOrCreate();
        final Dataset<Row> jsonDataFrame = sparkSession.read().format("jdbc")
                .option("url","jdbc:postgresql://localhost:5432/thingsservice")
                .option("dbtable","user_account")
                .option("user","ThingIngUser")
                .option("password","ThingIngUser").load();
        jsonDataFrame.show();
    }

}
