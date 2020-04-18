package com.bdqn.mall.mall.controller.admin;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.mall.mall.entity.Type;
import com.bdqn.mall.mall.service.TypeService;
import com.bdqn.mall.common.utils.DataGridViewResult;
import com.bdqn.mall.common.utils.JSONResult;
import com.bdqn.mall.common.utils.SystemConstants;
import com.bdqn.mall.common.utils.TreeNode;
import com.bdqn.mall.mall.vo.TypeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-01
 */
@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Resource
    private TypeService typeService;

    /**
     * 加载left.html左侧菜单树
     */
    @RequestMapping("/loadTypeTreeLeft")
    public DataGridViewResult loadDeptTreeLeft(){
        //查询所有部门
        List<Type> typeList = typeService.list();
        //创建节点集合
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //循环遍历部门集合
        for (Type type : typeList) {
            //是否展开
            Boolean spread = type.getOpen()== SystemConstants.OPEN_TRUE ?true:false;
            //封装树节点
            TreeNode treeNode=new TreeNode();
            treeNode.setId(type.getId());
            treeNode.setPid(type.getParent());
            treeNode.setTitle(type.getTypename());
            treeNode.setSpread(spread);
            //将树节点对象添加到节点集合中
            treeNodes.add(treeNode);
          }
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 加载右边数据表格数据
     * @param typeVo
     * @return
     */
    @RequestMapping("/typeList")
    public DataGridViewResult findDeptList(TypeVo typeVo){
        //创建分页对象
        IPage<Type> page = new Page<Type>(typeVo.getPage(),typeVo.getLimit());
        //创建条件构造器
        QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
        //部门名称查询
        queryWrapper.like(StringUtils.isNotBlank(typeVo.getTypename()),"typename",typeVo.getTypename());
        //编号
        queryWrapper.eq(typeVo.getId()!=null,"id",typeVo.getId()).or().eq(typeVo.getId()!=null,"parent",typeVo.getId());
        //排序
        queryWrapper.orderByAsc("id");
        //分页查询
        typeService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品类型
     * @param type
     * @return
     */
    @PostMapping("/addType")
    public JSONResult addDept(Type type){
        type.setType(type.getParent()+1);
        try {
            //调用新增的方法
            if(typeService.save(type)){
                //新增成功
                return SystemConstants.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstants.ADD_ERROR;
    }

    /**
     * 修改商品类型
     * @param type
     * @return
     */
    @PostMapping("/updateType")
    public JSONResult updateDept(Type type){
        try {
            type.setType(type.getParent()+1);
            //调用修改的方法
            if(typeService.updateById(type)){
                //修改成功
                return SystemConstants.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstants.UPDATE_ERROR;
    }

    /**
     * 判断当前节点是否有子节点
     * @param id
     * @return
     */
    @RequestMapping("/checkTypeHasChildren")
    public String checkDeptHasChildren(int id){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        //构建条件对象
        QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
        queryWrapper.eq("parent",id);//查询父节点下是否有数据
        //查询
        int count = typeService.count(queryWrapper);
        //判断集合是否有数据，有则不能删除
        if(count>0){
            map.put(SystemConstants.EXIST,true);//存在
            map.put(SystemConstants.MESSAGE,"对不起，当前商品类型节点下有子节点，无法删除");
        }else{
            map.put(SystemConstants.EXIST,false);//不存在
        }
        return JSON.toJSONString(map);
    }

        /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //删除成功
        if(typeService.removeById(id)){
            return SystemConstants.DELETE_SUCCESS;
        }
        //删除失败
        return SystemConstants.DELETE_ERROR;
    }
}

