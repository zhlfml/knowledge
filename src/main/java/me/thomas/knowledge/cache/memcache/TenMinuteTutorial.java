package me.thomas.knowledge.cache.memcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/1/20.
 */
public class TenMinuteTutorial {

    public static void main(String[] args) {
        String[] servers = new String[] {
            "127.0.0.1:11211"
        };
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(25);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketConnectTO(3000);
        pool.setAliveCheck(true);
        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
        pool.initialize();

        MemCachedClient client = new MemCachedClient();
        client.set("name", "thomas");
        client.prepend("name", "hello ");
        System.out.println(client.get("name"));

        List<Gemstone> gemstones = new ArrayList<>();
        gemstones.add(new Gemstone(12, 4));
        gemstones.add(new Gemstone(11, 2));
        gemstones.add(new Gemstone(16, 5));
        gemstones.add(new Gemstone(14, 3));

        client.replace("gemstones", gemstones);

        List<Gemstone> gemstonesCopy = (List<Gemstone>) client.get("gemstones");
        for (Gemstone gemstone : gemstonesCopy) {
            System.out.println(gemstone.getPrice() + "," + gemstone.getWeight());
        }
    }
}

/**
 * 放入memcache的对象必须能够序列化
 */
class Gemstone implements Serializable {

    private static final long serialVersionUID = -6956831926618247505L;
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
