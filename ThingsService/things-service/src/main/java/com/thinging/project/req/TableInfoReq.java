package com.thinging.project.req;

import com.thinging.project.utils.consts.SchemaType;
import com.thinging.project.utils.consts.StorageType;

public class TableInfoReq {

    private String name;
    private StorageType storageType;
    private SchemaType tableType;
    private String structure;
    private Long connectionOptionsId;


    public TableInfoReq() { }

    public TableInfoReq(String name, StorageType storageType, SchemaType tableType, String structure, Long connectionOptionsId) {
        this.name = name;
        this.storageType = storageType;
        this.tableType = tableType;
        this.structure = structure;
        this.connectionOptionsId = connectionOptionsId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public SchemaType getTableType() {
        return tableType;
    }

    public void setTableType(SchemaType tableType) {
        this.tableType = tableType;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Long getConnectionOptionsId() {
        return connectionOptionsId;
    }

    public void setConnectionOptionsId(Long connectionOptionsId) {
        this.connectionOptionsId = connectionOptionsId;
    }
}
