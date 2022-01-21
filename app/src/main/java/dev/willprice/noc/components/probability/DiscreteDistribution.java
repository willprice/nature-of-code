package dev.willprice.noc.components.probability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DiscreteDistribution<T> {
    List<Double> probabilities;
    List<T> events;
    private final Random random;

    public DiscreteDistribution(Map<T, Double> eventLikelihoods, Random random) {
        this.probabilities = new ArrayList<>(eventLikelihoods.size());
        this.events = new ArrayList<>(eventLikelihoods.size());
        this.random = random;
        int i = 0;
        double likelihoodSum = eventLikelihoods.values().stream().mapToDouble(Double::doubleValue).sum();
        for (Map.Entry<T, Double> pair : eventLikelihoods.entrySet()) {
            T event = pair.getKey();
            double prob = pair.getValue() / likelihoodSum;
            probabilities.add(prob);
            events.add(event);
            i++;
        }
    }

    public T sample() {
        double p = random.nextDouble();
        int eventIdx;
        for (eventIdx = 0; p > 0; eventIdx++) {
            p -= probabilities.get(eventIdx);
        }
        eventIdx--;
        return events.get(eventIdx);
    }
}
