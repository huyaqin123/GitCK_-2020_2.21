package com.bdqn.mall.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdqn.mall.mall.entity.Type;
import com.bdqn.mall.mall.dao.TypeMapper;
import com.bdqn.mall.mall.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.mall.mall.vo.TypeTree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.bdqn.mall.mall.entity.Type;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-13
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<TypeTree> selectTree(int typeId) {

        QueryWrapper<Type> queryWrapper=new QueryWrapper<Type>();
        List<Type> typelist = typeMapper.selectList(queryWrapper);
        Map<Integer,Type> catMaps = new HashMap<>();
        if(typelist!=null&&typelist.size()>0){
            for(Type item:typelist){
                catMaps.put(item.getId(),item);
            }
            return setAllTree(catMaps,typeId);
        }
        return  null;
    }

    public List<TypeTree> setAllTree(Map<Integer,Type> catMaps,Integer pid){
        List<TypeTree> typeTreelist = new ArrayList<>();
        Iterator<Map.Entry<Integer, Type>> iterator = catMaps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Type> next = iterator.next();
            Type type = next.getValue();
            if(pid.equals(type.getParent())){
                TypeTree typeTree = new TypeTree();
                typeTree.setId(type.getId());
                typeTree.setTypeName(type.getTypename());
                typeTree.setPid(type.getParent());
                typeTree.setChilderen(setAllTree(catMaps,type.getId()));
                typeTreelist.add(typeTree);
            }
        }
        return typeTreelist;
    }
}
