package com.acut.busi;


import com.acut.entity.Procedure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Worker
 * @author: zhangdx
 * @description: TODO
 * @date: 2022/9/19 15:07
 */
@Slf4j
@Component
public class Worker {

    @Autowired
    private Register register;

    private static final Map<String, Object> regMap = new HashMap<>();

    public void work() {
        while (register.funcs != null) {
            log.debug("begin work");
            register.funcs.forEach(this::execute);
        }
    }

    private static synchronized boolean register(String worker) {
        Object thread = regMap.get(worker);
        if (thread == null) {
            regMap.put(worker, "working");
            return true;
        }
        return false;
    }

    private static synchronized void unRegister(String worker) {
        regMap.put(worker, null);
    }

    private void execute(Map<String, Procedure> deal) {
        for (Map.Entry<String, Procedure> entry : deal.entrySet()) {
            String key = entry.getKey();
            Procedure procedure = entry.getValue();
            if (!register(key)) {
                return;
            }
            try {
                procedure.exc();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                unRegister(key);
            }
        }
    }

}
