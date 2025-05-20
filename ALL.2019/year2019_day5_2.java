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
class year2019_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day5.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Vector <Integer> vi = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[,]");
			   while (scanner.hasNext()) {
			   	String ne = scanner.next();
			  	vi.add(Integer.valueOf(ne));
			   }
		}
//padit..
		Integer outout = 0;
		Integer input = 5;
		for (int jj = 0; jj < vi.size(); jj++) {
			String wholecode = String.format("%05d", vi.get(jj));
			int opcode = Integer.valueOf(wholecode.substring(3,5));

			Character firstMode = wholecode.charAt(2);
			Character secondMode = wholecode.charAt(1);
			Character thirdMode = wholecode.charAt(0);

			int firstParam = 0;
			int secondParam = 0;

			if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6
					|| opcode == 7 || opcode == 8) {
				if (firstMode == '0') {
					firstParam = vi.get(vi.get(jj+1));
				} else {
					firstParam = vi.get(jj+1);
				}
				if (secondMode == '0') {
					secondParam = vi.get(vi.get(jj+2));
				} else {
					secondParam = vi.get(jj+2);
				}
			} else if (opcode == 4) {
				if (firstMode == '0') {
					firstParam = vi.get(vi.get(jj+1));
				} else {
					firstParam = vi.get(jj+1);
				}
			} else if (opcode == 3 || opcode == 99) {
			} else {
				out.println("ERROR");
			}

			if (opcode == 1) {
				vi.set(vi.get(jj+3), firstParam+secondParam);
				jj+=3;
			} else if (opcode == 2) {
				vi.set(vi.get(jj+3), firstParam*secondParam);
				jj+=3;
			} else if (opcode == 3) {
				if (firstMode == '0') {
					vi.set(vi.get(jj+1), input);
				} else if (firstMode == '1') {
					out.println("whathere...");
				}
				jj++;
			} else if (opcode == 4) {
				outout = firstParam;
				//out.print("OUTOUT: "); out.println(outout);
				jj++;
			} else if (opcode == 5) {
				if (firstParam != 0) {
					jj = secondParam-1;
				} else {
					jj+=2;
				}
			} else if (opcode == 6) {
				if (firstParam == 0) {
					jj = secondParam-1;
				} else {
					jj+=2;
				}
			} else if (opcode == 7) {
				if (firstParam < secondParam) {
					vi.set(vi.get(jj+3), 1);
				} else {
					vi.set(vi.get(jj+3), 0);
				}
				jj+=3;
			} else if (opcode == 8) {
				if (firstParam == secondParam) {
					vi.set(vi.get(jj+3), 1);
				} else {
					vi.set(vi.get(jj+3), 0);
				}
				jj+=3;
			} else if (opcode == 99) {
				break;
			}
		}
		out.print("**j_ans: ");
		out.print(outout);
		out.println("");
		//for (int ii = 0; ii < vi.size(); ii++) {
		//	out.print(","); out.print(vi.get(ii));
		//}
		//out.println();
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

