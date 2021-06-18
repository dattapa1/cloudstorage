package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFiles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper notesMapper, UserMapper userMapper) {
        this.fileMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public List<UserFiles> getUserFiles(String userName){
        User user = userMapper.getUser(userName);
        return fileMapper.getFiles(user.getUserId());
    }

    public boolean isFileNameExists(String filename, User user){
        boolean exists = false;
        List<UserFiles> userFiles = getUserFiles(user.getUsername());
        for(UserFiles userFile : userFiles){
            if(filename.equals(userFile.getFilename())){
                exists = true;
            }
        }
        return exists;
    }

    public void addFiles(UserFiles userFiles){
        fileMapper.addFile(userFiles);
    }
}
