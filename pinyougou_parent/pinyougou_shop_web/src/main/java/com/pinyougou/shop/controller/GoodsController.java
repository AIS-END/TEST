package com.pinyougou.shop.controller;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.GoodsService;
import com.pinyougou.common.Result;
import com.pinyougou.pojo.Goods;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Goods> findAll(){
		return goodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<Goods> findPage(int page, int size){
		return goodsService.findPage(page, size);
	}
	
	/**
	 * 增加Goods数据
	 * 响应数据success
	 * 响应消息message
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public HashMap<String, Object> add(@RequestBody Goods goods){
		try {
			//设置商家ID
			String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.setSellerId(sellerId);
			int add = goodsService.add(goods);
			if (add > 0) {
				return new Result(true, "增加成功").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(false, "增加失败").getMap();
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody Goods goods){
		//校验是否是当前商家的id
		Goods one = goodsService.findOne(goods.getId());
		//获取当前登录的商家id
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		//如果传递过来的商家id并不是当前登录的用户的id，则属于非法操作
		if (!one.getSellerId().equals(name) || !goods.getSellerId().equals(name)) {
//			System.out.println(name);
//			System.out.println(one.getSellerId());
//			System.out.println(goods.getSellerId());
			return new Result(false, "操作非法").getMap();
		}
		try {
			goodsService.update(goods);
			return new Result(true, "修改成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败").getMap();
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}
	
		/**
	 * 查询+分页
	 * @param goods
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping("/search")
	public PageInfo<Goods> search(@RequestBody Goods goods, int page, int size  ){
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.setSellerId(sellerId);
		return goodsService.findPage(goods, page, size);
	}
	
}
