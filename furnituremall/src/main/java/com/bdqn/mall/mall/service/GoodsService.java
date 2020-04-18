package com.bdqn.mall.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.mall.mall.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.mall.mall.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-05
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 分页查询商品列表
     * @param page
     * @param goodsVo
     * @return
     * @throws Exception
     */
    IPage<Goods> findGoodsListByPage(IPage<Goods> page, GoodsVo goodsVo) throws Exception;

    /**
     * 根据typeIdOne查询数据
     * @param typeIdOne
     * @return
     */
    List<Goods> findGoodsTypeIdNoe(int typeIdOne);
}
