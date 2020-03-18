package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.AreasMapper;
import com.pinyougou.pojo.Areas;
import com.pinyougou.pojo.AreasExample;
import com.pinyougou.pojo.AreasExample.Criteria;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AreasServiceImpl implements AreasService {

	@Autowired
	private AreasMapper areasMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Areas> findAll() {
		return areasMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Areas> areas = areasMapper.selectAll();
		PageInfo<Areas> areasPageInfo = new PageInfo<Areas>(areas);
		return areasPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Areas areas) {
		areasMapper.insert(areas);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Areas areas){
		areasMapper.updateByPrimaryKey(areas);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Areas findOne(Long id){
		return areasMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			areasMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(Areas areas, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		AreasExample example=new AreasExample();
//		Criteria criteria = example.createCriteria();
//
//		if(areas!=null){
//						if(areas.getAreaid()!=null && areas.getAreaid().length()>0){
//				criteria.andAreaidLike("%"+areas.getAreaid()+"%");
//			}
//			if(areas.getArea()!=null && areas.getArea().length()>0){
//				criteria.andAreaLike("%"+areas.getArea()+"%");
//			}
//			if(areas.getCityid()!=null && areas.getCityid().length()>0){
//				criteria.andCityidLike("%"+areas.getCityid()+"%");
//			}
//
//		}
//
//		Page<Areas> page= (Page<Areas>)areasMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
