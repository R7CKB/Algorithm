package Lecture4;

import java.util.PriorityQueue;

/**
 * @Title: ShowHeapGreater
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/8 19:13
 * @Description: 加强堆的使用方法及演示
 */
public class ShowHeapGreater {
    // 看这个测试！
    // 每一句都看懂
    public static void main(String[] args) {
        // 展示非基础类型的用法
        Student s1 = new Student(17, "A同学");
        Student s2 = new Student(10, "B同学");
        Student s3 = new Student(29, "C同学");
        Student s4 = new Student(33, "D同学");
        Student s5 = new Student(54, "E同学");
        Student s6 = new Student(93, "F同学");
        // 生成一个加强堆
        // 排序策略是年龄小的放在堆顶，年龄小根堆
        HeapGreater<Student> heap1 = new HeapGreater<>((a, b) -> a.age - b.age);
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>((a, b) -> a.age - b.age);
        // 把所有学生加入堆
        heap1.push(s1);
        heap1.push(s2);
        heap1.push(s3);
        heap1.push(s4);
        heap1.push(s5);
        heap1.push(s6);
        studentPriorityQueue.add(s1);
        studentPriorityQueue.add(s2);
        studentPriorityQueue.add(s3);
        studentPriorityQueue.add(s4);
        studentPriorityQueue.add(s5);
        studentPriorityQueue.add(s6);
        // 依次弹出所有学生
        // 加入之后
        // 可以把某个同学的年龄改了
        // 比如把s5，也就是E同学
        // 年龄从54改成了4
        s5.age = 4;
        // 此时堆被破坏了，因为你擅自改了一个同学的年龄
        // 只需要调用resign方法，就能让堆恢复成年龄小根堆
        // 而且复杂度是O(log N)，很快的
        // 系统提供的堆做不到的，加强堆可以
        heap1.resign(s5);
        // 系统提供的堆也可以做到,就是需要重新加入这个同学
        // 因为你改了年龄，所以需要重新加入
        studentPriorityQueue.remove(s5);
        studentPriorityQueue.add(s5);
        // 依次弹出所有学生
        // 会发现从年龄小到年龄大依次弹出
        // 说明堆是正确的
        while (!heap1.isEmpty()) {
            Student cur = heap1.pop();
            System.out.println("年龄 : " + cur.age + " , 名字 : " + cur.name);
        }

        System.out.println("======================");

        while (!studentPriorityQueue.isEmpty()) {
            Student cur = studentPriorityQueue.poll();
            System.out.println("年龄 : " + cur.age + " , 名字 : " + cur.name);
        }

        System.out.println("======================");

        // 现在展示非基础类型的加强堆用法
        int[] arr = {3, 3, 2, 5, 3};
        // arr[0] == 3
        // arr[1] == 3
        // arr[2] == 2
        // arr[3] == 5
        // arr[4] == 3
        // 每个位置的数字一定会自带一个下标，这是一定的!
        // 任何基础类型的元素，天生一定会自带一些类似下标的身份信息的！这是一定的！
        // 生成一个加强堆
        // 加强堆里只放下标即可，因为通过下标可以找到数字
        // 排序策略是 :
        // 数字小的下标，在堆顶
        HeapGreater<Integer> heap2 = new HeapGreater<>((i, j) -> arr[i] - arr[j]);
        PriorityQueue<Integer> indexPriorityQueue = new PriorityQueue<>((i, j) -> arr[i] - arr[j]);

        // 把数组所有的下标加入堆
        // 就等于加入了所有数字
        heap2.push(0);
        heap2.push(1);
        heap2.push(2);
        heap2.push(3);
        heap2.push(4);
        indexPriorityQueue.add(0);
        indexPriorityQueue.add(1);
        indexPriorityQueue.add(2);
        indexPriorityQueue.add(3);
        indexPriorityQueue.add(4);

        // 加入之后
        // 可以把某个下标上的数字改了
        // arr[1]原来是3，现在变成了-9
        arr[1] = -9;
        // 此时堆被破坏了，因为你擅自改了一个下标的数字
        // 只需要调用resign方法，就能让堆恢复
        // 而且复杂度是O(log N)，很快的
        // 系统提供的堆做不到的，加强堆可以
        // 调用resign方法
        heap2.resign(1);
        // 系统提供的堆也可以做到,就是需要重新加入这个下标
        // 因为你改了数字，所以需要重新加入
        indexPriorityQueue.remove(1);
        indexPriorityQueue.add(1);
        // 依次弹出所有下标
        // 会发现下标上的数字越小，下标越早弹出
        // 说明堆是正确的
        while (!heap2.isEmpty()) {
            int curIndex = heap2.pop();
            System.out.println("下标 : " + curIndex + " , 数字 :" + arr[curIndex]);
        }

        System.out.println("========================");
        while (!indexPriorityQueue.isEmpty()) {
            int curIndex = indexPriorityQueue.poll();
            System.out.println("下标 : " + curIndex + " , 数字 :" + arr[curIndex]);
        }
    }

    // 一个自己定义的非基础类型
    public static class Student {
        public int age;
        public String name;

        public Student(int a, String n) {
            age = a;
            name = n;
        }
    }
}
