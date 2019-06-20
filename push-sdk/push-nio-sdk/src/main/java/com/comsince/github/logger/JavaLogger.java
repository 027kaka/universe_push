package com.comsince.github.logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author comsicne
 *         Copyright (c) [2019] [Meizu.inc]
 * @Time 19-2-21 下午2:27
 **/
public class JavaLogger implements Log {

    private Class loggerClass;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void setTag(Class classTag) {
        this.loggerClass = classTag;
    }

    @Override
    public void i(String tag, String message) {
        System.out.println(loggerClass.getSimpleName()+"tag "+"message: "+message);
    }

    @Override
    public void i(String message) {
        System.out.println("["+Thread.currentThread().getName()+"]"+" ["+dateFormat.format(new Date())+"] "+"["+loggerClass.getSimpleName()+"] "+"message: "+message);
    }

    @Override
    public void e(String message, Throwable e) {
        System.err.println("["+Thread.currentThread().getName()+"]"+" ["+dateFormat.format(new Date())+"] "+"["+loggerClass.getSimpleName()+"] "+"message: "+message+" "+(e != null ? getStackMsg(e):""));
    }

    @Override
    public void e(String tag, String message, Exception e) {
        System.err.println("["+tag+"]"+"["+Thread.currentThread().getName()+"]"+" ["+dateFormat.format(new Date())+"] "+"["+loggerClass.getSimpleName()+"] "+"message: "+message+" "+(e != null ? getStackMsg(e):""));
    }

    @Override
    public void e(String message) {
        System.err.println("["+Thread.currentThread().getName()+"]"+" ["+dateFormat.format(new Date())+"] "+"["+loggerClass.getSimpleName()+"] "+"message: "+message);
    }


    public static String getStackMsg(Exception e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

}
