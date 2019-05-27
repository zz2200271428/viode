package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.MoveUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppUpdateEmailDao {

    //email更改记录增加
    int addEmaillogin(@Param("moveUserBean") MoveUserBean moveUserBean);

    //修改邮箱
    int updateDateEmail(@Param("moveUserBean") MoveUserBean moveUserBean);

    //修改手机号
    int updateDatePhone(@Param("moveUserBean") MoveUserBean moveUserBean);



    //Phone更改记录增加
    int addPhonelogin(@Param("moveUserBean") MoveUserBean moveUserBean);
    //修改手密码
    int update_Date_Password(@Param("moveUserBean")MoveUserBean moveUserBean);

}
