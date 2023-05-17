package com.bharath.location.repository;

import com.bharath.location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    // this query is a JPQL (JPA Query), made against the objects, hibernate will internally convert this to a database query
    @Query("select type, count(type) from location group by type")
    public List<Object[]> findTypeAndTypeCount();

}
