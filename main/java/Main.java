package main.java;

import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Mapper mapper=new Mapper();
		Reducer reducer=new Reducer();
		
		List<Pair<Object>> listValue=mapper.listPairOfKeyValueReadFromFile("input.txt");
	     
		
		Collections.sort(listValue);
		
		//mapper output
		System.out.println("Mapper Out Put");
		mapper.writeOutPutOnFile("output.txt",listValue);
		
		//reducer input
		System.out.println();
		System.out.println("Reducer Input:");
		List<Pair<Object>> listGroupByKey= reducer.listGroupByPair(listValue);
		
		for(Pair<Object> obj:listGroupByKey) {
			
			System.out.println("("+obj.getK()+","+obj.getV()+")");
		}
		
		//reducer.listGroupByPairSum(listGroupByKey);
		System.out.println();
		System.out.println("Reducer Output:");
		
       List<Pair<Object>> listGroupSum= reducer.listGroupByPairSum(listGroupByKey);
		
		for(Pair<Object> obj:listGroupSum) {
			
			System.out.println("("+obj.getK()+","+obj.getV()+")");
		}
		reducer.writeOutPutOnFile("output.txt",listGroupSum);
		
		
		
		
	}
}
