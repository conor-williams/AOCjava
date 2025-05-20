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
class year2024_day5 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day5.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(\\d+)\\|(\\d+)");
		Map <Integer, Vector <Integer>> mpBefore = new HashMap<>();
		Map <Integer, Vector <Integer>> mpAfter = new HashMap<>();
		Vector <Vector <Integer>> ve = new Vector<> ();
		int pages = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {pages = 1; continue;}
			if (pages == 0) {
				Matcher m = p.matcher(blah.get(i));
				//out.println(blah.get(i));
				m.find();
				Integer left = Integer.valueOf(m.group(1));
				//out.println(m.group(1));
				Integer right = Integer.valueOf(m.group(2));
				//out.println(m.group(2));
				{
					Vector <Integer> tmpB = new Vector<>();
					if (mpAfter.containsKey(left)) {
						tmpB = mpAfter.get(left);
					} 
					tmpB.add(right);
					mpAfter.put(left, tmpB);
				}
				{
					Vector <Integer> tmpA = new Vector<>();
					if (mpBefore.containsKey(right)) {
						tmpA = mpBefore.get(right);
					}
					tmpA.add(left);
					mpBefore.put(right, tmpA);
				}
			} else if (pages == 1) {
				Vector <Integer> tmp1 = new Vector<>();
				Scanner scanner = new Scanner(blah.get(i));
				scanner.useDelimiter("[,]");
				while (scanner.hasNext()) {
					String ne = scanner.next();
					Integer var_int = Integer.valueOf(ne);
					tmp1.add(var_int);
				}
				ve.add(tmp1);
			}
		}

		int tot = 0;
		for (int ii = 0; ii < ve.size(); ii++) {
			var group = ve.get(ii);

			int good = 1;
after:
			for (int kk = 0; kk < group.size(); kk++) {
				var oneint = group.get(kk);

				Vector bef = mpBefore.get(oneint);
				if (bef != null) {
					//out.print("bef for "); out.print(oneint);
					//for (int zzzz = 0; zzzz < bef.size(); zzzz++) { out.print(" "); out.print(bef.get(zzzz)); }
				}
				Vector aft = mpAfter.get(oneint);
				if (aft != null) {
					//out.print("aft for "); out.print(oneint);
					//for (int zzzz = 0; zzzz < aft.size(); zzzz++) { out.print(" "); out.print(aft.get(zzzz)); }
				}

				if (bef != null) {
					for (int bkk = 0; bkk < kk; bkk++) {
						//out.print("bef contains "); out.println(group.get(bkk));
						if (bef.contains(group.get(bkk))) {
							//ok
						} else {
							good = 0;
							break after;
						}
					}
				}
				if (aft != null) {
					for (int akk = kk+1; akk < group.size(); akk++) {
						//out.print("aft contains "); out.println(group.get(akk));
						if (aft.contains(group.get(akk))) {
							//ok
						} else {
							good = 0;
							break after;
						}
					}
				}
			}
			if (good == 1) {
				//out.print("good is 1 for group: "); out.println(ii);
				//out.print("middle: "); out.println(group.get(group.size()/2));
				tot += group.get(group.size()/2);
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

