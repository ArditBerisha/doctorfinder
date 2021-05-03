package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.temadiplomes.doctorfinder.entity.RecommendedMahoutTable;

public interface RecommendedMahoutTableRepository extends JpaRepository<RecommendedMahoutTable, Integer> {

	//List<RecommendedMahoutTable> findTop4ByOrderByPreferenceDesc();
	
	//@Query("SELECT new com.baeldung.aggregation.model.custom.Recommend(SUM(c.preferemce)) "
			  //+ "FROM RecommendMahoutTable AS c GROUP BY c.doctor ORDER BY c.preference DESC")
			//List<RecommendedMahoutTable> countTotalCommentsByYearClass();
}

