package com.godfunc.util;

import com.godfunc.model.TreeModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class TreeUtils {

    public static <T extends TreeModel<T>> List<T> build(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            List<T> result = new ArrayList<>();
            Map<Long, T> menuMap = new LinkedHashMap<>(list.size());
            list.forEach(x -> menuMap.put(x.getId(), x));
            for (T item : menuMap.values()) {
                T parent = menuMap.get(item.getPid());
                // is children
                if (parent != null && !item.getId().equals(parent.getId())) {
                    parent.getChildren().add(item);
                    continue;
                }
                // is parent
                result.add(item);
            }
            return result;
        }
    }
}
