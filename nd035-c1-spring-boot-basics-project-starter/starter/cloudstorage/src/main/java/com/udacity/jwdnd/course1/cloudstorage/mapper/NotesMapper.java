package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNotes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<UserNotes> getAllNotesOfUser(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNotes(UserNotes userNotes);

    @Update("UPDATE NOTES SET notetitle = #{notetitle} , notedescription = #{notedescription} WHERE noteid = #{noteid}")
    boolean updateNotes(UserNotes userNotes);

    @Delete("DELETE FROM NOTES WHERE notetitle = #{notetitle}")
    int deleteNotes(String notetitle);
}
