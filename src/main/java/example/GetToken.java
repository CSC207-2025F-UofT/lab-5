package example;

/**
 * Class for getting your Grade API token.
 */
public class GetToken {

    /**
     * Run to get your token after you complete the code.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        GetExample example = new GetExample();

        String gradeAPISignUpURL = "https://grade-apis.panchen.ca/signUp";
        // TODO Task 1: use the run method to get your Grade API token (or obtain your token through a web browser).
        String response = "";

        try{
            response = example.run(gradeAPISignUpURL, "username", "Avi");
            System.out.println(response);
        } catch (Exception e){

        }

        System.out.println(response);
    }
}
