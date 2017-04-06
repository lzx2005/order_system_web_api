package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
public interface UserService {
    ServiceResult login(String username,String password);
}
