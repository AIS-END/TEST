package com.pinyougou.sellergoods.service.impl;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pinyougou.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SellerMapper;
import com.pinyougou.pojo.Seller;
import tk.mybatis.mapper.entity.Example;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerMapper sellerMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Seller> findAll() {
		return sellerMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Seller> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Seller> sellers = sellerMapper.selectAll();
		PageInfo<Seller> sellerPageInfo = new PageInfo<Seller>(sellers);
		return sellerPageInfo;
	}

	/**
	 * 增加
	 */
	@Override
	public int add(Seller seller) {
		seller.setStatus("0");
		seller.setCreateTime(new Date());
		return sellerMapper.insert(seller);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Seller seller){
		sellerMapper.updateByPrimaryKey(seller);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Seller findOne(String id){
		return sellerMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			sellerMapper.deleteByPrimaryKey(id);
		}		
	}

	@Override
	public PageInfo<Seller> findPage(Seller seller, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		Example example = new Example(Seller.class);
		Example.Criteria criteria = example.createCriteria();

		if (seller != null) {
			if (StringUtils.isNotBlank(seller.getName())) {
				criteria.andLike("name", "%" + seller.getName() + "%");
			}
			if (StringUtils.isNotBlank(seller.getSellerId())) {
				criteria.andLike("sellerId", "%" + seller.getSellerId() + "%");
			}
			if (StringUtils.isNotBlank(seller.getNickName())) {
				criteria.andLike("nickName", "%" + seller.getNickName() + "%");
			}
			if (StringUtils.isNotBlank(seller.getEmail())) {
				criteria.andLike("email", "%" + seller.getEmail() + "%");
			}
			if (StringUtils.isNotBlank(seller.getMobile())) {
				criteria.andLike("mobile", "%" + seller.getMobile() + "%");
			}
			if (StringUtils.isNotBlank(seller.getTelephone())) {
				criteria.andLike("telephone", "%" + seller.getTelephone() + "%");
			}
			if (StringUtils.isNotBlank(seller.getStatus())) {
				criteria.andLike("status", "%" + seller.getStatus() + "%");
			}
			if (StringUtils.isNotBlank(seller.getAddressDetail())) {
				criteria.andLike("addressDetail", "%" + seller.getAddressDetail() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLinkmanName())) {
				criteria.andLike("linkmanName", "%" + seller.getLinkmanName() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLinkmanMobile())) {
				criteria.andLike("linkmanMobile", "%" + seller.getLinkmanMobile() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLinkmanQq())) {
				criteria.andLike("linkmanQq", "%" + seller.getLinkmanQq() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLinkmanEmail())) {
				criteria.andLike("linkmanEmail", "%" + seller.getLinkmanEmail() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLicenseNumber())) {
				criteria.andLike("licenseNumber", "%" + seller.getLicenseNumber() + "%");
			}
			if (StringUtils.isNotBlank(seller.getTaxNumber())) {
				criteria.andLike("taxNumber", "%" + seller.getTaxNumber() + "%");
			}
			if (StringUtils.isNotBlank(seller.getOrgNumber())) {
				criteria.andLike("orgNumber", "%" + seller.getOrgNumber() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLogoPic())) {
				criteria.andLike("logoPic", "%" + seller.getLogoPic() + "%");
			}
			if (StringUtils.isNotBlank(seller.getBrief())) {
				criteria.andLike("brief", "%" + seller.getBrief() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLegalPerson())) {
				criteria.andLike("legalPerson", "%" + seller.getLegalPerson() + "%");
			}
			if (StringUtils.isNotBlank(seller.getLegalPersonCardId())) {
				criteria.andLike("legalPersonCardId", "%" + seller.getLegalPersonCardId() + "%");
			}
			if (StringUtils.isNotBlank(seller.getBankUser())) {
				criteria.andLike("bankUser", "%" + seller.getBankUser() + "%");
			}
			if (StringUtils.isNotBlank(seller.getBankName())) {
				criteria.andLike("bankName", "%" + seller.getBankName() + "%");
			}
		}

		List<Seller> sellers = sellerMapper.selectByExample(example);
		PageInfo<Seller> sellerPageInfo = new PageInfo<Seller>(sellers);
		return sellerPageInfo;
	}

	@Override
	public int updataStatus(String id, String status) {
		Seller seller = sellerMapper.selectByPrimaryKey(id);
		seller.setStatus(status);
		int i = sellerMapper.updateByPrimaryKey(seller);
		return i;
	}

//
//		@Override
//	public PageResult findPage(TbSeller seller, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbSellerExample example=new TbSellerExample();
//		Criteria criteria = example.createCriteria();
//
//		if(seller!=null){
//						if(seller.getSellerId()!=null && seller.getSellerId().length()>0){
//				criteria.andSellerIdLike("%"+seller.getSellerId()+"%");
//			}
//			if(seller.getName()!=null && seller.getName().length()>0){
//				criteria.andNameLike("%"+seller.getName()+"%");
//			}
//			if(seller.getNickName()!=null && seller.getNickName().length()>0){
//				criteria.andNickNameLike("%"+seller.getNickName()+"%");
//			}
//			if(seller.getPassword()!=null && seller.getPassword().length()>0){
//				criteria.andPasswordLike("%"+seller.getPassword()+"%");
//			}
//			if(seller.getEmail()!=null && seller.getEmail().length()>0){
//				criteria.andEmailLike("%"+seller.getEmail()+"%");
//			}
//			if(seller.getMobile()!=null && seller.getMobile().length()>0){
//				criteria.andMobileLike("%"+seller.getMobile()+"%");
//			}
//			if(seller.getTelephone()!=null && seller.getTelephone().length()>0){
//				criteria.andTelephoneLike("%"+seller.getTelephone()+"%");
//			}
//			if(seller.getStatus()!=null && seller.getStatus().length()>0){
//				criteria.andStatusLike("%"+seller.getStatus()+"%");
//			}
//			if(seller.getAddressDetail()!=null && seller.getAddressDetail().length()>0){
//				criteria.andAddressDetailLike("%"+seller.getAddressDetail()+"%");
//			}
//			if(seller.getLinkmanName()!=null && seller.getLinkmanName().length()>0){
//				criteria.andLinkmanNameLike("%"+seller.getLinkmanName()+"%");
//			}
//			if(seller.getLinkmanQq()!=null && seller.getLinkmanQq().length()>0){
//				criteria.andLinkmanQqLike("%"+seller.getLinkmanQq()+"%");
//			}
//			if(seller.getLinkmanMobile()!=null && seller.getLinkmanMobile().length()>0){
//				criteria.andLinkmanMobileLike("%"+seller.getLinkmanMobile()+"%");
//			}
//			if(seller.getLinkmanEmail()!=null && seller.getLinkmanEmail().length()>0){
//				criteria.andLinkmanEmailLike("%"+seller.getLinkmanEmail()+"%");
//			}
//			if(seller.getLicenseNumber()!=null && seller.getLicenseNumber().length()>0){
//				criteria.andLicenseNumberLike("%"+seller.getLicenseNumber()+"%");
//			}
//			if(seller.getTaxNumber()!=null && seller.getTaxNumber().length()>0){
//				criteria.andTaxNumberLike("%"+seller.getTaxNumber()+"%");
//			}
//			if(seller.getOrgNumber()!=null && seller.getOrgNumber().length()>0){
//				criteria.andOrgNumberLike("%"+seller.getOrgNumber()+"%");
//			}
//			if(seller.getLogoPic()!=null && seller.getLogoPic().length()>0){
//				criteria.andLogoPicLike("%"+seller.getLogoPic()+"%");
//			}
//			if(seller.getBrief()!=null && seller.getBrief().length()>0){
//				criteria.andBriefLike("%"+seller.getBrief()+"%");
//			}
//			if(seller.getLegalPerson()!=null && seller.getLegalPerson().length()>0){
//				criteria.andLegalPersonLike("%"+seller.getLegalPerson()+"%");
//			}
//			if(seller.getLegalPersonCardId()!=null && seller.getLegalPersonCardId().length()>0){
//				criteria.andLegalPersonCardIdLike("%"+seller.getLegalPersonCardId()+"%");
//			}
//			if(seller.getBankUser()!=null && seller.getBankUser().length()>0){
//				criteria.andBankUserLike("%"+seller.getBankUser()+"%");
//			}
//			if(seller.getBankName()!=null && seller.getBankName().length()>0){
//				criteria.andBankNameLike("%"+seller.getBankName()+"%");
//			}
//
//		}
//
//		Page<TbSeller> page= (Page<TbSeller>)sellerMapper.selectByExample(example);
//		return new PageResult(page.getTotal(), page.getResult());
//	}
	
}
