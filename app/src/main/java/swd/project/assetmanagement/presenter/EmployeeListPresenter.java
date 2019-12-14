package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Employee;
import swd.project.assetmanagement.repository.EmployeeRepository;
import swd.project.assetmanagement.repository.EmployeeRepositoryImpl;
import swd.project.assetmanagement.view.EmployeeListView;
import swd.project.assetmanagement.view.LoadingView;

public class EmployeeListPresenter {
    EmployeeRepository empRepo = new EmployeeRepositoryImpl();
    EmployeeListView employeeListView;
    LoadingView loadingView;

    public EmployeeListPresenter(EmployeeListView employeeListView, LoadingView loadingView) {
        this.employeeListView = employeeListView;
        this.loadingView = loadingView;
    }

    public void fetchListEmployeeFromServer(Long departmentId) {
        empRepo.fetchEmployee(departmentId, new CallbackData<List<Employee>>() {
            @Override
            public void onSuccess(List<Employee> employees) {
                loadingView.hideProgress();
                employeeListView.onSuccessFetchListEmployee(employees);
            }

            @Override
            public void onFail(String msg) {
                loadingView.hideProgress();
                employeeListView.onFailureFetchListEmployee(msg);
            }
        });
        loadingView.showProgress();
    }
}
