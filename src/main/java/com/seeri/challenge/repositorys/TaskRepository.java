package com.seeri.challenge.repositorys;

import com.seeri.challenge.entitys.TaskEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query(value = "SELECT *FROM public.task where type_state_id = ?1 ",nativeQuery=true)
    List<TaskEntity> getTask(long idTypeTask);
}
