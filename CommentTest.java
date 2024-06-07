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
        // new File("comments.txt").delete();

        // Initialize a difficult post
        post = new Post(1, "Valid Title", "This is a valid post body that exceeds 250 characters. ".repeat(10), 
                        Arrays.asList("java", "testing"), "Difficult", "Highly Needed");

        // Add the post
        post.addPost();

        // Initialize an easy post
        // easyPost = new Post(2, "Easy Post Title", "This is a valid post body that exceeds 250 characters. ".repeat(10), 
        //                     Arrays.asList("easy", "test"), "Easy", "Ordinary");

        // // Add the easy post
        // easyPost.addPost();
    }

    @Test
    public void addCommentByValidPostID_expectedTrue() {
        boolean result = post.addComment("1", "This is a valid comment.");
        assertTrue(result);
    }

    @Test
    public void addCommentByInvalidPostID_expectedFalse(){
        boolean result = post.addComment("3", "This is a valid comment.");
        assertFalse(result);
    }

    @Test
    public void invalidCommentWithOutUpperCase_expectedFalse() {
        boolean result = post.addComment("1", "this is not a valid comment.");
        assertFalse(result);
    }

    @Test
    public void validCommentWithUpperCase_expectedTrue() {
        boolean result = post.addComment("1", "This is another valid comment.");
        assertTrue(result);
    }

    @Test
    public void commentWithLessThan4Words_expectedFalse() {
        boolean result = post.addComment("1", "Too short.");
        assertFalse(result);
    }

    @Test
    public void commentWithOver10Words_expectedFalse() {
        // easyPost.addComment("2", "This is a valid comment.");
        // easyPost.addComment("2", "This is another valid comment.");
        // easyPost.addComment("2", "Yet another valid comment.");

        boolean result = post.addComment("1", "This comment should exceed the limit of the word count that is giving.");
        assertFalse(result);
    }

    @Test
    public void commentWithValidLength_expectedTrue(){
        boolean result = post.addComment("1", "This is a valid comment length.");
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
        assertFalse(result);
    }

    @Test
    public void validCommentLimitForPostTypeDifficult_expectedTrue(){
        Post post = new Post(2, "Test Title", "This is a sufficiently long post body for testing purposes.", Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");

        post.addPost();
        post.addComment("2", "This is the first comment.");
        post.addComment("2", "This is the second comment.");
        post.addComment("2", "This is the third comment.");
     

        boolean result = post.addComment("2", "This is the fourth comment.");
        assertTrue(result);
    }
}
