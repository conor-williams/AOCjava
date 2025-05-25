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
// /java -Xmx2g year2019_day3.java *i1.txt


//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2015_day7 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day7.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("([a-z]+|\\d+) -> ([a-z]+)");
		Pattern p2 = Pattern.compile("([a-z]+|\\d+) (AND|OR|LSHIFT|RSHIFT) ([a-z]+|\\d+) -> ([a-z]+)");
		Pattern p3 = Pattern.compile("NOT ([a-z]+|\\d+) -> ([a-z]+)");
		//	month= m.group(2);

		Map <String, Integer> mp = new HashMap<>();
		int restart = 1;
		while (restart == 1) {
			for (int i = 0; i < blah.size(); i++) {
				out.println(blah.get(i));
				Matcher m1 = p1.matcher(blah.get(i));
				Matcher m2 = p2.matcher(blah.get(i));
				Matcher m3 = p3.matcher(blah.get(i));
				if (m2.find()) {
					int fir = 0;
					int sec = 0;

					if (Character.isDigit(m2.group(1).charAt(0))) { 
						fir = Integer.valueOf(m2.group(1));
					} else {
						if (mp.containsKey(m2.group(1))) {
							fir = mp.get(m2.group(1));
						} else {
							continue;
						}
					}
					if (Character.isDigit(m2.group(3).charAt(0))) { 
						sec = Integer.valueOf(m2.group(3));
					} else {
						if (mp.containsKey(m2.group(3))) {
							sec = mp.get(m2.group(3));
						} else {
							continue;
						}
					}
					final int f1 = fir;
					final int s1 = sec;
					switch (m2.group(2).charAt(0)) {
						case 'A':
							mp.compute(m2.group(4), (key,val) -> val==null ? f1 & s1 : f1 & s1);
							break;
						case 'O':
							mp.compute(m2.group(4), (key, val) -> val==null? f1 | s1 : f1 | s1);
							break;
						case 'L':
							mp.compute(m2.group(4), (key, val) -> val == null? f1 << s1 : f1 << s1);
							break;
						case 'R':
							mp.compute(m2.group(4), (key, val) -> val == null? f1 >> s1: f1 >> s1);
							break;
						default:
							out.println("ERROR");
							Runtime.getRuntime().halt(0);
					}
				} else if (m3.find()) {
					int fir = 0;
					if (Character.isDigit(m3.group(1).charAt(0))) { 
						fir = Integer.valueOf(m3.group(1));
						out.println(fir);
					} else {
						if (mp.containsKey(m3.group(1))) {
							fir = mp.get(m3.group(1));
						} else {
							continue;
						}
						out.println(fir);
					}
					//out.print("NOT, int value: "); out.println(fir);
					int f2 = ~fir;// & 0xff;
					String xx1 = Integer.toUnsignedString(f2);
					BigInteger xx2 = new BigInteger(xx1);
					BigInteger xx3 = xx2.and(new BigInteger("65535"));
					int xx4 = xx3.intValue();
					///out.println(xx4);
					final int f1 = xx4;
					mp.compute(m3.group(2), (key, val) -> val == null ? f1: f1);

					} else if (m1.find()) {
						int fir = 0;
						if (Character.isDigit(m1.group(1).charAt(0))) { 
							fir = Integer.valueOf(m1.group(1));
						} else {
							out.println(m1.group(1));
							if (mp.containsKey(m1.group(1))) {
								fir = mp.get(m1.group(1));
							} else {
								continue;
							}
						}
						final int vv = fir;
						mp.compute(m1.group(2), (key, val) -> (val == null) ? vv : vv);
						} else {
							out.println("NOT FOUND ERROR");
							Runtime.getRuntime().halt(0);
						}

			}
			restart = 0;
			if (!mp.containsKey("a")) {
				restart = 1;
			}
		}
		mp.entrySet().forEach(entry -> {
			out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()); });
		//mp.keySet().forEach(key -> {out.println("Key: " + key);});
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(mp.get("a"));
		out.println("");
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

