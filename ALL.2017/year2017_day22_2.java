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
import java.util.stream.Collectors;

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
class year2017_day22_2 {
	static int tot = 0;
	static int ST = 1000;
	static int startx = ST;
	static int starty = ST;

	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[(ST*2)+10][(ST*2)+10];
	public static char gridtmp [][] = new char[(ST*2)+10][(ST*2)+10];
	public static int already [][] = new int[(ST*2)+10][(ST*2)+10];

	public static void main(String [] args) {
		out.println("		2017 Day22.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		//grid = new char[leny][lenx];
		//already = new int[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			//grid[i] = blah.get(i).toCharArray();
			gridtmp[i] = blah.get(i).toCharArray();
		}


		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		expandgrid();
		tot = 0;
		next(ST, ST, 0);

		//for (int i = 0; i < blah.size(); i++) { } 
		//		System.setOut(originalOut);
		/*
		   out.print("**j_ans: ");
		   out.print(tot);
		   out.println("");
		   */
	}
	static void expandgrid() {
		for (int y=0; y < (ST - leny/2); y++) {
			for (int x = 0; x < ST*2; x++) {
				grid[y][x] = '.';
			}
		}
		for (int y= (ST + leny/2); y< ST*2; y++) {
			for (int x = 0; x < ST*2; x++) {
				grid[y][x] = '.';
			}
		}
		int stx1 = ST - (gridtmp[0].length)/2;
		int stx2 = ST + (gridtmp[0].length)/2 + 1;
		for (int y=(ST -leny/2); y <= (ST+leny/2); y++) {
			for (int x = 0; x < stx1; x++) {
				grid[y][x] = '.';
			}
			for (int x = stx1; x < stx2; x++) {
				grid[y][x] = gridtmp[y-(ST-leny/2)][x-stx1];
			}
			for (int x = stx2; x < 2*ST; x++) {
				grid[y][x] = '.';
			}

		}

	}
	static int ind = 0;
	static void next(int x, int y, int DIR) {
		ind++;
		if (ind == 10000001) {
			int mytot = 0;
			for (int y1 = 0; y1 < ST*2; y1++) {
				for (int x1 = 0; x1 < ST*2; x1++) {
					if (grid[y1][x1] == '#') {
						mytot++;
					}
				}
			}

		   out.print("**j_ans: ");
		   out.print(tot);
		   out.println("");
		   Runtime.getRuntime().halt(0);
		}

		int newdir = 0; int newx = 0 , newy = 0;
		if (grid[y][x] == '#') {
			grid[y][x] = 'F';
			newdir = (DIR+1)%4;
			var tu1 = calcnewxy(newdir, x, y);
			newx = tu1.first;
			newy = tu1.second;
		} else if (grid[y][x] == '.') {
			grid[y][x] = 'W';
			newdir = ((DIR-1)+4)%4;
			var tu1 = calcnewxy(newdir, x, y);
			newx = tu1.first;
			newy = tu1.second;
		} else if (grid[y][x] == 'W') {
			grid[y][x] = '#';
			tot++;
			newdir = DIR;
			var tu1 = calcnewxy(newdir, x, y);
			newx = tu1.first;
			newy = tu1.second;
		} else if (grid[y][x] == 'F') {
			grid[y][x] = '.';
			newdir = ((DIR-2)+4)%4;
			var tu1 = calcnewxy(newdir, x, y);
			newx = tu1.first;
			newy = tu1.second;
		}


		next(newx, newy, newdir);
	}


	static Tuple<Integer, Integer> calcnewxy(int newdir, int x, int y) {
		int newx = 0; int newy = 0;
		switch (newdir) {
			case 0:
				newx = x;
				newy = y-1;
				break;
			case 1:
				newx = x+1;
				newy = y;
				break;
			case 2:
				newx = x;
				newy = y+1;
				break;
			case 3:
				newx = x-1;
				newy = y;
				break;
		}
		return new Tuple(newx, newy);
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

