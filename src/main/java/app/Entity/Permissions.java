package app.Entity;

public class Permissions {
    public String role;

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String permission_name;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAllow_page() {
        return allow_page;
    }

    public void setAllow_page(String allow_page) {
        this.allow_page = allow_page;
    }

    public String allow_page;
}
