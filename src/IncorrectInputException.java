public class IncorrectInputException extends NumberFormatException {

    public IncorrectInputException(String text) {
        super("�� ����� ����� " + "'" + text + "'" + " ,� ����� �������� ��������");
    }
}
