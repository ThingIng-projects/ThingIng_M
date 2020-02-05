package com.thinging.project.entity;

import com.thinging.project.utils.consts.StorageType;
import com.thinging.project.utils.consts.SchemaType;

import javax.persistence.*;

@Entity
@Table
public class TableInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String name;
    private StorageType storageType;
    private SchemaType tableType;
    private String structure;

    @ManyToOne
    private ConnectionOption connectionOption;


    public Long getId() { return id; }

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

    public ConnectionOption getConnectionOption() {
        return connectionOption;
    }

    public void setConnectionOption(ConnectionOption connectionOption) {
        this.connectionOption = connectionOption;
    }
}
