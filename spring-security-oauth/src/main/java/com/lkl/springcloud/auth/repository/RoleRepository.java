package com.lkl.springcloud.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lkl.springcloud.auth.entity.RcRoleEntity;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:16:09
 * ProjectName:Mirco-Service-Skeleton
 */
@Repository
public interface RoleRepository extends JpaRepository<RcRoleEntity,Integer>{

    @Query(value = "select role.* from rc_role role,rc_user_role ur where role.id=ur.role_id and ur.user_id=?1",nativeQuery = true)
    List<RcRoleEntity> getRoleValuesByUserId(Integer userId);
}