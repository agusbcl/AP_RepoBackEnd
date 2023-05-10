package com.portfolio.ab.Security.Service;

import com.portfolio.ab.Security.Entity.User;
import com.portfolio.ab.Security.Repository.IUserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    IUserRepository iuserRepository;

    public Optional<User> getByUserName(String userName) {
        return iuserRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName) {
        return iuserRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email) {
        return iuserRepository.existsByEmail(email);
    }
    
    public void save(User user){
        iuserRepository.save(user);
    }
}
