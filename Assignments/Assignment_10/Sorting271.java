public class Sorting271 {
//main method
    public static void main(String[] args) { //random numbers I picked for the array
        int[] array = {4, 7, 5, 16, 1, 2, 8, 20}; 
        System.out.println("The Unsorted array is: ");
        printArray(array);

        int[] sortedArray = sort(array);

        System.out.println("The Sorted array is: ");
        printArray(sortedArray);
    }

    public static int[] sort(int[] array) { 
        // Base case: if the array has one or no elements, it is already sorted
        if (array.length <= 1) { //If there is only one element in the array it is sorted. If there are no elements in the array it is sorted as well. This is because there are no other numbers it can be compared to.
            return array; //print the array if it is sorted.
        }

        // Find the midpoint to divide the array into two halves
        int midpoint = array.length / 2;  //in a merge sort the middle of the array's element will determine the left side and right side of the data. This will help figure out how to quickly sort the remaining elements by the middle of the data.
//to find the midpoint divide by two from the array length.
        // define left and right array groups.
        int[] left = new int[midpoint]; //this is the first half, before the midpoint, this will be the right side.
        int[] right = new int[array.length - midpoint]; //this will happen after the left side has been decided. The right side will be the what has not been taking to be on the left side and all elements that are not considered the midpoint.

        // Populate the left and right subarrays
        copyOriginalArray(array, 0, midpoint, left); //use the orginal array that has been divided, begin to copy at the first index on the left side, stop at the midpoint, store it in the left array.
        copyOriginalArray(array, midpoint, right.length, right); //use the orginal array that has been divided, begin to copy at the midpoint , copy all of the right side, store it in the right array.
        
        // Recursively sort the left and right subarrays
        left = sort(left); //start sorting the left side in order (Ascending)
        right = sort(right); //start sorting the right side in order (Ascending)

        // Merge the sorted left and right 
        return merge(left, right); //merge the sorted mini copied arrays together 
    }



    // Method to print the array
    private static void printArray(int[] array) {
        printArrayRecursive(array, 0); //Use the array and begin from the first element of the array.
        System.out.println(); //print out the array
    }

    // Recursive method to print array elements
    private static void printArrayRecursive(int[] array, int index) { 
        if (index < array.length) { //print out the elements that were found in the array
            System.out.print(array[index] + " ");
            printArrayRecursive(array, index + 1);
        } //here you want to print all of the elements in the array so you will start with the first element and use that index to get the second one from the array until all the emelents in the array have been displayed.
    }
}
