package com.he.ssm.bean.api;

import com.he.ssm.entity.other.Banner;
import com.he.ssm.entity.other.News;
import com.he.ssm.entity.other.Notice;
import com.he.ssm.entity.other.Policy;
import com.he.ssm.entity.other.Teacher;
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
public class IndexResDto {
    @ApiModelProperty(value = "轮播图集合")
    private List<Banner> bannerList;
    @ApiModelProperty(value = "通知集合")
    private List<Notice> noticeList;
    @ApiModelProperty(value = "新闻集合")
    private List<News> newsList;
    @ApiModelProperty(value = "政策集合")
    private List<Policy>  policyList;
    @ApiModelProperty(value = "荣誉教师集合")
    private List<Teacher> teacherList;


}
