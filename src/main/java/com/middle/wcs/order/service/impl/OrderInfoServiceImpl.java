package com.middle.wcs.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.middle.wcs.order.entity.po.OrderInfo;
import com.middle.wcs.order.dao.OrderInfoMapper;
import com.middle.wcs.order.service.OrderInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * (OrderInfo)服务实现类
 *
 * @author makejava
 * @since 2024-12-28 23:59:48
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    
    @Resource
    private OrderInfoMapper orderInfoMapper;
    
    @Override
    public OrderInfo save(OrderInfo orderInfo) {
        orderInfoMapper.insert(orderInfo);
        return orderInfo;
    }
    
    @Override
    public Integer update(OrderInfo orderInfo) {
        return orderInfoMapper.updateById(orderInfo);
    }
    
    @Override
    public OrderInfo getOrderInfoById(Long id) {
        return orderInfoMapper.selectById(id);
    }
    
    @Override
    public List<OrderInfo> queryOrderList() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setInvalidFlag(0);
        QueryWrapper<OrderInfo> wrapper= new QueryWrapper<>(orderInfo);
        wrapper.ne("order_status", 3);
        wrapper.orderByDesc("order_status").orderByAsc("insert_time");
        return orderInfoMapper.selectList(wrapper);
    }

    @Override
    public PageInfo<OrderInfo> selectListByPage(OrderInfo orderInfo) {
        // 分页查询
        Page<OrderInfo> page = startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
        
        // 构建查询条件
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        
        // 如果有具体查询条件，添加到查询条件中
        if (orderInfo.getOrderId() != null && !orderInfo.getOrderId().isEmpty()) {
            queryWrapper.eq("order_id", orderInfo.getOrderId());
        }
        if (orderInfo.getProductCode() != null && !orderInfo.getProductCode().isEmpty()) {
            queryWrapper.eq("product_code", orderInfo.getProductCode());
        }
        if (orderInfo.getProductName() != null && !orderInfo.getProductName().isEmpty()) {
            queryWrapper.eq("product_name", orderInfo.getProductName());
        }
        if (orderInfo.getTrayCode() != null && !orderInfo.getTrayCode().isEmpty()) {
            queryWrapper.eq("tray_code", orderInfo.getTrayCode());
        }
        if (orderInfo.getInPut() != null) {
            queryWrapper.eq("in_put", orderInfo.getInPut());
        }
        if (orderInfo.getOrderStatus() != null) {
            queryWrapper.eq("order_status", orderInfo.getOrderStatus());
        }
        if (orderInfo.getIsTerile() != null) {
            queryWrapper.eq("is_terile", orderInfo.getIsTerile());
        }
        if (orderInfo.getInvalidFlag() != null) {
            queryWrapper.eq("invalid_flag", orderInfo.getInvalidFlag());
        }
        
        // 按插入时间倒序排列
        queryWrapper.orderByDesc("insert_time");
        
        List<OrderInfo> list = orderInfoMapper.selectList(queryWrapper);
        PageInfo<OrderInfo> voPage = new PageInfo<>(page);
        return voPage;
    }

    @Override
    public OrderInfo getNowRunningOrder() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setInvalidFlag(0);
        QueryWrapper<OrderInfo> wrapper= new QueryWrapper<>(orderInfo);
        wrapper.eq("order_status", 1);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(wrapper);
        if (orderInfos != null && orderInfos.size() > 0) {
            return orderInfos.get(0);
        }
        return null;
    }

    @Override
    public OrderInfo getOrderInfoByBatchId(Long batchId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setInvalidFlag(0);
        QueryWrapper<OrderInfo> wrapper= new QueryWrapper<>(orderInfo);
        wrapper.eq("batch_id", batchId);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(wrapper);
        if (orderInfos != null && orderInfos.size() > 0) {
            return orderInfos.get(0);
        }
        return null;
    }

    @Override
    public List<OrderInfo> selectList(OrderInfo po) {
        // 根据入参构建查询条件
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>(po);
        // 根据his_create_time排序
        queryWrapper.orderByDesc("insert_time");
        return this.orderInfoMapper.selectList(queryWrapper);
    }

    @Override
    public Integer updateOrderByOrderIdAndTrayCode(String orderId, String trayCode) {
        // 构建查询条件
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        queryWrapper.eq("tray_code", trayCode);
        // 按插入时间倒序排序
        queryWrapper.orderByDesc("insert_time");

        // 查询记录
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        if (orderInfo == null) {
            return 0; // 没有找到记录
        }

        // 更新完成时间和状态
        orderInfo.setFinishTime(new Date());
        orderInfo.setOrderStatus(1); // 1表示已完成

        // 执行更新
        return orderInfoMapper.updateById(orderInfo);
    }
}
