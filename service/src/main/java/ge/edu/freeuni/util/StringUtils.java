package ge.edu.freeuni.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static List<String> splitAndTrim(String input) {
        List<String> resultList = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            String[] parts = input.split("\\|");
            for (String part : parts) {
                String trimmedPart = part.trim();
                resultList.add(trimmedPart);
            }
        }
        return resultList;
    }
}
