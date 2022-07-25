import java.io.FileNotFoundException;
import java.util.*;

public class SynonymsCheckApp<K, V> {
    private Map<K, ArrayList<V>> SynonymWordsMap=new HashMap<>();


    public Map<K, ArrayList<V>> getSynonymWordsMap() {
        return SynonymWordsMap;
    }

    public SynonymsCheckApp(Map<K, ArrayList<V>> synonymWordsMap) {
        SynonymWordsMap = synonymWordsMap;
    }

    public void setSynonymWordsMap(Map<K, ArrayList<V>> synonymWordsMap) {

        SynonymWordsMap = synonymWordsMap;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, ArrayList<String>> map = new HashMap();
        SynonymsCheckApp<String, String> Application = new SynonymsCheckApp<>(map);
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the file path:");
        String filePath=sc.nextLine();


        FileIO.CreateSynonymDictionary(filePath, Application);



    }
    public Map<K, ArrayList<V>> getUpdatedMap(Map<K,ArrayList<V>> Map){
         Map= checkTransitivity(Map);
         return Map;
    };

    ;

    public String CheckSynonymWords(Map<K, ArrayList<V>> map, K QueryKey, V QueryValue) {
        System.out.println(SynonymChecker(map, QueryKey, QueryValue));
        return "";

    }

    private boolean checkSymmetry(Map<K, ArrayList<V>> map, K QueryKey, V QueryValue) {
        for (K key : map.keySet()) {


            if (QueryKey.equals(key)) {
                if (map.get(QueryValue) != null) {
                    for (V RealValue : map.get(QueryValue)) {
                        if (map.get(QueryValue).contains(RealValue)) {
                            return true;
                        }
                    }
                }
            }


        }
        return false;
    }


    private Map<K, ArrayList<V>> checkTransitivity(Map<K, ArrayList<V>> map) {


        for (K key : map.keySet()) {


            map.replace(key, (ArrayList<V>) reachableItems(map, key,null, new ArrayList<V>()));
        }


        return map;
    }


    private ArrayList<V> reachableItems(Map<K, ArrayList<V>> map, K key,K oldkey, ArrayList<V> updatedValues) {



        ArrayList<V> ans = map.get(key);

        if (ans != null&&!updatedValues.contains(oldkey)) {
            for (V v : ans) {

                updatedValues.add(v);
                if (map.get(v) != null) {
                     oldkey=key;

                    reachableItems(map, (K) v,oldkey, updatedValues);

                }
            }
        }


        return  updatedValues;
    }




    private String SynonymChecker(Map<K, ArrayList<V>> map, K QueryKey, V QueryValue) {

        for (K key : map.keySet()) {


            if (QueryKey.equals(QueryValue)) {
                return "synonyms";
            }
            if (checkSymmetry(map, QueryKey, QueryValue)) {
                return "synonyms";
            }





            /*for (String i: updatedMap.get(key)){
                System.out.println(key+" :" +i);
            }*/


            if (key.equals(QueryKey)) {
                if (map.get(key).contains(QueryValue)) {
                    return "synonyms";
                }

            } else if (key.equals(QueryValue)) {
                if (map.get(key).contains(QueryKey)) {
                    return "synonyms";
                }
                ;
            } else {

            }
        }
        return "different";
    }
}





















































































