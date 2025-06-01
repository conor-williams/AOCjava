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
class year2020_day10_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day10.2");
		Vector<String> blah = new Vector<>();
		blah.add("0");
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//PrintStream originalOut = System.out;
		//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Vector <Long> b2 = new Vector<>();
		for (int ii = 0; ii < blah.size(); ii++) {
			b2.add(Long.parseLong(blah.get(ii)));
		}
		Collections.sort(b2);
		long max = b2.get(b2.size()-1);
		long maxP = max+3;
		b2.add(maxP);

   		Map <Long, Long> paths = new HashMap<>();
		paths.put((long)0, (long)1);
		Vector <Long> adapters = new Vector<>(b2);
		for (long adapter : adapters) {
			for (long diff = 1; diff <= 3; diff++) {
				long nextAdapter = adapter + diff;
				if (adapters.contains(nextAdapter)) {
					paths.put(nextAdapter, paths.getOrDefault(nextAdapter, (long)0) + paths.get(adapter));
				}
			}
		}
		//out.println(paths.get(maxVoltage));
		//System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(paths.get(maxP));
		out.println("");
	}
}

/*
# paths[n] is the total paths from 0 to n
paths = defaultdict(int)
paths[0] = 1

for adapter in sorted(adapters):
for diff in range(1, 4):
next_adapter = adapter + diff
if next_adapter in adapters:
paths[next_adapter] += paths[adapter]
print(paths[max_voltage])
*/
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
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

