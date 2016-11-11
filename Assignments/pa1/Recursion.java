// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa1
// 10/7/16
// Recursion.java
// Reverses the array in 3 different ways using recursion
// Finds the min and max index of the array
// ----------------------------------------------------------------
public class Recursion {
	void reverseArray1(int[]x,int[]y,int n)
	{
		if(n!=0)
		{
		n-=1;
		y[n]=x[x.length-1 - n];
		reverseArray1(x,y,n);
		}
	}
	void reverseArray2(int[]x,int[]y,int n)
	{
		if(n!=0)
		{
			n-=1;
			y[n]=x[(x.length-1)-n];
			y[(y.length-1)-n]=x[n];
			reverseArray2(x,y,n);
			
		}
	}
	void reverseArray3(int[]x,int i,int j)
	{
		if(i<j)
		{
		j-=1;
		int temp = x[j];
		x[j] = x[i];
		x[i] = temp;
		i+=1;
		reverseArray3(x,i,j);
		}
	}
	   int minArrayIndex(int[] A, int p, int r){

		      if (p < r) {
			     int q = (p+r)/2; 
		         int left = minArrayIndex(A, p, q);
		         int right = minArrayIndex(A, q+1, r);
		         if (A[left] < A[right])
		         {
		        	 return left;
		         }
		         else if (A[right] < A[left])
		         {
		        	 return right;
		         }
		         else
		         {
		        	 return q;
		         }
		      }
		      return 0;
		   }
	   int maxArrayIndex(int[] A, int p, int r){
		      
		      if (p < r) {
		    	 int q = (p+r)/2; 
		         int left = maxArrayIndex(A, p, q);
		         int right = maxArrayIndex(A, q+1, r);
		         if (A[left] > A[right])
		         {
		        	 return left;
		         }
		         else if (A[right] > A[left])
		         {
		        	 return right;
		         }
		         else
		         {
		        	 return q;
		         }
		      }
		      return 0;
		   }
	void printArray(int[]x,char c)
	{
		System.out.print("array "+c+": {");
		for(int index =0;index<x.length;index++)
		{
			System.out.print(x[index]);
			if(index!=x.length-1)
			{
				System.out.print(",");
			}
		}
		System.out.println("}");
		
	}
}
