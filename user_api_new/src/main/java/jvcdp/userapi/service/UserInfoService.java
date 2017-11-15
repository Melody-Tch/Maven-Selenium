package jvcdp.userapi.service;

import java.util.List;
import jvcdp.userapi.model.UserInfo;


public interface UserInfoService {

	List<UserInfo> findAll();

	UserInfo addUser(UserInfo userinfo);

	UserInfo updateUser(UserInfo userinfo);

	UserInfo findOne(Long id);

	int delete(UserInfo delUserInfo);

	UserInfo findByEmail(String email);

	boolean authenticate(String name, String password);
	
}
