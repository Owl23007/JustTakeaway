package cn.woyioii.justtakeaway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import java.io.Serializable;

/**
 * 用户信息实体
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 姓名
    private String name;

    // 手机号
    private String phone;

    // 性别 0 女 1 男
    private String sex;

    // 身份证号
    private String idNumber;

    // 头像
    private String avatar;

    // 状态 0:禁用，1:正常
    private Integer status;
}
