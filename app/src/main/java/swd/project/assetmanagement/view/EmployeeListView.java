package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.Employee;

public interface EmployeeListView {

    void onSuccessFetchListEmployee(List<Employee> employees);
    void onFailureFetchListEmployee(String msg);

}
