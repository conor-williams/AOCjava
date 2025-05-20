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


class year2018_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day5.2");
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
		int x = 1;
		int y = 1;
		int [] ar = new int[blah.size()];
		String ne = new String();

		int minSize = 9999999;
		for (int i = 0; i < blah.size(); i++) {

			for (int qq = 0; qq < 26; qq++) {
				ne = blah.get(i);
				String repl1 = Character.toString('a'+qq);
				ne = ne.replaceAll(repl1, "");
				String repl2 = Character.toString('A'+qq);
				ne = ne.replaceAll(repl2, "");

				//out.println(ne);
				int end = ne.length();
				int di = Math.abs('a' - 'A');
				for (int jj = 0; jj < end-1; jj++) {
					if (Math.abs(ne.charAt(jj) - ne.charAt(jj+1)) == di) {
						//out.print(ne.charAt(jj)); out.print(" and "); out.println(ne.charAt(jj+1));
						StringBuilder sb = new StringBuilder(ne);
						sb.delete(jj, jj+2);
						ne = sb.toString();

						end -= 2;
						jj -= 3;
						if (jj < 0) {jj = -1;}
						//out.println(ne);
					}
				}
				if (ne.length() < minSize) {minSize = ne.length();}
			}

		}
		out.print("**j_ans: ");
		out.print(minSize);
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

