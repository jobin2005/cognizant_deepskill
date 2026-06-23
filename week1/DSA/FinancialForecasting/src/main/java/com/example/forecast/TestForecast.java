package com.example.forecast;

public class TestForecast {
    public static void main(String[] args) {
        double[] historical = new double[] {100.0, 110.0, 121.0, 133.1}; // ~10% growth
        int periods = 3;

        double rec = Forecast.predictRecursive(historical, periods);
        double fast = Forecast.predictFast(historical, periods);

        System.out.println("Historical: ");
        for (double v : historical) System.out.print(v + " ");
        System.out.println();

        System.out.println("Recursive prediction (" + periods + " periods): " + rec);
        System.out.println("Fast prediction (" + periods + " periods): " + fast);

        System.out.println();
        System.out.println("Complexity:");
        System.out.println("- Recursive method: O(k) recursive calls where k=periods.");
        System.out.println("- Optimized method using pow: O(1) for the exponentiation (constant-time library call).");
    }
}
