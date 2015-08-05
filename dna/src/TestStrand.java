import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;


/**
 * Class for running JUNit tests with different implementations of IDnaStrand. To use
 * a different implementation alter the method <code>getNewStrand</code> since that method
 * is called by all JUnit tests to create the IDnaStrand objects being tested.
 * 
 * @author ola
 * @date January 2008, modifed and commented in September 2008
 * @version 2.0, January 2009, added splice testing
 */

public class TestStrand {

    private static String[] strs = { "aggtccg", 
    	                                 "aaagggtttcccaaagggtttccc",
                                     "a", "g", "", 
                                     "aggtccgttccggttaaggagagagagagagttt" };

    
    /**
     * Return a strand to test by other JUnit tests
     * @param s is the string modeled by an IDnaStrand implementation
     * @return an IDnaStrand object for testing in this JUnit testing class.
     */
    public IDnaStrand getNewStrand(String s) {
    	//return new LinkStrand(s);
        return new SimpleStrand(s);
    }

    @Test
    public void testReverse(){
        for(String s : strs){
            IDnaStrand strand = getNewStrand(s);
            IDnaStrand rev = strand.reverse();
            String rs = new StringBuilder(s).reverse().toString();
            assertEquals("reverse fail for "+s,rev.toString(),rs);
        }
    }
    
    @Test
    public void testSplice(){
    	  String r = "gat";
    	  String sp = "xxyyzz";
          String[] strands = { "ttgatcc", "tcgatgatgattc", 
                               "tcgatctgatttccgatcc", "gat",
        		                   "gatctgatctgat", "gtacc","gatgatgat" };
          String[] recombs = { "ttxxyyzzcc", "tcxxyyzzxxyyzzxxyyzztc", 
                               "tcxxyyzzctxxyyzzttccxxyyzzcc", "xxyyzz", 
                               "xxyyzzctxxyyzzctxxyyzz","","xxyyzzxxyyzzxxyyzz" };
        
          for (int k = 0; k < strands.length; k++) {
              IDnaStrand str = getNewStrand(strands[k]);
              String bef = str.toString();
              IDnaStrand rec = str.cutAndSplice(r,sp);
              assertEquals("splice return fail at " + k, recombs[k], rec.toString());
              assertEquals("self alter fail " + k, str.toString(), bef);
          }
    }


    @Test
    public void testInitialize() {

        for (String s : strs) {
            IDnaStrand str = getNewStrand("");
            str.initializeFrom(s);
            assertEquals("init lengths differ for " + s + " : ", s.length(),
                    str.size());
            assertEquals("init strings differ ", s, str.toString());
        }
    }

    @Test
    public void testSize() {
        for (String s : strs) {
            IDnaStrand str = getNewStrand(s);
            assertEquals("construct lengths differ: ", s.length(), str.size());
        }
    }

    @Test
    public void testToString() {
        for (String s : strs) {
            IDnaStrand str = getNewStrand(s);
            assertEquals("toString differs: ", s, str.toString());
            assertEquals("toString size fail "+s,s.length(), str.size());
        }
    }

    @Test
    public void testAppend() {
        String app = "gggcccaaatttgggcccaaattt";
        for (String s : strs) {
            IDnaStrand str = getNewStrand(s);
            str.append(app);
            assertEquals("append fail: ", s + app, str.toString());
            assertEquals("append size fail,"+str,s.length()+app.length(),str.size());
        }
    }
    
    @Test
    public void testDoubleAppend() {
        String a = "agct";
        IDnaStrand str = getNewStrand(a);
        str.append(a);
        str.append(a);
        String trip = a+a+a;
        assertEquals("double append fail",trip,str.toString());
    }

    @Test
    public void testNodeList() {
    	IDnaStrand checkType = getNewStrand("");
    	if (!(checkType instanceof LinkStrand))
    		return;
		ArrayList<String> sol = new ArrayList<String>();
		sol.add("actg");
		LinkStrand sub = (LinkStrand) getNewStrand("actg");
		for(String s: strs){
			sol.add(s);
			sub.append(s);
			assertEquals("This test checks if nodeList returns the correct "
					+ "value", sol, sub.nodeList());
		}
    }
}
