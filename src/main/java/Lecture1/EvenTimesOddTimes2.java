package Lecture1;

// 一个数组中有两种数出现了奇数次, 其他数都出现了偶数次, 怎么找到并打印这两种数 (出现奇数次).
public class EvenTimesOddTimes2 {
    // 先异或所有的数,那么结果eor就是这两个数的异或(这两种数一定不相等(相等的话这个数就存在偶数次了)) ->
    // 所以eor一定不为0 ->
    // 这两个数一定有一位是不相同的(在二进制中)(因为异或相同为0) ->
    // 使用ExtractRightestOne方法提取出eor的最右侧的1,它也为一个数(rightOne) ->
    // 使用上面那个数再与数组中所有的数相与(这是对数据进行分组,分出这个位置为1的数和不为一的数)进行分组 ->
    // 这样就能得到两组数,然后使用一个变量(eor'(初始为0))和分组中的每一个数进行异或 ->
    // 得到这两个数中的一个(因为其他数都为偶数,只有这两个数为奇数) ->
    // 再使用一个变量eor'异或eor即为另一个数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        // eor = a ^ b
        // eor != 0
        // eor必然有一个位置是1
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0; // eor'
        for (int j : arr) {
            // 如果这两个数的最后一位都为1,那么这两个数的异或会将最后一位变为0,
            // 所以rightOne可以区分出这两个数,
            // 如果这两个数的最后一位都为0,那么这两个数的异或后最后一位还是0,
            // 所以rightOne还是可以区分出这两个数,
            if ((j & rightOne) != 0) { //代表这一个位置上有1
                onlyOne ^= j;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        int[] arr = {9,9,9,9,5,5,5,5,3,3,3,3,4,4,4,6,6,6,6,7,7,7,7,8,8,8};
        printOddTimesNum2(arr);
    }
}
