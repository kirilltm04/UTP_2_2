import java.io.*;
import java.util.*;

public class ProgLang {
    public HashMap<String, List<String>> map;

    public ProgLang(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        this.map = new HashMap<>();
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            List<String> arr = List.of(l.split("\t"));
            this.map.put(arr.get(0), arr.subList(1, arr.size()));
        }
    }

    public HashMap<String, List<String>> getLangsMap() {
        return this.map;
    }

    public HashMap<String, List<String>> getProgsMap() {
        HashMap<String, List<String>> ans = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> m : this.map.entrySet()) {
            String str = m.getKey();
            List<String> lst = m.getValue();
            for (String programmer : lst) {
                ans.computeIfAbsent(programmer, k -> new ArrayList<>()).add(str);
            }
        }
        return ans;
    }

}
