package Lecture3;

/**
 * @Title: DoubleLinkedListQuickSort
 * @Author R7CKB
 * @Package Lecture3
 * @Date 2024/10/3 18:57
 * @description: 双向链表的随机快速排序
 */

// 双向链表的随机快速排序
// 课上没有讲，因为这是群里同学问的问题
// 作为补充放在这，有需要的同学可以看看
// 和课上讲的数组的经典快速排序在算法上没有区别
// 但是coding需要更小心
public class DoubleLinkedListQuickSort {

    // 双向链表的数据结构
    public static class Node {
        public int value;
        public Node last;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static Node quickSort(Node head) {
        // 如果链表为空,返回空
        if (head == null) {
            return null;
        }
        // N为链表的长度,同时也为链表中节点的个数
        int N = 0;
        Node cur = head;
        Node tail = null;
        // 遍历得出链表的长度
        // 同时得到链表的尾节点(e)
        while (cur != null) {
            N++;
            tail = cur;
            cur = cur.next;
        }
        // 用链表的头节点(head),尾节点(tail)和链表的长度(N)调用process函数
        // 返回最后的头节点
        return process(head, tail, N).h;
    }

    // 我们使用一个额外的数据结构HeadTail来表示双向链表的头尾
    // 双向链表的头尾
    public static class HeadTail {
        public Node h;
        public Node t;

        public HeadTail(Node head, Node tail) {
            h = head;
            t = tail;
        }
    }

    // Info数据结构:
    // lh,lt,ls : 小于pivot的部分的头、尾、节点个数
    // gh,gt,gs : 大于pivot的部分的头、尾、节点个数
    // eh,et : 等于pivot的部分的头、尾
    public static class Info {
        public Node lh;
        public Node lt;
        public int ls;
        public Node gh;
        public Node gt;
        public int gs;
        public Node eh;
        public Node et;

        public Info(Node lH, Node lT, int lS, Node gH, Node gT, int gS, Node eH, Node eT) {
            lh = lH;
            lt = lT;
            ls = lS;
            gh = gH;
            gt = gT;
            gs = gS;
            eh = eH;
            et = eT;
        }
    }

    // L...R是一个双向链表的头和尾,
    // L的last指针指向null，R的next指针指向null
    // 也就是说L的左边没节点，R的右边也没节点
    // 就是一个正常的双向链表，一共有N个节点
    // 将这一段用随机快排的方式排好序
    // 返回排好序之后的双向链表的头和尾(HeadTail)
    public static HeadTail process(Node L, Node R, int N) {
        // 如果给定的链表头为空，返回空
        if (L == null) {
            return null;
        }
        // 如果给定的链表头和尾是同一个节点，返回这个节点的HeadTail(头尾指针)
        if (L == R) {
            return new HeadTail(L, R);
        }
        // L..R上不只一个节点
        // 随机得到一个随机下标
        // 范围是[0,N-1]
        int randomIndex = (int) (Math.random() * N);
        // 根据随机下标得到随机节点
        Node randomNode = L;
        // 链表的下标也是从0开始的
        while (randomIndex-- != 0) {
            randomNode = randomNode.next;
        }
        // 把随机节点从原来的环境里分离出来(其实就是链表删除这个节点)
        // 比如 a(L) -> b -> c -> d(R), 如果randomNode = c，那么调整之后
        // a(L) -> b -> d(R), c会被挖出来，randomNode = c
        // 如果randomNode = 头节点或者尾节点
        if (randomNode == L || randomNode == R) {
            if (randomNode == L) {
                L = randomNode.next;
                L.last = null;
            } else {
                // 删除尾节点
                randomNode.last.next = null;
            }
        } else { // randomNode一定是中间的节点
            // 调整链表,并不难，就是把randomNode的前后节点的指针调换一下
            randomNode.last.next = randomNode.next;
            randomNode.next.last = randomNode.last;
        }
        // 单独抽出来这个随机节点randomNode
        randomNode.last = null;
        randomNode.next = null;
        // 开始进行排序
        Info info = partition(L, randomNode);
        // <randomNode的部分去排序
        // lht代表<randomNode的部分的头尾
        HeadTail lht = process(info.lh, info.lt, info.ls);
        // >randomNode的部分去排序
        // rht代表>randomNode的部分的头尾
        HeadTail rht = process(info.gh, info.gt, info.gs);
        // 左部分排好序、右部分排好序
        // 把它们串在一起
        if (lht != null) {
            // 左部分的尾节点的next指针指向等于的部分的头节点
            lht.t.next = info.eh;
            // 等于的部分的头节点的last指针指向左部分的尾节点
            info.eh.last = lht.t;
        }
        if (rht != null) {
            // 等于的部分的尾节点的next指针指向右部分的头节点
            info.et.next = rht.h;
            // 右部分的头节点的last指针指向等于的部分的尾节点
            rht.h.last = info.et;
        }
        // 返回排好序之后总的头和总的尾
        // 这里还需要判断一下左右部分是否为空，如果为空，就返回等于的部分的头和尾
        Node h = lht != null ? lht.h : info.eh;
        Node t = rht != null ? rht.t : info.et;
        return new HeadTail(h, t);
    }


    // (L....一直到空)，是一个双向链表
    // pivot是一个不在(L....一直到空)的独立节点，它作为划分值
    // 根据荷兰国旗问题的划分方式，把(L....一直到空)划分成:
    // <pivot 、 =pivot 、 >pivot 三个部分，然后把pivot融进=pivot的部分
    // 比如 4(L)->6->7->1->5->0->9(Tail)->null pivot=5(这个5和链表中的5，是不同的节点)
    // 调整完成后:
    // 4->1->0 小于的部分
    // 5->5 等于的部分
    // 6->7->9 大于的部分
    // 三个部分是断开的
    // 然后返回Info：
    // 小于部分的头、尾、节点个数 : lh,lt,ls
    // 大于部分的头、尾、节点个数 : rh,rt,rs
    // 等于部分的头、尾 : eh,et
    // L是头节点，pivot是划分的节点
    public static Info partition(Node L, Node pivot) {
        Node lh = null;
        Node lt = null;
        int ls = 0;
        Node gh = null;
        Node gt = null;
        int gs = 0;
        Node eh = pivot;
        Node et = pivot;
        // 创建一个临时节点tmp
        Node tmp = null;
        // 整个while过程就是子啊构建一个新的链表，把小于、大于、等于的节点分开
        // 然后返回Info
        while (L != null) {
            // 先把L节点从原来的环境里挖出来
            // 然后重新插入到新的链表中
            // 这里的挖出来和插入，就是链表删除和插入的过程
            tmp = L.next;
            L.next = null;
            L.last = null;
            // 小于和大于的逻辑是一样的
            if (L.value < pivot.value) {
                ls++;
                if (lh == null) {
                    lh = L;
                    lt = L;
                } else {
                    lt.next = L;
                    L.last = lt;
                    lt = L;
                }
            } else if (L.value > pivot.value) {
                gs++;
                if (gh == null) {
                    gh = L;
                    gt = L;
                } else {
                    gt.next = L;
                    L.last = gt;
                    gt = L;
                }
            } else {
                // 等于的逻辑,等于区域没有计算节点个数
                et.next = L;
                L.last = et;
                et = L;
            }
            // L 不断往后移动
            L = tmp;
        }
        return new Info(lh, lt, ls, gh, gt, gs, eh, et);
    }
}
