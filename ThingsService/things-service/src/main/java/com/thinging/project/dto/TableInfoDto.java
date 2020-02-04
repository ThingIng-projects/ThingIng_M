package com.thinging.project.dto;

import com.thinging.project.entity.ConnectionOption;
import com.thinging.project.utils.consts.SchemaType;
import com.thinging.project.utils.consts.StorageType;

public class TableInfoDto {

    private Long id;
    private String name;
    private StorageType storageType;
    private SchemaType tableType;
    private String structure;
    private ConnectionOptionDto connectionOptionDto;


    public TableInfoDto() { }

    public TableInfoDto(Long id, String name, StorageType storageType, SchemaType tableType, String structure, ConnectionOptionDto connectionOptionDto) {
        this.id = id;
        this.name = name;
        this.storageType = storageType;
        this.tableType = tableType;
        this.structure = structure;
        this.connectionOptionDto = connectionOptionDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ConnectionOptionDto getConnectionOptionDto() {
        return connectionOptionDto;
    }

    public void setConnectionOptionDto(ConnectionOptionDto connectionOptionDto) {
        this.connectionOptionDto = connectionOptionDto;
    }
}
