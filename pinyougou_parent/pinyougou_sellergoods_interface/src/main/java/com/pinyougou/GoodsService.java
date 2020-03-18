package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Goods;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Goods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<Goods> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	 * @return
	 */
	public int add(Goods goods);
	
	
	/**
	 * 修改
	 */
	public void update(Goods goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Goods findOne(Long id);
	
	
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
	public PageInfo<Goods> findPage(Goods goods, int pageNum, int pageSize);

	/**
	 * 批量修改状态
	 * @param ids
	 * @param status
	 */
	public void updateStatus(Long[] ids, String status);
	
}
