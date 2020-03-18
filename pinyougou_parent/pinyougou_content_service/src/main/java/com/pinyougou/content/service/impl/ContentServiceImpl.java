package com.pinyougou.content.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.ContentMapper;
import com.pinyougou.pojo.Content;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

	//redis缓存
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Content> findAll() {
		return contentMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Content> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Content> contents = contentMapper.selectAll();
		PageInfo<Content> contentPageInfo = new PageInfo<Content>(contents);
		return contentPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Content content) {
		contentMapper.insert(content);
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Content content){
		//查询修改前的分类id(用主键从没修改的数据库查找原分类id)
		Long categoryId = contentMapper.selectByPrimaryKey(content).getCategoryId();
		redisTemplate.boundHashOps("content").delete(categoryId);
		contentMapper.updateByPrimaryKey(content);

		//如果修改造成分类id变化,清除修改后的分类id的缓存(传参content的分类id是新的分类id)
		if (categoryId.longValue() != content.getCategoryId().longValue()) {
			redisTemplate.boundHashOps("content").delete(content.getCategoryId());
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Content findOne(Long id){
		return contentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//通过主键查分类id
			Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();
			redisTemplate.boundHashOps("content").delete(categoryId);
			contentMapper.deleteByPrimaryKey(id);
		}		
	}

	/**
	 * 根据广告类型id查询列表
	 * @param categoryId
	 * @return
	 */
	@Override
	public List<Content> findByCategoryId(Long categoryId) {
		List<Content> contentList = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);

		if (contentList == null) {
			System.out.println("从数据库中读取数据放如缓存111111111111111      mysql");

			Example example = new Example(Content.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("categoryId", categoryId);//类型id
			criteria.andEqualTo("status", "1");//开启状态
			example.orderBy("id").asc();
			contentList = contentMapper.selectByExample(example);

			//存入缓存
			redisTemplate.boundHashOps("content").put(categoryId, contentList);
		} else {
			System.out.println("从缓存中读取22222222222222          redis");
		}

		return contentList;
	}


		@Override
	public PageInfo<Content> findPage(Content content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

//			Example example = new Example(Content.class);
//			Example.Criteria criteria = example.createCriteria();
			List<Content> all = findAll();
			PageInfo<Content> contentPageInfo = new PageInfo<Content>(all);
			return contentPageInfo;

	}
	
}
