import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class FileIO {

    private static Scanner sc = null;

    public static void CreateSynonymDictionary(String filePath, SynonymsCheckApp App) throws FileNotFoundException {
        Map<String, ArrayList<String>> map = App.getSynonymWordsMap();
        sc = new Scanner(new File(filePath));

        int TestCaseCounter = 0;
        int numberOfTestCases = sc.nextInt();
        int numberOfElements = sc.nextInt();


        while (TestCaseCounter < numberOfTestCases) {
            sc.nextLine();


            for (int s = 0; s <= numberOfElements - 1; s++) {
                String line = sc.nextLine();
                String[] strArr = line.split(" ");


                String key = strArr[0].toLowerCase(Locale.ROOT);
                String value = strArr[1].toLowerCase(Locale.ROOT);

                if (map.get(key) != null) {
                    if (!key.equals(value)) {
                        map.get(key).add(value);
                    }
                    if (map.get(value) != null) {
                        if (!key.equals(value)) {

                            if (!map.get(value).contains(key))
                            map.get(value).add(key);
                    }} else {
                        map.put(value, new ArrayList<String>(Arrays.asList(key)));
                    }
                } else {
                    if (!key.equals(value)) {
                        map.put(key, new ArrayList<String>(Arrays.asList(value)));
                        if (map.get(value) != null) {
                            if (!map.get(value).contains(key))
                                map.get(value).add(key);
                        } else {
                            map.put(value, new ArrayList<String>(Arrays.asList(key)));
                        }
                    }


                }}


                int NumberOfQueries = sc.nextInt();


                sc.nextLine();
                for (int i = 0; i <= NumberOfQueries - 1; i++) {
                    String line = sc.nextLine();
                    String[] strArr = line.split(" ");


                    String queryKey = strArr[0].toLowerCase(Locale.ROOT);
                    String queryValue = strArr[1].toLowerCase(Locale.ROOT);


                    App.CheckSynonymWords(App.getUpdatedMap(map), queryKey, queryValue);


                }
                TestCaseCounter++;

                map.clear();
                if (sc.hasNext()) {
                    numberOfElements = sc.nextInt();


                }

            }
        }
    }



































