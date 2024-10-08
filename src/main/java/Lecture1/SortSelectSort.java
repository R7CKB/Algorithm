package Lecture1;

public class SortSelectSort {
    public static int[] selectSort(int[] arr) {
        // Check if the array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return arr;
        }
        // Find the minimum element in the array and swap it with the first element
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
        return arr;
    }

    // 当一个排序是不稳定的时候,我们不可以使用 ^(异或) 来交换两个值
    // 因为异或会使得两个相同的数交换之后变为0,这会导致排序的结果不正确
    // 所以我们需要使用三次赋值的方法来交换两个值
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 3, 9, 1, 7, 4, 6};
        int[] sortedArr = selectSort(arr);
        for (int j : sortedArr) {
            System.out.print(j + " ");
        }
    }
}
