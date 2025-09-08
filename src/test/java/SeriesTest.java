/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.tvseriesmanagement.Series;
import com.mycompany.tvseriesmanagement.SeriesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SeriesTest {
    private Series series;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        series = new Series();
        System.setOut(new PrintStream(outContent));
        series.addSeries(new SeriesModel("S001", "Breaking Bad", "16", "62"));
        series.addSeries(new SeriesModel("S002", "Game of Thrones", "18", "73"));
        series.addSeries(new SeriesModel("S003", "Friends", "12", "236"));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void TestSearchSeries() {
        SeriesModel result = series.searchSeriesById("S001");
        
        assertNotNull(result, "Series should be found");
        assertEquals("S001", result.SeriesId);
        assertEquals("Breaking Bad", result.SeriesName);
        assertEquals("16", result.SeriesAge);
        assertEquals("62", result.SeriesNumberOfEpisodes);
        
        String output = outContent.toString();
        assertTrue(output.contains("SERIES FOUND"));
        assertTrue(output.contains("Breaking Bad"));
    }
    
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        SeriesModel result = series.searchSeriesById("S999");
        
        assertNull(result, "Series should not be found");
        
        String output = outContent.toString();
        assertTrue(output.contains("cannot be located"));
    }
    
    @Test
    public void TestUpdateSeries() {
        String input = "Updated Breaking Bad\n15\n65\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        
        Series testSeries = new Series();
        testSeries.addSeries(new SeriesModel("S001", "Breaking Bad", "16", "62"));
        
        boolean result = testSeries.updateSeriesById("S001");
        
        assertTrue(result, "Update should be successful");
        
        String output = outContent.toString();
        assertTrue(output.contains("successfully updated"));
    }
    
    @Test
    public void TestDeleteSeries() {
        // Mock user input for confirmation
        String input = "y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Series testSeries = new Series();
        testSeries.addSeries(new SeriesModel("S001", "Breaking Bad", "16", "62"));
        
        int initialSize = testSeries.getSeriesList().size();
        boolean result = testSeries.deleteSeriesById("S001");
        
        assertTrue(result, "Delete should be successful");
        assertEquals(initialSize - 1, testSeries.getSeriesList().size());
        
        String output = outContent.toString();
        assertTrue(output.contains("successfully deleted"));
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        boolean result = series.deleteSeriesById("S999");
        
        assertFalse(result, "Delete should fail for non-existing series");
        
        String output = outContent.toString();
        assertTrue(output.contains("cannot be located for deletion"));
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        assertTrue(series.validateAgeRestriction("2"), "Age 2 should be valid");
        assertTrue(series.validateAgeRestriction("10"), "Age 10 should be valid");
        assertTrue(series.validateAgeRestriction("18"), "Age 18 should be valid");
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(series.validateAgeRestriction("1"), "Age 1 should be invalid");
        assertFalse(series.validateAgeRestriction("19"), "Age 19 should be invalid");
        assertFalse(series.validateAgeRestriction("abc"), "Non-numeric input should be invalid");
        assertFalse(series.validateAgeRestriction("-5"), "Negative age should be invalid");
        assertFalse(series.validateAgeRestriction(""), "Empty string should be invalid");
    }
}