package com.project.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.algamoneyapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
