package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model {
    @Id
    private Long id;
    private String name;
}
