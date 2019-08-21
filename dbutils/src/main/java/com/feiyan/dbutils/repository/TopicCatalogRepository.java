package com.feiyan.dbutils.repository;

import com.feiyan.dbutils.dataObject.TopicCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 2:01 PM
 */
public interface TopicCatalogRepository extends JpaRepository<TopicCatalog,Integer> {

    List<TopicCatalog> findByCatalogId(Integer catalogId);

    void deleteByCatalogId(Integer catalogId);

}
