import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tomáš Honner.
 */
public class World {

    /**
     * List of cities
     */
    private ArrayList<City> cities;

    /**
     * List of ants
     */
    private ArrayList<Ant> ants;

    /**
     * List of connections
     */
    private ArrayList<Connection> connections;

    /**
     * Number of ants
     */
    private int numberOfAnts;


    /**
     * Number of cycles
     */
    private int numberOfCycles;

    /**
     * Random generator
     */
    private Random random;


    /**
     * Constructor of class World
     * @param numberOfAnts Number of ants
     * @param numberOfCycles Number of cycles
     */
    public World(int numberOfAnts, int numberOfCycles)
    {
        cities = new ArrayList<>();
        ants = new ArrayList<>();
        connections = new ArrayList<>();
        this.numberOfAnts = numberOfAnts;
        this.numberOfCycles = numberOfCycles;
        random = new Random();
    }

    /**
     * Add city to the list
     * @param city City
     */
    public void addCity(City city)
    {
        cities.add(city);
    }

    /**
     * Preperation before first run
     */
    public void prepare()
    {
        for (City cityA: cities
             ) {
            for (City cityB: cities
                 ) {
                if (cityA.getCityNumber() >= cityB.getCityNumber())
                {
                    continue;
                }
                connections.add(new Connection(cityA, cityB));
            }
        }

        for (int i = 0; i < numberOfAnts ; i++)
        {
            ants.add(new Ant(cities.get(random.nextInt(cities.size())), connections, cities));
        }
    }

    /**
     * Get connection length between cities
     * @param solution List of cities
     * @return Connection length
     */
    private Double getSolutionLength(ArrayList<City> solution)
    {
        Double res = Double.valueOf(0);
        for (int i = 0; i < solution.size() ; i++)
        {
            City a = solution.get(i);
            City b = null;
            if (i == solution.size() -1)
            {
                b = solution.get(0);
            }
            else
            {
                b = solution.get(i+1);
            }
            for (Connection conn: connections
                 ) {
                if ((a.equals(conn.getCityA()) && b.equals(conn.getCityB())) || (a.equals(conn.getCityB()) && b.equals(conn.getCityA())))
                {
                    res += conn.getLength();
                }
            }
        }
        return res;
    }

    /**
     * Run calculation
     */
    public void run()
    {
        ArrayList<City> bestSolution = null;
        for (int i = 0; i < numberOfCycles ; i++)
        {
            for (Ant ant: ants
                 ) {
                ArrayList<City> solution = ant.solve();
                //printSolution(solution);
                if (i == numberOfCycles -1)
                {
                    if (bestSolution == null)
                    {
                        bestSolution = solution;
                    }
                    else
                    {
                        if (getSolutionLength(solution) < getSolutionLength(bestSolution))
                        {
                            bestSolution = solution;
                        }
                    }
                }
            }

            for (Connection conn: connections
                 ) {
                conn.decreasePheromone();
            }

            for (Ant ant: ants
                 ) {
                ant.increasePheromone();
                ant.clearVisitedCities();
            }
        }
        printSolution(bestSolution);
    }

    /**
     * Print best solution
     * @param solution Best solution to print
     */
    public void printSolution(ArrayList<City> solution)
    {
        String res = "Solution: ";
        for (City city: solution
             ) {
            res += city.getCityNumber() + "  ";
        }
        System.out.println(res);
    }

}
