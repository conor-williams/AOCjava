import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class year2022_day25 {
	//public static String blah[] = {"34818266939311"};
	//public static Scanner ui = new Scanner(System.in);
	//base 5 14030430302314024221
	public static void main(String [] args) {
		System.out.println("		2022 Day25.1");
		Vector<String> blah = new Vector<>();
		BigInteger totOfNums = BigInteger.valueOf((long)0);
		//System.out.println(args[0]);
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		for (int i = 0; i < blah.size(); i++) {
			BigInteger thisNum = BigInteger.valueOf((long)0);
			for (int z = blah.get(i).length() -1; z >= 0; z--) {
				//System.out.println(blah[i].charAt(z));
				long po = ((long)(blah.get(i).length() - 1 - z));
				BigInteger sz = BigInteger.valueOf((long)(Math.pow(5, po))); 
				//System.out.println("pow: " + sz);
				BigInteger num = BigInteger.valueOf(0);
				switch (blah.get(i).charAt(z)) {
					case ('2') :
						//System.out.println("two");
						num = BigInteger.valueOf(2);
						break;
					case ('1') :
						//System.out.println("one");
						num = BigInteger.valueOf(1);
						break;
					case ('0') :
						//System.out.println("zero");
						num = BigInteger.valueOf(0);
						break;
					case ('-') :
						//System.out.println("minus");
						num = BigInteger.valueOf(-1);
						break;
					case ('=') :
						//System.out.println("equal");
						//num = new BigInteger(BigInteger.valueOf(-2));
						num = BigInteger.valueOf(-2);
						break;
					default:
						//System.out.println("here1 should not be here\n");
						break;
				}
				//System.out.println(sz.multiply(num));
				thisNum = thisNum.add(sz.multiply(num));
				//System.out.println(thisNum);
			}
			//System.out.println("+ " + thisNum);
			totOfNums = totOfNums.add(thisNum);
		}
		///System.out.println("** totOfNums: " + totOfNums);
		String value = totOfNums.toString(5);
		//String zz = Integer.toString(Integer.parseInt(String.valueOf(i), 10), 5);

		//String value = blah2.toString(5);

		char [] oldVal = value.toCharArray();
		char [] newVal = new char[30];
		for (int kk = value.length()-1; kk >= 0; kk--) {
			if (oldVal[kk] == '5') {
				newVal[kk] = '0';
				oldVal[kk-1] = (char)(Character.getNumericValue(oldVal[kk-1]) + 49);
			} else if (oldVal[kk] == '4') {
				newVal[kk] = '-';
				oldVal[kk-1] = (char)(Character.getNumericValue(oldVal[kk-1]) + 49);
			} else if (oldVal[kk] == '3') {
				newVal[kk] = '=';
				oldVal[kk-1] = (char)(Character.getNumericValue(oldVal[kk-1]) + 49);
			} else {
				newVal[kk] = oldVal[kk];
			}
		}
		newVal[value.length()] = '\0';
		System.out.print("**j_ans: ");
		System.out.println(newVal);
					
	}
}
