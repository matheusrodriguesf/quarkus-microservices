package org.acme.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.dto.CustomerForm;
import org.acme.dto.CustomerResponse;
import org.acme.entity.Customer;
import org.acme.repository.CustomerRepository;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    return toCustomerResponse(customer);
                }).collect(Collectors.toList());
    }

    public CustomerResponse findById(Long id) {
        return toCustomerResponse(customerRepository.findById(id));
    }

    @Transactional
    public void create(CustomerForm valueForm) {
        customerRepository.persist(toCustomer(valueForm));
    }

    @Transactional
    public void edit(Long id, CustomerForm valueForm) {
        var customer = customerRepository.findById(id);
        customer = Customer.builder()
                .id(customer.getId())
                .name(valueForm.getName())
                .email(valueForm.getEmail())
                .phone(valueForm.getPhone())
                .age(valueForm.getAge())
                .build();
        customerRepository.persist(customer);
    }

    @Transactional
    public boolean delete(Long id) {
        return customerRepository.deleteById(id);
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .age(customer.getAge())
                .build();
    }

    private Customer toCustomer(CustomerForm customerResponse) {
        return Customer.builder()
                .name(customerResponse.getName())
                .email(customerResponse.getEmail())
                .phone(customerResponse.getPhone())
                .age(customerResponse.getAge())
                .build();
    }
}
