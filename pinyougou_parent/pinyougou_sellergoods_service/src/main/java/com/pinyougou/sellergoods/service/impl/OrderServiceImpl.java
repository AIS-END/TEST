package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.OrderMapper;
import com.pinyougou.pojo.Order;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Order> findAll() {
		return orderMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Order> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Order> orders = orderMapper.selectAll();
		PageInfo<Order> orderPageInfo = new PageInfo<Order>(orders);
		return orderPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Order order) {
		orderMapper.insert(order);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Order order){
		orderMapper.updateByPrimaryKey(order);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Order findOne(Long id){
		return orderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			orderMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(Order order, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		OrderExample example=new OrderExample();
//		Criteria criteria = example.createCriteria();
//
//		if(order!=null){
//						if(order.getPaymentType()!=null && order.getPaymentType().length()>0){
//				criteria.andPaymentTypeLike("%"+order.getPaymentType()+"%");
//			}
//			if(order.getPostFee()!=null && order.getPostFee().length()>0){
//				criteria.andPostFeeLike("%"+order.getPostFee()+"%");
//			}
//			if(order.getStatus()!=null && order.getStatus().length()>0){
//				criteria.andStatusLike("%"+order.getStatus()+"%");
//			}
//			if(order.getShippingName()!=null && order.getShippingName().length()>0){
//				criteria.andShippingNameLike("%"+order.getShippingName()+"%");
//			}
//			if(order.getShippingCode()!=null && order.getShippingCode().length()>0){
//				criteria.andShippingCodeLike("%"+order.getShippingCode()+"%");
//			}
//			if(order.getUserId()!=null && order.getUserId().length()>0){
//				criteria.andUserIdLike("%"+order.getUserId()+"%");
//			}
//			if(order.geuyerMessage()!=null && order.getBuyerMessage().length()>0){
//				criteria.andBuyerMessageLike("%"+order.getBuyerMessage()+"%");
//			}
//			if(order.getBuyerNick()!=null && order.getBuyerNick().length()>0){
//				criteria.andBuyerNickLike("%"+order.getBuyerNick()+"%");
//			}
//			if(order.getBuyerRate()!=null && order.getBuyerRate().length()>0){
//				criteria.andBuyerRateLike("%"+order.getBuyerRate()+"%");
//			}
//			if(order.getReceiverAreaName()!=null && order.getReceiverAreaName().length()>0){
//				criteria.andReceiverAreaNameLike("%"+order.getReceiverAreaName()+"%");
//			}
//			if(order.getReceiverMobile()!=null && order.getReceiverMobile().length()>0){
//				criteria.andReceiverMobileLike("%"+order.getReceiverMobile()+"%");
//			}
//			if(order.getReceiverZipCode()!=null && order.getReceiverZipCode().length()>0){
//				criteria.andReceiverZipCodeLike("%"+order.getReceiverZipCode()+"%");
//			}
//			if(order.getReceiver()!=null && order.getReceiver().length()>0){
//				criteria.andReceiverLike("%"+order.getReceiver()+"%");
//			}
//			if(order.getInvoiceType()!=null && order.getInvoiceType().length()>0){
//				criteria.andInvoiceTypeLike("%"+order.getInvoiceType()+"%");
//			}
//			if(order.getSourceType()!=null && order.getSourceType().length()>0){
//				criteria.andSourceTypeLike("%"+order.getSourceType()+"%");
//			}
//			if(order.getSellerId()!=null && order.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+order.getSellerId()+"%");
//			}
//
//		}
//
//		Page<TbOrder> page= (Page<TbOrder>)orderMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
