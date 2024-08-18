package maze.robin;

import java.util.Set;

public interface NumberGenerator {
    public Set<Integer> generate(int countOfNumbers, int upperBoundExclude);
}
