package me.thomas.knowledge.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * 实现类名必须是接口名去除MBean之后的名称
 *
 * @author zhaoxinsheng
 * @date 6/24/16.
 */
public class WebServer implements WebServerMBean {

    private int port;
    private String logLevel;
    private boolean started;

    public WebServer(int port, String logLevel) {
        this.port = port;
        this.logLevel = logLevel;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getLogLevel() {
        return logLevel;
    }

    @Override
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    @Override
    public void stop() {
        this.started = false;
    }

    @Override
    public void start() {
        this.started = true;
    }

    public static void main(String[] args) {
        WebServer webServer = new WebServer(8080, "DEBUG");
        // 在JMXServer注册
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(webServer, new ObjectName("me.thomas.webserver", "type", "WebServer"));
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            // 如果接口名称不是以MBean或者MXBean结尾,则会抛出该异常信息. 注册不成功在jconsole客户端的MBean标签页上自然就看不到WebServer.
            // javax.management.NotCompliantMBeanException: MBean class me.thomas.knowledge.jmx.WebServer does not implement DynamicMBean,
            // and neither follows the Standard MBean conventions (javax.management.NotCompliantMBeanException: Class me.thomas.knowledge.jmx.WebServer is not a JMX compliant Standard MBean)
            // nor the MXBean conventions (javax.management.NotCompliantMBeanException: me.thomas.knowledge.jmx.WebServer: Class me.thomas.knowledge.jmx.WebServer is not a JMX compliant MXBean)
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        webServer.start();
        int times = 0;
        while (true) {
            while (webServer.isStarted()) {
                times++;
                if ("DEBUG".equals(webServer.getLogLevel())) {
                    System.out.println(times);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
