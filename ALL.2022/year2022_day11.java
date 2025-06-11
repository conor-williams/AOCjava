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
class year2022_day11 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day11.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
		//int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Monkey (\\d+):");
		Pattern p2 = Pattern.compile("Starting items: ([\\d+,?\\s*]+)");
		Pattern p3 = Pattern.compile("Operation: new = old (\\*|\\+) (old|\\d+)");
		Pattern p4 = Pattern.compile("Test: divisible by (\\d+)");
		Pattern p5 = Pattern.compile("If true: throw to monkey (\\d+)");
		Pattern p6 = Pattern.compile("If false: throw to monkey (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");

		Monkey monkeys[] = new Monkey[10];
		for (int ii = 0; ii < 10; ii++) {
			monkeys[ii] = new Monkey();
		}
		int monkeyNum = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			Matcher m2 = p2.matcher(ne);
			Matcher m3 = p3.matcher(ne);
			Matcher m4 = p4.matcher(ne);
			Matcher m5 = p5.matcher(ne);
			Matcher m6 = p6.matcher(ne);
			if (m.find()) {
				monkeyNum = Integer.valueOf(m.group(1));
				monkeys[monkeyNum].number = monkeyNum;
				continue;
			} else if (m2.find()) {
				out.println(ne);
				Scanner scanner = new Scanner(m2.group(1));
				out.println(m2.group(1));
				scanner.useDelimiter("[\t\\s,]+");
				Vector <Integer> var_ints = new Vector<>();
				while (scanner.hasNext()) {
					String ne2 = scanner.next();
					out.print("one: "); out.println(Integer.valueOf(ne2));
					var_ints.add(Integer.valueOf(ne2));
				}
				monkeys[monkeyNum].starts = new Vector<>(var_ints);
				continue;
			} else if (m3.find()) {
				out.print("opval1  "); out.println(m3.group(1));
				out.print("opval2  "); out.println(m3.group(2));
				monkeys[monkeyNum].plusmul = m3.group(1);
				monkeys[monkeyNum].opVal = m3.group(2);
				continue;
			} else if  (m4.find()) {
				monkeys[monkeyNum].divby = Integer.valueOf(m4.group(1));
			} else if (m5.find()) {
				monkeys[monkeyNum].monkeytrue = Integer.valueOf(m5.group(1));
				continue;
			} else if (m6.find()) {
				monkeys[monkeyNum].monkeyfalse = Integer.valueOf(m6.group(1));
				continue;
			} else if (ne.length() == 0) {
				continue;	
			} else {
				out.println("no here");
				out.println(ne);
			}

		}
		monkeyNum++;

		for (int i = 0; i < monkeyNum; i++) {
			out.println(monkeys[i].times);
			out.println(monkeys[i].number);
			for (int ii = 0; ii < monkeys[i].starts.size(); ii++) {
				out.print(" "); out.print(monkeys[i].starts.get(ii));
			}
			out.println();
			out.println(monkeys[i].plusmul);
			out.println(monkeys[i].opVal);
			out.println(monkeys[i].divby);
			out.println(monkeys[i].monkeytrue);
			out.println(monkeys[i].monkeyfalse);
		}
		//run the state machine...

		for (int ro = 0; ro < 20; ro++) {
			for (int ii = 0; ii < monkeyNum; ii++) {
				for (int zz = 0; zz < monkeys[ii].starts.size(); zz++) {
					monkeys[ii].times++;
					int val = monkeys[ii].starts.get(zz);
					monkeys[ii].starts.remove(zz);
					zz--;
					int vv = 0;
					if (monkeys[ii].opVal.equals("old")) {
						vv = val;
					} else {
						vv = Integer.valueOf(monkeys[ii].opVal);
					}
					if (monkeys[ii].plusmul.equals("+")) {
						val += vv;
					} else if (monkeys[ii].plusmul.equals("*")) {
						val *= vv;
					}
					val /= 3;

					if (val % monkeys[ii].divby == 0) {
						int to = monkeys[ii].monkeytrue;
						monkeys[to].starts.add(val);
					} else {
						int to = monkeys[ii].monkeyfalse;
						monkeys[to].starts.add(val);
					}

				}
			}
			/*
			out.print("round"); out.println(ro);
			for (int ii = 0; ii < monkeyNum; ii++) {
				out.print("monkey"); out.print(ii);
				for (int qqq = 0; qqq < monkeys[ii].starts.size(); qqq++) {
					out.println(monkeys[ii].starts.get(qqq));
				}
			}
			*/
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		}
		Vector <Integer> ti = new Vector<>();
		for (int ii = 0; ii < monkeyNum; ii++) {
			out.println(monkeys[ii].times);
			ti.add(monkeys[ii].times);
		}
		Collections.sort(ti, Collections.reverseOrder());

		long ans = ti.get(0) * ti.get(1);

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
}

class Monkey {
	public int times;
	public int number;
	public Vector <Integer> starts;
	public String plusmul;
	public String opVal;
	public int divby;
	public int monkeytrue;
	public int monkeyfalse;
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

