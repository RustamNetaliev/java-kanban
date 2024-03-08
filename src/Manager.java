import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class Manager {
    Scanner scanner = new Scanner(System.in);
    final static HashMap<Integer, Task> taskHashMap = new HashMap<>();
    final static HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    final static HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
    Integer id = 1;

public void inputTask(){
    System.out.println("Выберите тип задачи:");
    System.out.println("1 - одиночная задача (task)");
    System.out.println("2 - составная задача (epic)");
    System.out.println("3 - подзадача составной (subtask)");
    String inputTask = scanner.nextLine();
    System.out.println("Введите название задачи:");
    String name = scanner.nextLine();
    System.out.println("Укажите статус:NEW,\n" +
            "               IN_PROGRESS,\n" +
            "               DONE");
    String  status = scanner.nextLine();
    switch (status) {
        case "NEW", "IN_PROGRESS", "DONE" -> {
        }
        default -> {
            System.out.println("Ошибка ввода статуса. Начните заново :(");
            return;
        }
    }
    System.out.println("Добавьте описание:");
    String description = scanner.nextLine();

    switch (inputTask){
        case "1":
            Task task = new Task(id, name, status, description);
            taskHashMap.put(id, task);
            System.out.println("Создана задача(task) id="+id);
            break;
        case "2":
            Epic epic = new Epic(id, name,"NEW", description);
            epicHashMap.put(id, epic);
            System.out.println("Создана составная задача(epic) id="+id);
            break;
        case "3":
            Subtask subtask = new Subtask(id,name,status,description);
            System.out.println("Введите id составной задачи (epic)");
            String idEpic = scanner.nextLine();
            if(this.checkId(idEpic)) {
                Integer checkedIdEpic = Integer.parseInt(idEpic);
                epicHashMap.get(checkedIdEpic).subtasks.add(subtask);
                epicHashMap.get(checkedIdEpic).checkStatusOfSubs();
                subtaskHashMap.put(id, subtask);
                System.out.println("Создана подзадача(subtask) id=" + id);
            }else{
                System.out.println("Ошибка создания подзадачи");
            }
            break;
        default:
            System.out.println("ошибка ввода");
            break;
    }
    id++;
}

    public void printTask(Integer id) {
        if(!(taskHashMap.get(id)==null)) {
            System.out.println(taskHashMap.get(id).toString());
        } else {
            if(!(epicHashMap.get(id)==null)) {
                System.out.println(epicHashMap.get(id).toString());
            } else {
                if(!(subtaskHashMap.get(id)==null)) {
                    System.out.println(subtaskHashMap.get(id).toString());
                }
            }
        }
    }

    public void updateTaskById (String idUpdating) {
        if (this.checkId(idUpdating)){
            Integer id = Integer.parseInt(idUpdating);
            System.out.println("Введите название задачи:");
            String name = scanner.nextLine();
            System.out.println("Укажите статус:NEW,\n" +
                    "               IN_PROGRESS,\n" +
                    "               DONE");
            String status;
            while(true){
                status = scanner.nextLine();
                if(status.equals("NEW")||status.equals("IN_PROGRESS")||status.equals("DONE")){
                    break;
                } else {
                    System.out.println("ошибка ввода статуса, повторите ввод");
                    System.out.println("Укажите статус:NEW,\n" +
                            "           IN_PROGRESS,\n" +
                            "           DONE");
                }
            }
            System.out.println("Добавьте описание:");
            String description = scanner.nextLine();
            String type = this.checkType(id);
            switch (type){
                case "task":
                    Task task = taskHashMap.get(id);
                    task.setName(name);
                    task.setStatus(Status.valueOf(status));
                    task.setDescription(description);
                    System.out.println("Простая задача (task) id= "+id+" успешно обновлена");
                    break;
                case "epic":
                    Epic epic = epicHashMap.get(id);
                    epic.setName(name);
                    epic.setStatus(Status.valueOf(status));
                    epic.setDescription(description);
                    System.out.println("Составная задача (epic) id= "+id+" успешно обновлена");
                    break;
                case "subtask":
                    Subtask subtask = subtaskHashMap.get(id);
                    subtask.setName(name);
                    subtask.setStatus(Status.valueOf(status));
                    subtask.setDescription(description);
                    for(Epic epicParent : epicHashMap.values()){
                        for (Subtask sub : epicParent.subtasks){
                            if(sub.getId()==subtask.getId()){
                                sub=subtask;
                            }
                        }
                        epicParent.checkStatusOfSubs();
                    }
                    System.out.println("Подзадача (subtask) id= "+id+" успешно обновлена");
                    break;
            }

        }
    }

    public void idDelete (String idDelete){
        if (this.checkId(idDelete)){
            Integer id = Integer.parseInt(idDelete);
            if(!(taskHashMap.get(id) == null)) {
                taskHashMap.remove(id);
                System.out.println("Задача id= " + id + " успешно удалена");
            } else {
                if(!(epicHashMap.get(id) == null)) {
                    epicHashMap.remove(id);
                    System.out.println("Составная задача id= " + id + " успешно удалена");
                } else {
                    if (!(subtaskHashMap.get(id) == null)) {
                        Subtask sub = subtaskHashMap.get(id);
                        for(Epic epic : epicHashMap.values()){
                            epic.subtasks.remove(sub);
                        }
                        subtaskHashMap.remove(id);
                        System.out.println("Подзадача id= " + id + " успешно удалена");
                    }
                }
            }
        }
    }

    public void printSubtasksOfEpic(String idEpic){
        if (this.checkId(idEpic)){
            Integer id = Integer.parseInt(idEpic);
            for (Subtask sub : epicHashMap.get(id).subtasks){
                System.out.println(sub.toString());
            }
        }
    }

    public boolean checkId (String checkId){
        try {
            Integer id = Integer.parseInt(checkId);
            if (taskHashMap.containsKey(id)||epicHashMap.containsKey(id)||subtaskHashMap.containsKey(id)){
                return true;
            } else {
                System.out.println("Нет задачи с таким id");
                return false;
            }
        } catch (Exception e) {
            System.out.println("ошибка ввода id (требуется натуральное число)");
            return false;
        }
    }

    public String checkType (Integer id){
        if (taskHashMap.containsKey(id)){
            return "task";
        } else {
            if (epicHashMap.containsKey(id)){
                return "epic";
            } else {
                if (subtaskHashMap.containsKey(id)){
                    return "subtask";
                } else {
                    System.out.println("Нет задачи с таким id");
                    return null;
                }
            }
        }
    }
}

