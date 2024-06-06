import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private List<String> postTags;
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergencyTypes = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments = new ArrayList<>();

    private String postType;
    private String postEmergency;
    // private static int idCounter = 1; //Simple counter for postID


    public Post(int postID, String postTitle, String postBody, List<String> postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;   
    }

    //fixing the postID problem in test cases
    // public static void resetIDCounter(){
    //     idCounter = 1;
    // }

    public int getPostID(){
        return postID;
    }

    public boolean addPost() {
        if (isValidPost()) {
            // Write the post information to a TXT file
            try (FileWriter writer = new FileWriter("post.txt", true)) {
                writer.write("Post ID: " + postID + "\n");
                writer.write("Title: " + postTitle + "\n");
                writer.write("Body: " + postBody + "\n");
                writer.write("Tags: " + String.join(", ", postTags) + "\n");
                writer.write("Difficulty: " + postType + "\n");
                writer.write("Urgency: " + postEmergency + "\n");
                writer.write("\n");
                System.out.println("Post written to post.txt");
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Unable to write to file
            }

            return true;
        }
            System.out.println("Post validation failed.\n");
            return false; // Post validation failed
    }
    
    //checking for existing postID
    private boolean isValidPostID(int postID){
        try (BufferedReader reader = new BufferedReader(new FileReader("post.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Post ID: ")) {
                    String postIdStr = line.substring(9).trim();
                    // Convert postIdStr to integer manually
                    int existingPostID = 0;
                    for (int i = 0; i < postIdStr.length(); i++) {
                        char c = postIdStr.charAt(i);
                        if (c < '0' || c > '9') {
                            throw new NumberFormatException("Invalid post ID format");
                        }
                        existingPostID = existingPostID * 10 + (c - '0');
                    }
                    if (existingPostID == postID) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    //validate all the condition for the addPost
    private boolean isValidPost() {
        return isValidPostID(postID) && isValidTitle(postTitle) && isValidBody(postBody) && isValidTags(postTags) && isValidType(postType, postBody, postTags) && isValidEmergency(postEmergency, postType);
    }

    // Condition 1: Validate post title
    private boolean isValidTitle(String title){
        if(title.length() < 10 || title.length() > 250){
            System.out.println("Post title length validation failed.\n");
            return false;
        }
        for(int i = 0; i < 5; i++){
            if (!Character.isLetter(postTitle.charAt(i)) && !Character.isWhitespace(postTitle.charAt(i))) {
                System.out.println("Post title character validation failed.\n");
                return false;
            }
        }
        return true;
    }

    //Condition 2: validate post body
    private boolean isValidBody(String body){
        if (body.length() <= 250){
            System.out.println("Post body length validation failed.\n");
            return false;
        }
        return true;
    }

    // Condition 3: Validate post tags
    private boolean isValidTags(List<String>tags){
        if(tags.size() < 2 || tags.size() > 5){
            System.out.println("Post tags count validation failed.\n");
            return false;
        }
        for(String tag:tags){
            if(tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                System.out.println("Post tag validation failed: " + tag);
                return false;
            } 
        }
        return true;
    }

     // Condition 4: Validate post type and its constraints
    private boolean isValidType(String type, String body, List<String>tags){
        if(!Arrays.asList(postTypes).contains(type)){
            System.out.println("Invalid post type. Please enter \"Easy\", \"Difficult\" and \"Very Difficult\": ");
            return false;
        }

        if((type.equals("Very Difficult")) || type.equals("Diffucult") && body.length() < 300){
            System.out.println("Post body length is too short for type " + type + ". ");
            return false;
        }
        if(type.equals("Easy") && tags.size() > 3){
            System.out.println("Too many tags for type Easy. ");
            return false; 
        }
        return true;
    }

    // Condition 5: Validate post emergency and its constraints
    private boolean isValidEmergency(String emergency, String type){
        if(!Arrays.asList(postEmergencyTypes).contains(emergency)){
            System.out.println("Invalid post urgency. Please enter \"Immediately Needed\", \"Highly Needed\" and \"Ordinary\": "); ;
            return false; 
        }
        if(type.equals("Easy") && ((emergency.equals("Immediately Needed") || emergency.equals("Highly Needed")))){
            System.out.println("Easy post emergency validation failed.");
            return false;
        }
        if((type.equals("Very Difficult") || type.equals("Difficult")) && emergency.equals("Ordianary")){
            System.out.println("Invalid emergency level for type " + type + ".");
            return false;
        }
        return true; 
    }


    //  addComment function 
    public boolean addComment(String postIDOrTitle, String commentText) {
        if(!checkExistingPost(postIDOrTitle)){
            System.out.println("Invalid postID or title\n");
            return false;
        }

        if (validateComment(commentText)) {
            postComments.add(commentText);

            // Write the comments information to a TXT file
            try (FileWriter writer = new FileWriter("comments.txt", true)) {
                writer.write("Post ID: " + postID + "\n");
                for (String comment : postComments) {
                    writer.write("Comment: " + comment + "\n");
                }
                writer.write("\n");
                System.out.println("Comment written to comments.txt");
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Unable to write to file
            }
            return true;
        } else {
            System.out.println("Failed to add comment. Invalid comment text\n");
            return false;
        }
    }

    //Check existing posts before entering comment
    private boolean checkExistingPost(String input){
        try (BufferedReader reader = new BufferedReader(new FileReader("post.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.startsWith("Post ID: ") && line.substring(9).trim().equals(input)){
                    return true;
                }
                if(line.startsWith("Title: ") && line.substring(7).trim().equalsIgnoreCase(input)){
                    return true; 
                }
            }
        }catch(IOException e){
            e.printStackTrace();;
        }

        return false; 
    }

    private boolean validateComment(String commentText){
        // Condition 1: Validate comment text
        String[] words = commentText.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("Comment text validation failed."); ;
            return false;
        }

        // Condition 2: Validate the number of comments
        if ((postType.equals("Easy") || postEmergency.equals("Ordinary")) && postComments.size() >= 3) {
            System.out.println("Easy/Ordinary post comments count validation failed.");
            return false;
        }
        if (postComments.size() >= 5) {
            System.out.println("Post comments count validation failed.");
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        // resetIDCounter(); sometimes use to make sure the postID counter return to 1
        Scanner scanner = new Scanner(System.in);

        int postID; 
        String postTitle, postBody, postType, postEmergency;
        List<String> postTags = new ArrayList<>();
        Post post = new Post(0, null, null, null, null, null);

        //validate post ID
        while(true){
            System.out.println("Enter Post ID, make sure it is unique: ");
            postID = scanner.nextInt();
            scanner.nextLine();
            if(post.isValidPostID(postID)){
                break;
            }
            System.out.println("Invalid Post ID. Please enter a valid ID.");
        }


        // Validate post title
        while (true) {
            System.out.println("Enter Post Title:");
            postTitle = scanner.nextLine();
            // post = new Post(postTitle, "", new ArrayList<>(), "", "");
            if (post.isValidTitle(postTitle)) {
                break;
            }
            System.out.println("Invalid Post Title. Please enter a valid title.");
        }

        // Validate post body
        while (true) {
            System.out.println("Enter Post Body:");
            postBody = scanner.nextLine();
            // post = new Post(postID, postTitle, postBody, new ArrayList<>(), "", "");
            if (post.isValidBody(postBody)) {
                break;
            }
            System.out.println("Invalid post Body. Please enter a valid body.");
        }

        // Validate post tags
        while (true) {
            System.out.println("Enter number of tags (2-5):");
            int numTags = Integer.parseInt(scanner.nextLine());
            postTags = new ArrayList<>();
            for (int i = 0; i < numTags; i++) {
                System.out.println("Enter tag " + (i + 1) + ":");
                postTags.add(scanner.nextLine());
            }
            // post = new Post(postTitle, postBody, postTags, "", "");
            if (post.isValidTags(postTags)) {
                break;
            }
            System.out.println("Invalid post tags. Please enter valid tags.");
        }

        // Validate post type
        while (true) {
            System.out.println("Enter Post Type (Very Difficult, Difficult, Easy):");
            postType = scanner.nextLine();
            // post = new Post(postTitle, postBody, postTags, postType, "");
            if (post.isValidType(postType, postBody, postTags)) {
                break;
            }
            System.out.println("Invalid post Type. Please enter a valid Type.");
        }

        // Validate post emergency
        while (true) {
            System.out.println("Enter Post Emergency (Immediately Needed, Highly Needed, Ordinary):");
            postEmergency = scanner.nextLine();
            // post = new Post(postTitle, postBody, postTags, postType, postEmergency);
            if (post.isValidEmergency(postEmergency, postType)) {
                break;
            }
            System.out.println("Invalid post Urgency. Please enter a valid Urgency.");
        }

        post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        if (success) {
            System.out.println("Post added successfully!");
        } else {
            System.out.println("Failed to add post. Please check the conditions and try again.");
        }

        System.out.println("Do you want to add a comment? (yes/no)");
        String addComment = scanner.nextLine();
        while (addComment.equalsIgnoreCase("yes")) {
            System.out.println("Enter Post ID or Title you want to comment on: ");
            String postIDOrTitle =  scanner.nextLine();
            String commentText;
            boolean commentSuccess;
            do {
                System.out.println("Enter Comment:");
                commentText = scanner.nextLine();
                commentSuccess = post.addComment(postIDOrTitle, commentText);
                if(!commentSuccess && (postType.equals("Easy") || postEmergency.equals("Ordinary")) && post.postComments.size() >= 3){
                    System.out.println("Max comments reached for Easy/Ordinary post. Exiting...");
                    scanner.close();
                    return;
                }
                if(post.postComments.size() > 5){
                    System.err.println("Max comments reached for " + post.postTitle + ". Exiting...");
                    scanner.close();
                    return;
                }
            } while (!commentSuccess);
            System.out.println("Comment added successfully!");

            System.out.println("Do you want to add another comment? (yes/no)");
            addComment = scanner.nextLine();
        }

        scanner.close();
    }
}
