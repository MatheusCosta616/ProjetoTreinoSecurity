package com.muts.treinoSecurity.domain.entity.enums;

import java.util.Set;

public enum Role {
    USER(Set.of(Permission.VIEW_PRODUCT)),
    ADMIN(Set.of(Permission.CREATE_USER, Permission.EDIT_USER, Permission.DELETE_USER, Permission.VIEW_PRODUCT));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }
}
