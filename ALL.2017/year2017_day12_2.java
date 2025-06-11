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
class year2017_day12_2 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static Set <Integer> already = new HashSet<>();
	public static Set <Integer> already2 = new HashSet<>();
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day12.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
		//int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+) <-> ([\\d,\\s]+)");
		Map <Integer, Vector <Integer>> mp = new HashMap<>();
		int maxInt = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			m.find();
			out.println(ne);
			Integer mapPos = Integer.valueOf(m.group(1));

			String rest = m.group(2);

			Scanner scanner = new Scanner(rest);
			scanner.useDelimiter("[\t\\s, ]+");
			Vector <Integer> var_ints = new Vector<>();
			while (scanner.hasNext()) {
				String ne2 = scanner.next();
				var_ints.add(Integer.valueOf(ne2));
			}
			Vector <Integer> tmp1 = new Vector<>(var_ints);
			mp.put(mapPos, tmp1);
			if (mapPos > maxInt) {maxInt = mapPos;}
		}

		Set<Set <Integer>> allSets = new HashSet<>();

		for (int bbb = 0; bbb < maxInt+1; bbb++) {
			Set <Integer> one = new TreeSet<>();
			for (Map.Entry<Integer, Vector<Integer>> entry : mp.entrySet()) {
				already.clear();
				//System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
				var key = entry.getKey();

				if (already2.contains(key)) {continue;}

				var val = entry.getValue();

				if (key == bbb) {
					one.add(key);
					out.println(key);
					if (!already2.contains(key)) {
						already2.add(key);
					}
				} else if (checkHasZero(mp, val, bbb) == true) {
					one.add(key);
					out.println(key);
					if (!already2.contains(key)) {
						already2.add(key);
					}
				} else {
					continue;
				}
				//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			}
			//Collections.sort(one);
			if (one.size() > 0) {
				allSets.add(one);
			}
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(allSets.size());
		out.println("");
	}
	public static boolean checkHasZero(Map <Integer, Vector<Integer>> mp, Vector <Integer> ve,
			int bbb) {
		for (int ii = 0; ii < ve.size(); ii++) {
			if (already.contains(ve.get(ii))) {continue;}
			already.add(ve.get(ii));

			out.println(ve.get(ii));
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			if (ve.get(ii) == bbb) {
				return true;
			} else {
				var ve2 = mp.get(ve.get(ii));
				var ret = checkHasZero(mp, ve2, bbb);
				if (ret == false) {continue;} else {return true;}
			}
		}
		return false;
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
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

