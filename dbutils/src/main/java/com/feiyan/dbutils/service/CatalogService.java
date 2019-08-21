package com.feiyan.dbutils.service;

import com.feiyan.dbutils.dataObject.Catalog;

import java.util.List;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 12:25 PM
 */
public interface CatalogService {

    void assignSortNumber(Integer parentId);

    void moveParentTopicToChildren(Integer catalogId);

    void assignThreeLevelPId(Integer catalogId, Integer threeLevelId);

    void assignLevelId(Integer catalogId, Integer levelId);

    List<Catalog> findAll();

}