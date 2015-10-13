package me.thomas.knowledge.tools;

import me.thomas.knowledge.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchFile {

    public static void main(String[] args) throws IOException {

    	String dir = "E:\\workspace\\finestay\\4Code\\ww-finestay";

    	int total = 0;
    	long start = System.currentTimeMillis();

    	List<File> matchedFiles = FileUtil.find(dir, true, i("*.java"));
    	for (File file : matchedFiles) {
            doModify(file, "com.ww.finestay.persist.pagination", "com.ww.finestay.common.utils.pagination");
    	}
    	long end = System.currentTimeMillis();
    	System.out.println("Total: " + total + ", time: " + (end - start) + "ns.");
    }

    private static void doModify(File file, String target, String replacedWith) throws IOException {
        boolean modified = false;
        BufferedReader in = new BufferedReader(new FileReader(file));

        StringBuffer sb = new StringBuffer(1024);
        String s;
        while ((s = in.readLine()) != null) {
            if (s.contains(target)) {
                modified = true;
                s = s.replace(target, replacedWith);
            }
            sb.append(s).append("\r\n");
        }

        if (modified) {
            FileUtil.write(file, sb.toString());
        }

        in.close();
    }

    private static void doRename(File file) throws IOException {
        String filePath = file.getAbsolutePath().replace("table_export", "G_RULE");
        file.renameTo(new File(filePath));
    }

    private static String i(String keyWord) {
        return keyWord.replace(".", "\\.").replace('?', '.').replace("*", ".*");
    }

    private static void applyTemplate(String s) {
        String comparedSchema = "audit_drop2";
        String tableName = s;
        String template = "select * from ${tableName}\n" +
                "minus\n" +
                "select * from ${comparedSchema}.${tableName};\n";

        System.out.println(template.replace("${tableName}", tableName).replace("${comparedSchema}", comparedSchema));
    }
}
