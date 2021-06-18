package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFiles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<UserFiles> getFiles(int userid);

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{blobFile})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int addFile(UserFiles userFiles);
}
