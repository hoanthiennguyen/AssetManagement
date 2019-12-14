package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Employee;

public interface EmployeeRepository {
    void fetchEmployee(long departmentId, CallbackData<List<Employee>> callBack);
}
