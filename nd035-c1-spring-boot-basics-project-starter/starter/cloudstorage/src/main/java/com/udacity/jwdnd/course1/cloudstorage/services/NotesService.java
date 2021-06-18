package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNotes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;
    private UserMapper userMapper;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public void addNotes(UserNotes userNotes){
        notesMapper.addNotes(userNotes);
    }

    public void updateNotes(UserNotes userNotes){
        notesMapper.updateNotes(userNotes);
    }

    public List<UserNotes> getUserNotes(String username){
        User user = userMapper.getUser(username);
        return notesMapper.getAllNotesOfUser(user.getUserId());
    }
}
