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
class year2022_day24_2 {
	//	        public static int maxPath = 0;
	static int minPath = Integer.MAX_VALUE;
	static int minminPath = Integer.MAX_VALUE;
	static int minPathTot = 0;
	public static int lenx = 0;
	public static int leny = 0;
	static Map <TreTuple<Integer, Integer, Integer>, Integer> visited = new HashMap<>();
	static int dep = 1000;
	public static char grid [][] = new char[leny][lenx];
	public static int gridNumInter[][] = new int[leny][lenx];
	public static int gridNum[][][] = new int[1001][leny][lenx];

	static Stack <TreTuple<Integer, Integer, Integer>> mp2 = new Stack<>();
	public static void main(String [] args) {
		out.println("		2022 Day24.2");
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
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}
		gridNumInter = new int[leny][lenx];
		gridNum = new int[1001][leny][lenx];
		for (int y = 1; y < leny-1; y++) {
			for (int x = 1; x < lenx-1; x++) {
				switch(grid[y][x]) {
					case '^':
						gridNum[0][y][x] = 1;
						break;
					case '>':
						gridNum[0][y][x] = 2;
						break;
					case 'v':
						gridNum[0][y][x] = 4;
						break;
					case '<':
						gridNum[0][y][x] = 8;
						break;
					case '.':
						gridNum[0][y][x] = 0;
						break;
				}
			}
		}

		for (int i = 1; i < dep; i++) {
			gridNum[i] = new int[leny][lenx];
			makeMovesAll(i);
			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					if (x == 0) {
						gridNum[i][y][x] = -1;
					} else if (x == lenx -1) {
						gridNum[i][y][x] = -1;
					} else if (y == 0) {
						gridNum[i][y][x] = -1;
					} else if (y == leny-1) {
						gridNum[i][y][x] = -1;
					}
					gridNum[i][0][1] = 0;
					gridNum[i][leny-1][lenx-2] = 0;
				}
			}

		}
		int tot = 0;
		dijk2(1, 0, lenx-2, leny-1, 0);

		tot += minPath;
		dijk2(lenx-2, leny-1, 1, 0, minPath);
		tot += minPath;
		dijk2(1, 0, lenx-2, leny-1, minPath);
		tot += minPath;

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}

	static int dijk2(int conSX, int conSY, int conEX, int conEY, int mmpp) {

		minPath = Integer.MAX_VALUE;
		if (mmpp != 0) {
			for (int i = mmpp; i < dep; i++) {
				for (int y = 0; y < leny; y++) {
					for (int x = 0; x < lenx; x++) {
						gridNum[i-mmpp][y][x] = gridNum[i][y][x];
					}
				}
			}
			dep -= mmpp;
		} else {
		}

		visited.clear();
		mp2.clear();
		mp2.push(new TreTuple(conSX, conSY, 0));
		mp2.push(new TreTuple(conSX, conSY, 1));
		mp2.push(new TreTuple(conSX, conSY, 2));
		mp2.push(new TreTuple(conSX, conSY, 3));

egg:
		while (true) {
			if (mp2.size() == 0) { break; }
			var fir = mp2.pop();

			int x = fir.first; int y = fir.second; int pa = fir.third;

			if (pa < 350) {
				if (!visited.containsKey(new TreTuple(x, y, pa)) && gridNum[pa][y][x] == 0) { 
					visited.put(new TreTuple(x, y, pa), 1);
					if (x == conEX && y == conEY) {
						if (pa < minPath) {minPath = pa;}
						continue egg;
					}

					if (y-1 >= 0) {
						for (int aa = 1; aa < 5; aa++) {
							if (gridNum[pa+aa][y-1][x] != 0) {break;}
							if (gridNum[pa+aa][y-1][x] == 0) {
								int newPa = pa+aa;
								mp2.push(new TreTuple(x, y-1, newPa));
							}
						}
					}
					if (x+1 < lenx) {
						for (int bb = 1; bb < 5; bb++) {
							if (gridNum[pa+bb][y][x+1] != 0){break;}
							if (gridNum[pa+bb][y][x+1] == 0) {
								int newPa = pa+bb;
								mp2.push(new TreTuple(x+1, y, newPa));
							}
						} 
					}
					if (y+1 < leny) {
						for (int cc = 1; cc < 5; cc++) {
							if (gridNum[pa+cc][y+1][x] != 0) {break;}
							if (gridNum[pa+cc][y+1][x] == 0) {
								int newPa = pa+cc;
								mp2.push(new TreTuple(x, y+1, newPa));
							}
						}
					}
					if (x-1 >= 0) {
						for (int dd = 1; dd < 5; dd++) {
							if (gridNum[pa+dd][y][x-1] != 0) {break;}
							if (gridNum[pa+dd][y][x-1] == 0) {
								int newPa = pa+dd;
								mp2.push(new TreTuple(x-1, y, newPa));
							}
						}
					}
				}
			}
		}
		return 0;
	}


	static void makeMovesAll(int gridX) 
	{
		for (int y = 1; y < leny-1; y++) {
			for (int x = 1; x < lenx-1; x++) {
				if (gridNum[gridX-1][y][x] != 0) {
					if ((gridNum[gridX-1][y][x] & (1 << 0)) != 0) { 
						if (y == 1) {
							gridNumInter[leny-2][x] |= 1 << 0;
						} else {
							gridNumInter[y-1][x] |= 1 << 0;
						}
					}

					if ((gridNum[gridX-1][y][x] & (1 << 1)) != 0) { 
						if (x == lenx-2) {
							gridNumInter[y][1] |= 1 << 1;
						} else {
							gridNumInter[y][x+1] |= 1 << 1;
						}
					}

					if ((gridNum[gridX-1][y][x] & (1 << 2)) != 0) { 
						if (y == leny-2) {
							gridNumInter[1][x] |= 1 << 2;
						} else {
							gridNumInter[y+1][x] |= 1 << 2;
						}
					}

					if ((gridNum[gridX-1][y][x] & (1 << 3)) != 0) { 
						if (x == 1) {
							gridNumInter[y][lenx-2] |= 1 << 3;
						} else {
							gridNumInter[y][x-1] |= 1 << 3;
						}
					}
				} else {
				}
			}
		}

		for (int y = 1; y < leny-1; y++) {
			for (int x = 1; x < lenx-1; x++) {
				gridNum[gridX][y][x] = gridNumInter[y][x];
			}
		}

		gridNumInter = new int[leny][lenx];
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

