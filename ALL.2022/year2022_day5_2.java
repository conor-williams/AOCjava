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


class year2022_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static List<String> rotate90Clockwise(List<String> input) {
		if (input == null || input.isEmpty()) {
			return new ArrayList<>();
		}

		int rows = input.size();
		int cols = input.get(0).length();
		List<String> rotated = new ArrayList<>();

		for (int col = 0; col < cols; col++) {
			StringBuilder newRow = new StringBuilder();
			for (int row = rows - 1; row >= 0; row--) {
				newRow.append(input.get(row).charAt(col));
			}
			rotated.add(newRow.toString());
		}

		return rotated;
	}
	public static String padString(String str, int size) {
		int padding = size - str.length();
		return padding > 0 ? str + " ".repeat(padding) : str;
	}
	public static void main(String [] args) {
		out.println("		2022 Day5.2");
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
		List <String> initial = new ArrayList<>();
		List<String> rotated = new ArrayList<>();
		int start = 0;
		int maxStr = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			initial.add(ne);
			if (ne.length() > maxStr) {maxStr = ne.length();}
			//out.println(ne);
		//	out.println(ne.charAt(ne.length()-2));
			if (Character.isDigit(ne.charAt(ne.length()-2))) {
				for (int ii = 0; ii < i; ii++) {
					padString(initial.get(ii), maxStr);
				}
				rotated = rotate90Clockwise(initial);
				start = i+2;
				break;
			}
		}
		//rotated.forEach(out::println);
		Vector<Vector <Character>> ve = new Vector <>();
		for (int i = 0; i < rotated.size(); i++) {
			Vector <Character> vv = new Vector <> ();
			String ss = rotated.get(i);
			if (Character.isDigit(ss.charAt(0))) {
				for (int kk = 1; kk < ss.length(); kk++) {
					if (ss.charAt(kk) >= 'A' && ss.charAt(kk) <= 'Z') {
						vv.add(ss.charAt(kk));
					}
				}
				Vector <Character> v2 = new Vector <> (vv);
				ve.add(v2);
			}
		}
		//out.println(ve.size());

		/*
		for (int kk = 0; kk < ve.size(); kk++) {
			Vector <Character> v3 = ve.get(kk);
			for (int ii = 0; ii < v3.size(); ii++) {
				out.print(v3.get(ii)); out.print("-");
			}
			out.println();
		}
		*/


		Pattern p = Pattern.compile("move[\\s]+(\\d+)[\\s]+from[\\s]+(\\d+)[\\s]+to[\\s]+(\\d+)");
		//                           move        1         from        2         to          1
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);
		for (int i = start; i < blah.size(); i++) {
			//out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int howmany = Integer.valueOf(m.group(1));
			int from = Integer.valueOf(m.group(2)) - 1;
			int to = Integer.valueOf(m.group(3)) -1;
			
			Vector <Character> fromV = ve.get(from);
			Vector <Character> toV = ve.get(to);

			Vector <Character> tmpV = new Vector <> ();
			for (int ii = 0; ii < howmany; ii++) {
				Character fromC = fromV.get(fromV.size()-1);
				fromV.remove(fromV.size()-1);
				tmpV.add(fromC);
			}
			for (int ii = howmany-1; ii >= 0; ii--) {
				toV.add(tmpV.get(ii));
			}
			ve.set(from, fromV);
			ve.set(to, toV);
			/*
			for (int kk = 0; kk < ve.size(); kk++) {
				Vector <Character> v3 = ve.get(kk);
				for (int ii = 0; ii < v3.size(); ii++) {
					out.print(v3.get(ii)); out.print("-");
				}
				out.println();
			}
			*/
		}
		Vector <Character> ans = new Vector <> ();
		for (int kk = 0; kk < ve.size(); kk++) {
			Vector <Character> v3 = ve.get(kk);
			ans.add(v3.get(v3.size()-1));
			//for (int ii = 0; ii < v3.size(); ii++) { out.print(v3.get(ii)); out.print("-"); } out.println();
		}

		out.print("**j_ans: ");
		ans.forEach(out::print);
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

