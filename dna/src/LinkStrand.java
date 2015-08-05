import java.util.ArrayList;


public class LinkStrand implements IDnaStrand {
    
    /**
     * Create a strand representing an empty DNA strand, length of zero.
     */
    public LinkStrand() {
    	//TODO: Implement this method
    }

    /**
     * Create a strand representing s. No error checking is done to 
     * see if s represents valid genomic/DNA data.
     * @param s is the source of cgat data for this strand
     */
    public LinkStrand(String s) {
    	//TODO: Implement this method
    }

    /**
     * Cut this strand at every occurrence of enzyme, essentially replacing
     * every occurrence of enzyme with splicee.
     * @param enzyme is the pattern/strand searched for and replaced
     * @param splicee is the pattern/strand replacing each occurrence of enzyme
     * @return the new strand leaving the original strand unchanged.
     */
    @Override
    public IDnaStrand cutAndSplice(String enzyme, String splicee) {
    	//TODO: Implement this method
    }

    /**
     * Initialize this strand so that it represents the value of source.
     * No error checking is performed.
     * @param source is the source of this enzyme
     */
    @Override
    public void initializeFrom(String source) {
    	//TODO: Implement this method
    }

    /**
     * Returns the number of nucleotides/base-pairs in this strand.
     */
    @Override
    public long size() {
    	//TODO: Implement this method
    }

    /**
     * Returns the sequence of DNA this object represents as a String
     * @return the sequence of DNA this represents
     */
    @Override
    public String toString() {
    	//TODO: Implement this method
    }

    /**
     * Return some string identifying this class.
     * @return a string representing this strand and its characteristics
     */
    @Override
    public String strandInfo() {
    	//TODO: Implement this method
    }

    /**
     * Append a strand of DNA to this strand. If the strand being appended
     * is represented by a LinkStrand object then an efficient append is
     * performed.
     * @param dna is the strand being appended
     */
    @Override
    public IDnaStrand append(IDnaStrand dna) {
    	//TODO: Implement this method
    }

    /**
     * Simply append a strand of dna data to this strand.
     * @param dna is the String appended to this strand
     */
    @Override
    public IDnaStrand append(String dna) {
    	//TODO: Implement this method
    }

    /**
     * Returns an IDnaStrand that is the reverse of this strand,
     * e.g., for "CGAT" returns "TAGC"
     * @return reverse strand
     */
    @Override
    public IDnaStrand reverse(){
    	//TODO: Implement this method
    }

    /**
     * Returns a string that can be printed
     * to reveal information about what this
     * object has encountered as it is manipulated
     * by append and cutAndSplice.
     * @return
     */
    @Override
    public String getStats() {
    	//TODO: Implement this method
    }
    
    /**
     * Returns an ArrayList of Strings corresponding to
     * the nodes in the linked list this class contains.
     * That is, the first value of the list should be the info within
     * the head of the linked list, the second value should be the info
     * within the node the head points to, etc.
     * The ArrayList returned should be generated at the time the method
     * is called. That is, the ArrayList should have a scope of only this
     * method (i.e. not global)
     * @return 
     */
    public ArrayList<String> nodeList(){
    	//TODO: Implement this method    	
    }
}
