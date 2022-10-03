package com.he.ssm.bean.api;

import com.he.ssm.bean.FileInfo;
import com.he.ssm.entity.other.Courseware;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author itaem
 * date:2021-02-27 2021/2/27:14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursewareResDto extends Courseware {
    @ApiModelProperty(value = "文件集合")
    private List<FileInfo> fileInfoList;
}
