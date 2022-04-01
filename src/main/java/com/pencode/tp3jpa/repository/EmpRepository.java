package com.pencode.tp3jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pencode.tp3jpa.entities.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{

}
