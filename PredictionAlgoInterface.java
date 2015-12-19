import java.util.List;

//interface for predictionAlgo
public interface PredictionAlgoInterface {

	 public float findUserAverage(int[][] a,int rownum, int columnnum);
	 public double findItemSimilarity(int[][] a,int rowmax,int j,int k,float[] Ru);
	 public void addDataStructure(Integer item, Integer item2);
	 public List<Integer> getKeyValue(int j);
	 public Double weightedSum(int i, int j, List<Integer> sim_value,int[][] a);
	 public void writetoFile(int rowmax,int colummax,FileProcessorInterface fi,int [][]b);
	
}

