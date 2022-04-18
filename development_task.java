/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
Owner of code : Phillip Marais
Email : Phillipmar27@gmail.com
OS : windows
Java version 18
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
import java.util.ArrayList;
import java.io.*;

public class development_task {
    public static void main(String[] args) throws IOException {

       ArrayList<cellPhoneTower> tableCellPhoneTowers = initizializeArraylist();
       int[] givenFrequencies = new int[]{110,111,112,113,114,115};

       setFrequencyToCellphoneTowers(tableCellPhoneTowers,givenFrequencies);
       System.out.println(tableCellPhoneTowers);
    }

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*                            FUNCTIONS                              */
/*                                                                   */
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

    /* 
    @desc - Sets the frequencies of the towers to the most efficient frequency.
    @param - ArrayList of the cell phone towers.
           - Int array of the given frequencies.
    */
    public static void setFrequencyToCellphoneTowers (ArrayList tableCellPhoneTowers, int[] givenFrequencies) {
        
        int frequencyCounter = 0;

        for(int i = 0; i < tableCellPhoneTowers.size(); i++){
            //If CPT has no freq.
            if(((cellPhoneTower) tableCellPhoneTowers.get(i)).getFrequency() == 0){
                //Set freq to frequencyCounter.
                ((cellPhoneTower) tableCellPhoneTowers.get(i)).setFrequency(givenFrequencies[frequencyCounter%givenFrequencies.length]);
                //Find farthest CPT thats not set and sets to same frequency.
                setFarthestCPT(tableCellPhoneTowers, i,givenFrequencies, frequencyCounter%givenFrequencies.length);
                frequencyCounter++;
                //Set the closest CPT's to the remaning frequencies.
                for(int j = 0;j < givenFrequencies.length-1; j++){
                    setclosestThenFartestCPT(tableCellPhoneTowers, i,givenFrequencies, frequencyCounter%givenFrequencies.length);
                    frequencyCounter++;
                }
            }
        }
    }

    /* 
    @desc - Sets the frequencies of the tower closest to given index, that does not have a frequency, to the the next frequency
    and then find its farthest partner and set it to the same.
    @param - ArrayList of the cell phone towers.
           - Int index of the tower looking for closest tower.
           - Int array of the given frequencies.
           - Int of the counter to see what frequency we are setting. 
    */
    public static void setclosestThenFartestCPT(ArrayList tableCellPhoneTowers, int tower, int[] givenFrequencies, int frequencyCounter) {
        
        double minDistance = 100000000000.0;
        int index = 0;

        for(int i = 0; i < tableCellPhoneTowers.size(); i++){
            //If dist between tower and index < minDistance, set distance.
            if(((cellPhoneTower) tableCellPhoneTowers.get(i)).getFrequency() == 0){
                double dist =distance(((cellPhoneTower) tableCellPhoneTowers.get(tower)).getLatitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(tower)).getLongitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(i)).getLatitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(i)).getLongitude());
                if(dist < minDistance){
                    minDistance = dist;
                    index = i;
                }
            //If last tower cant find partner, return.
            } else if( i == tableCellPhoneTowers.size()-1 && minDistance == 100000000000.0){
                return;
            }
        }
        //Set the closest tower to frequency.
        ((cellPhoneTower) tableCellPhoneTowers.get(index)).setFrequency(givenFrequencies[frequencyCounter%givenFrequencies.length]);
        //Set the farthest tower to same frequency.
        setFarthestCPT(tableCellPhoneTowers, index, givenFrequencies, frequencyCounter); 
    }

    /* 
    @desc -Sets the frequencies of the tower farthest to given index, that does not have a frequency, to the same frequency
    @param - ArrayList of the cell phone towers.
           - Int index of the tower looking for closest tower.
           - Int array of the given frequencies.
           - Int of the counter to see what frequency we are setting. 
    */
    public static void setFarthestCPT(ArrayList tableCellPhoneTowers, int tower, int[] givenFrequencies, int frequencyCounter) {
        
        double maxDistance = 0.0;
        int index = 0;

        for(int i = 0; i < tableCellPhoneTowers.size(); i++){
            //If dist between tower and index > max distance ,set distance.
            if(((cellPhoneTower) tableCellPhoneTowers.get(i)).getFrequency() == 0){
                double dist =distance(((cellPhoneTower) tableCellPhoneTowers.get(tower)).getLatitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(tower)).getLongitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(i)).getLatitude(),
                ((cellPhoneTower) tableCellPhoneTowers.get(i)).getLongitude());
                if(dist > maxDistance){
                    maxDistance = dist;
                    index = i;
                }
            //If last tower cant find partner then change tower to next frequency.
            } else if( i == tableCellPhoneTowers.size()-1 && maxDistance == 0.0){
                index = tower;
            }
        }
        ((cellPhoneTower) tableCellPhoneTowers.get(index)).setFrequency(givenFrequencies[frequencyCounter%givenFrequencies.length]);
    }
    /* 
    @desc - Initialize an arraylist of objects that are cellphone towers
    */
    public static ArrayList initizializeArraylist () throws IOException {

         File file = new File(System.getProperty("user.dir")+'\\'+"table.txt");
         BufferedReader br = new BufferedReader(new FileReader(file));
         String st;

         //Created an arraylist of cell phone towers.
         ArrayList<cellPhoneTower> tableCellPhoneTowers = new ArrayList<cellPhoneTower>();
         while ((st = br.readLine()) != null){
             //Add all cellphone towers to arraylist tableCellPhoneTowers.
             tableCellPhoneTowers.add(new cellPhoneTower(
                 st.split("\\s+")[0],
                 Integer.parseInt(st.split("\\s+")[1]),
                 Integer.parseInt(st.split("\\s+")[2]),
                 Double.parseDouble(st.split("\\s+")[3]),
                 Double.parseDouble(st.split("\\s+")[4])));
         }
         return tableCellPhoneTowers;
    }

    /* 
    @desc - Calculates distance between two points with given latitudes and longitudes.
    @param - latitude of first tower
           - longitude of first tower
           - latitude of second tower
           - longitude of second tower
    */
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(degreesTorad(lat1)) * Math.sin(degreesTorad(lat2)) 
        + Math.cos(degreesTorad(lat1)) * Math.cos(degreesTorad(lat2)) * Math.cos(degreesTorad(theta));
        dist = Math.acos(dist);
        dist = radToDegrees(dist);
        dist = dist * 1.609344; // Kilometers
        return (dist);
    }

    /* 
    @desc - Converts degrees to radians.
    */
    private static double degreesTorad(double deg) {
        return (deg * Math.PI / 180.0);
    }
      
    /* 
    @desc - Converts radians to degrees.
    */
    private static double radToDegrees(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*                            OBJECTS                                */
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

class cellPhoneTower {
    String title;
    int easting;
    int northing;
    double longitude;
    double latitude;
    int frequency;

    cellPhoneTower(String A, int B, int C, double D, double E) {
        this.title = A;
        this.easting = B;
        this.northing = C;
        this.longitude = D;
        this.latitude = E;
    }
    //sets Frequency
    void setFrequency(int freq){
        this.frequency = freq;
    }
    //gets Frequency
    int getFrequency(){
        return this.frequency;
    }
    //return Title
    String getTitle () {
        return this.title;
    }
    //return longitude
    double getLongitude () {
        return this.longitude;
    }
    //return latitude
    double getLatitude () {
        return this.latitude;
    }

    @Override
    public String toString() {
    return "[ Cellphone Tower: " + this.title + 
           ", Frequency: " + this.frequency + " ] \n";
    }
}