package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SeckillGoodsMapper;
import com.pinyougou.pojo.SeckillGoods;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

	@Autowired
	private SeckillGoodsMapper seckillGoodsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<SeckillGoods> findAll() {
		return seckillGoodsMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<SeckillGoods> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SeckillGoods> seckillGoods = seckillGoodsMapper.selectAll();
		PageInfo<SeckillGoods> seckillGoodsPageInfo = new PageInfo<SeckillGoods>(seckillGoods);
		return seckillGoodsPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SeckillGoods seckillGoods) {
		seckillGoodsMapper.insert(seckillGoods);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SeckillGoods seckillGoods){
		seckillGoodsMapper.updateByPrimaryKey(seckillGoods);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SeckillGoods findOne(Long id){
		return seckillGoodsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			seckillGoodsMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(SeckillGoods seckillGoods, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		SeckillGoodsExample example=new TbSeckillGoodsExample();
//		Criteria criteria = example.createCriteria();
//
//		if(seckillGoods!=null){
//						if(seckillGoods.getTitle()!=null && seckillGoods.getTitle().length()>0){
//				criteria.andTitleLike("%"+seckillGoods.getTitle()+"%");
//			}
//			if(seckillGoods.getSmallPic()!=null && seckillGoods.getSmallPic().length()>0){
//				criteria.andSmallPicLike("%"+seckillGoods.getSmallPic()+"%");
//			}
//			if(seckillGoods.getSellerId()!=null && seckillGoods.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+seckillGoods.getSellerId()+"%");
//			}
//			if(seckillGoods.getStatus()!=null && seckillGoods.getStatus().length()>0){
//				criteria.andStatusLike("%"+seckillGoods.getStatus()+"%");
//			}
//			if(seckillGoods.getIntroduction()!=null && seckillGoods.getIntroduction().length()>0){
//				criteria.andIntroductionLike("%"+seckillGoods.getIntroduction()+"%");
//			}
//
//		}
//
//		Page<TbSeckillGoods> page= (Page<TbSeckillGoods>)seckillGoodsMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
