package com.traffic.pyp.traffic3.Bean;

public class AccountBean {
    private int carID;
    private int carImg;
    private String carNumber, carUsers;
    private int carMoney;

    public AccountBean(int carID, int carImg, String carNumber, String carUsers, int carMoney) {
        this.carID = carID;
        this.carImg = carImg;
        this.carNumber = carNumber;
        this.carUsers = carUsers;
        this.carMoney = carMoney;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCarImg() {
        return carImg;
    }

    public void setCarImg(int carImg) {
        this.carImg = carImg;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarUsers() {
        return carUsers;
    }

    public void setCarUsers(String carUsers) {
        this.carUsers = carUsers;
    }

    public int getCarMoney() {
        return carMoney;
    }

    public void setCarMoney(int carMoney) {
        this.carMoney = carMoney;
    }
}
