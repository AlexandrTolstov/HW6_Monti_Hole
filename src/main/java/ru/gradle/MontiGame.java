package ru.gradle;

import java.util.*;

public class MontiGame {
    HashMap<String, List<Door>> results;
    boolean[] door;
    Random rand;
    int INDEX_OF_WINNING_DOOR;
    int CHOICE_OF_GAMER;
    int MONTI_CHOICE;
    List<Door> doors;

    public MontiGame() {
        door = new boolean[3];
        results = new HashMap<>();
        rand = new Random();
        doors = new ArrayList<>();
        INDEX_OF_WINNING_DOOR = (int)(Math.random()*2);

        for (int i = 0; i < 3; i++) {
            if(i == INDEX_OF_WINNING_DOOR){
                doors.add(new Door(i + 1,((double)1)/3, true));
            }else {
                doors.add(new Door(i + 1,((double)1)/3, false));
            }
        }
    }

    /**
     * Старт игры
     */
    public void startGame(){

        System.out.println(doors);
        System.out.println("-------------------------------------------");
        CHOICE_OF_GAMER = gamerMakeChoice();

        results.put("Игрок cделал выбор двери №"+ CHOICE_OF_GAMER, doors);
        MONTI_CHOICE = montiMakeChoice();
        openTheDoor(MONTI_CHOICE);
        System.out.println("Монти открыл дверь №" + MONTI_CHOICE);

        for (Door door : doors){
            if(door.getNumberOfDoor() != CHOICE_OF_GAMER && door.getNumberOfDoor() != MONTI_CHOICE ){
                door.setProbabilityOfWinning(1 - (double) 1 /doors.size());
            }
            if(door.getNumberOfDoor() == MONTI_CHOICE){
                door.setProbabilityOfWinning(0);
            }
        }
        System.out.println(doors);
        results.put("Монти открыл дверь №"+ MONTI_CHOICE, doors);

        System.out.println("-------------------------------------------");

        int PREVIOUS_CHOICE = CHOICE_OF_GAMER;

        CHOICE_OF_GAMER = gamerChangeChoice();

        if(PREVIOUS_CHOICE == CHOICE_OF_GAMER){
            results.put("Геймер не менял выбор двери", doors);
        }else {
            results.put("Геймер поменял выбор двери", doors);
        }

        openTheDoor(CHOICE_OF_GAMER);
        System.out.println("Геймер открыл дверь №" + CHOICE_OF_GAMER);
        System.out.println(doors);

        if(doors.get(CHOICE_OF_GAMER - 1).isCarBehind){
            System.out.println("Геймер выиграл.");
        }else {
            System.out.println("Геймер проиграл.");
        }

    }

    /**
     * Игрок делает выбор
     * @return номер двери
     */
    private int gamerMakeChoice(){
        Scanner in = new Scanner(System.in);
        System.out.println("Выберете дверь: ");
        int choice;
        while (true){
            try {
                choice = in.nextInt();
                if (choice > 0 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Не верно выбрано окно, выберите от 1 до 3");
                }
            }catch (Exception e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                System.out.println("Попробуйте заново: ");
            }
        }
        return choice;
    };

    /**
     * Выбор Монти
     * @return номер двери
     */
    private int montiMakeChoice(){
        int choice;
        while (true){
            choice = (int)(Math.random()*3 + 1);
            if(CHOICE_OF_GAMER != choice && !doors.get(choice - 1).isCarBehind) //Выбор из оставшихся дверей и чтобы в ней не было машины
            {
                break;
            }
        }
        return choice;
    }

    /**
     * Открыть дверь
     * @param number дверь
     */
    private void openTheDoor(int number){
        for(Door door: doors){
            if(door.numberOfDoor == number){
                door.open();
                break;
            }
        }
    }


    Door getDorByNumber(int number){
        for(Door door1 : doors){
            if(door1.getNumberOfDoor() == number){
                return door1;
            }
        }
        return null;
    }

    private int gamerChangeChoice(){
        System.out.println("Будете менять ваш выбор? - Y/N: ");
        Scanner in = new Scanner(System.in);
        String choice;

        int doorChoice = 0;
        while (doorChoice == 0){
            try {
                choice = in.nextLine();
                if (choice.equals("N")) {
                    doorChoice = CHOICE_OF_GAMER;
                    break;
                } else if (choice.equals("Y")) {
                    for (int i = 0; i < 3; i++) {
                        if((i+1)!= CHOICE_OF_GAMER && (i+1)!= MONTI_CHOICE){
                            doorChoice = i+1;
                            break;
                        }
                    }
                } else {
                    System.out.println("Не верно выбрано окно, введите Y - да или N - нет");
                }
            }catch (Exception e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                System.out.println("Попробуйте заново: ");
            }
        }
        return doorChoice;
    }
}
