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
class year2021_day10 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day10.1");
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

		long tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			out.println(ne);
			Deque <Character> dek = new ArrayDeque<>();
after:
			for (int ii = 0; ii < ne.length(); ii++) {
				char ch = ne.charAt(ii);
				if (ch == '[' || ch == '(' || ch == '{' || ch == '<') {
					dek.addLast(ch);
				} else if (ch == ']' || ch == ')' || ch == '}' || ch == '>') {
					int notok = 0;
					char val = dek.removeLast();
					switch (ch) {
						case ']':
							if (val != '[') {
								notok = 1;
							}
							break;
						case ')':
							if (val != '(') {
								notok = 1;
							}
							break;
						case '}':
							if (val != '{') {
								notok = 1;
							}
							break;
						case '>':
							if (val != '<') {
								notok = 1;
							}
							break;
					}
					if (notok == 1) {
						out.println("notgood");
						switch (ch) {
							case ']':
								tot += 57;
								break;
							case ')':
								tot += 3;
								break;
							case '}':
								tot += 1197;
								break;
							case '>': 
								tot += 25137;
								break;
						}
						blah.remove(i);
						i--;
						break after;
					}
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
		if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

