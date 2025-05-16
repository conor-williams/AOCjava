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
import java.util.List;
import java.util.ArrayList;
// /java -Xmx2g year2019_day3.java *i1.txt


class year2017_day4_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day4.2");
		out.println("	SLOW 1minute49seconds");
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
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\\s]+");
			Vector <String> ve = new Vector<>();
			while (scanner.hasNext()) {
				ve.add(scanner.next());
			}
			int found = 0;
			Vector <Set<String>> anas = new Vector<>();
			for (int ii = 0; ii < ve.size(); ii++) {
				anas.add(generateAnagrams(ve.get(ii)));
			}
			//List<String> list = new ArrayList<Foo>(set); Foo obj = list.get(0);
after:
			for (int ii = 0; ii < anas.size()-1; ii++) {
				Set <String> a1 = anas.get(ii);
				List <String> l1 = new ArrayList<String>(a1);
				for (int jj = ii+1; jj < anas.size(); jj++) {
					//out.print(ve.get(ii)); out.print(" V "); out.println(ve.get(jj));
					Set <String> a2 = anas.get(jj);
					List <String> l2 = new ArrayList<String>(a2);
					for (int kk = 0; kk < l1.size(); kk++) {
						for (int ll = 0; ll < l2.size(); ll++) {
							if (l1.get(kk).equals(l2.get(ll))) {
								found = 1;
								break after;
							}
						}
					}
				}
			}
			if (found == 0) {
				tot++;
			}

		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}

	public static Set<String> generateAnagrams(String str) {
		Set<String> result = new HashSet<>();
		permute("", str, result);
		return result;
	}

	private static void permute(String prefix, String remaining, Set<String> result) {
		if (remaining.isEmpty()) {
			result.add(prefix);
		} else {
			for (int i = 0; i < remaining.length(); i++) {
				permute(prefix + remaining.charAt(i),
						remaining.substring(0, i) + remaining.substring(i + 1),
						result);
			}
		}
	}
}

public class Tuple<X,Y > {
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

