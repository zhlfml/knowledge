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

    public static String TABLE_NAME = "ACT_GE_BYTEARRAY";

    public static void main(String[] args) throws IOException {

    	String dir = "/home/thomas/DATA";

    	int total = 0;
    	long start = System.currentTimeMillis();

    	List<File> matchedFiles = FileUtil.find(dir, false, i("table_export*"));
    	for (File file : matchedFiles) {
            doRename(file);
    	}
    	long end = System.currentTimeMillis();
    	System.out.println("Total: " + total + ", time: " + (end - start) + "ns.");

    }

    private static String i(String keyWord) {
    	return keyWord.replace(".", "\\.").replace('?', '.').replace("*", ".*");
    }

    private static void doModify(File file) throws IOException {
        boolean contain = false;
        BufferedReader in = new BufferedReader(new FileReader(file));

        String s;
        while ((s = in.readLine()) != null) {
            if (s.contains("table_export")) {
                s = s.replace("table_export", TABLE_NAME);
            }
        }
        in.close();
    }

    private static void doRename(File file) throws IOException {
        String filePath = file.getAbsolutePath().replace("table_export", "G_RULE");
        file.renameTo(new File(filePath));
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
