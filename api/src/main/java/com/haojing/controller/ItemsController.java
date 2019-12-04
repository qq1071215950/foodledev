package com.haojing.controller;

import com.haojing.pojo.Items;
import com.haojing.pojo.ItemsImg;
import com.haojing.pojo.ItemsParam;
import com.haojing.pojo.ItemsSpec;
import com.haojing.service.ItemService;
import com.haojing.utils.JSONresult;
import com.haojing.utils.PagedGridResult;
import com.haojing.vo.CommentLevelCountsVO;
import com.haojing.vo.ItemInfoVO;
import com.haojing.vo.ShopcartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品信息",tags = {"商品信息相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController{

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONresult info(
            @ApiParam(name = "itemId",value = "商品id", required = true)
            @PathVariable String itemId){
       if (StringUtils.isBlank(itemId)){
           return JSONresult.errorMsg(null);
       }
       Items items = itemService.queryItemById(itemId);
       List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
       List<ItemsSpec> itemsSpecList = itemService.queryItemsSpecList(itemId);
       ItemsParam itemsParam =  itemService.queryItemsParam(itemId);
       ItemInfoVO itemInfoVO = new ItemInfoVO();
       itemInfoVO.setItem(items);
       itemInfoVO.setItemImgList(itemsImgList);
       itemInfoVO.setItemSpecList(itemsSpecList);
       itemInfoVO.setItemParams(itemsParam);
       return JSONresult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等价", notes = "查询商品评价等价", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONresult commentLevel(
            @ApiParam(name = "itemId",value = "商品id", required = true)
            @RequestParam String itemId){
        if (StringUtils.isBlank(itemId)){
            return JSONresult.errorMsg(null);
        }
        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);
        return JSONresult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评价", notes = "查询商品评价", httpMethod = "GET")
    @GetMapping("/comments")
    public JSONresult comments(
            @ApiParam(name = "itemId",value = "商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "分页大小", required = false)
            @RequestParam Integer pageSize){
        if (StringUtils.isBlank(itemId)){
            return JSONresult.errorMsg(null);
        }
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult grid = itemService.queryPageComments(itemId,level,page,pageSize);
        return JSONresult.ok(grid);
    }


    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public JSONresult search(
            @ApiParam(name = "keywords",value = "搜索关键字", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort",value = "排序", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "分页大小", required = false)
            @RequestParam Integer pageSize){
        if (StringUtils.isBlank(keywords)){
            return JSONresult.errorMsg(null);
        }
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult grid = itemService.searchItems(keywords,sort,page,pageSize);
        return JSONresult.ok(grid);
    }

    @ApiOperation(value = "分类id搜索商品列表", notes = "分类搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public JSONresult catItems(
            @ApiParam(name = "catId",value = "三级分类id", required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort",value = "排序", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "分页大小", required = false)
            @RequestParam Integer pageSize){
        if (catId == null){
            return JSONresult.errorMsg(null);
        }
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult grid = itemService.searchItemsByThirdCat(catId,sort,page,pageSize);
        return JSONresult.ok(grid);
    }

    @ApiOperation(value = "根据商品规格ids查找最新的商品数量", notes = "根据商品规格ids查找最新的商品数量", httpMethod = "GET")
    @GetMapping("/refresh")
    public JSONresult refresh(
            @ApiParam(name = "itemSpecIds",value = "规格ids", required = true)
            @RequestParam String itemSpecIds){
        if (StringUtils.isBlank(itemSpecIds)){
            return JSONresult.ok();
        }
        List<ShopcartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return JSONresult.ok(list);
    }
}
