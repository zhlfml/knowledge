package me.thomas.knowledge.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoxinsheng
 * @date 31/08/2017.
 */
public class RegexTutorial {

    public static void main(String[] args) {
        String text = "AN_FACET:(\"BRISTOL-MYERS SQUIBB COMPANY\" OR \"NOVARTIS AG\" OR \"TAKEDA PHARMACEUTICAL COMPANY LIMITED\") AND ANS_ID:(\"63f9358a-0594-4cb1-8c44-3932cb030f05\" OR \"a6c827d8-7ed0-4afb-ae89-cc556d3de48a\")";
        String regex = ":\\((\"|\\[)(.*?)(\"|\\])\\)";

        Matcher m = Pattern.compile(regex).matcher(text);
        List<String> valueArr = new ArrayList<>(m.groupCount() - 1);
        while (m.find()) {
            valueArr.add(m.group());
        }
        System.out.println(valueArr.toString());
    }
}
