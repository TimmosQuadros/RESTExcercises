package RESTException;

public class PersonMissingDataException extends Exception
{
    public PersonMissingDataException()
    {
    }

    public PersonMissingDataException(String msg)
    {
        super(msg);
    }
}