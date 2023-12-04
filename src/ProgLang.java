import java.io.*;
import java.util.*;

public class ProgLang {
    ArrayList<List<String>> arr;

    public ProgLang(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        this.arr = new ArrayList<>();
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            this.arr.add(List.of(l.split("\t")));
        }
    }
}
