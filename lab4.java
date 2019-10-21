public class lab4 {

    // Merge sort 
    public static void mergeSort (int[] array) {
    	int [] temp = new int[array.length]; //Initializing an array to hold the temp values towards the end of the sorting process
        mergeSortA(array, temp, 0 , array.length-1); //Calling the mergeSortA method while passing two arrays (array and temp) and two int values (0 and array.length - 1)
    }
    public static void mergeSortA (int [] array, int [] temp, int lS, int rE) {
    	if(lS >= rE) { //This if statement is the base case of the recursive algorithm of merge sort
    		return; //ends the method if array runs out of indexes 
    	}
    	int middle = (lS + rE)/2; //This line determines the splitting point of the array 
    	mergeSortA(array, temp, lS, middle); //This line calls the mergeSortA method while passing the argument of an array (named array) and two int value (lS, and middle)
    	mergeSortA(array, temp, middle+1, rE); //This line calls the mergeSort A method while passing the argument of an array (named array) and two int values (middle+1, and rE)
    	mergeHalves(array, temp, lS, rE); //This line calls the mergeHalves method while passing the argument of two arrays (array and temp) and two int values (lS, rE)
    }
    public static void mergeHalves (int [] array, int [] temp, int lS, int rE) {
    	int lE = (lS + rE)/2; //determines the end point of the left array
    	int rS = lE + 1; //determines the starting point of the right array
    	int size = rE - lS + 1; //determines the size of the total array
    	
    	int left = lS; //Initializes the left start point as left
    	int right = rS; //Initializes the right start point as right
    	int index = lS; //Initializes the index using the value in left start
    	
    	while (left <= lE && right <= rE) { //runs the while loop as long as there is an element left to compare in both the right and the left arrays 
    		if(array[left] <= array[right]) { //if the value in the element on the left is greater than the value in the element on the right 
    			temp[index] = array[left]; //assigns the value in the element of the left array in to the temp array at that same index value
    			left++; //increments left by 1
    		}
    		else { //if the value in the element on the right is greater than the value in the element on the left 
    			temp[index] = array[right]; //assigns the value in the element of the left array into the temp array at that same index value
    			right++; //increments right by 1
    		}
    		index++; //increments index by 1
    	}
    	
    	System.arraycopy(array, left, temp, index, lE - left + 1); //Uses the command .arraycopy to copy array (starting from left start) into temp (starting from index) for lE - left + 1 number of elements
    	System.arraycopy(array, right, temp, index, rE - right + 1); //Uses the command .arraycopy to copy array (starting from right start) into temp (starting from index) for rE - right + 1 number of elements
    	System.arraycopy(temp, lS, array, lS, size); //Uses the command .arraycopy to copy temp (starting from left start) into array (starting from left start) for size number of elements
    	
    	
    }
        
    // Quick sort 
    public static void quickSort (int[] array) {
        quickSortA(array, 0, array.length-1); //Calling the quickSortA method while passing an array (array) and two int values (0 and array.length-1)
    }
    public static void quickSortA (int[] array, int start, int end) {
    	if (start < end) { //This if statement runs until the array runs out of elements
    		int pivotIndex = pivotQuickSort(array, start, end); //Calling the pivotQuickSort method while passing an array (array) and two int values (start and end) and places the returned int value into pivotIndex
    		
    		quickSortA(array, start, pivotIndex-1); //Calling the quickSortA method while passing an array (array) and two int values (start and pivotIndex-1)
    		quickSortA(array, pivotIndex+1, end); //Calling the quickSortA method while passing an array (array) and two int values (pivotIndex+1 and end)
    	}
    }
    public static int pivotQuickSort(int[] array, int start, int end) {
    	int pivot = array[end]; //Initializes the pivot as the same value as the value in end
    	int p = (start-1); //Initializes p as the same value as the value of start-1
    	
    	for(int i = start; i < end; i++) { //A for loop that runs until i = end, and i starts from the value in start
    		if(array[i] <= pivot) { //This if statement runs as long as array[i] is not greater than the value in pivot
    			p++; //increments p by 1
    			
    			//swapping values in array (array)
    			int temp = array[p]; //Initializes temp with the value in array[p]
    			array[p] = array[i]; //Assigns array[p] with the value in array[i]
    			array[i] = temp; //Assigns array[i] with the value in temp 
    		}
    	}
    	
    	int temp = array[p+1]; //Initializes temp with the value in array[p+1]
    	array[p+1] = array[end]; //Assigns array[p+1] with the value in array[end]
    	array[end] = temp; //Assigns array[end] with the value in temp
    	
    	return p+1; //returns the value of p+1
    }
    
    // Your chosen quadratic sorting algorithm
    public static void insertionSort (int[] array) {
        insertionSortA(array, array.length); //Calling the insertionSortA method while passing an array (array) and an int value (array.length)
    }
    public static void insertionSortA (int[] array, int i) {
    	if( i <= 1) { //This if statement is the base case of the recursive algorithm of insertion sort
    		return; //ends the method if the array runs out of elements to sort
    	}
    	insertionSortA(array, i - 1); //This line calls insertionSortA method while passing the argument of an array (array) and an int value (i-1). It is also the line that makes the recursive calls
    	int k = array[i - 1]; //Assigns k with the value in the last part of the array (i-1)
    	int j = i - 2; //Assigns j with the value in the element before the the last one in the array (i-2)
    	while(j >= 0 && array[j] > k) { //This while loop determines which values in the array need to be inserted into a different part
    		array[j + 1] = array[j]; //Because the value of the lower indexed variable is higher than that of k, k's index value is changed to whatever is in the element array[j]
    		j--; //decrement of j
    	}
    	array[j + 1] = k;  //Reinsert the value sorted in the k variable into its spot in the array 
    }
    
    // Proposed combination algorithm (MergeInsertion Sort)
    public static void mergeInsertionSort (int[] array) {
    	int[] temp = new int[array.length]; //Initializing an array to hold the temp values towards the end of the sorting process
        mergeInsertionSortA(array, temp, 0, array.length-1); //Calling the mergeInsertionSortA method while passing two arrays (array and temp) and two int values (0 and array.length-1)       
    }
    public static void mergeInsertionSortA (int[] array, int[] temp, int lS, int rE) {
    	if(lS >= rE) { //This if statement is the base case of the recursive algorithm of merge sort
    		return; //ends the method if array runs out of indexes 
    	}
    	int middle = (lS + rE)/2; //This line determines the splitting point of the array 
    	mergeInsertionSortB(array, lS, middle); //This line calls the mergeInsertionSortB method while passing the argument of an array (named array) and two int values (lS, and middle)
    	mergeInsertionSortB(array, middle + 1, rE); //This line calls the mergeInsertionSortB method while passing the argument of an array (named array) and two int values (middle+1, and rE)
    	mergeInsertionHalves(array, temp, lS, rE); //This line calls the mergeInsertionHalves method while passing the argument of two arrays (array and temp) and two int values (lS, rE)
    }
    public static void mergeInsertionSortB (int[] array, int start, int i) {
    	if( i <= start - 1) { //This is the base case for the insertion sorting algorithm
    		return; //ends the method if array runs out of indexes 
    	}
    	mergeInsertionSortB(array, start, i - 1); //This line is where the recursion occurs
    	int tempH = array[i]; //Assigns the highest index value of array to tempH 
    	int j = i - 1; //Assigns j with the value in the index of i - 1
    	while(j >= 0 && array[j] > tempH) { //This while loop determines which values in the array need to be inserted into a different part. 
    		array[j + 1] = array[j]; //Because the value of the lower indexed variable is higher than that of tempH, tempH's index value is changed to whatever is in the element array[j]
    		j = j - 1; //iteration of the value of j 
    	}
    	array[j + 1] = tempH; //Reinsert the value sorted in the tempH variable into its spot in the array
    }
    public static void mergeInsertionHalves (int[] array, int[] temp, int lS, int rE) {
    	int lE = (lS + rE)/2; //determines the end point of the left array
    	int rS = lE + 1; //determines the starting point of the right array
    	int size = rE - lS + 1; //determines the size of the total array
    	
    	int left = lS; //Initializes the left start point as left
    	int right = rS; //Initializes the right start point as right
    	int index = lS; //Initializes the index using the value in left start 
    	
    	while (left <= lE && right <= rE) { //runs the while loop as long as there is a element left to compare in both the right and the left arrays
    		if(array[left] <= array[right]) { //if the value in the element on the left is greater than the value in the element on the right
    			temp[index] = array[left]; //assigns the value in the element of the left array into the temp array at that same index value
    			left++; //increments left by 1
    		}
    		else { //if the value in the element on the right is greater than the value in the element on the left
    			temp[index] = array[right]; //assigns the value in the element of the right array into the temp array at that same index value
    			right++; //increments right by 1
    		}
    		index++; //increments index by 1
    	}
    	
    	System.arraycopy(array, left, temp, index, lE - left + 1); //Uses the command .arraycopy to copy array (starting from left start) into temp (starting from index) for lE - left + 1 number of elements
    	System.arraycopy(array, right, temp, index, rE - right + 1); //Uses the command .arraycopy to copy array (starting from right start) into temp (starting from index) for rE - right + 1 number of elements
    	System.arraycopy(temp, lS, array, lS, size); //Uses the command .arraycopy to copy temp (starting from left start) into array (starting from left start) for size number of elements
    
    }
    
    //Run Algorithms using temp arrays in order to not create any problems with the single array input 
    public static int[] runMergeSort (int[] array) {
		int[] temp = new int[array.length]; //Initializes a 1D array called temp 
    	for(int i = 0; i < array.length; i++) { //For loop runs through each element in array 
    		temp[i] = array[i]; //Copies the values from array into temp
    	}
    	long timeS = System.nanoTime(); //Collects the time before the sorting method is ran
    	mergeSort(temp); //runs the mergeSort method while passing an array (temp)
    	long timeE = System.nanoTime(); //Collects the time after the sorting method is ran
    	long timeDelta = timeE - timeS; //Determines the time it takes to run the sorting method
    	System.out.println("The time elapsed when sorting using Merge Sort is " + timeDelta + " nanoseconds"); //Prints out the time taken to run the sorting method
    	return temp; //Returns the array (temp)
    }
    public static int[] runQuickSort (int[] array) {
    	int[] temp = new int[array.length]; //Initializes a 1D array called temp
    	for(int i = 0; i < array.length; i++) { //For loop runs through each element in array
    		temp[i] = array[i]; //Copies the values from array into temp 
    	}
    	long timeS = System.nanoTime(); //Collects the time before the sorting method is ran 
    	quickSort(temp); //runs the quickSort method while passing an array (temp)
    	long timeE = System.nanoTime(); //Collects the time after the sorting method is ran
    	long timeDelta = timeE - timeS; //Determines the time it takes to run the sorting method 
    	System.out.println("The time elapsed when sorting using Quick Sort is " + timeDelta + " nanoseconds"); //Prints out the time taken to run the sorting method
    	return temp; //Returns the array (temp)
    }
    public static int[] runInsertionSort (int[] array) {
		int[] temp = new int[array.length]; //Initializes a 1D array called temp
    	for(int i = 0; i < array.length; i++) { //For loop runs through each element in array 
    		temp[i] = array[i]; //Copies the values from array into temp 
    	}
    	long timeS = System.nanoTime(); //Collects the time before the sorting method is ran
    	insertionSort(temp); //runs the insertionSort method while passing an array (temp)
    	long timeE = System.nanoTime(); //Collects the time after the sorting method is ran
    	long timeDelta = timeE - timeS; //Determines the time it takes to run the sorting method 
    	System.out.println("The time elapsed when sorting using Insertion Sort is " + timeDelta + " nanoseconds"); //Prints out the time taken to run the sorting method 
    	return temp; //Returns the array (temp)
    }
    public static int[] runMergeInsertionSort (int[] array) {
		int[] temp = new int[array.length]; //Initializes a 1D array called temp 
    	for(int i = 0; i < array.length; i++) { //For loop runs through each element in array 
    		temp[i] = array[i]; //Copies the value from array into temp 
    	}
    	long timeS = System.nanoTime(); //Collects the time before the sorting method is ran
    	mergeInsertionSort(temp); //runs the mergeInsertion method while passing an array (temp)
    	long timeE = System.nanoTime(); //Collects the time after the sorting method is ran
    	long timeDelta = timeE - timeS; //Determines the time it takes to run the sorting method
    	System.out.println("The time elapsed when sorting using MergeInsertion Sort is " + timeDelta + " nanoseconds"); //Prints out the time taken to run the sorting method 
    	return temp; //Returns the array (temp)
    }
    
    //Print the sorted array
    public static void printArray(int[] array) {

    	checkArray(array); //Calls the checkArray method while passing an array (array) 
    	
		for(int i = 0; i < array.length; i++) { //This for loop is used to print out the sorted array
    		System.out.print(array[i] + " ");
    	}
		System.out.println(""); //This line is used to create a new line 

	}

    public static void checkArray(int[] array) {
    	boolean result = false; //Initializes a boolean (result) with the value of false
		for(int i = 1; i < array.length; i++) { //This for loop is used to check every single element in the array
			if(array[i] < array[i - 1]) { //if statement used to determine if the array is not sorted properly
				result = false; //if the array is not sorted properly then the value of result will be changed to false
			}
			else {
				result = true; //if the array is sorted properly then the value of result will be changed to true
			}
		}
		if(result == false) { //if statement is used to print out a message for either a correctly sorted array or incorrectly sorted array 
			System.out.println("The array was sorted incorrectly!! Fix the code!!"); //Prints out the message for an incorrectly sorted array
		}
		else {
			System.out.println("The array was sorted correctly!!"); //Prints out the message for an correctly sorted array
		}
    }
    
    public static int[] createRandom(int size) {
    	int[] array = new int[size]; //Initializes a 1D array at the size of the value in int (size) 
		for(int i = 0; i < array.length; i++) { //This for loop is used to go through each element in an array (array)
			int random = (int )(Math.random() * 100 + 1); //Initializes an int (random) and assigns it with a randomly generated value from 1-100
			array[i] = random; //Assigns the randomly generated number to an element in the array at index i
		}
		for(int i = 0; i < array.length; i++) { //This for loop is used to print out the values of the array by printing out each element at time
    		System.out.print(array[i] + " ");
    	}
		System.out.println(""); //Used to create a new line
		return array; //returns the array (array)
    }
    // Main method
    public static void main (String[] args) {
        int[] array = createRandom(Integer.parseInt(args[0])); //creates an array of randomly generated numbers (from 1-100)
		int[] mergedArray; //Initializes an array to hold the sorted arrays
		mergedArray = runMergeSort(array); //runs the mergeSorting process 
		printArray(mergedArray); //prints out array sorted using merge sort
		mergedArray = runQuickSort(array); //runs the quickSort process
		printArray(mergedArray); //prints out array sorted using quick sort
		mergedArray = runInsertionSort(array); //runs the insertionSort process
		printArray(mergedArray); //prints out array sorted using insertion sort
		mergedArray = runMergeInsertionSort(array); //runs the mergeInsertionSort process
		printArray(mergedArray); //prints out array sorted using mergeInsertion sort
    }
}