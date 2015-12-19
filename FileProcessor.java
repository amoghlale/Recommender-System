import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileProcessor --- Reading and writing to a file	
 * @author    	     Amogh Lale
 */
  public class FileProcessor implements FileProcessorInterface {
    
	BufferedReader br=null;
	String inputFile;
	String outputFile;
	BufferedWriter bw=null;  
	FileWriter fw=null;
	
	/**
	   * Parameterized constructor  
	   * @param input and output file names
	   * @exception NullPointerException,FileNotFoundException
	   * @return No return value.
	   */
	public FileProcessor(String inputFile, String outputFile){
		try {
    		this.inputFile=inputFile;
    		this.outputFile=outputFile;
			br = new BufferedReader(new FileReader(inputFile));
    	}catch(NullPointerException e){
       		System.err.println("NullPointerException");
    		System.exit(0);
		} catch (FileNotFoundException e) {
		    System.err.println("Filenotfound Exception occured");
			System.exit(0);	
	}
	
	}
	
	/**
	   * Reads from input file 
	   * @param No Arguments
	   * @exception IOException
	   * @return line
	   */
	public String readfile(){
		
		try {
			 String str=br.readLine();
			 return str;
		} catch (IOException e) {
			System.out.println("IOException occured");
			System.exit(0);	
		}
		return null;
		
   }			
	
	/**
	   * Creates a file and writes data to it. 
	   * @param output data
	   * @exception IOException
	   * @return No Return value
	   */
	public void writefile(String outData) {
		
		try {
				File file = new File(outputFile);
				if (!file.exists()) {
					file.createNewFile();    // check if file exists  
				} // end if condition

				fw = new FileWriter(file.getAbsoluteFile(),true);
				bw = new BufferedWriter(fw);
				bw.write(outData);
		} //end try block
		catch (IOException e) {
			System.err.println("IOException Error");
			System.exit(0);	
		} //end catch block
		finally{
			try {
			    bw.flush();
				bw.close();
				fw.close();
				br.close(); 
	} catch (IOException e) {
			System.err.println("File Cannot be closed Error");
			System.exit(0);	

			}
		}
	}
	
}
