package com.flyimg.service.impl;

import com.flyimg.dao.ImgAndAlbumMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.pojo.ImgAndAlbum;
import com.flyimg.service.ImgAndAlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tiansh
 * @version 1.0
 * @date 2019/12/19 15:40
 */
@Service
public class ImgAndAlbumServiceImpl implements ImgAndAlbumService {
    @Resource
    ImgAndAlbumMapper imgAndAlbumMapper;

    @Override
    public Integer addImgAndAlbum(ImgAndAlbum imgAndAlbum) {
        return imgAndAlbumMapper.addImgAndAlbum(imgAndAlbum);
    }

    @Override
    public List<ImgAndAlbum> getAlbumForImgname(String imgname) {
        return imgAndAlbumMapper.getAlbumForImgname(imgname);
    }

    @Override
    public Integer deleteImgAndAlbum(String imgname) {
        return imgAndAlbumMapper.deleteImgAndAlbum(imgname);
    }

    @Override
    public Integer deleteImgAndAlbumForKey(String albumkey) {
        return imgAndAlbumMapper.deleteImgAndAlbumForKey(albumkey);
    }

    @Override
    public List<FileOss> selectImgForAlbumkey(String albumkey) {
        return imgAndAlbumMapper.selectImgForAlbumkey(albumkey);
    }
}
