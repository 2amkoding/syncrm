package com.gigsync.syncrm.Repos;
import com.gigsync.syncrm.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
}
