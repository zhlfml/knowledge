package me.thomas.knowledge.utils;

import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

public class WordUtils {

    public static String readText(String filepath) throws Exception {
        OPCPackage opcPackage = POIXMLDocument.openPackage(filepath);
        POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
        return extractor.getText();
    }

    public static String readTextToHtml(String filepath) throws Exception {
        XWPFDocument docxDocument = new XWPFDocument(Files.newInputStream(new File(filepath).toPath()));
        XHTMLOptions options = XHTMLOptions.create();
        // 转换html
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(docxDocument, htmlStream, options);
        String htmlStr = htmlStream.toString();
        return htmlStr;
    }

    public static void main(String[] args) throws Exception {
        String text = WordUtils.readTextToHtml("/Users/thomas/Downloads/机票开放平台推广合作协议书（个人）.docx");
        System.out.println(text);
    }
}
