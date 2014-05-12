package com.knowledgehut.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowledgehut.crm.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
