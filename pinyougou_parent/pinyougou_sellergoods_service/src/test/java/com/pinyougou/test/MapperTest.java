//package com.pinyougou.test;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.pinyougou.mapper.BrandMapper;
//import com.pinyougou.pojo.Brand;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MapperTest {
//    private BrandMapper brandMapper;
//    private ApplicationContext act;
//
//    @Before
//    public void init(){
//        //初始化Spring容器
//        act = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
//
//        //从容器中获取BrandMapper 的实例
//        brandMapper = act.getBean(BrandMapper.class);
//    }
//
//
//    @Test
//    public void testSpring() {
//        String[] names = act.getBeanDefinitionNames();
//
//        for (String name : names) {
//            System.out.println(name);
//        }
//    }
//
//    @Test
//    public void testInsert() {
//        Brand tbBrand = new Brand();
//        tbBrand.setName("深圳黑马训练营");
//        tbBrand.setFirstChar("S");
////        tbBrandMapper.insert(tbBrand);
//        brandMapper.insertSelective(tbBrand);
//    }
//
//    @Test
//    public void testUpdateByPrimaryKey() {
//        Brand tbBrand = new Brand();
//        tbBrand.setId(25l);
//        tbBrand.setFirstChar("A");
////        int i = tbBrandMapper.updateByPrimaryKey(tbBrand);
//        int i = brandMapper.updateByPrimaryKeySelective(tbBrand);
//        System.out.println(i);
//
//    }
//
//    @Test
//    public void testUpdateByExample(){
//        Brand brand = new Brand();
//        brand.setName("小红果");
//        brand.setFirstChar("S");
//
//        //Example
//        Example example = new Example(Brand.class);
//        Example.Criteria criteria = example.createCriteria();
//
//        //修改firstchar名字为H   update tb_bran set name=xx where first_char=?
//        criteria.andEqualTo("firstChar",null);
//
////        brandMapper.updateByExample(brand,example);
//        brandMapper.updateByExampleSelective(brand,example);
//        //brandMapper.updateByPrimaryKey();
//        //brandMapper.updateByPrimaryKeySelective();
//    }
//
////    @Test
////    public void testUpdateByExample() {
////        Brand tbBrand = new Brand();
////        tbBrand.setName("深圳黑马训练营");
////
////        Example example = new Example(Brand.class);
////        Example.Criteria criteria = example.createCriteria();
////
////        criteria.andEqualTo("firstChar", "A");
////        int i = brandMapper.updateByExample(tbBrand, example);
////
////        System.out.println(i);
////
////    }
////
////    @Test
////    public void testUpdateByEcampleSelective(){
////        TbBrand tbBrand = new TbBrand();
////        tbBrand.setFirstChar("S");
////
////        Example example = new Example(TbBrand.class);
////        Example.Criteria criteria = example.createCriteria();
////
////        criteria.andEqualTo("firstChar", 'A');
////        int i = tbBrandMapper.updateByExampleSelective(tbBrand, criteria);
////
////        System.out.println(i);
////    }
////
////    @Test
////    public void testSelectBuPrimaryKey() {
////        long id = 25L;
////        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(id);
////        System.out.println(tbBrand);
////    }
//
////    @Test
////    public void testSelectOne() {
////        tbBrandMapper.
//
//    @Test
//    public void testExample() {
//        Example example = new Example(Brand.class);
//        Example.Criteria criteria = example.createCriteria();
//
//        ArrayList<Long> ids = new ArrayList<>();
//        ids.add(1L);
//        ids.add(3L);
//        ids.add(4L);
//        ids.add(5L);
//
//        criteria.andIn("id", ids);
//
//        List<Brand> brands = brandMapper.selectByExample(example);
//        for (Brand brand : brands) {
//            System.out.println(brand);
//        }
//    }
//
//    @Test
//    public void testDelect() {
//        Brand brand = new Brand();
//        brand.setName("LG");
//        brand.setFirstChar("L");
//
////        Example example = new Example(Brand.class);
////        Example.Criteria criteria = example.createCriteria();
//        List<Brand> brands = brandMapper.select(brand);
//        for (Brand b : brands) {
//            System.out.println(b);
//        }
//
//    }
//
//    @Test
//    public void testSelectAll() {
//        Brand brand = new Brand();
//        List<Brand> brands = brandMapper.selectAll();
//        for (Brand b : brands) {
//            System.out.println(b);
//        }
//    }
//
//    @Test
//    public void testDeleteByPrimaryKey() {
//        Long l = 24L;
//        int i = brandMapper.deleteByPrimaryKey(l);
//        System.out.println(i);
//    }
//
//    @Test
//    public void testDelete() {
//        Brand brand = new Brand();
//        brand.setName("深圳黑马训练营");
//        brand.setFirstChar("S");
//
//        int delete = brandMapper.delete(brand);
//        System.out.println(delete);
//    }
//
//    @Test
//    public void testPage() {
////        page当前页，size每页显示多少条
//        int page = 1, size = 10;
////        分页处理，只需要调用pageHelper.startPage静态方法即可
//        PageHelper.startPage(page, size);
//
//        List<Brand> brands = brandMapper.selectAll();
//        System.out.println(brands.size());
//
//        for (Brand b : brands) {
//            System.out.println(b);
//        }
//
//        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
//        System.out.println(brandPageInfo);
//
//    }
//
//
//
//}
