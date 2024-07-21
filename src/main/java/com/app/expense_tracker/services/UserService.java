package com.app.expense_tracker.services;

import com.app.expense_tracker.db_mappers.UserMapper;
import com.app.expense_tracker.dto.PasswordUpdateDto;
import com.app.expense_tracker.dto.PhoneUpdateDto;
import com.app.expense_tracker.exception_manager.TrackerExceptionFactory;
import com.app.expense_tracker.exception_manager.TrackerExceptionHandler;
import com.app.expense_tracker.models.User;
import com.app.expense_tracker.settings.ServiceManager;
import com.app.expense_tracker.utils.LoggerUtil;
import com.app.expense_tracker.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    private UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String register(User user) {
        try {
            userMapper.register(user);
            return Util.convertToSHA1(user.getId());
        } catch (DuplicateKeyException e) {
            LoggerUtil.printInfo(this.getClass(), e.getMessage());
            throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.DUPLICATE_PHONE_ERROR);
        } catch (Exception e) {
            LoggerUtil.printInfo(this.getClass(), e.getMessage());
            throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.USER_SIGNUP_ERROR);
        }
    }

    public String login(User user) {
        try {
            String id = userMapper.login(user);
            if(null != id) {
                return id;
            }
        } catch (Exception e) {
            LoggerUtil.printInfo(this.getClass(), e.getMessage());
            throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.USER_LOGIN_ERROR);
        }
        LoggerUtil.printInfo(this.getClass(), "User not found");
        throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.USER_NOT_FOUND);
    }

    public void updatePassword(PasswordUpdateDto updateDto) {
        if(null != updateDto && null != updateDto.getId() && null != updateDto.getOldPassword() && null !=
                updateDto.getNewPassword()) {
            User user = new User();
            user.setId(updateDto.getId());
            user.setPassword(updateDto.getOldPassword());
            String user_id = userMapper.getUser(user);
            if(null == user_id) {
                LoggerUtil.printInfo(this.getClass(), "User not found");
                throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.USER_NOT_FOUND);
            }
            user.setPassword(updateDto.getNewPassword());
            try {
                userMapper.updatePassword(user);
            } catch (Exception e) {
                LoggerUtil.printInfo(this.getClass(), e.getMessage());
                throw TrackerExceptionFactory.throwCustomizedException(ServiceManager.ErrorCode.USER_PROFILE_UPDATE_ERROR,
                        ServiceManager.ErrorMessage.USER_PROFILE_UPDATE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "Password");
            }
        } else {
            throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.INVALID_REQUEST_ERROR);
        }

    }

    public void updatePhone(PhoneUpdateDto updateDto) {
        if(null != updateDto && null != updateDto.getPhone() && null != updateDto.getPassword() && null != updateDto.getId()) {
            User user = new User();
            user.setId(updateDto.getId());
            user.setPassword(updateDto.getPassword());
            user.setPhone(updateDto.getPhone());
            String user_id = userMapper.getUser(user);
            if(null == user_id) {
                LoggerUtil.printInfo(this.getClass(), "User not found");
                throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.USER_NOT_FOUND);
            }
            try {
                userMapper.updatePhone(user);
            } catch (DuplicateKeyException e) {
                LoggerUtil.printInfo(this.getClass(), e.getMessage());
                throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.DUPLICATE_PHONE_ERROR);
            } catch (Exception e) {
                LoggerUtil.printInfo(this.getClass(), e.getMessage());
                throw TrackerExceptionFactory.throwCustomizedException(ServiceManager.ErrorCode.USER_PROFILE_UPDATE_ERROR,
                        ServiceManager.ErrorMessage.USER_PROFILE_UPDATE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "Phone");
            }
        }
        else {
            throw TrackerExceptionFactory.throwException(ServiceManager.ErrorCode.INVALID_REQUEST_ERROR);
        }
    }

}
