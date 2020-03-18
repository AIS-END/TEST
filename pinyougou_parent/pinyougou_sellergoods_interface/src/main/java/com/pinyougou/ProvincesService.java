package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Provinces;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ProvincesService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Provinces> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<Provinces> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Provinces provinces);
	
	
	/**
	 * 修改
	 */
	public void update(Provinces provinces);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Provinces findOne(Long id);
	
	
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
//	public PageInfo<Provinces> findPage(Provinces provinces, int pageNum, int pageSize);
	
}
