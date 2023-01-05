package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    
}
