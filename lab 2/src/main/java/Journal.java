import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Journal {

    private Map<String, List<String>> list;

    public Journal() {
        list = new HashMap<>();
    }

    /**
     * add new word in journal
     * @param value - String, which must be added
     */
    public void add(String value) {
        //Get first letter
        String key = value.substring(0, 1);
        //Get a list of words starting with a certain letter
        List<String> listWithStartLetter = list.get(key);
        //If there is no such list, create it, put a value in it and save it under the selected letter
        if (listWithStartLetter == null) {
            listWithStartLetter = new ArrayList<>();
        }
        listWithStartLetter.add(value);
        list.put(key, listWithStartLetter);
    }

    /**
     * returns the number of words starting with
     * @param text - word for search
     * @return int
     */
    public int find(String text) {
        //Get first letter
        String firstLetter = text.substring(0, 1);
        //Get list by first letter
        List<String> listLetters = list.get(firstLetter);
        //if list is null, means there are no words beginning with such a letter
        if (listLetters == null) {
            return 0;
        }
        //Get text length
        int textLength = text.length();
        //Initialize words count
        int count = 0;

        for (String s : listLetters){
            //If length word from list less length search word, that continue
            if (s.length() < textLength) {
                continue;
            }
            //Get substring same length as the word you are looking for
            String substring = s.substring(0, textLength);
            //If the search word and substring are equal, then the counter plus 1
            if (substring.equals(text)) {
                log.info("Find: {}", s);
                ++count;
            }
        }
        //return count find words
        return count;
    }
}
