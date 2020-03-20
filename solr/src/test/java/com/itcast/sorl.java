package com.itcast;

import com.itcast.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Crotch;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class sorl {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testAdd() {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(new BigDecimal(2000));

        solrTemplate.saveBean(item);
        solrTemplate.commit();

    }

    @Test
    public void testFindOne() {
        TbItem byId = solrTemplate.getById(1, TbItem.class);
        System.out.println(byId.getTitle());
    }

    @Test
    public void testDeleteById() {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    @Test
    public void testInertArray() {
        ArrayList<TbItem> beans = new ArrayList<TbItem>();
        for (int i = 0; i < 100; i++) {
            TbItem item = new TbItem();
            item.setId(i + 1L);
            item.setBrand("华为" + i);
            item.setCategory("手机" + i);
            item.setGoodsId(1L + i);
            item.setSeller("华为2号专卖店" + i);
            item.setTitle("华为Mate9" + i);
            item.setPrice(new BigDecimal(2000));
            beans.add(item);
        }

        solrTemplate.saveBeans(beans);
        solrTemplate.commit();
    }

    @Test
    public void testPageQuery() {
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        simpleQuery.setOffset(20);
        simpleQuery.setRows(20);

        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(simpleQuery, TbItem.class);
        for (TbItem item : tbItems) {
            System.out.println(item.getTitle()+"  "+item.getPrice()+"  "+item.getBrand());
        }
        System.out.println("总记录数：" + tbItems.getTotalElements());
        System.out.println("总页数：" + tbItems.getTotalPages());

    }

    @Test
    public void testPageQuerymutil() {
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        Criteria item_title = new Criteria("item_title").contains("2");
        item_title = item_title.and("item_title").contains("5");
        simpleQuery.addCriteria(item_title);

//        simpleQuery.setOffset(20);
//        simpleQuery.setRows(20);

        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(simpleQuery, TbItem.class);
//        System.out.println("总记录数：" + tbItems.getTotalElements());
//        List<TbItem> content = tbItems.getContent();
        for (TbItem item : tbItems) {
            System.out.println(item.getTitle()+"  "+item.getPrice()+"  "+item.getBrand());
        }
        System.out.println("总记录数：" + tbItems.getTotalElements());
        System.out.println("总页数：" + tbItems.getTotalPages());

    }

    @Test
    public void deleteAll() {
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        solrTemplate.delete(simpleQuery);
        solrTemplate.commit();

    }
}
