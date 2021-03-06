package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.FreightTemplate;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface FreightTemplateService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<FreightTemplate> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<FreightTemplate> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(FreightTemplate freightTemplate);
	
	
	/**
	 * 修改
	 */
	public void update(FreightTemplate freightTemplate);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public FreightTemplate findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
//	public PageInfo<FreightTemplate> findPage(FreightTemplate freightTemplate, int pageNum, int pageSize);
	
}
