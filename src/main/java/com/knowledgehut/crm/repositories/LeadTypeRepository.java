package com.knowledgehut.crm.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledgehut.crm.entities.LeadType;

@Repository
@Scope("singleton")
public interface LeadTypeRepository extends JpaRepository<LeadType, Long>, JpaSpecificationExecutor<LeadType> {


  @Query(value = "select lt from LeadType lt where lt.groupId is null")
  @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
  public List<LeadType> findLeadGroup();
  
  @Query(value = "select lt from LeadType lt where lt.groupId=:groupId")
  @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
  public List<LeadType> findLeadTypeByGroup(@Param("groupId")Long groupId);
  
}
