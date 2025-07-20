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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2021_day17 {
	public static int GX = 300;
	public static int GY = 20000;
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[GY][GX];
	//    public static int already [][] = new int[leny][lenx];

	public static void main(String [] args) {
		out.println("		2021 Day17.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		Arrays.stream(grid).forEach(row -> Arrays.fill(row, '.'));

		//out.println("target area: x=207..263, y=-115..-63");
		//out.println("target area: x=20..30, y=-10..-5");
		Pattern p = Pattern.compile("target area: x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		int sx  = 0, sy = 0;
		int xfrom = 0, xto = 0, yfrom = 0, yto = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			xfrom = Integer.valueOf(m.group(1));
			xto = Integer.valueOf(m.group(2));
			yto = Math.abs(Integer.valueOf(m.group(3)));
			yfrom = Math.abs(Integer.valueOf(m.group(4)));

			yfrom += GY-300;
			yto += GY-300;
			sy +=GY-300;
		}
		for (int y = yfrom; y <= yto ; y++) {
			for (int x = xfrom; x <= xto; x++) {
				grid[y][x] = 'T';
			}
		}
		int maxy = 0;
		int maxsvx, maxsvy;
		for (int ovx = 2; ovx < 200; ovx++) {
			for (int ovy = 1; ovy < 400; ovy++) {
				int vx = ovx;
				int vy = ovy;
				int x1 = sx;
				int y1 = sy;
				int lx[] = new int[100];
				int ly[] = new int[100];
				int grav;
				if (ovy < 0) {grav = 1;} else {grav = 0;}
				int interimmaxy = 0;
				for (int t = 0; t < 1000; t++) {
					x1 += vx; vx--;
					if (vx < 0) {vx = 0;}
					int height = 0;
					if (vy == 0) {height = sy - y1;}
					y1 -= vy; vy--;

					if (x1 < 0 || y1 < 0) {break;}

					if (height > interimmaxy) {interimmaxy = height;}
					if (x1 >= xfrom && x1 <= xto && y1 >= yfrom && y1 <= yto) {
						if (interimmaxy > maxy) {maxsvx = ovx; maxsvy = ovy; maxy = interimmaxy;}
						break;
					}
					if (x1 > xto) {
						break;
					}
					if (y1 > yto) {
						break;
					}
				}
			}
		}


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxy);
		out.println("");
	}
}

class Tuple<X,Y > {
	public X first;
	public Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
	public X first;
	public Y second;
	public Z third;

	public TreTuple(Object o) {
		TreTuple tu2 = (TreTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
	}
	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;

	public QuadTuple(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTuple tu2 = (QuadTuple) o;
		if (this == o) return true;
		if (!(o instanceof QuadTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
	public X first;
	public Y second;
	public Z third;
	public V fourth;
	public W fifth;

	public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}
	@Override
	public boolean equals(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		if (this == o) return true;
		if (!(o instanceof CinqTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		if (!fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
	}

}

