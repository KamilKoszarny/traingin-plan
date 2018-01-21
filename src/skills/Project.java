package skills;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Project implements Serializable{
    private static final long serialVersionUID = 1L;

    public static ProjectsSet projects = new ProjectsSet();
    private String name;

    public Project(String name){
        this.name = name;
//        projects.add(this);
    }

    public String getName() {
        return name;
    }

    public static Project getProjectByName(String name){
        for (Project project: projects) {
            if (project.getName() == name)
                return project;
        }
        return null;
    }

    public static class ProjectsSet extends HashSet<Project>{
        @Override
        public String toString() {
            Iterator<Project> it = iterator();
            if (! it.hasNext())
                return "[no projects]";

            StringBuilder sb = new StringBuilder();
            sb.append("Projects: ").append('[');
            for (;;) {
                Project project = it.next();
                sb.append(project.getName());
                if (! it.hasNext())
                    return sb.append(']').toString();
                sb.append(',').append(' ');
            }
        }

        public ArrayList<String> getNamesList(){
            ArrayList<String> namesList = new ArrayList<>();
            for (Project project: this) {
                namesList.add(project.getName());
            }
            return namesList;
        }
    }
}
