package com.kayo.c2m.c2mbackend.repository;

import com.kayo.c2m.c2mbackend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,String> {

    Optional<Person> findById(String id);
}
