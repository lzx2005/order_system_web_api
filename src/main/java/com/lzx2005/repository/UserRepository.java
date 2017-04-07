package com.lzx2005.repository;

import com.lzx2005.entity.User;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Lizhengxian on 2017/2/22.
 */
public interface UserRepository extends CrudRepository<User,Integer>{
    User findFirstByUsername(String username);

    User findFirstByUsernameAndPassword(String username, String password);
}
