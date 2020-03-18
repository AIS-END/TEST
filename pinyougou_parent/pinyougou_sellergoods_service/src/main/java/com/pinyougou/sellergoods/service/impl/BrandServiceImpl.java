package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
//import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
//import com.pinyougou.pojo.BrandExample;
//import com.pinyougou.pojo.BrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;
import tk.mybatis.mapper.entity.Example;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Map<String,Object>> selectOptionList() {
        return  brandMapper.selectOptionList();
    }

    /**
     * 查询全部
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 按分页查询
     */
    @Override
    public PageInfo<Brand> findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Brand> brands = brandMapper.selectAll();
        PageInfo<Brand> brandPageInfo = new PageInfo<Brand>(brands);
        return brandPageInfo;
    }

    /**
     * 增加
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }


    /**
     * 修改
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Brand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

//	/**
//	 * 批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@Override
//	public int deleteByIds(List<Long> ids) {
//		Example example = new Example(Brand.class);
//		Example.Criteria criteria = example.createCriteria();
//		criteria.andIn("id", ids);
//		int i = brandMapper.deleteByExample(example);
//		return i;
//	}


    @Override
    public PageInfo<Brand> findPage(Brand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (brand != null) {
            //名字模糊搜索
            if (StringUtils.isNotBlank(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }

            //首字母搜索
            if (StringUtils.isNotBlank(brand.getFirstChar())) {
                criteria.andEqualTo("firstChar", brand.getFirstChar());
            }
        }
        //执行查询
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> brandPageInfo = new PageInfo<Brand>(brands);
        return brandPageInfo;

//        PageHelper.startPage(pageNum, pageSize);
//
//        BrandExample example = new BrandExample();
//        Criteria criteria = example.createCriteria();
//
//        if (brand != null) {
//            if (brand.getName() != null && brand.getName().length() > 0) {
//                criteria.andNameLike("%" + brand.getName() + "%");
//            }
//            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
//                criteria.andFirstCharLike("%" + brand.getFirstChar() + "%");
//            }
//
//        }
//
//        Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(example);
//        return new PageResult(page.getTotal(), page.getResult());
    }

}
