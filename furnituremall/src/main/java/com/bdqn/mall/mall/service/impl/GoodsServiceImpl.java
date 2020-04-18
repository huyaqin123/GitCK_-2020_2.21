package com.bdqn.mall.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.mall.mall.entity.Goods;
import com.bdqn.mall.mall.dao.GoodsMapper;
import com.bdqn.mall.mall.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.mall.mall.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public IPage<Goods> findGoodsListByPage(IPage<Goods> page, GoodsVo goodsVo) throws Exception {
        return goodsMapper.findGoodsListByPage(page,goodsVo);
    }

    /**
     * 根据typeIdOne查询商品
     * @param typeIdOne
     * @return
     */
    @Override
    public List<Goods> findGoodsTypeIdNoe(int typeIdOne) {
        return goodsMapper.findGoodsTypeIdNoe(typeIdOne);
    }
}
