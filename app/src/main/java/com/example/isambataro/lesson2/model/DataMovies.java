package com.example.isambataro.lesson2.model;

/**
 * Created by isambataro on 18/06/18.
 */

public class DataMovies {
    private String m_Title;
    private String m_Episode_id;
    private String m_Producer;
    private String m_Director;
    private String m_Realease_Date;


    public DataMovies (){

    }

    public DataMovies (String title, String episode, String producer,
                       String director, String releaseDate) {
        m_Title = title;
        m_Episode_id = episode;
        m_Producer = producer;
        m_Director = director;
        m_Realease_Date = releaseDate;
    }

    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String title) {
        this.m_Title = title;
    }

    public String getEpisodeId() {
        return m_Episode_id;
    }

    public void setEpisodeid(String episodeId) {
        this.m_Episode_id = episodeId;
    }

    public String getProducer() {
        return m_Producer;
    }

    public void setProducer(String producer) {
        this.m_Producer = producer;
    }

    public String getDirector() {
        return m_Director;
    }

    public void setDirector(String director) {
        this.m_Director = director;
    }

    public String getRealeaseDate() {
        return m_Realease_Date;
    }

    public void setRealeaseDate(String realeaseDate) {
        this.m_Realease_Date = realeaseDate;
    }
}
