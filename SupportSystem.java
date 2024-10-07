import java.util.HashSet;


public class SupportSystem
{
    private InputReader reader;
    private Responder responder;
    
    public SupportSystem()
    {
        reader = new InputReader();
        responder = new Responder();
    }

    public void start()
    {
        boolean finished = false;

        printWelcome();

        while(!finished) {
            HashSet<String> input = reader.getInput();

            if(input.contains("bye")) {
                finished = true;
            }
            else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
        printGoodbye();
    }


    private void printWelcome()
    {
        System.out.println("Selamat datang di Technical Support System.");
        System.out.println();
        System.out.println("Apa masalah yang sedang anda hadapi?.");
        System.out.println("Kami akan membantumu.");
        System.out.println("Ketik 'bye' jika ingin keluar.");
    }


    private void printGoodbye()
    {
        System.out.println("Bye....");
    }
}