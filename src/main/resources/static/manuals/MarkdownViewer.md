# MarkDown Viewer

## commonmark-java
markdown을 html로 파싱하기 위해 commonmark-java library를 활용 [commonmark-java [Github]](https://github.com/commonmark/commonmark-java)

## 코드 구성
1. MarkDownController

```java
package com.liam.toy_box.toybox.markdown.controller;

import com.liam.toy_box.toybox.utils.markdown.MarkDownUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/markdown-viewer")
public class MarkDownController {

    @GetMapping
    public String viewer(Model model) {
        String markDownToHtml = MarkDownUtils.render("MarkdownViewer.md");
        model.addAttribute("contents", markDownToHtml);
        return "markdown/viewer";
    }
}

```
2. MarkDownUtils
```java
package com.liam.toy_box.toybox.markdown;

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
```
