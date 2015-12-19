
import java.util.List;

/**
 * Driver --- 	Main class. execution starts from here  
 * @author    	Amogh Lale
 */
public class Driver {

	public static void main(String[] args) {
		FileProcessorInterface fi = new FileProcessor("train_all_txt.txt","output.txt");
		String str = "";
		int rowmax=944;
		int colummax=1683;
		int[][]a = new int[rowmax][colummax]; //input matrix
		int[][]b = new int[rowmax][colummax]; //output matrix
		float[] Ru  = new float[rowmax];
		Integer value = null;
		Double simi_threshold =0.3;  //similarity threshold value is set to 0.3
		PredictionAlgoInterface pa=new PredictionAlgo(colummax);
		
		//Read file line by line, split the string by space
		//and create a 2D input matrix a[][]  
		while((str = fi.readfile())!=null)
		{
		    String[] sp = str.split("\\s+");
		    value = Integer.parseInt(sp[2]);
			a[Integer.parseInt(sp[0])][Integer.parseInt(sp[1])]=value;
			
        }
		
		
		//To calculate time of execution for 943 users and 1682 columns
		long startTime = System.currentTimeMillis();
		
		//find average of users (rows) and store the value in 
		//float array Ru
		      	
		for(int i=1;i<rowmax;i++)
    	{
       		// Get the ith user average
    		Ru[i]=pa.findUserAverage(a,i,colummax);
    	}
    	
		
		// find item similarity between columns and prepare a hashmap
		//containing items and list of items similar to those items
		for(int j=1;j<colummax;j++)
		{
			for(int k=1;k<colummax;k++)
			{
				  double isimi = pa.findItemSimilarity(a,rowmax,j,k,Ru);
				  
				  //add an entry to hashmap only if similarity value 
				  //is greater than given threshold of 0.3
				  if(isimi >= simi_threshold)
					  pa.addDataStructure(j, k);
				  				  
			}
		}
		
		
		// find 0 rating for user i and column j and predict rating 
		for (int i=1;i<rowmax;i++)
		{
			for(int j=1;j<colummax;j++)
			{
				if(a[i][j]==0)  //if rating 0
				{
		   		    //find list of similar items
					List<Integer> sim_value = pa.getKeyValue(j);
					
					
					if(sim_value!=null){
						Double predicted_value = pa.weightedSum(i,j,sim_value,a);
						int pr_val = (int)Math.round(predicted_value);
                        
						//if predicted value after rounding off is less than or equal 
						//to 0 then set the rating as 1 as rating is between 1 and 5
						if(pr_val<=0)
                        	b[i][j]=1;
						
						//if predicted value after rounding off is greater than 5, 
						//set 5 rating
                        else if (pr_val > 5)
                        	b[i][j]=5;
						
						//else set calculated value of rating 
                        else 
                        	b[i][j]=pr_val;
				
			    	}
					else //if no similar item then set rating to 1
					{
					   b[i][j]=1;
					}				
				}
				else // copy the non zero rating values directly to output array 
					b[i][j]=a[i][j];
			}
			
		}
		
		
		//write to file
		pa.writetoFile(rowmax, colummax, fi, b);
		
		long finishTime = System.currentTimeMillis();
		long total_time=finishTime-startTime;
		System.out.println("Total time required is: "+total_time);
		
	}

}


