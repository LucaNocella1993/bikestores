package com.example.bikestores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	//JPQL Query
	@Query("FROM Customer WHERE first_name = ?1 AND last_name = ?2")
	List<Customer> findByFirstNameAndLastNameJPQL(String firstName, String lastName);

	//Native query
	@Query(value = "SELECT * FROM Customers WHERE first_name = :firstName AND last_name = :lastName", nativeQuery = true)
	List<Customer> findByFirstNameAndLastNameNative(String firstName, String lastName);

	//Derived Query
	List<Customer> findByCity(String city);

	//Update with native query
	@Query(value = "UPDATE Customers SET first_name = :firstName, last_name = :lastName WHERE customer_id = :id", nativeQuery = true)
	@Modifying
	void renameCustomer(String firstName, String lastName, Long id);
	
	//Procedure
	@Query(value = "EXEC Customer_Rename :firstName, :lastName, :id", nativeQuery = true)
	@Modifying
	void renameCustomerProcedure(String firstName, String lastName, Long id);
}

