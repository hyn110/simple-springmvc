package com.fmi110.quartz;

/**
 * @author fmi110
 * @Description:
 * @Date 2018/2/6 22:06
 */
public class TimeJob {
    public static int count =0;
    public  void doJob(){
        System.out.println("我是定时执行的任务 : "+(count++));
    }
}
