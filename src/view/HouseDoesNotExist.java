package view;

public class HouseDoesNotExist extends Exception
{
    public HouseDoesNotExist(String errorMessage)
    {
        super(errorMessage);
    }
}
