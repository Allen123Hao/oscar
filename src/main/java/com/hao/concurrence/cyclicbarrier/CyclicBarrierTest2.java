package com.hao.concurrence.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <code>CyclicBarrierTest2</code>
 *
 * @description:
 * @author: Hao Xueqiang(haoxueqiang@kingsoft.com)
 * @creation: 2019/5/27
 * @version: 1.0
 */
public class CyclicBarrierTest2 {
    private static int SIZE = 5;
    private static CyclicBarrier cb;
    public static void main(String[] args) {

        cb = new CyclicBarrier(SIZE, new Runnable () {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CyclicBarrier's parties is: "+ cb.getParties());
            }
        });

        // 新建5个任务
        for(int i=0; i<SIZE; i++)
            new InnerThread().start();
    }

    static class InnerThread extends Thread{
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");

                // 将cb的参与者数量加1
                cb.await();

                // cb的参与者数量等于5时，才继续往后执行
                System.out.println(Thread.currentThread().getName() + " continued.");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
