package com.godfunc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 * @date 2020/7/31
 */
@Slf4j
public class ConvertUtils {

    public static <T> T source2Target(Object source, Class<T> target) {
        if(source == null) {
            return null;
        }
        T targetObj = null;
        try {
            targetObj = target.newInstance();
            BeanUtils.copyProperties(source, targetObj);
        } catch (Exception e) {
            log.error("ConvertUtils error ", e);
        }
        return targetObj;
    }

    public static <T> List<T> source2Target(Collection<?> sourceCollection, Class<T> target) {
        if(CollectionUtils.isEmpty(sourceCollection)) {
            return Collections.emptyList();
        }
        List<T> targetList = new ArrayList<>(sourceCollection.size());
        try {
            for (Object sourceObj : sourceCollection) {
                T targetObj = source2Target(sourceObj, target);
                targetList.add(targetObj);
            }
        } catch (Exception e) {
            log.error("ConvertUtils error ", e);
        }
        return targetList;
    }
}
