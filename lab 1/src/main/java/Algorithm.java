import model.Distance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<Integer> distances = new ArrayList<>();
        int index = 0;
        for (String value : list) {
            if (value.equals(word1)) {
                distances.addAll(calculateDistance(list.subList(index, list.size()), index, word2));
            } else if (value.equals(word2)) {
                distances.addAll(calculateDistance(list.subList(index, list.size()), index, word1));
            }
            index++;
        }
        List<Integer> sortedResults = distances.stream().sorted().collect(Collectors.toList());
        System.out.println("min distance: " + sortedResults.get(0));
        System.out.println("max distance: " + sortedResults.get(sortedResults.size() - 1));
        return new Distance(sortedResults.get(0), sortedResults.get(sortedResults.size() - 1));
    }

    /**
     * Calculates the distance between the first and second word
     * @param words - list of words coming after the desired
     * @param keyIndex - index key word
     * @param searchWord - word for search for calculate distance between him and key
     **/
    private static List<Integer> calculateDistance(List<String> words, Integer keyIndex, String searchWord) {
        int index = keyIndex + 1;
        List<Integer> distances = new ArrayList<>();
        for (String word: words) {
            if (word.equals(searchWord)) {
                distances.add((index - 1) - (keyIndex + 1));
            }
            index++;
        }
        return distances;
    }
}
