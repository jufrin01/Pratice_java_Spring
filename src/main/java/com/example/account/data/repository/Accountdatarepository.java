package com.example.account.data.repository;

import com.example.account.data.entities.Accountdata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Accountdatarepository extends JpaRepository<Accountdata,Integer> {
    // TODO : CARA MEMAKAI METODE PAGE
    @Override
    Page<Accountdata> findAll(Pageable pageable);
}