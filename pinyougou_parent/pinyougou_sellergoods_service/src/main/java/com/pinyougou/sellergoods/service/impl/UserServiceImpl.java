package com.pinyougou.sellergoods.service.impl;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.UserMapper;
import com.pinyougou.pojo.User;

import tk.mybatis.mapper.entity.Example;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<User> findAll() {
		return userMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<User> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.selectAll();
		PageInfo<User> userPageInfo = new PageInfo<User>(users);
		return userPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(User user) {
		userMapper.insert(user);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(User user){
		userMapper.updateByPrimaryKey(user);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public User findOne(Long id){
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			userMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageInfo<User> findPage(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

			Example example = new Example(User.class);
			Example.Criteria criteria = example.createCriteria();

			if (user != null) {
				if (StringUtils.isNotBlank(user.getEmail())) {
					criteria.andLike("email", "%" + user.getEmail() + "%");
				}
				if (StringUtils.isNotBlank(user.getHeadPic())) {
					criteria.andLike("headPic", "%" + user.getHeadPic() + "%");
				}
//				if (StringUtils.isNotBlank(user.getIsEmailCheck())) {
//					criteria.andLike("isEmailCheck", "%" + user.isEmailCheck() + "%");
//				}
				if (StringUtils.isNotBlank(user.getIsMobileCheck())) {
					criteria.andLike("isMobileCheck", "%" + user.getIsMobileCheck() + "%");
				}
				if (StringUtils.isNotBlank(user.getName())) {
					criteria.andLike("name", "%" + user.getName() + "%");
				}
				if (StringUtils.isNotBlank(user.getNickName())) {
					criteria.andLike("nickName", "%" + user.getNickName() + "%");
				}
				if (StringUtils.isNotBlank(user.getPhone())) {
					criteria.andLike("phone", "%" + user.getPhone() + "%");
				}
				if (StringUtils.isNotBlank(user.getQq())) {
					criteria.andLike("qq", "%" + user.getQq() + "%");
				}
				if (StringUtils.isNotBlank(user.getSex())) {
					criteria.andLike("sex", "%" + user.getSex() + "%");
				}
				if (StringUtils.isNotBlank(user.getSourceType())) {
					criteria.andLike("sourceType", "%" + user.getSourceType() + "%");
				}
				if (StringUtils.isNotBlank(user.getStatus())) {
					criteria.andLike("status", "%" + user.getStatus() + "%");
				}
				if (StringUtils.isNotBlank(user.getUsername())) {
					criteria.andLike("username", "%" + user.getUsername() + "%");
				}
//				if (StringUtils.isNotBlank(user.getAccountBalance())) {
//					criteria.andLike("accountBalance", "%" + user.getAccountBalance() + "%");
//				}
//				if (StringUtils.isNotBlank(user.getCreated())) {
//					criteria.andLike("created", "%" + user.getCreated() + "%");
//				}
//				if (StringUtils.isNotBlank(user.getExperienceValue())) {
//					criteria.andLike("experienceValue", "%" + user.getExperienceValue() + "%");
//				}
//				if (StringUtils.isNotBlank(user.getLastLoginTime())) {
//					criteria.andLike("lastLoginTime", "%" + user.getLastLoginTime() + "%");
//				}
			}

			List<User> users = userMapper.selectByExample(example);
			PageInfo<User> userPageInfo = new PageInfo<User>();
			return userPageInfo;
		}
	
}
