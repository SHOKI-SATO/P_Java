package projava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSample1 {
    public static void main(String[] args) {
        var data = List.of("yamamoto", "kis", "sugiyama");
        var result = data.stream().filter(s -> s.length() >= 5).collect(Collectors.toCollection(ArrayList::new));

        System.out.println(result);
    }
}
