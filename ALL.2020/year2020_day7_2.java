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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2020_day7_2 {
	public static Map <String, Vector <String>> mp = new HashMap<>();
	public static Map <String, Vector <Integer>> mpVal = new HashMap<>();
	public static Set <String> already = new HashSet<>();
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day7.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		Pattern p = Pattern.compile("([a-z\\s]+) bags contain ([\\da-z\\s,.]+)");
		Pattern p2 = Pattern.compile("(\\d+) ([a-z\\s.]+) bag");

		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String mainBag = m.group(1);
			String rest = m.group(2);
			//out.println(rest);

			Vector <String> hasBags = new Vector<>();
			Vector <Integer> bagVal = new Vector<>();
			if (rest.equals("no other bags.") || rest.contains("no other")) {
				//ignore
			} else {
				Scanner scanner = new Scanner(rest);
				scanner.useDelimiter("[,.]");
				while (scanner.hasNext()) {
					String ne = scanner.next();
					//out.println(ne);
					Matcher m2 = p2.matcher(ne);
					m2.find();
					bagVal.add(Integer.valueOf(m2.group(1)));
					//out.println(m2.group(1));// out.println(m2.group(2));

					hasBags.add(trans(m2.group(2)));
				}
				mp.put(trans(mainBag), hasBags);
				mpVal.put(trans(mainBag), bagVal);
			}
		}

		int tot = countBags("shinygold");


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int countBags(String in) {
		if (!mp.containsKey(in)) {return 0;}
		int tot1 = 0;
		var bags = mp.get(in);
		var bagsNum = mpVal.get(in);
		for (int ii = 0; ii < bags.size(); ii++) {
			//if (!already.contains(bags.get(ii))) 
			tot1 += bagsNum.get(ii);
			{
				already.add(bags.get(ii));
				//tot1 += bagsNum.get(ii);
				int mul = bagsNum.get(ii);
				out.print("mul: "); out.print(mul); out.print(" "); out.println(bags.get(ii));
				//out.println(bags.get(ii));
				int val = countBags(bags.get(ii));
				if (val != 0) {
					tot1 += mul * val;
				}
			}
		}
		return tot1;
	}
	public static String trans(String in) {
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter("[ ]");
		char [] onelet = new char[20];
		int pos = 0;
		String ke = "";
		while (scanner.hasNext()) {
			String ne = scanner.next();
			if (ne.equals("")) {continue;}
			ke = ke.concat(ne);
		}
		ke = ke.replaceAll("[\\s \\t,.]+", "");
		return ke;
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

