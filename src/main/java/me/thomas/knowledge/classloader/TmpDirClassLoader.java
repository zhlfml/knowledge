package me.thomas.knowledge.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 从系统的临时目录下装载.class文件
 *
 * @author zhaoxinsheng
 * @date 09/03/2018.
 */
public class TmpDirClassLoader extends ClassLoader {

    // 类加载器名称
    private String name;
    // 类加载路径
    private static final String folder = "/tmp/";

    private static final String fileType = ".class";

    public TmpDirClassLoader(String name) {
        this.name = name;
    }

    public TmpDirClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loaderClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loaderClassData(String name) throws ClassNotFoundException {
        byte[] classData;
        String classPath = name.replace('.', '/');
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             InputStream is = new FileInputStream(new File(folder + classPath + fileType))) {
            int c;
            while ((c = is.read()) > -1) {
                baos.write(c);
            }
            classData = baos.toByteArray();
        } catch (IOException ioe) {
            throw new ClassNotFoundException(name + " cannot loaded!");
        }
        return classData;
    }
}
