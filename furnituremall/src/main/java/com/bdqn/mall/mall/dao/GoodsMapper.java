package com.bdqn.mall.mall.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.mall.mall.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.mall.mall.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-05
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 分页查询商品列表
     * @param page
     * @param goodsVo
     * @return
     * @throws Exception
     */
    IPage<Goods> findGoodsListByPage(@Param("page") IPage<Goods> page, @Param("goods") GoodsVo goodsVo) throws Exception;

    /**
     * 根据typeIdOne查询商品
     * @param typeIdOne
     * @return
     */
    List<Goods> findGoodsTypeIdNoe(int typeIdOne);
}
