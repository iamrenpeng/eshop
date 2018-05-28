package com.roncoo.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.roncoo.eshop.cache.ha.http.HttpClientUtils;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {
    private long productId;
    public GetProductInfoCommand(long productId){
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommand"));
        this.productId=productId;

    }
    @Override
    protected ProductInfo run() throws Exception {
        // 用HttpClient去调用商品服务的http接口
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return JSONObject.parseObject(response,ProductInfo.class);
    }
}
