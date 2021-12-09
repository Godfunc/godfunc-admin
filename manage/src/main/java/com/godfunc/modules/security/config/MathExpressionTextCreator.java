package com.godfunc.modules.security.config;

import com.godfunc.exception.GException;
import com.godfunc.result.ApiCodeMsg;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数学验证码文本生成
 */
@Slf4j
public class MathExpressionTextCreator extends DefaultTextCreator {

    private final int defaultMaxNumber = 10;
    private final String defaultOperators = "+-*";

    @Override
    public String getText() {
        String maxNumberStr = this.getConfig().getProperties().getProperty(KaptchaConfig.KAPTCHA_CODEMATH_MAXNUMBER);
        String operators = this.getConfig().getProperties().getProperty(KaptchaConfig.KAPTCHA_CODEMATH_OPERATORS);
        int number1 = generateNumber(maxNumberStr);
        int number2 = generateNumber(maxNumberStr);
        char operator = randomOperator(operators);
        StringBuilder sb = new StringBuilder();
        sb.append(number1).append(operator).append(number2).append("=").append(calcResult(number1, operator, number2));
        return sb.toString();
    }

    /**
     * 计算两个数字的结果
     *
     * @param number1
     * @param operator
     * @param number2
     * @return
     */
    public int calcResult(int number1, char operator, int number2) {
        switch (operator) {
            case '-':
                return number1 - number2;
            case '+':
                return number1 + number2;
            case '*':
                return number1 * number2;
            default:
                log.error("验证码不支持的操作符 {} {} {}", number1, operator, number2);
                throw new GException(ApiCodeMsg.FAILED);
        }


    }

    /**
     * 随机获取一个数学操作符
     *
     * @param operators
     * @return
     */
    public char randomOperator(String operators) {
        if (StringUtils.isBlank(operators)) {
            operators = defaultOperators;
        }
        char[] operatorArr = operators.toCharArray();
        return operatorArr[ThreadLocalRandom.current().nextInt(operatorArr.length)];
    }

    /**
     * 生成运算数字
     *
     * @param maxNumberStr
     * @return
     */
    public int generateNumber(String maxNumberStr) {
        int maxNumber = defaultMaxNumber;
        if (StringUtils.isNotBlank(maxNumberStr)) {
            try {
                maxNumber = Integer.parseInt(maxNumberStr);
                if (maxNumber < 0) {
                    maxNumber = defaultMaxNumber;
                }
            } catch (NumberFormatException e) {
                log.error("验证码设置项值有无，请使用int类型", e);
            }
        }
        return ThreadLocalRandom.current().nextInt(maxNumber + 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(2));
        }
    }

}