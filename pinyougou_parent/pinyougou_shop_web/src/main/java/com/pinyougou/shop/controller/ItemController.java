package com.pinyougou.shop.controller;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.ItemCatService;
import com.pinyougou.ItemService;
import com.pinyougou.common.Result;
import com.pinyougou.pojo.Item;
import com.pinyougou.pojo.ItemCat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	@Reference
	private ItemService itemService;

	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<Item> findPage(int page, int size){
		return itemService.findPage(page, size);
	}
	
	/**
	 * 增加
	 * @param item
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody Item item){
		try {
			itemService.add(item);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}
	
	/**
	 * 修改
	 * @param item
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody Item item){
		try {
			itemService.update(item);
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
	public Item findOne(Long id){
		return itemService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			itemService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}

	
//		/**
//	 * 查询+分页
//	 * @param brand
//	 * @param rows
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public PageInfo<Item> search(@RequestBody TbItem item, int page, int size  ){
//		return itemService.findPage(item, page, size);
//	}
//
}
