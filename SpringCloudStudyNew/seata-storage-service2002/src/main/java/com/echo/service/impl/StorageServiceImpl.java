package com.echo.service.impl;

import com.echo.dao.StorageDao;
import com.echo.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("--------storage-service中扣减库存开始--------");
        storageDao.decrease(productId,count);
        LOGGER.info("--------storage-service中扣减库存结束--------");
    }
}
