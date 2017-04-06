package com.lzx2005.service.impl;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.repository.UserRepository;
import com.lzx2005.service.UserService;
import com.lzx2005.tools.StringTools;
import com.lzx2005.tools.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ServiceResult login(String username, String password) {
        if(StringTools.hasEmpty(username,password)){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult();
        }
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user!=null){
            user.setPassword("");
            String token = TokenTools.createToken(user.getUsername() + "," + user.getUserId());
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(token);
        }
        return ServiceResultEnum.WRONG_USERNAME_OR_PASSWORD.toServiceResult();
    }
}
