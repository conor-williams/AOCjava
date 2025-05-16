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
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.*;
import java.util.stream.*;
// /java -Xmx2g year2019_day3.java *i1.txt


class year2016_day4 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day4.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		String fp = Pattern.quote("[");
		String sp = Pattern.quote("]");
		Pattern p = Pattern.compile("([a-z\\-0-9]+)-(\\d+)"+fp+"([a-z]+)"+sp);
		//Pattern p2 = Pattern.compile("([a-z\\-]+)-(\\d+)"+fp+"([a-z]+)"+sp);
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Map<Character, Integer> letters = new HashMap<>();
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String part1 = m.group(1);
			String partid = m.group(2);
			String part2 = m.group(3);
			//out.println(part1); out.println(partid); out.println(part2);
			for (int ii = 0; ii < part1.length(); ii++) {
				Character ch = part1.charAt(ii);
				int val = 0;
				if (letters.containsKey(ch)) {
					val = letters.get(ch);
				}
				if (Character.isLowerCase(ch)) {
					letters.put(ch, val+1);
				}
			}
			Map<Character, Integer> sortedMap = sortByValueAndKey(letters);
			//sortedMap.forEach((key, value) -> System.out.println(key + " -> " + value));

			int jj = 0;
			int found = 0;
			for (Character keyinmap: sortedMap.keySet()) {
				//out.print(keyinmap); out.print(" --- "); out.println(part2.charAt(jj));
				if (part2.charAt(jj) != keyinmap) {found = 1; break;}
				jj++;
				if (jj == 5) {break;}
			}
			if (found == 0) {tot+=Integer.valueOf(partid);}
		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static Map<Character, Integer> sortByValueAndKey(Map<Character, Integer> map) {
		// Use a stream to sort the entries
		return map.entrySet()
			.stream()
			.sorted((entry2, entry1) -> {
				// Compare by value first
				int valueComparison = entry1.getValue().compareTo(entry2.getValue());
				if (valueComparison != 0) {
					return valueComparison;
				}
				// If values are equal, compare by key lexicographically
				return entry2.getKey().compareTo(entry1.getKey());
			})
		// Collect into a LinkedHashMap to maintain order
		.collect(LinkedHashMap::new, (mapAcc, entry) -> mapAcc.put(entry.getKey(), entry.getValue()), Map::putAll);
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

