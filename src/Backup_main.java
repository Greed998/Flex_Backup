import java.io.File;

public class Backup_main {
	
	public static void main(String[] args) {
		int num = 842;
		int nul = 0;
		if (num < 1000) {
			File theDir = new File("D:\\BHUM" + nul + num);
			if (!theDir.exists()) {	
			    System.out.println("creating directory: " + theDir.getName());
			    boolean result = false;
			    try{
			        theDir.mkdir();
			        result = true;
			    } 
			    catch(SecurityException se){
			        //handle it
			    }        
			    if(result) {    
			        System.out.println("DIR created");  
			    }
		}else {
			System.out.println("Dir already Created");
		}
		}else {
			File theDir = new File("D:\\BHUM" + num);
			if (!theDir.exists()) {	
			    System.out.println("creating directory: " + theDir.getName());
			    boolean result = false;			    
			    try{
			        theDir.mkdir();
			        result = true;
			    } 
			    catch(SecurityException se){
			        //handle it
			    }        
			    if(result) {    
			        System.out.println("DIR created");  
			    }
		}else {
			System.out.println("Dir already Created");
		}
		}
	}
}
		    
		