package com.thinging.project.service.sys;

import com.thinging.project.config.ThingIngSystemUserConfig;
import com.thinging.project.errors.UserExistsException;
import com.thinging.project.errors.UserNotExistsException;
import com.thinging.project.response.Role;
import com.thinging.project.response.UserAccountDto;
import com.thinging.project.dto.UserAccountResDto;
import com.thinging.project.entity.UserAccount;
import com.thinging.project.repository.UserAccountRepository;
import com.thinging.project.utils.parser.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAccountService {

    private UserAccountRepository userRepository;
    private DataParser dataParser;
    private ThingIngSystemUserConfig thingIngSystemUserConfig;

    public UserAccountService(UserAccountRepository userRepository, DataParser dataParser, ThingIngSystemUserConfig thingIngSystemUserConfig) {
        this.userRepository = userRepository;
        this.dataParser = dataParser;
        this.thingIngSystemUserConfig = thingIngSystemUserConfig;
    }

    public UserAccountDto createUser(UserAccountDto userReq) {

        if(userRepository.existsByEmail(userReq.getEmail()) || thingIngSystemUserConfig.getSystemUserEmail().equals(userReq.getEmail()))
            throw new UserExistsException(String.format("User with userName - %s exists",userReq.getEmail()));

        if (userReq.getRole() == Role.SYSTEM) throw new UserNotExistsException(String.format("Only one System role can avalible!"));

        UserAccount newUser = dataParser.dtoToUserAccount(userReq,null);

        return  dataParser.userAccountToDto(userRepository.save(newUser));
    }

    public UserAccountDto updateUser(UserAccountDto userReq, Long id) {
        Optional<UserAccount> byId = userRepository.findById(id);
        if (byId.get() == null)
            throw new UserNotExistsException(String.format("User with userName - %s not exists",userReq.getEmail()));

        if (userReq.getRole() == Role.SYSTEM) throw new UserNotExistsException(String.format("Only one System role can avalible!"));

        UserAccount updated = dataParser.dtoToUserAccount(userReq,byId.get());

        return  dataParser.userAccountToDto(userRepository.save(updated));
    }

    public void deleteUser(Long id) {
        Optional<UserAccount> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.delete(byId.get());
        }
    }

    public UserAccountResDto getUserById(Long id) {
        Optional<UserAccount> byId = userRepository.findById(id);
        if (!byId.isPresent())
            throw new UserNotExistsException(String.format("User with id - %d not exists",id));
        return new UserAccountResDto(byId.get());
    }

    public UserAccountDto getUserByEmail(String email) {
        if(email.equals(thingIngSystemUserConfig.getSystemUserEmail())){
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setEmail(thingIngSystemUserConfig.getSystemUserEmail());
            userAccountDto.setPassword(thingIngSystemUserConfig.getSystemUserPassword());
            userAccountDto.setRole(Role.SYSTEM);
            return userAccountDto;
        }
        Optional<UserAccount> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isPresent())
            throw new UserNotExistsException(String.format("User with userName - %s not exists",email));

        return dataParser.userAccountToDto(byEmail.get());
    }

    public List<UserAccountResDto> getUserList() {
        List<UserAccount> userList = userRepository.findAll();
        ArrayList<UserAccountResDto> list = userList.stream()
                .map(UserAccountResDto::new)
                .collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

}
