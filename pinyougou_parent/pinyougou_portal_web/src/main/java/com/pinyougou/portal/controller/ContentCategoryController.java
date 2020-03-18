package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.Result;
import com.pinyougou.content.service.ContentCategoryService;
import com.pinyougou.pojo.ContentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

	@Reference
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<ContentCategory> findAll(){
		return contentCategoryService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<ContentCategory> findPage(int page, int size){
		return contentCategoryService.findPage(page, size);
	}
	
	/**
	 * 增加
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody ContentCategory contentCategory){
		try {
			contentCategoryService.add(contentCategory);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}
	
	/**
	 * 修改
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody ContentCategory contentCategory){
		try {
			contentCategoryService.update(contentCategory);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public ContentCategory findOne(Long id){
		return contentCategoryService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			contentCategoryService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}
	
		/**
	 * 查询+分页
	 * @param contentCategory
	 * @param page
	 * @param size
	 * @return
	 */
//	@RequestMapping("/search")
//	public PageInfo<ContentCategory> search(@RequestBody ContentCategory contentCategory, int page, int size  ){
//		return contentCategoryService.findPage(contentCategory, page, size);
//	}


	
}
