package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Reducer {
	
	public List<Pair<Object>> listGroupByPair(List<Pair<Object>> listValues){
		List<Pair<Object>> listGroupByKey=new ArrayList<Pair<Object>>();
		
		
		int iter=0;
		for(int i=0; i<listValues.size();i=i+iter) {
			List<Object> values=new ArrayList<Object>();
			values.add(listValues.get(i).getV());
			for(int j=i+1; j<listValues.size(); j++) {
				
				if(listValues.get(i).getK().equals(listValues.get(j).getK())) {
				  values.add(listValues.get(j).getV());
				 
				}
			}
			listGroupByKey.add(new Pair<Object>(listValues.get(i).getK(),values));
			
			iter=values.size();
		}
		
			
		return listGroupByKey;
		
	}
	
	public List<Pair<Object>> listGroupByPairSum(List<Pair<Object>> listGroupByPair){
		List<Pair<Object>> listGroupAndSum=new ArrayList<Pair<Object>>();
		
		for(Pair<Object> obje:listGroupByPair) {
			 Integer sum=0;
			 List<Object> values=(List<Object>) obje.getV();
			 for(int i=0; i<values.size(); i++) {
				 sum=sum+Integer.parseInt(values.get(i).toString());
				 
			 }
			 
			 //System.out.println("Sum:"+sum);
			listGroupAndSum.add(new Pair<Object>(obje.getK(),sum));
		}
		return listGroupAndSum;
		
	}
	
	public int getPartition(String key, int numberOfReducer) {
		return Math.abs(key.hashCode()%numberOfReducer);
	}
	
	public  List<List<Pair<Object>>> reducerListPairs(List<List<Pair<Object>>> listMapper,List<List<Pair<Object>>> reducers){
	    
		//List<List<Pair<Object>>> listGroupPairs=new ArrayList<List<Pair<Object>>>();
	
		
		for(int i=0; i< listMapper.size(); i++) {
		//System.out.println("Reducer"+i+" Input:");
		
			List<Pair<Object>> pairObje=new ArrayList<Pair<Object>>();
			
       for(int j=0; j<listMapper.get(i).size(); j++) {
    	  
    	   List<Object> values=new ArrayList<Object>();

		   values.add(listMapper.get(i).get(j).getV());
    	   for(int t=j+1; t<listMapper.get(i).size(); t++) {
    		   
    	      if(listMapper.get(i).get(j).getK().equals(listMapper.get(i).get(j).getK())) {
    	    	  values.add(listMapper.get(i).get(t).getV());
    	      }
    	      
    	   }
    	      
    	      reducers.get(getPartition(listMapper.get(i).get(j).getK(),reducers.size())).add(new Pair<Object>(listMapper.get(i).get(j).getK(),values));
       	   
         
       }
       
       
       
		}
		
		
		
		return reducers;
	}


	public  void writeOutPutOnFile(String filename,List<Pair<Object>> listValue) {
		File file=new File("Data/reducer/"+filename);
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
