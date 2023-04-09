package com.example.lostitfoundit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertAll(User... users);

    @Query("select * from user")
    List<User> getAllUsers();

    @Query("select * from user where email = :email and password = :password")
    User getUserAtLogin(String email, String password);

}
