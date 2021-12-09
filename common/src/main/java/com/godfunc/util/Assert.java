package com.godfunc.util;

import com.godfunc.exception.GException;
import com.godfunc.result.ApiCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class Assert {

    public static void isBlank(String str, String message) {
        isBlank(str, message, new Object[]{});
    }

    public static void isBlank(String str, String message, Object...args) {
        if (StringUtils.isBlank(str)) {
            throw new GException(MessageFormatUtils.format(message, args), ApiCode.NOT_BE_BLANK);
        }
    }

    public static void isNull(Object object, String message, Object...args) {
        if (object == null) {
            throw new GException(MessageFormatUtils.format(message, args), ApiCode.DATA_NOT_EXIST);
        }
    }

    public static void isNull(Object object, String message) {
        isNull(object, message, new Object[]{});
    }

    public static void isNotNull(Object object, String message) {
        isNotNull(object, message, new Object[]{});
    }

    public static void isNotNull(Object object, String message, Object...args) {
        if (object != null) {
            throw new GException(MessageFormatUtils.format(message, args), ApiCode.DATA_EXIST);
        }
    }

    public static void isTrue(boolean b, String message) {
        isTrue(b, message, new Object[]{});
    }

    public static void isTrue(boolean b, String message, Object... args) {
        if (b) {
            throw new GException(MessageFormatUtils.format(message, args), ApiCode.DATA_ERROR);
        }
    }
}
