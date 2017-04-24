package com.lzx2005.service.impl;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.dto.Token;
import com.lzx2005.entity.User;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.repository.UserRepository;
import com.lzx2005.service.UserService;
import com.lzx2005.tools.SecretTools;
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
    private UserRepository userRepository;

    @Override
    public ServiceResult login(String username, String password) {
        if(StringTools.hasEmpty(username,password)){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult();
        }
        User user = userRepository.findFirstByUsername(username);
        if(user==null) {
            return ServiceResultEnum.CANT_FIND_USER.toServiceResult();
        }else {
            String secrectInput = SecretTools.secrect(password, user.getUserId());
            if(secrectInput.equals(user.getPassword())){
                //密码正确
                user.setPassword("");
                String token = TokenTools.createToken(user.getUsername() + "," + user.getUserId());
                return ServiceResultEnum.SUCCESS.toServiceResult().setData(token);
            }else{
                return ServiceResultEnum.WRONG_PASSWORD.toServiceResult();
            }
        }
    }

    @Override
    public ServiceResult consoleLogin(String username, String password) {
        if(StringTools.hasEmpty(username,password)){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult();
        }
        User user = userRepository.findFirstByUsername(username);
        if(user==null) {
            return ServiceResultEnum.CANT_FIND_USER.toServiceResult();
        }else {
            String secrectInput = SecretTools.secrect(password, user.getUserId());
            if(secrectInput.equals(user.getPassword())){
                //密码正确
                user.setPassword("");
                return ServiceResultEnum.SUCCESS.toServiceResult().setData(user);
            }else{
                return ServiceResultEnum.WRONG_PASSWORD.toServiceResult();
            }
        }
    }

    @Override
    public ServiceResult resetPassword(int userId, String newPassword) {
        User one = userRepository.findOne(userId);
        if(one==null){
            return ServiceResultEnum.CANT_FIND_USER.toServiceResult();
        }
        String secrectPassword = SecretTools.secrect(newPassword, userId);
        one.setPassword(secrectPassword);
        User save = userRepository.save(one);
        if(save!=null){
            save.setPassword("");
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(save);
        }
        return ServiceResultEnum.FAIL.toServiceResult();
    }

    @Override
    public ServiceResult getUserInfo(String token) {
        Token token1 = TokenTools.parseToken(token);
        String userId = token1.getUserId();
        User one = userRepository.findOne(Integer.valueOf(userId));
        if(one==null){
            return ServiceResultEnum.CANT_FIND_USER.toServiceResult();
        }else{
            one.setPassword("");
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(one);
        }
    }
}
