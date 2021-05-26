package com.bit.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{

	//전체 리스트를 가져와 페이징 처리(->10개씩 ,15개씩 보여줄수있게하는거)
	public List<FreeBoard> findByBnoGreaterThan(Long bno,Pageable pageable);

	@Query("SELECT b.bno, b.title, count(r) FROM FreeBoard b LEFT OUTER JOIN b.replies r WHERE b.bno>0 GROUP BY b")
	public List<Object[]> getPage(Pageable pageable);
}
