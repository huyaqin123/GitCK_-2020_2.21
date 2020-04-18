package com.bdqn.mall.mall.service;

import com.bdqn.mall.mall.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.mall.mall.vo.TypeTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-13
 */
public interface TypeService extends IService<Type> {

    /**
     * 根据typeId查询分类
     * @param typeId
     * @return
     */
    List<TypeTree> selectTree(int typeId);

}
