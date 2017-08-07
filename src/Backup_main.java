import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Backup_main {
	
	public static void main(String[] args) {
		String user = "bhucsapu";
		int num = 842;
		int nul = 0;
		File DirLocation = new File("C:/users/" + user + "/Downloads");
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
			        try{
			        	copyFolder(DirLocation,theDir);
			           }catch(IOException e){
			        	e.printStackTrace();
			        	//error, just exit
			                System.exit(0);
			           }
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
	public static void copyFolder(File src, File dest)
	    	throws IOException{

	    	if(src.isDirectory()){

	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Directory copied from "
	                              + src + "  to " + dest);
	    		}

	    		//list all the directory contents
	    		String files[] = src.list();

	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}

	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    			InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest);

	    	        byte[] buffer = new byte[4096];

	    	        int length;
	    	        //copy the file content in bytes
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }

	    	        in.close();
	    	        out.close();
	    	        System.out.println("File copied from " + src + " to " + dest);
	    	}
	    }
	}

		    
		