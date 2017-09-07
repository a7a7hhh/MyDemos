package com.example.winkey.mydemos.presentation.eventbus;

import android.support.annotation.NonNull;


import com.example.winkey.mydemos.view.utils.DataUtils;
import com.example.winkey.mydemos.view.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * @author: xupg
 * @date:2016/3/30
 * @description:
 */
public class RxBus {

    private static RxBus instance;
    public static boolean DEBUG = false;
    /**
     * subject存储tag,list
     */
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    private RxBus() {
    }

    public static synchronized RxBus getInstance() {
        if (null == instance) {
            synchronized (RxBus.class) {
                if (null == instance) {
                    instance = new RxBus();
                }
            }
        }

        return instance;
    }

    /**
     * 注册
     *
     * @param tag
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        if (DEBUG) Logger.debug("[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    /**
     * 注销
     *
     * @param tag
     * @param observable
     */
    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject) observable);
            if (DataUtils.isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
        }

        if (DEBUG) Logger.debug("[unregister]subjectMapper: " + subjectMapper);
    }

    /**
     * 发送带tag的event
     *
     * @param tag
     * @param content
     */
    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);

        if (!DataUtils.isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
        if (DEBUG) Logger.debug("[send]subjectMapper: " + subjectMapper);
    }

}
