/**
 * Created by Tomáš Honner.
 */
public class City {

    /**
     * X position of the city
     */
    private int x;

    /**
     * Y position of the city
     */
    private int y;

    /**
     * City indetifier
     */
    private int cityNumber;


    /**
     * Default constructor
     * @param x X position
     * @param y Y posotion
     * @param cityNumber City identifier
     */
    public City(int x,int y, int cityNumber)
    {
        this.setX(x);
        this.setY(y);
        this.setCityNumber(cityNumber);
    }

    /**
     * Getter for X position
     * @return X position
     */
    public int getX()
    {
        return x;
    }

    /**
     * Setter for X position
     * @param x X position
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Getter for Y position
     * @return Y position
     */
    public int getY()
    {
        return y;
    }

    /**
     * Setter for Y position
     * @param y Y position
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Getter for city number
     * @return City number
     */
    public int getCityNumber()
    {
        return cityNumber;
    }

    /**
     * Setter for city number
     * @param cityNumber City number
     */
    public void setCityNumber(int cityNumber)
    {
        this.cityNumber = cityNumber;
    }
}
