package org.hw_4.repository;

import org.hw_4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerById(int id);

    Optional<Customer> findCustomerByUsernameStartingWith(String statement);

    @Modifying
    @Query(value = "delete from Customer c where c.age < 20", nativeQuery = true)
    void deleteCustomerDueToAge();

    @Modifying
    @Query(value = "delete from Customer c where c.age < :age", nativeQuery = true)
    void deleteCustomerWhereAgeLessThan(@Param("age") int age);

}
