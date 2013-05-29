import java.io.*;
/*
 * @Class:  Triangle
 * @Version:2013.05.28.0.0
 * @Author: Lei Jian
 * @eMail:	lei.jianray@gmail.com
 * 
 * */
public class Triangle {
	private final static int CAPACITY = 100;
//	private static int count = 0;
	private static int maxSum = 0;
	private static int row = -1;
	private static Integer[][] intArr = new Integer[CAPACITY][CAPACITY];
	
	public static void main(String[] args) {
		if(args.length != 0){
			new Triangle().readFile(args[0]);
		}else{
			System.out.println("You forgot to input FILE_NAME!");
			System.exit(-1);
		}
		maxSum = new Triangle().maxTotal();

		System.out.println("max Total is : " + maxSum);
	}
	
	public void readFile(String fName){
		try{
			BufferedReader in = new BufferedReader(
                     new FileReader(new File(fName)));
			String line;
			while ((line = in.readLine()) != null){
				String[] lineArr = line.split("\\s");
				
				if(lineArr.length > 0){
					row++;
					convertLine(lineArr);
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void convertLine(String[] lineArr){
		for(int col=0; col <= row; col++){
			intArr[row][col] = Integer.parseInt(lineArr[col]);
		}
	}
	
	public int maxTotal(){
		for(--row; row >= 0; row--){
			for(int col = 0; col <= row ; col++ ){
				intArr[row][col] +=  max(intArr[row+1][col],intArr[row+1][col+1]);
			}
		}
		return intArr[0][0];
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
}
