package com.godfunc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.godfunc.modules.log.entity.LogOperation;
import com.godfunc.modules.log.service.LogOperationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManageApplicationTests {

    @Autowired
    private  LogOperationService logOperationService;
    @Test
    void contextLoads() {
        Page<LogOperation> page = logOperationService.page(new Page<>(1, 10));
        System.out.println(page);
    }

}
