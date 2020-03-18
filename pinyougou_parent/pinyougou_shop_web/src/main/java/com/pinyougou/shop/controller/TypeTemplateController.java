package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.TypeTemplateService;
import com.pinyougou.common.Result;
import com.pinyougou.pojo.TypeTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<TypeTemplate> findPage(int page, int size){
		return typeTemplateService.findPage(page, size);
	}
	
	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody TypeTemplate typeTemplate){
		try {
			typeTemplateService.add(typeTemplate);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody TypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
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
	public TypeTemplate findOne(Long id){
		return typeTemplateService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}
	
		/**
	 * 查询+分页
	 * @param typeTemplate
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/search")
	public PageInfo<TypeTemplate> search(@RequestBody TypeTemplate typeTemplate, int page, int size  ){
		return typeTemplateService.findPage(typeTemplate, page, size);
	}

	/**
	 * 根据模板id查询规格选项
	 * @param id
	 * @return
	 */
	@RequestMapping("/findSpecList")
	public List<Map> findSpecList(Long id) {
		List<Map> specList = typeTemplateService.findSpecList(id);
		return specList;
	}

	
}
