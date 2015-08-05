import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordNgramTester {
;
    private WordNgram[] myNgrams;
    
    @Before
    public void setUp(){
        String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
        String[] array = str.split("\\s+");
        myNgrams= new WordNgram[array.length-2];
        for(int k=0; k < array.length-2; k++){
            myNgrams[k] = new WordNgram(array,k,3);
        }
    }
    
    @Test
    public void testHashEquals(){
    	assertEquals("hash fail on equals 0,3",myNgrams[0].hashCode(),myNgrams[3].hashCode());
    	assertEquals("hash fail on equals 0,3",myNgrams[0].hashCode(),myNgrams[6].hashCode());
    	assertEquals("hash fail on equals 0,3",myNgrams[1].hashCode(),myNgrams[4].hashCode());
    	assertEquals("hash fail on equals 0,3",myNgrams[2].hashCode(),myNgrams[8].hashCode());
    	assertEquals("hash fail on equals 0,3",myNgrams[2].hashCode(),myNgrams[5].hashCode());
    }
    
    @Test
    public void testEquals(){
       
        assertEquals("fail on 0,3",myNgrams[0].equals(myNgrams[3]),true);
        assertEquals("fail on 0,6",myNgrams[0].equals(myNgrams[6]),true);
        assertEquals("fail on 1,4",myNgrams[1].equals(myNgrams[4]),true);
        assertEquals("fail on 2,5",myNgrams[2].equals(myNgrams[5]),true);
        assertEquals("fail on 2,8",myNgrams[2].equals(myNgrams[8]),true);
        assertEquals("fail on 0,2",myNgrams[0].equals(myNgrams[2]),false);
        assertEquals("fail on 0,4",myNgrams[0].equals(myNgrams[2]),false);
        assertEquals("fail on 2,3",myNgrams[2].equals(myNgrams[3]),false);
        assertEquals("fail no 2,6",myNgrams[2].equals(myNgrams[6]),false);
        assertEquals("fail no 7,8",myNgrams[7].equals(myNgrams[8]),false);
    }
    
    @Test
    public void testHash(){
        Set<Integer> set = new HashSet<Integer>();
        for(WordNgram w : myNgrams) {
            set.add(w.hashCode());
        }
        
        assertTrue("hash code test", set.size() > 9);
    }
    
}
