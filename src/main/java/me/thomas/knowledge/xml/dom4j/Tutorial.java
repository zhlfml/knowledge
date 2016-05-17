package me.thomas.knowledge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * Created by thomas on 4/28/16.
 */
public class Tutorial {

    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(ClassLoader.getSystemResourceAsStream("askForLeave.bpmn"));
        Element rootElement = document.getRootElement();
        Element process = rootElement.element("process");
        List<Element> userTasks = process.elements("userTask");
        System.out.println(userTasks.size());
    }

}
