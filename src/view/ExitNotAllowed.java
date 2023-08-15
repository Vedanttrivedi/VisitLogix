package view;

public class ExitNotAllowed extends Exception
{
    public ExitNotAllowed(String errorMessage)
    {
        super(errorMessage);
    }
}
