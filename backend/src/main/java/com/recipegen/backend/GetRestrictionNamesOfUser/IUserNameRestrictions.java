package com.recipegen.backend.GetRestrictionNamesOfUser;

import java.util.Set;

public interface IUserNameRestrictions {
    Set<String> getRestrictionNamesOfUser(String name);
}
