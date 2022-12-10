package com.br.dorotech.app.repositories;

import com.br.dorotech.app.controllers.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, Products> {


}
