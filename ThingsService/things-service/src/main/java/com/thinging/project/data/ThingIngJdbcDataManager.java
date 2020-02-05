package com.thinging.project.data;

import com.thinging.project.entity.ConnectionOption;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ThingIngJdbcDataManager implements ThingIngDataManager {

   private ConnectionOption connectionOption;
   private SparkSession     sparkSession;
   private DataFrameReader  dataFrameReader;

    public ThingIngJdbcDataManager(ConnectionOption connectionOption) {
        System.setProperty("hadoop.home.dir", "C:/winutils");
        this.connectionOption = connectionOption;
    }

    @Override
    public void openSession() {
        sparkSession = SparkSession.builder().appName("ThingIng").master("local").getOrCreate();

        dataFrameReader = sparkSession.read().format("jdbc")
                .option("url",      connectionOption.getUrl())
                .option("dbtable",  connectionOption.getDbTable())
                .option("user",     connectionOption.getUserName())
                .option("password", connectionOption.getPassword());
    }

    @Override
    public void search() {
        Dataset<Row> jsonDataFrame = dataFrameReader.load();
        jsonDataFrame.show();
    }

    @Override
    public void save() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void closeSession() {
        sparkSession.close();
    }
}
