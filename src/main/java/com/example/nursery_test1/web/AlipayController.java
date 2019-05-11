package com.example.nursery_test1.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AlipayController {
//    @Autowired
//    private AlipayClient client;//支付宝请求sdk客户端

    /**
     * 测试地址：http://localhost:8080/alipay/20150320010101001/10.00/0
     * @param orderId
     * @param money
     * @param subject
     * @param response
     * @throws AlipayApiException
     * @throws IOException
     */
    @RequestMapping(value="/alipay/{orderId}/{money}/{subject}",method= RequestMethod.GET)
    public void pay(@PathVariable String orderId,@PathVariable String money,@PathVariable int subject,HttpServletResponse response) throws Exception {

        AlipayClient client = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016093000632887", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCOH+RyN6vsCgmJsL3+lnPvdROJAATVJLESQ7AQlypTNOhHCyx0aFPmx3KRHTCdSwjuDBrKkC8mH/TjvKIL+iU3GKB8eK8EJRUVoNC+pJJP52AwRqRWmO4JsOrq9wq2x87H2Ywi7IafE9OJcz44iwOn3qQUuon3jWBpFHFydgWcmkeNwWWoWVoJ7gKNwPmT1cf3zHoukjTRsRfE1Dajs2+UNcprbtX5pB2clKNZPtCrujSRnJcGE3kQSdlDWbWSa41gvun5JQIGfK1DgLW874u842HepCehXaT6Yg80CCS0DvCEBDUPYjM1JaChWZ8PbZQO6qMyFbeG8SpGHhl+h76nAgMBAAECggEAfZNsVAcgntRXjRNfLH1fPqrhceFUY8ehtROdk2sdeJt8yJj01H4B9B0x+0ohVDmiUELdwTEYl8UQqdBYFeEV1NKrqo8vhKgHMy7+ghQDOxqjAMTKG8HQQTvStX1GOC3fZPGxQoJeiFwKQp5S0b7yofa0X6RalyKRlwb7Hjltx+mtn2x1YJ91BMeuK7COExuVZgiziAIcNv1sGdNYcsKPECHyqW+64Ss5jtueTfQ/ra0gWIRJ+poffXWweVjYqgDx6b/dbHoPZP6VOZApXx702iriLgSInESQUhWAJLI2jbYdVKUwQgsy8KnloaAWtap62F4jGnTKeo8QwjJzXh3c8QKBgQDYaS242cVVnfBMhztfd/YDDQtHGQHJm1vtidjicp+ZbW2a23JVY6Foj2IOdvIprKmotkWDeYpIt8I5ll22X4jd1NqXlyI8jXBlToxiuaaf7E/jARbrhfHAP3gzfY/9BRSG5D3/047ffvDRA/qYqafGa4LxLfj4pGU2iC7JMQ9W8wKBgQCoH8i/38oqsEhuC9Upn57C3gxko9mO4m7vL/ncIr2DgMeE5gQ2BudY3YjSC5/EmXMkdgSPuIzttwkqvJ6wJfXYthqq+jM+Sv/W69CHLu1WMhqJX96aqvk/IIRKR7I9qjNZlN6v7XG+Rm6S7QnAh6HHb7X76gdzPsda6aTXE9MOfQKBgD6/MEyah8btRlFKHILvy1rTG2P/8i68ZwkWoNX3miLoQZFW822it5gTisDa6BgBQifywXD/eoLfFOeRm5ZXWN3I66BsZ7ba463b+oRdg9x2risJk9WyPogZXwa+71LTeEIsR3W3p4NR0Q0pdC1zc3vqjErVo4ykTdxj2/zlxqbVAoGBAJiCTJ8RqNjUlL+O6E0byO7IcsGQiLg+25cKFEQHF5cE6Pi7lzJTTOWmAIlZlhLHiFWY4UZmiSDSmvQzqlfr93ogp0xWLsnhSvdxbvZSMWWxibT4THxNgFAtdRPUufGKRY6Lpv48DKQoTAHoGKKX5r99rL3aa+u1FS55J9ka2KgZAoGBALQuT8CDkwB7YDN7W3ZU2KPPkHWxmdYbxzKEbkzp6sWj35LHhIiPM9jCUgRglKSIb85Jn5NAxAsvkqPweS10V2hkZTltr51FDKURX3yiWHuNDAFqFoeVhSTH/fzvb2LMDDv3v+XwuM4P0X8Q1sXrkmxUueI4oemx19A+DR5LK6Su", "json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjh/kcjer7AoJibC9/pZz73UTiQAE1SSxEkOwEJcqUzToRwssdGhT5sdykR0wnUsI7gwaypAvJh/047yiC/olNxigfHivBCUVFaDQvqSST+dgMEakVpjuCbDq6vcKtsfOx9mMIuyGnxPTiXM+OIsDp96kFLqJ941gaRRxcnYFnJpHjcFlqFlaCe4CjcD5k9XH98x6LpI00bEXxNQ2o7NvlDXKa27V+aQdnJSjWT7Qq7o0kZyXBhN5EEnZQ1m1kmuNYL7p+SUCBnytQ4C1vO+LvONh3qQnoV2k+mIPNAgktA7whAQ1D2IzNSWgoVmfD22UDuqjMhW3hvEqRh4Zfoe+pwIDAQAB", "RSA2");
        JSONObject data=new JSONObject();
        //订单号,必填
        data.put("out_trade_no", orderId);
        //PC支付 FAST_INSTANT_TRADE_PAY, APP支付 QUICK_MSECURITY_PAY, 移动H5支付 QUICK_WAP_PAY
        data.put("product_code","FAST_INSTANT_TRADE_PAY");
        //付款金额,必填
        data.put("total_amount", money);
        //订单描述,必填
        if(subject==0)
            data.put("subject","充值业务");
        //该笔订单允许的最晚付款时间，逾期将关闭交易
        //data.put("timeout_express","");
        //公共校验参数
        //data.put("passback_params","");
        //PC支付
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //APP支付
        //AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //移动H5支付
        //AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //异步通知地址
        request.setNotifyUrl("http://localhost:8765/pay/alipay/notify");
        //同步通知地址
        request.setReturnUrl("http://localhost:8080/register/"+orderId);
        //业务参数
        System.out.println("业务参数json数据————————"+JSONObject.valueToString(data));
        request.setBizContent(JSONObject.valueToString(data));
        AlipayTradePagePayResponse alipayResponse=client.pageExecute(request);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(alipayResponse.getBody());
    }


    @GetMapping("alipayResponse")
    public String acceptAlipay(HttpServletRequest request,HttpServletResponse response){
        response.getContentType();
        return response.getContentType();
    }
}
