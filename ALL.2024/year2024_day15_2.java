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
// /java -Xmx2g year2019_day3.java *i1.txt


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2024_day15_2 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static char gridTmp [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static Vector<Tuple<Integer, Integer>> ve = new Vector<>();
	public static int madeMove = 0;
	public static void main(String [] args) {
		out.println("		2024 Day15.2");
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		grid = new char[leny][lenx];
		int gg = 0;
		String moves = new String("");
		for (int i = 0; i < blah.size();i++) {
			if (blah.get(i).length() == 0) {
				leny--;
				gg = 1;
				continue;
			}
			if (gg == 0) {
				grid[i] = blah.get(i).toCharArray();
			} else if (gg == 1) {
				leny--;
				moves += new String(blah.get(i));
			}
		}
		gridTmp = new char[leny][lenx*2];
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				switch (grid[y][x]) {
					case '.':
						gridTmp[y][x*2] = '.';
						gridTmp[y][x*2+1] = '.';
						break;
					case '#':
						gridTmp[y][x*2] = '#';
						gridTmp[y][x*2+1] = '#';
						break;
					case 'O':
						gridTmp[y][x*2] = '[';
						gridTmp[y][x*2+1] = ']';
						break;
					case '@':
						gridTmp[y][x*2] = '@';
						gridTmp[y][x*2+1] = '.';
						break;
				}
			}
		}
		lenx *= 2;
		grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);

		out.print("moves : "); out.println(moves);
		out.print("moves length: "); out.println(moves.length());
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		out.println(grid[leny-1]);
		out.println("---------");


		int sx = 0;
		int sy = 0;
after1:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == '@') {
					grid[y][x] = '.';
					sx = x; sy = y; break after1;
				}
			}
		}
		int numMoves = moves.length();


		for (int i = 0; i < numMoves; i++) {
			if (!ve.isEmpty()) {ve.clear();}
			madeMove = 0;
			switch (moves.charAt(i)) {
				case '^':
					//sx stays same
					if (grid[sy-1][sx] == '.') {
						sy--;
						break;
					} else if (grid[sy-1][sx] == '#') {
						//no move
						break;
					} else if (grid[sy-1][sx] == '[') {
						Tuple<Integer, Integer> pa1 = new Tuple(sx, sy); ve.add(pa1);
						copyToGridTmp();
						int ret = checkAbove(sx, sx+1, sy-1);
						if (ret == -1) {
							break;
						} else {
							makeMoveUp();
							sy--;
						}
						if (madeMove == 1) {gridTmp[sy][sx+1] = '.';}
					} else if (grid[sy-1][sx] == ']') {
						Tuple<Integer, Integer> pa1 = new Tuple(sx, sy); ve.add(pa1);
						copyToGridTmp();
						int ret = checkAbove(sx, sx-1, sy-1);
						if (ret == -1) {
							break;
						} else {
							makeMoveUp();
							sy--;
						}
						if (madeMove == 1) {gridTmp[sy][sx-1] = '.';}
					} else {
					}
					break;
				case '>':
					//y stays same
					if (grid[sy][sx+1] == '.') {
						sx++;
						break;
					} else if (grid[sy][sx+1] == '#') {
						//no move
						break;
					} else if (grid[sy][sx+1] == '[') {
						//O with no dot ending in hash -- no move
						for (int x = sx+2; x < lenx; x++) {
							if (grid[sy][x] == '.') {
								grid[sy][sx+1] = '.';
								grid[sy][x] = '[';
								for (int x1 = sx+2; x1 <= x; x1++) {
									if (grid[sy][x1] == '[') {
										grid[sy][x1] = ']';
									} else if (grid[sy][x1] == ']') {
										grid[sy][x1] = '[';
									} else {
									}
								}
								sx++;
								break;
							} else if (grid[sy][x] == '[' || grid[sy][x] == ']') {
								continue;
							} else if (grid[sy][x] == '#') {
								//nomove
								break;
							} else {
							}
						}
					}
					break;
				case 'v':
					//sx stays same
					if (grid[sy+1][sx] == '.') {
						sy++;
						break;
					} else if (grid[sy+1][sx] == '#') {
						//no move
						break;
					} else if (grid[sy+1][sx] == '[') {
						Tuple<Integer, Integer> pa1 = new Tuple(sx, sy); ve.add(pa1);
						copyToGridTmp();
						int ret = checkBelow(sx, sx+1, sy+1);
						if (ret == -1) {
							break;
						} else {
							makeMoveDown();
							sy++;
						}
						if (madeMove == 1) {gridTmp[sy][sx+1] = '.';}
					} else if (grid[sy+1][sx] == ']') {
						Tuple<Integer, Integer> pa1 = new Tuple(sx, sy); ve.add(pa1);
						copyToGridTmp();
						int ret = checkBelow(sx, sx-1, sy+1);
						if (ret == -1) {
							break;
						} else {
							makeMoveDown();
							sy++;
						}
						if (madeMove == 1) {gridTmp[sy][sx-1] = '.';}
					} else {
					}
					break;
				case '<':
					//sy stays same
					if (grid[sy][sx-1] == '.') {
						sx--;
						break;
					} else if (grid[sy][sx-1] == '#') {
						//no move
						break;
					} else if (grid[sy][sx-1] == ']') {
						//O with no dot ending in hash -- no move
						for (int x = sx-2; x > 0; x--) {
							if (grid[sy][x] == '.') {
								grid[sy][sx-1] = '.';
								grid[sy][x] = ']';
								for (int x1 = sx-2; x1 >= x; x1--) {
									if (grid[sy][x1] == '[') {
										grid[sy][x1] = ']';
									} else if (grid[sy][x1] == ']') {
										grid[sy][x1] = '[';
									} else {
									}
								}
								sx--;
								break;
							} else if (grid[sy][x] == '[' || grid[sy][x] == ']') {
								continue;
								//keep going until space
							} else if (grid[sy][x] == '#') {
								//nomove
								break;
							} else {
							}
						}
					}
					break;
				default:
					break;
			}

			if (madeMove == 1) {
				for (int y = 0; y < leny; y++) {
					for (int x = 0; x < lenx; x++) {
						grid[y][x] = gridTmp[y][x];
					}
				}
			}
			continue;
		}
		long ans = 0;
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				out.print(grid[y][x]);
				if (grid[y][x] == '[') {
					ans += (100 * y) + x;
				}
			}
			out.println();
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}

	public static void copyToGridTmp() {
		gridTmp = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
		/*
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				gridTmp[y][x] = grid[y][x];
			}
		}
		*/
	}
	public static void makeMoveUp() {
		if (madeMove == 0) {madeMove = 1;}
		for (int it = 0; it <ve.size(); it++) { var pa = ve.get(it); gridTmp[pa.second][pa.first] = '.'; }
		for (int it = 0; it < ve.size(); it++) {
			var pa = ve.get(it);
			gridTmp[pa.second-1][pa.first] = grid[pa.second][pa.first];
		}
	}

	public static void makeMoveDown() {
		if (madeMove == 0) {madeMove = 1;}
		for (int it = 0; it < ve.size(); it++) { var pa = ve.get(it); gridTmp[pa.second][pa.first] = '.'; }
		for (int it = 0; it < ve.size(); it++) {
			var pa = ve.get(it);
			gridTmp[pa.second+1][pa.first] = grid[pa.second][pa.first];
		}
	}

	public static int level = 0;
	public static int checkBelow(int fir, int sec, int y) {
		level++;
		if (y > leny-1) {return -1;}

		if (grid[y+1][fir] == '.') {
			Tuple<Integer, Integer> tmp = new Tuple(fir, y);
			ve.add(tmp);
			//ok
		} else if (grid[y+1][fir] == '#') {	
			level--;
			return -1;	
		} else if (grid[y+1][fir] == '[') {
			int ret1 = checkBelow(fir, fir+1, y+1);
			if (ret1 == -1) {
				level--;
				return -1;
			} else {
				if (grid[y][fir] == '[') {
					Tuple<Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir+1, y);
					ve.add(tmp1);
				} else if (grid[y][fir] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir-1, y);
					ve.add(tmp1);
				}
			}

		} else if (grid[y+1][fir] == ']') {
			int ret1 = checkBelow(fir, fir-1, y+1);
			if (ret1 == -1) {
				level--;
				return -1;
			} else {
				if (grid[y][fir] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir-1, y);
					ve.add(tmp1);
				} else if (grid[y][fir] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir+1, y);
					ve.add(tmp1);
				}

			}
		}

		if (grid[y+1][sec] == '.') {
			Tuple <Integer, Integer> tmp = new Tuple(sec, y);
			ve.add(tmp);
			//ok
		} else if (grid[y+1][sec] == '#') {	
			level--;
			return -1;	
		} else if (grid[y+1][sec] == '[') {
			int ret1 = checkBelow(sec, sec+1, y+1);
			if (ret1 == -1) {
				level--;
				return -1;
			} else {
				if (grid[y][sec] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec+1, y);
					ve.add(tmp1);
				} else if (grid[y][sec] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec-1, y);
					ve.add(tmp1);
				}
			}

		} else if (grid[y+1][sec] == ']') {
			int ret1 = checkBelow(sec, sec-1, y+1);
			if (ret1 == -1) {
				level--;
				return -1;
			} else {
				if (grid[y][sec] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec-1, y);
					ve.add(tmp1);
				} else if (grid[y][sec] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec+1, y);
					ve.add(tmp1);
				}
			}
		}
		level--;
		return 0;
	}

	public static int checkAbove(int fir, int sec, int y) {
		if (y < 0) {return -1;}

		if (grid[y-1][fir] == '.') {
			Tuple <Integer, Integer> tmp = new Tuple(fir, y);
			ve.add(tmp);
			//ok
		} else if (grid[y-1][fir] == '#') {	
			return -1;	
		} else if (grid[y-1][fir] == '[') {
			int ret1 = checkAbove(fir, fir+1, y-1);
			if (ret1 == -1) {
				return -1;
			} else {
				if (grid[y][fir] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir+1, y);
					ve.add(tmp1);
				} else if (grid[y][fir] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir-1, y);
					ve.add(tmp1);
				}
			}

		} else if (grid[y-1][fir] == ']') {
			int ret1 = checkAbove(fir, fir-1, y-1);
			if (ret1 == -1) {
				return -1;
			} else {
				if (grid[y][fir] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir-1, y);
					ve.add(tmp1);
				} else if (grid[y][fir] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(fir, y);
					ve.add(tmp1);
					tmp1 = new Tuple(fir+1, y);
					ve.add(tmp1);
				}
			}
		}

		if (grid[y-1][sec] == '.') {
			Tuple <Integer, Integer> tmp = new Tuple(sec, y);
			ve.add(tmp);
			//ok
		} else if (grid[y-1][sec] == '#') {	
			return -1;	
		} else if (grid[y-1][sec] == '[') {
			int ret1 = checkAbove(sec, sec+1, y-1);
			if (ret1 == -1) {
				return -1;
			} else {
				if (grid[y][sec] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec-1, y);
					ve.add(tmp1);
				} else if (grid[y][sec] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec+1, y);
					ve.add(tmp1);
				}
			}

		} else if (grid[y-1][sec] == ']') {
			int ret1 = checkAbove(sec, sec-1, y-1);
			if (ret1 == -1) {
				return -1;
			} else {
				if (grid[y][sec] == ']') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec-1, y);
					ve.add(tmp1);
				} else if (grid[y][sec] == '[') {
					Tuple <Integer, Integer> tmp1 = new Tuple(sec, y);
					ve.add(tmp1);
					tmp1 = new Tuple(sec+1, y);
					ve.add(tmp1);
				}
			}
		}
		return 0;
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

