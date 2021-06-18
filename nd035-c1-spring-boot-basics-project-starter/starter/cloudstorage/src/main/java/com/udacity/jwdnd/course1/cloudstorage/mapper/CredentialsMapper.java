package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredentials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<UserCredentials> getCredentials(int userid);
}
