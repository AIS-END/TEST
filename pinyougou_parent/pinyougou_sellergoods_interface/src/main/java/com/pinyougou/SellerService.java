package com.pinyougou;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Seller;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SellerService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Seller> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageInfo<Seller> findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public int add(Seller seller);
	
	
	/**
	 * 修改
	 */
	public void update(Seller seller);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Seller findOne(String id);
	
	
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
	public PageInfo<Seller> findPage(Seller seller, int pageNum, int pageSize);

	/**
	 * 商家入驻审核，改变id商家的状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int updataStatus(String id, String status);
}
