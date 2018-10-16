package com.priv.gabriel.repository;

import com.priv.gabriel.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-14
 * @Description:
 */
@SqlResource("user")
public interface UserRepository extends BaseMapper<User>{
	List<User> selectByTest();
}