package com.godfunc.modules.security.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 生成验证码配置
 *
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Configuration
public class KaptchaConfig {

    public final static String KAPTCHA_CODEMATH_MAXNUMBER = "kaptcha.code.math.maxNumber";

    public final static String KAPTCHA_CODEMATH_OPERATORS = "+-*";

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER, "no");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, WaterRipple.class.getName());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL, MathExpressionTextCreator.class.getName());
        properties.setProperty(KAPTCHA_CODEMATH_MAXNUMBER, "10");
        properties.setProperty(KAPTCHA_CODEMATH_OPERATORS, "+-*");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
