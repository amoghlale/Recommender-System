import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PredictionAlgo--- 	Compute Prediction Algorithm  
 * 							 
 * @author    		    Amogh Lale
 */
public class PredictionAlgo implements PredictionAlgoInterface{

    public float sum=0;// used to calculate sum of non zero items for a user
    public int count=0;   // calculate count of non zero items for a user
    public Double [][]colmatrix;  // 2D matrix to store similarity values between columns
    public int colummax; //Max column (here 1682)
    public double numerator=0;  //calculate numerator of weightedSum()
	public double denominator=0;  //calculate denominator of weightedSum()
	public double l=0;  //Numerator of item similarity
	public double m=0;  // used for denominator of item similarity algo
	public double n=0;	
	public double msqrt; 
	public double nsqrt;
	public double cosres; // adjusted cosine similarity value of items
	
    // Hash Map to store list of items similar to given item
	Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    
	/**
	   * Default Constructor of PredictionAlgo Class 
	   * @param Maximum Column
	   * @exception No exception
	   * @return No return value.
	   */	
    public PredictionAlgo(int colummax){
    	this.colummax = colummax;
    	colmatrix = new Double[colummax][colummax];
    	
    	//Initialize column matrix with 0.0
    	for(int i=1;i<colummax;i++)
    	{
    		for(int j=1;j<colummax;j++)
    		{
    			colmatrix[i][j]=0.0;
    		}
    	}
    	
    }
    		
    /**
	   * Find average of a row (user). 
	   * @param input array, row value, max column 
	   * @exception No exception
	   * @return Average.
	   */    
    public float findUserAverage(int[][] a,int rowval, int colummax){
    	for(int j=1;j<colummax;j++)
		  {
    		if(a[rowval][j]!=0) //if non zero value of item for rowval
    			{
    				sum=sum+a[rowval][j]; //sum 
    				count++;    //count of non zero terms
    			}
			    
		  }
    	float average = sum/count;
    	sum=0;    //reset sum
    	count=0;  //reset count
    	return average;
	}
    
    /**
	   * Find item similarity. 
	   * @param input array, max row value,
	   * similarity between items j and k,
	   * average of user(float [] Ru) 
	   * @exception No exception
	   * @return similarity value.
	   */ 
    public double findItemSimilarity(int[][] a,int rowmax,int j,int k,float[] Ru){
    	
    	//iterate over all rows having rating for item j and k 
    	//and calculate numerator and denominator of algo
    	for(int i=1;i<rowmax;i++)
    	{
    		if(a[i][j]!=0 && a[i][k]!=0){
    		l+=((a[i][j]-Ru[i])*(a[i][k]-Ru[i]));
    		m+=((a[i][j]-Ru[i])*(a[i][j]-Ru[i]));
    		n+=((a[i][k]-Ru[i])*(a[i][k]-Ru[i]));
    		}
    		
    	}
    	msqrt = Math.sqrt(m);
    	nsqrt = Math.sqrt(n);
    	
    	//apply algorithm
    	cosres = l/(msqrt * nsqrt);
    	
    	//add the similarity value to column matrix
    	colmatrix[j][k]=(cosres);
    	l=0; //reset value of l,m,n
    	m=0;
    	n=0;
    	return (cosres);                 
    }
    
    /**
	   * Add similar items to Hashmap. 
	   * @param item 1, item 2 
	   * @exception No exception
	   * @return No return value
	   */  
    public void addDataStructure(Integer item, Integer item2){
    	
		if (map.containsKey(item))   //if key already exists in hashmap
		        map.get(item).add(item2);  // add similar item to existing key
		else 
		{
		        List<Integer> list = new ArrayList<Integer>(); //create a list
		        list.add(item2); //add the item2 to the list
		        // put this list into the hashmap corresponding to key item
		        map.put(item, list); 
		       	        
		}	 
			
	}
    
    /**
	   * Method to find and return list of items
	   * similar to a given item (key) from the 
	   * hashmap 
	   * @param Key 
	   * @exception No exception
	   * @return List of items (List<Integer>)
	   */     
    public List<Integer> getKeyValue(int j){
    	if (map.containsKey(j) )  
			return map.get(j);   //get list of items similar to j 
	    else
			return null;   	    //else return null
    	}
    
    
    /**
	   * Perform Prediction computation using weighted sum 
	   * technique
	   * @param user(i), predict rating of item(j) List of similar items to j,
	   * input array a[][] 
	   * @exception No exception
	   * @return final predicted value
	   */   
    public Double weightedSum(int i, int j, List<Integer> sim_value,int[][] a){
    	numerator=0;  //initialize numerator to 0
    	denominator=0; //initialize denominator to 0 
       	
    	//loop over similar items
    	for(int k=0;k<sim_value.size();k++){  
       		
    		//if the item rating for a user is non zero 
    		//then only calculate numerator and denominator
    		if(a[i][sim_value.get(k)]!=0)
    		{
    			//multiply similarity value of given item j and k with
    			//rating of item k for user i. We have to take summation 
    			numerator+=((colmatrix[j][sim_value.get(k)]) * (a[i][sim_value.get(k)]));
    			
    			// calculate summation of absolute value of similarity between j and k
    			denominator+=Math.abs(colmatrix[j][sim_value.get(k)]);
       		}
       	}
    	Double predicted_value=numerator/denominator;//find predicted value
    	return predicted_value;
    }
    
    /**
	   * write result to output file 
	   * @param max row, max column output 2D matrix and 
	   * fileprocessorInterface fi
	   * @exception No exception
	   * @return no return value
	   */ 
    public void writetoFile(int rowmax,int colummax,FileProcessorInterface fi,int [][]b){
    	StringBuilder builder=new StringBuilder();
    	for(int i=1;i<rowmax;i++)
		{
		    for(int j=1;j<colummax;j++)
		    { 
		    	builder.append(i+" "+j+" "+b[i][j]+" \n");
		    }  
		}
    	fi.writefile(builder.toString());
    }

	
}
