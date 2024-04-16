package com.example.ApiSporte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ApiSporte.model.Enumerado;


@Repository
public interface EnumeradoRepository extends JpaRepository<Enumerado, Integer>{

    @Query("SELECT u FROM Enumerado u WHERE u.padre.id = :id")
    List<Enumerado> findAllHijos(@Param("id") int id);
    
}
