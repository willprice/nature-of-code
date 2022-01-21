package dev.willprice.noc.utilities;

public class Probability {
    /**
     * Compute the cumulative sum of the elements of the array {@code xs}
     */
    public static double[] cumsum(double[] xs) {
        double[] cumulativeSum = new double[xs.length];
        double sum = 0;
        for (int i = 0; i < xs.length; i++) {
            sum += xs[i];
            cumulativeSum[i] = sum;
        }
        return cumulativeSum;
    }
}
