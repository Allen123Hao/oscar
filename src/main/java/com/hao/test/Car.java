package com.hao.test;

/**
 * <code>ApplicationConfig</code>
 *
 * @description:
 * @author: Hao Xueqiang(xueqiang.hao@tendcloud.com)
 * @creation: 2016/12/15
 * @version: 1.0
 */
public class Car implements Product {

    public Car(){
        System.out.println("Car的无参构造方法");
    }

    public Car(Integer id,String name){
        this();
        this.id = id;
        this.name = name;
        System.out.println(this);
    }

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!id.equals(car.id)) return false;
        return name.equals(car.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public void func2(){
        System.out.println(this);

    }
}
