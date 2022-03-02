package com.lina.market_project.service.impl;



import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;

import com.lina.market_project.service.AliPayService;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Service;

import javax.swing.text.AbstractDocument;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AliPayServiceImpl implements AliPayService {


    /**
     * 生成支付表单
     * @param subject 商品描述
     * @param money 支付金额
     * @param tradeNo 订单号
     * @return 返回一个支付表单
     * @throws Exception
     */
    @Override
    public String aliPay(String subject,String tradeNo, float money, int userId, List<Integer> list,String url) throws Exception {
        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay(subject, tradeNo,
                String.valueOf(money), url+"/#/payPage?userId="+userId+"&order="+list);
        String payForm = null;
        if (ResponseChecker.success(pay)) {
            payForm = pay.getBody();
        }
        return payForm;
    }

    @Override
    public String refund(String trade_no, double refund_amount) throws Exception {
        try {
            AlipayTradeRefundResponse response = Factory.Payment
                    .Common()
                    //添加唯一标识可用于部分退款
                    .optional("out_request_no",String.valueOf(new Date()))
                    // 调用交易退款(商家订单号, 退款金额)
                    .refund(trade_no,String.valueOf(refund_amount));

            if (response.getMsg().equals("Success")) {return "退款成功";}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "退款失败";
    }
}
