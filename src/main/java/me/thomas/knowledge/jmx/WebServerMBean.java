package me.thomas.knowledge.jmx;

/**
 * 接口必须以MBean或者MXBean结尾
 *
 * @author zhaoxinsheng
 * @date 6/24/16.
 */
public interface WebServerMBean {
    int getPort();

    String getLogLevel();
    void setLogLevel(String level);

    boolean isStarted();

    void stop();
    void start();
}
