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
@SuppressWarnings("unchecked")
class year2023_day17 {
	public static int minPath = 99999;
	public static int lenx = 0;
	public static int leny = 0;
	//public static al_s already [][] = new al_s[leny][lenx];
	public static Box[][] already;
	///public static al_s alreadyBase [][] = new al_s[leny][lenx];
	public static char grid [][] = new char[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	
	public static void main(String [] args) {
		out.println("		2023 Day17.1");
		out.println("	SLOW ~1minute 45seconds");
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
		grid = new char[leny][lenx];
		//already = new al_s[leny][lenx];
		//alreadyBase = new al_s[leny][lenx];
		/*
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				already[yy][xx] = new al_s();
				alreadyBase[yy][xx] = new al_s();
			}
		}
		*/

		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

		already = new Box[leny][lenx];
		// initialize "boxes", then...
		for (int x=0; x < lenx; x++) {
			for (int y=0; y < leny; y++) {
				already[x][y] = new Box();
		        	//already[x][y].clear();
    		    	}
		}
		int sx = 0; int sy = 0;
		int ex = lenx-1; int ey = leny-1;
		int minmin = lenx*leny*9;
		{
			//Arrays.stream(already).parallelSetAll(row, i -> new Arrays.stream(i.path).forEach(row -> Arrays.fill(row, 0)));
			//Arrays.parallelSetAll(already, i -> new al_s());
			//Arrays.stream(already).setAll(row, i -> new al_s());
			//Arrays.stream(already).setAll(row -> i->new al_s());
			//Arrays.setAll(already, i->new al_s());
			//Arrays.setAll(already, i -> { al_s[] row = new al_s[leny]; Arrays.setAll(row, j -> new al_s()); return row; });
			//Arrays.setAll(already, i -> { int[] row = new int[cols]; Arrays.setAll(row, j -> i + j); return row; });
			/*
			Arrays.parallelSetAll(already, i -> {
		            int[] row = new int[lenx];
            			Arrays.parallelSetAll(row, Arrays.stream(j.path).forEach(row -> Arrays.fill(row, 0)));
            			return row;
			});
			*/
			/*
			already = new al_s[leny][lenx];
			for (int yy = 0; yy < leny; yy++) {
				for (int xx = 0; xx < lenx; xx++) {
					already[yy][xx] = new al_s();
				}
			}
			*/

			
			minPath = lenx*leny*9;
			//already = IntStream.range(0, leny).mapToObj(i -> IntStream.range(0, lenx).map(j -> new al_s()).toArray()).toArray(al_s[][]::new);
			//Arrays.parallelSetAll(already, i -> { al_s[] row = new al_s[leny]; Arrays.parallelSetAll(row, j -> new al_s()); return row; });
			//Arrays.parallelSetAll(already, i -> { al_s[] row = new al_s[leny]; Arrays.parallelSetAll(row, Arrays.stream(j.path).forEach(row1 -> Arrays.fill(row1, 0))); return row; });
			//Arrays.setAll(already, i -> { al_s[] row = new al_s[leny]; Arrays.parallelSetAll(row, j -> new al_s()); return row; });
			//already = alreadyBase.clone();
			//Arrays.stream(already).flatMap(Arrays::stream).forEach(Box::clear);
			for (int x=0; x < lenx; x++) {
			    for (int y=0; y < leny; y++) {
			        already[y][x].clear();
    				}
			}
			//Arrays.asList(already).parallelStream() .flatMap(arr -> Arrays.stream(arr)) .forEach(als -> Arrays.setAll(als.path, ix -> { int[] row = als.path[ix]; Arrays.fill(row, 0); return row; }));
			next(sx, sy, ex, ey, - (grid[sy][sx] - 48), 1, 0);
			if (minPath < minmin) {minmin = minPath;}

			//already = IntStream.range(0, leny).mapToObj(i -> IntStream.range(0, lenx).map(j -> new al_s()).toArray()).toArray(al_s[][]::new);
			//Arrays.setAll(already, i -> { al_s[] row = [leny]; Arrays.parallelSetAll(row, j -> new al_s()); return row; });
			//Arrays.asList(already).parallelStream() .flatMap(arr -> Arrays.stream(arr)) .forEach(als -> Arrays.setAll(als.path, ix -> { int[] row = als.path[ix]; Arrays.fill(row, 0); return row; }));
			//already = alreadyBase.clone();
			//Arrays.stream(already).flatMap(Arrays::stream).forEach(Box::clear);
			for (int x=0; x < lenx; x++) {
			    for (int y=0; y < leny; y++) {
			        already[y][x].clear();
    				}
			}
			next(sx, sy, ex, ey, - (grid[sy][sx] - 48), 2, 0);
			if (minPath < minmin) {minmin = minPath;}
		}


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minmin);
		out.println("");
	}
	public static void next (int x, int y, int ex, int ey, int path, int dir, int timesInDir) {
		if (x > lenx -1 || y > leny -1 || x < 0 || y < 0) {
			return;
		}
		if (timesInDir > 3) {return;}

		path += grid[y][x] - 48;
		if (path >= minPath) {return;}

		if (x == ex && y == ey) {
			if (path < minPath) {minPath = path;}
			return;
		}

		if (already[y][x].path[4*dir+timesInDir] == 0 || path < already[y][x].path[4*dir+timesInDir])
		{
			already[y][x].path[4*dir+timesInDir] = path;

			if (dir == 0) {
				next(x, y-1, ex, ey, path, 0, timesInDir+1);
				next(x-1, y, ex, ey, path, 3, 1);
				next(x+1, y, ex, ey, path, 1, 1);
			} else if (dir == 1) {
				next(x+1, y, ex, ey, path, 1, timesInDir+1);
				next(x, y-1, ex, ey, path, 0, 1);
				next(x, y+1, ex, ey, path, 2, 1);
			} else if (dir == 2) {
				next(x, y+1, ex, ey, path, 2, timesInDir+1);
				next(x+1, y, ex, ey, path, 1, 1);
				next(x-1, y, ex, ey, path, 3, 1);
			} else if (dir == 3) {
				next(x-1, y, ex, ey, path, 3, timesInDir+1);
				next(x, y+1, ex, ey, path, 2, 1);
				next(x, y-1, ex, ey, path, 0, 1);
			}
		}
	}

	public static class al_s {
		int path[][] = new int[4][5];
	}
}
public final class Box {

    private static final int PATH_ROWS = 5;
    private static final int PATH_COLS = 4;

    //

    int[] path;

    public Box() {
        this.path = new int[PATH_ROWS * PATH_COLS];
    }

    //

    public int get(int row, int col) {
        return this.path[this.makeIndex(row, col)];
    }

    public void set(int row, int col, int value) {
        this.path[this.makeIndex(row, col)] = value;
    }

    public void clear() {
        this.clear(0);
    }

    public void clear(int value) {
        Arrays.fill(this.path, value);
    }

    private int makeIndex(int row, int col) {
        if (0 > row || row >= PATH_ROWS)
            throw new IndexOutOfBoundsException("Row index " + row + " out of bounds");

        if (0 > col || col >= PATH_COLS)
            throw new IndexOutOfBoundsException("Column index " + col + " out of bounds");

        return (col * PATH_ROWS) + row;
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

