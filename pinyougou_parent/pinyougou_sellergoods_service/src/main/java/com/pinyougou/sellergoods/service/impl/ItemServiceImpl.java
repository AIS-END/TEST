package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.pojo.Item;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Item> findAll() {
		return itemMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Item> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Item> items = itemMapper.selectAll();
		PageInfo<Item> itemPageInfo = new PageInfo<Item>(items);
		return itemPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Item item) {
		itemMapper.insert(item);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Item item){
		itemMapper.updateByPrimaryKey(item);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Item findOne(Long id){
		return itemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			itemMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(Item item, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		ItemExample example=new ItemExample();
//		Criteria criteria = example.createCriteria();
//
//		if(item!=null){
//						if(item.getTitle()!=null && item.getTitle().length()>0){
//				criteria.andTitleLike("%"+item.getTitle()+"%");
//			}
//			if(item.getSellPoint()!=null && item.getSellPoint().length()>0){
//				criteria.andSellPointLike("%"+item.getSellPoint()+"%");
//			}
//			if(item.gearcode()!=null && item.gearcode().length()>0){
//				criteria.andBarcodeLike("%"+item.gearcode()+"%");
//			}
//			if(item.getImage()!=null && item.getImage().length()>0){
//				criteria.andImageLike("%"+item.getImage()+"%");
//			}
//			if(item.getStatus()!=null && item.getStatus().length()>0){
//				criteria.andStatusLike("%"+item.getStatus()+"%");
//			}
//			if(item.getItemSn()!=null && item.getItemSn().length()>0){
//				criteria.andItemSnLike("%"+item.getItemSn()+"%");
//			}
//			if(item.getIsDefault()!=null && item.getIsDefault().length()>0){
//				criteria.andIsDefaultLike("%"+item.getIsDefault()+"%");
//			}
//			if(item.getSellerId()!=null && item.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+item.getSellerId()+"%");
//			}
//			if(item.getCartThumbnail()!=null && item.getCartThumbnail().length()>0){
//				criteria.andCartThumbnailLike("%"+item.getCartThumbnail()+"%");
//			}
//			if(item.getCategory()!=null && item.getCategory().length()>0){
//				criteria.andCategoryLike("%"+item.getCategory()+"%");
//			}
//			if(item.gerand()!=null && item.gerand().length()>0){
//				criteria.andBrandLike("%"+item.gerand()+"%");
//			}
//			if(item.getSpec()!=null && item.getSpec().length()>0){
//				criteria.andSpecLike("%"+item.getSpec()+"%");
//			}
//			if(item.getSeller()!=null && item.getSeller().length()>0){
//				criteria.andSellerLike("%"+item.getSeller()+"%");
//			}
//
//		}
//
//		Page<Item> page= (Page<Item>)itemMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
