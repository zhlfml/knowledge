package me.thomas.knowledge.algorithm.epic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise4Test {

    @Test(expected = IllegalArgumentException.class)
    public void itoa_illegal_1() {
        Exercise4.itoa(1750, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itoa_illegal_2() {
        Exercise4.itoa(1750, 37);
    }

    @Test
    public void itoa_1() {
        assertEquals("0", Exercise4.itoa(0, 2));
        assertEquals("0", Exercise4.itoa(0, 8));
        assertEquals("0", Exercise4.itoa(0, 10));
        assertEquals("0", Exercise4.itoa(0, 16));
        assertEquals("0", Exercise4.itoa(0, 36));
    }

    @Test
    public void itoa_2() {
        System.out.println(Exercise4.itoa(Integer.MIN_VALUE, 2));
        assertEquals("11011010110", Exercise4.itoa(1750, 2));
        assertEquals("3326", Exercise4.itoa(1750, 8));
        assertEquals("1750", Exercise4.itoa(1750, 10));
        assertEquals("6d6", Exercise4.itoa(1750, 16));
        assertEquals("1cm", Exercise4.itoa(1750, 36));
    }

    @Test
    public void itoa_3() {
        assertEquals("-11011010110", Exercise4.itoa(-1750, 2));
        assertEquals("-3326", Exercise4.itoa(-1750, 8));
        assertEquals("-1750", Exercise4.itoa(-1750, 10));
        assertEquals("-6d6", Exercise4.itoa(-1750, 16));
        assertEquals("-1cm", Exercise4.itoa(-1750, 36));
    }

    @Test
    public void itoa_4() {
        assertEquals("1111111111111111111111111111111", Exercise4.itoa(Integer.MAX_VALUE, 2));
        assertEquals("17777777777", Exercise4.itoa(Integer.MAX_VALUE, 8));
        assertEquals("2147483647", Exercise4.itoa(Integer.MAX_VALUE, 10));
        assertEquals("7fffffff", Exercise4.itoa(Integer.MAX_VALUE, 16));
        assertEquals("zik0zj", Exercise4.itoa(Integer.MAX_VALUE, 36));
    }

    @Test
    public void itoa_5() {
        System.out.println(Integer.toString(Integer.MIN_VALUE, 2));
        assertEquals("-10000000000000000000000000000000", Exercise4.itoa(Integer.MIN_VALUE, 2));
        assertEquals("-20000000000", Exercise4.itoa(Integer.MIN_VALUE, 8));
        assertEquals("-2147483648", Exercise4.itoa(Integer.MIN_VALUE, 10));
        assertEquals("-80000000", Exercise4.itoa(Integer.MIN_VALUE, 16));
        assertEquals("-zik0zk", Exercise4.itoa(Integer.MIN_VALUE, 36));
    }

}