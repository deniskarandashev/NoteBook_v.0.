package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Note;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 06.06.2020
 */

@Controller
public class ConvertDoc {

  @Transactional
  @GetMapping("/toDoc/{note}")
  public String toDoc(@RequestParam Note note, Model model)
      throws IOException {
    String output = "Note"
        + "_"
        + note.getId()
        + ".docx";

    XWPFDocument document = new XWPFDocument();

    XWPFParagraph title = document.createParagraph();
    title.setAlignment(ParagraphAlignment.CENTER);
    XWPFRun titleRun = title.createRun();
    titleRun.setText("NoteBook");
    titleRun.setColor("009933");
    titleRun.setBold(true);
    titleRun.setFontFamily("Courier");
    titleRun.setFontSize(20);

    XWPFParagraph subTitle = document.createParagraph();
    subTitle.setAlignment(ParagraphAlignment.CENTER);
    XWPFRun subTitleRun = subTitle.createRun();
    subTitleRun.setText("by Denis Karandashev");
    subTitleRun.setColor("0018cc");
    subTitleRun.setFontFamily("Courier");
    subTitleRun.setFontSize(12);
    subTitleRun.setTextPosition(20);
    subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

    XWPFParagraph para1 = document.createParagraph();
    para1.setAlignment(ParagraphAlignment.RIGHT);
    String string1 = String.valueOf(note.getAuthorName());
    XWPFRun para1Run = para1.createRun();
    para1Run.setText(string1);
    para1Run.setItalic(true);

    XWPFParagraph para2 = document.createParagraph();
    para2.setAlignment(ParagraphAlignment.RIGHT);
    String string2 = String.valueOf(note.getDate());
    XWPFRun para2Run = para2.createRun();
    para2Run.setText(string2);
    para2Run.setItalic(true);

    XWPFParagraph sectionTitle = document.createParagraph();
    XWPFRun sectionTRun = sectionTitle.createRun();
    sectionTRun.setText(note.getTitle());
    sectionTRun.setColor("00CC44");
    sectionTRun.setBold(true);
    sectionTRun.setFontFamily("Courier");

    XWPFParagraph para3 = document.createParagraph();
    para3.setAlignment(ParagraphAlignment.LEFT);
    String string3 = note.getText();
   XWPFRun para3Run = para3.createRun();
    para3Run.setText(string3);

    FileOutputStream out = new FileOutputStream(output);
    document.write(out);
    out.close();
    document.close();
    return "redirect:/main";
  }
}
