package personserver;

public class DemoLoader {
    public static void main(String[] args) throws PersonLoadException {

        PersonLoader pl = new PersonLoader("D:\\Temp\\PR2-2020\\personen1.txt");

        System.out.println(pl.load());



    }
}
