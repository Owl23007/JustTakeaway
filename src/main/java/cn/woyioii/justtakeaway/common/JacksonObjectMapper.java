package cn.woyioii.justtakeaway.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;

/**
 * 自定义Jackson的ObjectMapper，统一全局JSON序列化和反序列化配置
 */
public class JacksonObjectMapper extends ObjectMapper {
    public JacksonObjectMapper() {
        super();
        // 忽略空Bean转json的错误
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 只序列化非空字段
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 支持Java8时间类型
        this.registerModule(new JavaTimeModule());
        // 统一日期格式
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 统一驼峰转下划线
         this.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}

