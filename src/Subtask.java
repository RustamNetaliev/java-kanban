import java.util.Objects;
public class Subtask extends Task{
    public Subtask(Integer id, String name, String status, String description){
        super(id,name,status,description);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description +
                "'}";
    }
}
