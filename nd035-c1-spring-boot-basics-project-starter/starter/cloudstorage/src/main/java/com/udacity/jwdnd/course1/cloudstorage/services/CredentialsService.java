package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;
    private UserMapper userMapper;

    public CredentialsService(CredentialsMapper notesMapper, UserMapper userMapper) {
        this.credentialsMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public List<UserCredentials> getUserCredentials(String userName){
        User user = userMapper.getUser(userName);
        return credentialsMapper.getCredentials(user.getUserId());
    }
}
