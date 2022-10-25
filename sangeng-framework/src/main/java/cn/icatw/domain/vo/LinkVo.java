package cn.icatw.domain.vo;

import lombok.Data;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class LinkVo {
    private Long id;


    private String name;

    private String logo;
    private String description;
    //网站地址
    private String address;
}
