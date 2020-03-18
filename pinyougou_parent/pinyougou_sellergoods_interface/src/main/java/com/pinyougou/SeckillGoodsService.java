package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.SeckillGoods;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SeckillGoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<SeckillGoods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<SeckillGoods> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(SeckillGoods seckillGoods);
	
	
	/**
	 * 修改
	 */
	public void update(SeckillGoods seckillGoods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public SeckillGoods findOne(Long id);
	
	
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
//	public PageInfo<SeckillGoods> findPage(SeckillGoods seckillGoods, int pageNum, int pageSize);
	
}
