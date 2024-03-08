import java.util.ArrayList;
public class Epic extends Task{
    ArrayList<Subtask> subtasks;

    public Epic(Integer id, String name, String status, String description){
        super(id,name,status,description);
        this.subtasks = new ArrayList<>();
    }

    public void checkStatusOfSubs(){
        int countOfDoneSubs = 0;
        for (Subtask sub : this.subtasks) {
            if (sub.status == Status.DONE) {
            countOfDoneSubs++;
            }
        }
        if (countOfDoneSubs == this.subtasks.size()) {
            this.setStatus(Status.DONE);
        }
        int countOfNewSubs = 0;
        for (Subtask sub : this.subtasks) {
            if (sub.status == Status.NEW) {
                countOfNewSubs++;
            }
        }
        if (countOfNewSubs == this.subtasks.size()) {
            this.setStatus(Status.NEW);
        } else {
            if(!(countOfDoneSubs==this.subtasks.size())){
                this.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description +
                ", subtasks='" + subtasks +
                "'}";
    }
}
