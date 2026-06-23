package com.example.forecast;

public class Forecast {
    // compute average growth rate between consecutive historical values
    public static double averageGrowthRate(double[] values) {
        if (values == null || values.length < 2) return 0.0;
        double sum = 0.0;
        int count = 0;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] == 0) continue;
            sum += (values[i + 1] - values[i]) / values[i];
            count++;
        }
        return count == 0 ? 0.0 : sum / count;
    }

    // Recursive prediction: applies average growth rate repeatedly
    public static double predictRecursive(double[] historical, int periods) {
        if (historical == null || historical.length == 0) throw new IllegalArgumentException("historical required");
        double rate = averageGrowthRate(historical);
        return predictRecursiveHelper(historical[historical.length - 1], periods, rate);
    }

    private static double predictRecursiveHelper(double current, int periods, double rate) {
        if (periods <= 0) return current;
        return predictRecursiveHelper(current * (1 + rate), periods - 1, rate);
    }

    // Optimized direct formula using exponentiation
    public static double predictFast(double[] historical, int periods) {
        if (historical == null || historical.length == 0) throw new IllegalArgumentException("historical required");
        double rate = averageGrowthRate(historical);
        return historical[historical.length - 1] * Math.pow(1 + rate, periods);
    }
}
