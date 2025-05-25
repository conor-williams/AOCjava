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

class year2017_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static Map <String, Vector<String>> mpAft = new HashMap<>();
	public static Map <String, Vector<String>> mpBef = new HashMap<>();
	public static Map <String, Integer> mpVals = new HashMap<>();
	public static Set <String> seElems = new HashSet<>();
	public static Set <String> under = new HashSet<>();
	public static void main(String [] args) {
		out.println("		2017 Day7.2");
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
		//for (Map.Entry<String, Vector<String>> entry : mpAft.entrySet()) 
                //	entry.getKey();

		int ans22 = 0;
after:
		{
			var ve1 = mpAft.get(root);
			int [] xx = new int[ve1.size()];
			for (int ii = 0; ii < ve1.size(); ii++) {
				xx[ii] = calcAllUnder(ve1.get(ii));
				out.println(xx[ii]);
			}
			Arrays.sort(xx);
			for (int ii = 0; ii < ve1.size(); ii++) {
				out.print(xx[ii]); out.print(" ");
			}
			out.println();

			int dif = 0;
			int actualVal = 0;
			int shouldBe = 0;
			String actual = "";
			if (xx[0] - xx[1] != 0) {
				out.print("here1: "); out.print(xx[0]); out.print(" "); out.println(xx[1]);
				actualVal = xx[0];
				shouldBe = xx[1];
				dif = xx[1] - xx[0];
			} else {
				out.print("here2: "); out.print(xx[ve1.size()-1]); out.print(" "); out.println(xx[ve1.size()-2]);
				out.println(ve1.size());
				out.println(ve1.size());
				actualVal = xx[ve1.size()-1];
				shouldBe = xx[ve1.size()-2];
				dif = xx[ve1.size()-2] - xx[ve1.size()-1];

			}
			out.print("dif: "); out.println(dif);

			for (int ii = 0; ii < ve1.size(); ii++) {
				int tmp1 = calcAllUnder2(ve1.get(ii));
				if (tmp1 == actualVal) {
					actual = ve1.get(ii);
					break;
				}
			}
			out.println(actual);
			//for all under apply the diff then check xx again...
			Map<String, Integer>mpValOrig = new HashMap<>(mpVals); 
			for (String ss: under) {
				mpVals = new HashMap<>(mpValOrig);
				mpVals.put(ss, mpVals.get(ss)+dif);
				if (calc2(actual) == shouldBe) {
					out.println("got..." + ss);
					ans22 = mpVals.get(ss);
					break after;
				}
			}
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans22);
		out.println("");
	}

	public static Integer calc2(String xx2) {
		int tot = 0;
		tot += mpVals.get(xx2);
		Vector <String> und = mpAft.get(xx2);
		
		int [] yy = new int[und.size()];
		for (int ii = 0; ii < und.size(); ii++) {
			int vv = calc2(und.get(ii));
			yy[ii] = vv;
			tot += vv;
		}
		if (und.size() > 0) {
			for (int ii = 0; ii < und.size()-1; ii++) {
				if (yy[ii] != yy[ii+1]) {
					//out.println("no good...");
					return -1000;
				}
			}
		}
		return tot;
	}

	public static Integer calcAllUnder2(String xx2) {
		int tot = 0;
		tot += mpVals.get(xx2);
		Vector <String> und = mpAft.get(xx2);
		
		for (int ii = 0; ii < und.size(); ii++) {
			//tot += mpVal.get(und.get(ii));
			tot += calcAllUnder2(und.get(ii));
		}
		return tot;
	}
	public static Integer calcAllUnder(String xx2) {
		int tot = 0;
		tot += mpVals.get(xx2);
		under.add(xx2);
		Vector <String> und = mpAft.get(xx2);
		
		for (int ii = 0; ii < und.size(); ii++) {
			//tot += mpVal.get(und.get(ii));
			under.add(und.get(ii));
			tot += calcAllUnder(und.get(ii));
		}
		return tot;
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

