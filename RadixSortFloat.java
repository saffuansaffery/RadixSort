import java.util.ArrayList;
import java.util.Arrays;

public class RadixSortFloat {
  public static float decimalPlaces = 0;
  public static void radixSorting(float[] input){

    ArrayList<Integer>[] arrayOne = new ArrayList[10];
    ArrayList<Integer>[] arrayTwo = new ArrayList[10];
    int[] tempArr = new int[input.length];

    for (int i = 0; i < 10; i++) {
        arrayOne[i] = new ArrayList<Integer>();
        arrayTwo[i] = new ArrayList<Integer>();
    }

    wholeNumber(input, tempArr);
    int tmp, placement = 1, length = String.valueOf(Arrays.stream(tempArr).max().getAsInt()).length();
    boolean turn = true;

    for(int i : tempArr){
        tmp = i / placement;
        arrayOne[tmp % 10].add(i);
    }
    placement *= 10;
    printList(arrayOne);
    
    for(int i = 1; i < length; i++){
        ContinueSorting(arrayOne, arrayTwo, turn, placement);
        placement *= 10;
        turn = (!turn);
    }

    if(turn == false){ 
      int y = 0;
      for (int x = 0; x < 10; x++) {
          for (int i : arrayTwo[x]) {
              input[y] = i;
              y++;
          }
      } 
    } else {
      int y = 0;
      for (int x = 0; x < 10; x++) {
          for (int i : arrayOne[x]) {
              input[y] = i;
              y++;
          }
      } 
    }
  }

  public static void ContinueSorting(ArrayList<Integer>[] array1, ArrayList<Integer>[] array2, boolean turn, int placement){
    int tmp;
    if(turn == true){
        for (int x = 0; x < 10; x++) {
            for (int i : array1[x]) {
                tmp = i / placement;
                array2[tmp % 10].add(i);
            }
            array1[x].clear();
        }
        printList(array2);
    } else {
        for (int x = 0; x < 10; x++) {
            for (int i : array2[x]) {
                tmp = i / placement;
                array1[tmp % 10].add(i);
            }
            array2[x].clear();
        }
        printList(array1);
    }
  }

  public static void printList(ArrayList<Integer>[] array){
    for(int x = 0; x < 10; x++){
        for(int i : array[x]) {
            System.out.print( i/(Math.pow(10, decimalPlaces)) + " ");
        }
    }
    System.out.print("\n");
  }

  public static void wholeNumber(float[] input, int[] tempArr) {
    for(float i : input){
        String FloatStr = Float.toString(i);
        String[] strArr = FloatStr.split("\\.");
        if(strArr[1].length() > decimalPlaces){ decimalPlaces = strArr[1].length();};
    }
    for(int x = 0; x < input.length; x++){
            input[x] *= Math.pow(10, decimalPlaces);
            tempArr[x] = (int)input[x];
    }
  }

  public static void main(String[] args) {

    float[] input = { 25.921f, 87.3f, 87.1f, 87.22f, 4019.1f, 0.1f };
    System.out.println("Unsorted array : " + Arrays.toString(input));
    radixSorting(input);
    for(int i = 0; i < input.length; i++){ 
      input[i] /= (Math.pow(10, decimalPlaces)); 
    }
    System.out.println("Sorted array : " + Arrays.toString(input));
  }
};