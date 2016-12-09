package com.digzdigital.healthapp.model.data;

import com.digzdigital.healthapp.R;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Digz on 04/12/2016.
 */

public class Drug extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private String type;
    private String description;

    public Drug(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId(){
        if (type.equals("Syrup"))return R.mipmap.syrup_icon;
        return R.mipmap.pill_icon;
    }
}
