package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 06.06.2020
 */

@Controller
public class ConvertXml {
    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @GetMapping("/toXml/{note}")
    public String toXml(@RequestParam Note note, Model model) {

        try {
            String fileName = "Note"
                    + "_"
                    + note.getId()
                    + ".xml";

            Element company = new Element("note");
            Document doc = new Document(company);
            doc.setRootElement(company);

            Element n = new Element("note");
            n.setAttribute(new Attribute("id", String.valueOf(note.getId())));
            n.addContent(new Element("date").setText(String.valueOf(note.getDate())));
            n.addContent(new Element("last_edit_date").setText(String.valueOf(note.getLastEditDate())));
            n.addContent(new Element("title").setText(note.getTitle()));
            n.addContent(new Element("text").setText(note.getText()));
            n.addContent(new Element("author_name").setText(note.getAuthorName()));
            n.addContent(new Element("coauthor").setText(note.getCoauthor()));
            n.addContent(new Element("redaction").setText(String.valueOf(note.getRedaction())));

            doc.getRootElement().addContent(n);

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(fileName));

            model.addAttribute("message", "Ok!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        return "redirect:/main";
    }



    @Transactional
    @GetMapping("/fromXml")
    public String fromXml(Model model) throws JAXBException, IOException, SAXException, ParserConfigurationException {
        File fXmlFile;
        String dir;

        try{
            FileDialog dialog = new FileDialog((Frame) null);
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            dir = dialog.getFile();

        fXmlFile = new File(dir);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        Note note = new Note();
        note.setAuthorName(doc.getElementsByTagName("author_name").item(0).getTextContent());

            for (User user : userRepo.findAll()) {
                if(note.getAuthorName().equals(user.getUsername())){
                    note.setAuthor(user);
                }
            }

            note.setAuthorName(note.getAuthor().getUsername());

        note.setVisible(true);

        Date date = new GregorianCalendar().getTime();
        note.setDate(date);
        note.setLastEditDate(date);
        note.setTitle(doc.getElementsByTagName("title").item(0).getTextContent());
        note.setText(doc.getElementsByTagName("text").item(0).getTextContent());
        note.setCoauthor(doc.getElementsByTagName("coauthor").item(0).getTextContent());

        for (User user : userRepo.findAll()) {
            if(note.getCoauthor().equals(user.getUsername())){
                note.setCoauthorId(user.getId());
            }
        }

        note.setRedaction(Integer.valueOf(doc.getElementsByTagName("redaction").item(0).getTextContent()));
        note.setRestored(true);
        model.addAttribute("note", note);
//        note.setNoteGroup(note.getId());
        notesRepo.save(note);

        note.setNoteGroup(note.getId());


        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/main";
        }

        return "redirect:/main";
    }
}
