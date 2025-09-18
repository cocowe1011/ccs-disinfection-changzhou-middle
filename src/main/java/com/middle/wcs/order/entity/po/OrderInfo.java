package com.middle.wcs.order.entity.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * (OrderInfo)实体类
 *
 * @author makejava
 * @since 2024-12-28 23:59:48
 */
@Data
@TableName("order_info")
public class OrderInfo {
    /**
    * 主键
    */
    @TableId
    private String id;

    /**
    * 插入时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertTime;

    /**
     * 完成时间 finish_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;


    /**
    * 生产总订单号
    */
    private String orderId;

    /**
    * 产品编号
    */
    private String productCode;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 托盘码
    */
    private String trayCode;

    /**
     * 收货单号
     */
    private String receiptOrderCode;

    /**
    * 进货口指定：1、一楼外部进货。2、二楼进货。3、三楼进货。4、四楼进货
    */
    private Integer inPut;

    /**
     * 订单状态，0待执行1已完成
     */
    private Integer orderStatus;

    /**
    * 托盘是否灭菌：0不灭菌，1灭菌
    */
    private Integer isTerile;

    /**
     * 作废标识，0未作废，1作废
     */
    private Integer invalidFlag;

    /**
     * 一个产品编号一条明细
     */
    private String detailList;

    /** 预热间名称/编号 */
    private String preheatingRoom;

    /** 进入预热间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inPreheatingRoomTime;

    /** 离开预热间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outPreheatingRoomTime;

    /** 灭菌间名称/编号 */
    private String sterilizationRoom;

    /** 进入灭菌间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inSterilizationRoomTime;

    /** 离开灭菌间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outSterilizationRoomTime;

    /** 解析间名称/编号 */
    private String analysisRoom;

    /** 进入解析间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inAnalysisRoomTime;

    /** 离开解析间时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outAnalysisRoomTime;

    /** 批号 */
    private String batchNo;

    /**
     * 备注
     */
    private String remark;

    /** 规格 */
    private String unit;

    /** 规格数量 */
    private String receivedPkgQuantity;

    /**
     * 起始页数
     */
    @TableField(exist = false)
    @NotNull(message = "起始页数不能为空")
    private Integer pageNum;

    /**
     * 每页大小
     */
    @TableField(exist = false)
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;
}
