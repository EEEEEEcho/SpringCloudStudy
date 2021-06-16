package com.echo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-----TestA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "-----TestB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "-----TestD";
    }

    @GetMapping("/testE")
    public String testE() {

        log.info("testE 异常比例");
        int age = 10 / 0;
        return "-----TestE";
    }

    @GetMapping("/testF")
    public String testF() {
        log.info("testF 异常数量");
        int age = 10 / 0;
        return "-----TestF";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",//value值一般与GetMapping中的一样，只不过不加/，当然名称任意，只要唯一
            blockHandler = "dealTestHotKey" //如果违背了配置的热点规则，就由dealTestHotKey来兜底
    )
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        //int age = 10 / 0;
        return "------testHotKey";
    }

    public String dealTestHotKey(String p1, String p2, BlockException exception){
        //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
        return "------dealTestHotKey";
    }
}
