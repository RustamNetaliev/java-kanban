import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch(command) {
                case "a":
                    System.out.println("Обычные задачи (task):");
                    for (Integer id : Manager.taskHashMap.keySet()){
                        manager.printTask(id);
                    }
                    System.out.println("Составные задачи (epic):");
                    for (Integer id : Manager.epicHashMap.keySet()){
                        manager.printTask(id);
                    }
                    System.out.println("Подзадачи (subtask):");
                    for (Integer id : Manager.subtaskHashMap.keySet()){
                        manager.printTask(id);
                    }
                    break;
                case "b":
                    Manager.taskHashMap.clear();
                    System.out.println("Обычные задачи очищены");
                    Manager.epicHashMap.clear();
                    System.out.println("Составные задачи очищены");
                    Manager.subtaskHashMap.clear();
                    System.out.println("Подзадачи очищены");
                    break;
                case "c":
                    System.out.println("Введите id задачи:");
                    String checkId = scanner.nextLine();
                    if (manager.checkId(checkId)) {
                        Integer id = Integer.parseInt(checkId);
                        manager.printTask(id);
                    }
                    break;
                case "d":
                    manager.inputTask();
                    break;
                case "e":
                    System.out.println("введите id задачи");
                    String idUpd = scanner.nextLine();
                    manager.updateTaskById(idUpd);
                    break;
                case "f":
                    System.out.println("введите id задачи для удаления");
                    String idDel = scanner.nextLine();
                    manager.idDelete(idDel);
                    break;
                case "g":
                    System.out.println("введите id составной задачи");
                    String id = scanner.nextLine();
                    manager.printSubtasksOfEpic(id);
                    break;
                case "h":
                    break;
                default:
                    System.out.println("ошибка ввода");
                    break;
            }
            if (command.equals("h")){
                break;
            }
        }

    }

    public static void printMenu(){
        System.out.println("Введите буквой (a-h):");
        System.out.println("a. Получение списка всех задач.");
        System.out.println("b. Удаление всех задач.");
        System.out.println("c. Вывод задачи по id.");
        System.out.println("d. Создать задачу");
        System.out.println("e. Обновление по id.");
        System.out.println("f. Удаление по id.");
        System.out.println("g. Получение списка всех подзадач определённого эпика");
        System.out.println("h. Выход");
    }
}
