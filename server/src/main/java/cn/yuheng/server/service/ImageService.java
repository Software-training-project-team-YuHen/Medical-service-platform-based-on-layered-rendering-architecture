package cn.yuheng.server.service;

import cn.yuheng.server.mapper.ImageMapper;
import cn.yuheng.server.model.Image;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/10 上午9:10
 */
@Service
public class ImageService {
    @Autowired
    @Setter
    private ImageMapper imageDao;

    public Image getByID(Integer id) {
        Image image = imageDao.selectByPrimaryKey(id);
        return image;
    }

    public boolean removeByID(Integer id) {
        int change = imageDao.deleteByPrimaryKey(id);
        return change == 1;
    }

    public boolean add(Image image) {
        int change = imageDao.insert(image);
        return change == 1;
    }

    public boolean update(Image image) {
        int change = imageDao.updateByPrimaryKeySelective(image);
        return change == 1;
    }
}
