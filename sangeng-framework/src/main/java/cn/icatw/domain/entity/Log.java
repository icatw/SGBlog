package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志(Log)实体类
 *
 * @author icatw
 * @since 2022-10-27 10:51:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Log")
@TableName("sys_log")
public class Log implements Serializable {
    private static final long serialVersionUID = -56646343480001713L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请求接口路径
     */
    @TableField(value = "url")
    @ApiModelProperty("请求接口路径")
    private String url;

    /**
     * 接口名
     */
    @TableField(value = "business_name")
    @ApiModelProperty("接口名")
    private String businessName;

    /**
     * 请求类型
     */
    @TableField(value = "http_method")
    @ApiModelProperty("请求类型")
    private String httpMethod;

    /**
     * 类名
     */
    @TableField(value = "class_method")
    @ApiModelProperty("全路径类名")
    private String classMethod;

    /**
     * 请求ip
     */
    @TableField(value = "ip")
    @ApiModelProperty("请求ip")
    private String ip;

    /**
     * 请求参数（json）
     */
    @TableField(value = "request_args")
    @ApiModelProperty("请求参数（json）")
    private String requestArgs;

    /**
     * 响应（json）
     */
    @TableField(value = "response")
    @ApiModelProperty("响应（json）")
    private String response;
    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("请求时间")
    private Date createTime;
}

