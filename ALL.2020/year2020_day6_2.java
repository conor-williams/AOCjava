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
//System.setOut(originalOut);
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2020_day6_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day6.2");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int x = 1;
		int y = 1;
		int [] ar = new int[blah.size()];
		
		Vector <Character> prev = new Vector<>();
		/*
		String xx = blah.get(0);
		for (int ii = 0; ii < xx.length(); ii++) {
			prev.add(xx.charAt(ii));
		}
		*/

		Vector <Vector <Vector <Character>>> groups = new Vector<>();
		Vector <Vector <Character>> gro = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);	
			out.print("incoming string: "); out.println(ne);
			if (ne.length() == 0) {
				out.println("len zero");
				Vector <Vector <Character>> groTmp = new Vector<>(gro);
				groups.add(groTmp);
				out.print("group size: "); out.println(groTmp.size());
				gro.clear();
				continue;
			}

			Vector <Character> vetmp = new Vector<>();
			for (int ii = 0; ii < ne.length(); ii++) {
				vetmp.add(ne.charAt(ii));
			}
			Vector <Character> vetmp2 = new Vector<>(vetmp);
			out.print("a line of group"); out.println(vetmp2.size());
			gro.add(vetmp2);
		}

		if (gro.size() > 0) {
			Vector <Vector <Character>> groTmp2 = new Vector<>(gro);
			groups.add(groTmp2);
		}


		int tot = 0;
		out.println(groups.size());
		for (int ii = 0; ii < groups.size(); ii++) {
			Vector <Vector <Character>> groTmp = groups.get(ii);
			out.println(groTmp.size());
			
			//Set <Character> aa = new HashSet<>();
			Vector <Character> aa = new Vector<>(groTmp.get(0));
			for (int jj = 1; jj < groTmp.size(); jj++) {
				aa.retainAll(groTmp.get(jj));
			}
			tot += aa.size();
		}
		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(tot);
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

