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

class year2016_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day7.2");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\\[\\]]");
			int evenodd = 0;
			Vector <String>  partsGood = new Vector<>();
			Vector <String> partsBad = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				if (evenodd % 2 == 0) { 
					partsGood.add(ne);
				} else {
					partsBad.add(ne);
				}
				evenodd++;
			}
			Vector <String> abbaGood = new Vector <> ();
			Vector <String> abbaFlip = new Vector <> ();
			for (int ii = 0; ii < partsGood.size(); ii++) {
				String pbG = partsGood.get(ii);
				for (int kk = 0; kk < pbG.length()-2; kk++) {
					if (pbG.charAt(kk) == pbG.charAt(kk+2) && pbG.charAt(kk) != pbG.charAt(kk+1)) {
						abbaGood.add(pbG.substring(kk, kk+3));
						char [] abG = new char[4];
						abG[0] = pbG.charAt(kk+1);
						abG[2] = pbG.charAt(kk+1);
						abG[1] = pbG.charAt(kk);

						//out.print("here1: "); out.println(pbG.substring(kk, kk+3));
						String abGS = new String(abG, 0, 3);
						abbaFlip.add(abGS);
						//out.print("here2: "); out.println(abGS);
					}
				}
			}


			Vector <String> abbaBad = new Vector <> ();
after:
			for (int ii = 0; ii < partsBad.size(); ii++) {
				//out.println(partsBad.get(ii));
				String pbB = partsBad.get(ii);
				for (int kk = 0; kk < abbaFlip.size(); kk++) {
					if (pbB.contains(abbaFlip.get(kk))) {
						tot++;
						break after;
					}
				}
			}
			/*
			abbaGood.removeAll(abbaBad);
			if (abbaGood.size() > 0) {
				for (int ii = 0; ii < abbaGood.size(); ii++) {
					out.println(abbaGood.get(ii));
				}
				out.println("tot++");
				tot++;
			}
			*/
		}
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

