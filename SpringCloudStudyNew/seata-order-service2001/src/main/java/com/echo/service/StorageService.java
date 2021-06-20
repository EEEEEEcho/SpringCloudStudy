package com.echo.service;

import com.echo.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
    /**
     * 向库存微服务发起post请求，使其减少productId所对应的商品的库存，减少的数量为count
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decreaseStorage(@RequestParam("productId")Long productId,
                                 @RequestParam("count")Integer count);
}
