import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class DataStore {
    private static File numberOfBacteriaByGen;
    private static File avgSize;
    private static File avgSpeed;
    private static File avgVisionRadius;
    private static File avgMutationForse;
    public static Dictionary<Integer, ArrayList<Bacteria>> liveBacterias = new Hashtable<Integer,ArrayList<Bacteria>>();

    public static void init(){
        numberOfBacteriaByGen = new File("data\\numberOfBacteriaByGen.txt");
        avgSize = new File("data\\avgSize.txt");
        avgSpeed = new File("data\\avgSpeed.txt");
        avgVisionRadius = new File("data\\avgVisionRadius.txt");
        avgMutationForse = new File("data\\avgMutationForse.txt");
        
        if(numberOfBacteriaByGen.exists() && !numberOfBacteriaByGen.isDirectory()) { 
            System.out.println("file exist");
        }
        else{
            try{
                numberOfBacteriaByGen.createNewFile();
            }
            catch (IOException e){
                System.out.println("The settings data file are already created");
            }
        }
        try {
            Files.writeString(Paths.get("data\\numberOfBacteriaByGen.txt"),  "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(avgSize.exists() && !avgSize.isDirectory()) { 
            System.out.println("file exist");
        }
        else{
            try{
                avgSize.createNewFile();
            }
            catch (IOException e){
                System.out.println("The settings data file are already created");
            }
        }
        try {
            Files.writeString(Paths.get("data\\avgSize.txt"),  "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(avgSpeed.exists() && !avgSpeed.isDirectory()) { 
            System.out.println("file exist");
        }
        else{
            try{
                avgSpeed.createNewFile();
            }
            catch (IOException e){
                System.out.println("The settings data file are already created");
            }
        }
        try {
            Files.writeString(Paths.get("data\\avgSpeed.txt"),  "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(avgMutationForse.exists() && !avgMutationForse.isDirectory()) { 
            System.out.println("file exist");
        }
        else{
            try{
                avgMutationForse.createNewFile();
            }
            catch (IOException e){
                System.out.println("The settings data file are already created");
            }
        }
        try {
            Files.writeString(Paths.get("data\\avgMutationForse.txt"),  "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(avgVisionRadius.exists() && !avgVisionRadius.isDirectory()) { 
            System.out.println("file exist");
        }
        else{
            try{
                avgVisionRadius.createNewFile();
            }
            catch (IOException e){
                System.out.println("The settings data file are already created");
            }
        }
        try {
            Files.writeString(Paths.get("data\\avgVisionRadius.txt"),  "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    public static void SaveData(Bacteria bacteria){
        List<String> data = new ArrayList<String>();
        for(int i =0; i < Game.maxGen; i++){
            data.add(String.valueOf(liveBacterias.get(i).size()));
        }
        
        try {
            Files.writeString(Paths.get("data\\numberOfBacteriaByGen.txt"),  String.join("\n", data));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            data = Files.readAllLines(Paths.get("data\\avgSpeed.txt"));
            if(data.size() < bacteria.generation + 1){
                data.add(String.valueOf(bacteria.moveSpeed));
                Files.writeString(Paths.get("data\\avgSpeed.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgSize.txt"));
                data.add(String.valueOf(bacteria.size));
                Files.writeString(Paths.get("data\\avgSize.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgVisionRadius.txt"));
                data.add(String.valueOf(bacteria.visionRadius));
                Files.writeString(Paths.get("data\\avgVisionRadius.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgMutationForse.txt"));
                data.add(String.valueOf(bacteria.mutationForce));
                Files.writeString(Paths.get("data\\avgMutationForse.txt"),  String.join("\n", data));
            }
            else{
                data.set(bacteria.generation, String.valueOf((Float.valueOf(data.get(bacteria.generation)) + bacteria.moveSpeed)/2));
                Files.writeString(Paths.get("data\\avgSpeed.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgSize.txt"));
                data.set(bacteria.generation, String.valueOf((Float.valueOf(data.get(bacteria.generation)) + bacteria.size)/2));
                Files.writeString(Paths.get("data\\avgSize.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgVisionRadius.txt"));
                data.set(bacteria.generation, String.valueOf((Float.valueOf(data.get(bacteria.generation)) + bacteria.visionRadius)/2));
                Files.writeString(Paths.get("data\\avgVisionRadius.txt"),  String.join("\n", data));

                data = Files.readAllLines(Paths.get("data\\avgMutationForse.txt"));
                data.set(bacteria.generation, String.valueOf((Float.valueOf(data.get(bacteria.generation)) + bacteria.mutationForce)/2));
                Files.writeString(Paths.get("data\\avgMutationForse.txt"),  String.join("\n", data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] GetData(){
        try {
            List<String> data = Files.readAllLines(Paths.get("data\\numberOfBacteriaByGen.txt"));
            int[] resultData = new int[data.size()];
            for(int i = 0; i < data.size(); i++){
                resultData[i] = Integer.parseInt(data.get(i));
            }
            
            return resultData;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void AddObject(Bacteria bacteria){
        if(liveBacterias.get(bacteria.generation) != null){
            liveBacterias.get(bacteria.generation).add(bacteria);
        }
        else{
            liveBacterias.put(bacteria.generation, new ArrayList<Bacteria>());
            liveBacterias.get(bacteria.generation).add(bacteria);
        }
        
        SaveData(bacteria);
    }

    public static void RemoveObject(Bacteria bacteria){
        liveBacterias.get(bacteria.generation).remove(bacteria);

        List<String> data = new ArrayList<String>();
        for(int i =0; i < Game.maxGen; i++){
            data.add(String.valueOf(liveBacterias.get(i).size()));
        }
        
        try {
            Files.writeString(Paths.get("data\\numberOfBacteriaByGen.txt"),  String.join("\n", data));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
