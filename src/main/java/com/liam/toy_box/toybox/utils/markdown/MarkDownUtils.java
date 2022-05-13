package com.liam.toy_box.toybox.utils.markdown;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MarkDownUtils {

    private final static String LOCAL_MANUAL_PATH = "static/manuals/";

    public static String render(String manualPage) {
        StringBuilder stringBuilder = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource(LOCAL_MANUAL_PATH + manualPage);

        BufferedReader br = null;

        try {
            br = Files.newBufferedReader(Paths.get(classPathResource.getURI()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        br.lines().forEach(line -> stringBuilder.append(line).append("\n"));
        Node document = Parser.builder().build().parse(stringBuilder.toString());

        return HtmlRenderer.builder().build().render(document);
    }
}
