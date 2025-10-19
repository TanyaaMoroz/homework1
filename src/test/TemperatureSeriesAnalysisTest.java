package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.InputMismatchException;

import org.junit.Test;

import ua.edu.ucu.tempseries.TempSummaryStatistics;
import ua.edu.ucu.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

        @Test
        public void testAverageWithOneElementArray() {
            double[] temperatureSeries = {-1.0};
            TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
            double expResult = -1.0;
    
            double actualResult = seriesAnalysis.average();
    
            assertEquals(expResult, actualResult, 0.00001);
        }
    
        @Test(expected = IllegalArgumentException.class)
        public void testAverageWithEmptySeries() {
            TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

            seriesAnalysis.average();
        }
    
        @Test
        public void testAverage() {
            double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
            TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
            double expResult = 1.0;
    
            double actualResult = seriesAnalysis.average();
    
            assertEquals(expResult, actualResult, 0.00001);
        }
    
        @Test
        public void testDeviation() {
                double[] temperature = {3.0, 4.0, 5.0};
                TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperature);
                double expResult = 0.816496580927726;
                double actualResult = seriesAnalysis.deviation();
        
                assertEquals(expResult, actualResult, 0.00001);
            }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
    TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    double tempValue = 2.0;
    double expResult = 3.0;

    double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);

    assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double tempValue = 2.0;
        double[] expResult = {-5.0, 1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThan(tempValue);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double tempValue = 2.0;
        double[] expResult = {3.0, 5.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThan(tempValue);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double lowerBound = -1.0;
        double upperBound = 4.0;
        double[] expResult = {1.0, 3.0};

        double[] actualResult = seriesAnalysis.findTempsInRange(lowerBound, upperBound);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.reset();
        
        assertEquals(0, seriesAnalysis.getSize());
        assertArrayEquals(new double[10], seriesAnalysis.getTemperatureSeries(), 0.00001);
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.0, 1.0, 3.0, 5.0};

        double[] actualResult = seriesAnalysis.sortTemps();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] initialTemps = {3.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initialTemps);
        double[] newTemps = {1.0, 5.0};
        int expSize = 4;

        int actualSize = seriesAnalysis.addTemps(newTemps);

        assertEquals(expSize, actualSize);
        assertArrayEquals(new double[]{3.0, -5.0, 1.0, 5.0}, Arrays.copyOf(seriesAnalysis.getTemperatureSeries(), actualSize), 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddInvalidTemps() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] invalidTemps = {3.0, -5.0, -300.0};
        seriesAnalysis.addTemps(invalidTemps);
    }
}
        