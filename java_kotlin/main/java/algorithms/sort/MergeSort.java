package algorithms.sort;

import java.util.Arrays;

@SuppressWarnings("ManualArrayCopy")
public class MergeSort {

  public static void mergeSort(int[] array) {
    mergeSortRecursive(array, 0, array.length);
  }

  public static void mergeSortRecursive(int[] array, int left, int right) {
    if (right - left > 1) {
      int middle = (right + left) / 2;
      mergeSortRecursive(array, left, middle);
      mergeSortRecursive(array, middle, right);
      merge(array, left, middle, right);
    }
  }

  public static void merge(int[] array, int left, int middle, int right) {
    int[] leftArray = new int[middle - left + 1];
    int[] rightArray = new int[right - middle + 1];

    for (int i = left; i < middle; i++) {
      leftArray[i - left] = array[i];
    }
    for (int i = middle; i < right; i++) {
      rightArray[i - middle] = array[i];
    }

    leftArray[leftArray.length - 1] = Integer.MAX_VALUE;
    rightArray[rightArray.length - 1] = Integer.MAX_VALUE;

    int i = 0;
    int j = 0;
    int k = left;
    while (k < right) {
      if (leftArray[i] <= rightArray[j]) {
        array[k] = leftArray[i];
        i++;
      } else {
        array[k] = rightArray[j];
        j++;
      }
      k++;
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[]{4, 1, 3, 2, 4, 5};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
