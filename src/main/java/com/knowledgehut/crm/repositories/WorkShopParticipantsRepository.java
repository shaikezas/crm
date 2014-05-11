package com.knowledgehut.crm.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledgehut.crm.entities.Customer;
import com.knowledgehut.crm.entities.UserCourse;
import com.knowledgehut.crm.entities.UserEntity;
import com.knowledgehut.crm.entities.WorkShop;
import com.knowledgehut.crm.entities.WorkShopParticipants;

@Repository
@Scope("singleton")
public interface WorkShopParticipantsRepository extends JpaRepository<WorkShopParticipantsRepository, Long>, 
JpaSpecificationExecutor<WorkShopParticipants> {

 
}
