package com.pinyougou.sellergoods.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.pinyougou.GoodsService;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsDescMapper goodsDescMapper;

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private SellerMapper sellerMapper;

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Goods> findAll() {
		return goodsMapper.selectAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo<Goods> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> goods = goodsMapper.selectAll();
		PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(goods);
		return goodsPageInfo;
	}

	/***
	 * 增加Goods信息
	 * @param goods
	 * @return
	 */
	@Override
	public int add(Goods goods) {
		//默认状态
		goods.setAuditStatus("0");
		//增加tb_goods
		int acount = goodsMapper.insertSelective(goods);

		//增加tb_goods_desc
		//@GeneratedValue(strategy = GenerationType.IDENTITY)   用于获取主键自增值
		GoodsDesc goodsDesc = goods.getGoodsDesc();
		goodsDesc.setGoodsId(goods.getId());
		goodsDescMapper.insertSelective(goodsDesc);
		addItems(goods);
		return acount;
	}

	/***
	 * 增加商品
	 * @param goods
	 */
	public void addItems(Goods goods) {
		//category
		ItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id());
		//brand
		Brand brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
		//seller
		Seller seller = sellerMapper.selectByPrimaryKey(goods.getSellerId());
		//当前时间
		Date now = new Date();

		//如果启用了规格，则批量增加SKU  item
		if (goods.getIsEnableSpec().equals("1")) {
			//增加SKU
			for (Item item : goods.getItemList()) {
				//标题  华为荣耀4 16G  联通3G
				String title = "";

				//获取goods的名称
				title = goods.getGoodsName();

				//规格属性   {"机身内存":"16G","网络":"联通3G"}
				Map<String, String> specMap = JSON.parseObject(item.getSpec(), Map.class);
				for (Map.Entry<String, String> entry : specMap.entrySet()) {
					//标题  华为荣耀4 16G  联通3G
					title += "  " + entry.getValue().toString();
				}
				item.setTitle(title);

				//图片
				// [{"color":"白色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXEWAWuHOAAjlKdWCzvg949.jpg"},
				// {"color":"黑色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXEuAB_ujAAETwD7A1Is158.jpg"},
				// {"color":"蓝色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXFWANtjTAAFa4hmtWek619.jpg"}]
				//调用抽取方法初始化
				goodsParameterInit(goods, itemCat, brand, seller, now, item);

				//增加到数据库
				itemMapper.insertSelective(item);
			}
		} else {
			Item item = new Item();
			//获取goods的名称
			String goodsName = goods.getGoodsName();
			item.setTitle(goodsName);
			//调用抽取方法初始化
			goodsParameterInit(goods, itemCat, brand, seller, now, item);

			//缺4个
			item.setPrice(goods.getPrice());    //价格
			item.setStatus("1");            //是否启用
			item.setNum(1);                 //数量
			item.setIsDefault("1");         //是否是默认的商品

			//增加到数据库
			itemMapper.insertSelective(item);
		}
	}

	//商品信息初始化
	public void goodsParameterInit(Goods goods, ItemCat itemCat, Brand brand, Seller seller, Date now, Item item) {
		//图片
		// [{"color":"白色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXEWAWuHOAAjlKdWCzvg949.jpg"},
		// {"color":"黑色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXEuAB_ujAAETwD7A1Is158.jpg"},
		// {"color":"蓝色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVmNXFWANtjTAAFa4hmtWek619.jpg"}]
		String goodsDescitemimages = goods.getGoodsDesc().getItemImages();
		List<Map> imagesMap = JSON.parseArray(goodsDescitemimages, Map.class);
		String imageimage = imagesMap.get(0).get("url").toString();
		item.setImage(imageimage);

		//分类ID
		item.setCategoryid(goods.getCategory3Id());
		//创建时间，修改时间
		item.setUpdateTime(now);
		item.setCreateTime(now);

		//设置SKU也就是goods的id
		item.setGoodsId(goods.getId());

		//设置sellerid
		item.setSellerId(goods.getSellerId());

		//category
		item.setCategory(itemCat.getName());

		//brand
		item.setBrand(brand.getName());

		//seller
		item.setSeller(seller.getName());
	}
//	public int add(Goods goods) {
//		//默认状态
//		goods.setAuditStatus("0");
//		//增加Goods
//		int acount = goodsMapper.insertSelective(goods);
//
//		//GoodsDesc
//		GoodsDesc goodsDesc = goods.getGoodsDesc();
//		goodsDesc.setGoodsId(goods.getId());
//		goodsDescMapper.insertSelective(goodsDesc);
//
//		saveItems(goods);
//		return acount;
//	}
//
//
//	private void saveItems(Goods goods) {
//		//获取品牌信息
//		Brand brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
//		//获取商家信息
//		Seller seller = sellerMapper.selectByPrimaryKey(goods.getSellerId());
//		//商品分类-三级分类
//		ItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id());
//
//		//当前时间
//		Date now = new Date();
//		//增加SKU信息
//		for (Item item : goods.getItemList()) {
//			//组装title
//			String title = goods.getGoodsName();
//			//将Spec（规格转成Map集合）
//			Map<String,String> specMap = (Map<String, String>) JSON.parse(item.getSpec());
//			for (Map.Entry<String, String> entity : specMap.entrySet()) {
//				//拼接标题
//				title+=" "+entity.getValue();
//			}
//			item.setTitle(title);
//			//补全其他数据
//			item.setGoodsId(goods.getId());     //商品SPU编号
//			item.setSellerId(goods.getSellerId());//商家编号
//			item.setCategoryid(goods.getCategory3Id());//商品分类编号：三级编号
//			item.setCreateTime(now);        //创建日期
//			item.setUpdateTime(now);        //修改日期
//			//设置品牌信息
//			item.setBrand(brand.getName());
//			//设置商家信息
//			item.setSeller(seller.getNickName());
//			//设置三级分类
//			item.setCategory(itemCat.getName());
//			//获取商品第一张图片
//			List<Map> imageMap =JSON.parseArray(goods.getGoodsDesc().getItemImages(),Map.class);
//			if(imageMap.size()>0){
//				item.setImage(imageMap.get(0).get("url").toString());
//			}
//			//增加商品SKU入库
//			itemMapper.insertSelective(item);
//		}
//	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Goods goods){
		//修改Goods   状态由审核通过变成审核待审核   update goods set xxx  where sellerid=goods.getSellerId();
		goods.setAuditStatus("0");
		int mcount = goodsMapper.updateByPrimaryKeySelective(goods);
		//修改GoodsDesc
		goodsDescMapper.updateByPrimaryKeySelective(goods.getGoodsDesc());

		//删除items  delete from tb_items where goodsid=?
		Item item = new Item();
		item.setGoodsId(goods.getId());
		itemMapper.delete(item);

		//新增items -for循环增加-批量增加-xml  foreach:效率高
		//goods.getItems();   ?????
		addItems(goods);

		//采用xml的foreach方式增加
		//获取所有Items集合
		//List<Item> items = buildItems(goods);
		//itemMapper.batchInsert(items);

		//异常测试事务
		//int q=10/0;

//		return mcount;
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Goods findOne(Long id){
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(goodsDesc);

		//查询sku商品列表
		Example example = new Example(Item.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("goodsId", id);
//		criteria.andNotEqualTo("isDelete", "1");
		List<Item> items = itemMapper.selectByExample(example);
		goods.setItemList(items);

		return goods;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			Goods goods = goodsMapper.selectByPrimaryKey(id);
			goods.setIsDelete("1");
			goodsMapper.updateByPrimaryKey(goods);
		}
	}
	
	
		@Override
	public PageInfo<Goods> findPage(Goods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

			Example example = new Example(Goods.class);
			Example.Criteria criteria = example.createCriteria();

			if (goods != null) {
				criteria.andEqualTo("sellerId", goods.getSellerId());

				if (StringUtils.isNotBlank(goods.getAuditStatus())) {
					criteria.andEqualTo("auditStatus", goods.getAuditStatus());
				}
				if (StringUtils.isNotBlank(goods.getGoodsName())) {
					criteria.andLike("goodsName", "%" + goods.getGoodsName() + "%");
				}
			}
			criteria.andIsNull("isDelete");

			List<Goods> goodsList = goodsMapper.selectByExample(example);
			PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(goodsList);
			return goodsPageInfo;
		}

    @Override
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setAuditStatus(status);
            goodsMapper.updateByPrimaryKey(goods);
        }
    }

}
