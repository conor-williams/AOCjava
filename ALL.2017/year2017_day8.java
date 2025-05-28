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

class year2017_day8 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day8.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([a-z]+) (inc|dec) (-?\\d+) if ([a-z]+) (>|<|>=|<=|==|!=) (-?\\d+)");
		/*
		   uz inc 134 if hx > -10
		   qin dec -300 if h <= 1
		   ubi inc 720 if qin <= 306
		   si inc -108 if he <= 1
		   hx inc 278 if hx <= -10
		   */
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <String, Integer> mp = new HashMap<>();
		for (int i = 0; i < blah.size(); i++) {
			//out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();

			String reg1 = m.group(1);
			String incdec = m.group(2);
			Integer incdecby = Integer.valueOf(m.group(3));

			String ifreg2 = m.group(4);
			String iftodo = m.group(5);
			Integer ifby = Integer.valueOf(m.group(6));
			int val = 0;
			if (mp.containsKey(ifreg2)) {
				val = mp.get(ifreg2);
			}

			boolean an = false;
			if (iftodo.equals(">")) {
				if (val > ifby) {
					an = true;
				}
			} else if (iftodo.equals("<")) {
				if (val < ifby) {
					an = true;
				}
			} else if (iftodo.equals(">=")) {
				if (val >= ifby) {
					an = true;
				}
			} else if (iftodo.equals("<=")) {
				if (val <= ifby) {
					an = true;
				}
			} else if (iftodo.equals("==")) {
				if (val == ifby) {
					an = true;
				}
			} else if (iftodo.equals("!=")) {
				if (val != ifby) {
					an = true;
				}
			} else {
				out.println("ERR");
				Runtime.getRuntime().halt(0);
			}
			if (an == true) {
				int vv = 0;
				if (mp.containsKey(reg1)) {
					vv = mp.get(reg1);
				}

				if (incdec.equals("inc")) {
					vv += incdecby;
				} else {
					vv -= incdecby;
				}
				mp.put(reg1, vv);
			}

		}

		Integer m2 = Collections.max(mp.values());
		/*
		out.println(m2);
		   Integer maxValue = 0;
		   for (Map.Entry<String, Integer> entry : mp.entrySet()) {
		   System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		   int vv = entry.getValue();
		   if (vv > maxValue) {maxValue = vv;}
		   }
		   out.println(maxValue);
		   */
		out.print("**j_ans: ");
		out.print(m2);
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

