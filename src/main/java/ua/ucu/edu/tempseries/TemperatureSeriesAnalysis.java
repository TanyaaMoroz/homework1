package ua.ucu.edu.tempseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ABSOLUTE_ZERO = -273;
    private double[] temperatureSeries;
    private int size;

    
    // Constructor without parameters
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Constructor with parameter
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty.");
        }
        checkValidTemperatures(temperatureSeries);
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                                        temperatureSeries.length);
        this.size = temperatureSeries.length;
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

    public int getSize() {
        return size;
    }

    private void checkValidTemperatures(double[] temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO) {
                throw new InputMismatchException("Temperature cannot be " 
                + "below absolute zero.");
            }
        }
    }

    public double average() {
        checkEmptySeries();
        double sum = 0.0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / size;
    }

    public double deviation() {
        checkEmptySeries();
        double avg = average();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += (temperatureSeries[i] - avg)*(temperatureSeries[i] - avg);
        }
        return Math.sqrt(sum / size);
    }

    public double min() {
        checkEmptySeries();
        double min = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (temperatureSeries[i] < min) {
                min = temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() {
        checkEmptySeries();
        double max = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (temperatureSeries[i] > max) {
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        checkEmptySeries();
        double closest = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (Math.abs(temperatureSeries[i]) < Math.abs(closest)  
            || (Math.abs(temperatureSeries[i]) == Math.abs(closest)  
            && temperatureSeries[i] > closest)) {
                closest = temperatureSeries[i];
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        double[] sortedTemperatures = Arrays.copyOf(temperatureSeries,
                                             temperatureSeries.length);
        Arrays.sort(sortedTemperatures);
    
        double closestTemp = sortedTemperatures[0];
        double minDifference = Math.abs(tempValue - closestTemp);
        
        for (double temp : sortedTemperatures) {
            double difference = Math.abs(tempValue - temp);
    
            if (difference < minDifference 
            || (difference == minDifference && temp > closestTemp)) {
                minDifference = difference;
                closestTemp = temp;
            }
        }
        return closestTemp;
    }

    public double[] findTempsLessThan(double tempValue) {
        return Arrays.stream(temperatureSeries, 0, size)
                     .filter(t -> t < tempValue)
                     .toArray();
    }

    public double[] findTempsGreaterThan(double tempValue) {
        return Arrays.stream(temperatureSeries, 0, size)
                     .filter(t -> t >= tempValue)
                     .toArray();
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
    ArrayList<Double> resultList = new ArrayList<>();
    for (double temp : temperatureSeries) {
        if (temp >= lowerBound && temp <= upperBound) {
            resultList.add(temp);
        }
    }
    double[] result = new double[resultList.size()];
    for (int i = 0; i < resultList.size(); i++) {
        result[i] = resultList.get(i);
    }
    Arrays.sort(result);
    return result;
    }


    public void reset() {
        size = 0;
        temperatureSeries = new double[DEFAULT_CAPACITY];
    }

    public double[] sortTemps() {
        double[] sortedTemps = Arrays.copyOf(temperatureSeries, size);
        Arrays.sort(sortedTemps);
        return sortedTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        checkEmptySeries();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        checkValidTemperatures(temps);
        ensureCapacity(size + temps.length);
        for (double temp : temps) {
            temperatureSeries[size++] = temp;
        }
        return size;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > temperatureSeries.length) {
            temperatureSeries = Arrays.copyOf(temperatureSeries,
                                    temperatureSeries.length * 2);
        }
    }

    private void checkEmptySeries() {
        if (size == 0) {
            throw new IllegalArgumentException("Temperature series is empty.");
        }
    }
}
