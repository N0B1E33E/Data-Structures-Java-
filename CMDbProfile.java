package extraCredit;

import java.util.NoSuchElementException;

public class CMDbProfile extends PriorityQueue<Integer, String>{

    private String userName = "";
    
    /**
     * This is a constructor that initialize the user name and other fields in priority queue.
     * @param userName
     */
    public CMDbProfile(String userName){
        super();
        this.userName = userName;
    }

    /**
     * setter method for the user name
     * @param userName new user name needed to be assigned 
     */
    public void changeUserName(String userName){
        this.userName = userName;
    }

    /**
     * Add a rating for the specified movie to this profile.
     * @param movie searching key, given movie
     * @param rating new rate needed to be added into the profile.
     * @return true if succeeded in adding. Otherwise false.
     */
    public boolean rate(String movie, int rating){
        if(rating >= 1 && rating <= 10){
            this.add(rating, movie);
            return true;
        }
        return false;
    }
    
    /**
     * Change the rating for the specified movie to this profile.
     * @param movie searching key, given movie
     * @param newRating new rating needed to be changed in the profile.
     * @return True if succeeded in Changing the rating. Otherwise false.
     */
    public boolean changeRating(String movie, int newRating){
        try{
            this.update(newRating, movie);
        }catch(NoSuchElementException e){
            return false;
        }
        if(newRating >= 1 && newRating <= 10){
            try{
                this.update(newRating, movie);
            }catch(NoSuchElementException e){
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * This method takes one string input as the searching key for the move,
     * removes the rating of the movie.
     * @param movie searching key for the movie
     * @return True if succeeded in changing the rating, otherwise false.
     */
    boolean removeRating(String movie){
        try{
            this.poll(movie);
        }catch(NoSuchElementException e){
            return false;
        }
        return true;
    }

    /**
     * This method takes an integer input and returns a string array.
     * Find the first k highest rating movie and return the names of the movie as an array.
     * @param k first key highest rating movie.
     * @return the name of the first k highest movies and returns an array of strings 
     * contains all the names of the movie.
     */
    public String[] favorite(int k){
        return this.peek(k);
    }

    /**
     * This method takes no input value and return the profile information on the screen.
     * @return the information of profile as a string.
     */
    public String profileInformation(){
        return(userName + "(" + this.size() + ")" + " Favourite movie:" + (String)this.peek());
    }

    /**
     * This method takes an Object input and first check if it is in the type of CMDbProfile,
     * and then check if it has the same name of this one.
     * @return true if so, otherwise, return false.
     */
    public boolean equals(Object o){
        if(o instanceof CMDbProfile){
            return ((CMDbProfile) o).userName.equals(this.userName);
        }
        return false;
    }
}