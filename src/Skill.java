public class Skill {

    private String name;
    private int occurencies;
    private int rank;
    private String[] subSkills;
    private String superSkill;





    //get/set////////////////////////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public int getOccurencies() {
        return occurencies;
    }

    public int getRank() {
        return rank;
    }

    public String[] getSubSkills() {
        return subSkills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccurencies(int occurencies) {
        this.occurencies = occurencies;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSubSkills(String[] subSkills) {
        this.subSkills = subSkills;
    }
}
