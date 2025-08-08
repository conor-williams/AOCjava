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


class year2019_day3_2 {
	public static Map<Integer, Map<Integer, Character>> grid = new HashMap<>();
	public static void main(String [] args) {
		out.println("		2019 Day3.2");

		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(U|R|D|L)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		int pX = 0; int pY = 0;
		int minX = 9999999, minY = 9999999;
		int maxX = 0, maxY = 0;
		for (int i = 0; i < blah.size(); i++) {
			pX = 0; pY = 0;
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			Vector <Integer> var_ints = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				Matcher m = p.matcher(ne);
				m.find();
				char URDL = m.group(1).charAt(0);
				int val = Integer.valueOf(m.group(2));
				switch (URDL) {
					case 'U':
						pY-=val;
						break;
					case 'R':
						pX+= val;
						break;
					case 'D':
						pY+=val;
						break;
					case 'L':
						pX-=val;
						break;
				}
				if (pX > maxX) {maxX = pX;}
				if (pX < minX) {minX = pX;}
				if (pY > maxY) {maxY = pY;}
				if (pY < minY) {minY = pY;}
			}
		}
		int GRIDX = maxX-minX+30;
		int GRIDY = maxY-minY+30;
		int STARTX=Math.abs(minX)+15;
		int STARTY=Math.abs(minY)+15;
		//grid = new char[GRIDY][GRIDX];
		Map<Integer, Map<Integer, Integer>> trip1 = new HashMap<>();
		Map<Integer, Map<Integer, Integer>> trip2 = new HashMap<>();

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//int trip1[][] = new int[GRIDY][GRIDX];
		//int trip2[][] = new int[GRIDY][GRIDX];
		for (int i = 0; i < blah.size(); i++) {
			int lenlen = 0;
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			Vector <Integer> var_ints = new Vector<>();
			int x = STARTX;
			int y = STARTY;
			grid.putIfAbsent(y, new HashMap<>());
			if (i == 1) {
				grid.get(y).put(x, '1');
				//grid[y][x] = '1';
			} else if (i == 2) {
				if (grid.get(y).get(x) != null &&  grid.get(y).get(x) == '1') {
					//grid[y][x] = '3';
					grid.get(y).put(x, '3');
				} else {
					//grid[y][x] = '2';
					grid.get(y).put(x, '2');
				}
			}
			while (scanner.hasNext()) {
				String ne = scanner.next();
				Matcher m = p.matcher(ne);
				m.find();
				char URDL = m.group(1).charAt(0);
				int val = Integer.valueOf(m.group(2));
				switch (URDL) {
					case 'U':
						for (int yy = 0; yy < val; yy++) {
							y--;
							lenlen++;
							grid.putIfAbsent(y, new HashMap<>());
							trip1.putIfAbsent(y, new HashMap<>());
							trip2.putIfAbsent(y, new HashMap<>());
							if (i == 0) {
								grid.get(y).put(x, '1');
								if (trip1.get(y).get(x) == null || trip1.get(y).get(x) == 0) {
									trip1.get(y).put(x, lenlen);
								}
							} else if (i == 1) {
								if (trip2.get(y).get(x) == null || trip2.get(y).get(x) == 0) {
									trip2.get(y).put(x, lenlen);
								}
								if (grid.get(y).get(x) != null) {
								       if (grid.get(y).get(x) == '1' || grid.get(y).get(x) == '3') {
										grid.get(y).put(x, '3');
								        } else {
										grid.get(y).put(x, '2');
									}

									//grid[y][x] = '3';
								} else {
									grid.get(y).put(x, '2');
									//grid[y][x] = '2';
								}
							}
						}
						break;
					case 'R':
						for (int xx = 0; xx < val; xx++) {
							x++;
							lenlen++;
							grid.putIfAbsent(y, new HashMap<>());
							trip1.putIfAbsent(y, new HashMap<>());
							trip2.putIfAbsent(y, new HashMap<>());
							if (i == 0) {
								if (trip1.get(y).get(x) == null || trip1.get(y).get(x) == 0) {
									trip1.get(y).put(x, lenlen);
								}
								//grid[y][x] = '1';
								grid.get(y).put(x, '1');
							} else if (i == 1) {
								if (trip2.get(y).get(x) == null || trip2.get(y).get(x) == 0) {
									trip2.get(y).put(x,lenlen);
								}
								if (grid.get(y).get(x) != null) {
									if (grid.get(y).get(x) == '1' || grid.get(y).get(x) == '3') {
									//grid[y][x] = '3';
										grid.get(y).put(x, '3');
									} else {
										grid.get(y).put(x, '2');
									}
								} else {
									//grid[y][x] = '2';
									grid.get(y).put(x, '2');
								}
							}
						}
						break;
					case 'D':
						for (int yy = 0; yy < val; yy++) {
							y++;
							lenlen++;
							grid.putIfAbsent(y, new HashMap<>());
							trip1.putIfAbsent(y, new HashMap<>());
							trip2.putIfAbsent(y, new HashMap<>());
							if (i == 0) {
								if (trip1.get(y).get(x) == null || trip1.get(y).get(x) == 0) {
									trip1.get(y).put(x, lenlen);
								}
								grid.get(y).put(x, '1');
							} else if (i == 1) {
								if (trip2.get(y).get(x) == null || trip2.get(y).get(x) == 0) {
									trip2.get(y).put(x, lenlen);
								}
								if (grid.get(y).get(x) != null) {
									if (grid.get(y).get(x) == '1' || grid.get(y).get(x) == '3') {
										grid.get(y).put(x, '3');
									} else {
										grid.get(y).put(x, '2');
									}
								} else {
									grid.get(y).put(x, '2');
									//grid[y][x] = '2';
								}
							}
						}
						break;
					case 'L':
						for (int xx = 0; xx < val; xx++) {
							x--;
							lenlen++;
							grid.putIfAbsent(y, new HashMap<>());
							trip1.putIfAbsent(y, new HashMap<>());
							trip2.putIfAbsent(y, new HashMap<>());
							if (i == 0) {
								if (trip1.get(y).get(x) == null || trip1.get(y).get(x) == 0) {
									trip1.get(y).put(x, lenlen);
								}
								//grid[y][x] = '1';
								grid.get(y).put(x, '1');
							} else if (i == 1) {
								if (trip2.get(y).get(x) == null || trip2.get(y).get(x) == 0) {
									trip2.get(y).put(x, lenlen);
								}
								if (grid.get(y).get(x) != null) {
								        if (grid.get(y).get(x) == '1' || grid.get(y).get(x) == '3') {
										grid.get(y).put(x, '3');
									} else {
										grid.get(y).put(x, '2');
									}

									//grid[y][x] = '3';
								} else {
									grid.get(y).put(x, '2');
									//grid[y][x] = '2';
								}
							}
						}
						break;
				}

			}

		}
		int minlen = 9999999;
		for (var yyy: grid.entrySet()) {
		//for (int yy = 0; yy < GRIDY; yy++) 
			//for (int xx = 0; xx < GRIDX; xx++) {
			//out.println(yyy.getValue());
			for (var xxx: yyy.getValue().entrySet()) {
				if (xxx.getValue() == '3') {
					int len = trip1.get(yyy.getKey()).get(xxx.getKey()) + trip2.get(yyy.getKey()).get(xxx.getKey());
					if (len < minlen) {minlen = len;}
				}
			}
		}

		out.print("**j_ans: ");
		out.print(minlen);
		out.println("");
	}
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

