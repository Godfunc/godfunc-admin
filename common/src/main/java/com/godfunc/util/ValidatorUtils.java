package com.godfunc.util;


import com.godfunc.constant.CommonConstant;
import com.godfunc.exception.GException;
import com.godfunc.result.ApiCode;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author godfunc
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     */
    public static void validate(Object object)
            throws GException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(CommonConstant.VALIDATE_SPLIT);
            }
            throw new GException(msg.toString(), ApiCode.PARAM_ERROR);
        }
    }
}
