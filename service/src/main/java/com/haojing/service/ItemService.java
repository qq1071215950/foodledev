package com.haojing.service;

import com.haojing.pojo.Items;
import com.haojing.pojo.ItemsImg;
import com.haojing.pojo.ItemsParam;
import com.haojing.pojo.ItemsSpec;
import com.haojing.utils.PagedGridResult;
import com.haojing.vo.CommentLevelCountsVO;
import com.haojing.vo.ItemCommentVO;
import com.haojing.vo.ShopcartVO;

import java.util.List;

/**
 * 商品接口服务
 */
public interface ItemService {

    /**
     * 根据商品id查询商品信息
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品Id查询商品图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**根据商品Id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemsSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemsParam(String itemId);

    /**
     * 根据商品id查询商品评价
     * @param itemId
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 分页查询商品的评价
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 商品搜索列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 按分类查询
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);


    /**
     * 规格Ids 查询购物车的商品数据
     * @param specIds
     * @return
     */
    public List<ShopcartVO> queryItemsBySpecIds(String specIds);

    public ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获取商品的主图
     * @param itemId
     * @return
     */
    public String queryItemMainImgById(String itemId);

    /**
     * 扣库存
     */
    public void decreaseItemSpecStock(String specId, int buyCounts);
}
