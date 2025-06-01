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
class year2015_day10_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day10.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

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
		long length = 0;
		for (int i = 0; i < blah.size(); i++) {
			//out.println("---------");
			String ne = blah.get(i);	
			int [] an = new int[6000000];
			for (int ii = 0; ii < ne.length(); ii++) {
				an[ii] = ne.charAt(ii) - '0';
			}
			//out.print("ne: "); out.println(ne);
			int counter = 0;
			while (counter < 50) {	
				//out.println(counter);
				int [] anTmp = new int[6000000];
				int pos = 0;
				for (int ii = 0; an[ii] != 0; ii++) {
					int one = an[ii];

					int times = 0;
					for (; an[ii] != 0 ; ii++) {
						if (an[ii] == one) {
							times++;
						} else {
							break;
						}
					}
					///out.print("pos: "); out.print(pos); out.print(" times: ");out.print(times); out.print(" one:"); out.println(one);
					if (times != 0) {
						anTmp[pos] = times;
						anTmp[pos+1] = one;
						pos+=2;
					}
					//out.print("ii: "); out.println(ii);
					ii--;
				}
				an = anTmp.clone();
				counter++;
			}
			length = 0;
			for (int qq = 0; an[qq] != 0; qq++) {
				length++;
			}
			//out.println(length);
		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(length);
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

