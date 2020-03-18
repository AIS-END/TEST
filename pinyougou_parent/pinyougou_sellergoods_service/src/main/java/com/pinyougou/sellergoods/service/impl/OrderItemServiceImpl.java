package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.OrderItemMapper;
import com.pinyougou.pojo.OrderItem;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<OrderItem> findAll() {
		return orderItemMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<OrderItem> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<OrderItem> orderItems = orderItemMapper.selectAll();
		PageInfo<OrderItem> orderItemPageInfo = new PageInfo<OrderItem>(orderItems);
		return orderItemPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(OrderItem orderItem) {
		orderItemMapper.insert(orderItem);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(OrderItem orderItem){
		orderItemMapper.updateByPrimaryKey(orderItem);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public OrderItem findOne(Long id){
		return orderItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			orderItemMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(OrderItem orderItem, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		OrderItemExample example=new OrderItemExample();
//		Criteria criteria = example.createCriteria();
//
//		if(orderItem!=null){
//						if(orderItem.getTitle()!=null && orderItem.getTitle().length()>0){
//				criteria.andTitleLike("%"+orderItem.getTitle()+"%");
//			}
//			if(orderItem.getPicPath()!=null && orderItem.getPicPath().length()>0){
//				criteria.andPicPathLike("%"+orderItem.getPicPath()+"%");
//			}
//			if(orderItem.getSellerId()!=null && orderItem.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+orderItem.getSellerId()+"%");
//			}
//
//		}
//
//		Page<OrderItem> page= (Page<OrderItem>)orderItemMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
