package stream_api_exercises.inheritance_polymorphism_exercises.exercise_two;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class WordsFilter {

    public static void main(String[] args) {
        System.out.println("Enter random words separated by space: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println("Enter minimum length to filter words: ");
        int minLength = scanner.nextInt();

        String result[] = filterWordsByLength(minLength, input.split(" "));


    }

    public static String[] filterWordsByLength(int minLength, String[] words) {
        return Arrays.stream(words)
                .filter(Objects::nonNull)
                .filter(s -> s.length()>=minLength)
                .toArray(String[]::new);
    }


}
