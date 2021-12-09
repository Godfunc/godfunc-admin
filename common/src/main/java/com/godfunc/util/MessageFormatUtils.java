package com.godfunc.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class MessageFormatUtils {

    public static String format(String message, Object[] args) {
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }
}
