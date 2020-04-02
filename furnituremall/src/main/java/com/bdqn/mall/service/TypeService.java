package com.bdqn.mall.service;

import com.bdqn.mall.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.mall.vo.TypeTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-01
 */
public interface TypeService extends IService<Type>{

    List<TypeTree> selectTree(int typeId);
}
