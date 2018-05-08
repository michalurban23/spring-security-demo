package com.codecool.demo;

public class Rule {

    private long ID;
    private String role;
    private String path;

    public Rule(long ID, String role, String path) {
        this.ID = ID;
        this.role = role;
        this.path = path;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ID=" + ID +
                ", role='" + role + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
