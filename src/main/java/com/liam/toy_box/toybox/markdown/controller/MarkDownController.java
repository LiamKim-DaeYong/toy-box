package com.liam.toy_box.toybox.markdown.controller;

import com.liam.toy_box.toybox.markdown.MarkDownUtils;
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
