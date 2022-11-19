package com.he.ssm.controller;


import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.UserProfilePwdReqDto;
import com.he.ssm.entity.other.Users;
import com.he.ssm.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @PostMapping("/sys/user/login")
    public ResultT<Users> login(@Validated @RequestBody Users req) {
        return this.sysUserService.login(req);
    }

    @GetMapping("/sys/user/logout")
    public ResultT<Void> logout() {
        return ResultT.success();
    }


    @ApiOperation(value = "用户信息", notes = "获取用户信息")
    @GetMapping("/sys/user/profile")
    public ResultT<Users> profile(Long userId) {
        return this.sysUserService.getUserInfo(userId);
    }

    @ApiOperation("更新用户个人信息")
    @PostMapping("/sys/user/profile/update")
    public ResultT<Void> profileUpdate(@RequestBody Users users) {
        return this.sysUserService.profileUpdate(users);
    }

    @ApiOperation("更新用户个人密码")
    @PostMapping("/sys/user/profile/password/update")
    public ResultT<Void> profilePasswordUpdate(@RequestBody UserProfilePwdReqDto reqDto) {
        return this.sysUserService.profilePasswordUpdate(reqDto);
    }

    @ApiOperation("更新用户个人头像")
    @PostMapping("/sys/user/profile/avatar/upload")
    public ResultT<FileInfo> avatarUpload(Long userId, @RequestParam("file") MultipartFile file) {
        return this.sysUserService.avatarUpload(userId, file);
    }

    //@PostMapping("/user/reg")
    //public ResultT reg(@Validated @RequestBody User req) {
    //   return this.userService.reg(req);
    //}
    //@GetMapping("/user/logout")
    //public ResultT logout() {
    //    // 退出登录，清除redis缓存
    //    return ResultT.success();
    //}
    //
    //@GetMapping("/user/find")
    //public ResultT find(String search, @RequestParam(value = "pageIndex",defaultValue ="1" )Integer pageIndex, @RequestParam(value = "pageSize",defaultValue ="20" ) Integer pageSize) {
    //    return this.userService.find(search,pageIndex,pageSize);
    //}
    //@PostMapping("/user/add")
    //public ResultT<Void> add(@RequestBody User user) {
    //    return this.userService.add(user);
    //}
    //@PostMapping("/user/update")
    //public ResultT<Void> update(@RequestBody User user) {
    //    return this.userService.update(user);
    //}
    //@GetMapping("/user/del")
    //public ResultT<Void> del(Long id,String type) {
    //    return this.userService.del(id,type);
    //}
    @GetMapping("/sys/user/menu")
    public ResultT menu(String type) {
        //Menu menu = new Menu("/user","user",new Menu.MetaDTO("用户管理"));


        String menu = "";
        //if ("admin".equals(type)) {
//            menu = "[" +
//                    "{\"path\":\"/user\",\"name\":\"user\",\"meta\":{\"title\":\"用户管理\"}}," +
//                    "{\"path\":\"/banner\",\"name\":\"banner\",\"meta\":{\"title\":\"轮播图管理\"}}," +
//                    "{\"path\":\"/cases\",\"name\":\"cases\",\"meta\":{\"title\":\"案例管理\"}}," +
//                    "{\"path\":\"/experience\",\"name\":\"experience\",\"meta\":{\"title\":\"经验借鉴管理\"}}," +
//                    "{\"path\":\"/news\",\"name\":\"news\",\"meta\":{\"title\":\"新闻管理\"}}," +
//                    "{\"path\":\"/notice\",\"name\":\"notice\",\"meta\":{\"title\":\"通知管理\"}}," +
//                    "{\"path\":\"/policy\",\"name\":\"policy\",\"meta\":{\"title\":\"政策措施管理\"}}," +
//                    "{\"path\":\"/practice\",\"name\":\"practice\",\"meta\":{\"title\":\"社会实践管理\"}}," +
//                    "{\"path\":\"/edu\",\"name\":\"edu\",\"meta\":{\"title\":\"教学管理\"},\"children\":[" +
//                        "{\"path\":\"/teacher\",\"name\":\"teacher\",\"meta\":{\"title\":\"教师管理\"}}," +
//                        "{\"path\":\"/courseware\",\"name\":\"courseware\",\"meta\":{\"title\":\"课件管理\"}}," +
//                        "{\"path\":\"/video\",\"name\":\"video\",\"meta\":{\"title\":\"教学视频管理\"}}" +
//                    "]}," +
//                    "{\"path\":\"/msg\",\"name\":\"msg\",\"meta\":{\"title\":\"留言管理\"},\"children\":[" +
//                        "{\"path\":\"/title\",\"name\":\"title\",\"meta\":{\"title\":\"标题管理\"}}," +
//                        "{\"path\":\"/content\",\"name\":\"content\",\"meta\":{\"title\":\"内容管理\"}}" +
//                    "]},"+
//                    "{\"path\":\"/show\",\"name\":\"show\",\"meta\":{\"title\":\"介绍管理\"},\"children\":[" +
//                        "{\"path\":\"/cinfo\",\"name\":\"cinfo\",\"meta\":{\"title\":\"课程介绍\"}}," +
//                        "{\"path\":\"/historyChange\",\"name\":\"historyChange\",\"meta\":{\"title\":\"历史沿革\"}}," +
//                        "{\"path\":\"/build\",\"name\":\"build\",\"meta\":{\"title\":\"建设规划\"}}," +
//                        "{\"path\":\"/tmethod\",\"name\":\"tmethod\",\"meta\":{\"title\":\"教学方法\"}}," +
//                        "{\"path\":\"/rshow\",\"name\":\"rshow\",\"meta\":{\"title\":\"成果展示\"}}" +
//                    "]}" +
//                    "]";
        menu = "[\n" +
                "    {\n" +
                "        \"path\": \"/show2\",\n" +
                "        \"name\": \"show2\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"课程信息\"\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"path\": \"/cinfo\",\n" +
                "                \"name\": \"cinfo\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"课程介绍\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/historyChange\",\n" +
                "                \"name\": \"historyChange\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"历史沿革\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/build\",\n" +
                "                \"name\": \"build\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"建设规划\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/tmethod\",\n" +
                "                \"name\": \"tmethod\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"教学方法\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/edu\",\n" +
                "        \"name\": \"edu\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"教学团队\"\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"path\": \"/teacher\",\n" +
                "                \"name\": \"teacher\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"课程负责人\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/edu2\",\n" +
                "        \"name\": \"edu2\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"教学资源\"\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"path\": \"/courseware\",\n" +
                "                \"name\": \"courseware\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"教学课件\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/video\",\n" +
                "                \"name\": \"video\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"教学视频\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"\",\n" +
                "        \"name\": \"\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"扩展交流\"\n" +
                "        },\n" +
                "        \"children\" : [\n" +
                "            {\n" +
                "                \"path\": \"/news\",\n" +
                "                \"name\": \"news\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"媒体报道\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/cases\",\n" +
                "                \"name\": \"cases\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"案例解读\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\" : \"/show\",\n" +
                "        \"name\" : \"show\",\n" +
                "        \"meta\" : {\n" +
                "            \"title\" : \"教学成果\"\n" +
                "        },\n" +
                "        \"children\" : [\n" +
                "            {\n" +
                "                \"path\": \"/rshow\",\n" +
                "                \"name\": \"rshow\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"成果展示\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/msg\",\n" +
                "        \"name\": \"msg\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"互动交流\"\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"path\": \"/notice\",\n" +
                "                \"name\": \"notice\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"课程通知\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/title\",\n" +
                "                \"name\": \"title\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"互动管理\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"path\": \"/content\",\n" +
                "                \"name\": \"content\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"平台留言\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/practice\",\n" +
                "        \"name\": \"practice\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"实践教学\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/experience\",\n" +
                "        \"name\": \"experience\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"经验借鉴管理\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/banner\",\n" +
                "        \"name\": \"banner\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"轮播图管理\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/user\",\n" +
                "        \"name\": \"user\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"用户管理\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"path\": \"/policy\",\n" +
                "        \"name\": \"policy\",\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"政策措施管理\"\n" +
                "        }\n" +
                "    }\n" +
                "]";
        return ResultT.successWithData(menu);
    }


}
