package main;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class SkillsLoadSaver {

    public void saveToFile(Set<Skill> skills){
        FileOutputStream fOutS;
        ObjectOutputStream oOutS;
        try {
            fOutS = new FileOutputStream("skills.ser");
            oOutS = new ObjectOutputStream(fOutS);
            oOutS.writeObject(skills);
            fOutS.close();
            oOutS.close();
            System.out.println("object saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<Skill> loadFromFile(){
        FileInputStream fInS = null;
        ObjectInputStream oInS = null;
        Set<Skill> skills = new TreeSet<>();

        try {
            fInS = new FileInputStream("skills.ser");
            oInS = new ObjectInputStream(fInS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            skills = (Skills)oInS.readObject();
            oInS.close();
            fInS.close();
            System.out.println("object readed");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return skills;
    }
}
