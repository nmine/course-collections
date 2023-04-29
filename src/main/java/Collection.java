import java.util.List;
import java.util.stream.Collectors;

public class Collection {

    public void list() {
        List<String> strings = List.of("test");
        strings.stream().collect(Collectors.toList());
    }
}
