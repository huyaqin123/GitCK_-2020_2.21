package com.bdqn.mall.mall.controller.admin;


import com.bdqn.mall.mall.service.GoodsProviderService;
import com.bdqn.mall.common.utils.DataGridViewResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/admin/provider")
public class AdminGoodsProviderController {

    @Resource
    private GoodsProviderService goodsProviderService;

    /**
     * 查询所有商品
     * @return
     */
    @RequestMapping("/findAllProviderList")
    public DataGridViewResult findAllProviderList(){
        return new DataGridViewResult(goodsProviderService.list());
    }
}

