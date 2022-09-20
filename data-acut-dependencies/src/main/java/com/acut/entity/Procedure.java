package com.acut.entity;

/**
 * @Author zhangdx
 * @CreateDate 2020/6/4 12:05 下午
 * @Describe 函数式接口，() -> void
 */
@FunctionalInterface
public interface Procedure {

    void exc() throws InterruptedException;

    default Procedure andThen(Procedure after) {
        return () -> {
            this.exc();
            after.exc();
        };
    }

    default Procedure compose(Procedure before) {
        return () -> {
            before.exc();
            this.exc();
        };
    }
}
