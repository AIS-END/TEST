package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.ItemCatMapper;
import com.pinyougou.pojo.ItemCat;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<ItemCat> findAll() {
		return itemCatMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<ItemCat> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ItemCat> itemCats = itemCatMapper.selectAll();
		PageInfo<ItemCat> itemCatPageInfo = new PageInfo<ItemCat>(itemCats);
		return itemCatPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(ItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(ItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			itemCatMapper.deleteByPrimaryKey(id);
		}		
	}

	/**
	 * 根据上级ID查询列表
	 */
	@Override
	public List<ItemCat> findByParentId(Long parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);

		List<ItemCat> itemCats = itemCatMapper.select(itemCat);
		return  itemCats;
	}


//		@Override
//	public PageResult findPage(ItemCat itemCat, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		ItemCatExample example=new ItemCatExample();
//		Criteria criteria = example.createCriteria();
//
//		if(itemCat!=null){
//						if(itemCat.getName()!=null && itemCat.getName().length()>0){
//				criteria.andNameLike("%"+itemCat.getName()+"%");
//			}
//
//		}
//
//		Page<ItemCat> page= (Page<ItemCat>)itemCatMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
