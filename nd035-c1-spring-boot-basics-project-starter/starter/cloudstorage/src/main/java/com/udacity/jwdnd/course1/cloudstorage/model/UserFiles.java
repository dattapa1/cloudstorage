package com.udacity.jwdnd.course1.cloudstorage.model;

import org.apache.ibatis.type.BlobInputStreamTypeHandler;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.sql.Blob;

public class UserFiles {
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private File file;
    private Integer userid;
    private FileReader blobFile;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileDetails(){
        setFilesize(file.length() + "");
        setFilename(file.getName());
        try {
            setContenttype(Files.probeContentType(file.toPath()));
            blobFile = new FileReader(file);
        }
        catch(Exception exception){
            setContenttype("text");
        }
    }
}
