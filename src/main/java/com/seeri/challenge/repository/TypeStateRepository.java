package com.seeri.challenge.repository;

import com.seeri.challenge.entity.TypeStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStateRepository extends JpaRepository<TypeStateEntity, Long> {
    @Query(value = "SELECT *FROM public.type_state where name = ?1 ",nativeQuery=true)
    TypeStateEntity getTypeState(String name);
}
