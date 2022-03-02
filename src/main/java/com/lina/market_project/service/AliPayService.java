package com.lina.market_project.service;


import java.util.List;

public interface AliPayService {
    /**
     * 支付宝支付接口
     * @param
     * @return AlipayApiException
     * @throws
     */
    String aliPay(String subject,String  tradeNo, float money, int userId, List<Integer> list,String url) throws Exception;

    String refund(String trade_no, double refund_amount) throws Exception;
}
