/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesmanagement;

/**
 *
 * @author zaaki
 */
import java.util.*;

public class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;

    public Series() {
        this.seriesList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
  
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n**************************");
            System.out.println("SERIES MANAGEMENT MENU");
            System.out.println("**************************");
            System.out.println("1. Capture a new TV Series");
            System.out.println("2. Search for a TV Series");
            System.out.println("3. Update TV Series details");
            System.out.println("4. Delete a TV Series");
            System.out.println("5. Display Series report");
            System.out.println("6. Exit");
            System.out.println("**************************");
            System.out.print("Please select an option >> ");
            
            choice = getValidIntegerInput();
            
            switch (choice) {
                case 1:
                    CaptureSeries();
                    break;
                case 2:
                    SearchSeries();
                    break;
                case 3:
                    UpdateSeries();
                    break;
                case 4:
                    DeleteSeries();
                    break;
                case 5:
                    SeriesReport();
                    break;
                case 6:
                    ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-6.");
            }
        } while (choice != 6);
    }
 
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW TV SERIES");
        System.out.println("*************************");
        
        System.out.print("Enter the series id >> ");
        String seriesId = scanner.nextLine().trim();
   
        if (findSeriesById(seriesId) != null) {
            System.out.println("Series with ID '" + seriesId + "' already exists!");
            return;
        }
        
        System.out.print("Enter the series name >> ");
        String seriesName = scanner.nextLine().trim();
        
        String seriesAge = getValidAgeRestriction();
        
        System.out.print("Enter the number of episodes >> ");
        String episodes = scanner.nextLine().trim();
        
        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, episodes);
        seriesList.add(newSeries);
        
        System.out.println("\nSeries details have been successfully saved.");
    }
    
    private String getValidAgeRestriction() {
        String ageInput;
        int age;
        
        while (true) {
            System.out.print("Enter the series age restriction >> ");
            ageInput = scanner.nextLine().trim();
            
            try {
                age = Integer.parseInt(ageInput);
                
                if (age >= 2 && age <= 18) {
                    return String.valueOf(age);
                } else {
                    System.out.println("Please enter a valid age restriction between 2 and 18.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age restriction.");
            }
        }
    }
    
    public boolean validateAgeRestriction(String age) {
        try {
            int ageInt = Integer.parseInt(age);
            return ageInt >= 2 && ageInt <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
   
    public void SearchSeries() {
        System.out.print("\nEnter the series id to search >> ");
        String searchId = scanner.nextLine().trim();
        
        SeriesModel foundSeries = findSeriesById(searchId);
        searchSeriesById(searchId);
    }
    
   
    public SeriesModel searchSeriesById(String seriesId) {
        SeriesModel foundSeries = findSeriesById(seriesId);
        
        if (foundSeries != null) {
            System.out.println("\nSERIES FOUND");
            System.out.println("*************");
            System.out.println("Series Id: " + foundSeries.SeriesId);
            System.out.println("Series Name: " + foundSeries.SeriesName);
            System.out.println("Series Age Restriction: " + foundSeries.SeriesAge);
            System.out.println("Series Number of Episodes: " + foundSeries.SeriesNumberOfEpisodes);
        } else {
            System.out.println("\nSeries with series id " + seriesId + " cannot be located.");
        }
        
        return foundSeries;
    }
    
    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update >> ");
        String updateId = scanner.nextLine().trim();
        
        updateSeriesById(updateId);
    }
    
    public boolean updateSeriesById(String seriesId) {
        SeriesModel seriesToUpdate = findSeriesById(seriesId);
        
        if (seriesToUpdate != null) {
            System.out.println("\nUPDATE SERIES DETAILS");
            System.out.println("*********************");
            
            System.out.print("Enter the new series name >> ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                seriesToUpdate.SeriesName = newName;
            }
            
            System.out.print("Enter the new age restriction >> ");
            String newAge = getValidAgeRestriction();
            seriesToUpdate.SeriesAge = newAge;
            
            System.out.print("Enter the new number of episodes >> ");
            String newEpisodes = scanner.nextLine().trim();
            if (!newEpisodes.isEmpty()) {
                seriesToUpdate.SeriesNumberOfEpisodes = newEpisodes;
            }
            
            System.out.println("\nSeries details have been successfully updated.");
            return true;
        } else {
            System.out.println("\nSeries with id " + seriesId + " cannot be located for update.");
            return false;
        }
    }
    
 
    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete >> ");
        String deleteId = scanner.nextLine().trim();
        
        deleteSeriesById(deleteId);
    }
    
    public boolean deleteSeriesById(String seriesId) {
        SeriesModel seriesToDelete = findSeriesById(seriesId);
        
        if (seriesToDelete != null) {
            System.out.println("\nDELETE SERIES");
            System.out.println("*************");
            System.out.println("Series to delete: " + seriesToDelete.SeriesName);
            System.out.print("Are you sure you want to delete this series? (y/n) >> ");
            
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                seriesList.remove(seriesToDelete);
                System.out.println("Series has been successfully deleted.");
                return true;
            } else {
                System.out.println("Delete operation cancelled.");
                return false;
            }
        } else {
            System.out.println("\nSeries with id " + seriesId + " cannot be located for deletion.");
            return false;
        }
    }

    public void SeriesReport() {
        System.out.println("\n\nSERIES REPORT");
        System.out.println("*************");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available.");
            return;
        }
        
        seriesList.sort((s1, s2) -> s1.SeriesId.compareTo(s2.SeriesId));
    
        System.out.printf("%-10s %-25s %-5s %-10s%n", "ID", "NAME", "AGE", "EPISODES");
        System.out.println("----------------------------------------------------");
        
        for (SeriesModel series : seriesList) {
            System.out.printf("%-10s %-25s %-5s %-10s%n", 
                            series.SeriesId, 
                            series.SeriesName, 
                            series.SeriesAge, 
                            series.SeriesNumberOfEpisodes);
        }
        
        System.out.println("----------------------------------------------------");
        System.out.println("Total number of series: " + seriesList.size());
    }
    

    public void ExitSeriesApplication() {
        System.out.println("\nThank you for using the Series Management System!");
        System.out.println("Goodbye!");
        System.exit(0);
    }
    

    private SeriesModel findSeriesById(String seriesId) {
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equalsIgnoreCase(seriesId)) {
                return series;
            }
        }
        return null;
    }
    
    private int getValidIntegerInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number >> ");
            }
        }
    }
    
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
    
    public void addSeries(SeriesModel series) {
        seriesList.add(series);
    }
}