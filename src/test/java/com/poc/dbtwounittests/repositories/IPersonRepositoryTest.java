package com.poc.dbtwounittests.repositories;

import com.poc.dbtwounittests.entities.Person;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IPersonRepositoryTest {

    @Autowired
    private IPersonRepository repository;

    @Nested
    @DisplayName("Native queries")
    class NativeQueriesTest {
        @Before
        public void init(){
            var person = Person.builder().name("Test").build();
            repository.save(person);
        }

        @Test
        void testNativeQuery(){
            var response = repository.getAllPersonsInDatabase();

            assertEquals(1, response.size());
            assertEquals("Test", response.get(0).getName());
        }
    }

    @Nested
    @DisplayName("CRUD")
    class CRUDTests {
        @Test
        void testSave(){
            var person = Person.builder().name("Test").build();
            var response = repository.save(person);

            assertEquals(1, response.getId());
            assertEquals("Test", response.getName());
        }
    }

}
