package com.knowledgehut.crm.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.knowledgehut.crm.entities.LeadTransition;

@Repository
@Scope("singleton")
public interface LeadTransitionRepository extends JpaRepository<LeadTransition, Long>, JpaSpecificationExecutor<LeadTransition> {


}
