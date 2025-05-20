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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2020_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day5.2");
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
		Pattern p = Pattern.compile("([FB]{7})([LR]{3})");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int maxPass = 0;
		Vector <Integer> possids = new Vector<>();
		for (int zz = 12; zz <= 111; zz++) {
			for (int qq = 0; qq <= 7; qq++) {
				possids.add((zz * 8) + qq);
			}
		}
		Vector <Integer> actids = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
		//	out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String fb = m.group(1);
			String lr = m.group(2);

			int be = 0;
			int en = 127;
			for (int ii = 0; ii < fb.length(); ii++) {
				int half = (en-be)/2;
				if (fb.charAt(ii) == 'F') {
					//be = be;
					en -= half+1;
				} else {
					be += half+1;
				}
				//out.print(be); out.print(" to "); out.println(en);
			}

			int lrbe = 0;
			int lren = 7;
			for (int ii = 0; ii < lr.length(); ii++) {
				int half = (lren-lrbe)/2;
				if (lr.charAt(ii) == 'L') {
					lren -= half+1;
				} else {
					lrbe += half+1;
				}
			}

			int val = (be * 8) + lrbe;
			actids.add(val);
			//int val = 0; if ( (val = ((be * 8) + lrbe)) > maxPass) { maxPass = val;}
		}
		//out.println(possids.size()); out.println(actids.size());
		//Collections.sort(actids); Collections.sort(possids);
		/*
		HashSet<Integer> se = new HashSet<>(possids);
		se.retainAll(actids);
		out.println(se);
		*/
		Iterator <Integer> itr = possids.iterator();	
		int ans = 0;
		while (itr.hasNext()) {
			int val = 0;
			if (!actids.contains(val = itr.next())) {
				ans = val;
				//out.println(val);
			}
		}
		
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

