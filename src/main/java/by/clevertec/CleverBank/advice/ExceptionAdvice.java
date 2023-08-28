package by.clevertec.CleverBank.advice;


import java.util.List;

public class ExceptionAdvice extends IllegalArgumentException{
    public ExceptionAdvice(String errorMessage) {
        super(errorMessage);
    }
    public ExceptionAdvice(List<String> errorMessages) {
        super(errorMessages.toString());
    }
}