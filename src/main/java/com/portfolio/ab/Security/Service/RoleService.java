package com.portfolio.ab.Security.Service;

import com.portfolio.ab.Security.Entity.Role;
import com.portfolio.ab.Security.Enums.RoleName;
import com.portfolio.ab.Security.Repository.IRoleRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository iroleRepository;
    
    public Optional<Role> getByRoleName(RoleName roleName){
        return iroleRepository.findByRoleName(roleName);
    }
    
    public void save(Role role){
        iroleRepository.save(role);
    }
}
