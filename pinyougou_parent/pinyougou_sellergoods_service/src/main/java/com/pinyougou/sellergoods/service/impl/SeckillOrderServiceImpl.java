package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SeckillOrderMapper;
import com.pinyougou.pojo.SeckillOrder;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

	@Autowired
	private SeckillOrderMapper seckillOrderMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<SeckillOrder> findAll() {
		return seckillOrderMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<SeckillOrder> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SeckillOrder> seckillOrders = seckillOrderMapper.selectAll();
		PageInfo<SeckillOrder> seckillOrderPageInfo = new PageInfo<SeckillOrder>(seckillOrders);
		return seckillOrderPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SeckillOrder seckillOrder) {
		seckillOrderMapper.insert(seckillOrder);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SeckillOrder seckillOrder){
		seckillOrderMapper.updateByPrimaryKey(seckillOrder);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SeckillOrder findOne(Long id){
		return seckillOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			seckillOrderMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(TbSeckillOrder seckillOrder, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbSeckillOrderExample example=new TbSeckillOrderExample();
//		Criteria criteria = example.createCriteria();
//
//		if(seckillOrder!=null){
//						if(seckillOrder.getUserId()!=null && seckillOrder.getUserId().length()>0){
//				criteria.andUserIdLike("%"+seckillOrder.getUserId()+"%");
//			}
//			if(seckillOrder.getSellerId()!=null && seckillOrder.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+seckillOrder.getSellerId()+"%");
//			}
//			if(seckillOrder.getStatus()!=null && seckillOrder.getStatus().length()>0){
//				criteria.andStatusLike("%"+seckillOrder.getStatus()+"%");
//			}
//			if(seckillOrder.getReceiverAddress()!=null && seckillOrder.getReceiverAddress().length()>0){
//				criteria.andReceiverAddressLike("%"+seckillOrder.getReceiverAddress()+"%");
//			}
//			if(seckillOrder.getReceiverMobile()!=null && seckillOrder.getReceiverMobile().length()>0){
//				criteria.andReceiverMobileLike("%"+seckillOrder.getReceiverMobile()+"%");
//			}
//			if(seckillOrder.getReceiver()!=null && seckillOrder.getReceiver().length()>0){
//				criteria.andReceiverLike("%"+seckillOrder.getReceiver()+"%");
//			}
//			if(seckillOrder.getTransactionId()!=null && seckillOrder.getTransactionId().length()>0){
//				criteria.andTransactionIdLike("%"+seckillOrder.getTransactionId()+"%");
//			}
//
//		}
//
//		Page<TbSeckillOrder> page= (Page<TbSeckillOrder>)seckillOrderMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
