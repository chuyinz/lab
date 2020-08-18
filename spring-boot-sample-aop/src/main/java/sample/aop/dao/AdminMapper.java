package sample.aop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sample.aop.entity.Admin;

@Mapper
public interface AdminMapper {
    Admin  queryByUsernameAndPassword(@Param("username") String username, @Param("password")String  password);
}
