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
class year2022_day23_2 {
	//	        public static int maxPath = 0;
	//static int numRounds = 10;

	static int var_round = 0;
	public static int PAD = 4800;
	static int SX = (PAD*2)+200;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[SX][SX];
	public static char gridNew [][] = new char[SX][SX];
	public static char gridNewOrig [][] = new char[SX][SX];
	public static int gridTmp [][] = new int[SX][SX];
	static int maxX = 0, minX = 0, maxY = 0, minY = 0;

	public static void main(String [] args) {
		out.println("		2022 Day23.2");
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
		//grid = new char[][lenx];
		{
			for (int y = 0; y < PAD; y++) {
				for (int ii = 0; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}

			for (int y = PAD; y < leny+PAD; y++) {
				for (int ii = 0; ii < PAD; ii++) {
					grid[y][ii] = '.';
				}
				for (int ii = PAD; ii < PAD+lenx; ii++) {
					grid[y][ii] = blah.get(y-PAD).charAt(ii-PAD);
				}
				for (int ii = lenx+PAD; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}
			for (int y = PAD+leny; y < PAD+leny+PAD; y++) {
				for (int ii = 0; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}
		}
		{
			for (int yy = 0; yy < SX; yy++) {
				for (int xx = 0; xx < SX; xx++) {
					gridNew[yy][xx] = '.';
					gridNewOrig[yy][xx] = '.';
				}
			}
		}
		{
			minX = 99999;
			minY = 99999;
			for (int y = PAD-10; y < PAD+100; y++) {
				for (int x = PAD-10; x < PAD+100; x++) {
					if (grid[y][x] == '#') {
						if (x < minX) {minX = x;}
						if (y < minY) {minY = y;}
					}
				}
			}
			for (int y = PAD+leny-100; y < PAD+leny+100; y++) {
				for (int x = PAD+lenx-100; x < PAD+lenx+100; x++) {
					if (grid[y][x] == '#') {
						if (x > maxX) {maxX = x;}
						if (y > maxY) {maxY = y;}
					}
				}
			}
			maxX++;
			maxY++;
		}
		//out.print("minY, maxY, minX, maxX"); out.print(minY); out.print(" "); out.print(maxY); out.print(" "); out.print(minX); out.print(" "); out.println(maxX);
		lenx = lenx+PAD*2;
		leny = leny+PAD*2;

		int rounds = 0;
		setitup(0);

		do {
			getmaxes();
			moveNfirst();
			var_round = rounds;
			setitup(1);
			rounds++;
			//====================
			getmaxes();
			moveSfirst();
			var_round = rounds;
			setitup(1);
			rounds++;
			//=====================
			getmaxes();
			moveWfirst();
			var_round = rounds;
			setitup(1);
			rounds++;
			//=====================
			getmaxes();
			moveEfirst();
			var_round = rounds;
			setitup(1);
			rounds++;
			//=====================
			//out.print("round: "); out.println(rounds);
		} while (true);

		//		System.setOut(originalOut);
	}
	static void getmaxes() {
		maxX--; maxY--;
		for (int y = minY - 2; y < maxY+3; y++) {
			for (int x = minX - 2; x < maxY+3; x++) {
				if (grid[y][x] == '#') {
					if (x < minX) {minX = x;}
					if (y < minY) {minY = y;}
					if (x > maxX) {maxX = x;}
					if (y > maxY) {maxY = y;}
				}
			}
		}
		maxX++; maxY++;
		//out.print("minY, maxY, minX, maxX"); out.print(minY); out.print(" "); out.print(maxY); out.print(" "); out.print(minX); out.print(" "); out.println(maxX);
	}


	static void setitup(int reset)
	{
		//=====================
		//if (var_round > 2) {
		int found = 0;
after:
		for (int yy = minY-2; yy < maxY+2; yy++) {
			for (int xx = minX-2; xx < maxX+2; xx++) {
				if (gridNew[yy][xx] != grid[yy][xx]) {
					found = 1;
					break after;
				}
			}
		}
		if (found == 0) {
			out.print("**j_ans: ");
			out.print(var_round+1);
			out.println("");
			Runtime.getRuntime().halt(0);
		}

		//if (Arrays.deepEquals(gridNew, grid)) 
		//}
		for (int yy = minY-1; yy < maxY+1; yy++) {
			for (int xx = minX-1; xx < maxX+1; xx++) {
				gridTmp[yy][xx] = 0;
			}
		}
		if (reset == 1) {
			for (int yy = minY-1; yy < maxY+1; yy++) {
				for (int xx = minX-1; xx < maxX+1; xx++) {
					grid[yy][xx] = gridNew[yy][xx];
					gridNew[yy][xx] = '.';
				}
			}
			///grid = Arrays.stream(gridNew).map(char[]::clone).toArray(char[][]::new);
			//gridNew = Arrays.stream(gridNewOrig).map(char[]::clone).toArray(char[][]::new);
		}

	}


	static void moveNfirst()
	{
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' &&
						grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.' &&
						grid[y][x+1] == '.' && 
						grid[y+1][x+1] == '.' &&  grid[y+1][x] == '.' && grid[y+1][x-1] == '.' &&
						grid[y][x-1] == '.') {
					gridNew[y][x] = '#';
					continue;
						}
				if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					gridTmp[y-1][x]++;			
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					gridTmp[y+1][x]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y][x-1]
					gridTmp[y][x-1]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					//move to grid grid[y][x+1]
					gridTmp[y][x+1]++;
				} else if (grid[y][x] == '#') {
					gridNew[y][x] = '#';
				}
			}
		}

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					if (gridTmp[y-1][x] == 1) {
						gridNew[y-1][x] = '#';
					} else {
						gridNew[y][x] = '#';
					}
					//move to grid grid[y-1][x]
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y+1][x] == 1) {
						gridNew[y+1][x] = '#';
					} else {
						//move to grid grid[y+1][x]
						gridNew[y][x] = '#';			
					}
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y][x-1] == 1) {
						gridNew[y][x-1] = '#';			
					} else {
						gridNew[y][x] = '#';			
					}
					//move to grid grid[y][x-1]
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					if (gridTmp[y][x+1] == 1) {
						gridNew[y][x+1] = '#';
					} else {
						//move to grid grid[y][x+1]
						gridNew[y][x] = '#';
					}
				}
			}
		}
	}

	static void moveSfirst() 
	{
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' &&
						grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.' &&
						grid[y][x+1] == '.' && 
						grid[y+1][x+1] == '.' &&  grid[y+1][x] == '.' && grid[y+1][x-1] == '.' &&
						grid[y][x-1] == '.') {
					gridNew[y][x] = '#';
					continue;
						}

				if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y+1][x]
					gridTmp[y+1][x]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y][x-1]
					gridTmp[y][x-1]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					//move to grid grid[y][x+1]
					gridTmp[y][x+1]++;
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					gridTmp[y-1][x]++;			
					//move to grid grid[y-1][x]
				} else if (grid[y][x] == '#') {
					gridNew[y][x] = '#';
				}
			}
		}

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {

				if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y+1][x] == 1) {
						gridNew[y+1][x] = '#';
					} else {
						//move to grid grid[y+1][x]
						gridNew[y][x] = '#';			
					}
					//move to grid grid[y-1][x]
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y][x-1] == 1) {
						gridNew[y][x-1] = '#';			
					} else {
						gridNew[y][x] = '#';			
					}
					//move to grid grid[y][x-1]
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					if (gridTmp[y][x+1] == 1) {
						gridNew[y][x+1] = '#';
					} else {
						//move to grid grid[y][x+1]
						gridNew[y][x] = '#';
					}
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					if (gridTmp[y-1][x] == 1) {
						gridNew[y-1][x] = '#';;			
					} else {
						gridNew[y][x] = '#';
					}
				}
			}
		}
	}

	static void moveWfirst() {
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' &&
						grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.' &&
						grid[y][x+1] == '.' && 
						grid[y+1][x+1] == '.' &&  grid[y+1][x] == '.' && grid[y+1][x-1] == '.' &&
						grid[y][x-1] == '.') {
					gridNew[y][x] = '#';
					continue;
						}

				if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y][x-1]
					gridTmp[y][x-1]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					//move to grid grid[y][x+1]
					gridTmp[y][x+1]++;
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					gridTmp[y-1][x]++;			
					//move to grid grid[y-1][x]
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y+1][x]
					gridTmp[y+1][x]++;			
				} else if (grid[y][x] == '#') {
					gridNew[y][x] = '#';
				}
			}
		}

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y][x-1] == 1) {
						gridNew[y][x-1] = '#';			
					} else {
						gridNew[y][x] = '#';			
					}
					//move to grid grid[y][x-1]
				} else if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					if (gridTmp[y][x+1] == 1) {
						gridNew[y][x+1] = '#';
					} else {
						//move to grid grid[y][x+1]
						gridNew[y][x] = '#';
					}
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					if (gridTmp[y-1][x] == 1) {
						gridNew[y-1][x] = '#';
					} else {
						gridNew[y][x] = '#';
					}
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y+1][x] == 1) {
						gridNew[y+1][x] = '#';
					} else {
						//move to grid grid[y+1][x]
						gridNew[y][x] = '#';			
					}
				}
				//move to grid grid[y-1][x]
			}
		}
	}

	//void moveEfirst() 
	static void moveEfirst() {
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' &&
						grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.' &&
						grid[y][x+1] == '.' && 
						grid[y+1][x+1] == '.' &&  grid[y+1][x] == '.' && grid[y+1][x-1] == '.' &&
						grid[y][x-1] == '.') {
					gridNew[y][x] = '#';
					continue;
						}

				if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					//move to grid grid[y][x+1]
					gridTmp[y][x+1]++;
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					gridTmp[y-1][x]++;			
					//move to grid grid[y-1][x]
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y+1][x]
					gridTmp[y+1][x]++;			
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					//move to grid grid[y][x-1]
					gridTmp[y][x-1]++;			
				} else if (grid[y][x] == '#') {
					gridNew[y][x] = '#';
				}
			}
		}

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				if (grid[y][x] == '#' && grid[y-1][x+1] == '.' && grid[y][x+1] == '.' && grid[y+1][x+1] == '.') {
					if (gridTmp[y][x+1] == 1) {
						gridNew[y][x+1] = '#';
					} else {
						//move to grid grid[y][x+1]
						gridNew[y][x] = '#';
					}
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y-1][x] == '.' && grid[y-1][x+1] == '.') {
					if (gridTmp[y-1][x] == 1) {
						gridNew[y-1][x] = '#';
					} else {
						gridNew[y][x] = '#';
					}
				} else if (grid[y][x] == '#' && grid[y+1][x+1] == '.' && grid[y+1][x] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y+1][x] == 1) {
						gridNew[y+1][x] = '#';
					} else {
						//move to grid grid[y+1][x]
						gridNew[y][x] = '#';			
					}
				} else if (grid[y][x] == '#' && grid[y-1][x-1] == '.' && grid[y][x-1] == '.' && grid[y+1][x-1] == '.') {
					if (gridTmp[y][x-1] == 1) {
						gridNew[y][x-1] = '#';			
					} else {
						gridNew[y][x] = '#';			
					}
					//move to grid grid[y][x-1]
				}
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

