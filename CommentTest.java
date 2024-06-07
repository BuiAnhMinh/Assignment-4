import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;

public class CommentTest {
    
    private Post post;
    private Post easyPost;

    @BeforeEach
    public void setup() {
        // Clean up files before each test
        new File("post.txt").delete();
        new File("comments.txt").delete();

        // Initialize a difficult post
        post = new Post(1, "Valid Title", "This is a valid post body that exceeds 250 characters. ".repeat(10), 
                        Arrays.asList("java", "testing"), "Difficult", "Highly Needed");

        // Add the post
        post.addPost();

        // Initialize an easy post
        easyPost = new Post(2, "Easy Post Title", "This is a valid post body that exceeds 250 characters. ".repeat(10), 
                            Arrays.asList("easy", "test"), "Easy", "Ordinary");

        // // Add the easy post
        easyPost.addPost();
    }

    @Test
    public void addCommentByValidPostID_expectedTrue() {
        boolean result = post.addComment("1", "This is a valid comment.");
        post.saveCommentsToFile();
        assertTrue(result);
    }

    @Test
    public void addCommentByInvalidPostID_expectedFalse(){
        boolean result = post.addComment("3", "This is a valid comment.");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void invalidCommentWithOutUpperCase_expectedFalse() {
        boolean result = post.addComment("1", "this is not a valid comment.");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void validCommentWithUpperCase_expectedTrue() {
        boolean result = post.addComment("1", "This is another valid comment.");
        post.saveCommentsToFile();
        assertTrue(result);
    }

    @Test
    public void commentWithLessThan4Words_expectedFalse() {
        boolean result = post.addComment("1", "Too short.");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void commentWithOver10Words_expectedFalse() {
        boolean result = post.addComment("1", "This comment should exceed the limit of the word count that is giving.");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void commentWithValidLength_expectedTrue(){
        boolean result = post.addComment("1", "This is a valid comment length.");
        post.saveCommentsToFile();
        assertTrue(result);
    }

    @Test
    public void exceedingCommentLimitForPostTypeDifficult_expectedFalse(){
        post.addComment("1", "This is the first comment.");
        post.addComment("1", "This is the second comment.");
        post.addComment("1", "This is the third comment.");
        post.addComment("1", "This is the fourth comment.");
        post.addComment("1", "This is the fifth comment.");

        boolean result = post.addComment("1", "This is the sixth comment.");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void validCommentLimitForPostTypeDifficult_expectedTrue(){
        post.addComment("1", "This is the first comment.");
        post.addComment("1", "This is the second comment.");
        post.addComment("1", "This is the third comment.");
        boolean result = post.addComment("1", "This is the fourth comment.");
        post.saveCommentsToFile();
        assertTrue(result);
    }

    @Test
    public void exceedingCommentLimitForEasyPost_expectedFalse(){
        easyPost.addComment("2", "This is the first comment.");
        easyPost.addComment("2", "This is the second comment.");
        easyPost.addComment("2", "This is the third comment.");
        boolean result = easyPost.addComment("2", "This is the fourth comment.");
        easyPost.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void validCommentLimitForEasyPost_expectedTrue(){
        easyPost.addComment("2", "This is the first comment.");
        boolean result = easyPost.addComment("2", "This is the second comment.");
        easyPost.saveCommentsToFile();
        assertTrue(result);
    }

    @Test
    public void addCommentWithInvalidPostTitle_expectedFalse(){
        post.addComment("Valid title", "This is the valid first comment");
        boolean result = post.addComment("Invalid title", "This is a valid second comment");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test
    public void addCommentWithValidPostTitle_expecteTrue(){
        post.addComment("Valid title",  "This is the valid first comment");
        boolean result = post.addComment("Valid title", "This is the valid second comment");
        post.saveCommentsToFile();
        assertTrue(result);
    }

    @Test 
    public void addDuplicateComment_expectedFalse(){
        post.addComment("1", "This is a valid comment");
        boolean result = post.addComment("1", "This is a valid comment");
        post.saveCommentsToFile();
        assertFalse(result);
    }

    @Test 
    public void addDifferentComment_expectedTrue(){
        post.addComment("1", "This is a valid comment");
        boolean result = post.addComment("1", "This is another valid comment");
        post.saveCommentsToFile();
        assertTrue(result);
    }
}
