
import java.util.*;
public class FractionalKnapsackAlgo{


public static void main(String[] args){
  	int[][] arr={{100, 20 },{60,10},{100,50},{200,50} }; 
 	int W=90;
	Arrays.sort(arr,(a,b)->  Double.compare((double)b[0] / b[1], (double)a[0] / a[1]) );
  	
	double total=0;
	for(int[] block:arr){
	    
 	if(block[1]<=W){
				
	total+=block[0];
	W-=block[1];
         }
	 else{

        total+= (double) block[0]/block[1] * W;
    break; 
   			}
} 										
				
	System.out.println(total);		
}

}