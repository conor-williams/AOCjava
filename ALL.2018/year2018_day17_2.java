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
class year2018_day17_2 {
	//	        public static int maxPath = 0;
	public static int tot = 0;
	public static int SZ = 9000;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[SZ][SZ];
	public static char gridOrig [][] = new char[SZ][SZ];
	//    public static int already [][] = new int[leny][lenx];
	public static int minx = 9999;
	public static int miny = 9999;
	public static int maxx = 0;
	public static int maxy = 0;
	public static Vector<Tuple<Integer, Integer>> save = new Vector<>();
	public static Map <Tuple<Integer, Integer>, Integer> mp = new HashMap<>();

	public static void main(String [] args) {
		out.println("		2018 Day17.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("x=(\\d+), y=(\\d+)..(\\d+)");
		Pattern p2 = Pattern.compile("y=(\\d+), x=(\\d+)..(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int yy = 0; yy < SZ; yy++) {
			for (int xx = 0; xx < SZ; xx++) {
				grid[yy][xx] = '.';
			}
		}
		grid[0][500] = '+';
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			if (m.find()) {
				int x1 = Integer.valueOf(m.group(1));
				int y1 = Integer.valueOf(m.group(2));
				int y2 = Integer.valueOf(m.group(3));
				if (y1 > y2) {int tmp; tmp = y1; y1 = y2; y2 = tmp;}
				if (x1 < minx) {minx = x1;} if (x1 > maxx) {maxx = x1;}
				if (y1 < miny) {miny = y1;} if (y2 > maxy) {maxy = y2;}
				int x = x1;
				for (int y = y1; y <= y2; y++) {
					grid[y][x] = '#';
				}
				continue;
			}
			Matcher m2 = p2.matcher(blah.get(i));
			if (m2.find()) {
				int y1 = Integer.valueOf(m2.group(1));
				int x1 = Integer.valueOf(m2.group(2));
				int x2 = Integer.valueOf(m2.group(3));
				if (x1 > x2) {int tmp; tmp = x1; x1 = x2; x2 = tmp;}
				//	if (y1 < miny) {miny = y1;} if (y1 > maxy) {maxy = y1;}
				if (x1 < minx) {minx = x1;} if (x2 > maxx) {maxx = x2;}
				int y = y1;
				for (int x = x1; x <= x2; x++) {
					grid[y][x] = '#';
				}
				continue;
			}
			out.println("ERR");
		}

		out.print("miny:"); out.println(miny);
		int origMiny = miny;
		miny = 0;
		int origMaxy = maxy;
		maxy += 5;
		minx -= 5;
		maxx += 5;



		for (int y = miny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx; x++) {
				gridOrig[y][x] = grid[y][x];
			}
		}
		int sx = 500;
		int sy = 0;
		//int bottom;
		grid[sy][sx] = '+';
		mp.put(new Tuple(sx, sy),1);
		save.add(new Tuple(sx, sy));
		drilldown(sx, sy+1);
		int i = save.size()-1;

		comeup(save.get(i).first, save.get(i).second, 500, 0, 1);

		//tidy up -- spurious R.L or L.R

		int Ftot = 0;
		for (int y = miny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx-2; x++) {
				if (grid[y][x] == 'R' && grid[y][x+1] == '.' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'L' && grid[y][x+1] == '.' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'L' && grid[y][x+1] == '.' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'R' && grid[y][x+1] == '.' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'F';
					Ftot++;
				}
			}
		}


		int susperr;
		do {
			susperr = 0;
			for (int x = minx; x<= maxx; x++) {
				for (int y = miny; y <= origMaxy; y++) {
					if (grid[y][x] == 'L' && grid[y+1][x] == '.') {
						susperr++;
						drilldown(x, y);
						comeup(save.get(save.size()-1).first, save.get(save.size()-1).second, x, y, 1);
					} else if (grid[y][x] == 'R' && grid[y+1][x] == '.') {
						susperr++;
						drilldown(x, y);
						comeup(save.get(save.size()-1).first, save.get(save.size()-1).second, x, y, 1);
					} else if (grid[y][x] == '|' && grid[y+1][x] == '.') {
						drilldown(x, y);
						comeup(save.get(save.size()-1).first, save.get(save.size()-1).second, x, y, 1);
						susperr++;
					}
				}
			}
		} while (susperr!= 0);

		Ftot = 0;
		for (int y = miny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx-2; x++) {
				if (grid[y][x] == 'R' && grid[y][x+1] == '.' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'L' && grid[y][x+1] == '.' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'L' && grid[y][x+1] == '.' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'F';
					Ftot++;
				} else if (grid[y][x] == 'R' && grid[y][x+1] == '.' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'F';
					Ftot++;
				}
			}
		}


		int hasherr =0;
		for (int x = minx; x<= maxx; x++) {
			for (int y = miny; y <= origMaxy; y++) {
				if (grid[y][x] == '#' && gridOrig[y][x] != '#') {
					hasherr++;
				}
				if (gridOrig[y][x] == '#' && grid[y][x] != '#') {
					hasherr++;
				}
			}
		}

		grid[sy][sx] = '+';
		/*
		   for (int x = minx; x <= maxx; x++) {
		   out.print(grid[origMaxy+1][x]);
		   }
		   out.println();
		   for (int x = minx; x <= maxx; x++) {
		   out.print(grid[origMaxy][x]);
		   }
		   */

		for (int y = origMiny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx-2; x++) {
				if (grid[y][x] == 'R' && grid[y][x+1] == '|' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'R';
				}
				if (grid[y][x] == 'L' && grid[y][x+1] == '|' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'R';
				}
				if (grid[y][x] == 'L' && grid[y][x+1] == '|' && grid[y][x+2] == 'R') {
					grid[y][x+1] = 'L';
				}
				if (grid[y][x] == 'R' && grid[y][x+1] == '|' && grid[y][x+2] == 'L') {
					grid[y][x+1] = 'L';
				}
			}
		}
		for (int y = origMiny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx-2; x++) {
				if (grid[y][x] == '.' && grid[y][x+1] == '|' && grid[y][x+2] == '.') {
					grid[y][x+1] = '.';
				} else if (grid[y][x] == '#' && grid[y][x+1] == '|' && grid[y][x+2] == '.') {
					grid[y][x+1] = '.';
				} else if (grid[y][x] == '.' && grid[y][x+1] == '|' && grid[y][x+2] == '#') {
					grid[y][x+1] = '.';
				}

				if (grid[y][x] == '.' && (grid[y][x+1] == 'R' || grid[y][x+1] == 'L' || grid[y][x+1] == 'F' || grid[y][x+1] == '|')) {
					grid[y][x+1] = '.';
				}
			}
		}
		for (int y = origMiny; y <= origMaxy; y++) {
			for (int x = maxx; x>= minx; x--) {
				if (grid[y][x] == '.' && (grid[y][x-1] == 'R' || grid[y][x-1] == 'L' || grid[y][x-1] == 'F' || grid[y][x-1] == '|')) {
					grid[y][x-1] = '.';
				}
			}
		}

		
//		for(int ii = 0; ii <= /*origMaxy*/130; ii++) {
//			for(int jj = /*186*/400; jj <= /*560*/544; jj++) {
//				if (grid[ii][jj] == '.') {
//					out.print(" ");
//				} else {
//					out.print(grid[ii][jj]);
//				}
//			}
//			out.println(ii);
//		}
	
		
		tot = 0;
		for (int y = origMiny; y <= origMaxy; y++) {
			for (int x = minx; x<= maxx; x++) {
				if (grid[y][x] == '|' || grid[y][x] == 'R' || grid[y][x] == 'L' || grid[y][x] == 'F') {
					tot++;
				}
			}
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int drilldown(int x1, int y1) {
		if (y1 >= maxy) {return 55;}
		int myret = 0; int bot = 0;

		for (int y = y1; y <= maxy; y++) {
			if (grid[y][x1] != '#') {
				grid[y][x1] = '|';
				if (y == maxy) {bot = maxy; return 55;}
				continue;
			} else {
				bot = y-1;
				break;
			}
		}

		if (mp.containsKey(new Tuple(x1, bot))) {
			return 11;
		}

		save.add(new Tuple(x1, bot));
		mp.put(new Tuple(x1, bot), 1);
		return myret;
	}

	public static int comeup(int frmx1, int frmy1, int tox1, int toy1, int drillit) {
		if (frmy1 == maxy || toy1 == maxy) {return 55;}

		int breakit = 0;
		int y;
		int itsx = frmx1+1;
		int end123 = 0;
		for (y = frmy1; y>toy1;y--) {
			if (y >= 95  && y <= 97) { out.print("y: "); out.println(y); }
			int x;
			for (x = /*itsx */frmx1+1; x <= maxx ;x++) {
				if (grid[y][x] == '#') { //found wall right
					if (y >= 95  && y <= 97) {out.print("found wall right break..."); out.print(x); out.print(" "); out.println(y);
					 	out.print(frmy1); out.print(" "); out.println(toy1-1);
					 	out.print(frmx1); out.print(" "); out.println(maxx);/* toy1--;*/}
					toy1--;
					end123 = x+1;
					break;
				} else {
					grid[y][x] = 'R';
					if (y >= 95  && y <= 97) {out.print("setting R1..."); out.print(x); out.print(" "); out.println(y); 
					 	out.print(frmy1); out.print(" "); out.println(toy1-1);
					 	out.print(frmx1); out.print(" "); out.println(maxx);}
					
					if ((y+1 <= frmy1 && grid[y+1][x] == '#' )|| (grid[y+1][x] == '.')) {
						if (grid[y+1][x+1] == '.') {
							if (grid[y+1][x] != '.') {
								if (y+1 >= 95  && y+1 <= 97) {out.println("setting R2..."); out.print(x); out.print(" "); out.println(y+1);}

								grid[y][x+1] = 'R';
								end123 = x+1;
								int ret2  = drilldown(x+1, y+1);
								if (ret2 == 55) {breakit = 1; break;}
								else if (ret2 == 11) {breakit = 1; break;}
								else {if (33 == comeup(save.get(save.size()-1).first, save.get(save.size()-1).second, save.get(save.size()-1).first, 1, 1)) {} }
								breakit = 1;
								break;
							} else {
								breakit = 1;
								break;
							}
						} else if (grid[y+1][x+1] == '|' && grid[y+1][x+2] == '.') {
							if (y+1 >= 95  && y+1 <= 97) {out.println("stopping...");}
							breakit = 1; break;
						}

					} else {
						if (y+1 >= 95  && y+1 <= 97) {out.println("else...");}
					}
				}
			}

			for (x = frmx1-1; x >= minx ; x--) {
				if (grid[y][x] == '#') { //found wall left
					itsx = x-1;
					toy1--;
					break;
				} else {
					grid[y][x] = 'L';
					if ((y+1 <= frmy1 && grid[y+1][x] == '#') || (grid[y+1][x] == '.')) {
						if (grid[y+1][x-1] == '.') {
							if (grid[y+1][x] != '.') {
								grid[y][x-1] = 'L';
								itsx = x-1;
								int ret1  = drilldown(x-1, y+1);
								if (ret1 == 55) {breakit = 1; break;}
								else if (ret1 == 11) {breakit=1; break;}
								else {if (33 == comeup(save.get(save.size()-1).first, save.get(save.size()-1).second, save.get(save.size()-1).first, 1, 1)) {}}
								breakit = 1;
								break;
							} else {
								breakit = 1;
								break;
							}
						} else if (grid[y+1][x-1] == '|' && grid[y+1][x-2] == '.') {
							breakit = 1; break;
						}

					}
				}
			}
			if (breakit == 1) { 
				for (int i = itsx; i <=end123 ; i++) {
					if (grid[y][i] == 'R' || grid[y][i] == 'L') {
						if (grid[y-1][i] != 'R' && grid[y-1][i] != 'L') {
							grid[y][i] = 'R';
						} 
					}
				}
				return 33;
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

