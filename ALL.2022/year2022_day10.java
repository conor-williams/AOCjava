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
class year2022_day10 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day10.1");
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
		Pattern p = Pattern.compile("addx (-?\\d+)");
		//Pattern p2 = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		long cycles = 0;
		long x = 1;
		long tot = 0;
		int done = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			done = 0;
			if (ne.charAt(0) == 'n') {
				cycles++;
				if (cycles == 20) {tot += cycles*x;done=1;} else if ((cycles-20) % 40 == 0) {out.print(cycles); out.print(" "); out.println(x);tot += cycles*x; done=1;}
				continue;
			} else {
				Matcher m = p.matcher(ne);
				m.find();
				cycles++;
				if (cycles == 20) {tot += cycles*x;done=1;} else if ((cycles-20) % 40 == 0) {out.println(cycles); out.print(" "); out.println(x); tot += cycles*x;done=1;}
				cycles++;
				if (cycles == 20) {tot += cycles*x;done=1;} else if ((cycles-20) % 40 == 0) {out.println(cycles); out.print(" "); out.println(x); tot += cycles*x;done=1;}
				x += Integer.valueOf(m.group(1));
			}
			/*
			if (done == 0 && cycles == 20 || (cycles-20) %40 == 0) {
				out.println("cyc:"); out.println(cycles);
				out.println("ERR"); 
				Runtime.getRuntime().halt(0);
			}
			*/
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
		if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

