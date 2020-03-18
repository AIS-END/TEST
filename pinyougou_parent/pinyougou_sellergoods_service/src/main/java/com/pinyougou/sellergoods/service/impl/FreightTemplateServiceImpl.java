package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.FreightTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.FreightTemplateMapper;
import com.pinyougou.pojo.FreightTemplate;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

	@Autowired
	private FreightTemplateMapper freightTemplateMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<FreightTemplate> findAll() {
		return freightTemplateMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<FreightTemplate> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<FreightTemplate> freightTemplates = freightTemplateMapper.selectAll();
		PageInfo<FreightTemplate> freightTemplatePageInfo = new PageInfo<FreightTemplate>(freightTemplates);
		return freightTemplatePageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(FreightTemplate freightTemplate) {
		freightTemplateMapper.insert(freightTemplate);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(FreightTemplate freightTemplate){
		freightTemplateMapper.updateByPrimaryKey(freightTemplate);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public FreightTemplate findOne(Long id){
		return freightTemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			freightTemplateMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
//		@Override
//	public PageResult findPage(FreightTemplate freightTemplate, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		FreightTemplateExample example=new FreightTemplateExample();
//		Criteria criteria = example.createCriteria();
//
//		if(freightTemplate!=null){
//						if(freightTemplate.getSellerId()!=null && freightTemplate.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+freightTemplate.getSellerId()+"%");
//			}
//			if(freightTemplate.getIsDefault()!=null && freightTemplate.getIsDefault().length()>0){
//				criteria.andIsDefaultLike("%"+freightTemplate.getIsDefault()+"%");
//			}
//			if(freightTemplate.getName()!=null && freightTemplate.getName().length()>0){
//				criteria.andNameLike("%"+freightTemplate.getName()+"%");
//			}
//			if(freightTemplate.getSendTimeType()!=null && freightTemplate.getSendTimeType().length()>0){
//				criteria.andSendTimeTypeLike("%"+freightTemplate.getSendTimeType()+"%");
//			}
//
//		}
//
//		Page<FreightTemplate> page= (Page<FreightTemplate>)freightTemplateMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
