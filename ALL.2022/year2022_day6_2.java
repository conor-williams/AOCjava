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


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2022_day6_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day6.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int pos = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);

			for (int ii = 0; ii < ne.length()-14; ii++) {
				String sub1 = ne.substring(ii, ii+14);
				String sorted = Arrays.stream(sub1.split("")).sorted().reduce("", String::concat);
				if             (  sorted.charAt(0) != sorted.charAt(1)  &&
						sorted.charAt(1) != sorted.charAt(2)  &&
						sorted.charAt(2) != sorted.charAt(3)  &&
						sorted.charAt(3) != sorted.charAt(4)  &&
						sorted.charAt(4) != sorted.charAt(5)  &&
						sorted.charAt(5) != sorted.charAt(6)  &&
						sorted.charAt(6) != sorted.charAt(7)  &&
						sorted.charAt(7) != sorted.charAt(8)  &&
						sorted.charAt(8) != sorted.charAt(9)  &&
						sorted.charAt(9) != sorted.charAt(10)  &&
						sorted.charAt(10) != sorted.charAt(11)  &&
						sorted.charAt(11) != sorted.charAt(12)  &&
						sorted.charAt(12) != sorted.charAt(13)) {
							pos = ii+14;
							break;
				}
			}
		}
		out.print("**j_ans: ");
		out.print(pos);
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

