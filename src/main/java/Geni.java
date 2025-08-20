public class Geni {
    private UI ui;

    public Geni() {
        ui = new UI();
    }
    public void run() {
        StringBuilder output = new StringBuilder();
        output.append(ui.getGreeting()).append(ui.getExit());
        System.out.println(output);
    }
    public static void main(String[] args) {
        new Geni().run();
    }
}
