import java.io.*;
import java.util.*;

public class year2018_day14_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String target = br.readLine().trim();
        br.close();

        int[] pattern = new int[target.length()];
        for (int i = 0; i < target.length(); i++) {
            pattern[i] = target.charAt(i) - '0';
        }

        ArrayList<Integer> recipes = new ArrayList<>();
        recipes.add(3);
        recipes.add(7);

        int elf1 = 0;
        int elf2 = 1;

        while (true) {
            int sum = recipes.get(elf1) + recipes.get(elf2);

            if (sum >= 10) {
                recipes.add(sum / 10);
                if (checkMatch(recipes, pattern)) break;
            }

            recipes.add(sum % 10);
            if (checkMatch(recipes, pattern)) break;

            elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
            elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
        }

        System.out.println(recipes.size() - pattern.length);
    }

    private static boolean checkMatch(List<Integer> recipes, int[] pattern) {
        int n = pattern.length;

        if (recipes.size() < n) return false;

        int start = recipes.size() - n;

        // check last n
        if (matches(recipes, pattern, start)) return true;

        // check last n+1 (because we may have added 2 digits)
        if (recipes.size() > n && matches(recipes, pattern, start - 1)) return true;

        return false;
    }

    private static boolean matches(List<Integer> recipes, int[] pattern, int start) {
        if (start < 0) return false;

        for (int i = 0; i < pattern.length; i++) {
            if (recipes.get(start + i) != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
