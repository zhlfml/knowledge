package me.thomas.knowledge.bit;

/**
 * 还有点问题
 *
 * @author zhaoxinsheng
 * @date 2020/04/25 Saturday
 */
public class PrintFloatBit {

    public static void main(String[] args) {
        int bit = 1;
        int value = Float.floatToIntBits(-0.1f);
        System.out.println(Integer.toBinaryString(value));
        System.out.println(Integer.toBinaryString(-1));
        char[] chars = new char[32];
        for (int i = 31; i >= 0; i--) {
            chars[i] = (value & bit) > 0 ? '1' : '0';
            bit <<= 1;
        }
        System.out.println(new String(chars));
    }
}
