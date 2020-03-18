package com.pinyougou.sellergoods.service.impl;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pinyougou.SpecificationService;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.SpecificationOption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.pojo.Specification;
import tk.mybatis.mapper.entity.Example;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private SpecificationMapper specificationMapper;

	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Specification> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Specification> specifications = specificationMapper.selectAll();
		PageInfo<Specification> specificationPageInfo = new PageInfo<Specification>(specifications);
		return specificationPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public int add(Specification specification) {
//		specificationMapper.insert(specification);

//		先增加规则信息
		int acount = specificationMapper.insertSelective(specification);

//		再增加规则选项
		if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size()>0) {
			for (SpecificationOption specificationOption : specification.getSpecificationOptionList()) {
				specificationOption.setSpecId(specification.getId());

//				增加
				specificationOptionMapper.insert(specificationOption);
			}
		}
		return acount;
	}

	
	/**
	 * 修改
	 */
	@Override
	public int update(Specification specification){
//		specificationMapper.updateByPrimaryKey(specification);

		//先修改规格信息
		int i = specificationMapper.updateByPrimaryKey(specification);

		//删除关联的全部规则信息
		SpecificationOption specificationOption = new SpecificationOption();
		specificationOption.setSpecId(specification.getId());
		specificationOptionMapper.delete(specificationOption);

		//插入新的规格选项
		if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() > 0) {
			for (SpecificationOption specificationOption1 : specification.getSpecificationOptionList()) {
				specificationOption1.setSpecId(specification.getId());
				//增加
				specificationOptionMapper.insert(specificationOption1);
			}
		}
		return i;
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
//		return specificationMapper.selectByPrimaryKey(id);
		//根据id查规则信息
		Specification specification = specificationMapper.selectByPrimaryKey(id);
		//根据规格id查规格选项信息
		SpecificationOption specificationOption = new SpecificationOption();
		specificationOption.setSpecId(id);
		List<SpecificationOption> select = specificationOptionMapper.select(specificationOption);
		specification.setSpecificationOptionList(select);
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public int delete(Long [] ids) {
//		for(Long id:ids){
////			specificationMapper.deleteByPrimaryKey(id);
//
//			//删除规格信息
//			int i = specificationMapper.deleteByPrimaryKey(id);
//			//删除关联的规格选项
//			SpecificationOption specificationOption = new SpecificationOption();
//			specificationOption.setSpecId(id);
//			int delete = specificationOptionMapper.delete(specificationOption);
//		}
//		return count;

		//删除规格选项
		List<Long> longs = Arrays.asList(ids);
		Example optionsExample = new Example(SpecificationOption.class);
		Example.Criteria optionCriteria = optionsExample.createCriteria();
		optionCriteria.andIn("specId",longs);
		specificationOptionMapper.deleteByExample(optionsExample);


		//删除规格信息
		//创建Example，来构建根据ID删除数据
		Example example = new Example(Specification.class);
		Example.Criteria criteria = example.createCriteria();

		//所需的SQL语句类似 delete from tb_specification where id in(1,2,5,6)
		criteria.andIn("id",longs);
		int dcount = specificationMapper.deleteByExample(example);
		return dcount;
	}


		@Override
	public PageInfo<Specification> findPage(Specification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

			Example example = new Example(Specification.class);
			Example.Criteria criteria = example.createCriteria();

			if (specification != null) {
				if (StringUtils.isNotBlank(specification.getSpecName())) {
					criteria.andLike("specName", specification.getSpecName());
				}
			}

			List<Specification> specifications = specificationMapper.selectByExample(example);
			PageInfo<Specification> specificationPageInfo = new PageInfo<Specification>(specifications);
			return specificationPageInfo;
		}

	@Override
	public List<Map<String, Object>> selectOptionList() {
		return specificationMapper.selectOptionList();
	}

}
