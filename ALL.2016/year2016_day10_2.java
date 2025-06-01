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
class year2016_day10_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day10.2");
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
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Vector <Integer> [] bots = new Vector[300];
		int [] donebots = new int[300];
		int [] outputs = new int[300];
		for (int ii = 0; ii < 300; ii++) {
			outputs[ii] = -1;
		}
		Pattern p = Pattern.compile("value (\\d+) goes to bot (\\d+)");
		Pattern p2 = Pattern.compile("bot (\\d+) gives low to (output|bot) (\\d+) and high to (output|bot) (\\d+)");
		for (int ii = 0; ii < 300; ii++) {
			bots[ii] = new Vector<>();
		}

		int maxBot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			if (ne.charAt(0) == 'v') {
				Matcher m = p.matcher(ne);
				m.find();
				out.println(ne);
				int value = Integer.valueOf(m.group(1));
				int bot = Integer.valueOf(m.group(2));
				if (bot > maxBot) {maxBot = bot;}
				bots[bot].add(value);
				blah.remove(i);
				i--;
			}
		}
		long ans = -1;
after:
		while (1 == 1) {
			out.println("while again...");
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			
			for (int i = 0; i < blah.size(); i++) {
				String ne = blah.get(i);
				if (outputs[0] != -1 && outputs[1] != -1 && outputs[2] != -1) {
					ans = outputs[0] * outputs[1] * outputs[2];
					break after;
				}
				if (ne.charAt(0) == 'b') {
					out.println(ne);
					Matcher m = p2.matcher(ne);
					m.find();
					int bot = Integer.valueOf(m.group(1));
					out.print("bot: "); out.print(bot); out.print(" contains ");
					for (int kk = 0; kk < bots[bot].size(); kk++) {
						out.print(bots[bot].get(kk)); out.print(" "); 
					}
					out.println();
					if (bots[bot].size() >= 2 && donebots[bot] == 0) {
						Collections.sort(bots[bot]);
						donebots[bot] = 1;
						if (m.group(2).equals("bot") && m.group(4).equals("bot")) {
							out.println("bot bot");
							bots[Integer.valueOf(m.group(3))].add(bots[bot].get(0));
							bots[Integer.valueOf(m.group(5))].add(bots[bot].get(bots[bot].size()-1));
							bots[bot].remove(bots[bot].size()-1);
							bots[bot].remove(0);
						} else if (m.group(2).equals("bot") && m.group(4).equals("output")) {
							out.println("bot out");
							bots[Integer.valueOf(m.group(3))].add(bots[bot].get(0));
							outputs[Integer.valueOf(m.group(5))] = (bots[bot].get(bots[bot].size()-1));
							bots[bot].remove(bots[bot].size()-1);
							bots[bot].remove(0);
						} else if (m.group(2).equals("output") && m.group(4).equals("bot")) {
							out.println("out bot");
							outputs[Integer.valueOf(m.group(3))] = (bots[bot].get(0));
							bots[Integer.valueOf(m.group(5))].add(bots[bot].get(bots[bot].size()-1));
							bots[bot].remove(bots[bot].size()-1);
							bots[bot].remove(0);
						} else { //output-output
							out.println("out out");
							outputs[Integer.valueOf(m.group(3))] = bots[bot].get(0);
							outputs[Integer.valueOf(m.group(5))] = bots[bot].get(bots[bot].size()-1);
							bots[bot].remove(bots[bot].size()-1);
							bots[bot].remove(0);
						}
					} else {continue;}
				}
			}
			/*
			for (int ii = 0; ii <= maxBot; ii++) {
				if (doneBot[ii] == 0) {continue;} else {break;}
			}
			*/
		}
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

