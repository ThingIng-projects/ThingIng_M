package com.thinging.project.req;

public class ConnectionOptionReq {

    private String url;
    private String dbTable;
    private String userName;
    private String password;

    public ConnectionOptionReq(String url, String dbTable, String userName, String password) {
        this.url = url;
        this.dbTable = dbTable;
        this.userName = userName;
        this.password = password;
    }

    public ConnectionOptionReq() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
