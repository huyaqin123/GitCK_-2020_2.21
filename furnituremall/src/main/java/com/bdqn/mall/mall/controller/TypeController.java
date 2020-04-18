package com.bdqn.mall.mall.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.mall.common.utils.DataGridViewResult;
import com.bdqn.mall.common.utils.ResponseShop;
import com.bdqn.mall.common.utils.ResponseUtil;
import com.bdqn.mall.mall.entity.Goods;
import com.bdqn.mall.mall.service.GoodsService;
import com.bdqn.mall.mall.service.TypeService;
import com.bdqn.mall.mall.vo.GoodsVo;
import com.bdqn.mall.mall.vo.TypeTree;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.bdqn.mall.mall.entity.Type;

import java.util.*;

@Controller
@RequestMapping("/mall/type")

public class TypeController {

    @Resource
    private TypeService typeService;

    @Resource
    private GoodsService goodsService;

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
    public String findTowType(@RequestParam(defaultValue = "1") Integer page, @PathVariable Integer typeId, GoodsVo goodsVo,String clomu ,HttpSession session, Model model){
        try {
            //自定义每页的数量
            int pageSize=4;
            //保存当前方法路径
            String lur="/mall/type/findTowType/";
            //二级分类
            List<TypeTree> categoryTow = typeService.selectTree(typeId);
            //查询一级分类商品
            List<Goods> goodsList= goodsService.findGoodsTypeIdNoe(typeId);

            if (clomu!=null){
               goodsList=sort(goodsList,clomu);
            }
            //计算集合中的分页
            int toIndex=goodsList.size()> page * pageSize ?page * pageSize : goodsList.size();
            //将一级分类的Id保存到会话
            session.setAttribute("TypeOneId",typeId);
            model.addAttribute("goodsList",goodsList.subList((page-1)*pageSize,toIndex));
            model.addAttribute("categoryTow",categoryTow);
            model.addAttribute("pageCode",getUpAndDownPage(page,goodsList.size(),lur,typeId,pageSize));
            model.addAttribute("sort",getUpdateDownSort(lur,typeId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mall/detail/list";
    }

    /**
     * 查询三级分类
     * @param typeId
     * @return
     */
    @RequestMapping("/findThreeType/{typeId}")
    public String findThreeType(@RequestParam(defaultValue = "1") Integer page,@PathVariable Integer typeId,String clomu,HttpSession session,Model model){
        //自定义每页的数量
        int pageSize=4;
        //保存当前方法路径
        String lur="/mall/type/findThreeType/";
        //将二级分类的Id保存到会话
        session.setAttribute("TypeTowId",typeId);
        //获取一级ID
       Integer TypeOneId= (Integer) session.getAttribute("TypeOneId");
        //二级分类
        List<TypeTree> categoryTow = typeService.selectTree(TypeOneId);
        //第三级分类
        List<TypeTree> categoryTrees = typeService.selectTree(typeId);
        //查询二级分类商品
        List<Goods> goodsList=goodsService.findGoodsTypeIdNoe(typeId);
        //装三级分类商品
        List<Goods> goods=null;
        for (int i = 0; i <categoryTrees.size() ; i++) {
            //循环三级分类id查询三级分类商品
            goods=goodsService.findGoodsTypeIdNoe(categoryTrees.get(i).getId());
            //判断是否有商品
            if (goods!=null){
                //循环商品并添加到goodsList集合
                for (int j = 0; j < goods.size(); j++) {
                    goodsList.add(goods.get(i));
                }
            }
        }
        //排序
        if (clomu!=null){
            sort(goodsList,clomu);
        }
        //计算集合中的分页
        int toIndex=goodsList.size()> page * pageSize ?page * pageSize : goodsList.size();
        model.addAttribute("goodsList",goodsList.subList((page-1)*pageSize,toIndex));
        model.addAttribute("categoryTow",categoryTow);
        model.addAttribute("categoryTrees",categoryTrees);
        model.addAttribute("pageCode",getUpAndDownPage(page,goodsList.size(),lur,typeId,pageSize));
        model.addAttribute("sort",getUpdateDownSort(lur,typeId));
        return "mall/detail/list";
    }

    /**
     * 查询三级商品
     * @param typeId
     * @return
     */
    @RequestMapping("/goodsList/{typeId}")
    public String goodsList(@RequestParam(defaultValue = "1") Integer page,@PathVariable Integer typeId,String clomu,HttpSession session,Model model) {
        //自定义每页的数量
        int pageSize=4;
        //保存当前方法路径
        String lur="/mall/type/goodsList/";
        //获取一级ID
        Integer TypeOneId= (Integer) session.getAttribute("TypeOneId");
        //获取二级ID
        Integer TypeTowId= (Integer) session.getAttribute("TypeTowId");
        //二级分类
        List<TypeTree> categoryTow = typeService.selectTree(TypeOneId);
        //第三级分类
        List<TypeTree> categoryTrees = typeService.selectTree(TypeTowId);
        //三级商品
        List<Goods> goodsList = goodsService.findGoodsTypeIdNoe(typeId);
        //排序
        if (clomu!=null){
            sort(goodsList,clomu);
        }
        //计算集合中的分页
        int toIndex=goodsList.size()> page * pageSize ?page * pageSize : goodsList.size();
        model.addAttribute("categoryTow",categoryTow);
        model.addAttribute("categoryTrees",categoryTrees);
        model.addAttribute("goodsList",goodsList.subList((page-1)*pageSize,toIndex));
        model.addAttribute("pageCode",getUpAndDownPage(page,goodsList.size(),lur,typeId,pageSize));
        model.addAttribute("sort",getUpdateDownSort(lur,typeId));

        return "mall/detail/list";
    }

    /**
     * 排序
     * @return
     */
    private List sort(List<Goods> list,String cloum){
        if (cloum.equals("price")){
                Collections.sort(list, new Comparator<Goods>() {
                    public int compare(Goods arg0, Goods arg1) {
                        return arg0.getoPrice().compareTo(arg1.getoPrice());
                    }
                });
        }else if (cloum.equals("time")){
            Collections.sort(list, new Comparator<Goods>() {
                public int compare(Goods arg0, Goods arg1) {
                    return arg0.getShelfTime().compareTo(arg1.getShelfTime());
                }
            });
        }
        return list;
    }


    private String getUpdateDownSort(String lur,Integer typeId){
        StringBuilder SortCode=new StringBuilder();
        SortCode.append("<li class='active'>默认</li>");
        SortCode.append("<li id='price'><a href='"+lur+""+typeId+"?clomu=price'>价格</a></li>");
        SortCode.append("<li id='Time'><a href='"+lur+""+typeId+"?clomu=time'>上架时间</a></li>");
        return SortCode.toString();
    }



    private String getUpAndDownPage(Integer page, Integer size,String lur,Integer typeId,Integer pageSize) {
        long totalPage=size%pageSize==0?size/pageSize:size/pageSize+1;
        StringBuilder pageCode=new StringBuilder();
        if (totalPage==0){
            return "";
        }else {
            pageCode.append("<div class='layui-box layui-laypage layui-laypage-default'>");
            if (page>1){
                pageCode.append("<a href='"+lur+""+typeId+"?page="+(page-1)+"' class='layui-laypage-prev'>上一页</a>");
            }else {
                pageCode.append("<a href='#' class='layui-laypage=prev layui-disabled'>上一页</a>");
            }
            if (page<totalPage){
                pageCode.append("<a href='"+lur+""+typeId+"?page="+(page+1)+"' class='layui-laypage-next'>下一页</a>");
            }else {
                pageCode.append("<a href='#' class='layui-laypage=prev layui-disabled'>下一页</a>");
            }
            pageCode.append("</div>");
        }
        return pageCode.toString();
    }
}

