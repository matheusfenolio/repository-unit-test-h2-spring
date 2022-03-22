package com.poc.dbtwounittests.repositories;

import com.poc.dbtwounittests.entities.Person;
import com.poc.dbtwounittests.projections.PersonProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from {h-schema}person"
    )
    List<PersonProjection> getAllPersonsInDatabase();

    @Query(
            nativeQuery = true,
            value = "WITH persons AS (SELECT * FROM {h-schema}person) " +
                    "SELECT * FROM persons"
    )
    List<PersonProjection> getAllPersons();
}
