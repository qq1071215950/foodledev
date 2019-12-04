package com.haojing.service;

import com.haojing.pojo.Category;
import com.haojing.vo.CategoryVO;
import com.haojing.vo.NewItemsVO;

import java.util.List;

/**
 * 分类接口服务
 */
public interface CategoryService {

    /**
     * 查询一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();


    /**
     * 查询二级分类
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> querySubCatList(Integer rootCatId);

    /**
     * 首页查询一级分类下的六个商品
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
