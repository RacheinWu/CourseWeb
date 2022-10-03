package com.he.ssm.service;

import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.UserProfilePwdReqDto;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.UsersDao;
import com.he.ssm.entity.other.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 用户Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class SysUserService {
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private UsersDao     usersDao;
    @Resource
    private UploadService uploadService;

    /**
     * 登录
     */
    public ResultT<Users> login(Users req) {
        Users user = this.usersDao.getByAccountAndPwdAndUserType(req.getAccount(), req.getPwd(), req.getUserType());
        if (Objects.isNull(user)) {
            return ResultT.errorWithCodeAndMsg(1001, "账户密码错误");
        }
        if(StringUtils.isNotBlank(user.getAvatar())){
            user.setAvatar(uploadConfig.getHttpPrefix()+user.getAvatar());
        }
        return ResultT.successWithData(user);
    }

    /**
     * 获取用户全部信息
     */
    public ResultT<Users> getUserInfo(Long userId) {
        Users users = this.usersDao.selectByPrimaryKey(userId);
        if (StringUtils.isBlank(users.getAvatar())) {
            users.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }
        return ResultT.successWithData(users);
    }


    public ResultT<Void> profileUpdate(Users users) {
        int count = this.usersDao.updateByPrimaryKey(users);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    public ResultT<Void> profilePasswordUpdate(UserProfilePwdReqDto reqDto) {
        Long id = reqDto.getId();
        Users existData = this.usersDao.selectByPrimaryKey(id);
        if (!existData.getPwd().equals(reqDto.getOldPassword())) {
            return ResultT.errorWithMsg("原始密码错误");
        }
        existData.setPwd(reqDto.getNewPassword());
        int count = this.usersDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    public ResultT<FileInfo> avatarUpload(Long userId, MultipartFile file) {
        ResultT<FileInfo> uploadResult = this.uploadService.upload(file);
        if (ResultT.SUCCESS != uploadResult.getCode()) {
            return ResultT.errorWithMsg(uploadResult.getMsg());
        }
        Users users = this.usersDao.selectByPrimaryKey(userId);
        users.setAvatar(uploadResult.getData().getRelativePath());
        int count = this.usersDao.updateByPrimaryKey(users);
        if (count > 0) {
            return ResultT.successWithData(uploadResult.getData());
        }
        return ResultT.error();
    }
}
