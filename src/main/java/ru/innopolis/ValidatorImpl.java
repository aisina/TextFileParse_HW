package ru.innopolis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by innopolis on 15.12.2016.
 */

public class ValidatorImpl implements Validator {

    private String regexp;

    ValidatorImpl(){
        //this.regexp = "^([а-яА-Я]+(;|:|,|(\\.)|(\\.){3}|!|))+|(-)+$";
        this.regexp = "^([а-яА-Я]+(;|:|,|(\\.)|(\\.){3}|!|))+|(-)+|[а-яА-Я]+(-)[а-яА-Я]+$";
    }

    public String getRegexp() {
        return regexp;
    }

    /**
     *
     * @param ob
     * проверка ob на соответствие определенному шаблону.
     * Если подходит под данный шаблон,
     * @return true
     */
    @Override
    public boolean validate(Object ob) {
        Pattern pattern = Pattern.compile(this.regexp);
        Matcher matcher = pattern.matcher(ob.toString());

        return matcher.matches();
    }

    public static void main(String[] args) {
        ValidatorImpl val = new ValidatorImpl();
        boolean b = val.validate("khf");
        System.out.println(b);
    }
}
