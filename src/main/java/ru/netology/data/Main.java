package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("ru"));
        String city = faker.address().city();
        System.out.println(city);

    }
}
