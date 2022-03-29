package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

class BullsCows1 extends BullsCows {
    @Override
    public String getHint(String secret, String guess) {
        int numbers[] = new int[10];
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) bulls++;
            else {
                if (numbers[g] < 0) cows++;
                if (numbers[s] > 0) cows++;
                numbers[s]--;
                numbers[g]++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}

public class BullsCows {
    private Set<Integer>[] buildMap(String s) {
        // map is the locations of 10 digits.
        Set<Integer> map[] = new HashSet[10];
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            Set<Integer> locations = map[digit];
            if (locations == null) {
                locations = new HashSet<>();
                map[digit] = locations;
            }

            locations.add(i);
        }

        return map;
    }

    public String getHint(String secret, String guess) {
        Set<Integer> mapSecret[] = buildMap(secret);
        Set<Integer> mapGuess[] = buildMap(guess);

        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            Set<Integer> s = mapSecret[i];
            Set<Integer> g = mapGuess[i];
            if (s == null || g == null)
                continue;

            Set<Integer> intersection = new HashSet<>(s);
            intersection.retainAll(g);
            bulls += intersection.size();

            s.removeAll(intersection);
            g.removeAll(intersection);
            cows += Math.min(s.size(), g.size());
        }
        return bulls + "A" + cows + "B";
    }

    @Test
    public void test() {
        BullsCows bc = new BullsCows1();
        assertEquals("1A3B", bc.getHint("1807", "7810"));
        assertEquals("1A1B", bc.getHint("1123", "0111"));
        assertEquals("0A0B", bc.getHint("1", "0"));
        assertEquals("1A0B", bc.getHint("1", "1"));
    }
}
