package me.thomas.knowledge.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtil {

    /**
     * find out the files which match regular keyWord in specify directory,
     * default not include sub directory.
     *
     * @param directory the directory want to be find.
     * @param keyWord   a regular expression to match filename.
     * @return all matched files.
     */
    public static List<File> find(String directory, String keyWord) {
        return find(new File(directory), true, keyWord);
    }

    /**
     * find out the files which match regular keyWord in specify directory,
     * default not include sub directory.
     *
     * @param directory the directory want to be find.
     * @param keyWord   a regular expression to match filename.
     * @return all matched files.
     */
    public static List<File> find(File directory, String keyWord) {
        return find(directory, true, keyWord);
    }

    /**
     * find out the files which match regular keyWord in specify directory.
     *
     * @param directory     the directory want to be find.
     * @param includeSubDir whether include sub-folders or not.
     * @param keyWord       a regular expression to match filename.
     * @return all matched files.
     */
    public static List<File> find(String directory, boolean includeSubDir, String keyWord) {
        return find(new File(directory), includeSubDir, keyWord);
    }

    /**
     * find out the files which match regular keyWord in specify directory.
     *
     * @param directory     the directory want to be find.
     * @param includeSubDir whether include sub-folders or not.
     * @param keyWord       a regular expression to match filename.
     * @return all matched files.
     */
    public static List<File> find(File directory, boolean includeSubDir, final String keyWord) {

        if (!directory.exists()) {
            return new ArrayList<File>();
        }

        List<File> matchedFiles = new ArrayList<File>();

        matchedFiles.addAll(Arrays.asList(directory.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(keyWord);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        })));

        if (includeSubDir) {
            File[] dirs = listDirs(directory);

            for (int i = 0; i < dirs.length; i++) {
                matchedFiles.addAll(find(dirs[i], includeSubDir, keyWord));
            }
        }

        return matchedFiles;
    }

    public static String read(String file) throws IOException {
        return read(new File(file));
    }

    public static String read(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = new BufferedReader(new FileReader(file));

        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s).append("\n");
        }
        in.close();

        return sb.toString();
    }

    public static void write(String file, String text) throws IOException {
        write(new File(file), text);
    }

    public static void write(File file, String text) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")));

        out.print(text);
        out.close();
    }

    public static void write(String file, InputStream is) throws IOException {
        write(new File(file), is);
    }

    public static void write(File file, InputStream is) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[1024 * 8];
        while ((bytesRead = is.read(buffer, 0, buffer.length)) > 0) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        is.close();
    }

    public static File[] listDirs(File file) {
        List<File> dirs = new ArrayList<File>();

        File[] fileArray = file.listFiles();

        for (int i = 0; (fileArray != null) && (i < fileArray.length); i++) {
            if (fileArray[i].isDirectory()) {
                dirs.add(fileArray[i]);
            }
        }

        return dirs.toArray(new File[dirs.size()]);
    }

    public static String getShortFileName(String fullFileName) {
        int pos = fullFileName.lastIndexOf(File.separator);

        String shortFileName =
                fullFileName.substring(pos + 1, fullFileName.length());

        return shortFileName;
    }

    public static String getShortFileName(File file) {
        return getShortFileName(file.getAbsolutePath());
    }

    public static String getParent(String fullFileName) {
        int pos = fullFileName.lastIndexOf(File.separator);

        String parentFileName = fullFileName.substring(0, pos + 1);

        return parentFileName;
    }

    public static String getParentFileName(File file) {
        return getParent(file.getAbsolutePath());
    }

    public static byte[] getBytes(File file) throws IOException {
        if ((file == null) || !file.exists()) {
            return null;
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        byte[] bytes = new byte[(int) randomAccessFile.length()];

        randomAccessFile.readFully(bytes);

        randomAccessFile.close();

        return bytes;
    }

    /**
     * 删除整个目录，包括子目录及文件
     *
     * @param dir 被删除的目录
     */
    public static void rmdir(String dir) {
        rmdir(new File(dir));
    }

    /**
     * 删除整个目录，包括子目录及文件
     *
     * @param dir 被删除的目录
     */
    public static void rmdir(File dir) {
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File[] filelist = dir.listFiles();
                for (File subfile : filelist) {
                    rmdir(subfile.getAbsolutePath());
                }
            }
            dir.delete();
        }
    }

    public static boolean mkdir(String dir) {
        return mkdir(new File(dir));
    }

    public static boolean mkdir(File dir) {
        return dir.exists() || dir.mkdirs();
    }

    public static boolean touch(String file) throws IOException {
        return touch(new File(file));
    }

    public static boolean touch(File file) throws IOException {
        return file.exists() || (mkdir(file.getParent()) && file.createNewFile());
    }

    /**
     * test file whether contain word
     *
     * @param file  file path
     * @param regex key word
     * @return true if find word in file
     */
    public static boolean contains(String file, String regex) throws IOException {
        return contains(new File(file), regex);
    }

    /**
     * test file whether contain word
     *
     * @param file  file path
     * @param regex key word
     * @return true if find word in file
     */
    public static boolean contains(File file, String regex) throws IOException {

        boolean contain = false;
        BufferedReader in = new BufferedReader(new FileReader(file));
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        String s;
        while ((s = in.readLine()) != null) {
            Matcher m = pattern.matcher(s.trim());
            if (m.find()) {
                contain = true;
                break;
            }
        }
        in.close();

        return contain;
    }
}
