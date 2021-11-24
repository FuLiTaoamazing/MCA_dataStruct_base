package com.flt;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName: Test.java
 * @author: FULITAO
 * @description:一个数组有两个不同的数出现了奇数次 找出来
 * @createTime: 2021年07月16日 10:02:00
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis() - (sdf.parse("2021-08-01 13:26:00").getTime());
        System.out.println((double)l/3600000);
    }
}
