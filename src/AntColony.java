/**
 * Created by Tomáš Honner.
 */
public class AntColony {

    /**
     * Main method
     * @param args
     */
    public static void main(String args[]) throws Exception
    {
        World world = new World(5, 5);
        world.addCity(new City(0, 0, 1));
        world.addCity(new City(0, 50, 2));
        world.addCity(new City(10, 100, 3));
        world.addCity(new City(20, 150, 4));
        world.addCity(new City(40, 70, 5));


        world.prepare();

        world.run();
    }
}
