package models;

import lombok.Data;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Data
public class TodoState {
    @Id
    private Long id;

    private String name;

    private String description;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    public static Model.Finder<String, TodoState> find = new Model.Finder<>(String.class, TodoState.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for(TodoState t: find.orderBy("id").findList()) {
            options.put(t.id.toString(), t.description);
        }
        return options;
    }
}
