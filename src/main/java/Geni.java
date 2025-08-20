import java.util.Scanner;

public class Geni {
    private UI ui;

    public Geni() {
        ui = new UI();
    }
    public void run() {

        System.out.println(ui.getGreeting());
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine().trim();
        while (!inp.equals("bye")) {
            StringBuilder output = new StringBuilder();
            output.append("____________________________________________________________\n");
            output.append(inp).append('\n');
            output.append("____________________________________________________________\n");
            System.out.println(output);
            inp = sc.nextLine().trim();

        }

        System.out.println(ui.getExit());
    }
    public static void main(String[] args) {
        new Geni().run();
    }
}
