package com.feiyan.dbutils.service.impl;

import com.feiyan.dbutils.dataObject.Catalog;
import com.feiyan.dbutils.dataObject.TopicCatalog;
import com.feiyan.dbutils.repository.CatalogRepository;
import com.feiyan.dbutils.repository.TopicCatalogRepository;
import com.feiyan.dbutils.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 12:26 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CatalogServiceImplTest {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private TopicCatalogRepository topicCatalogRepository;

    @Test
    public void assignSortNumber() {
        catalogService.assignSortNumber(0);
    }

    @Test
    public void moveTest() {
        List<Catalog> catalogList = catalogService.findAll();
        for (Catalog catalog : catalogList) {
            if(catalogRepository.findByParentIdOrderByCatalogName(catalog.getCatalogId()).size() > 0){
                catalogService.moveParentTopicToChildren(catalog.getCatalogId());
            }
        }
    }

    @Test
    public void verifyMove() {
        List<Catalog> catalogList = catalogService.findAll();
        for (Catalog catalog : catalogList) {
            List<Catalog> catalogs = catalogRepository.findByParentIdOrderByCatalogName(catalog.getCatalogId());
            if (catalogs.size() > 0) {
                List<TopicCatalog> topicCatalogList = topicCatalogRepository.findByCatalogId(catalog.getCatalogId());

                Assert.assertTrue(topicCatalogList.size() == 0);
            }
        }
    }

    @Test
    public void assignThreeLevelPIdTest(){
        catalogService.assignThreeLevelPId(0,null);
    }

    @Test
    public void assignLevelIdTest(){
        catalogService.assignLevelId(0,1);
    }
}
