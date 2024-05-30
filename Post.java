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
    private static int idCounter = 1; //Simple counter for postID


    public Post(String postTitle, String postBody, List<String> postTags, String postType, String postEmergency) {

        this.postID = idCounter++;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;

        
    }

    public boolean addPost() {
        String validationMessage = isValidPost();
        if (validationMessage.isEmpty()) {
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
        return false; // Post validation failed
    }


    private String isValidPost() {
        StringBuilder validationMessage = new StringBuilder();
        validationMessage.append(isValidTitle(postTitle));
        validationMessage.append(isValidBody(postBody));
        validationMessage.append(isValidTags(postTags));
        validationMessage.append(isValidType(postType, postBody, postTags));
        validationMessage.append(isValidEmergency(postEmergency, postType));
        return validationMessage.toString();
    //     return isValidTitle(postTitle) && isValidBody(postBody) && isValidTags(postTags) && isValidType(postType, postBody, postTags) && isValidEmergency(postEmergency, postType);
    }

    // Condition 1: Validate post title
    private String isValidTitle(String title){
        if(title.length() < 10 || title.length() > 250){
            System.out.println("Post title length validation failed.");
            // return false;
        }
        for(int i = 0; i < 5; i++){
            if (!Character.isLetter(postTitle.charAt(i)) && !Character.isWhitespace(postTitle.charAt(i))) {
                System.out.println("Post title character validation failed.");
                // return false;
            }
        }
        return "";
    }

    //Condition 2: validate post body
    private String isValidBody(String body){
        if (body.length() <= 250){
            return "Post body length validation failed.";
        }
        return "";
    }

    // Condition 3: Validate post tags
    private String isValidTags(List<String>tags){
        if(tags.size() < 2 || tags.size() > 5){
            System.out.println("Post tags count validation failed.");
            // return false;
        }
        for(String tag:tags){
            if(tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                System.out.println("Post tag validation failed: " + tag);
                // return false;
            } 
        }
        return "";
    }

     // Condition 4: Validate post type and its constraints
    private String isValidType(String type, String body, List<String>tags){
        if(!Arrays.asList(postTypes).contains(type)){
            return "Invalid post type. Please enter \"Easy\", \"Difficult\" and \"Very Difficult\": " ;
            // return false;
        }

        if((type.equals("Very Difficult")) || type.equals("Diffucult") && body.length() < 300){
            return "Post body length is too short for type " + type + ". ";
        }
            // return false;
        if(type.equals("easy") && tags.size() > 3){
            return "Too many tags for type Easy. ";
            // return false; 
        }
        return "";
    }

    // Condition 5: Validate post emergency and its constraints
    private String isValidEmergency(String emergency, String type){
        if(!Arrays.asList(postEmergencyTypes).contains(emergency)){
            return "Invalid post urgency. Please enter \"Immediately Needed\", \"Highly Needed\" and \"Ordinary\": " ;
            // return false; 
        }
        if(type.equals("Easy") && ((emergency.equals("Immediately Needed") || emergency.equals("Highly Needed")))){
            return "Easy post emergency validation failed.";
            // return false;
        }
        if((type.equals("Very Difficult") || type.equals("Difficult")) && emergency.equals("Ordianary")){
            return "Invalid emergency level for type " + type + ". ";
            // return false;
        }
        return ""; 
    }
    //  addComment function 
    public boolean addComment(String commentText) {
        String validationMessage = validateComment(commentText);
        if (validationMessage.isEmpty()) {
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
            System.out.println("Failed to add comment. " + validationMessage);
            return false;
        }
    }

    private String validateComment(String commentText){
        // Condition 1: Validate comment text
        String[] words = commentText.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return "Comment text validation failed.";
            // return false;
        }

        // Condition 2: Validate the number of comments
        if ((postType.equals("Easy") || postEmergency.equals("Ordinary")) && postComments.size() >= 3) {
            return"Easy/Ordinary post comments count validation failed.";
            // return false;
        }
        if (postComments.size() >= 5) {
            return "Post comments count validation failed.";
            // return false;
        }
        return "";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String postTitle, postBody, postType, postEmergency;
        List<String> postTags = new ArrayList<>();
        Post post = null;

        // Validate post title
        while (true) {
            System.out.println("Enter Post Title:");
            postTitle = scanner.nextLine();
            post = new Post(postTitle, "", new ArrayList<>(), "", "");
            if (post.isValidTitle(postTitle).isEmpty()) {
                break;
            }
            System.out.println(post.isValidTitle(postTitle));
        }

        // Validate post body
        while (true) {
            System.out.println("Enter Post Body:");
            postBody = scanner.nextLine();
            post = new Post(postTitle, postBody, new ArrayList<>(), "", "");
            if (post.isValidBody(postBody).isEmpty()) {
                break;
            }
            System.out.println(post.isValidBody(postBody));
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
            post = new Post(postTitle, postBody, postTags, "", "");
            if (post.isValidTags(postTags).isEmpty()) {
                break;
            }
            System.out.println(post.isValidTags(postTags));
        }

        // Validate post type
        while (true) {
            System.out.println("Enter Post Type (Very Difficult, Difficult, Easy):");
            postType = scanner.nextLine();
            post = new Post(postTitle, postBody, postTags, postType, "");
            if (post.isValidType(postType, postBody, postTags).isEmpty()) {
                break;
            }
            System.out.println(post.isValidType(postType, postBody, postTags));
        }

        // Validate post emergency
        while (true) {
            System.out.println("Enter Post Emergency (Immediately Needed, Highly Needed, Ordinary):");
            postEmergency = scanner.nextLine();
            post = new Post(postTitle, postBody, postTags, postType, postEmergency);
            if (post.isValidEmergency(postEmergency, postType).isEmpty()) {
                break;
            }
            System.out.println(post.isValidEmergency(postEmergency, postType));
        }

        boolean success = post.addPost();
        if (success) {
            System.out.println("Post added successfully!");
        } else {
            System.out.println("Failed to add post. Please check the conditions and try again.");
        }

        System.out.println("Do you want to add a comment? (yes/no)");
        String addComment = scanner.nextLine();
        while (addComment.equalsIgnoreCase("yes")) {
            String commentText;
            boolean commentSuccess;
            do {
                System.out.println("Enter Comment:");
                commentText = scanner.nextLine();
                commentSuccess = post.addComment(commentText);
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
