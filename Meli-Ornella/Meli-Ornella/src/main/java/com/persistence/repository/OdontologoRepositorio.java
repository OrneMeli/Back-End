package com.persistence.repository;

import com.persistence.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepositorio extends JpaRepository<Odontologo, Long> {

}
