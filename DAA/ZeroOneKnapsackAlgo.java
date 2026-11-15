
import java.util.*;
public class ZeroOneKnapsackAlgo{


 static int fun(int i,int w,int[][]arr, int[][]dp){
	if(i==0) {
	if(arr[i][1]<=w) return arr[i][0];
	return 0;
		}
       if(dp[i][w]!=-1) return dp[i][w];

      int notTake=0+fun(i-1,w,arr,dp);
      int take=Integer.MIN_VALUE;
       if(arr[i][1]<=w) take=arr[i][0]+fun(i-1,w-arr[i][1] , arr,dp);
return dp[i][w]= Math.max(notTake,take);		


}


public static void main(String[] args){
     int w=6;
     int[][] arr={{30,3} , {40 , 2} , {60 , 5}};
     int[][]dp=new int[3][7];  
   for(int[] arrr:dp) Arrays.fill(arrr,-1);
   System.out.println(fun(2,w,arr,dp));
}

}