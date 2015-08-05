import java.util.*;
import java.io.Reader;

/**
 * This class supplies a bare minimum model implementation,
 * basically allowing views to be added, removed, and
 * notified of messages, errors, and other model information.
 * 
 * @author Owen Astrachan
 *
 */
public abstract class AbstractModel implements IModel
{
    private ArrayList<IView> myViews;
    
    public AbstractModel(){
        myViews = new ArrayList<IView>();
    }
    
    /**
     * Adds a view to this model, all views are notified when
     * there is communication between a model and its views.
     * @param view is added to this model
     */
    public void addView(IView view){
        myViews.add(view);
    }
    
    /**
     * Remove a view from this model.
     * @param view to be removed, if not present nothing changes
     */
    public void removeView(IView view){
        myViews.remove(view);
    }
    
    /**
     * Clear all views.
     */
    public void clear(){
        for(IView view : myViews){
            view.clear();
        } 
    }
    
    /**
     * Notify all views by sending a string to each view's update method.
     * @param s is the string sent to each view
     */
    public void notifyViews(String s){
        for(IView view : myViews){
            view.update(s);
        }
    }
    
    /**
     * Notify all views that an error has occurred by calling showError.
     * @param s is the message processed by each view's showError method
     */
    public void showViewsError(String s){
        for(IView view : myViews){
            view.showError(s);
        }
    }
    
    /**
     * Send a message to each view by calling showMessage with a String.
     * @param is is the message sent to each view.
     */
    public void messageViews(String s){
        for(IView view : myViews){
            view.showMessage(s);
        }
    }
    
    /**
     * Initialize a model by reading from the provided parameter.
     * @param r is the source used for initialization
     */
    public void initialize(Reader r){
        initialize(new Scanner(r));
    }
}
