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

class year2017_day7 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day7.1");
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

//jlbcwrl (93)
///fzqsahw (256) -> lybovx, pdmhva

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("([a-z]+) \\((\\d+)\\) -> ([a-z\\s,]+)");
		Pattern p2 = Pattern.compile("([a-z]+) \\((\\d+)\\)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <String, Vector<String>> mpAft = new HashMap<>();
		Map <String, Vector<String>> mpBef = new HashMap<>();
		Map <String, Integer> mpVals = new HashMap<>();
		Set <String> seElems = new HashSet<>();

		for (int i = 0; i < blah.size(); i++) {
			Matcher m1 = p1.matcher(blah.get(i));
			Matcher m2 = p2.matcher(blah.get(i));
			//out.println(blah.get(i));

			if (m1.find()) {
				String elem = m1.group(1);
				Integer value = Integer.valueOf(m1.group(2));
				String rest = m1.group(3);
				Scanner scanner = new Scanner(rest);
				scanner.useDelimiter("[\\t\\s,]+");
				Vector <String> ve = new Vector<>();
				while (scanner.hasNext()) {
					String ne = scanner.next();
					ve.add(ne);
					Vector <String> ve2 = new Vector<>();
					if (mpBef.containsKey(ne)) {
						ve2 = mpBef.get(ne);
					}
					if (!ve2.contains(elem)) {
						ve2.add(elem);
					}
					mpBef.put(ne, ve2);
				}
				mpAft.put(elem, ve);
				mpVals.put(elem, value);
				seElems.add(elem);
			} else if (m2.find()) {
				String elem = m2.group(1);
				Integer value = Integer.valueOf(m2.group(2));
				Vector <String> ve = new Vector<>();
				mpAft.put(elem, ve);
				mpVals.put(elem, value);
				seElems.add(elem);
			}
		}
		/*
		out.println(mpAft.size());
		out.println(mpBef.size());
		out.println(mpVals.size());
		out.println(seElems.size());
		*/
		String root = "";
		for (String elem: seElems) {
			if (!mpBef.containsKey(elem)) {
				root = elem;
				break;
			}
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(root);
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

