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
class year2018_day10 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day10.1");
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

		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("position=<\\s+(-?\\d+),\\s+(-?\\d+)> velocity=<\\s+(-?\\d+),\\s+(-?\\d+)>");
		Pattern p = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Vector <point> po = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			point tmp1 = new point();
			out.println(blah.get(i));
			tmp1.x = Integer.valueOf(m.group(1));
			tmp1.y = Integer.valueOf(m.group(2));
			tmp1.vx = Integer.valueOf(m.group(3));
			tmp1.vy = Integer.valueOf(m.group(4));
			po.add(tmp1);
		}
		out.println(po.size());
		out.println(po.get(0).x);
		out.println(po.get(0).y);
		out.println(po.get(0).vx);
		out.println(po.get(0).vy);
		int time = 0;
		int minminy = 0;
		int minminx = 0;
		while (1 == 1) {
			int maxy = -1000000;
			int miny = 9999999; 
			int minx = 9999999;
			out.print("time: "); out.println(time);
			for (int qq = 0; qq < po.size(); qq++) {
				point tmp1 = po.get(qq);
				int yy = tmp1.y + (time * tmp1.vy);
				int xx = tmp1.x + (time * tmp1.vx);

				if (yy > maxy) {maxy = yy;}
				if (yy < miny) {miny = yy;}
				if (xx < minx) {minx = xx;} 
				out.println("xx:"); out.println(xx);
				out.println("yy:"); out.println(yy);
				//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			}
			out.print("miny: "); out.println(miny);
			out.print("maxy: "); out.println(maxy);
			if (maxy - miny + 1 < 11) { 
				minminy = miny;
				minminx = minx;
				break;
			}
			time++;
		}
		out.print("time: "); out.println(time);
		int grid[][] = new int [12][80];
		out.print("minminx: "); out.println(minminx);
		out.print("minminy: "); out.println(minminy);
		for (int qq = 0; qq < po.size(); qq++) {
			point tmp1 = po.get(qq);
			int yy = tmp1.y + (time * tmp1.vy) - minminy;
			int xx = tmp1.x + (time * tmp1.vx) - minminx;
			grid[yy][xx] = 1;
		}
		System.setOut(originalOut);
		out.println("**j_ans: ");
		for (int yy = 0; yy < 12; yy++) {
			for (int xx = 0; xx < 80; xx++) {
				if (grid[yy][xx] == 0) {
					out.print(" ");
				} else {
					out.print("|");
				}
			}
			out.println();
		}
		out.println("");




		//out.print(num);
	}
}

class point {
	public int x;
	public int y;
	public int vx;
	public int vy;
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

