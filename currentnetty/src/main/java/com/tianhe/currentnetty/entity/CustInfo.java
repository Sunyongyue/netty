package com.tianhe.currentnetty.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("doc_cust_info")
public class CustInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 最后一级id
     */
    private Integer addrId;
    /**
     * 用户地址集合
     */
    private String addrIds;
    /**
     * 地址名称
     */
    private String address;
    /**
     * 详细地址名称
     */
    private String addressMore;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除状态位0未删除，1已删除
     */
    private Integer deleted;
    /**
     * 启用状态位0未开户，1已开户
     */
    private Integer enable;
    /**
     * 用户状态位0正常1停用2注销
     */
    private Integer status;
    /**
     * 创建人
     */
    @TableField(value = "createBy")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private String createTime;

    /**
     * 更新人
     */
    @TableField(value = "lastUpdateBy")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @TableField(value = "lastUpdateTime")
    private String lastUpdateTime;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 租户id
     */
    private Integer tenantId;
//    /**
//     * DTU信息
//     */
//    @TableField(exist = false)
//    private List<DtuInfo> dtuInfoList;
//
//    @TableField(exist = false)
//    private DtuInfo dtu;
//    @TableField(exist = false)
//    private TerminalInfo terminal;
//    @TableField(exist = false)
//    private Integer[] addrArray;
//    @TableField(exist = false)
//    private Integer rechargeType;
//    public Integer[] getAddrArray() {
//        return Convert.toIntArray(addrIds.substring(0,addrIds.length()-1).split(",")) ;
//    }

}
