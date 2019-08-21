package com.feiyan.dbutils.repository;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 12:25 PM
 */

import com.feiyan.dbutils.dataObject.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 11:32 AM
 */
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

    List<Catalog> findByParentIdOrderByCatalogName(Integer parentId);

}