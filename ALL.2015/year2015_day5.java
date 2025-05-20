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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2015_day5 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day5.1");
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
			String ne = blah.get(i);
			int countVowels = 0;
			int dub = 0;
			int consek = 0;
			for (int ii = 0; ii < ne.length(); ii++) { 
				if (ne.charAt(ii) == 'a' || ne.charAt(ii) == 'e' || ne.charAt(ii) == 'i' ||
						ne.charAt(ii) == 'o' || ne.charAt(ii) == 'u') {
					countVowels++;
				}
				if (ii < ne.length()-1) {
					if (dub == 0 && (ne.charAt(ii) - ne.charAt(ii+1) == 0)) {
						dub++;
					}
				}
				if (ii < ne.length()-1) {
					if (consek == 0 && ((ne.charAt(ii) == 'a' && ne.charAt(ii+1) == 'b') ||
							(ne.charAt(ii) == 'c' && ne.charAt(ii+1) == 'd') ||
							(ne.charAt(ii) == 'p' && ne.charAt(ii+1) == 'q') ||
							(ne.charAt(ii) == 'x' && ne.charAt(ii+1) == 'y'))) {
						consek++;
					}
				}
			}
			//out.print(countVowels); out.print(" <vowels dub>"); out.print(dub); out.print(" consek>"); out.println(consek);

			if (consek == 0 && dub > 0 && countVowels >= 3) {
				//out.print("good: "); out.println(ne);
				tot++;
			}

		}
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

