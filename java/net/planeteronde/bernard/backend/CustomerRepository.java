package net.planeteronde.bernard.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import net.planeteronde.bernard.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
