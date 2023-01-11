package stream_api_exercises.inheritance_polymorphism_exercises.exercise_one;

import java.util.Arrays;
import java.util.Objects;

public class Present {

    private static int CAPACITY = 10;
    private Sweet[] sweets;
    private int index;

    {
        sweets = new Sweet[CAPACITY];
        index = 0;
    }

    public Sweet[] filterSweetsBySugarRange(double minSugarWeight, double maxSugarWeight) {
        return Arrays.stream(sweets)
                .filter(Objects::nonNull).filter(s -> {
                    return s.getSugarWeight() >= minSugarWeight && s.getSugarWeight() <= maxSugarWeight;
                })
                .toArray(Sweet[]::new);
    }

    // the method calculates total weight of the present
    public double calculateTotalWeight() {
        return Arrays.stream(sweets)
                .filter(Objects::nonNull)
                .mapToDouble(Sweet::getWeight).sum();
    }

    // the method that adds sweet to the present
    public void addSweet(Sweet sweet) {
        if (sweet == null) {
            return;
        }
        if (sweets.length <= index) {
            sweets = Arrays.copyOf(sweets, sweets.length * 2);
        }
        sweets[index++] = sweet;
    }

    // the method returns copy of the Sweet[] array so that nobody could
    // modify state of the present without addSweet() method.
    // Also array shouldn’t contain null values.
    public Sweet[] getSweets() {
        return Arrays.stream(sweets)
                .filter(Objects::nonNull)
                .toArray(Sweet[]::new);
    }

}
