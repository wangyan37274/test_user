package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @Auther: wangyan
 * @Date: 2019/1/3
 * @Description: service
 * @version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userJpaRepository;

    @Transactional
    public void updateUser(String name , Integer id){
        userJpaRepository.updateUserById(name,id);
    }
}
