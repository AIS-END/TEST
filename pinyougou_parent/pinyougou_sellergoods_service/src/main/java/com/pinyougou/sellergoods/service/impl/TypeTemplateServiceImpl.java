package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.pinyougou.TypeTemplateService;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.SpecificationOption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.TypeTemplate;
import tk.mybatis.mapper.entity.Example;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TypeTemplateMapper typeTemplateMapper;

	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TypeTemplate> findAll() {
		return typeTemplateMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<TypeTemplate> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TypeTemplate> typeTemplates = typeTemplateMapper.selectAll();
		PageInfo<TypeTemplate> typeTemplatePageInfo = new PageInfo<TypeTemplate>(typeTemplates);
		return typeTemplatePageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TypeTemplate typeTemplate) {
		typeTemplateMapper.insert(typeTemplate);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TypeTemplate typeTemplate){
		typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TypeTemplate findOne(Long id){
		return typeTemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			typeTemplateMapper.deleteByPrimaryKey(id);
		}		
	}

	@Override
	public PageInfo<TypeTemplate> findPage(TypeTemplate typeTemplate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		Example example = new Example(TypeTemplate.class);
		Example.Criteria criteria = example.createCriteria();

		if (typeTemplate != null) {
			if (StringUtils.isNotBlank(typeTemplate.getName())) {
				criteria.andLike("name", "%"+typeTemplate.getName()+"%");
			}
		}

		List<TypeTemplate> typeTemplates = typeTemplateMapper.selectByExample(example);
		PageInfo<TypeTemplate> typeTemplatePageInfo = new PageInfo<TypeTemplate>(typeTemplates);
		return typeTemplatePageInfo;
	}

	/**
	 * 根据规格名id查规格值
	 * @param id
	 * @return
	 */
	/**************
	 *    构建数据格式
	 *   [
	 *       {"id":1,"text":"内存大小","options":[{"id":123,"optionName":"4G"}]},
	 *       {"id":2,"text":"网络制式","options":[{"id":123,"optionName":"移动4G"}]},
	 *       {"id":3,"text":"屏幕尺寸","options":[{"id":123,"optionName":"16寸"},{"id":124,"optionName":"60寸"}]}
	 *   ];
	 ********/
	@Override
	public List<Map> findSpecList(Long id) {
		//先根据模板ID查询出规格列表，规格列表是一个集合[字符串]
		TypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);

		//通过FastJSON把字符串集合转换成集合数据
		List<Map> listMap = JSON.parseArray(typeTemplate.getSpecIds(),Map.class);

		for (Map map : listMap) {
			//循环集合，根据规格的ID查询出规格选项
			long specId = Long.parseLong(map.get("id").toString()); //规格ID
			SpecificationOption specificationOption = new SpecificationOption();
			specificationOption.setSpecId(specId);
			List<SpecificationOption> options = specificationOptionMapper.select(specificationOption);
			//构建JSON数据options结构
			map.put("options",options);
		}
		return listMap;
	}



//		@Override
//	public PageResult findPage(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbTypeTemplateExample example=new TbTypeTemplateExample();
//		Criteria criteria = example.createCriteria();
//
//		if(typeTemplate!=null){
//						if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
//				criteria.andNameLike("%"+typeTemplate.getName()+"%");
//			}
//			if(typeTemplate.getSpecIds()!=null && typeTemplate.getSpecIds().length()>0){
//				criteria.andSpecIdsLike("%"+typeTemplate.getSpecIds()+"%");
//			}
//			if(typeTemplate.getBrandIds()!=null && typeTemplate.getBrandIds().length()>0){
//				criteria.andBrandIdsLike("%"+typeTemplate.getBrandIds()+"%");
//			}
//			if(typeTemplate.getCustomAttributeItems()!=null && typeTemplate.getCustomAttributeItems().length()>0){
//				criteria.andCustomAttributeItemsLike("%"+typeTemplate.getCustomAttributeItems()+"%");
//			}
//
//		}
//
//		Page<TbTypeTemplate> page= (Page<TbTypeTemplate>)typeTemplateMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
