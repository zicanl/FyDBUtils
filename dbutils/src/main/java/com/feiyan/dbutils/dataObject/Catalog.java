package com.feiyan.dbutils.dataObject;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 12:23 PM
 */
import lombok.Data;

import javax.persistence.*;

/**
 * @作者: 李子灿
 * @日期：8/16/2019 11:30 AM
 */
@Data
@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATALOGID")
    private Integer catalogId;

    @Column(name = "CATALOGNAME")
    private String catalogName;

    @Column(name = "PARENTID")
    private Integer parentId;

    @Column(name = "SORTNUMBER")
    private Integer sortNumber;

    private Integer threeLevelPid;

    private Integer levelCode;
}

