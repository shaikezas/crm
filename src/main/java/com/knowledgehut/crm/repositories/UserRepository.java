package com.knowledgehut.crm.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledgehut.crm.entities.UserEntity;

@Repository
@Scope("singleton")
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @Query(value = "From UserEntity where id = :id")
    public UserEntity findOne(@Param("id") Long id);

    @Query(value = "select U from UserEntity U where loginName=:loginName and password=:password and deleted=false and active=true")
    public UserEntity findByLoginPassword(@Param("loginName") String loginName, @Param("password") String password);

    @Query(value = "select U from UserEntity U where loginName=:loginName and deleted=false and active=true")
    public UserEntity findByLoginId(@Param("loginName") String loginName);

}
