package com.wedevol.emptyspringrest.utils;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.jsoup.Jsoup;

public class MarkdownUtil {

    public static String markdownToPlainText(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        String html = HtmlRenderer.builder().build().render(document);
        return Jsoup.parse(html).text()
                .replaceAll("\\s+", " ")   // 合并多余空格
                .trim();
    }

}
