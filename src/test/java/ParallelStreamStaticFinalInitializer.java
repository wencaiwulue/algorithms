import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamStaticFinalInitializer {
    @Test
    public void testStream() {
        String s = Solution.STRUCT_MAP.get("");
        // stuck here
        System.out.println(s);
    }

    static class Solution {
        static final Map<String, String> STRUCT_MAP = Stream.of("a", "b", "c")
                .parallel()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(e -> e, e -> e, (o1, o2) -> o1));
    }
}
