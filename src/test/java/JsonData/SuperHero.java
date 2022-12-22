package JsonData;

public class SuperHero {
    public String squadName;
    public String homeTown;
    public int formed;
    public String secretBase;
    public boolean active;
    public Members[] members;

    public static class Members {
        public String name;
        public int age;
        public String secretIdentity;
        public String[] powers;
    }
}
