package com.AVKGact3.demoCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AVKGact3.demoCrud.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {}