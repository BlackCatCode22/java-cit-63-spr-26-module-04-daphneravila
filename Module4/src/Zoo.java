import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Zoo {
    public static void main(String[] args) {

        ArrayList<Animal> animalList = new ArrayList<>();
// This hashmap counts all the species together.
        HashMap<String, Integer> speciesCount = new HashMap<>();
        ArrayList<String> hyenaNames = new ArrayList<>();
        ArrayList<String> lionNames = new ArrayList<>();
        ArrayList<String> tigerNames = new ArrayList<>();
        ArrayList<String> bearNames = new ArrayList<>();

        try {
        File nameFile = new File("animalNames.txt");
        Scanner nameReader = new Scanner(nameFile);
        String currentSpeciesName = "";
// This loops through the animalNames.txt file, which identifies species and stores names into the right ArrayLists.
        while (nameReader.hasNextLine()) {
            String line = nameReader.nextLine().trim();
            if (line.contains("Hyena")){
                currentSpeciesName = "hyena";
            } else if (line.contains("Lion")) {
                currentSpeciesName = "lion";
            } else if (line.contains("Tiger")) {
                currentSpeciesName = "tiger";
            } else if (line.contains("Bear")) {
                currentSpeciesName = "bear";
            } else if (!line.isEmpty()) {
    String[] names = line.split(",");
    for (String name : names) {
        if (currentSpeciesName.equals("hyena")) {
            hyenaNames.add(name);
        } else if (currentSpeciesName.equals("lion")) {
            lionNames.add(name);
    } else if (currentSpeciesName.equals("tiger")) {
            tigerNames.add(name);
        } else if (currentSpeciesName.equals("bear")) {
            bearNames.add(name);
        }
    }
            }
    }
// In this part, I had to go back and redo a lot, because I had put brackets in the wrong spot. It grabs the age and specie.
        nameReader.close();

            File myFile = new File("arrivingAnimals.txt");
            Scanner reader = new Scanner(myFile);
// This checks what kind of animal it's looking at, so it can give it a name.
            while (reader.hasNextLine()) {
                String data = reader.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                String[] commaParts = data.split(",");
                String[] wordParts = commaParts[0].trim().split(" ");

                int age = Integer.parseInt((wordParts[0]));
                String species = wordParts[4].toLowerCase();

                System.out.println("Age: " + age + " | Species: " + species);
                if (species.equals("hyena")) {
                    Hyena newHyena = new Hyena(hyenaNames.remove(0), age);
                    animalList.add(newHyena);
                } else if (species.equals("lion")) {
                    Lion newLion = new Lion(lionNames.remove(0), age);
                    animalList.add(newLion);
                } else if (species.equals("tiger")) {
                    Tiger newTiger = new Tiger(tigerNames.remove(0), age);
                    animalList.add(newTiger);
                } else if (species.equals("bear")) {
                    Bear newBear = new Bear(bearNames.remove(0), age);
                    animalList.add(newBear);
                }
                if (speciesCount.containsKey(species)) {
                    int currentCount = speciesCount.get(species);
                    speciesCount.put(species, currentCount + 1);
                } else {
                    speciesCount.put(species, 1);
                }

            }
// This is the part that actually puts everything together, at least I think so...It prints out all the animal details.
            reader.close();

            FileWriter writer = new FileWriter("newAnimals.txt");
            writer.write("--- Final Zoo Report ---\n");
            writer.write("Total Animals: " + animalList.size() + "\n\n");
            writer.write("--- Animal Details ---\n");
            for (Animal animal : animalList) {
                writer.write("Name: " + animal.getName() + " | Species: " + animal.getSpecies() + " | Age: " + animal.getAge() + "" + "\n");
            }
            writer.write("\n --- Species Counts ---\n");

            for (String key: speciesCount.keySet()) {
                writer.write(key + ": " + speciesCount.get(key)+ "\n");
            }
            writer.close();
            System.out.println("Report successfully created!");
        } catch (Exception e) {
            System.out.println("Could not find the animal file!");
            e.printStackTrace();
        }
    }
}