// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa2
// 10/14/16
// Search.java
// Searches for the given words if found it outputs found the given word. if not outputs given word not found. Gets the words from the given file called Dictionary uses mergesort to sort them in lexical ordering and binarysearch to effecienty find the word in the sorted array and returns the index of the found word.
// ----------------------------------------------------------------
import java.io.*;
import java.util.*;
public class Search {

	public static void main(String[] args) throws IOException{

		Scanner in;	// first infile inpput
		Scanner newin;	// second infile input
                int found;      // returned found index from binary search
                int size=0;    // initial size of the file, to be incremented to be used for the size of the array
		try {
	         in = new Scanner(new File("Dictionary")); // sacnner input with File object input from the file Dictionary

	         while(in.hasNextLine()) // 
	         {
	        	 in.nextLine(); // so it goes to the next line 
	        	 size++;        // then increments the size 
	         }

	         newin = new Scanner(new File("Dictionary")); // since the first scanner input file has reached the end of file we need a new to start fron the beginning to start receving the each word on each line
	         String[] file_string = new String[size]; // initialize an array of string with the size of the words in the Dictionary file
	         int index=0;
	         while(newin.hasNextLine())
	         {
	        	 file_string[index] = newin.nextLine(); // gets new words on each new line
	        	 index++;
	         }
	         mergeSort(file_string, 0, size-1); // merges the words in lexical ordering 
                 index =0;
                 while(index < args.length) 
                 {
	         found = binarySearch(file_string, 0, size-1, args[index]); // returns the index of the found word in the merged sorted array
	         if(found != -1) // -1 inticates that the word has not been found in the given array
	         {
	        	 System.out.println("found "+args[index]+'.');
	         }
	         else
	         {
	        	 System.out.println("word "+args[index]+" not found.");
	         }
                 index++;
	        }//end while 
	      }
		catch(IOException e) // if the input file fales to open
		{
			System.out.println("Caught exception: "+e);
		}

	}


	   // binarySearch() the array needs to be sorted first
		// uses divide and concur to find the appropriate index of the search string
	   // pre: Array A[p..r] is sorted
	   public static int binarySearch(String[] A, int p, int r,  String target){
	      int q;  // to store the middle index.
	      if(p > r) { // if start index goes over end index return -1 not found
	         return -1;
	      }else{
	         q = (p+r)/2; // calculate middle index
	         if(target.compareTo(A[q])==0){ // if target string is the same as A at position q return q the index position
	            return q;
	         }else if(target.compareTo(A[q])<0){ // if target string is smaller than A at position q
	            return binarySearch(A, p, q-1, target); // do recursive  call from starting position to middle of the array
	         }else{ // target > A[q] // if target string is bigger than A at position q
	            return binarySearch(A, q+1, r, target);// do recursive  call from starting middle to end of the array
	         }
	      }
	   }

	// mergeSort()
	   // sort subarray A[p...r]
	   public static void mergeSort(String[] A, int p, int r){
	      int q;			// to store the middle index.
	      if(p < r) {		// if the beginning and end array index hasn't reached each other.
	         q = (p+r)/2;	// getting the middle index of the array.
	         mergeSort(A,p, q); // using recursive to pass the beginning and the middle index of the array
	         mergeSort(A, q+1, r); // using recursive to pass the index one after the middle  of the array to the end.
	         merge(A,p, q, r);   // merge the array from beginning to the end, separated by the middle.
	      }
	   }

	   // merge()
	   // merges sorted subarrays A[p..q] and A[q+1..r]
	   public static void merge(String[] A, int p, int q, int r){
	      int n1 = q-p+1; // first half
	      int n2 = r-q; //second half
	      String[] L = new String[n1]; // init of size n1
	      String[] R = new String[n2]; // init of size n2
	      int i, j, k;  // loop control variables

	      for(i=0; i<n1; i++){ // while less than the size of n1-first half
	         L[i] = A[p+i];  // start from p which the starting position of the given index of the first half array 
	         					// store it in L array of size n1
	      }
	      for(j=0; j<n2; j++){ // while less than the size of n2-second half
	         R[j] = A[q+j+1]; // start from q which the starting position of the given index of the second half array
	         					// store it in R array of size n2
	      }

	      i = 0; j = 0;
	      for(k=p; k<=r; k++){ // while the starting position of the array hasn't reach the end continue
	         if( i<n1 && j<n2 ){ // i counter less than the size of n1 and j less than the size of n2
	            if(L[i].compareTo(R[j]) < 0){// uses lexical ordering to compare string on L array to R array if L is smaller then execute
	               A[k] = L[i];  // store the alphabet in the A starting from smallest to largest
	               i++;
	            }else{       // else string in R array is bigger
	               A[k] = R[j];// Store string in R at position J in A at position K
	               j++;
	            }
	         }else if( i<n1 ){  // if J counter ends first(meaning the second half of array is smaller)
	            A[k] = L[i];    // start storing the remaining alphabet in L back into A 
	            i++;
	         }else{ // j<n2 
	        	// if I counter ends first(meaning the first half of array is smaller)
	            A[k] = R[j]; // start storing the remaining alphabet in R back into A
	            j++;
	         }
	      }
	   }
           }// end of class Search
