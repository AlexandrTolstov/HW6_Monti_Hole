package ru.gradle;

import lombok.Getter;
import lombok.Setter;

public class Door {
    @Getter
    int numberOfDoor;
    boolean isCarBehind;
    @Getter
    boolean isOpened;

    @Getter
    @Setter
    double probabilityOfWinning;

    public Door(int numberOfDoor, double probabilityOfWinning, boolean isCarBehind) {
        isOpened = false;
        this.numberOfDoor = numberOfDoor;
        this.probabilityOfWinning = probabilityOfWinning;
        this.isCarBehind = isCarBehind;
    }

    public void open(){
        isOpened = true;
    }

    @Override
    public String toString() {
        String status;
        String isCar;
        if(isOpened){
            status = "Открыта";
        }else {
            status = "Закрыта";
        }
        if(isCarBehind){
            isCar = "Да";
        }else {
            isCar = "Нет";
        }
        return String.format("\n[Номер двери: %d:\nСтатус: %s; Вероятность выигрыша: %.2f; Есть ли за ней машина: %s]",
                numberOfDoor, status, probabilityOfWinning, isCar);
    }
}
