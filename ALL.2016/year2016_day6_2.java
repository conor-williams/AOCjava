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
// /java -Xmx2g year2019_day3.java *i1.txt


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
class year2016_day6_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day6.2");
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
		int [][] count = new int [10][27];
		int numlets = blah.get(0).length();
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			for (int ii = 0; ii < ne.length(); ii++) {
				int val = ne.charAt(ii) - 'a';
				count[ii][val]++;
				//out.print(ii); out.print(" "); out.println(val);
			}
		}
		int [] min = new int[10];
		for (int ii = 0; ii < numlets; ii++) {
			min[ii] = 99999;
		}

		int [] let = new int[10];
		for (int zz = 0; zz < numlets; zz++) {
			for (int qq = 0; qq < 26; qq++) {
				//out.println(count[zz][qq]);
				if (count[zz][qq] < min[zz] && count[zz][qq] != 0) {min[zz] = count[zz][qq]; let[zz] = qq;}
			}
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		}
		char [] ans = new char[10];
		for (int zz = 0; zz < numlets; zz++) {
			ans[zz] = (char)(let[zz]+'a');
		}
		//out.println(ans);
		String ansS = new String(ans, 0, numlets);

		out.print("**j_ans: ");
		out.print(ansS);
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

