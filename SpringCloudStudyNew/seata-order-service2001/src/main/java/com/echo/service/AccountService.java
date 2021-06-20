package com.echo.service;

import com.echo.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    /**
     * 向账户微服务发起post请求，使其减少userId所对应用户的账户余额，减少的数量为money
     * @param userId
     * @param money
     * @return
     */
    @PostMapping(value = "/account/decrease")
    CommonResult decreaseStorage(@RequestParam("userId")Long userId,
                                 @RequestParam("money") BigDecimal money);
}
