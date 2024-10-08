package Lecture1;

public class SortInsertSort {
    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3, 5, 5, 5, 9, 7, 4, 5, 5, 3, 4, 1, 2, 3, 1};
        int[] sortedArr = insertSort(arr);
        for (int j : sortedArr) {
            System.out.print(j + " ");
        }
    }
}
