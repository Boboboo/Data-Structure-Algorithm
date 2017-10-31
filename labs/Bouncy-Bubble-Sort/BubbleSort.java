package Exercise0;

import java.util.*;



public class BubbleSort0 {

	public static void main(String[] args) {
		int [] array={7,5,6,8,1,2,4,9};
		bubSort(array);
		System.out.println(Arrays.toString(array));

	}
	public static int [] bubSort(int [] array){
		
		for (int pass = 0; pass < array.length - 1; pass++) {		      
	        int swapcount1=0;
        	int swapcount2=0;
	        if (pass % 2 == 0) {
	        	
	            for (int i = pass/2; i < array.length - 1- pass/2; i++) {
	            	
	                if (array[i] > array[i + 1]) {
	                    int temp = array[i];
	                    array[i] = array[i + 1];
	                    array[i + 1] = temp;
	                    swapcount1 ++;
	                }
	                
	            }
	            if(swapcount1==0){
	            	return array;
	            }
	        }
	        else {
	            for (int i = array.length -1- pass/2; i >pass/2; i--) {
	                if (array[i] < array[i - 1]) {
	                    int temp = array[i];
	                    array[i] = array[i - 1];
	                    array[i - 1] = temp;
	                    swapcount2 ++;	                    
	                }
	            }
	            
	            if(swapcount2==0){
	            	return array;
	            }
	        }
	        
	        for(int a=0;a<array.length;a++){
	        	System.out.print(array[a]+" ");
	        }
	        
	        System.out.println("");
	    }
	    return array;
	}
		
   
}			
		

	

