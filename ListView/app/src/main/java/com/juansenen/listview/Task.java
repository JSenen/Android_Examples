package com.juansenen.listview;

public class Task {

    public String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
