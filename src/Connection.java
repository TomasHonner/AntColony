/**
 * Created by Tomáš Honner.
 */
public class Connection {

    /**
     * First city for a connection
     */
    private City cityA;

    /**
     * Second city for a connection
     */
    private City cityB;

    /**
     * Amount of pheromone
     */
    private Double pheromone;

    /**
     * Dafault and minimal value for the pheromone
     */
    private static final Double pheromoneDefaultValue = 1.0;

    /**
     * Pheromone decrease value
     */
    private static final Double pheromoneDecreaseRate = 0.3;


    /**
     * Constructor of class Connection
     * @param a
     * @param b
     */
    public Connection(City a, City b)
    {
        this.cityA = a;
        this.cityB = b;
        pheromone = pheromoneDefaultValue;
    }

    /**
     * Add pheromone on connection
     * @param step Amount of pheromone
     */
    public void addPheromone(Double step)
    {
        pheromone += step;
    }

    /**
     * Return distance between cities for probability calculation
     * @return Inverted distance for probability calculation
     */
    public Double getCost()
    {
        Double res;
        res = 1 / Math.sqrt(Math.pow(Math.abs(cityA.getX() - cityB.getX()), 2) + Math.pow(Math.abs(cityA.getY() - cityB.getY()), 2));
        return res;
    }

    /**
     * Return not inverted distance between cities
     * @return Not inverted distance
     */
    public Double getLength()
    {
        Double res = Double.MAX_VALUE;
        res = Math.sqrt(Math.pow(Math.abs(cityA.getX() - cityB.getX()), 2) + Math.pow(Math.abs(cityA.getY() - cityB.getY()), 2));
        return res;
    }

    /**
     * Decrease amount of pheromone
     */
    public void decreasePheromone()
    {
        pheromone = pheromone * (1.0 - pheromoneDecreaseRate);
        if (pheromone < pheromoneDefaultValue)
        {
            pheromone = pheromoneDefaultValue;
        }
    }

    /**
     * Getter for city A
     * @return City a
     */
    public City getCityA()
    {
        return cityA;
    }

    /**
     * Getter for city b
     * @return City b
     */
    public City getCityB()
    {
        return cityB;
    }

    /**
     * Getter for amount of pheromone
     * @return Amount of pheromone
     */
    public Double getPheromone()
    {
        return pheromone;
    }
}
