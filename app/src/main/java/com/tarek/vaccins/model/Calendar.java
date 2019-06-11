package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calendar {


    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("value")
    @Expose
    private Integer value;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
