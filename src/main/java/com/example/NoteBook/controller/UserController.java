package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Cousers;
import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.Role;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.CousersRepo;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import com.example.NoteBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.1.1 2
 * @date 27.05.2020
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private CousersRepo cousersRepo;


    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("regDate", user.getRegDate());
        model.addAttribute("coauthor", user.getCoauthor());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String coauthor,
            Model model
    ) {
        Iterable<Note> notes = notesRepo.findAllByAuthor(user);
        List<User> users = userRepo.findAll();
        //обновление соавтора (один соавтор)
        if (coauthor.equals("0") || coauthor.equals(" ") || coauthor.equals("")) {
            userService.updateProfile(user, "0");
            for (Note n : notes) {
                n.setCoauthor("0");
                n.setCoauthorId(0l);
            }
        } else {
            userService.updateProfile(user, coauthor);
            for (Note n : notes) {
                n.setCoauthor(coauthor);
                for (User u : users) {
                    if (n.getCoauthor().equals(u.getUsername())) {
                        n.setCoauthorId(u.getId());
                    }
                }
            }
            model.addAttribute("coauthor", user.getCoauthor());
            notesRepo.saveAll(notes);
        }

        return "redirect:/user/profile";
    }

//    @PostMapping("profile/addcoauthor")
//    public String addCoauthor(
//            @AuthenticationPrincipal User user,
//            @RequestParam(required = false) String coauthor,
//            Model model) {
//        //добавление соавтора - новая редакция (много соавторов)
//        Iterable<Cousers> cousers = cousersRepo.findAll();
//        List<User> users = userRepo.findAll();
//        for (User u : users) {
//            if (u.getUsername().equals(coauthor)) {
//                Cousers couser = new Cousers(user);
//                couser.setCoauthor(u);
//                model.addAttribute("coauthor", couser.getCoauthor());
//            }
//        }
//        cousersRepo.saveAll(cousers);
//
//        return "redirect:/user/profile";
//    }

//    @PostMapping("profile/addCoreader")
//    public String addCoreader(
//            @AuthenticationPrincipal User user,
//            @RequestParam(required = false) String coreader,
//            Model model) {
//        //добавление соавтора - новая редакция (много соавторов)
//        boolean isCoreader = false;
//        Iterable<Cousers> cousers = cousersRepo.findAll();
//        List<User> users = userRepo.findAll();
//        for (User u : users) {
//            if (u.getUsername().equals(coreader)) {
//                for (Cousers c : cousers){
//                    if (c.getCoreader().equals(coreader) && c.getAuthor().equals(user)){
//                        isCoreader = true;
//                        break;
//                    }
//                }
//                if (isCoreader == false){
//                    Cousers couser = new Cousers(user);
//                    couser.setAuthor(user);
//                    couser.setCoreader(u);
//                    model.addAttribute("author", user.getUsername());
//                    model.addAttribute("coreader", couser.getCoreader());
//                }
//            }
//        }
//        cousersRepo.saveAll(cousers);
//        return "redirect:/user/profile";
//    }
}
