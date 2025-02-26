package com.imooc.factory.i18n;

public class I18NFactory {
    //静态工厂
    public static Language getI18NObject(String area){
        if(area.equals("china")){
            return new Chinese();
        }else if(area.equals("spain")){
            return new Spainish();
        }else if(area.equals("italy")){
            return new Italian();
        }else{
            return null;
        }
    }
}
