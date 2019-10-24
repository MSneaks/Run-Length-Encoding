package proj6;


import java.io.*;
import java.util.*;

public class Main 
{
	
	static class runLength
	{
		int numRows, numCols,minVal,maxVal;
		int whichMethod;
		public runLength()
		{
			whichMethod=1;
		}
		
		public void encodeMethod(FileInputStream iF, File outFile) throws Exception
		{
			Scanner inFile=new Scanner(iF);
			numRows=inFile.nextInt();
			numCols = inFile.nextInt();
			minVal = inFile.nextInt();
			maxVal= inFile.nextInt();
			String x = numRows + " " +numCols+ " " + minVal + " " + maxVal;
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			writer.write(x);
			writer.newLine();
			
			int row = 0;
			
			while(row<numRows)
			{
				int col = 0;
				int length = 0;
				int curVal = inFile.nextInt();
				
				x = row + " " + col + " " + curVal;
				writer.write(x);
				
				length++;
				
				while(col<(numCols-1))
				{
					col++;
					int nextVal = inFile.nextInt();
					if(nextVal == curVal)
					{
						length++;
					}
					else
					{
						x = " " + length;
						writer.write(x);
						writer.newLine();
						curVal = nextVal;
						length = 1;
						x = row + " " + col + " " +curVal;
						writer.write(x);
						
					}
				}
				row++;
				x = " " + length;
				writer.write(x);
				writer.newLine();
				col = 0;
			}
			writer.close();
			inFile.close();
					
			return;
		}
		
		public void decodeMethod(File iFile, File outFile) throws Exception
		{
			Scanner inFile1 = new Scanner(iFile);
			
			int rowsize;
			int colsize;
			int minVal;
			int maxVal;
			
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(outFile));
		
			String x;

			rowsize = inFile1.nextInt();
			colsize=inFile1.nextInt();
			minVal=inFile1.nextInt();
			maxVal =inFile1.nextInt();
			
			x = rowsize +" "+ colsize+" " + minVal+" " + maxVal+"\n";
			writer1.write(x);
			writer1.newLine();
			int[][] run = new int[rowsize][colsize];
			
			int row=0;
			int col = 0;
			int val=0;
			int counter = 0;
			
			while(inFile1.hasNextInt())
			{
				
				row = inFile1.nextInt();
				col = inFile1.nextInt();
				val =inFile1.nextInt();
				counter=inFile1.nextInt();
				
				while(col!=colsize)
				{
					
					run[row][col] = val;
					col++;
				}
				

			}
			for(int i = 0; i<rowsize;i++)
			{
				for(int j=0;j<colsize;j++)
				{
					int y = run[i][j];
					System.out.print(y);
					writer1.write(String.valueOf(y)+ " ");
					
				}
				writer1.newLine();
			}
			writer1.close();
			return;
		}
	}
	
	
	public static void main(String[] argc)throws Exception {
		// TODO Auto-generated method stub

    	String nameEncodeFile;
    	String nameDecodeFile;
    	
    	nameEncodeFile = (String)argc[0];
    	nameEncodeFile=nameEncodeFile.substring(0, 28) + nameEncodeFile.substring(29);
    	nameEncodeFile+= "_EncodeMethod1";
    	
    	nameDecodeFile=nameEncodeFile;
    	nameDecodeFile+="_Decoded";
    	
		FileInputStream inFile = new FileInputStream(argc[0]);
		
    	File outFile = new File(nameEncodeFile);
    	
    	
    	runLength rL = new runLength();
    	
    	rL.encodeMethod(inFile, outFile);
    	
    	
    	inFile.close();
    	
    	File doutFile= new File(nameDecodeFile);
    
    	rL.decodeMethod(outFile, doutFile);
    	
    	
    	
    	
    	

		

	}

}


