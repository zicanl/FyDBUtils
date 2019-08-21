package com.feiyan.dbutils.dataObject;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 2:00 PM
 */
@Data
@Entity
@Table(name = "topic_catalog")
public class TopicCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer Id;

    @Column(name = "CATALOGID")
    private Integer catalogId;

    @Column(name = "TOPICID")
    private Integer topicId;
}
