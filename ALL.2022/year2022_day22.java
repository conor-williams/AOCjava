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
class year2022_day22 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	static class startend_s {
		int st;
		int en;
	}
	static startend_s RowStae[] = new startend_s[300];
	static startend_s ColStae[] = new startend_s[300];
	static String mands = new String();
	static String mandsOrig = new String();

	static class mands2_s {
		char rl;
		int val;
	}
	static mands2_s mands2[] = new mands2_s[6000];
	static int numPos = 0;


	public static void main(String [] args) {
		out.println("		2022 Day22.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		leny-= 1;
		for (int ii = 0; ii < 300; ii++) {
			RowStae[ii] = new startend_s();
			ColStae[ii] = new startend_s();
		}
		for (int ii = 0; ii < 6000; ii++) {
			mands2[ii] = new mands2_s();
		}


		int maxX = 0;
		int f2 = 1;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				f2 = 0;
				continue;
			} else if (f2 == 1) {
				if (blah.get(i).length() > maxX) {maxX = blah.get(i).length();}
			} else if (f2 == 0) {
				break;
			}
		}
		lenx = maxX;

		grid = new char[leny][lenx+1];
		/*
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();

		   }
		   */

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
		int f1 = 1;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				f1 = 0;
				continue;
			} else if (f1 == 1) {
				//grid[i] = blah.get(i).toCharArray();
				//string tmpY[] = blah.get(i).toCharArray();
				for (int qq = 0; qq < blah.get(i).length(); qq++) {
					grid[i][qq] = blah.get(i).charAt(qq);
				}
				grid[i][blah.get(i).length()] = '\0';
				/*
				   for (int qq = blah.get(i).length(); qq < lenx; qq++) {
				   grid[i][qq] = ' ';
				   }
				   */
			} else if (f1 == 0) {
				mands = new String(blah.get(i));
			}
		}
		for (int y = 0; y < leny; y++) {
			int first = 1;
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == '\0') {break;}
				if (first == 1 && (grid[y][x] == '.' || grid[y][x] == '#')) {first = 0; RowStae[y].st = x;}
				else if (first == 0 && (grid[y][x] == ' ' || grid[y][x+1]==0)) {RowStae[y].en = x; break;}
			}
		}

		for (int x = 0; x < lenx; x++) {

			int first = 1;
			for (int y = 0; y < leny; y++) {
				if (first == 1 && (grid[y][x] == '.' || grid[y][x] == '#')) {first = 0; ColStae[x].st = y;}
				else if (first == 0 && (grid[y][x] == 0 || grid[y][x] == ' ' || y+1 ==leny)) {ColStae[x].en = y-1; break;}
			}
		}
		for (int y = 0; y < leny; y++) {
			out.print(y); out.print(" "); out.print(RowStae[y].st); out.print(" "); out.println(RowStae[y].en);
		}
		for (int x = 0; x < lenx; x++) {
			out.print(x); out.print(" "); out.print( ColStae[x].st); out.print(" "); out.println(ColStae[x].en);
		}


		mandsOrig = new String(mands);

		Scanner scanner = new Scanner(mands);
		scanner.useDelimiter("[RL]");

		numPos = 0;
		while (scanner.hasNext()) {
			String ne = scanner.next();
			mands2[numPos++].val = Integer.valueOf(ne);
		}
		out.println(numPos);

		mands = new String(mandsOrig);	
		int newPos = 0;
		for (int i = 0; i < mands.length(); i++) {
			if (mands.charAt(i) == 'R') {
				mands2[newPos].rl = 'R';
				newPos++;
			} else if (mands.charAt(i) == 'L') {
				mands2[newPos].rl = 'L';
				newPos++;
			}
		}


		int stx = RowStae[0].st; int sty = 0;
		int dir = 1;
		int crx = stx; int cry = sty;

		out.print("starts: "); out.print(crx); out.print(" "); out.println(cry);
		out.print("start dir: "); out.println(dir);


		out.print("numPos: "); out.println(numPos);

		for (int i = 0; i < numPos; i++) {
			//out.print("dir: "); out.print(dir);out.print(" val:"); out.print(mands2[i].val); out.print(" rl: "); out.println(mands2[i].rl);
			//out.print(crx); out.print(" "); out.println(cry);
			switch(dir) {
				case(0):
					{
						//y-- ^^^^
						for (int kk = 0; kk < mands2[i].val; kk++) {
							out.print("crx: "); out.print(crx); out.print(" cry: "); out.println(cry);
							if (cry == ColStae[crx].st || grid[cry-1][crx] == '9' || grid[cry-1][crx] == 0 || grid[cry-1][crx] == ' ') {
								if (grid[ColStae[crx].en][crx] != '#') {cry = ColStae[crx].en;} else {break;}
							} else if (grid[cry-1][crx] == '.') {cry--;}
							else if (grid[cry-1][crx] == '#') {break;}
							//printit(crx, cry, dir);
							//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();
						}
						break;
					}
				case(1):
					{

						//x++ >>>>>
						for (int kk = 0; kk < mands2[i].val; kk++) {
							if (crx == RowStae[cry].en || grid[cry][crx+1] == '9' || grid[cry][crx+1] == 0 || grid[cry][crx+1] == ' ') {
								if (grid[cry][RowStae[cry].st] != '#') {crx = RowStae[cry].st;} else {break;}
							} else if (grid[cry][crx+1] == '.') {crx++;}
							else if (grid[cry][crx+1] == '#') {break;}
							//printit(crx, cry, dir);
							//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();
						}
						break;
					}
				case(2):
					{
						for (int kk = 0; kk < mands2[i].val; kk++) {
							if (cry == ColStae[crx].en || grid[cry+1][crx] == '9' || grid[cry+1][crx] == 0|| grid[cry+1][crx] == ' ') {
								if (grid[ColStae[crx].st][crx] != '#') {cry = ColStae[crx].st;} else {break;}
							} else if (grid[cry+1][crx] == '.') {cry++;}
							else if (grid[cry+1][crx] == '#') {break;}
							//printit(crx, cry, dir);
							///Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();
						}
						//y++ vvvvvv
						break;
					}
				case(3):
					{
						//x-- <<<<<<
						for (int kk = 0; kk < mands2[i].val; kk++) {
							if (crx == RowStae[cry].st || grid[cry][crx-1] == '9' || grid[cry][crx-1] == 0 || grid[cry][crx-1] == ' ') {
								if (grid[cry][RowStae[cry].en] != '#') {crx = RowStae[cry].en;} else {break;}
							} else if (grid[cry][crx-1] == '.') {crx--;}
							else if (grid[cry][crx-1] == '#') {break;}
							//printit(crx, cry, dir);
							//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();
						}
						break;
					}
			}
			if (     mands2[i].rl == 'R')      {dir++; dir %= 4;}
			else if (mands2[i].rl == 'L') {dir--; dir+= 4; dir %= 4;} 
		}
		int posDir = dir;
		posDir--;
		posDir += 4;
		posDir %= 4;

		long ans =   (1000 * (cry+1)) + (4 * (crx+1)) + posDir;

		out.println(crx);
		out.println(cry);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	static void printit(int crx, int cry, int dir) {
		int sx, sy;
		int ex, ey;

		sx = crx-5 <                      0 ? 0                      : crx-5;
		ex = crx+5 > grid[cry].length ? grid[cry].length : crx+5;

		sy = cry-5 <    0 ? 0    : cry-5;
		ey = cry+5 > leny ? leny : cry+5;

		for (int y = sy; y < ey; y++) {
			for (int x = sx; x < ex; x++) {
				if (y == cry && x == crx) {
					switch(dir) {
						case 0:
							out.print('^'); break;
						case 1:
							out.print('>'); break;
						case 2:
							out.print('v'); break;
						case 3:
							out.print('<'); break;

					}
				} else {
					out.print(grid[y][x]);
				}
			}
			out.print("\n");
		}
		out.print("\n");
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

