package com.bdqn.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdqn.mall.entity.Type;
import com.bdqn.mall.service.TypeService;
import com.bdqn.mall.utils.JSONResult;
import com.bdqn.mall.utils.ResponseShop;
import com.bdqn.mall.utils.ResponseUtil;
import com.bdqn.mall.vo.TypeTree;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-01
 */
@Controller
@RequestMapping("/mall/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 查询一级分类
     * @return
     */
    @RequestMapping(value = "/findOneType",method = RequestMethod.GET)
    @ResponseBody
    public ResponseShop findOneTypee(){
        QueryWrapper<Type> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type","1");
        List<Type> types=typeService.list(queryWrapper);
        return ResponseUtil.ok(types);
    }

    /**
     * 查询二级分类
     * @param typeId
     * @return
     */
    @RequestMapping("/findTowType/{typeId}")
    public String findTowType(@PathVariable int typeId, Model model){
        List<TypeTree> categoryTrees = typeService.selectTree(typeId);
        model.addAttribute("categoryTrees",categoryTrees);
        return "/detail/list";
    }

    /**
     * 查询三级分类
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping("/findThreeType")
    public ResponseShop findThreeType(int pid){
        List<TypeTree> categoryTrees = typeService.selectTree(pid);
        return ResponseUtil.ok(categoryTrees);
    }
}

