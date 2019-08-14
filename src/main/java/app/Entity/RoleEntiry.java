package app.Entity;

import java.util.ArrayList;

public class RoleEntiry {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public ArrayList getPermission_list() {
        return permission_list;
    }

    public void setPermission_list(ArrayList permission_list) {
        this.permission_list = permission_list;
    }

    int id;
    String role_name;
    int relation_id;
    ArrayList permission_list;
}
