package com.flyimg.service;


import com.flyimg.pojo.FileSave;

public interface FileSaveService {

    Integer  add(FileSave fileSave);

    FileSave getByMd5(String md5);

}
