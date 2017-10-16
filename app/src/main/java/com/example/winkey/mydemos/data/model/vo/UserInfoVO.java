package com.example.winkey.mydemos.data.model.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */
@DatabaseTable(tableName = "UserInfo")
public class UserInfoVO {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(canBeNull = false,columnName = "name",indexName = "userName",uniqueCombo = true)
    public String name;
    @DatabaseField(columnName = "email")
    public String email;
    @DatabaseField(canBeNull = false,columnName = "phone")
    public String phone;

    public UserInfoVO(){

    }

    public UserInfoVO(String name,String email,String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public static class Builder {
        public UserInfoVO userInfoVO;

        public Builder() {
            userInfoVO = new UserInfoVO();
        }

        public UserInfoVO build() {
            return userInfoVO;
        }

        public Builder name(String name) {
            userInfoVO.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            userInfoVO.email = email;
            return this;
        }
        public Builder setPhone(String phone) {
            userInfoVO.phone = phone;
            return this;
        }
    }
}
