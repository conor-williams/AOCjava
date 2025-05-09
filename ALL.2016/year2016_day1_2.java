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

class year2016_day1_2 {
	public static void main(String [] args) {
		out.println("		2016 Day1.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		int dir = 0;
		int EW = 0;
		int NS = 0;
		int count = 0;
		//Set <Tuple<int, int>> se = new HashSet<>();
		Set <Tuple<Integer, Integer>> se = new HashSet<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter(", "); 
			while (scanner.hasNext()) {
				count++;
				String ne = scanner.next();
				if (ne.charAt(0) == 'L') {
					dir = ((dir-1) + 4)%4;
				} else if (ne.charAt(0) == 'R') {
					dir = (dir+1)%4;
				} else {
					Runtime.getRuntime().halt(0);
				}
				//out.println("ne is " + ne);
				//out.print(ne.charAt(1));
				//out.print(ne.charAt(1)-48);
				Matcher m = p.matcher(ne);
				m.find();
				//out.println(m.group(1));
				int val=Integer.valueOf(m.group(2));
				switch (dir) {
					case 0:
						for (int ii = 0; ii < val; ii++) {
							NS--;
							Tuple<Integer, Integer> tu = new Tuple<>(Integer.valueOf(NS), Integer.valueOf(EW));
							if (se.contains(tu)) {
								//out.println(NS + " " + EW );
								out.print("**j_ans: ");
								out.println(Math.abs(NS) + Math.abs(EW));

								Runtime.getRuntime().halt(0);
							}
							se.add(tu);
						}
						break;
					case 1:
						for (int ii = 0; ii < val; ii++) {
							EW++;
							Tuple<Integer, Integer> tu = new Tuple<>(Integer.valueOf(NS), Integer.valueOf(EW));
							if (se.contains(tu)) {
								/*
		Iterator <Tuple <Integer, Integer>> seIter = se.iterator();
		while (seIter.hasNext()) {
			Tuple <Integer, Integer> tu33 = seIter.next();
			out.print(tu33.first);
			out.print(" xxx ");
			out.println(tu33.second);
		}
		*/
								//out.println(NS + " " + EW );
								out.print("**j_ans: ");
								out.println(Math.abs(NS) + Math.abs(EW));

								Runtime.getRuntime().halt(0);
							}
							se.add(tu);
						}
						break;
					case 2:
						for (int ii = 0; ii < val; ii++) {
							NS++;
							Tuple<Integer, Integer> tu = new Tuple<>(Integer.valueOf(NS), Integer.valueOf(EW));
							if (se.contains(tu)) {
								/*
		Iterator <Tuple <Integer, Integer>> seIter = se.iterator();
		while (seIter.hasNext()) {
			Tuple <Integer, Integer> tu33 = seIter.next();
			out.print(tu33.first);
			out.print(" xxx ");
			out.println(tu33.second);
		}
		*/
								//out.println(NS + " " + EW );
								out.print("**j_ans: ");
								out.println(Math.abs(NS) + Math.abs(EW));

								Runtime.getRuntime().halt(0);
							}
							se.add(tu);
						}
						break;
					case 3:
						for (int ii = 0; ii < val; ii++) {
							EW--;
							Tuple<Integer, Integer> tu = new Tuple<>(Integer.valueOf(NS), Integer.valueOf(EW));
							if (se.contains(tu)) {
								/*
		Iterator <Tuple <Integer, Integer>> seIter = se.iterator();
		while (seIter.hasNext()) {
			Tuple <Integer, Integer> tu33 = seIter.next();
			out.print(tu33.first);
			out.print(" xxx ");
			out.println(tu33.second);
		}
		*/
								//out.println(NS + " " + EW );
								out.print("**j_ans: ");
								out.println(Math.abs(NS) + Math.abs(EW));

								Runtime.getRuntime().halt(0);
							}
							se.add(tu);
						}
						break;
					default:
						Runtime.getRuntime().halt(0);
				}

			}
		}
		/*
		Iterator <Tuple <Integer, Integer>> seIter = se.iterator();
		while (seIter.hasNext()) {
			Tuple <Integer, Integer> tu = seIter.next();
			out.print(tu.first);
			out.print(" xxx ");
			out.println(tu.second);
		}
		*/
	}
}
/*
public class Tuple<int, int > {
	public final int first;
	public final int second;

	public Tuple(int first, int second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;

		Tuple tu2 = (Tuple) o;

		if (first1 != first2) return false;
		if (second1 != second2) return false;

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
*/
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
		/*
		out.print("check::");
		out.print(first);
		out.print("   ");
		out.print(second);
		out.print("   V    ");
		out.print(tu2.first);
		out.print("    ");
		out.println(tu2.second);
		*/

		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;

		/*
		out.print(fir1);
		out.print("  V   ");
		out.print(fir2);
		out.print(sec1);
		out.print("  V   ");
		out.println(sec2);
		*/
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		//if (fir1 != fir2) {out.println("false"); return false;}
		//if (sec1 != sec2) {out.println("false"); return false;}
		//if (first != tu2.first) {out.println("false"); return false;}
		//if (second != tu2.second) {out.println("false"); return false;}


		/*
		if (first != tu2.first) return false;
		if (second != tu2.second) return false;
		*/

		/*
		out.print("true::");
		out.print(first);
		out.print("   ");
		out.println(second);
		*/

		return true;
	}
	@Override
	/*
	public int hashCode() {
		int result = 17;
		int fir = (int)first;
		int sec = (int)second;
		out.print("hashcode::");
		out.print(fir);
		out.print("   ");
		out.println(sec);

		result = (31 * result) + fir;
		result = (31 * result) + sec;
		return result;
	}
	*/
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
