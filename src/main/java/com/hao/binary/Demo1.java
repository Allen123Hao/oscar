package com.hao.binary;

/**
 * <code>Solution_70</code>
 *
 * @description:
 * @author: Hao Xueqiang(xueqiang.hao@tendcloud.com)
 * @creation: 2017/6/14
 * @version: 1.0
 */
public class Demo1 {
    public void func1(){
        double i = 0.6;
        System.out.println(i/4);
    }

    public void func2(){
        int i = 31;
        System.out.println(Integer.toBinaryString(i));
        int a = 9;
        System.out.println(Integer.bitCount(a));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a & 31);
        System.out.println(a >> 3);
    }

    public void func3(){
        int i = 1;
        System.out.println(Integer.toBinaryString(i));
    }

    public void func4(){
        int a = 21;
        System.out.println(Integer.toBinaryString(a));
        int b = -21;
        System.out.println(Integer.toBinaryString(b));
    }

    public void func5(){
        int a = -1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a << 29));
        System.out.println(a << 29);
        int b = 1;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(b << 29));
        System.out.println(Integer.toBinaryString((b << 29) -1));
    }

    public void func6(){
        int capacity = (1 << 29)-1;
        System.out.println(Integer.toBinaryString(capacity));
        int stop = 1 << 29;
        System.out.println(Integer.toBinaryString(stop));
        System.out.println(Integer.toBinaryString(stop & ~capacity));

    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.func5();
    }
}
