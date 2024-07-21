package com.app.expense_tracker.db_mappers;

import com.app.expense_tracker.models.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Integer register(User user);

    String login(User user);

    String getUser(User user);

    void updatePassword(User user);

    void updatePhone(User user);
}
