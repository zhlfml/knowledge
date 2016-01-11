package me.thomas.knowledge.algorithm.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 背包算法：动态规划
 *
 * Created by thomas on 6/6/14.
 */
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
    int flag = 0;

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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

public class BagSolution {

    public static void main(String[] args) {
        Bag bag = new Bag(7);
        List<Gemstone> gemstones = Arrays.asList(
                new Gemstone(20, 5),
                new Gemstone(10, 4),
                new Gemstone(12, 3)
        );

        // 定义二维数组来存放所有的情况: 宝石从少到多，剩余容量从多到少。
        int table[][] = new int[gemstones.size() + 1][bag.getCapacity() + 1];
        for (int i = 0, len = gemstones.size(); i <= len; i++) { // i表示前i个宝石
            for (int j = bag.getCapacity(); j > 0; j--) { // j表示当前背包剩余容量
                if (i == 0) {
                    table[i][j] = 0;
                } else if (j >= gemstones.get(i - 1).getWeight()) { // j>=gemstones.get(i-1).getWeight()表示第i个宝石可以放入背包的剩余容量中
                    if (table[i - 1][j - gemstones.get(i - 1).getWeight()] + gemstones.get(i - 1).getPrice() > table[i - 1][j]) {
                        // 如果放入第i个宝石的总价值大于不放入时的价值。
                        // 当然此时前i-1个宝石的剩余容量为j-gemstones.get(i-1).getWeight()
                        table[i][j] = table[i - 1][j - gemstones.get(i - 1).getWeight()] + gemstones.get(i - 1).getPrice();
                    } else {
                        table[i][j] = table[i - 1][j];
                    }
                }
            }
        }

        // table[gemstones.size()][bag.getCapacity()]就是计算出的最大价值
        bag.setValue(table[gemstones.size()][bag.getCapacity()]);

        // 这里检查哪些宝石被放入了包中
        int cap = bag.getCapacity();
        for (int i = gemstones.size(); i > 0; i--) {
            if (table[i][cap] > table[i - 1][cap]) {
                gemstones.get(i - 1).setFlag(1);
                // 装入第i-1个宝石后背包能装入的体积就只剩下cap - gemstones.get(i - 1).getWeight();
                cap -= gemstones.get(i - 1).getWeight();
            }
        }
        for (int i = 0; i < gemstones.size(); i ++) {
            System.out.print(gemstones.get(i).getFlag() + "\t");
        }
        System.out.println(bag.getValue());
    }

}
