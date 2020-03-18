package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.GoodsDesc;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsDescService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<GoodsDesc> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<GoodsDesc> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(GoodsDesc goodsDesc);
	
	
	/**
	 * 修改
	 */
	public void update(GoodsDesc goodsDesc);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public GoodsDesc findOne(Long id);
	
	
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
//	public PageInfo<GoodsDesc> findPage(GoodsDesc goodsDesc, int pageNum, int pageSize);
	
}
