package org.lucanscr.cachedemo.service;

import org.lucanscr.cachedemo.model.User;
import org.lucanscr.cachedemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Cacheable(value= "users", key="#id")
    public User getById(Long id){
        log.info("getting user with id {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else{
            return null;
        }
    }

    public User create(User user){
        return userRepository.save(user);
    }

    @CachePut(value="users", key = "T(java.lang.String).format('%s-%s-%s,  #user.id)")
    public User update(User user){
        Long id = user.getId();
        User userInDb = getById(id);
        if (!ObjectUtils.isEmpty(userInDb)){
            return create(user);
        }else{
            return null;
        }
    }

    @CacheEvict(value= "users", allEntries = false, key="#id")
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
