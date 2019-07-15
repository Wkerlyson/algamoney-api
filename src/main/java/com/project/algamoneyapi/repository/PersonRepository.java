package com.project.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.algamoneyapi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
