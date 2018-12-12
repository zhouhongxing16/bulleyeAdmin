package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.LogsMapper;
import com.chris.bulleyeadmin.pojo.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:42
 */
@Service
public class LogsService extends BaseService<Logs> {

    @Autowired
    LogsMapper logsMapper;

    @Override
    public BaseMapper<Logs> getMapper() {
        return logsMapper;
    }
}
