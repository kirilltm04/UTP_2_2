import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    public LinkedHashMap<String, List<String>> map;

    public LinkedHashMap<String, List<String>> fileReader(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        LinkedHashMap<String, List<String>> ans = new LinkedHashMap<>();
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            List<String> arr = List.of(l.split("\t"));
            ans.put(arr.get(0), arr.subList(1, arr.size()));
        }
        return ans;
    }

    public ProgLang(String path) throws FileNotFoundException {
        this.map = fileReader(path);
    }

    public LinkedHashMap<String, List<String>> getLangsMap() {
        return this.map;
    }

    public void reversed(LinkedHashMap<String, List<String>> m) {
        ArrayList<String> keys = new ArrayList<>(m.keySet());
        ArrayList<List<String>> values = new ArrayList<>(m.values());
        Collections.reverse(keys);
        Collections.reverse(values);
        m.clear();
        for (int i = 0; i < keys.size(); i++) {
            m.put(keys.get(i), values.get(i));
        }
    }

    public LinkedHashMap<String, List<String>> getProgsMap() {
        LinkedHashMap<String, List<String>> ans = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> m : this.map.entrySet()) {
            String str = m.getKey();
            List<String> lst = m.getValue();
            for (String programmer : lst) {
                ans.computeIfAbsent(programmer, k -> new ArrayList<>()).add(str);
            }
        }
        return ans;
    }

    public LinkedHashMap<String, List<String>> getLangsMapSortedByNumOfProgs() {
        LinkedHashMap<String, List<String>> sorted = new LinkedHashMap<>();
        this.map.entrySet().stream()
                .sorted(Comparator.comparingInt(m -> m.getValue().size()))
                .forEach(m -> sorted.put(m.getKey(), m.getValue()));
        reversed(sorted);
        return sorted;
    }

    public LinkedHashMap<String, List<String>> getProgsMapSortedByNumOfLangs() {
        LinkedHashMap<String, List<String>> progs = getProgsMap();
        LinkedHashMap<String, List<String>> sortedMap = new LinkedHashMap<>();

        progs.entrySet().stream()
                .sorted(Comparator.comparingInt(a -> a.getValue().size()))
                .forEach(a-> sortedMap.put(a.getKey(), a.getValue()));
        reversed(sortedMap);
        return sortedMap;
    }

    public LinkedHashMap<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int num) {
        LinkedHashMap<String, List<String>> progs = getProgsMap();
        return progs.entrySet().stream()
                .filter(entry -> entry.getValue().size() > num)
                .collect(LinkedHashMap::new, (m, arr) -> m.put(arr.getKey(), arr.getValue()), Map::putAll);
    }

    public static <T, X> Map<T, X> sorted(Map<T, X> map, Comparator<Map.Entry<T, X>> comparator) {
        return map.entrySet().stream()
                .sorted(comparator)
                .collect(LinkedHashMap::new, (m, arr) -> m.put(arr.getKey(), arr.getValue()), Map::putAll);
    }

    public static <T, X> Map<T, X> filtered(Map<T, X> map, Predicate<Map.Entry<T, X>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .collect(LinkedHashMap::new, (m, arr) -> m.put(arr.getKey(), arr.getValue()), Map::putAll);
    }
}

