package com.godfunc.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.godfunc.modules.security.util.SecurityUser;
import com.godfunc.modules.sys.model.UserDetail;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatisplus自动填充
 * @author godfunc
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        UserDetail user = SecurityUser.getUser();
        if (user.getId() == null) {
            user.setId(0L);
        }
        this.strictInsertFill(metaObject, "createId", Long.class, user.getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        UserDetail user = SecurityUser.getUser();
        if (user.getId() == null) {
            user.setId(0L);
        }
        this.strictUpdateFill(metaObject, "updateId", Long.class, user.getId());
    }
}