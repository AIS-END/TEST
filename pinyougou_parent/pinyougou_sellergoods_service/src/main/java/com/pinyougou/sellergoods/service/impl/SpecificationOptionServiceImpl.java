package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.SpecificationOption;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<SpecificationOption> findAll() {
		return specificationOptionMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<SpecificationOption> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SpecificationOption> specificationOptions = specificationOptionMapper.selectAll();
		PageInfo<SpecificationOption> specificationOptionPageInfo = new PageInfo<SpecificationOption>(specificationOptions);
		return specificationOptionPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SpecificationOption specificationOption) {
		specificationOptionMapper.insert(specificationOption);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SpecificationOption specificationOption){
		specificationOptionMapper.updateByPrimaryKey(specificationOption);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SpecificationOption findOne(Long id){
		return specificationOptionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationOptionMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(TbSpecificationOption specificationOption, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
//		Criteria criteria = example.createCriteria();
//
//		if(specificationOption!=null){
//						if(specificationOption.getOptionName()!=null && specificationOption.getOptionName().length()>0){
//				criteria.andOptionNameLike("%"+specificationOption.getOptionName()+"%");
//			}
//
//		}
//
//		Page<TbSpecificationOption> page= (Page<TbSpecificationOption>)specificationOptionMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
