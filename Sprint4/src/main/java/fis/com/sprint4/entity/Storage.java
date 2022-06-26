package fis.com.sprint4.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
@Entity
@Data
public class Storage extends AbstractEntity {
    private String name;
    private String location;

    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet = new HashSet<>();

}
