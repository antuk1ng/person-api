package org.houseone.person.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PERSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
