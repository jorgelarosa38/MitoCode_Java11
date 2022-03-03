package com.mitocode.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// NO DEBE IR EL "@REPOSITORY". AL SER GENÉRICO, BUSCARÁ CARGAR LOS MÉTODOS DEL OBJETO
// OBJETO QUE EN ESTE CASO ES ABSTRACTO
@NoRepositoryBean
public interface IGenericRepository<T, ID> extends JpaRepository<T, ID> {
}
