package com.bdqn.mall.mall.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.mall.mall.entity.Goods;
import com.bdqn.mall.mall.entity.Type;
import com.bdqn.mall.mall.service.GoodsService;
import com.bdqn.mall.mall.service.TypeService;
import com.bdqn.mall.common.utils.DataGridViewResult;
import com.bdqn.mall.common.utils.FileUploadUtils;
import com.bdqn.mall.common.utils.JSONResult;
import com.bdqn.mall.common.utils.SystemConstants;
import com.bdqn.mall.mall.vo.GoodsVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private TypeService typeService;

    /**
     * 查看商品列表
     * @param goodsVo
     * @return
     */
    @RequestMapping("/goodsList")
    public DataGridViewResult goodsList(GoodsVo goodsVo){
        //创建分页对象
        IPage<Goods> page = new Page<Goods>(goodsVo.getPage(),goodsVo.getLimit());
        try {
            //调用方法
            goodsService.findGoodsListByPage(page,goodsVo);
            //返回
            return new DataGridViewResult(page.getTotal(),page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商品添加
     * @param goods
     * @return
     */
    @RequestMapping("/addGoods")
    public JSONResult addGoods(Goods goods){
        //判断图片文件名称后缀是否有_temp，有的话就要去到_temp后缀
        if(goods!=null && goods.getGoodsImg()!=null && goods.getGoodsImg().endsWith("_temp")){
            //重名名文件（将后缀_temp去掉）
            String newFileName = FileUploadUtils.renameFile(goods.getGoodsImg());
            //赋值
            goods.setGoodsImg(newFileName);
        }
        //判断是否有三级分类，没有则只赋值一二级分类即可
        if (getGoodsPid(getGoodsPid(goods.getTypeId()))!=null) {
            goods.setTypeIdTwo(getGoodsPid(goods.getTypeId()));
            goods.setTypeIdOne(getGoodsPid(getGoodsPid(goods.getTypeId())));
        }else {
            goods.setTypeIdTwo(getGoodsPid(goods.getTypeId()));
        }
        //当商品上架后，给上架时间赋值
        if (goods.getIsShelf()==SystemConstants.SHELFTIME){
            goods.setShelfTime(new Date());
        }
        goods.setStorageTime(new Date());//入库时间
        goods.setInventory(goods.getSupply());//库存
        if(goodsService.save(goods)){
            return SystemConstants.ADD_SUCCESS;
        }
        return SystemConstants.ADD_ERROR;
    }

    @RequestMapping("/updateGoods")
    public JSONResult updateGoods(Goods goods){
        //判断当前商品的图片是否是默认图片，若不是默认图片，则就要去掉_temp后缀
        if(!(goods!=null && goods.getGoodsImg().equals(SystemConstants.DEFAULT_GOODS_IMAGE))){
            //判断文件名称是否是_temp结尾
            if(goods.getGoodsImg().endsWith("_temp")){
                //重名名文件（将后缀_temp去掉）
                String newFileName = FileUploadUtils.renameFile(goods.getGoodsImg());
                //赋值
                goods.setGoodsImg(newFileName);
                //删除旧文件
                String oldFile =  goodsService.getById(goods.getId()).getGoodsImg();
                //删除
                FileUploadUtils.deleteFile(oldFile);
            }
        }
        //判断是否有三级分类，没有则只赋值一二级分类即可
        if (getGoodsPid(getGoodsPid(goods.getTypeId()))!=null) {
            goods.setTypeIdTwo(getGoodsPid(goods.getTypeId()));
            goods.setTypeIdOne(getGoodsPid(getGoodsPid(goods.getTypeId())));
        }else {
            goods.setTypeIdTwo(getGoodsPid(goods.getTypeId()));
        }
        if (goods.getIsShelf()==SystemConstants.SHELFTIME){
            goods.setShelfTime(new Date());
        }
        //当商品上架后，给上架时间赋值
        goods.setStorageTime(new Date());
        goods.setInventory(goods.getSupply());//库存
        if(goodsService.updateById(goods)){
            return SystemConstants.UPDATE_SUCCESS;
        }
        return SystemConstants.UPDATE_ERROR;
    }

    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id,String goodsImg){
        if(goodsService.removeById(id)){
            //删除源文件
            FileUploadUtils.deleteFile(goodsImg);
            return SystemConstants.DELETE_SUCCESS;
        }
        return SystemConstants.DELETE_ERROR;
    }


    /**
     * 根据编号查询父级编号
     * @param typeId
     * @return
     */
    public Integer getGoodsPid(Integer typeId){
        QueryWrapper<Type> queryWrapper=new QueryWrapper<Type>();
        queryWrapper.eq("id",typeId);
        Type type =  typeService.getOne(queryWrapper);
        return type.getParent();
    }
}
