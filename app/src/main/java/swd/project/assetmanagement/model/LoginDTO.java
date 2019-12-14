package swd.project.assetmanagement.model;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    String jwt;
    Employee employee;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
