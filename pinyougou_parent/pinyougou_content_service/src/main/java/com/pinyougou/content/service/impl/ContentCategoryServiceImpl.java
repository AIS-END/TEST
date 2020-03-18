package com.pinyougou.content.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.content.service.ContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.ContentCategoryMapper;
import com.pinyougou.pojo.ContentCategory;
import tk.mybatis.mapper.entity.Example;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<ContentCategory> findAll() {
		return contentCategoryMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<ContentCategory> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContentCategory> contentCategories = contentCategoryMapper.selectAll();
		PageInfo<ContentCategory> contentCategoryPageInfo = new PageInfo<ContentCategory>(contentCategories);
		return contentCategoryPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(ContentCategory contentCategory) {
		contentCategoryMapper.insert(contentCategory);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(ContentCategory contentCategory){
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ContentCategory findOne(Long id){
		return contentCategoryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			contentCategoryMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int pageNum, int pageSize) {
			PageHelper.startPage(pageNum, pageSize);

			Example example = new Example(ContentCategory.class);
			Example.Criteria criteria = example.createCriteria();
			if (contentCategory != null) {
				if (StringUtils.isNotBlank(contentCategory.getName())) {
					criteria.andLike("name", "%" + contentCategory.getName() + "%");
				}
			}
			List<ContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
			PageInfo<ContentCategory> contentCategoryPageInfo = new PageInfo<ContentCategory>(contentCategories);
			return contentCategoryPageInfo;

		}
}
