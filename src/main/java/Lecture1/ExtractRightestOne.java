package Lecture1;

public class ExtractRightestOne {
    public static void main(String[] args) {
        int n = 23; // 二进制23 = 0001 0111
        int rightestOne = n & (~n | 1);
        System.out.println(rightestOne);
    }
}
