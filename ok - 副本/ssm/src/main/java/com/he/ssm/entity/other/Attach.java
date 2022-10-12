package com.he.ssm.entity.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itaem
 * date:2021-03-10 2021/3/10:22:03
 */

/**
 * 附件
 */
@ApiModel(value = "com-he-ssm-entity-other-Attach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 附件类型(视频:video,课件:courseware,社会实践:practice)
     */
    @ApiModelProperty(value = "附件类型(视频:video,课件:courseware,社会实践:img)")
    private String attachType;

    /**
     * 数据Id
     */
    @ApiModelProperty(value = "数据Id")
    private Long dataId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileName;

    /**
     * 附件Url
     */
    @ApiModelProperty(value = "附件Url")
    private String relativePath;

    /**
     * 附件大小
     */
    @ApiModelProperty(value = "附件大小")
    private Integer fileSize;

    /**
     * 附件扩展名
     */
    @ApiModelProperty(value = "附件扩展名")
    private String extName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String state;

    private Long downloadCount;
}