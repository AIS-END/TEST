package com.pinyougou.shop.controller;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.ItemCatService;
import com.pinyougou.common.Result;
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
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<ItemCat> findAll(){
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<ItemCat> findPage(int page, int size){
		return itemCatService.findPage(page, size);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody ItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody ItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public ItemCat findOne(Long id){
		return itemCatService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			itemCatService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}

	/**
	 * 根据上级ID查询列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/findByParentId")
	public List<ItemCat> findByParentId(Long parentId){
		return itemCatService.findByParentId(parentId);
	}
	
//		/**
//	 * 查询+分页
//	 * @param brand
//	 * @param rows
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public PageInfo<ItemCat> search(@RequestBody TbItemCat itemCat, int page, int size  ){
//		return itemCatService.findPage(itemCat, page, size);
//	}
	
}
