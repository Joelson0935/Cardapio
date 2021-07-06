package br.com.cardapio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.entity.Bebidas;

@Repository
public interface BebidasRepository extends CrudRepository<Bebidas, Long>{
	
	List<Bebidas> findByBebidaContaining(String bebida);
}
