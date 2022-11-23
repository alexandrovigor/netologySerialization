public class IncorrectInputException extends NumberFormatException {

    public IncorrectInputException(String text) {
        super("Вы ввели текст " + "'" + text + "'" + " ,а нужно цифровые значения");
    }
}
