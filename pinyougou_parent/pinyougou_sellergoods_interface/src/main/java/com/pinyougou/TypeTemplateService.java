package com.pinyougou;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.pinyougou.pojo.TypeTemplate;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface TypeTemplateService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TypeTemplate> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<TypeTemplate> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TypeTemplate typeTemplate);
	
	
	/**
	 * 修改
	 */
	public void update(TypeTemplate typeTemplate);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TypeTemplate findOne(Long id);
	
	
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
	public PageInfo<TypeTemplate> findPage(TypeTemplate typeTemplate, int pageNum, int pageSize);

	/**
	 * 由于在模板表中只存储了规格名称，没有规格值，所以新增方法查询规格列表（规格名+规格值）
	 * 返回规格列表
	 * @param id
	 * @return
	 */
	public List<Map> findSpecList(Long id);
	
}
