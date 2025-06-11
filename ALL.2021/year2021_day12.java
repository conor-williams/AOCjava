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


///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2021_day12 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static Map <String, Vector<String>> mp = new HashMap<>();
	public static void main(String [] args) {
		out.println("		2021 Day12.1");
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
                /*sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                already = new int[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([A-Za-z]+)-([A-Za-z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		// XW-ed
		int lower[] = new int[26];
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			out.println(blah.get(i));
			String left = m.group(1);
			Vector <String> right = new Vector<>();
			if (mp.containsKey(left)) {
				right = mp.get(left);
			}
			right.add(m.group(2));
			mp.put(left, new Vector<>(right));
			///////////
			Vector <String> right2 = new Vector<>();
			if (mp.containsKey(m.group(2))) {
				right2 = mp.get(m.group(2));
			}	
			right2.add(m.group(1));
			mp.put(m.group(2), new Vector<>(right2));
		}
		
                for (Map.Entry<String, Vector<String>> entry : mp.entrySet()) {
                            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		String start = "start";
		Vector <String> pa = new Vector<>();
		pa.add(start);
		travel(start, pa);
		
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(numPaths);
		out.println("");
	}
	public static Map<String, Integer> mpLower = new HashMap<>();
	public static int numPaths = 0;
	public static void travel(String from, Vector<String> path) {
		out.println(path);
		Set<String> uniq2 = new HashSet<>();
		Set<String> dup2 = new HashSet<>();
		for (String str: path) {
			if (str.chars().allMatch(Character::isLowerCase)) {
				if (!uniq2.add(str)) {
					dup2.add(str);
				}
			}
		}
		out.println(dup2);
		if (dup2.size() > 0) {return;}
		if (from.equals("end")) {numPaths++; return;}

		var to = mp.get(from);
		for (int ii = 0; ii < to.size(); ii++) {
			if (to.get(ii).equals("start")) {continue;}
			Vector <String> path2 = new Vector<>(path);
			path2.add(to.get(ii));
			travel(to.get(ii), path2);
		}
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
