package com.tekup.loc_voiture.dao.entities.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private List<String> role;
}
