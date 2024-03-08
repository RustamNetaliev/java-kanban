import java.util.ArrayList;

public class Task {
    protected String name;
    protected String description;
    private int id;
    protected Status status;

    public Task (Integer id, String name, String status, String description){
        this.id = id;
        this.name = name;
        this.setStatus(Status.valueOf(status));
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description +
                "'}";
    }
}
