package com.example.onsitethree.StorageData;

public class Model {
    private String name;
    private String path;
    private Boolean clicked;
    private String items;
    private String date;
    private String type;

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getItems() {
        return items;
    }

    public void setItems( String items ) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked( Boolean clicked ) {
        this.clicked = clicked;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath( String path ) {
        this.path = path;
    }
}
