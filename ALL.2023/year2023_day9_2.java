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
class year2023_day9_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day9.2");
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
		long tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\t\\s ]+");
			Vector <Integer> [] vv = new Vector[100];
			for (int ii = 0; ii < 100; ii++) {
				vv[ii] = new Vector<>();
			}
			while (scanner.hasNext()) {
				String ne = scanner.next();
				vv[0].add(Integer.valueOf(ne));
				//out.println(Integer.valueOf(ne));
			}
			Collections.reverse(vv[0]);
			/*
			for (int ii = 0; ii < vv[0].size(); ii++) {
				out.print(vv[0].get(ii)); out.print(" ");
			} 
			out.println();
			*/

			for (int jj = 0; jj < 100-1; jj++) {
				for (int kk = 0; kk < vv[jj].size()-1; kk++) {
					vv[jj+1].add(vv[jj].get(kk) - vv[jj].get(kk+1));
				}
				/*
				for (int ii = 0; ii < vv[jj+1].size(); ii++) {
					out.print(vv[jj+1].get(ii)); out.print(" ");
				} 
				out.println();
				*/
				int found = 0;
				for (int kk = 0; kk < vv[jj+1].size(); kk++) {
					if (vv[jj+1].get(kk) != 0) {
						found = 1;
						break;
					}
				}
				if (found == 0) {
					int ans = vv[jj+1].get(vv[jj+1].size()-1);
					assert(ans == 0);
					for (int zz = jj; zz >=0; zz--) {
						ans = vv[zz].get(vv[zz].size()-1) - ans;
					}
					//out.println(ans);
					tot += ans;
					break;
				}
			}

		}
		//		System.setOut(originalOut);
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

