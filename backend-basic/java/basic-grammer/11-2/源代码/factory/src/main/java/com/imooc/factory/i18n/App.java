package com.imooc.factory.i18n;

public class App {
    public static void main(String[] args) {
        Language language = I18NFactory.getI18NObject("spain");
        System.out.println(language.getTitle());
    }
}
