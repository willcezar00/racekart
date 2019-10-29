package org.william.racekart.converter;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class NumberConverterTest {

    @Test
    public void convertIntegerTest() throws Exception {
        NumberConverter<Integer> converter = new NumberConverter<>(Integer.class);
        Integer expected =  10;
        Integer result = converter.convert("10");
        assertEquals(expected,result);
    }

    @Test
    public void convertIntTest() throws Exception {
        NumberConverter<Integer> converter = new NumberConverter<>(Integer.TYPE);
        int expected =  10;
        int result = converter.convert("10");
        assertEquals(expected,result);
    }

    @Test(expected = InvocationTargetException.class)
    public void convertIntParseErrorTest() throws Exception {
        NumberConverter<Integer> converter = new NumberConverter<>(Integer.TYPE);
        converter.convert("12313e");
    }

    @Test(expected = UnsupportedTypeConverterException.class)
    public void convertUnsupportedTypeConverterExceptionTest() throws Exception {
        NumberConverter<NumberConverterTest> converter = new NumberConverter<>(NumberConverterTest.class);
    }

}
