package com.hao.test;

/**
 * <code>CPUCacheTest</code>
 *
 * @description:
 * @author: Hao Xueqiang(haoxueqiang@58.com)
 * @creation: 2020-06-15
 * @version: 1.0
 */
public class CPUCacheTest {

    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][8];
        int sum = 0;
        // 横向遍历
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked)+ "ms");

        sum = 0;
        marked = System.currentTimeMillis();
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked)+ "ms");
    }
}
