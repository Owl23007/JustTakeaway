package cn.woyioii.justtakeaway.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作，自动填充
     * 
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        // 获取当前用户ID，如果为空则使用默认值1（管理员）
        Long currentUserId = BaseContext.getCurrentId();
        if (currentUserId == null) {
            currentUserId = 1L; // 默认管理员ID
            log.warn("当前用户ID为空，使用默认管理员ID: 1");
        }

        metaObject.setValue("createUser", currentUserId);
        metaObject.setValue("updateUser", currentUserId);
    }

    /**
     * 更新操作，自动填充
     * 
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        long id = Thread.currentThread().threadId();
        log.info("线程id为：{}", id);

        metaObject.setValue("updateTime", LocalDateTime.now());

        // 获取当前用户ID，如果为空则使用默认值1（管理员）
        Long currentUserId = BaseContext.getCurrentId();
        if (currentUserId == null) {
            currentUserId = 1L; // 默认管理员ID
            log.warn("当前用户ID为空，使用默认管理员ID: 1");
        }

        metaObject.setValue("updateUser", currentUserId);
    }
}
