package maze.robin;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public Set<Integer> generate(int countOfNumbers, int upperBoundExclude) {
        Set<Integer> randomNumbers = new HashSet<>();
        Random random = new Random();
        while (randomNumbers.size() < countOfNumbers) {
            randomNumbers.add(random.nextInt(upperBoundExclude));
        }
        return randomNumbers;
    }
}
