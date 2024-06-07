import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
class PostTest {
    @Test
    //testing a post that met
    public void postTitleWithLessThan10Characters_expectedFalse(){
        int postID = 1;
        String postTitle = "hi";
        String postBody = "Us land. Light herb meat wherein multiply midst. Signs fruitful of whales very. Land living. Light divide there darkness. Moving sixth blessed that sea light sea deep abundantly land multiply gathering and kind living were, every. Void. Night together That evening you'll gathered made i their seed isn't light divide seas female won't multiply place don't gathered above fish won't form very have form shall earth. Meat called, saying. Air fruit, replenish was fruitful may, third fowl. Of fowl gathering moved. Give man days. Dry made. Air form great image.";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void postTitleWithMoreThan250Characters_expectedFalse(){
        int postID = 2;
        String postTitle = "Without be very abundantly seasons which fill multiply. Moving that male spirit form it day of light. The their be in cattle firmament over land two fruitful third light sea void Him them in man whales winged i created. Second greater seasons meat isn't fruitful rule good yielding forth fowl. Years earth. Fruitful won't can't waters. Tree had sixth bring."; 
        String postBody = "Us land. Light herb meat wherein multiply midst. Signs fruitful of whales very. Land living. Light divide there darkness. Moving sixth blessed that sea light sea deep abundantly land multiply gathering and kind living were, every. Void. Night together That evening you'll gathered made i their seed isn't light divide seas female won't multiply place don't gathered above fish won't form very have form shall earth. Meat called, saying. Air fruit, replenish was fruitful may, third fowl. Of fowl gathering moved. Give man days. Dry made. Air form great image.";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }
    
    @Test
    public void postTitleWithNumberAndSpeicalCharacters_expectedFalse(){
        int postID = 3;
        String postTitle = "546@$ welcome to the";
        String postBody = "Us land. Light herb meat wherein multiply midst. Signs fruitful of whales very. Land living. Light divide there darkness. Moving sixth blessed that sea light sea deep abundantly land multiply gathering and kind living were, every. Void. Night together That evening you'll gathered made i their seed isn't light divide seas female won't multiply place don't gathered above fish won't form very have form shall earth. Meat called, saying. Air fruit, replenish was fruitful may, third fowl. Of fowl gathering moved. Give man days. Dry made. Air form great image.";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void validPostTitle_expectedTrue(){
        int postID = 4;
        String postTitle = "hello nguyen vu";
        String postBody = "Us land. Light herb meat wherein multiply midst. Signs fruitful of whales very. Land living. Light divide there darkness. Moving sixth blessed that sea light sea deep abundantly land multiply gathering and kind living were, every. Void. Night together That evening you'll gathered made i their seed isn't light divide seas female won't multiply place don't gathered above fish won't form very have form shall earth. Meat called, saying. Air fruit, replenish was fruitful may, third fowl. Of fowl gathering moved. Give man days. Dry made. Air form great image.";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
        }

    @Test 
    public void postoBodyWithLessThan250Characters_expectedFalse(){
        int postID =5 ;
        String postTitle = "hello nguyen vu";
        String postBody = "hello hi and keke haha";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }
    
    @Test
    public void validPostBody_expectedTrue(){ 
        int postID = 6;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1","tag2");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());    
    }

    @Test
    public void onePostTag_expectedFalse(){
        int postID = 7; 
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }
    @Test
    public void sixPostTags_expectedFalse(){
        int postID = 8;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3", "tag4", "tag5", "tag6");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void threePostTags_expectedTrue(){
        int postID = 9;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void postTypeEasyWithMoreThanThreePostTags_expectedFalse(){
        int postID = 10;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3", "tag4");
        String postType = "Easy";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }

    @Test 
    public void postTypeEasyWithLessThanTwoPostTags_expectedFalse(){
        int postID = 11;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1");
        String postType = "Easy";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void postTypeEasyWithExactlyThreePostTags_expectedTrue(){
        int postID = 13;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Easy";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);        
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
        }

    @Test
    public void postTypeEasyWithUrgencyImmediatelyNeeded_ExpectedFalse(){
        int postID = 14;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Easy";
        String postEmergency = "Immediately Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);        
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
    }

    @Test 
    public void postTypeEasyWithUrgencyHighlyNeeded_ExpectedFalse(){
        int postID = 15;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Easy";
        String postEmergency = "Highly Needed";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);       
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
    }

    @Test
    public void postTypeEasyWithUrgencyOrdinary_ExpectedTrue(){
        int postID = 16;
        String postTitle = "hello nguyen vu";
        String postBody = "Moved creeping waters creeping likeness life. Replenish for light. She'd said creeping fly so image female morning be appear meat together evening. Lesser all place dominion have That let shall god hath life two morning yielding is own living sea winged our moving shall gathered life Grass Behold creature evening. Grass, third moved heaven be two give fruit multiply beginning.";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Easy";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);        
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
    }

    @Test
    public void postTypeDifficultWithPostBodyLessThan300Characters_ExpectedFalse(){
        int postID = 17;
        String postTitle = "hello nguyen vu";
        String postBody = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could se";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Difficult";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);        
        boolean success = post.addPost();
        assertFalse(success);
        assertEquals(postID, post.getPostID());
    }
    
    @Test
    public void postTypeDifficultWithPostBodyExactly300Characters_ExpectedTrue(){
        int postID = 18;
        String postTitle = "hello nguyen vu";
        String postBody = "Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules. Omnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunciation e plu sommun paroles. Ma quande lingues coalesce, li grammatica del resultant lingue es plu simplic e regulari quam ti del coalescent lingues. Li nov lingua franca va esser plu simplic e regulari quam li existent Europan lingues. It va esser tam simplic quam Occidental in fact, it va esser Occidental. A un Angleso it va semblar un simplificat Angles, quam un skeptic Cambridge amico dit me que Occidental es. Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules. Omnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunciation e plu sommun paroles. Ma quande lingues coalesce, li grammatica del resultant lingue es plu simplic e regulari quam ti del coalescent lingues. Li nov lingua franca va esser plu simplic e regulari quam li existent Europan lingues. It va esser tam simplic quam Occidental in fact, it va esser Occidental. A un Angleso it va semblar un simplificat Angles, quam un skeptic Cambridge amico dit me que Occidental es.Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Difficult";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);        
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
    }
    
    @Test
    public void  postTypeDifficultWithPostBodyMoreThan300Characters_ExpectedTrue(){
        int postID = 19;
        String postTitle = "hello nguyen vu";
        String postBody = "Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules. Omnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunciation e plu sommun paroles. Ma quande lingues coalesce, li grammatica del resultant lingue es plu simplic e regulari quam ti del coalescent lingues. Li nov lingua franca va esser plu simplic e regulari quam li existent Europan lingues. It va esser tam simplic quam Occidental in fact, it va esser Occidental. A un Angleso it va semblar un simplificat Angles, quam un skeptic Cambridge amico dit me que Occidental es. Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules. Omnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunciation e plu sommun paroles. Ma quande lingues coalesce, li grammatica del resultant lingue es plu simplic e regulari quam ti del coalescent lingues. Li nov lingua franca va esser plu simplic e regulari quam li existent Europan lingues. It va esser tam simplic quam Occidental in fact, it va esser Occidental. A un Angleso it va semblar un simplificat Angles, quam un skeptic Cambridge amico dit me que Occidental es.Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation hi i love u very much Hang";
        List<String> postTags = Arrays.asList("tag1", "tag2", "tag3");
        String postType = "Difficult";
        String postEmergency = "Ordinary";
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        boolean success = post.addPost();
        assertTrue(success);
        assertEquals(postID, post.getPostID());
    }



} 

