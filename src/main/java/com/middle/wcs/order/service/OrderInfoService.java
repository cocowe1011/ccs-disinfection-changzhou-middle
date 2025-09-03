package com.middle.wcs.order.service;

import com.middle.wcs.order.entity.po.OrderInfo;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * (OrderInfo)服务接口
 *
 * @author makejava
 * @since 2024-12-28 23:59:48
 */
public interface OrderInfoService {
    
    /**
     * 保存订单信息
     *
     * @param orderInfo 订单信息
     */
    Integer save(OrderInfo orderInfo);
    
    /**
     * 更新订单信息
     *
     * @param orderInfo 订单信息
     */
    Integer update(OrderInfo orderInfo);
    
    /**
     * 根据id查询订单信息
     *
     * @param id 订单id
     * @return 订单信息
     */
    OrderInfo getOrderInfoById(Long id);
    
    /**
     * 根据条件查询订单列表
     *
     * @return 订单列表
     */
    List<OrderInfo> queryOrderList();

    PageInfo<OrderInfo> selectListByPage(OrderInfo orderInfo);

    OrderInfo getNowRunningOrder();

    OrderInfo getOrderInfoByBatchId(Long batchId);

    List<OrderInfo> selectList(OrderInfo po);

    /**
     * 根据订单号和托盘号更新完成时间和状态
     *
     * @param orderId 订单号
     * @param trayCode 托盘号
     * @return 更新影响的行数
     */
    Integer updateOrderByOrderIdAndTrayCode(String orderId, String trayCode);
}
