package com.he.ssm.bean;

import com.he.ssm.entity.other.Courseware;
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
public class CoursewareReqBean extends Courseware {
    private List<Long>     delIdList;
    private List<FileInfo> uploadFileList;
}
