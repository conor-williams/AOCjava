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

class year2015_day25 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day25.1");
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
		Pattern p = Pattern.compile("To continue, please consult the code grid in the manual.  Enter the code at row (\\d+), column (\\d+).");

		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		BigInteger one =  new BigInteger("1");
		BigInteger zero =  new BigInteger("0");
		int row = 0;
		int col = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(0));
			m.find();
			row = Integer.valueOf(m.group(1));
			col = Integer.valueOf(m.group(2));
		}
		//out.println(row); out.println(col);

		int botleft = 1;
		int toprow = 0;

		int botleftprev = 1;
		int toprowprev = 0;
		int TERM = 0;
after:
		for (int side = 1; ; side++) {
			toprowprev = toprow;	
			botleftprev = botleft;

			botleft = botleft+side;;
			toprow = toprow+side;;
			//out.print("row: "); out.print(side+1); out.print(" start diag: "); out.println(botleft);
			for (int jj = 0; jj <= side; jj++) {
				//out.print("	term: "); out.print(botleft+jj); out.print(" row "); out.print(side+1-jj); out.print(" col: "); out.println(jj+1);
				if (row == side+1-jj && col == jj+1) {
					TERM = botleft+jj;
					break after;
				}
			}
				
			
		}

		BigInteger ans = zero;
		BigInteger prev = new BigInteger("20151125");
		//for (int ii = 0; ii < TERM; ii++) {
		BigInteger www = new BigInteger("252533");
		BigInteger qqq = new BigInteger("33554393");
		//out.print("TERM: "); out.println(TERM);
		for (int ii = 1; ii < TERM ; ii++) {
			ans = prev.multiply(www).mod(qqq);
			prev = ans;
			//out.println(ans);
		}

		out.print("**j_ans: ");
		out.print(ans);
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

