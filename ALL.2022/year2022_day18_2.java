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
class year2022_day18_2 {
	//	        public static int maxPath = 0;
	//public static int lenx = 0;
	public static int leny = 0;
	public static int SX = 40;
	public static char grid [][][] = new char[SX+3][SX+3][SX+3];
	public static int already [][][] = new int[SX+3][SX+3][SX+3];
	public static int alreadyOrig [][][] = new int[SX+3][SX+3][SX+3];
	public static char gridPlay [][][] = new char[SX+3][SX+3][SX+3];
	//    public static int already [][] = new int[leny][lenx];

	public static void main(String [] args) {
		out.println("		2022 Day18.2");
		out.println("	SLOW ~20seconds");
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
		for (int zz = 0; zz < SX; zz++) {
			for (int yy = 0; yy < SX; yy++) {
				for (int xx = 0; xx < SX; xx++) {
					grid[zz][yy][xx] = '.';
				}
			}
		}
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+),(\\d+),(\\d+)");
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
			if (m.find()) {
				int x1 = Integer.valueOf(m.group(1));
				int y1 = Integer.valueOf(m.group(2));
				int z1 = Integer.valueOf(m.group(3));
				grid[z1+1][y1+1][x1+1] = '#';
			}
		}
		{
			for (int x = SX; x >= 0; x--) {
				for (int z = 0; z < SX; z++) {
					for (int y = 0; y < SX; y++) {
						if (grid[z][y][x] == '.') {
							//memcpy(gridPlay, grid, sizeof(grid));
							for (int i = 0; i < grid.length; i++) {
								gridPlay[i] = new char[grid[i].length][];
								for (int j = 0; j < grid[i].length; j++) {
									gridPlay[i][j] = grid[i][j].clone();

								}
							}
							//gridPlay = grid.clone();
							//memset(already, 0, sizeof(already));
							//already = new int[SX+3][SX+3][SX+3];
							for (int i = 0; i < already.length; i++) {
								already[i] = new int[alreadyOrig[i].length][];
								for (int j = 0; j < already[i].length; j++) {
									already[i][j] = alreadyOrig[i][j].clone();

								}
							}
							////already = alreadyOrig.clone();
							//already = new int[SX+3][SX+3][SX+3];
							if (floodFill3Dp(x, y, z) == -22) {} else {
								for (int i = 0; i < gridPlay.length; i++) {
									grid[i] = new char[gridPlay[i].length][];
									for (int j = 0; j < gridPlay[i].length; j++) {
										grid[i][j] = gridPlay[i][j].clone();

									}
								}
								//memcpy(grid, gridPlay, sizeof(grid));
							}
						}
					}
				}
			}

		}

		int surfaceS = 0;
		{
			for (int xRay = SX-2; xRay >= 0; xRay--) {
				for (int z = 0; z < SX; z++) {
					for (int y = 0; y < SX; y++) {
						if (grid[z][y][xRay] == '#' && grid[z][y][xRay+1] == '.') {
							surfaceS++;
						}
					}
				}
			}
			for (int xRay = 1; xRay < SX; xRay++) {
				for (int z = 0; z < SX; z++) {
					for (int y = 0; y < SX; y++) {
						if (grid[z][y][xRay] == '#' && grid[z][y][xRay-1] == '.') {
							surfaceS++;
						}
					}
				}
			}
		}
		{
			for (int yRay = SX-2; yRay >= 0; yRay--) {
				for (int z = 0; z < SX; z++) {
					for (int x = 0; x < SX; x++) {
						if (grid[z][yRay][x] == '#' && grid[z][yRay+1][x] == '.') {
							surfaceS++;
						}
					}
				}
			}
			for (int yRay = 1; yRay < SX; yRay++) {
				for (int z = 0; z < SX; z++) {
					for (int x = 0; x < SX; x++) {
						if (grid[z][yRay][x] == '#' && grid[z][yRay-1][x] == '.') {
							surfaceS++;
						}
					}
				}
			}
		}
		{
			for (int zRay = SX-2; zRay >= 0; zRay--) {
				for (int y = 0; y < SX; y++) {
					for (int x = 0; x < SX; x++) {
						if (grid[zRay][y][x] == '#' && grid[zRay+1][y][x] == '.') {
							surfaceS++;
						}
					}
				}
			}
			for (int zRay = 1; zRay < SX; zRay++) {
				for (int y = 0; y < SX; y++) {
					for (int x = 0; x < SX; x++) {
						if (grid[zRay][y][x] == '#' && grid[zRay-1][y][x] == '.') {
							surfaceS++;
						}
					}
				}
			}

		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(surfaceS);
		out.println("");
	}
	public static int floodFill3Dp(int x, int y, int z) {
		//printf("in ff %d %d %d (ind:%d)\n", x, y, z, ind); fflush(stdout);
		if (x < 0 || x >= SX || y < 0 || y >= SX || z < 0 || z >= SX) {
			return -22;
		}
		if (already[z][y][x] != 0) {return 0;} else {already[z][y][x] = 1;}


		if (gridPlay[z][y][x] == '#') {
			return 0;
		} else {
			gridPlay[z][y][x] = 'X';
		}

		if (floodFill3Dp(x+1, y, z) == -22) { return -22;}
		if (floodFill3Dp(x-1, y, z) == -22) { return -22;}
		if (floodFill3Dp(x, y+1, z) == -22) { return -22;}
		if (floodFill3Dp(x, y-1, z) == -22) { return -22;}
		if (floodFill3Dp(x, y, z+1) == -22) { return -22;}
		if (floodFill3Dp(x, y, z-1) == -22) { return -22;}
		/*
		   if (x+1 < SX && floodFill3Dp(x+1, y, z) == -22) { return -22;}
		   if (x-1 >= 0 && floodFill3Dp(x-1, y, z) == -22) { return -22;}
		   if (y+1 < SX && floodFill3Dp(x, y+1, z) == -22) { return -22;}
		   if (y-1 >= 0 && floodFill3Dp(x, y-1, z) == -22) { return -22;}
		   if (z+1 < SX && floodFill3Dp(x, y, z+1) == -22) { return -22;}
		   if (z-1 >= 0 && floodFill3Dp(x, y, z-1) == -22) { return -22;}
		   */

		return 1;
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

