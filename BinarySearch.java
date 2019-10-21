
public class BinarySearch {
		  public static void main(String[] args) {
		    System.out.println("Hello world!");
		    int[] arr = {1,2,3,4,5,6,7,8,9};
		    int element = BinarySearch1(arr,4);
		    System.out.println("Answer: " + element);
		  }
		  
		  public static int BinarySearch1(int[] arr, int num){
		    int size = arr.length;
		    int midElem = arr.length/2;
		    if(arr[midElem] == num){
		      return arr.length/2;
		    }
		    while(size > 1){
		      while(arr[midElem] < num){
		        midElem = ((arr.length - midElem)/2);
		        size--;
		      }
		      while(arr[midElem] > num){
		        midElem = ((arr.length - midElem)/2) + midElem;
		        size--;
		      }
		    }
		    return midElem;
		  }
		  
		  // public static int BinarySearch2(int[] arr, int num){
		    
		  // }

}
