package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.Product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

}
