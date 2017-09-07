package com.example.winkey.mydemos.data.database;

import android.content.Context;

import com.example.winkey.mydemos.data.database.exception.InvalidParamsException;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class BaseDao<T, Integer> {
    protected DataBaseHelper mDataBaseHelper;

    protected Context mContext;

    public BaseDao(Context context) {
        mContext = context;
        getHelper();
    }

    /**
     * 获取DatabaseHelper
     * @return
     */
    public DataBaseHelper getHelper() {
        if (mDataBaseHelper == null) {
            mDataBaseHelper = OpenHelperManager.getHelper(mContext, DataBaseHelper.class);
        }
        return mDataBaseHelper;
    }

    public abstract Dao<T, Integer> getDao() throws SQLException;

    /**
     * 存储数据
     * @param t
     * @return
     * @throws SQLException
     */
    public int save(T t) throws SQLException {
        return getDao().create(t);
    }

    /**
     *
     * @param preparedQuery
     * @return
     * @throws SQLException
     */
    private List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.query(preparedQuery);
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return
     * @throws SQLException
     */
    private List<T> query(String attributeName, String attributeValue) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        queryBuilder.where().eq(attributeName, attributeValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * @param attributeNames
     * @param attributeValues
     * @return
     * @throws SQLException
     * @throws InvalidParamsException
     */
    private List<T> query(String[] attributeNames, String[] attributeValues) throws SQLException,
            InvalidParamsException {
        if (attributeNames.length != attributeValues.length) {
            throw new InvalidParamsException("params size is not equal");
        }
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        for (int i = 0; i < attributeNames.length; i++) {
            wheres.eq(attributeNames[i], attributeValues[i]);
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<T> queryAll() throws SQLException {
        // QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        // PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        // return query(preparedQuery);
        Dao<T, Integer> dao = getDao();
        return dao.queryForAll();
    }

    /**
     * @param idName
     * @param idValue
     * @return
     * @throws SQLException
     */
    private T queryById(String idName, String idValue) throws SQLException {
        List<T> lst = query(idName, idValue);
        if (null != lst && !lst.isEmpty()) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    /**
     * @param preparedDelete
     * @return
     * @throws SQLException
     */
    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(preparedDelete);
    }

    /**
     * @param t
     * @return
     * @throws SQLException
     */
    private int delete(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(t);
    }

    /**
     * @param lst
     * @return
     * @throws SQLException
     */
    private int delete(List<T> lst) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(lst);
    }

    /**
     * @param attributeNames
     * @param attributeValues
     * @return
     * @throws SQLException
     * @throws InvalidParamsException
     */
    public int delete(String[] attributeNames, String[] attributeValues) throws SQLException,
            InvalidParamsException {
        List<T> lst = query(attributeNames, attributeValues);
        if (null != lst && !lst.isEmpty()) {
            return delete(lst);
        }
        return 0;
    }

    /**
     * @param idName
     * @param idValue
     * @return
     * @throws SQLException
     * @throws InvalidParamsException
     */
    public int deleteById(String idName, String idValue) throws SQLException,
            InvalidParamsException {
        T t = queryById(idName, idValue);
        if (null != t) {
            return delete(t);
        }
        return 0;
    }

    /**
     * @param t
     * @return
     * @throws SQLException
     */
    public int update(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.update(t);
    }

    /**
     * @return
     * @throws SQLException
     */
    public boolean isTableExsits() throws SQLException {
        return getDao().isTableExists();
    }

    /**
     * @return
     * @throws SQLException
     */
    public long countOf() throws SQLException {
        return getDao().countOf();
    }

    /**
     * @param map
     * @return
     * @throws SQLException
     */
    public List<T> query(Map<String, Object> map) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        if (!map.isEmpty()) {
            Where<T, Integer> wheres = queryBuilder.where();
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * @param map
     * @param lowMap
     * @param highMap
     * @return
     * @throws SQLException
     */
    public List<T> query(Map<String, Object> map, Map<String, Object> lowMap,
                         Map<String, Object> highMap) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        if (!map.isEmpty()) {
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        if (!lowMap.isEmpty()) {
            Set<String> keys = lowMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if(map.isEmpty()){
                    wheres.gt(keyss.get(i), lowMap.get(keyss.get(i)));
                }else{
                    wheres.and().gt(keyss.get(i), lowMap.get(keyss.get(i)));
                }
            }
        }

        if (!highMap.isEmpty()) {
            Set<String> keys = highMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                wheres.and().lt(keyss.get(i), highMap.get(keyss.get(i)));
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }
}
