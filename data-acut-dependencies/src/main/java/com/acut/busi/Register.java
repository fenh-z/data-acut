package com.acut.busi;


import com.acut.entity.Procedure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: Register
 * @author: zhangdx
 * @description: 注册器
 * @date: 2022/9/19 14:19
 */
@Slf4j
@Component
public class Register {

    public List<Map<String, Procedure>> funcs = new ArrayList<>();

    /**
     * @description: 注册
     * @author  zhangdx
     * @date    2022/10/31 10:39
     * @param
     * @return  void
    */
    @PostConstruct
    public void regist(){
        log.debug(" begin regist ");

        //注册功能
        Map<String, Procedure> demoMap = new HashMap<>();
//        demoMap.put("demo", demo::deal);

        this.funcs.add(demoMap);
    }
}
