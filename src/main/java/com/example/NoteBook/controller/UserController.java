package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.Role;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import com.example.NoteBook.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

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
}
