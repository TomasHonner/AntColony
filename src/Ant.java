import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tomáš Honner.
 */
public class Ant {

    /**
     * List of all cities
     */
    private ArrayList<City> cities;

    /**
     * List of visited cities
     */
    private ArrayList<City> visitedCities;

    /**
     * List of connections (roads) between cities
     */
    private ArrayList<Connection> connections;

    /**
     * Position if the ant
     */
    private City city;

    /**
     * Instance of random generator
     */
    private Random random;

    /**
     * Amount of pheromone for probability calculation
     */
    private static final int pheromoneExp = 1;

    /**
     * Pheromone cost of one trip
     */
    private static final int costExp = 2;

    /**
     * Amount of pheromone for one ant on one connection
     */
    private static final Double pheromoneStep = 1.0;


    /**
     * Default constructor of class Ant
     */
    public Ant()
    {

    }

    /**
     * Constructor of class Ant
     * @param city Current city
     * @param connections List of connections
     * @param cities List of cities
     */
    public Ant(City city, ArrayList<Connection> connections, ArrayList<City> cities)
    {
        this.city = city;
        this.connections = connections;
        this.cities = cities;
        this.visitedCities = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Generate road solution
     * @return Road solution
     */
    public ArrayList<City> solve()
    {
        for (int i = 0; i < cities.size() ; i++)
        {
            decide();
        }
        return visitedCities;
    }

    /**
     * Decide which city visit
     */
    private void decide()
    {
        ArrayList<City> choices = new ArrayList<>();
        ArrayList<Double> probs = new ArrayList<>();
        for (City city: cities
             ) {
            if(visitedCities.contains(city) || this.city.equals(city))
            {
                continue;
            }
            choices.add(city);
            probs.add(new Double(getProb(city)));
        }

        if (choices.size() == 0)
        {
            visitedCities.add(this.city);
            return;
        }

        Double probSum = 0.0;
        for (Double d: probs
             ) {
            probSum += d.doubleValue();
        }

        ArrayList<Double> probsCH = new ArrayList<>();
        for (Double d: probs
             ) {
            probsCH.add(new Double(d.doubleValue() / probSum));
        }

        Double doubleTreshold = Double.valueOf(0);
        Double rand = random.nextDouble();
        int i = 0;
        for (Double dd: probsCH
             ) {
            if (rand >= doubleTreshold && rand <= (doubleTreshold + dd.doubleValue()))
            {
                visitedCities.add(city);
                city = choices.get(i);
                break;
            }
            i++;
            doubleTreshold += dd.doubleValue();
        }


    }

    /**
     *  Get probability for the city
     * @param city City
     * @return Probability of the city
     */
    private Double getProb(City city)
    {
        Double res = -1.0;
        for (Connection connection: connections
             ) {
            if (this.city.equals(connection.getCityA()) && city.equals(connection.getCityB()) || this.city.equals(connection.getCityB()) && city.equals(connection.getCityA()))
            {
                res = Math.pow(connection.getPheromone(), pheromoneExp) * Math.pow(connection.getCost(), costExp);
            }
        }

        return res;
    }

    /**
     * Increase pheromone on connections
     */
    public void increasePheromone()
    {
        for (int i = 0; i < visitedCities.size() ; i++)
        {
            City a = visitedCities.get(i);
            City b = null;

            if (i == visitedCities.size() -1)
            {
                b = visitedCities.get(0);
            }
            else
            {
                b = visitedCities.get(i+1);
            }

            for (Connection conn: connections
                 ) {
                if ((a.equals(conn.getCityA()) && b.equals(conn.getCityB())) || (a.equals(conn.getCityB()) && b.equals(conn.getCityA())))
                {
                    conn.addPheromone(pheromoneStep);
                }
            }
        }

    }

    /**
     * Clear list of visited cities
     */
    public void clearVisitedCities()
    {
        visitedCities = new ArrayList<>();
    }

}
