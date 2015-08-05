
 
public interface IView
{
    /**
     * Display an information message in this view.
     * By convention this should not require
     * user-interaction, i.e., no modal dialog.
     * @param s is the message displayed
     */
    public void showMessage(String s);
    
    /**
     * Display an error message in this view. For GUI views
     * this could pop up an error dialog.
     * @param s is the error message displayed
     */
    public void showError(String s);
    
    /**
     * Clear a view's 'display'
     */
    public void clear();
    
    /**
     * Display s in some way where s isn't a message and isn't an error.
     * Doesn't clear before displaying
     * @param s is to be displayed
     */
    public void update(String s);
}
