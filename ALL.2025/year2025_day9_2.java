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
class year2025_day9_2 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	public static HashMap <Integer, HashMap<Integer, Character>> gr = new HashMap<>();
	//public static HashMap <Integer, HashMap<Integer, Boolean>> already = new HashMap<>();
	public static int minx;
	public static int miny;
	public static int maxx;
	public static int maxy;
	public static int depth = 0;


	public static Vector<Tuple <Integer, Integer>> already = new Vector<>();
	public static void main(String [] args) {
		out.println("		2025 Day9.2");
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
		/*
		   grid = new char[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+),(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Vector <Tuple<Integer, Integer>> points = new Vector<>();
		minx = 99999999;
		miny = 99999999;
		maxx = 0;
		maxy = 0;
		for (int i = 0, nn = blah.size(); i < nn; i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int xxx1 = Integer.valueOf(m.group(1));
			int yyy1 = Integer.valueOf(m.group(2));
			Tuple<Integer, Integer> po = new Tuple(xxx1, yyy1);

			//gr.computeIfAbsent(yyy1, k -> new HashMap<>()).put(xxx1, 'R');
			points.add(po);
		}
		int en = points.size();
		long max = 0;
		Vector <QuadTuple <Tuple<Integer, Integer>, Tuple<Integer, Integer>, Long, Tuple<Integer, Integer>>> allRects = new Vector<>();
		for (int ii = 0; ii < en-1; ii++) {
			int xx1 = points.get(ii).first;
			int yy1 = points.get(ii).second;
			if (xx1 < minx) {minx = xx1;}
			if (yy1 < miny) {miny = yy1;}
			if (xx1 > maxx) {maxx = xx1;}
			if (yy1 > maxy) {maxy = yy1;}
			for (int jj = ii+1; jj < en; jj++) {
				int xx2 = points.get(jj).first;
				int yy2 = points.get(jj).second;
				if (xx2 < minx) {minx = xx2;}
				if (yy2 < miny) {miny = yy2;}
				if (xx2 > maxx) {maxx = xx2;}
				if (yy2 > maxy) {maxy = yy2;}

				/*
				   if (xx2 == xx1) {
				   if (yy1 > yy2) {
				   for (int yy = yy2; yy <= yy1; yy++) {
				//grid[yy][xx2] = 'R';
				gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx2, 'R');
				   }
				   } else {
				   for (int yy = yy1; yy <= yy2; yy++) {
				//grid[yy][xx2] = 'R';
				gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx2, 'R');
				   }
				   }

				   }
				   if (yy2 == yy1) {
				   if (xx1 > xx2) {
				   for (int xx = xx2; xx <= xx1; xx++) {
				   gr.computeIfAbsent(yy2, k -> new HashMap<>()).put(xx, 'R');
				   }
				   } else{
				   for (int xx = xx1; xx <= xx2; xx++) {
				   gr.computeIfAbsent(yy2, k -> new HashMap<>()).put(xx, 'R');
				   }
				   }
				   }
				   */

				long size = (long)(Math.abs(xx2-xx1)+1) * (long)(Math.abs(yy2-yy1)+1);
				allRects.add(new QuadTuple(points.get(ii), points.get(jj), size, new Tuple(ii, jj)));
				if (size > max) {max = size;}

			}
		}
		Collections.sort(allRects, Comparator.comparing(t -> t.third));
		//compress
		Vector <Tuple<Integer, Integer>> pointsCompressed = new Vector<>(points);
		out.println("before y compression...");
aft99:
		for (int yy = maxy; yy >= miny; yy--) {
			for (int ii = 0; ii < en; ii++) {
				int yy1 = pointsCompressed.get(ii).second;
				if (yy1 == yy) {
					continue aft99;
				}
			}
			//row clear
			for (int ii = 0; ii < en; ii++) {
				int yy1 = pointsCompressed.get(ii).second;
				if (yy1 > yy) {
					int xx1 = pointsCompressed.get(ii).first;
					yy1--;
					pointsCompressed.set(ii, new Tuple(xx1, yy1));
				}
			}
			//out.println("cleared row: " + yy);
			maxy--;
		}
		out.println("before x compression...");
aft100:
		for (int xx = maxx; xx >= minx; xx--) {
			for (int ii = 0; ii < en; ii++) {
				int xx1 = pointsCompressed.get(ii).first;
				if (xx1 == xx) {
					continue aft100;
				}
			}
			//col clear
			for (int ii = 0; ii < en; ii++) {
				int xx1 = pointsCompressed.get(ii).first;
				if (xx1 > xx) {
					int yy1 = pointsCompressed.get(ii).second;
					xx1--;
					pointsCompressed.set(ii, new Tuple(xx1, yy1));
				}
			}
			//out.println("cleared col: " + xx);
			maxx--;
		}
		out.println("after x compression...");

		for (int i = 0, nn = blah.size(); i < nn; i++) {
			int xxx1 = pointsCompressed.get(i).first;
			int yyy1 = pointsCompressed.get(i).second;
			gr.computeIfAbsent(yyy1, k -> new HashMap<>()).put(xxx1, 'R');
		}
		for (int ii = 0; ii < en-1; ii++) {
			int xx1 = pointsCompressed.get(ii).first;
			int yy1 = pointsCompressed.get(ii).second;
			for (int jj = ii+1; jj < en; jj++) {
				int xx2 = pointsCompressed.get(jj).first;
				int yy2 = pointsCompressed.get(jj).second;
				if (xx2 == xx1) {
					if (yy1 > yy2) {
						for (int yy = yy2; yy <= yy1; yy++) {
							//grid[yy][xx2] = 'R';
							gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx2, 'R');
						}
					} else {
						for (int yy = yy1; yy <= yy2; yy++) {
							//grid[yy][xx2] = 'R';
							gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx2, 'R');
						}
					}

				}
				if (yy2 == yy1) {
					if (xx1 > xx2) {
						for (int xx = xx2; xx <= xx1; xx++) {
							gr.computeIfAbsent(yy2, k -> new HashMap<>()).put(xx, 'R');
						}
					} else{
						for (int xx = xx1; xx <= xx2; xx++) {
							gr.computeIfAbsent(yy2, k -> new HashMap<>()).put(xx, 'R');
						}
					}
				}
			}
		}
		out.println("after line joins x&y compression...");
		/*
		   for (int yy = miny-2; yy < maxy+5; yy++) {
		   for (int xx = minx-2; xx < maxx+5; xx++) {
		   if (gr.get(yy) == null) {
		   out.print("E");
		   } else if (gr.get(yy).get(xx) == null) {
		   out.print("n");
		   } else {
		   out.print(gr.get(yy).get(xx));
		   }
		   }
		   out.println();
		   }
		   */


		out.println("biggest: **" + allRects.get(allRects.size()-1).third);

		long ans = 0;
		HashMap <Integer, HashMap<Integer, Character>> grOrig = deepCopy(gr);
		//for (int which = allRects.size()-1; which >= 0; which--) 

		int sx = 0;
		int sy = 0;
		int yyy = maxy-3;
aft1:
		for (int xxx = minx-4; xxx < maxx +3; xxx++) {
			if (gr.get(yyy) != null) {
				if (gr.get(yyy).get(xxx) != null) {
					if (gr.get(yyy).get(xxx) == 'R') {
						sx = xxx; sy = yyy;
						break aft1;
					}
				}
			}
		}

		out.println("marking edge..." + sx + " sy: " + sy);
		gr.computeIfAbsent(miny-1, k -> new HashMap<>()).put(0, '.');
		gr.computeIfAbsent(maxy+1, k -> new HashMap<>()).put(0, '.');
		markEdge2(sx, sy, 0);
		out.println("after marking edge...");
		/*
		   for (int yy = miny-2; yy < maxy+7; yy++) {
		   for (int xx = minx-2; xx < maxx+8; xx++) {
		   if (gr.get(yy) == null) {
		   out.print("E");
		   } else if (gr.get(yy).get(xx) == null) {
		   out.print("n");
		   } else {
		   out.print(gr.get(yy).get(xx));
		   }
		   }
		   out.println();
		   }
		   Scanner scanner = new Scanner(System.in); scanner.nextLine();
		   */
		//floodFillOutside(minx-1, miny-1);

		int size1 = gr.size();
		for (int which = allRects.size()-1; which >= 0; which--) {
			var tre = allRects.get(which);
			//if (tre.third != 1578115935) {continue;}
			var tu4 = allRects.get(which).fourth;
			int x1 = pointsCompressed.get(tu4.first).first;
			int y1 = pointsCompressed.get(tu4.first).second;
			int x2 = pointsCompressed.get(tu4.second).first;
			int y2 = pointsCompressed.get(tu4.second).second;
			/*
			   int x1 = tre.first.first;
			   int y1 = tre.first.second;
			   int x2 = tre.second.first;
			   int y2 = tre.second.second;
			   */


			out.println("trying + " + x1 + " " + y1 + " :: " + x2 + " " + y2);
			int rightX = 0;
			int topY = 0;
			int leftX = 0;
			int btmY = 0;
			if (x1 > x2) {rightX = x1; leftX = x2;} else {rightX = x2; leftX = x1;}

			if (y1 > y2) {btmY = y1; topY = y2;} else {btmY = y2; topY = y1;}

			out.println("leftX: " + leftX + " rightX: " + rightX + " topY: " + topY + " btmY: " + btmY);

			boolean bad = false;
			if (bad == false) {
				//int xx = leftX+1;
after1:
				for (int xx = leftX; xx <= rightX; xx++) {
					for (int yy = topY; yy <= btmY; yy++) {
						if (gr.get(yy) != null) {
							if (gr.get(yy).get(xx) != null) {
								if (gr.get(yy).get(xx) == 'F') {
									bad = true;
									break after1;
								}
							}
						}
					}
				}
			}

			if (bad == true) {continue;}
			ans = tre.third;
			break;

		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	public static <K1, K2, V> HashMap<K1, HashMap<K2, V>> deepCopy(HashMap<K1, HashMap<K2, V>> original) {
		if (original == null) {
			return null; // Handle null input
		}

		HashMap<K1, HashMap<K2, V>> copy = new HashMap<>();

		for (Map.Entry<K1, HashMap<K2, V>> outerEntry : original.entrySet()) {
			// Create a new inner map for each outer key
			HashMap<K2, V> innerCopy = new HashMap<>();
			if (outerEntry.getValue() != null) {
				innerCopy.putAll(outerEntry.getValue()); // Shallow copy of inner map values
			}
			copy.put(outerEntry.getKey(), innerCopy);
		}

		return copy;
	}

	/*
	   public static void traceR(int x, int y) {
	   if (gr.get(y) != null && gr.get(y).get(x) != null && gr.get(y).get(x) == 'R'
	   && 
	   ((gr.get(y).get(x-1) != null && gr.get(y).get(x-1) == 'F') ||
	   (gr.get(y-1).get(x) != null && gr.get(y-1).get(x) == 'F') ||
	   (gr.get(y).get(x+1) != null && gr.get(y).get(x+1) == 'F') ||
	   (gr.get(y+1).get(x) != null && gr.get(y+1).get(x) == 'F')) {

	   if (gr.get(y) != null && gr.get(y).get(x) != null && gr.get(y).get(x)=='F') {
	   boolean ok = false;
	   if (gr.get(y).get(x-1) != null) {
	   if (gr.get(y).get(x-1) == 'F') {
	   ok = true;
	   }
	   }
	   if (gr.get(y).get(x+1) != null) {
	   if (gr.get(y).get(x+1) == 'R') {
	   if (gr.get(y+1).get(x-1)
	   if (gr.get(y-1).get(x-1)
	   gr.computeIfAbsent(y, k -> new HashMap<>()).put(x, 'F');
	   }
	   if (x == xstart 

	   }
	   */
	public static void markEdge2(int x, int y, int dir) {
		//out.println("markEdge2: " + x + " x<-->y " + y);
		/*
		   for (int yy = miny-2; yy < maxy+3; yy++) {
		   for (int xx = minx-2; xx < maxx+3; xx++) {
		   if (x == xx && y == yy) {
		   out.print("X");
		   } else if (gr.get(yy) == null) {
		   out.print("E");
		   } else if (gr.get(yy).get(xx) == null) {
		   out.print("n");
		   } else {
		   out.print(gr.get(yy).get(xx));
		   }
		   }
		   out.println();
		   }
		   */
		//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		if (gr.get(y) != null && gr.get(y).get(x) != null) {
			var tu = new Tuple(x, y);
			if (already.contains(tu)) {
				return;
			} else {
				already.add(tu);
			}

			if (dir == 0) {
				gr.computeIfAbsent(y, k -> new HashMap<>()).put(x-1, 'F');
			} else if (dir == 1) {
				/*
				   if (gr.get(y) != null && (gr.get(y-1).get(x) != null || (gr.get(y-1).get(x) != 'F'))) {
				   out.println("ErrF2");
				   }
				   */
				gr.computeIfAbsent(y-1, k -> new HashMap<>()).put(x, 'F');
			} else if (dir == 2) {
				gr.computeIfAbsent(y, k -> new HashMap<>()).put(x+1, 'F');
			} else if (dir == 3) {
				gr.computeIfAbsent(y+1, k -> new HashMap<>()).put(x, 'F');
			}
		}
		//Left 90
		boolean goneLeft90 = false;
		if (dir == 0 && (gr.get(y-1) == null || (gr.get(y-1).get(x-1) != null && gr.get(y-1).get(x-1) == 'R'))) {
			dir = 3;
			x--;
			y--;
			goneLeft90 = true;
		} else if (dir == 1 && (gr.get(y-1) == null  || (gr.get(y-1).get(x+1) != null && gr.get(y-1).get(x+1) == 'R'))) {
			dir = 0;
			x++;
			y--;
			goneLeft90 = true;
		} else if (dir == 2 && (gr.get(y+1) == null || (gr.get(y+1).get(x+1) != null && gr.get(y+1).get(x+1) == 'R'))) {
			dir = 1;
			x++;
			y++;
			goneLeft90 = true;
		} else if (dir == 3 && (gr.get(y+1) == null || (gr.get(y+1).get(x-1) != null && gr.get(y+1).get(x-1) == 'R'))) {
			dir = 2;
			x--;
			y++;
			goneLeft90 = true;
		}

		if (goneLeft90) {markEdge2(x, y, dir);return;}
		// Straight
		boolean goneStraight = false;
		if (dir == 0 && (gr.get(y-1) == null || (gr.get(y-1).get(x) != null && gr.get(y-1).get(x) == 'R'))) {
			y--;
			goneStraight = true;
		} else if (dir == 1 && (gr.get(y) == null || (gr.get(y).get(x+1) != null && gr.get(y).get(x+1) == 'R'))) {
			x++;
			goneStraight = true;
		} else if (dir == 2 && (gr.get(y+1) == null || (gr.get(y+1).get(x) != null && gr.get(y+1).get(x) == 'R'))) {
			y++;
			goneStraight = true;
		} else if (dir == 3 && (gr.get(y) == null || (gr.get(y).get(x-1) != null && gr.get(y).get(x-1) == 'R'))) {
			x--;
			goneStraight = true;
		} 
		if (goneStraight) {markEdge2(x, y, dir); return;}

		boolean goneRight270 = false;
		if (dir == 0 && gr.get(y).get(x+1) != null && gr.get(y).get(x+1) == 'R') {
			dir = 1;
			goneRight270 = true;
		} else if (dir == 1 && gr.get(y+1).get(x) != null && gr.get(y+1).get(x) == 'R') {
			dir = 2;
			goneRight270 = true;
		} else if (dir == 2 && gr.get(y).get(x-1) != null && gr.get(y).get(x-1) == 'R') {
			dir = 3;
			goneRight270 = true;
		} else if (dir == 3 && gr.get(y-1).get(x) != null && gr.get(y-1).get(x) == 'R') {
			dir = 0;
			goneRight270 = true;
		}
		if (goneRight270) {already.remove(already.size()-1); markEdge2(x, y, dir); return;}

		for (int yy = y-10; yy < y+10; yy++) {
			for (int xx = x-10; xx < x+10; xx++) {
				if (gr.get(yy) == null) {
					out.print("E");
				} else if (gr.get(yy).get(xx) == null) {
					out.print("n");
				} else {
					out.print(gr.get(yy).get(xx));
				}
			}
			out.println();
		}
		out.println("ERR somewhhere in markEdge2....dir: " + dir);
		/*
		   out.println("minx: " + minx);
		   out.println("maxx: " + maxx);
		   out.println("miny: " + miny);
		   out.println("maxy: " + maxy);
		   */





	}

	public static void markEdge(int x, int y) {
		if (x < minx-1 || y < miny-1 || x > maxx+1 || y > maxy+1) {
			return;
		}
after10:
		for (int xx = minx; xx <= maxx; xx++) {
			for (int yy = miny-1; yy <= maxy; yy++) {
				if (gr.get(yy) != null) {
					if (gr.get(yy).get(xx) != null) {
						if (gr.get(yy).get(xx) == 'R') {
							gr.computeIfAbsent(yy-1, k -> new HashMap<>()).put(x, 'F');
							continue after10;
						}
					}
				}
			}
		}
		out.println("top...");
after11:
		for (int xx = minx; xx <= maxx; xx++) {
			for (int yy = maxy+1; yy >= miny; yy--) {
				if (gr.get(yy) != null) {
					if (gr.get(yy).get(xx) != null) {
						if (gr.get(yy).get(xx) == 'R') {
							gr.computeIfAbsent(yy+1, k -> new HashMap<>()).put(x, 'F');
							continue after11;
						}
					}
				}
			}
		}
		out.println("left");
after12:
		for (int yy = miny-1; yy <= maxy; yy++) {
			for (int xx = minx-1; xx <= maxx; xx++) {
				if (gr.get(yy) != null) {
					if (gr.get(yy).get(xx) != null) {
						if (gr.get(yy).get(xx) == 'R') {
							gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx-1, 'F');
							continue after12;
						}
					}
				}
			}
		}
		out.println("right");
after13:
		for (int yy = miny-1; yy <= maxy; yy++) {
			for (int xx = maxx+1; xx >= minx; xx--) {
				if (gr.get(yy) != null) {
					if (gr.get(yy).get(xx) != null) {
						if (gr.get(yy).get(xx) == 'R') {
							gr.computeIfAbsent(yy, k -> new HashMap<>()).put(xx+1, 'F');
							continue after13;
						}
					}
				}
			}
		}


		//traceF(x, y, x, y);
	}


	public static boolean floodFillOutside(int x, int y) {
		if (x < minx-1 || y < miny-1 || x > maxx+1 || y > maxy+1) {
			//out.println("out of range... return false..." + " out: " + x + " " + y);
			//out.println("minx: " + minx + " miny:" + miny + " maxx:" + maxx + " maxy:" + maxy);
			return true;
		}

		if (gr.get(y) == null || gr.get(y).get(x) == null) {
			gr.computeIfAbsent(y, k -> new HashMap<>()).put(x, 'F');
		} else if (gr.get(y).get(x) == 'R') {
			return true;
		} else if (gr.get(y).get(x) == 'F') {
			return true;
		}

		if (floodFillOutside(x+1, y) == false) {return false;}
		if (floodFillOutside(x-1, y) == false) {return false;}
		if (floodFillOutside(x, y+1) == false) {return false;}
		if (floodFillOutside(x, y-1) == false) {return false;}
		return true;
	}

	public static boolean floodFill(int x, int y) {
		if (x < minx-1 || y < miny-1 || x > maxx+1 || y > maxy+1) {
			out.println("out of range... return false..." + " out: " + x + " " + y);
			out.println("minx: " + minx + " miny:" + miny + " maxx:" + maxx + " maxy:" + maxy);
			return false;
		}

		if (gr.get(y) == null || gr.get(y).get(x) == null) {
			gr.computeIfAbsent(y, k -> new HashMap<>()).put(x, 'F');
		} else if (gr.get(y).get(x) == 'R') {
			return true;
		} else if (gr.get(y).get(x) == 'F') {
			return true;
		}

		if (floodFill(x+1, y) == false) {return false;}
		if (floodFill(x-1, y) == false) {return false;}
		if (floodFill(x, y+1) == false) {return false;}
		if (floodFill(x, y-1) == false) {return false;}
		return true;
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

