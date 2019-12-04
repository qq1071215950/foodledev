package com.haojing.controller;


import com.haojing.enums.YesOrNo;
import com.haojing.pojo.Carousel;
import com.haojing.pojo.Category;
import com.haojing.service.CarouselService;
import com.haojing.service.CategoryService;
import com.haojing.utils.JSONresult;
import com.haojing.vo.CategoryVO;
import com.haojing.vo.NewItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "首页",tags = {"首页相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查看首页轮播图", notes = "首页轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONresult carousel(){
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return JSONresult.ok(list);
    }

    /**
     * 首页分裂展示需求
     * 1 第一次更新主页时，查询大分类，渲染到首页
     * 2 如果鼠标移动到大类来，加载子分类的内容，如果存在子分类时，则不需要加载子分类的内容。
     */
    @ApiOperation(value = "查询一级分类", notes = "查询一级分类", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONresult cats(){
        List<Category> list = categoryService.queryAllRootLevelCat();
        return JSONresult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONresult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id", required = true)
            @PathVariable Integer rootCatId){
        if (rootCatId == null){
            return JSONresult.errorMsg("分类不存在");
        }
        List<CategoryVO> list = categoryService.querySubCatList(rootCatId);
        return JSONresult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的商品", notes = "查询每个一级分类下的商品", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONresult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类id", required = true)
            @PathVariable Integer rootCatId){
        if (rootCatId == null){
            return JSONresult.errorMsg("分类不存在");
        }
        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONresult.ok(list);
    }
}
