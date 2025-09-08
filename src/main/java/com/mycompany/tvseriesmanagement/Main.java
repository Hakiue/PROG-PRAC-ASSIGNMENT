/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesmanagement;

/**
 *
 * @author zaaki
 */
public class Main {
    public static void main(String[] args) {
     
        System.out.println("Welcome to the TV Series Management System!");
        System.out.println("==========================================");
        
        Series seriesManager = new Series();
        seriesManager.displayMenu();
    }
}
