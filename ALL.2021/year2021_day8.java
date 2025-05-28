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

class year2021_day8 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day8.1");
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

		Pattern p = Pattern.compile("([a-z\\s]+)\\|([a-z\\s]+)");
		long tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String left = m.group(1);
			String right = m.group(2);

			//out.println(blah.get(i)); out.println(left); out.println(right);
			Vector <String> leftNums = new Vector <>();
			Scanner scanner = new Scanner(left);
			scanner.useDelimiter("[\t\\s ]+");
			while (scanner.hasNext()) {
			   String ne = scanner.next();
			   leftNums.add(ne);
			}
			out.println(leftNums.size());

			Vector <String> rightNums = new Vector <>();
			Scanner scanner2 = new Scanner(right);
			scanner2.useDelimiter("[\t\\s ]+");
			while (scanner2.hasNext()) {
				String ne = scanner2.next();
				rightNums.add(ne);
			}
			out.println(rightNums.size());

			for (int kk = 0; kk < rightNums.size(); kk++) {
				out.println(rightNums.get(kk));
				String bb = rightNums.get(kk);
				if (bb.length() == 2 || bb.length() == 4 || bb.length() == 7 || bb.length() == 3) {
					tot++;
				}
			}

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

