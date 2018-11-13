package com.example.spring_boot_alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.spring_boot_alipay.model.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Author gz
 * @Date 2018/11/13 21:40
 */
@Controller
@RequestMapping("/alipay/page")
public class AlipayPayController {

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayController alipayController;

    @PostMapping("/goPay")
    public void gotoPayPage(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("支付测试");
        model.setTotalAmount("0.01");
        model.setBody("支付测试，共0.01元");
        model.setProductCode(productCode);

        AlipayTradePagePayRequest pagePayRequest =new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl("http://2qcsc3.natappfree.cc/alipay/page/returnUrl");
        pagePayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        pagePayRequest.setBizModel(model);

        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(pagePayRequest).getBody();
        response.setContentType("text/html;charset=" + alipayProperties.getCharset());
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        response.setContentType("text/html;charset=" + alipayProperties.getCharset());

        boolean verifyResult = alipayController.rsaCheckV1(request);
        System.out.println(verifyResult);
        if(verifyResult){
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交//商户订单号易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            return "paySuccess";
        }else{
            return "pagePayFail";
        }
    }
}
