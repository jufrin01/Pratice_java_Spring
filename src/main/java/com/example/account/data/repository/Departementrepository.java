package com.example.account.data.repository;

import com.example.account.data.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Departementrepository extends JpaRepository<Departement,Integer> {
}
