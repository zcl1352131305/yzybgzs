package cn.gov.spb.cq.yzglfzgj.config;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class SetSignConfig {
   
    public static String  setSign(WxPayUnifiedOrderRequest orderRequest ) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuffer sa = new StringBuffer();
        sa.append("appid="+orderRequest.getAppid());
        if(orderRequest.getAttach() != null){
            sa.append("&attach="+orderRequest.getAttach());
        }
        if(orderRequest.getBody() != null){
            sa.append("&body="+orderRequest.getBody());
        }
        if(orderRequest.getMchId() != null){
            sa.append("&mch_id="+orderRequest.getMchId());
        }
        if(orderRequest.getNonceStr() != null){
            sa.append("&nonce_str="+orderRequest.getNonceStr());
        }
        if(orderRequest.getNotifyURL() != null){
            sa.append("&notify_url="+orderRequest.getNotifyURL());
        }
        if(orderRequest.getOpenid() != null){
            sa.append("&openid="+orderRequest.getOpenid());
        }
        if(orderRequest.getOutTradeNo() != null){
            sa.append("&out_trade_no="+orderRequest.getOutTradeNo());
        }
        if(orderRequest.getProductId() != null){
            sa.append("&product_id="+orderRequest.getProductId());
        }
        if(orderRequest.getSpbillCreateIp() != null){
            sa.append("&spbill_create_ip="+orderRequest.getSpbillCreateIp());
        }
        if(orderRequest.getTotalFee() != 0){
            sa.append("&total_fee="+orderRequest.getTotalFee());
        }
        if(orderRequest.getTradeType() != null){
            sa.append("&trade_type="+orderRequest.getTradeType());
        }
        sa.append("&key=09059d887c163a3396d1d80da2b140cc");
        System.out.println(sa.toString());
        String sign = CipherUtils.hmd5(sa.toString()).toUpperCase();
        
        return  sign;
    }
}
