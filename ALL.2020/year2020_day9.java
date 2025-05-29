import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Objects;
import java.util.Collections;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;
import java.lang.*;
// /java -Xmx2g year2019_day3.java *i1.txt


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2020_day9 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day9.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Vector <Long> nums = new Vector<>();
		int numnums = 25;
		for (int i = 0; i < numnums; i++) {
			nums.add(Long.parseLong(blah.get(i)));
		}

		long ans = 0;
		for (int i = numnums; i < blah.size(); i++) {
			//int [] intAr = nums.stream().mapToInt(Integer::intValue).toArray();
			//int [] copy = Arrays.copyOf(intAr, numnums);

			List <Integer> intLi = new ArrayList<>();
			for (int ii = 0; ii < numnums-2; ii++) {
				intLi.add(0);
			}
			intLi.add(1);
			intLi.add(1);
			List <Integer> copy = new ArrayList<>(intLi);

			long toget = Long.parseLong(blah.get(i));

			int found2 = 0;
			do {
				long firstNum = 0;
				long secondNum = 0;
				int found = 0;
				for (int ii = 0; ii < numnums; ii++) {
					int val = intLi.get(ii);
					if (val == 1 && found == 0) {
						firstNum = nums.get(ii);
						found = 1;
					} else if (val == 1 && found == 1) {
						secondNum = nums.get(ii);
						break;
					}
				}
				if (firstNum + secondNum == toget) {
					//onto next blah.get
					found2 = 1;
					break;
				} else {
					//nextperm
				}

				nextPermutation(intLi);
			} while (!intLi.equals(copy));

			if (found2 == 0) {
				ans = toget;
				break;
			}
			
			nums.remove(0);
			nums.add(toget);
			//nums.add(Long.parseLong(blah.get(i)));

		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
        public static boolean nextPermutation(List<Integer> list) {
                int n = list.size();
                if (n <= 1) return false;

                // Step 1: Find the largest index `i` such that list[i] < list[i + 1]
                int i = n - 2;
                while (i >= 0 && list.get(i) >= (list.get(i + 1))) {
                        i--;
                }

                // If no such index exists, the list is in descending order (last permutation)
                if (i < 0) {
                        Collections.reverse(list);
                        return false;
                }

                // Step 2: Find the largest index `j` such that list[j] > list[i]
                int j = n - 1;
                while (list.get(j) <= list.get(i)) {
                        j--;
                }

                // Step 3: Swap elements at indices `i` and `j`
                Collections.swap(list, i, j);

                // Step 4: Reverse the sublist from `i + 1` to the end
                Collections.reverse(list.subList(i + 1, n));

                return true;
        }
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

