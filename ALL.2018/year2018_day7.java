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
import java.util.Vector;
import java.util.stream.Collectors;
// /java -Xmx2g year2019_day3.java *i1.txt


//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2018_day7 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day7.1");
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
		Pattern p = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin.");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <Character, Vector <Character>> mp = new HashMap<>();
		Vector <Character> charList = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			char be = m.group(1).charAt(0);
			char af = m.group(2).charAt(0);
			if (mp.containsKey(af)) {
				Vector <Character> tmp1 = mp.get(af);
				tmp1.add(be);
				mp.put(af, tmp1);
			} else {
				Vector <Character> tmp1 = new Vector<>();
				tmp1.add(be);
				mp.put(af, tmp1);
			}

			if (!charList.contains(be)) {
				charList.add(be);
			} 
			if (!charList.contains(af)) {
				charList.add(af);
			}
		}

		Vector <Character> vAns = new Vector<>();
		Character first = 'A';

		Vector <Character> already = new Vector<>();
		while (1 == 1) {
			Vector <Character> poss = new Vector<>();
			for (int ii = 0; ii < charList.size(); ii++) {
				if (!mp.containsKey(charList.get(ii)) && !already.contains(charList.get(ii))) {
					poss.add(charList.get(ii));
				}
			}
			if (poss.size() == 0) {break;}
			poss = poss.stream().sorted().collect(Collectors.toCollection(Vector::new));

			out.print("poss: ");
			for (int kk = 0; kk < poss.size(); kk++) {
				out.print(" "); out.print(poss.get(kk));
			}
			//if (possSorted.size() == 0) {break;}
			out.println();
			first = poss.get(0);
			already.add(first);
			vAns.add(first);

			for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
				// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
				Vector <Character> en = entry.getValue();
				int end = en.size();
				for (int jj = 0; jj < en.size(); jj++) {
					if (en.get(jj) == first) {
						en.remove(jj);
						break;
					}
				}
				if (en.size() == 0) {
					out.print("en empty: ");
					out.println(entry.getKey());
				} else {
				}
			}
			mp.entrySet().removeIf(entry -> entry.getValue().size() == 0);
		}


		char [] ansC = new char[30];
		for (int ii = 0; ii < vAns.size(); ii++) {
			ansC[ii] = vAns.get(ii);
		}
		String ans = new String(ansC, 0, 26);
		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(ans);
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

