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
class year2023_day24_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	static class line {
		double Ix;
		double Iy;
		double Iz;
		double Isx;
		double Isy;
		double Isz;
		double slopexy;
		double cxy;
	};
	static int MAX_LINES = 320;
	static line l[] = new line[MAX_LINES];
	static int tot = 0;
	static int ans123 = 0;
	static class Nxyz {long x; long y; long z;};

	public static void main(String [] args) {
		out.println("		2023 Day24.2");
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
		for (int ii = 0; ii < MAX_LINES; ii++) {
			l[ii] = new line();
		}
		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(-?\\d+), (-?\\d+), (-?\\d+) @ (-?\\d+), (-?\\d+), (-?\\d+)");
		//308205470708820, 82023714100543, 475164418926765 @ 42, 274, -194
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
			l[i].Ix = (double)Long.parseLong(m.group(1));
			l[i].Iy = (double)Long.parseLong(m.group(2));
			l[i].Iz = (double)Long.parseLong(m.group(3));
			l[i].Isx = (double)Long.parseLong(m.group(4));
			l[i].Isy = (double)Long.parseLong(m.group(5));
			l[i].Isz = (double)Long.parseLong(m.group(6));
			l[i].slopexy = (double)((double)l[i].Isy/(double)l[i].Isx);
			l[i].cxy = (double)l[i].Iy - (double)((double)l[i].slopexy*(double)l[i].Ix);
		}

		int nums[] = new int[(5*leny)];
		for (int i = 0; i < (5*leny); i++) {
			nums[i] = i;
		}

		Nxyz next[][] = new Nxyz[(5*leny)][(5*leny)+1];
		for (int yy = 0; yy < 5*leny; yy++) {
			for (int xx = 0; xx < (5*leny)+1; xx++) {
				next[yy][xx] = new Nxyz();
			}
		}
		for (int i = 0; i < leny; i++) {
			for (int j = 0; j < (5*leny)+1; j++) {
				next[i][j].x = (long)(l[i].Ix + j*l[i].Isx);
				next[i][j].y = (long)(l[i].Iy + j*l[i].Isy);
				next[i][j].z = (long)(l[i].Iz + j*l[i].Isz);
			}
		}
		for (int i = leny; i < (5*leny); i++) {
			for (int j = 0; j < (5*leny)+1; j++) {
				next[i][j].x = 0;
				next[i][j].y = 0;
				next[i][j].z = 0;
			}
		}

		int potX[][] = new int[5][200];
		int potXpos[] = new int[5];
		int lineX = 0;
		int potY[][] = new int[5][200];
		int potYpos[] = new int[5];
		int lineY = 0;
		int potZ[] = new int[200];
		int potZpos = 0;
		for (int i = 0; i < leny-1; i++) {
			for (int j = i+1; j < leny; j++) {
				if (l[i].Isx == l[j].Isx && l[i].Isy == l[j].Isy) {
					for (int z = -1000; z < 1000; z++) {
						if (Math.abs(z) > 100) {
							if (Math.abs(z-l[i].Isx) != 0) {
								long xmove = Math.abs((long)l[i].Ix - (long) l[j].Ix) % (long)Math.abs(z-l[i].Isx);
								if (xmove == 0) {potX[lineX][potXpos[lineX]] = z; potXpos[lineX]++;}
							}
						}
						if (Math.abs(z) > 100) {
							if (Math.abs(z-l[i].Isy) != 0) {
								long ymove = Math.abs((long)l[i].Iy - (long) l[j].Iy) % (long)Math.abs(z-l[i].Isy);
								if (ymove == 0) { potY[lineY][potYpos[lineY]] = z; potYpos[lineY]++; }
							}
						}
					}

					lineX++;
					lineY++;
				}

			}
		}

		int outY[] = new int[100];
		int outYPos =0;
		for (int i = 0; i < potYpos[0]; i++) {
			for (int j = 0; j < potYpos[1]; j++) {
				if (potY[0][i] == potY[1][j]) {
					int found = 0;
					for (int k = 0; k < outYPos; k++) {
						if (outY[k] ==  potY[0][i]) {found = 1; break;}
					}
					if (found == 0) {
						outY[outYPos] = potY[0][i];
						outYPos++;
					}
				}
			}
		}
		int outX[] = new int[100];
		int outXPos =0;
		for (int i = 0; i < potXpos[0]; i++) {
			for (int j = 0; j < potXpos[1]; j++) {
				if (potX[0][i] == potX[1][j]) {
					int found = 0;
					for (int k = 0; k < outXPos; k++) {
						if (outX[k] ==  potX[0][i]) {found = 1; break;}
					}
					if (found == 0) {
						outX[outXPos] = potX[0][i];
						outXPos++;
					}
				}
			}
		}
		int  sameY[] = new int[100];
		int sameYPos =0;
		int  sameX[] = new int[100];
		int sameXPos =0;

		int interX[][] = new int[400][400];
		int interXpos[] = new int[400];
		int interXposLine = 0;
		int interY[][] = new int[400][400];
		int interYpos[] = new int[400];
		int interYposLine = 0;
		int interZ[][] = new int[400][400];
		int interZpos[] = new int[400];
		int interZposLine = 0;
		for (int i = 0; i < leny-1; i++) {
			for (int j = i+1; j < leny; j++) {
				if (l[i].Isx ==  l[j].Isx) {
					for (int z = -1000; z < 1000; z++) {
						if (z-l[i].Isx != 0) {
							long xmove = ((long)l[i].Ix - (long) l[j].Ix) % (long)Math.abs(z-l[i].Isx);
							if (xmove == 0) {
								int found = 0;
								for (int q=0; q<interXpos[interXposLine]; q++) {
									if (interX[interXposLine][q] == z) {
										found =  1; break;
									}
								}
								if (found == 0) {
									interX[interXposLine][interXpos[interXposLine]] = z; 
									interXpos[interXposLine]++;
								}
							}
						}

					}
					interXposLine++;
				}
				if (l[i].Isy ==  l[j].Isy) {
					for (int y = -1000; y < 1000; y++) {
						if (y-l[i].Isy != 0) {
							long ymove = ((long)l[i].Iy - (long) l[j].Iy) % (long)Math.abs(y-l[i].Isy);
							if (ymove == 0) {
								int found = 0;
								for (int q=0; q<interYpos[interYposLine]; q++) {
									if (interY[interYposLine][q] == y) {
										found =  1; break;
									}
								}
								if (found == 0) {
									interY[interYposLine][interYpos[interYposLine]] = y; 
									interYpos[interYposLine]++;
								}
							}
						}

					}
					interYposLine++;
				}
				if (l[i].Isz ==  l[j].Isz) {
					for (int z = -1000; z < 1000; z++) {
						if (z-l[i].Isz != 0) {
							long zmove = ((long)l[i].Iz - (long) l[j].Iz) % (long)Math.abs(z-l[i].Isz);
							if (zmove == 0) {
								int found = 0;
								for (int q=0; q<interZpos[interZposLine]; q++) {
									if (interZ[interZposLine][q] == z) {
										found =  1; break;
									}
								}
								if (found == 0) {
									interZ[interZposLine][interZpos[interZposLine]] = z; 
									interZpos[interZposLine]++;
								}
							}
						}

					}
					interZposLine++;
				}
			}
		}

		int outX2[] = new int[100];
		int outX2Pos =0;

		/*
		   int counter = 0;
		   for (int i = 0; i < interXposLine; i++) {
		   for (int j = 0; j < interXpos[i]; j++) {
		   if (interX[i][j] == 214) {
		   counter++;
		   }
		   }
		   }
		   int counterY = 0;
		   for (int i = 0; i < interYposLine; i++) {
		   for (int j = 0; j < interYpos[i]; j++) {
		   if (interY[i][j] == -168) {
		   counterY++;
		   }
		   }
		   }
		   int counterZ = 0;
		   for (int i = 0; i < interZposLine; i++) {
		   for (int j = 0; j < interZpos[i]; j++) {
		   if (interZ[i][j] == 249) {
		   counterZ++;
		   }
		   }
		   }
		   */
		{
			int l1 = 0;
			for (int l2 = 1; l2 < interZposLine; l2++) {
				for (int z1=0; z1 < interZpos[l1]; z1++) {
					if (interZ[l1][z1] == 0) {continue;}
					int found = 0;
					for (int z2=0; z2 < interZpos[l2]; z2++) {
						if (interZ[l1][z1] == interZ[l2][z2]) {
							found = 1; break;
						}
					}
					if (found == 0) {
						interZ[l1][z1] = 0;
						break;
					}
				}	
			}

		}
		int cZ = 0;
		for (int i = 0; i < interZpos[0]; i++) {
			if (interZ[0][i] != 0) {
				cZ = interZ[0][i];
			}
		}
		{
			int l1 = 0;
			for (int l2 = 1; l2 < interXposLine; l2++) {
				for (int x1=0; x1 < interXpos[l1]; x1++) {
					if (interX[l1][x1] == 0) {continue;}
					int found = 0;
					for (int x2=0; x2 < interXpos[l2]; x2++) {
						if (interX[l1][x1] == interX[l2][x2]) {
							found = 1; break;
						}
					}
					if (found == 0) {
						interX[l1][x1] = 0;
						break;
					}
				}	
			}

		}

		int cX = 0;
		for (int i = 0; i < interXpos[0]; i++) {
			if (interX[0][i] != 0) {
				cX = interX[0][i];
			}
		}

		{
			int l1 = 0;
			for (int l2 = 1; l2 < interYposLine; l2++) {
				for (int x1=0; x1 < interYpos[l1]; x1++) {
					if (interY[l1][x1] == 0) {continue;}
					int found = 0;
					for (int x2=0; x2 < interYpos[l2]; x2++) {
						if (interY[l1][x1] == interY[l2][x2]) {
							found = 1; break;
						}
					}
					if (found == 0) {
						interY[l1][x1] = 0;
						break;
					}
				}	
			}

		}
		int cY = 0;
		for (int i = 0; i < interYpos[0]; i++) {
			if (interY[0][i] != 0) {
				cY = interY[0][i];
			}
		}
		int ind1 = -1;
		int ind2 = -1;
		int ind3 = -1;
		long Xt1Mt2 = 0L;
		long Xt1Mt3 = 0L;
		long Xt2Pt3 = 0L;
		int b = 0;
end:
		for (int i = 0; i < leny-1; i++) {
			for (int j = i+1; j < leny; j++) {
				for (int k = j+1; k < leny; k++) {
					if (l[i].Isx ==  l[j].Isx && l[i].Isx == l[k].Isx) {
						ind1 = i; ind2 = j; ind3 = k; break end;
					}
				}
			}
		}
		Xt1Mt2 = ((long)l[ind1].Ix - (long) l[ind2].Ix) / (long)(cX-l[ind1].Isx);
		Xt1Mt3 = ((long)l[ind1].Ix - (long) l[ind3].Ix) / (long)(cX-l[ind1].Isx);
		Xt2Pt3 = ((long)l[ind2].Ix - (long) l[ind3].Ix) / (long)(cX-l[ind2].Isx);
		long Xt3 = (long) Xt1Mt2 - Xt1Mt3 + Xt2Pt3 / 2 ;

		long stX = (long)(l[ind3].Ix + Xt3*l[ind3].Isx - Xt3*cX);

		/////////////////////////////////////////////////////////////
		double q2 = l[1].Isx - cX;
		double q1 = l[1].Isy - cY;
		double q4 = l[2].Isx - cX;
		double q3 = l[2].Isy - cY;
		double q5 = l[2].Ix - l[1].Ix;
		double B = (double)(q4*q1)/(double)(q2*q3);
		double B2 = (double)(q5*q1)/(double)q2;

		double Yst = (double)(l[1].Iy - B*l[2].Iy +B2) / (double) (1-B);


		double Xst = (double)((Yst - l[1].Iy)*(l[1].Isx-cX))/(double)(l[1].Isy - cY) + l[1].Ix;
		double Zst = (double)((Yst - l[1].Iy)*(l[1].Isz-cZ))/(double)(l[1].Isy - cY) + l[1].Iz;


		long ans222 = (long)Xst+(long)Yst+(long)Zst;

		//		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(ans222);
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

