package com.curiosity.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname Order
 * @Description
 * @Date 2021/7/1 17:40
 */

@Data
@ToString
public class Order {
    //订单原始价格，即优惠前价格
    private Double originalPrice;

    //订单真实价格，即优惠后价格
    private Double realPrice;
}
