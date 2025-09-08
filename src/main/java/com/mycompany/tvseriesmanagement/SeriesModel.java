/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesmanagement;

/**
 *
 * @author zaaki
 */
public class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
 
    public SeriesModel(String seriesId, String seriesName, String seriesAge, String seriesNumberOfEpisodes) {
        this.SeriesId = seriesId;
        this.SeriesName = seriesName;
        this.SeriesAge = seriesAge;
        this.SeriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
    
    public SeriesModel() {
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Age: %s, Episodes: %s", 
                           SeriesId, SeriesName, SeriesAge, SeriesNumberOfEpisodes);
    }
}
