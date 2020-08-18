package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 06.06.2020
 */

@Controller
public class ConvertDoc {

  @Autowired
  private NotesRepo notesRepo;

  @Autowired
  private UserRepo userRepo;

  @Transactional
  @GetMapping("/toDoc/{note}")
  public String toDoc(@RequestParam Note note, Model model)
      throws IOException, URISyntaxException, InvalidFormatException {
//        String logo = "logo-leaf.png";
//        String paragraph1 = note.getTitle();
//        String paragraph2 = note.getAuthorName();
//        String paragraph3 = note.getText();
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

//        XWPFParagraph image = document.createParagraph();
//        image.setAlignment(ParagraphAlignment.CENTER);
//        XWPFRun imageRun = image.createRun();
//        imageRun.setTextPosition(20);
//        Path imagePath = Paths.get(ClassLoader.getSystemResource(logo).toURI());
//        imageRun.addPicture(Files.newInputStream(imagePath), XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(), Units.toEMU(50), Units.toEMU(50));

    XWPFParagraph para1 = document.createParagraph();
    para1.setAlignment(ParagraphAlignment.RIGHT);
    String string1 = String.valueOf(note.getAuthorName());
//        String string1 = Files.lines(Paths.get(ClassLoader.getSystemResource(paragraph1).toURI())).collect(Collectors.joining(" "));
    XWPFRun para1Run = para1.createRun();
    para1Run.setText(string1);
    para1Run.setItalic(true);

    XWPFParagraph para2 = document.createParagraph();
    para2.setAlignment(ParagraphAlignment.RIGHT);
    String string2 = String.valueOf(note.getDate());
//        String string2 = Files.lines(Paths.get(ClassLoader.getSystemResource(paragraph2).toURI())).collect(Collectors.joining(" "));
    XWPFRun para2Run = para2.createRun();
    para2Run.setText(string2);
    para2Run.setItalic(true);

    XWPFParagraph sectionTitle = document.createParagraph();
    XWPFRun sectionTRun = sectionTitle.createRun();
    sectionTRun.setText(note.getTitle());
    sectionTRun.setColor("00CC44");
    sectionTRun.setBold(true);
    sectionTRun.setFontFamily("Courier");

//        XWPFParagraph para1 = document.createParagraph();
//        para1.setAlignment(ParagraphAlignment.BOTH);
////        String string1 = convertTextFileToString(paragraph1);
//        String string1 = note.getTitle();
////        String string1 = Files.lines(Paths.get(ClassLoader.getSystemResource(paragraph1).toURI())).collect(Collectors.joining(" "));
//        XWPFRun para1Run = para1.createRun();
//        para1Run.setText(string1);

    XWPFParagraph para3 = document.createParagraph();
    para3.setAlignment(ParagraphAlignment.LEFT);
    String string3 = note.getText();
//        String string3 = Files.lines(Paths.get(ClassLoader.getSystemResource(paragraph3).toURI())).collect(Collectors.joining(" "));
    XWPFRun para3Run = para3.createRun();
    para3Run.setText(string3);

    FileOutputStream out = new FileOutputStream(output);
    document.write(out);
    out.close();
    document.close();
    return "redirect:/main";
  }
}
