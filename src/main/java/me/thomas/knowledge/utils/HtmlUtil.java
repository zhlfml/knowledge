package me.thomas.knowledge.utils;

public class HtmlUtil {

    public static String escape(String text) {
        if (text == null) {
            return null;
        }

        if (text.length() == 0) {
            return "";
        }

        // Escape using XSS recommendations from
        // http://www.owasp.org/index.php/Cross_Site_Scripting
        // #How_to_Protect_Yourself

        StringBuilder sb = null;

        int lastReplacementIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            String replacement = null;

            switch (c) {
                case '<':
                    replacement = "&lt;";

                    break;

                case '>':
                    replacement = "&gt;";

                    break;

                case '&':
                    replacement = "&amp;";

                    break;

                case '"':
                    replacement = "&#034;";

                    break;

                case '\'':
                    replacement = "&#039;";

                    break;

                case '\u00bb': // '锟�
                    replacement = "&#187;";

                    break;

                case '\u2013':
                    replacement = "&#x2013;";

                    break;

                case '\u2014':
                    replacement = "&#x2014;";

                    break;
            }

            if (replacement != null) {
                if (sb == null) {
                    sb = new StringBuilder();
                }

                if (i > lastReplacementIndex) {
                    sb.append(text.substring(lastReplacementIndex, i));
                }

                sb.append(replacement);

                lastReplacementIndex = i + 1;
            }
        }

        if (sb == null) {
            return text;
        }
        else {
            if (lastReplacementIndex < text.length()) {
                sb.append(text.substring(lastReplacementIndex));
            }

            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        System.out.println(HtmlUtil.escape("<script>alert(1)</script>"));
    }

}
