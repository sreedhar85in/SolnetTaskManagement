package nz.co.solnet.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import nz.co.solnet.entitiy.TaskEntity;

@RepositoryRestResource(path = "task", collectionResourceRel = "task")
public interface TaskDataRestRepository extends PagingAndSortingRepository<TaskEntity, Integer> {

	Optional<TaskEntity> findById(Long id);

}
