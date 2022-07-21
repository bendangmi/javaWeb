package com.atguigu.fruit.service.impl;

import org.junit.jupiter.api.Test;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/21-8:55
 */

public class test {
//    public static void main(String[] args) {
//        System.out.println("程序最早开始执行");
//        try{
//            System.out.println("try开始");
//            String str = null;
//            int length = str.length();
//            System.out.println("try完毕");
//        } catch (Exception e1){
//            System.out.println("空指针异常");
//        }
//        System.out.println("产生异常之后的所有程序");
//    }

    public static void main(String[] args) {
        final test1 test1 = new test1();
            test1.testb(1, 0);

        System.out.println("0程序继续执行");
    }

}
class test1{
    public void testb(int a, int b)  {
        final test2 test2 = new test2();

        try {
            test2.testa(1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("1程序继续执行");
    }
}

class test2{
    public void testa(int a, int b) {
        try {
            System.out.println(a / b);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出错了！");
        }
        System.out.println("2程序继续执行");
    }
}
