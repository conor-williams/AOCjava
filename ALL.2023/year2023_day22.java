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
class year2023_day22 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static class brick {
		int IfromX;
		int IfromY;
		int IfromZ;
		int ItoX;
		int ItoY;
		int ItoZ;
	}
	static brick bricks[] = new brick[2000];
	static int wall[][][] = new int[500][10][10];
	static int wallOrig[][][] = new int[500][10][10];
	static int maxX = 0; 
	static int maxY = 0;
	static int maxZ = 0;

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day22.1");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */

		for (int ii = 0; ii < 2000; ii++) {
			bricks[ii] = new brick();
		}
		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+),(\\d+),(\\d+)~(\\d+),(\\d),(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			bricks[i].IfromX = Integer.valueOf(m.group(1));
			bricks[i].IfromY = Integer.valueOf(m.group(2));
			bricks[i].IfromZ = Integer.valueOf(m.group(3));

			bricks[i].ItoX = Integer.valueOf(m.group(4));
			bricks[i].ItoY = Integer.valueOf(m.group(5));
			bricks[i].ItoZ = Integer.valueOf(m.group(6));
		}
		for (int i = 0; i < leny; i++) {
			if (bricks[i].ItoZ > maxZ) {maxZ = bricks[i].ItoZ;}
			if (bricks[i].IfromZ > maxZ) {maxZ = bricks[i].IfromZ;}
		}
		for (int i = 0; i < leny; i++) {
			if (bricks[i].ItoY > maxY) {maxY = bricks[i].ItoY;}
			if (bricks[i].IfromY > maxY) {maxY = bricks[i].IfromY;}
		}
		for (int i = 0; i < leny; i++) {
			if (bricks[i].ItoX > maxX) {maxX = bricks[i].ItoX;}
			if (bricks[i].IfromX > maxX) {maxX = bricks[i].IfromX;}
		}

		int maxBrickNum = 0;
		for (int i = 0; i < leny; i++) {
			for (int x = bricks[i].IfromX; x <= bricks[i].ItoX; x++) {
				for (int y = bricks[i].IfromY; y <= bricks[i].ItoY; y++) {
					for (int z = bricks[i].IfromZ; z <= bricks[i].ItoZ; z++) {
						wall[z][y][x] = i+1;
						maxBrickNum = i+1;
					}
				}
			}
		}
		for (int q = 0; q < 500; q++) {
			int bT[] = new int[2000]; int bTi = 0;
			for (int z = 2; z <= maxZ; z++) {
				bTi = 0;
				for (int y = 0; y <= maxY; y++) {
					for (int x = 0; x <= maxX; x++) {
						if (wall[z][y][x] != 0) {
							int end = bTi;
							int found = 0;
							for (int r = 0; r < end; r++) {
								if (bT[r] == wall[z][y][x]) {
									found = 1;
									break;
								}
							}
							if (found == 0) {
								bT[bTi] = wall[z][y][x];
								bTi++;
							}
						}
					}
				}

				int once = 0;
				for (int k = 0; k < bTi; k++) {
					int dealoff = 0;
out9:
					for (int y = 0; y <= maxY; y++) {
						for (int x = 0; x <= maxX; x++) {
							if (wall[z][y][x] == bT[k] && wall[z-1][y][x] != 0) {
								dealoff = 1;
								break out9;
							}
						}
					}

					if (dealoff == 0) {
						once++;
						for (int y = 0; y <= maxY; y++) {
							for (int x = 0; x <= maxX; x++) {
								if (wall[z][y][x] == bT[k]) {
									wall[z-1][y][x] = wall[z][y][x];
									wall[z][y][x] = 0;
								}
							}
						}
					}

				}
				if (once == 1) {
					z = z-2;
					if (z < 2) {z = 2;}
				}
			}
		}

		for (int z = maxZ; z >= 3 ; z--) {
			int allzeroes = 0;
out5:
			for (int y = 0; y <= maxY; y++) {
				for (int x = 0; x <= maxX; x++) {
					if (wall[z][y][x] != 0) {
						allzeroes = 1;
						break out5;
					}
				}

			}

			if (allzeroes == 0) {
				maxZ--;
			}

		}

		for (int z = 0; z <= maxZ; z++) {
			for (int y = 0; y <= maxY; y++) {
				for (int x = 0; x <= maxX; x++) {
					wallOrig[z][y][x] = wall[z][y][x];
				}
			}
		}

		int disintegrate = 0;
		int integ = 0;
		//////////////////////////
		for (int bN = 1; bN < leny+1; bN++) {
			/////////////
			for (int z = 0; z <= maxZ; z++) {
				for (int y = 0; y <= maxY; y++) {
					for (int x = 0; x <= maxX; x++) {
						wall[z][y][x] = wallOrig[z][y][x];
					}
				}
			}
			int changed = 0;
			for (int z = 0; z <= maxZ; z++) {
				for (int y = 0; y <= maxY; y++) {
					for (int x = 0; x <= maxX; x++) {
						if (wall[z][y][x] == bN) {
							changed = 1;
							wall[z][y][x] = 0;
						}
					}
				}
			}

			///////////////
			for (int z = 2; z <= maxZ; z++) {
				int bTi = 0; int bT[] = new int[1000];
				for (int y = 0; y <= maxY; y++) {
					for (int x = 0; x <= maxX; x++) {
						//for all brick types
						if (wall[z][y][x] != 0) {
							int end = bTi;
							int found = 0;
							for (int r = 0; r < end; r++) {
								if (bT[r] == wall[z][y][x]) {
									found = 1;
									break;
								}
							}
							if (found == 0) {
								bT[bTi] = wall[z][y][x];
								bTi++;
							}
						}
					}
				}

				for (int k = 0; k < bTi; k++) {
					int first = 1;
					int breakout = 0;
					int dealoff = 0;
out10:
					for (int y = 0; y <= maxY; y++) {
						for (int x = 0; x <= maxX; x++) {
							if (wall[z][y][x] == bT[k] && wall[z-1][y][x] != 0) {
								dealoff = 1;
								break out10;
							}
						}
					}

					if (dealoff == 0) {
						if (first == 1) { first++; disintegrate++; }
						break;
					} else {
						integ++;
					}

					if (breakout == 1) {break;}
				}
			}
		}




		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(leny-disintegrate);
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

