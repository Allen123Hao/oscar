package com.hao.corejava.classloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <code>CustomerClassTest</code>
 *
 * @description:
 * @author: Hao Xueqiang(xueqiang.hao@tendcloud.com)
 * @creation: 2016/12/14
 * @version: 1.0
 */
public class CustomerClassTest {
    public static void main(String[] args) {
        /**要进行热加载的类名**/
        String name = "com.hao.corejava.classloader.Printer";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IPrinter printer = null;
        while (true) {
            System.out.println("输入任意字符进行热加载，直接敲回车键退出程序");
            try {
                String line = reader.readLine();
                if(line != null && line.length() > 0){
                    CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader() , name);
                    Class<?> clazz = loader.loadClass();
                    /**
                     * 被子加载器加载的类拥有被父加载器加载的类的可见性
                     * Printer是由自定义类加载器加载的，
                     * 而它的父类IPrinter是由系统类加载器加载的，
                     * 因此IPrinter对于Printer具有可见性，
                     * 因此转型成功，并不会因为类加载器不同导致ClassCastException异常
                     */
                    printer = (IPrinter) clazz.newInstance();
                    /**看看是否热加载成功了**/
                    printer.print();
                }else{
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main1(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("输入任意字符进行热加载，直接敲回车键退出程序");
            try {
                String s = reader.readLine();
                if(s != null && s.length() > 0){
                    Class clazz = Class.forName("com.hao.corejava.classloader.Printer");
                    IPrinter printer = (IPrinter) clazz.newInstance();
                    printer.print();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
