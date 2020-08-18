package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 06.06.2020
 */

@Controller
public class ConvertJSON {
    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @GetMapping("/toJSON/{note}")
    public String toXml(@RequestParam Note note, Model model) {

        ObjectMapper mapper = new ObjectMapper();

        Note noteForJSON = new Note();
        noteForJSON.setId(note.getId());
        noteForJSON.setDate(note.getDate());
        noteForJSON.setLastEditDate(note.getLastEditDate());
        noteForJSON.setTitle(note.getTitle());
        noteForJSON.setText(note.getText());
        noteForJSON.setAuthorName(note.getAuthorName());
        noteForJSON.setCoauthor(note.getCoauthor());
        noteForJSON.setRedaction(note.getRedaction());

        try {
            String fileName = "Note"
                    + "_"
                    + note.getId()
                    + ".json";
            // Java objects to JSON file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), noteForJSON);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/main";
        }
        return "redirect:/main";
    }

    @Transactional
    @GetMapping("/fromJSON")
    public String fromXml(Model model) throws JAXBException, IOException, SAXException, ParserConfigurationException {
        File fXmlFile;
        String dir;

        FileDialog dialog = new FileDialog((Frame) null);
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dir = dialog.getFile();


        ObjectMapper mapper = new ObjectMapper();

        try {

            // JSON file to Java object
            Note noteFromJSON = mapper.readValue(new File(dir), Note.class);

            noteFromJSON.setVisible(true);

            Date date = new GregorianCalendar().getTime();
            noteFromJSON.setDate(date);
            noteFromJSON.setLastEditDate(date);

            for (User user : userRepo.findAll()) {
                if(noteFromJSON.getAuthorName().equals(user.getUsername())){
                    noteFromJSON.setAuthor(user);
                }
            }

            for (User user : userRepo.findAll()) {
                if (noteFromJSON.getCoauthor().equals(user.getUsername())) {
                    noteFromJSON.setCoauthorId(user.getId());
                }
            }
            noteFromJSON.setNoteGroup(noteFromJSON.getId());
            noteFromJSON.setRestored(true);
            model.addAttribute("note", noteFromJSON);
            notesRepo.save(noteFromJSON);

            noteFromJSON.setNoteGroup(noteFromJSON.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/main";
        }

        return "redirect:/main";
    }
}
