package com.godfunc.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class TokenGenerateUtils {

    public static String getToken() {
        return DigestUtils.md5Hex(IdWorker.getIdStr());
    }
}
