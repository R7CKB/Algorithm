package Lecture4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: HeapGreater
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/8 19:12
 * @Description: 加强堆的实现
 */

/**
 * 1）建立反向索引表
 * 2）建立比较器
 * 3）核心在于各种结构相互配合，非常容易出错
 * @param <T>
 */
public class HeapGreater<T> {
    // 使用链表实现的堆结构
    private ArrayList<T> heap;
    // 元素到索引的映射
    private HashMap<T, Integer> indexMap;
    // 堆的大小
    private int heapSize;
    // 元素比较器
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    // 多了一个自定义的方法,判断某个元素是否在堆中
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    // 和系统堆一样,返回堆顶元素
    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        // 先把元素添加到堆中
        heap.add(obj);
        // 再把元素的索引记录到映射中
        indexMap.put(obj, heapSize);
        // 最后,进行调整使其符合堆的性质
        heapInsert(heapSize++);
    }

    public T pop() {
        // 先得到堆顶元素
        T ans = peek();
        // 然后把堆顶元素和最后一个元素交换,
        swap(0, heapSize - 1);
        // 并更新映射,将该元素从映射中删除
        indexMap.remove(ans);
        // 将这个元素移除
        heap.remove(--heapSize);
        // 最后,进行堆的调整使其符合堆的性质
        heapify(0);
        return ans;
    }

    // 还支持删除某个元素
    public void remove(T obj) {
        // 先得到最后一个元素的位置
        T replace = heap.get(heapSize - 1);
        // 得到删除元素的索引
        int index = indexMap.get(obj);
        // 从映射中删除该元素
        indexMap.remove(obj);
        // 删除数组的最后一个元素
        heap.remove(--heapSize);
        // 如果删除的元素不是最后一个元素,则用最后一个元素替换之
        if (obj != replace) {
            // 将删除的元素更新成最后一个元素
            heap.set(index, replace);
            // 更新映射
            indexMap.put(replace, index);
            // 调整堆使其符合堆的性质
            resign(replace);
        }
    }

    // 调整堆的某个元素,使其符合堆的性质
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    // 返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    // 堆的插入操作,将元素插入到合适的位置,使其符合堆的性质
    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 堆的调整操作,使其符合堆的性质
    private void heapify(int index) {
        // 过程和基本的堆一样,还是那句话,一般使用左子树而不是右子树
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smallest = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            smallest = comp.compare(heap.get(smallest), heap.get(index)) < 0 ? smallest : index;
            if (smallest == index) {
                break;
            }
            swap(smallest, index);
            index = smallest;
            left = index * 2 + 1;
        }
    }

    // 交换两个元素的位置
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        // 因为使用数组,不需要额外变量交换元素,直接设置数组中的元素即可
        heap.set(i, o2);
        heap.set(j, o1);
        // 更新映射
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }
}
