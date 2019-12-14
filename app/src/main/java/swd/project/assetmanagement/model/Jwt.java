package swd.project.assetmanagement.model;

import java.io.Serializable;

public class Jwt implements Serializable {
    String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
