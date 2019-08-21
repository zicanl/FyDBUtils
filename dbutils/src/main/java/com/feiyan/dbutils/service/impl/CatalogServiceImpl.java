package com.feiyan.dbutils.service.impl;

import com.feiyan.dbutils.dataObject.Catalog;
import com.feiyan.dbutils.dataObject.TopicCatalog;
import com.feiyan.dbutils.repository.CatalogRepository;
import com.feiyan.dbutils.repository.TopicCatalogRepository;
import com.feiyan.dbutils.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 12:26 PM
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private TopicCatalogRepository topicCatalogRepository;

    private Integer sortNumber;

    @Override
    public void assignSortNumber(Integer parentId) {
        Catalog parentCatalog = catalogRepository.findById(parentId).orElse(null);
        if (parentCatalog == null) {
            sortNumber = 1;
        } else {
            sortNumber = parentCatalog.getSortNumber() + 1;
        }
        List<Catalog> catalogList = catalogRepository.findByParentIdOrderByCatalogName(parentId);
        for (Catalog catalog : catalogList) {
            catalog.setSortNumber(sortNumber);
            catalogRepository.save(catalog);
            sortNumber++;
            helper(catalog);
        }
    }

    @Override
    @Transactional
    public void moveParentTopicToChildren(Integer catelogId) {
        List<TopicCatalog> topics = topicCatalogRepository.findByCatalogId(catelogId);
        for(TopicCatalog topicCatalog: topics){
            topicCatalogRepository.deleteByCatalogId(catelogId);
            moveTopic(topicCatalog.getTopicId(), catelogId);
        }
    }

    @Override
    @Transactional
    public void assignThreeLevelPId(Integer catalogId, Integer threeLevelId) {
        List<Catalog> catalogList = catalogRepository.findByParentIdOrderByCatalogName(catalogId);
        for(Catalog catalog: catalogList){
            if(catalog.getLevelCode() == 3){
                threeLevelId = catalog.getCatalogId();
            }
            if(null != threeLevelId){
                if(catalog.getLevelCode() >= 4){
                    catalog.setThreeLevelPid(threeLevelId);
                    catalogRepository.save(catalog);
                }
            }
            assignThreeLevelPId(catalog.getCatalogId(), threeLevelId);

        }

    }

    @Override
    @Transactional
    public void assignLevelId(Integer catalogId, Integer levelId) {
        List<Catalog> catalogList = catalogRepository.findByParentIdOrderByCatalogName(catalogId);
        for(Catalog catalog: catalogList){
            catalog.setLevelCode(levelId);
            catalogRepository.save(catalog);
            assignLevelId(catalog.getCatalogId(), levelId+1);
        }
    }

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    private void helper(Catalog parent) {
        List<Catalog> catalogList = catalogRepository.findByParentIdOrderByCatalogName(parent.getCatalogId());
        if (catalogList.size() > 0) {
            for (Catalog catalog : catalogList) {
                catalog.setSortNumber(sortNumber);
                catalogRepository.save(catalog);
                sortNumber++;
                helper(catalog);
            }
        }
    }


    private void moveTopic(Integer topicId, Integer catelogId) {
        List<Catalog> catalogList = catalogRepository.findByParentIdOrderByCatalogName(catelogId);
        if (catalogList.size() > 0) {
            for (Catalog catalog : catalogList) {
                moveTopic(topicId, catalog.getCatalogId());
            }
        }else{
            TopicCatalog topicCatalog = new TopicCatalog();
            topicCatalog.setCatalogId(catelogId);
            topicCatalog.setTopicId(topicId);
            topicCatalogRepository.save(topicCatalog);
        }
    }


}