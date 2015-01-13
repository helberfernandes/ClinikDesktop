import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MontaArquivo {
	
	
	public static void main(String args []){
	
	FileReader reader = null;
	try {
		reader = new FileReader(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\listafotos.txt"));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	BufferedReader leitor = new BufferedReader(reader);
    String linha = null;  
    try {
		while((linha = leitor.readLine()) != null) {
			if(linha.endsWith(".jpg")){
		        System.out.println("'" + linha.replace(".jpg", "',"));  
			}
		}
		   leitor.close();  
   	    reader.close();  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    
    

    	 
	}
}
