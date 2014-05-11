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

import com.knowledgehut.crm.entities.LeadStateTransition;

@Repository
@Scope("singleton")
public interface LeadStateTransitioinRepository extends JpaRepository<LeadStateTransition, Long>, JpaSpecificationExecutor<LeadStateTransition> {

  @Query(value = "FROM LeadStateTransition t where t.isDeleted=false")
  @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
  public List<LeadStateTransition> findAllReqStateTransitions();

  @Query(value = "FROM LeadStateTransition WHERE toState.id = :toState")
  @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
  public List<LeadStateTransition> findAllLeadStateTransitionsForGivenToState(@Param("toState") Long toState);
}
