package com.pinyougou.shop.controller;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.GoodsDescService;
import com.pinyougou.common.Result;
import com.pinyougou.pojo.GoodsDesc;
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
@RequestMapping("/goodsDesc")
public class GoodsDescController {

	@Reference
	private GoodsDescService goodsDescService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<GoodsDesc> findAll(){
		return goodsDescService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<GoodsDesc> findPage(int page, int size){
		return goodsDescService.findPage(page, size);
	}
	
	/**
	 * 增加
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody GoodsDesc goodsDesc){
		try {
			goodsDescService.add(goodsDesc);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}
	
	/**
	 * 修改
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody GoodsDesc goodsDesc){
		try {
			goodsDescService.update(goodsDesc);
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
	public GoodsDesc findOne(Long id){
		return goodsDescService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			goodsDescService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}
	
//		/**
//	 * 查询+分页
//	 * @param goodsDesc
//	 * @param size
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public PageInfo<GoodsDesc> search(@RequestBody TbGoodsDesc goodsDesc, int page, int size  ){
//		return goodsDescService.findPage(goodsDesc, page, size);
//	}
//
}
