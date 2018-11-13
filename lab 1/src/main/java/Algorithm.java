import model.Distance;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {

    /**
     * Calculates the max and min distance between the first and second word
     * @param text- text string for search
     * @param word1 - first word for search distance
     * @param word2 - second word for search distance
     **/
    public static Distance calculate(String text, String word1, String word2) {
        List<String> list = Arrays.asList(text.split(" "));
        boolean isStartWordWas = false;
        boolean isEndWordWas = false;
        int startWordIndex = 0;
        int endWordIndex = 0;
        Set<Integer> distances = new HashSet<>();
        int i = 0;
        for (String value : list) {
            if (value.equals(word1)) {
                isStartWordWas = true;
                startWordIndex = i;
            }
            else if (value.equals(word2)) {
                isEndWordWas = true;
                endWordIndex = i;
            }

            if (isStartWordWas && value.equals(word2)) {
                distances.add(i - startWordIndex - 1);
            }else if (isEndWordWas && value.equals(word1)) {
                distances.add(i - endWordIndex - 1);
            }
            i++;
        }
        List<Integer> sortedResults = distances.stream().sorted().collect(Collectors.toList());
        System.out.println("min distance: " + sortedResults.get(0));
        System.out.println("max distance: " + sortedResults.get(sortedResults.size() - 1));
        return new Distance(sortedResults.get(0), sortedResults.get(sortedResults.size() - 1));
    }
}
