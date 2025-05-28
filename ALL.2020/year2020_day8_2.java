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


//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//		System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2020_day8_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day8.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(acc|jmp|nop) ([-?|+?]\\d+)");

		long acc = 0;
		Vector <String> blahOrig = new Vector<>(blah);
after:
		for (int kk = 0; kk < blahOrig.size(); kk++) {
			blah = new Vector<>(blahOrig);
			if (blah.get(kk).charAt(0) == 'j') {
				String zz = blah.get(kk);
				zz = zz.replaceAll("jmp", "nop");
				blah.set(kk, zz);
			} else if (blah.get(kk).charAt(0) == 'n') {
				String zz = blah.get(kk);
				zz = zz.replaceAll("nop", "jmp");
				blah.set(kk, zz);
			} else {
				continue;
			}

			int [] times = new int[blah.size()];
			acc = 0;
			/*
			out.println("----------");
			for (int i = 0; i < blah.size(); i++) {
				out.println(blah.get(i));
			}
			out.println("----------");
			Scanner scanner = new Scanner(System.in); scanner.nextLine();
			*/
			for (int i = 0; i < blah.size(); i++) {
				Matcher m = p.matcher(blah.get(i));
				m.find();
				if (times[i] >= 20) {
					break;
				}
				//out.println(blah.get(i)); out.println(m.group(1));
				times[i]++;
				if (m.group(1).equals("acc")) {
					acc += Integer.valueOf(m.group(2));
				} else if (m.group(1).equals("jmp")) {
					i = i+Integer.valueOf(m.group(2))-1;
				} else if (m.group(1).equals("nop")) {
					//nothing
				} else {
					out.println("ERR");
					Runtime.getRuntime().halt(0);
				}
				if (i > blah.size()-2) {
					//out.println("valid break valid break");
					break after;
				}
			}
		}
		out.print("**j_ans: ");
		out.print(acc);
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

