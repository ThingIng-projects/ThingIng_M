package com.thinging.project;

import com.google.common.base.Splitter;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class ThingsServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ThingsServiceApplication.class, args);
    }

}
