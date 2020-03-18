package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.CitiesMapper;
import com.pinyougou.pojo.Cities;
import com.pinyougou.pojo.CitiesExample;
import com.pinyougou.pojo.CitiesExample.Criteria;
import com.pinyougou.sellergoods.service.CitiesService;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	private CitiesMapper citiesMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Cities> findAll() {
		return citiesMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Cities> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Cities> cities = citiesMapper.selectAll();
		PageInfo<Cities> citiesPageInfo = new PageInfo<Cities>(cities);
		return citiesPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Cities cities) {
		citiesMapper.insert(cities);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Cities cities){
		citiesMapper.updateByPrimaryKey(cities);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Cities findOne(Long id){
		return citiesMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			citiesMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(Cities cities, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		CitiesExample example=new CitiesExample();
//		Criteria criteria = example.createCriteria();
//
//		if(cities!=null){
//						if(cities.getCityid()!=null && cities.getCityid().length()>0){
//				criteria.andCityidLike("%"+cities.getCityid()+"%");
//			}
//			if(cities.getCity()!=null && cities.getCity().length()>0){
//				criteria.andCityLike("%"+cities.getCity()+"%");
//			}
//			if(cities.getProvinceid()!=null && cities.getProvinceid().length()>0){
//				criteria.andProvinceidLike("%"+cities.getProvinceid()+"%");
//			}
//
//		}
//
//		Page<Cities> page= (Page<Cities>)citiesMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
