package com.example.isambataro.lesson2.model;

/**
 * Created by isambataro on 22/06/18.
 */

public class DataPeople {

    private String m_Name;
    private String m_Gender;

    public DataPeople (){

    }

    public DataPeople (String name, String gender) {
        m_Name = name;
        m_Gender = gender;
    }

    public void setName(String m_Name) {
        this.m_Name = m_Name;
    }

    public void setGender(String m_Gender) {
        this.m_Gender = m_Gender;
    }

    public String getGender() {
        return m_Gender;
    }

    public String getName() {
        return m_Name;
    }
}

