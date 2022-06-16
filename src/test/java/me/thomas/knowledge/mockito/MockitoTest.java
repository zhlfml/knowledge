/**
 * LY.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package me.thomas.knowledge.mockito;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * MockitoTest
 *
 * @author xinsheng2.zhao
 * @version Id: MockitoTest.java, v 0.1 2020/12/21 1:38 PM xinsheng2.zhao Exp $
 */
public class MockitoTest {

    @Test
    public void mockitoSpy() {
        Stack<String> real = new Stack<>();
        Stack<String> spy = spy(real);
        doReturn("foo")
                .when(spy)
                .pop();
        assertEquals("foo", spy.pop());
    }
}
