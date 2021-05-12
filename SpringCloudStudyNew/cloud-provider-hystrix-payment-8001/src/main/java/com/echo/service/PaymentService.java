package com.echo.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问肯定没问题的方法
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " PaymentInfo_OK,id: " + id + "\t" + "哈哈";
    }

    /**
     * 会超时的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",
            commandProperties = {
            //设置自身调用超时时间的峰值，峰值内可以正常运行，超过了需要有兜底的方法处理，作服务降级fallback
            //设置这个线程的超时时间是3秒钟
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })   //如果该方法出现异常/超时，就会调用fallback中指定的方法
    public String paymentInfoTimeout(Integer id){
        /**
         * 约定 3秒钟，正常。
         * 超过3秒钟，异常
         * 一旦调用服务方法失败并抛出了错误信息之后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中指定的方法
         */
        int timeNumber = 2;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //直接抛出异常
        //int x = 1 / 0;
        return "线程池: " + Thread.currentThread().getName() + " PaymentInfo_Timeout,id: " + id
                + "\t" + "哈哈 耗时:"  + timeNumber + "秒钟";

    }


    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeoutHandler,id: " + id
                + "\t" + "系统繁忙或运行出错，请稍后再试";
    }


    //===========服务熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    //是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    //请求次数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
                    //时间窗口期
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    //失败率达到多少后，跳闸
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
                    //总结一下就是在10秒的时间窗口期，以10秒中为一个时间单位，如果在10请求中，有60%失败，则跳闸
            }
    )
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("****ID 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id")Integer id){
        return "ID 不能为负数，请稍后再试，/(T o T)/~~   id:" + id;
    }

    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            groupKey = "strGroupCommand",
            commandKey = "strCommand",
            threadPoolKey = "strThreadPool",
            commandProperties = {
                    //设置隔离策略，THREAD标识线程池SEMAPHORE：信号池隔离
                    @HystrixProperty(name = "execution.isolation.strategy",value="THREAD"),
                    //当隔离策略选择信号池隔离的时候，用来设置信号池的大小（最大并发数）
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "10"),
                    //配置命令执行的超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
                    //是否启用超时时间
                    @HystrixProperty(name = "execution.timeout.enable",value = "true"),
                    //执行超时的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout",value = "true"),
                    //执行被取消的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel",value = "true"),
                    //允许回调方法执行的最大并发数
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "10"),
                    //服务降级是否启用，是否执行回调函数
                    @HystrixProperty(name = "fallback.enabled",value = "true"),
                    //是否启用断路器
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    //该属性用来设置在滚动时间窗中，断路器熔断的最小请求数。例如，默认值为20时，如果滚动时间窗（默认10s)
                    //内仅收到了19个请求，即使这19个请求都失败了，断路器也不会打开
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "20"),
                    //该属性用来设置在滚动时间窗中，请求数量超过circuitBreaker.requestVolumeThreshold的情况下，如果错误请求
                    //的百分比超过了50，就把断路器设置为打开的状态，否则就设置为关闭的状态
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    //该属性用来设置断路器打开后的休眠时间窗，休眠时间窗结束之后，会将断路器设置为“半开”的状态，尝试熔断的请求命令,
                    //如果依然失败就将断路器继续设置为“打开”的状态，如果成功就设置为“关闭”的状态
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
                    //断路器强制打开
                    @HystrixProperty(name = "circuitBreaker.forceOpen",value = "false"),
                    //断路器强制关闭
                    @HystrixProperty(name = "circuitBreaker.forceClosed",value = "false"),
                    //滚动时间窗设置，该时间用于断路器判断健康度时，需要收集信息的持续时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000"),
                    //该属性用来设置滚动时间窗统计指标信息时，划分“桶”的数量，断路器在收集指标信息的时候，会根据设置的时间窗长度
                    //拆分成多个“桶”来累计各个度量值，每个“桶”记录了一段时间内的采集指标。
                    //比如10秒内拆分成10个“桶”收集，所以timeInMilliseconds必须能被numBuckets整除，否则会出现异常
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "10"),
                    //该属性用来设置对命令执行的延迟是否使用百分位数来跟踪和计算，如果设置为false，那么所有的概要统计都要返回-1
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled",value = "false"),
                    //该属性用来设置百分位统计的滚动窗口的持续时间，单位为毫秒
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds",value = "600000"),
                    //该属性用来设置百分位统计的滚动窗口中使用的“桶”的数量
                    @HystrixProperty(name = "metrics.rollingPercentile.numBuckets",value = "600000"),
                    //该属性用来设置在执行过程中每个“桶”中保留的最大执行次数，如果在滚动时间窗内发生超过该设定值的执行次数，
                    //就从最初的位置开始重写。例如，将该值设置为100，滚动窗口为10秒，若在10秒内一个“桶”中发生了500次执行，
                    //那么该“桶”中只保留最后的100次执行的统计，另外，增加该值的大小将会增加内存量的消耗，并增加排序百分位数所需要的计算时间。
                    @HystrixProperty(name = "metrics.rollingPercentile.bucketSize",value = "100"),
                    //该属性用来设置采集影响断路器状态的健康快照（请求的成功，错误百分比）的间隔等待时间
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds",value = "500"),
                    //是否开启请求缓存
                    @HystrixProperty(name = "requestCache.enabled",value = "true"),
                    //HystrixCommand的执行和时间是否打印日志到HystrixRequestLog中
                    @HystrixProperty(name = "requestLog.enabled",value = "true"),
            },
            threadPoolProperties = {
                    //该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量
                    @HystrixProperty(name = "coreSize",value = "10"),
                    //该参数用来设置线程池的最大队列大小。当设置为-1时，线程池将使用SynchronousQueue实现的队列，否则将使用LinkedBlockingQueue实现的队列
                    @HystrixProperty(name = "maxQueueSize",value = "-1"),
                    //该参数用来为队列设置拒绝阈值，通过该参数，即使队列没有达到最大值也能拒绝请求，该参数主要是对LinkedBlockingQueue队列的补充，
                    //因为LinkedBlockingQueue队列不能动态修改它的对象的大小，而通过该属性就可以调整拒绝请求的队列的大小了
                    @HystrixProperty(name = "queueSizeRejectionThreshold",value = "5"),
            }
    )
    public String test(){
        return null;
    }
}
