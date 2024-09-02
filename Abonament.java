//In aceasta clasa sunt create abonamentele
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

//Abonament
public class Abonament{
    static Color color = new Color();
    String name;
    int gb;
    int min;
    String servicii;
    String OC;
    int pret;
    static String abAles;//abonamentul ales
    static String abY;//abonamentul introdus de in input

    public Abonament(){
        this.name = "Unknown";
        this.gb = 0;
        this.min = 0;
        this.servicii = "None";
        this.OC = "Unknown";
        this.pret = 0;
    }

    public Abonament(String name, int gb, int min, String servicii, String OC, int pret){
        this.name = name;
        this.gb = gb;
        this.min = min;
        this.servicii = servicii;
        this.OC = OC;
        this.pret = pret;
    }

    //metoda care face display la datele abunamentului dupa ce acesta a fost ales de catre utilizator
    public static void displayAbonamentData(Abonament abonament) {
        System.out.println(color.GREEN + "Subscription's information: " + color.RESET);
        System.out.println("Type: " + abonament.name);
        System.out.println("Internet: " + abonament.gb + "Gb");
        System.out.println("Minutes: " + abonament.min + "min");
        System.out.println("Services: " + abonament.servicii);
        System.out.println("Offer on connection: " + abonament.OC);
        System.out.println("Monthly payment: " + abonament.pret + "Lei");
    }

    //Abonamentele
    static Abonament Start100 = new Abonament("Start 100", 8, 200, "3 numere favorite.", "De doua ori mai mult internet.", 100);
    static Abonament Max140 = new Abonament("Max 140", 12, 450, "3 numere favorite.", "De doua ori mai mult internet.", 140);
    static Abonament Max175 = new Abonament("Max 175", 25, 750, "Apeluti nelimitate in retea.", "De doua ori mai mult internet.", 175);
    static Abonament Max200 = new Abonament("Max 200", 60, 1000, "Apeluti nelimitate in retea.", "De doua ori mai mult internet.", 200);
    static Abonament Max290 = new Abonament("Max 290", 100, 1000000, "Apeluti nelimitate in retea.", "De doua ori mai mult internet.", 290);

    public static void ab(String Abo, Abonament abonament) {


        if (Objects.equals(abY, Abo)) {
            System.out.println();
            displayAbonamentData(abonament);
            System.out.println();
            System.out.println("1 confirm");
            System.out.println("2 back");
            String conf_Back = Main.input.nextLine();

            if (Objects.equals(conf_Back, "1")) {
                abAles = abY;
                System.out.println();
            }
            if (Objects.equals(conf_Back, "2")) alegeAb();
        }
    }

    //metoda care ajuta utilizatorul sa aleaga abonamentul
    public static void alegeAb() {
        System.out.println(color.GREEN + "\nList of subscriptions:" + color.RESET);
        System.out.println("Start 100");
        System.out.println("Max 140");
        System.out.println("Max 175");
        System.out.println("Max 200");
        System.out.println("Max 290");
        System.out.print("Choose a subscription: ");
        abY = Main.input.nextLine();
        if (!Objects.equals(abY, "Start 100") && !Objects.equals(abY, "Max 140") && !Objects.equals(abY, "Max 175") && !Objects.equals(abY, "Max 200") && !Objects.equals(abY, "Max 290")){
            System.out.println(Main.color.RED + "\nSuch subscription does not exist!" + Main.color.RESET);
            alegeAb();
        }


        ab("Start 100", Start100);
        ab("Max 140", Max140);
        ab("Max 175", Max175);
        ab("Max 200", Max200);
        ab("Max 290", Max290);

    }

    //matoda care face schimb de abonament
    public static void schimbDeAbonament(String ID) {
        MainMenu.findClient(ID);
        String abCurent = MainMenu.foundClientData.get(5);
        String opCurent = MainMenu.foundClientData.get(6);
        alegeAb();
        String newLine = MainMenu.foundClientLine.replace(abCurent, "Abonament: " + abAles);
        String newLine1 = newLine.replace(opCurent, "Optiuni: None");
        FileHelper.replaceLine("ListaClientilor.txt", MainMenu.foundClientLine, newLine1);

        try (BufferedReader br = new BufferedReader(new FileReader("StareaContului.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ID)) {
                    if (Objects.equals(abAles, "Start 100")) {
                        String newStare = "ID: " + ID + ", GB: " + Start100.gb + ", MIN: " + Start100.min;
                        FileHelper.replaceLine("StareaContului.txt", line, newStare);
                    }
                    if (Objects.equals(abAles, "Max 140")) {
                        String newStare = "ID: " + ID + ", GB: " + Max140.gb + ", MIN: " + Max140.min;
                        FileHelper.replaceLine("StareaContului.txt", line, newStare);
                    }
                    if (Objects.equals(abAles, "Max 175")) {
                        String newStare = "ID: " + ID + ", GB: " + Max175.gb + ", MIN: " + Max175.min;
                        FileHelper.replaceLine("StareaContului.txt", line, newStare);
                    }
                    if (Objects.equals(abAles, "Max 200")) {
                        String newStare = "ID: " + ID + ", GB: " + Max200.gb + ", MIN: " + Max200.min;
                        FileHelper.replaceLine("StareaContului.txt", line, newStare);
                    }
                    if (Objects.equals(abAles, "Max 290")) {
                        String newStare = "ID: " + ID + ", GB: " + Max290.gb + ", MIN: " + Max290.min;
                        FileHelper.replaceLine("StareaContului.txt", line, newStare);
                    }
                }
            }
        } catch (IOException e) {
            // handle exception...
            System.out.println("No!");

        }



        MainMenu.foundClientData.clear();
    }

    public static void main(String[] args) {
        schimbDeAbonament("84755655");
    }
}



