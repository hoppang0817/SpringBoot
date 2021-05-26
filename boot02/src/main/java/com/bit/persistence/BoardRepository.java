package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{
	
}
