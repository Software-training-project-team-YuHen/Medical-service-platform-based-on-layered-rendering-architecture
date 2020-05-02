package cn.yuheng.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * image
 *
 * @author
 */
@Data
public class Image implements Serializable {
    private Integer id;

    private byte[] image;

    private static final long serialVersionUID = 1L;
}