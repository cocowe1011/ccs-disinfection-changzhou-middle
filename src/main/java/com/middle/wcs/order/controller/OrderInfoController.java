package com.middle.wcs.order.controller;

import com.github.pagehelper.PageInfo;
import com.middle.wcs.hander.ResponseResult;
import com.middle.wcs.order.entity.po.OrderInfo;
import com.middle.wcs.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * (OrderInfo)控制器
 *
 * @author makejava
 * @since 2024-12-28 23:59:48
 */

@Api(tags = "订单管理接口")
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;
    
    @ApiOperation("保存订单信息")
    @PostMapping("/insert")
    public ResponseResult<OrderInfo> save(@ApiParam(value = "订单信息", required = true) @RequestBody OrderInfo po) {
        return ResponseResult.success(this.orderInfoService.save(po));
    }
    
    @ApiOperation("更新订单信息")
    @PostMapping("/update")
    public ResponseResult<Integer> update(@ApiParam(value = "订单信息", required = true) @RequestBody OrderInfo po) {
        return ResponseResult.success(this.orderInfoService.update(po));
    }

    @ApiOperation("更新订单信息-通过订单号和托盘号")
    @PostMapping("/updateByOrderIdAndTrayCode")
    public ResponseResult<Integer> updateByOrderIdAndTrayCode(@ApiParam(value = "订单信息", required = true) @RequestBody OrderInfo orderInfo) {
        return ResponseResult.success(this.orderInfoService.updateOrderByOrderIdAndTrayCode(orderInfo.getOrderId(), orderInfo.getTrayCode()));
    }

    @ApiOperation("根据ID查询订单信息")
    @GetMapping("/getOrderInfoById")
    public ResponseResult<OrderInfo> getOrderInfoById(@ApiParam(value = "订单ID", required = true) Long id) {
        return ResponseResult.success(this.orderInfoService.getOrderInfoById(id));
    }

    @ApiOperation("通过batchId查询订单信息")
    @GetMapping("/getOrderInfoByBatchId")
    public ResponseResult<OrderInfo> getOrderInfoByBatchId(@ApiParam(value = "批次ID", required = true) Long batchId) {
        return ResponseResult.success(this.orderInfoService.getOrderInfoByBatchId(batchId));
    }

    @ApiOperation("查询订单列表-正在执行和待执行的")
    @PostMapping("/queryOrderList")
    public ResponseResult<List<OrderInfo>> queryOrderList() {
        return ResponseResult.success(this.orderInfoService.queryOrderList());
    }

    @ApiOperation("查询订单-正在执行")
    @PostMapping("/getNowRunningOrder")
    public ResponseResult<OrderInfo> getNowRunningOrder() {
        return ResponseResult.success(this.orderInfoService.getNowRunningOrder());
    }



    /**
     * 综合查询-分页
     * @param orderInfo 订单信息（包含分页参数和查询条件）
     * @return 分页查询结果
     */
    @ApiOperation("综合查询-分页")
    @PostMapping("/selectListByPage")
    public ResponseResult<PageInfo<OrderInfo>> selectListByPage(@ApiParam(value = "订单信息（包含分页参数和查询条件）", required = true) @RequestBody OrderInfo orderInfo) {
        return ResponseResult.success(this.orderInfoService.selectListByPage(orderInfo));
    }

    @ApiOperation("综合查询")
    @PostMapping("/selectList")
    public ResponseResult<List<OrderInfo>> selectList(@ApiParam(value = "订单信息", required = true) @RequestBody OrderInfo po) {
        return ResponseResult.success(this.orderInfoService.selectList(po));
    }
}
