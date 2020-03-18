package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.ProvincesMapper;
import com.pinyougou.pojo.Provinces;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ProvincesServiceImpl implements ProvincesService {

	@Autowired
	private ProvincesMapper provincesMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Provinces> findAll() {
		return provincesMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Provinces> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Provinces> provinces = provincesMapper.selectAll();
		PageInfo<Provinces> provincesPageInfo = new PageInfo<Provinces>(provinces);
		return provincesPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Provinces provinces) {
		provincesMapper.insert(provinces);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Provinces provinces){
		provincesMapper.updateByPrimaryKey(provinces);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Provinces findOne(Long id){
		return provincesMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			provincesMapper.deleteByPrimaryKey(id);
		}		
	}
	
//
//		@Override
//	public PageResult findPage(Provinces provinces, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbProvincesExample example=new TbProvincesExample();
//		Criteria criteria = example.createCriteria();
//
//		if(provinces!=null){
//						if(provinces.getProvinceid()!=null && provinces.getProvinceid().length()>0){
//				criteria.andProvinceidLike("%"+provinces.getProvinceid()+"%");
//			}
//			if(provinces.getProvince()!=null && provinces.getProvince().length()>0){
//				criteria.andProvinceLike("%"+provinces.getProvince()+"%");
//			}
//
//		}
//
//		Page<TbProvinces> page= (Page<TbProvinces>)provincesMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
