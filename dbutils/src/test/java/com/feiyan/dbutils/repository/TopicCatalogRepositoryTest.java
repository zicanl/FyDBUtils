package com.feiyan.dbutils.repository;

import com.feiyan.dbutils.dataObject.TopicCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 2:01 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicCatalogRepositoryTest {

    @Autowired
    private TopicCatalogRepository repository;

    @Test
    public void findAllTest(){
        List<TopicCatalog> list = repository.findAll();
        Assert.assertTrue(list.size() > 0);
    }

}