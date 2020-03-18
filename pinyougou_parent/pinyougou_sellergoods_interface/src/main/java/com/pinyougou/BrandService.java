package com.pinyougou.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Brand;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BrandService {

	/**
	 * 查询品牌的id和name，给前端下拉列表select2使用，select2只认id和text两个key
	 * @return
	 */
	public List<Map<String,Object>> selectOptionList();

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Brand> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<Brand> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Brand brand);
	
	
	/**
	 * 修改
	 */
	public void update(Brand brand);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Brand findOne(Long id);
	
	
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
	public PageInfo<Brand> findPage(Brand brand, int pageNum, int pageSize);
	
}
