package com.haojing.mapper;

import com.haojing.vo.ItemCommentVO;
import com.haojing.vo.SearchItemsVO;
import com.haojing.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {

    /**
     * 查询商品的评价
     * @param map
     * @return
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    /**
     * 搜索商品
     * @param map
     * @return
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    /**
     * 按分类搜索
     * @param map
     * @return
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    /**
     * 渲染购物车
     * @param specIdsList
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    /**
     * 数据库乐观锁的形式扣库存
     * @param specId
     * @param pendingCounts
     * @return
     */
    public int decreaseItemSpecStock(@Param("specId") String specId,@Param("pendingCounts") int pendingCounts);
}