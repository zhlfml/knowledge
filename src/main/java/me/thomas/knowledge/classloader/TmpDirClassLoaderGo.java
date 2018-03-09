package me.thomas.knowledge.classloader;

import java.lang.reflect.Method;

import me.thomas.knowledge.designpatterns.observer.weather.WeatherData;

/**
 * 运行该类前先运行命令
 * rm -rf /tmp/me && cp -r target/classes/me /tmp/me
 * 将class文件拷贝到/tmp目录下
 *
 * @author zhaoxinsheng
 * @date 09/03/2018.
 */
public class TmpDirClassLoaderGo {

    public static void main(String[] args) throws Exception {
        // 设置parent=null是不希望SystemClassLoader抢先加载到类
        ClassLoader classLoader = new TmpDirClassLoader(null,"TmpDirClassLoader");
        Class<?> klass = classLoader.loadClass("me.thomas.knowledge.designpatterns.observer.weather.WeatherData");
        Object weatherStation = klass.newInstance();

        // 简单测试
        System.out.println("WeatherData.class.isAssignableFrom(klass) => " + WeatherData.class.isAssignableFrom(klass));

        // 使用反射调用WeatherStation的方法
        Method autoRegisterObserver = klass.getMethod("autoRegisterObserver");
        Method setMeasurements = klass.getMethod("setMeasurements", float.class, float.class, float.class);
        autoRegisterObserver.invoke(weatherStation);
        setMeasurements.invoke(weatherStation, 80, 65, 30.4f);
        setMeasurements.invoke(weatherStation, 82, 70, 29.2f);
        setMeasurements.invoke(weatherStation, 78, 90, 29.2f);
    }
}
