package com.example.winkey.mydemos.data.database.base;

import android.content.Context;

import com.example.winkey.mydemos.data.database.BaseDao;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.utils.Logger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class UserInfoDao extends BaseDao<UserInfoVO, Integer> {
    public Context mContext;

    public UserInfoDao(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Dao<UserInfoVO, Integer> getDao() throws SQLException {
        return getHelper().getDao(UserInfoVO.class);
    }

    public boolean create(UserInfoVO userInfoVO) {
        try {
            getDao().createIfNotExists(userInfoVO);
            return true;
        } catch (SQLException e) {
            Logger.debug("exception" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<UserInfoVO> query() {
        List<UserInfoVO> list = null;
        try {
            list = getDao().queryForAll();
            /*Logger.debug("总数"+getDao().queryBuilder().query().size());
            userInfoVO=getDao().queryBuilder().queryForFirst();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> queryName() {
        List<String> list = new ArrayList<>();
        try {
            for (UserInfoVO userInfoVO : getDao().queryForAll()) {
                list.add(userInfoVO.name);
            }
            /*Logger.debug("总数"+getDao().queryBuilder().query().size());
            userInfoVO=getDao().queryBuilder().queryForFirst();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserInfoVO query(String name) {
        try {
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.where().eq("name", name);
            UserInfoVO userInfoVO = (UserInfoVO) queryBuilder.queryForFirst();
            return userInfoVO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String name) {
        try {
            DeleteBuilder deleteBuilder = getDao().deleteBuilder();
            deleteBuilder.where().eq("name", name);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(String name,String phone,String email) {
        try {
            UpdateBuilder updateBuilder = getDao().updateBuilder();
            updateBuilder.updateColumnValue("phone",phone).where().eq("name", name);
            updateBuilder.updateColumnValue("email",email).where().eq("name", name);
            updateBuilder.update();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clear() {
        try {
            List<UserInfoVO> all = getDao().queryForAll();
            getDao().delete(all);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
