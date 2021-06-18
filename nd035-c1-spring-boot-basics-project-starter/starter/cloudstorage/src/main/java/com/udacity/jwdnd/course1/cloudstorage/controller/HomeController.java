package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredentials;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFiles;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNotes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NotesService notesService;
    private FileService fileService;
    private CredentialsService credentialsService;
    private UserService userService;

    public HomeController(NotesService notesService, FileService fileService, CredentialsService credentialsService, UserService userService) {
        this.notesService = notesService;
        this.fileService = fileService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model, @ModelAttribute("files") UserFiles files, @ModelAttribute("notes") UserNotes notes, @ModelAttribute("credentials") UserCredentials credentials){
        model.addAttribute("userFiles",this.fileService.getUserFiles(authentication.getName()));
        model.addAttribute("userNotes",this.notesService.getUserNotes(authentication.getName()));
        model.addAttribute("userCredentials",this.credentialsService.getUserCredentials(authentication.getName()));
        return "home";
    }

    @PostMapping("/uploadfile")
    public String uploadFile(Authentication authentication, @ModelAttribute("files") UserFiles files, @ModelAttribute("notes") UserNotes notes, @ModelAttribute("credentials") UserCredentials credentials, Model model){
        User user = userService.getUser(authentication.getName());
        files.setUserid(user.getUserId());
        files.setFileDetails();
        if(fileService.isFileNameExists(files.getFilename(),user)){
            boolean error = true;
            model.addAttribute("error",error);
            return "result";
        }
        fileService.addFiles(files);
        return "home";
    }

    @PostMapping("/savenotes")
    public String updateNotes(Authentication authentication, @ModelAttribute("files") UserFiles files, @ModelAttribute("notes") UserNotes notes, @ModelAttribute("credentials") UserCredentials credentials, Model model){
        User user = userService.getUser(authentication.getName());
        notes.setUserid(user.getUserId());
        List<UserNotes> userNotes = notesService.getUserNotes(authentication.getName());
        boolean noteExists = false;
        for(UserNotes userNote : userNotes){
            if(notes.getNoteid() == userNote.getNoteid()){
                noteExists = true;
                break;
            }
        }
        if(!noteExists) {
            notesService.addNotes(notes);
        }
        else{
            notesService.updateNotes(notes);
        }
        model.addAttribute("successful",true);
        return "result";
    }

    @PostMapping("/savecredentials")
    public String updateCredentials(Authentication authentication, @ModelAttribute("files") UserFiles files, @ModelAttribute("notes") UserNotes notes, @ModelAttribute("credentials") UserCredentials credentials, Model model){
        return "home";
    }


}
