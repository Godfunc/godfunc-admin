package com.godfunc.modules.security.service.impl;

import com.godfunc.modules.security.service.CaptchaService;
import com.google.code.kaptcha.Producer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * @author Godfunc
 * @date 2019/12/1 21:59
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final Producer producer;

    public CaptchaServiceImpl(Producer producer) {
        this.producer = producer;
    }

    /**
     * 有需要可以替换成redis
     */
    Cache<String, String> captchaCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();

    @Override
    public BufferedImage create(String uuid) {
        String code = producer.createText();
        String mathStr = code.substring(0, code.lastIndexOf("=") + 1);
        //获取运算式结果
        String result = code.substring(code.lastIndexOf("=") + 1);
        captchaCache.put(uuid, result);
        return producer.createImage(mathStr + "?");
    }

    @Override
    public boolean validate(String uuid, String code) {
        String text = captchaCache.getIfPresent(uuid);
        if (StringUtils.isNotBlank(text)) {
            return code.equalsIgnoreCase(text);
        }
        return false;
    }
}
