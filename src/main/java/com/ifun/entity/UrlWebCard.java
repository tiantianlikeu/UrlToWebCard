package com.ifun.entity;

import lombok.Data;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/11 15:52
 */
@Data
public class UrlWebCard {

    /**
     * 链接
     */
    private String url;

    /**
     * 图片url 链接
     */
    private String imgUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;
}
