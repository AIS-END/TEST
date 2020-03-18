package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.AddressMapper;
import com.pinyougou.pojo.Address;
import com.pinyougou.pojo.AddressExample;
import com.pinyougou.pojo.AddressExample.Criteria;
import tk.mybatis.mapper.entity.Example;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectAll();
    }

    /**
     * 按分页查询
     */
    @Override
    public PageInfo<Address> findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Address> addresses = addressMapper.selectAll();
        PageInfo<Address> addressPageInfo = new PageInfo<Address>(addresses);
        return addressPageInfo;
    }

    /**
     * 增加
     */
    @Override
    public void add(Address address) {
        addressMapper.insert(address);
    }


    /**
     * 修改
     */
    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKey(address);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Address findOne(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            addressMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageInfo<Address> findPage(Address address, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();

        if (address != null) {
            //地址模糊搜索
            if (StringUtils.isNotBlank(address.getAddress())) {
                criteria.andLike("address", "%" + address.getAddress() + "%");
            }
            //联系人模糊搜索
            if (StringUtils.isNotBlank(address.getContact())) {
                criteria.andLike("contact", "%" + address.getContact() + "%");
            }
            //别名模糊搜索
            if (StringUtils.isNotBlank(address.getAlias())) {
                criteria.andLike("alias", "%" + address.getAlias() + "%");
            }
        }
        //执行查询
        List<Address> addresses = addressMapper.selectByExample(example);
        PageInfo<Address> addressPageInfo = new PageInfo<Address>(addresses);
        return addressPageInfo;
    }
}
