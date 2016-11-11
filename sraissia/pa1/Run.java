// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa1
// 10/7/16
// Run.java
// Reverses the array in 3 different ways using recursion
// Finds the max and min index of the array
// prints the array in original order then after the given reverse,min and max function
// ----------------------------------------------------------------
public class Run {

	public static void main(String[] args) {
		Recursion r = new Recursion();
		int[]a={-1,2,6,12,9,2,-5,-2,8,5,7};
		int[]b = new int[11];
		int[]c = new int[11];
		r.printArray(a,'a');
		System.out.println("min index: "+r.minArrayIndex(a, 0, a.length-1));
		System.out.println("max index: "+r.maxArrayIndex(a, 0, a.length-1));
		r.reverseArray1(a, b, a.length);
		r.printArray(b,'b');
		r.reverseArray2(a, c, a.length);
		r.printArray(c,'c');
		r.reverseArray3(a, 0, a.length);
		r.printArray(a,'a');

	}

}
