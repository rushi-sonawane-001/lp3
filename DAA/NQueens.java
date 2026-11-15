import java.util.*;

public class NQueens{

static int n;
static char[][] board;
static int[] leftRow;
static int[] upperDiagonal;
static int[] lowerDiagonal;
static int firstCol;

static boolean solve(int col){
if(col==n)
 return true;

if(firstCol==col) return solve(col+1);

for(int row=0;row<n;row++){
    if(leftRow[row]==0 && upperDiagonal[n-1+col-row] == 0 && lowerDiagonal[row+col]==0 ){
 	leftRow[row]=1;
 	upperDiagonal[n-1+col-row] =1;
	 lowerDiagonal[row+col]=1;
 	board[row][col]='Q';

        if(solve(col+1)) return true;
   	
  	leftRow[row]=0;
 	upperDiagonal[n-1+col-row] =0;
	lowerDiagonal[row+col]=0;
 	board[row][col]='.';

			}  

}   

return false;  
 

}


static void printBoard() {
        for (char[] r : board) {
            for (char c : r) System.out.print(c + " ");
            System.out.println();
        }
    }


public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        n = sc.nextInt();
        System.out.print("Enter row and column of first queen (0-based): ");
        int firstRow = sc.nextInt();
        firstCol = sc.nextInt();
        sc.close();

       board=new char[n][n];
       for(char[] row:board) Arrays.fill(row,'.'); 
        leftRow = new int[n];
        lowerDiagonal = new int[2 * n - 1];
        upperDiagonal = new int[2 * n - 1];
       board[firstRow][firstCol] = 'Q';
        leftRow[firstRow] = 1;
        lowerDiagonal[firstRow + firstCol] = 1;
        upperDiagonal[n - 1 + firstCol - firstRow] = 1;
 
        System.out.println("\nInitial board (first queen placed):");
        printBoard();
 
        if (solve(0)) {
            System.out.println("\nFinal " + n + "-Queens matrix:");
            printBoard();
        } else {
            System.out.println("\nNo solution exists with the first queen at ("
                    + firstRow + ", " + firstCol + ")");
        }


}

}