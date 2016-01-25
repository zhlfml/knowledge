package me.thomas.knowledge.algorithm.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 背包算法：动态规划
 *
 * Created by thomas on 6/6/14.
 */
public class BagSolution {

    public static void main(String[] args) {
        Bag bag = new Bag(9);
        List<Gemstone> gemstones = Arrays.asList(
                new Gemstone(20, 5),
                new Gemstone(10, 4),
                new Gemstone(12, 3),
                new Gemstone(9, 2)
        );

        // 定义二维数组来存放所有的情况: 宝石从少到多，剩余容量从多到少。
        // 因为所有情况包括一颗宝石都不存放的情况，所以第一维数组包括0[], 1[], 2[], ..., gemstones.size()[]等情况。
        // 所以第一维数组的大小为gemstones.size() + 1，同理第二维数组的大小为bag.getCapacity() + 1。
        int table[][] = new int[gemstones.size() + 1][bag.getCapacity() + 1];
        for (int i = 0, len = gemstones.size(); i <= len; i++) { // i表示前i个宝石
            for (int j = bag.getCapacity(); j > 0; j--) { // j表示当前背包剩余容量
                if (i == 0) {
                    table[i][j] = 0;
                } // j>=gemstones.get(i-1).getWeight()表示第i个宝石可以放入背包的剩余容量中
                else if (j >= gemstones.get(i - 1).getWeight()) {
                    // 如果放入第i个宝石的总价值大于不放入时的价值。table[i - 1][j]表示不计当前宝石，在这之前放入背包宝石的最大价值。
                    // 当然此时前i-1个宝石的剩余容量为j-gemstones.get(i-1).getWeight()
                    if (table[i - 1][j - gemstones.get(i - 1).getWeight()] + gemstones.get(i - 1).getPrice() > table[i - 1][j]) {
                        table[i][j] = table[i - 1][j - gemstones.get(i - 1).getWeight()] + gemstones.get(i - 1).getPrice();
                    } // 如果强硬放入当前的宝石而导致背包中总的价值反而更低，也就说明前i个宝石，综合下来最大的价值和前i-1个宝石的价值相同。
                    else {
                        table[i][j] = table[i - 1][j];
                    }
                }
            }
        }

        // table[gemstones.size()][bag.getCapacity()]就是计算出的最大价值
        bag.setValue(table[gemstones.size()][bag.getCapacity()]);

        // 这里检查哪些宝石被放入了包中
        int cap = bag.getCapacity();
        // 从最后一个开始检查又没有放入背包
        for (int i = gemstones.size(); i > 0; i--) {
            // 如果放了第i个宝石的价值大于未放入时的价值，说明这个宝石已经放入了背包中。
            if (table[i][cap] > table[i - 1][cap]) {
                gemstones.get(i - 1).setFlag(true);
                // 装入第i个宝石后背包能装入的体积就只剩下cap - gemstones.get(i - 1).getWeight();
                cap -= gemstones.get(i - 1).getWeight();
            }
        }
        for (int i = 0; i < gemstones.size(); i ++) {
            System.out.print(gemstones.get(i).getFlag() + "\t");
        }
        System.out.println(bag.getValue());
    }

}

class Bag {
    // 总价值
    int value;
    // 总容量
    int capacity;

    /**
     * @param capacity 总容量
     */
    public Bag(int capacity) {
        this.capacity = capacity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class Gemstone {
    // 价格
    int price;
    // 重量
    int weight;
    // 存放标记: 0 不入背包, 1 存入背包
    boolean flag = false;

    /**
     *
     * @param price 价格
     * @param weight 重量
     */
    public Gemstone(int price, int weight) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
