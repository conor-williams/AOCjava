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
class year2019_day2_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day2.2");
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
		Vector <Integer> vi = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[,]");
			   while (scanner.hasNext()) {
			   	String ne = scanner.next();
			   	vi.add(Integer.valueOf(ne));
			   }
		}
		//vi.set(1, 12); vi.set(2, 2);
		Vector <Integer> viOrig = new Vector<>(vi);

		int no = 0;
		int ve = 0;
		for (int noun = 0; noun < 100; noun++) {
			for (int verb = 0; verb < 100; verb++) {
				vi = new Vector<>(viOrig);
				vi.set(1, noun);
				vi.set(2, verb);
				for (int jj = 0; jj < vi.size(); jj++) {
					if (vi.get(jj) == 1) {
						vi.set(vi.get(jj+3), (vi.get(vi.get(jj+1)) + 
								vi.get(vi.get(jj+2))));
						jj+=3;
					} else if (vi.get(jj) == 2) {
						vi.set(vi.get(jj+3), (vi.get(vi.get(jj+1)) * 
								vi.get(vi.get(jj+2))));
						jj+=3;
					} else if (vi.get(jj) == 99) {
						break;
					}
				}
				if (vi.get(0) == 19690720) {
					no = noun;
					ve = verb;
				}
			}
		}
		out.print("**j_ans: ");
		out.print((100*no)+ve);
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

