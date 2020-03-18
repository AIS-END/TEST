package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.Result;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.Content;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/content")
public class ContentController {

	@Reference
	private ContentService contentService;

	/**
	 * 返回全部列表
	 *
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Content> findAll() {
		return contentService.findAll();
	}


	/**
	 * 返回全部列表
	 *
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo<Content> findPage(int page, int size) {
		return contentService.findPage(page, size);
	}

	/**
	 * 增加
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/add")
	public HashMap<String, Object> add(@RequestBody Content content) {
		try {
			contentService.add(content);
			return new Result(true, "增加成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败").getMap();
		}
	}

	/**
	 * 修改
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/update")
	public HashMap<String, Object> update(@RequestBody Content content) {
		try {
			contentService.update(content);
			return new Result(true, "修改成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败").getMap();
		}
	}

	/**
	 * 获取实体
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Content findOne(Long id) {
		return contentService.findOne(id);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public HashMap<String, Object> delete(Long[] ids) {
		try {
			contentService.delete(ids);
			return new Result(true, "删除成功").getMap();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败").getMap();
		}
	}

//	/**
//	 * 查询+分页
//	 *
//	 * @param content
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public PageInfo<Content> search(@RequestBody Content content, int page, int size  ){
//		return contentService.findPage(content, page, size);
//	}


	@RequestMapping("/findByCategoryId")
	public List<Content> findByCategoryId(Long categoryId) {
		System.out.println("运行findByCategoryId");
		return contentService.findByCategoryId(categoryId);
	}
}
