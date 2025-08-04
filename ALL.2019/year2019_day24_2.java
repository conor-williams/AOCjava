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
import java.util.stream.IntStream;


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
///MyClass[] array = IntStream.range(0, 5) .mapToObj(i -> new MyClass()) .toArray(MyClass[]::new);
@SuppressWarnings("unchecked")
class year2019_day24_2 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	static int SZ = 6;
	static int DEPTH = 251;
	static int TIME = 200;

	public static char grid [][][] = new char[DEPTH+2][SZ][SZ];
	public static char gridTmp [][][] = new char[DEPTH+2][SZ][SZ];
	public static char gridOrig [][] = new char[SZ][SZ];

	static int PAD = 1;
	public static void main(String [] args) {
		out.println("		2019 Day24.2");
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
		gridOrig = new char[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			gridOrig[i] = blah.get(i).toCharArray();
		}

		for (int i = 0; i < DEPTH; i++) {
			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					if (x == 2 && y == 2) {
						gridTmp[i][2][2] = '?';
					} else {
						gridTmp[i][y][x] = '.';
					}
				}
			}
		}

		for (int i = 0; i < DEPTH; i++) {
			if (DEPTH/2 == i) {continue;}
			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					if (x==2 && y==2) {
						grid[i][2][2] = '?';
					} else {
						grid[i][y][x] = '.';
					}
				}
			}
		}

		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				grid[DEPTH/2][y][x] = gridOrig[y][x];
			}
		}

		int t = 0;
		while (true) {
			checkGrid(DEPTH/2);
			for (int gr = (DEPTH/2)+1; gr < DEPTH; gr++) {
				checkGrid(gr);
			}
			for (int gr = (DEPTH/2)-1; gr > 0; gr--) {
				checkGrid(gr);
			}

			t++;
			for (int i = 0; i < DEPTH; i++) {
				for (int y = 0; y < leny; y++) {
					for (int x = 0; x < lenx; x++) {
						grid[i][y][x] = gridTmp[i][y][x];
					}
				}
			}
			if (t == TIME) {break;}
		}

		/// FINISH with a count:
		int count2 = 0;
		for (int i = 0; i < DEPTH; i++) {
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 5; x++) {
					if (grid[i][y][x] == '#') {
						count2++;
					}
				}
			}
		}




		out.print("**j_ans: ");
		out.print(count2);
		out.println("");
	}
	static void swi(int count, int de, int x, int y)
	{
		/*
		   if (grid[de][y][x] != '#' && grid[de][y][x] != '.') {
		   printf("ERROR\n"); exit(0);
		   }
		   if (x == 2 && y ==2) {
		   printf("ERR\n"); exit(0);
		   }
		   */

		switch(grid[de][y][x]) {
			case '#':
				if (count == 1) {
					gridTmp[de][y][x] = '#';
				} else {
					gridTmp[de][y][x] = '.';
				}
				break;
			case '.':
				if (count == 1 || count == 2) {
					gridTmp[de][y][x] = '#';
				} else {
					gridTmp[de][y][x] = '.';
				}
				break;

		}
	}

	static void checkGrid(int gr) {
		for (int y = 1; y < leny-1; y++) {
			for (int x = 1; x < lenx-1; x++) {
				if (x == 2 && y == 2) {continue;}

				if (x == 2 && y == 1) {continue;}
				if (x == 2 && y == 3) {continue;}
				if (x == 1 && y == 2) {continue;}
				if (x == 3 && y == 2) {continue;}

				int count = 0;
				if (grid[gr][y-1][x] == '#') {count++;} 
				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}

				swi(count, gr, x, y);
			}
		}

		{int y = 0; int x = 0;
			{
				int count = 0;
				if (grid[gr-1][1][2] == '#') {count++;} 
				if (grid[gr-1][2][1] == '#') {count++;}

				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}
				swi(count, gr, x, y);
			}
		}

		{int y = 0; 
			for (int x = 1; x< 4; x++) {
				int count = 0;
				if (grid[gr-1][1][2] == '#') {count++;} 

				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				swi(count, gr, x, y);
			}
		}

		{int y = 0; int x = 4;
			{
				int count = 0;
				if (grid[gr-1][2][3] == '#') {count++;} 
				if (grid[gr-1][1][2] == '#') {count++;}

				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				swi(count, gr, x, y);
			}
		}

		{int x = 4;
			for (int y = 1; y<4; y++) {
				int count = 0;
				if (grid[gr-1][2][3] == '#') {count++;}

				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				swi(count, gr, x, y);
			}
		}

		{int y = 4; int x = 4;
			{
				int count = 0;
				if (grid[gr-1][2][3] == '#') {count++;}

				if (grid[gr-1][3][2] == '#') {count++;}

				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y-1][x] == '#') {count++;}
				swi(count, gr, x, y);

			}
		}

		{
			int y = 4;
			for (int x = 3; x > 0; x--) {
				int count = 0;

				if (grid[gr-1][3][2] == '#') {count++;}

				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				swi(count, gr, x, y);
			}
		}

		{
			int y = 4; int x = 0;
			{
				int count = 0;

				if (grid[gr-1][2][1] == '#') {count++;}
				if (grid[gr-1][3][2] == '#') {count++;}

				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				swi(count, gr, x, y);
			}
		}

		{int x = 0;
			for (int y = 3; y > 0; y--) 
			{
				int count = 0;

				if (grid[gr-1][2][1] == '#') {count++;}

				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				swi(count, gr, x, y);
			}
		}

		/// INSIDE....

		{int x = 2; int y = 1;
			{
				int count = 0;
				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}

				int y1 = 0;
				for (int x1 = 0; x1 <= 4; x1++) {
					if (grid[gr+1][y1][x1] == '#') {count++;}	
				}


				swi(count, gr, x, y);

			}
		}

		{int x = 3; int y = 2; //N
			{
				int count = 0;
				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				int x1 = 4;
				for (int y1 = 0; y1 <= 4; y1++) {
					if (grid[gr+1][y1][x1] == '#') {count++;}	
				}

				swi(count, gr, x, y);

			}
		}

		{int x = 2; int y = 3;
			{
				int count = 0;
				if (grid[gr][y][x+1] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				int y1 = 4;
				for (int x1 = 0; x1 <= 4; x1++) {
					if (grid[gr+1][y1][x1] == '#') {count++;}	
				}

				swi(count, gr, x, y);

			}
		}

		{int x = 1; int y = 2;
			{
				int count = 0;
				if (grid[gr][y-1][x] == '#') {count++;}
				if (grid[gr][y][x-1] == '#') {count++;}
				if (grid[gr][y+1][x] == '#') {count++;}

				int x1 = 0;
				for (int y1 = 0; y1 <= 4; y1++) {
					if (grid[gr+1][y1][x1] == '#') {count++;}	
				}
				swi(count, gr, x, y);

			}
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

