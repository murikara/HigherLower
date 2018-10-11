package com.example.higherlower;

/**
 * Deze klasse is een "worp" object wat gebruikt wordt voor het gooien van een random getal
 * en het ophalen van dat getal
 */
public class Throw {

    private int throwNumber;

    public Throw() {
    }

    public Throw(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    public int getThrowNumber() {
        return throwNumber;
    }

    public void setThrowNumber(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    @Override
    public String toString() {
        return "Throw is " + throwNumber;
    }
}
