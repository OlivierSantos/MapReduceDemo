package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
	
  
	
	
	//A Key value pair list Read From a file 
	
	
	public  List<Pair<Object>> listPairOfKeyValueReadFromFile(String fileName) {
		List<Pair<Object>> list=new ArrayList<Pair<Object>>();
		 String regexp = " ";
	    
	     File file=new File("Data/"+fileName);
	     StringBuilder divideBuilder=new StringBuilder();
		  
	     
	     try {
	    	 BufferedReader reader=new BufferedReader(new FileReader(file));
	           
	         String st;
	         while((st=reader.readLine())!=null) {
	        	 divideBuilder.append(st);
	        	 divideBuilder.append(System.lineSeparator());
	        	
	         }
	     }catch(Exception e) {
        	 e.printStackTrace();
         }
	     
	       
	     String[] words=new String[divideBuilder.length()];
	     if(divideBuilder!=null) {
	    	 String value=divideBuilder.toString();
	    	 
	    	words=value.split(regexp);
	    	
	    	for(String s:words) {
	    		 s=s.toLowerCase();
	    		 s.replace("\"", "").replace("'", "");
	    		 String regs = "([a-zA-Z])+";
	    		 if(s.matches(regs)||s.contains("-")) {
	    		    if(s.contains("-")) {
	    		    	
	    		    String[] wrds=s.split("-");
	    		    for(String ss:wrds) {
	    		    	list.add(new Pair<Object>(ss,1));
	    		    }
	    		    }
	    		    else {
	    			
	    		      list.add(new Pair<Object>(s,1));
	    		         }
	    		    
	    		 }
	    		 
	    			 }
	    	 }
	    	 
	    	 return list;
	    	
	     }
	
	//Write the output to the file
	
	public  void writeOutPutOnFile(String filename,List<Pair<Object>> listValue) {
		File file=new File("Data/mapper/"+filename);
		  try {
			  PrintWriter writer = new PrintWriter(new FileWriter(file));
			  for(Pair<Object> pair: listValue) {
				     System.out.println("("+pair.getK()+","+pair.getV()+")");
				     writer.println("("+pair.getK()+","+pair.getV()+")");
			}
			  
			  writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
}
