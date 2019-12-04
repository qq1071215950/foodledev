package com.haojing.service;

import com.haojing.pojo.Carousel;

import java.util.List;

/**
 * 轮播图
 */
public interface CarouselService {

    /**
     * 查询轮播图
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
