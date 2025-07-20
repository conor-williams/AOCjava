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
class year2022_day17_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	public static int SX = 10000;
	public static char grid [][] = new char[10100][8];
	public static char line1[] = new char[20200];
	public static int jetPos = 0;
	public static int vBottom = 0;
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day17.2");
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
		Arrays.stream(grid).forEach(row -> Arrays.fill(row, '.'));
		vBottom = SX-10;
		for (int x = 0; x < 7; x++) {
			grid[vBottom][x] = '#';
		}

		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */

		for (int i = 0; i < blah.size(); i++) {
			line1 = blah.get(i).toCharArray();
		}
		int perv = 0;
		int diffs[] = new int[20000];
		int vals[] = new int[20000];
		for (int j = 0; j < 4300; j++) {
			out.println(j);
			int shape = j % 5;
			switch(shape) {
				case 0: //-
					drawMinus();
					break;
				case 1: //+
					drawPlus();
					break;
				case 2: //_|
					drawEll();
					break;
				case 3: //|
					drawPipe();
					break;
				case 4: //|=|
					drawSquare();
					break;
			}
			int new1 = vBottom - bottomIt();
			vals[j] = new1;
			diffs[j] = new1-perv;
			perv = new1;
		}

		int mincyc = 999999;
		int start0 = diffs[1000];
		int start1 = diffs[1001];
		int start2 = diffs[1002];
		int start3 = diffs[1003];
		int start4 = diffs[1004];
		int start5 = diffs[1005];
		int start6 = diffs[1006];
		int start7 = diffs[1007];
		int start8 = diffs[1008];
		int start9 = diffs[1009];
		int start10 = diffs[1010];
		for (int jj = 1100; jj < 6400; jj++) {
			if (      diffs[jj] == start0 && diffs[jj+1] == start1 &&
					diffs[jj+2] == start2 && diffs[jj+3] == start3 &&
					diffs[jj+4] == start4 && diffs[jj+5] == start5 &&
					diffs[jj+6] == start6 && diffs[jj+7] == start7 &&
					diffs[jj+8] == start8 && diffs[jj+9] == start9 && diffs[jj+10]==start10) {
				if (jj-1000 < mincyc) {mincyc = jj-1000;}
			}
		}
		int startCy = 0;

		for (int ii = 0; ii < 6400; ii++) {
			int found = 0;
			for (int jj = 0; jj < mincyc; jj++) {
				out.println(ii+jj);
				out.println(ii+mincyc+ii);
				if (diffs[ii+jj] == diffs[ii+mincyc+jj]) {
				} else {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				startCy = ii;
				break;
			}
		}

		int posTrill = (int)((long) (1000000000000L - (long)startCy ) % (long)mincyc);
		int cena = 0;
		for (int ii = startCy+1; ii < startCy+posTrill; ii++) {
			cena+=diffs[ii];
		}
		int sumDiffs = 0;
		for (int ii = startCy; ii < startCy+mincyc; ii++) {
			sumDiffs += diffs[ii];
		}

		long numCycles = (long) (1000000000000L - (long)startCy ) / (long) mincyc;

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(vals[startCy]+(numCycles*sumDiffs) + cena);
		out.println("");
	}

	public static int bottomIt() {
		int bottom[] = new int[7];
		int bottomSrt[] = new int[7];
		int found0 = 0;
		int found1 = 0;
		int found2 = 0;
		int found3 = 0;
		int found4 = 0;
		int found5 = 0;
		int found6 = 0;
		//out.print("vBottom: "); out.println(vBottom);
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < vBottom+5; y++) {
				if (x == 0 && grid[y][x] == '#' && found0 == 0) {
					bottom[0] = y;
					found0 = 1;
				} else if (x == 1 && grid[y][x] == '#' && found1 == 0) {
					bottom[1] = y;
					found1 = 1;
				} else if (x == 2 && grid[y][x] == '#' && found2 == 0) {
					bottom[2] = y;
					found2 = 1;
				} else if (x == 3 && grid[y][x] == '#' && found3 == 0) {
					bottom[3] = y;
					found3 = 1;
				} else if (x == 4 && grid[y][x] == '#' && found4 == 0) {
					bottom[4] = y;
					found4 = 1;
				} else if (x == 5 && grid[y][x] == '#' && found5 == 0) {
					bottom[5] = y;
					found5 = 1;
				} else if (x == 6 && grid[y][x] == '#' && found6 == 0) {
					bottom[6] = y;
					found6 = 1;
				}
			}
		}

		for (int i = 0; i < 7; i++) {
			bottomSrt[i] = bottom[i];
		}

		//sort(bottomSrt, bottomSrt+7);
		//Arrays.sort(bottomSrt, Collections.reverseOrder());
		//bottomSrt = Arrays.stream(bottomSrt) .boxed() .sorted(Collections.reverseOrder()) .mapToInt(Integer::intValue) .toArray();
		//bottomSrt = Arrays.stream(bottomSrt).boxed() .sorted() .mapToInt(Integer::intValue) .toArray();
		//assert(bottomSrt[0] <= bottomSrt[1]);

		Arrays.sort(bottomSrt);

		int highest = bottomSrt[0];
		return highest;
	}

	public static int canIMoveSq(int startX, int startY, int xM, int yM) 
	{
		if (xM == 7 && yM == 7) {
			grid[startY-1][startX] = '#'; grid[startY-1][startX+1] = '#';
			grid[startY][startX] = '#'; grid[startY][startX+1] = '#';
			return 1;
		}

		int var_sx = startX+xM;
		int var_sy = startY+yM;
		if (grid[var_sy-1][var_sx] == '.' && grid[var_sy-1][var_sx+1] == '.' &&
				grid[var_sy][var_sx] == '.' && grid[var_sy][var_sx+1] == '.') {
			return 1;
				}
		return 0;

	}


	public static int canIMovePipe(int startX, int startY, int xM, int yM)
	{
		if (xM == 7 && yM == 7) {
			grid[startY  ][startX] = '#';
			grid[startY-1][startX] = '#';
			grid[startY-2][startX] = '#';
			grid[startY-3][startX] = '#';
			return 0;
		}

		int var_sx = startX+xM;
		int var_sy = startY+yM;
		if (grid[var_sy][var_sx] == '.' &&
				grid[var_sy-1][var_sx] == '.' &&
				grid[var_sy-2][var_sx] == '.' &&
				grid[var_sy-3][var_sx] == '.') {
			return 1;
				}
		return 0;
	}


	public static void drawSquare()
	{
		int highest = bottomIt();
		int startY = highest-4;

		int startX = 2;
		int endX = 3;

		jetPos = jetPos % ((int) line1.length);

		while(true) {
			if (line1[jetPos] == '>') {
				if (endX != 6 && canIMoveSq(startX, startY, 1, 0)!=0) {
					startX++;	
					endX++;
				} else {
				}

				if (canIMoveSq(startX, startY, 0, +1) != 0)  {
					startY++;
				} else {
					break;
				}
			} else if (line1[jetPos] == '<') {
				if (startX != 0 &&  canIMoveSq(startX, startY, -1, 0) != 0) {
					startX--;
					endX--;
				} else {
				}
				if (canIMoveSq(startX, startY, 0, +1) != 0) {
					startY++;
				} else {
					break;
				}
			}
			jetPos = (jetPos+1) % ((int) line1.length);
		}
		jetPos = (jetPos+1) % ((int) line1.length);
		if (canIMoveSq(startX, startY, 0, 0) != 0) {
			canIMoveSq(startX, startY, 7, 7);
		} else {
		}
	}


	public static void drawPipe()
	{
		int highest = bottomIt();
		int startY = highest-4;

		int startX = 2;
		int endX = 2;

		jetPos = jetPos % ((int) line1.length);


		while(true) {
			if (line1[jetPos] == '>') {
				if (endX != 6 && canIMovePipe(startX, startY, 1, 0) != 0) {
					startX++;	
					endX++;
				} else {
				}
				if (canIMovePipe(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			} else if (line1[jetPos] == '<') {
				if (startX != 0 && canIMovePipe(startX, startY, -1, 0) != 0) {
					startX--;
					endX--;
				} else {
				}
				if (canIMovePipe(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			}
			jetPos = (jetPos+1) % ((int) line1.length);
		}
		jetPos = (jetPos+1) % ((int) line1.length);
		if (canIMovePipe(startX, startY, 0, 0) != 0) {
			canIMovePipe(startX, startY, 7, 7);
		}
	}


	public static int canIMoveMinus(int startX, int startY, int xM, int yM) {
		if (xM == 7 && yM == 7) {
			grid[startY][startX] = '#'; grid[startY][startX+1] = '#'; grid[startY][startX+2] = '#'; grid[startY][startX+3] = '#';
			return 0;
		}


		int var_sY = startY+yM;
		int var_sX = startX+xM;

		if (grid[var_sY][var_sX] == '.' && grid[var_sY][var_sX+1] == '.' && grid[var_sY][var_sX+2] == '.' && grid[var_sY][var_sX+3] == '.') {
			return 1;
		}
		return 0;

	}
	public static void drawMinus()
	{
		int highest = bottomIt();
		int startY = highest-4;

		int startX = 2;
		int endX = 5;

		jetPos = jetPos % ((int) line1.length);


		while(true) {
			if (line1[jetPos] == '>') {
				if (endX != 6 && (canIMoveMinus(startX, startY, 1, 0) != 0)) {
					startX++;	
					endX++;
				} else {
				}
				if (canIMoveMinus(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}	
			} else if (line1[jetPos] == '<') {
				if (startX != 0 && canIMoveMinus(startX, startY, -1, 0) != 0) {
					startX--;
					endX--;
				} else {
				}
				if (canIMoveMinus(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			}
			jetPos = (jetPos+1) % ((int) line1.length);
		}
		jetPos = (jetPos+1) % ((int) line1.length);

		if (canIMoveMinus(startX, startY, 0, 0) != 0) {
			canIMoveMinus(startX, startY, 7, 7);
		}
	}

	public static int canIMovePlus(int startX, int startY, int xM, int yM)
	{
		if(xM == 7 && yM == 7) {
			grid[startY-2][startX+1] = '#';
			grid[startY-1][startX] = '#'; 	grid[startY-1][startX+1] = '#'; grid[startY-1][startX+2] = '#';
			grid[startY  ][startX+1] = '#';
			return 0;
		} 

		if (grid[startY-2+yM][startX+1+xM] == '.' && 
				grid[startY-1+yM][startX+xM] == '.' && 	grid[startY-1+yM][startX+1+xM] == '.' && grid[startY-1+yM][startX+2+xM] == '.' &&
				grid[startY  +yM][startX+1+xM] == '.') {
			return 1;

				}
		return 0;
	}
	public static void drawPlus()
	{
		int highest = bottomIt();
		out.println(highest);
		int startY = highest-4;

		int startX = 2;
		int endX = 4;

		jetPos = jetPos % ((int) line1.length);

		while(true) {
			if (line1[jetPos] == '>') {
				if (endX != 6 &&  canIMovePlus(startX, startY, 1, 0) != 0) {
					startX++;	
					endX++;
				} else {
				}

				if (canIMovePlus(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			} else if (line1[jetPos] == '<') {
				if (startX != 0 && canIMovePlus(startX, startY, -1, 0) != 0) {
					startX--;
					endX--;
				} else {
				}

				if (canIMovePlus(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			}
			jetPos = (jetPos+1) % ((int) line1.length);
		}
		jetPos = (jetPos+1) % ((int) line1.length);
		if (canIMovePlus(startX, startY, 0, 0) != 0) {
			canIMovePlus(startX, startY, 7, 7);
		} else {
		}
	}
	public static int canIMoveEll(int startX, int startY, int mX, int mY) 
	{

		if (mX == 7 && mY == 7) {
			grid[startY-2][startX+2] = '#';
			grid[startY-1][startX+2] = '#';
			grid[startY][startX] = '#';	grid[startY][startX+1] = '#';  	grid[startY  ][startX+2] = '#';
			return 0;
		}
		if (grid[startY-2+mY][startX+2+mX] == '.' &&
				grid[startY-1+mY][startX+2+mX] == '.' &&
				grid[startY+mY][startX+mX] == '.' && 	grid[startY+mY][startX+1+mX] == '.' && grid[startY  +mY][startX+2+mX] == '.') {
			return 1;
				}
		return 0;
	}

	public static void drawEll()
	{
		int highest = bottomIt();
		int startY = highest-4;

		int startX = 2;
		int endX = 4;

		jetPos = jetPos % ((int) line1.length);


		while(true) {

			if (line1[jetPos] == '>') { //_|
				if (endX != 6 && canIMoveEll(startX, startY, 1, 0) != 0) {
					startX++;	
					endX++;
				} else {
				}

				if (canIMoveEll(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			} else if (line1[jetPos] == '<') {
				if (startX != 0 && canIMoveEll(startX, startY, -1, 0) != 0) {
					startX--;
					endX--;
				} else {
				}

				if (canIMoveEll(startX, startY, 0, 1) != 0) {
					startY++;
				} else {
					break;
				}
			}
			jetPos = (jetPos+1) % ((int) line1.length);
		}
		jetPos = (jetPos+1) % ((int) line1.length);

		if (canIMoveEll(startX, startY, 0, 0) != 0) {
			canIMoveEll(startX, startY, 7, 7);
		} else {
		}
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

