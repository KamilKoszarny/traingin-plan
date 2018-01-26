package skills;

import java.util.ArrayList;
import java.util.Date;

public enum Skill {

//o layer
JAVA ("Java", 0, null, 8, 1),
    //1st layer
    LANGUAGE ("Language", 1, JAVA, 12, 1),
        //2nd layer
        BASICS("Basics", 2, LANGUAGE, 10, 1),
            //3rd layer
            LANG_BASICS("Language basics", 3, BASICS, 11, 1),
            OOP_IN_JAVA("OOP in Java", 3, BASICS, 12, 1),
            CLASSES("Classes", 3, BASICS, 7, 1),
            GUI("GUI", 3, BASICS, 5, 1.2),
        ADVANCED("Advanced", 2, LANGUAGE, 9, 1),
            PATTERNS("Design patterns", 3, ADVANCED, 10, 1),
            GENERICS("Generics", 3, ADVANCED, 7, 1),
            JAVA8("Java 8", 3, ADVANCED, 7, 1),
            COLLECTIONS("Collections", 3, ADVANCED, 7, 1.2),
            ALGORITHMS("Algorithms", 3, ADVANCED, 8, 1.5),
        COMMERCIAL("Commercial", 2, LANGUAGE, 8, 1),
            JEE("JEE", 3, COMMERCIAL, 7, 1),
                EJB("EJB", 4, JEE, 5, 1),
                J2EE("J2EE", 4, JEE, 12, 1),
                SERVLETS("Servlets", 4, JEE, 7, 1),
            ANT("Ant", 3, COMMERCIAL, 5, 1),
            JMS("JMS", 3, COMMERCIAL, 6, 1),
            REST("REST", 3, COMMERCIAL, 6, 1),
                REST_ASSURED("REST Assured", 4, REST, 4, 1),
            Graph_QL("GraphQL", 3, COMMERCIAL, 3, 1),
            JPA("JPA", 3, COMMERCIAL, 4, 1),

    FRAMEWORKS ("Frameworks", 1, JAVA, 8, 1),
        SPRING("Spring", 2, FRAMEWORKS, 7, 1),
            SPRING_BOOT("Spring Boot", 3, SPRING, 7, 1),
            SPRING_WEBFLOW("Spring Webflow", 3, SPRING, 5, 1),
            SPRING_WEBSERVICES("Spring WebServices", 3, SPRING, 6, 1),
            SPRING4("Spring 4", 3, SPRING, 6, 1),
        HIBERNATE("Hibernate", 2, FRAMEWORKS, 6, 1),
        JSF("JSF", 2, FRAMEWORKS, 5, 1),
        STRUTS("Struts", 2, FRAMEWORKS, 4, 1),
        ANDROID("Android", 2, FRAMEWORKS, 6, 1),

    TOOLS ("Tools", 1, JAVA, 6, 3),
        VERSION_CONTROL("Version Control", 2, TOOLS, 7, 3),
            GIT("Git", 3, VERSION_CONTROL, 9, 3),
                GIT_FLOW("GitFlow", 4, GIT, 4, 3),
                BITBUCKET("Bitbucket", 4, GIT, 5, 3),
                GIT_LAB("GitLab", 4, GIT, 5, 3),
                GIT_HUB("GitHub", 4, GIT, 5, 3),
            SVN("SVN", 3, VERSION_CONTROL, 4, 3),
        PROJECT_MANAGEMENT("Project Management", 2, TOOLS, 6, 5),
            MAVEN("Maven", 3, PROJECT_MANAGEMENT, 7, 5),
            JIRA("JIRA", 3, PROJECT_MANAGEMENT, 6, 5),
            REDMINE("Redmine", 3, PROJECT_MANAGEMENT, 6, 5),
            CONFLUENCE("Confluence", 3, PROJECT_MANAGEMENT, 5, 5),
        IDE("IDE", 2, TOOLS, 8, 3),
            INTELLIJ("IntelliJ", 3, IDE, 9, 3),
            ECLIPSE("Eclipse", 3, IDE, 3, 3),
            NETBEANS("NetBeans", 3, IDE, 6, 3),
            NOTEPAD("Notepad++", 3, IDE, 5, 10),
        AUTOMATION("Automation", 2, TOOLS, 6, 1),
            JENKINS("Jenkins", 3, AUTOMATION, 6, 1),
            GRADLE("Gradle", 3, AUTOMATION, 7, 1),
            DOCKER("Docker", 3, AUTOMATION, 8, 1),
            BAMBOO("Bamboo", 3, AUTOMATION, 3, 1),
            HUDSON("Hudson", 3, AUTOMATION, 4, 1),
            KAFKA("Kafka", 3, AUTOMATION, 5, 1),
            POSTMAN("Postman", 3, AUTOMATION, 4, 1),
        OTHER_TOOLS("Other tools", 2, TOOLS, 4, 1),
            WEBLOGIC("WebLogic", 3, OTHER_TOOLS, 6, 1),
            ELASTICSEARCH("Elasticsearch", 3, OTHER_TOOLS, 8, 1),
                KIBANA("Kibana", 4, ELASTICSEARCH, 7, 1),
            LOGSTASH("Logstash", 3, OTHER_TOOLS, 5, 1),

    TESTS ("Tests", 1, JAVA, 6, 1),
        JUNIT("JUnit", 2, TESTS, 8, 1),
            TEST_NG("TestNG", 3, JUNIT, 7, 1),
        HAMCREST("Hamcrest", 2, TESTS, 6, 1),
        MOCKITO("Mockito", 2, TESTS, 6, 1),
        JMETER("JMeter", 2, TESTS, 6, 1),

    DATABASES ("Data Bases", 1, JAVA, 7, 1),
        SQL("SQL", 2, DATABASES, 6, 1),
            MY_SQL("MySQL", 3, SQL, 6, 2),
            POSTGRE_SQL("PostgreSQL", 3, SQL, 8, 1),
        ORACLE("Oracle", 2, DATABASES, 8, 1),
        JDBC("JDBC", 2, DATABASES, 7, 1),
            MY_BATIS("MyBatis", 3, JDBC, 6, 1),
        MONGO("MongoDB", 2, DATABASES, 5, 1),

    SERVERS ("Servers", 1, JAVA, 7, 1),
        TOMCAT("TomCat", 2, SERVERS, 6, 1),
        APACHE("Apache", 2, SERVERS, 6, 1),
            APACHE_SERVICEMIX("Apache ServiceMix", 3, APACHE, 5, 1),

    CONCEPTS("Concepts", 1, JAVA, 5, 1),
        HARD_CONCEPTS("Hard Concepts", 2, CONCEPTS, 8, 1),
            OOP("OOP", 3, HARD_CONCEPTS, 8, 1),
            CLEAN_CODE("Clean Code", 3, HARD_CONCEPTS, 7, 1),
            MACHINE_LEARNING("Machine learning", 3, HARD_CONCEPTS, 6, 1),
            FUNCTIONAL_PROGRAMMING("Functional Programming", 3, HARD_CONCEPTS, 4, 1),
            WEB_SERVICES("WebServices", 3, HARD_CONCEPTS, 5, 1),
            MVC("MVC", 3, HARD_CONCEPTS, 6, 1),
        SOFT_CONCEPTS("Soft", 2, CONCEPTS, 6, 1),
            AGILE("Agile", 3, SOFT_CONCEPTS, 6, 1),
                SCRUM("SCRUM", 4, AGILE, 9, 1),
            SOA("SOA", 3, SOFT_CONCEPTS, 7, 1),
            CONTINUOUS_DELIVERY("Continuous Delivery", 3, SOFT_CONCEPTS, 7, 1),
            CONTINUOUS_INTEGRATION("Continuous Integration", 3, SOFT_CONCEPTS, 8, 1),

    OTHER_LANGUAGES("Other languages", 1, JAVA, 2, 1),
        JS("JavaScript", 2, OTHER_LANGUAGES, 8, 0.8),
            JS_FRAMEWORKS("JS Frameworks", 3, JS, 7, 1),
                ANGULAR("Angular", 4, JS_FRAMEWORKS, 8, 1),
                REACT("React", 4, JS_FRAMEWORKS, 7, 1),
                NODE("Node", 4, JS_FRAMEWORKS, 5, 1),
            JS_TOOLS("JS Tools", 3, JS, 5, 1),
                NPM("npm", 4, JS_TOOLS, 7, 1),
                GWT("GWT", 4, JS_TOOLS, 5, 1),
            JS_OTHER("JS Other", 3, JS, 7, 1),
                JQUERY("JQuery", 4, JS_OTHER, 8, 1),
                TYPESCRIPT("TypeScript", 4, JS_OTHER, 8, 1),
        WEB("Web", 2, OTHER_LANGUAGES, 7, 1),
            HTML("HTML", 3, WEB, 7, 2),
                HTML5("HTML 5", 4, HTML, 8, 2),
                THYMELEAF("Thymeleaf", 4, HTML, 6, 1),
                JSP("JSP", 4, HTML, 5, 1),
            CSS("CSS", 3, WEB, 6, 2),
                RWD("RWD", 4, CSS, 5, 1),
            PHP("PHP", 3, WEB, 7, 1),
            CMS("CMS", 3, WEB, 4, 1),
            XML("XML", 3, WEB, 5, 2),
                SOAP("SOAP", 4, XML, 5, 1),
        OTHER_OTHER_LANGUAGES("Other", 2, OTHER_LANGUAGES, 6, 1),
            PYTHON("Python", 3, OTHER_OTHER_LANGUAGES, 8, 1),
            CPP("C++", 3, OTHER_OTHER_LANGUAGES, 5, 1),
            SCALA("Scala", 3, OTHER_OTHER_LANGUAGES, 6, 1),
            KAREL("KAREL", 3, OTHER_OTHER_LANGUAGES, 2, 1),
            ARDUINO("Arduino", 3, OTHER_OTHER_LANGUAGES, 4, 1),
        SMALL_LANGUAGES("\"Small\" languages", 2, OTHER_LANGUAGES, 5, 1),
            JSON("JSON", 3, SMALL_LANGUAGES, 6, 1),
            UML("UML", 3, SMALL_LANGUAGES, 9, 1),
            MQ("MQ", 3, SMALL_LANGUAGES, 5, 1),

    OTHER("Other skills", 1, JAVA, 3, 1),
        OS("Operating systems", 2, OTHER, 7, 1),
            WINDOWS("Windows", 3, OS, 6, 1),
                CMD("cmd", 4, WINDOWS, 6, 1),
            MAC("Mac", 3, OS, 5, 1),
            LINUX("Linux", 3, OS, 8, 1),
        BIG_DATA("BigData", 2, OTHER, 6, 1),
            MAP_REDUCE("MapReduce", 3, BIG_DATA, 6, 1),
            SPARK("Spark", 3, BIG_DATA, 6, 1),
        MISC("Miscellaneous", 2, OTHER, 7, 1),
            DWH("DWH", 3, MISC, 5, 1),
            SOLR("SOLR", 3, MISC, 5, 1),
            CLOUD("Cloud", 3, MISC, 6, 1),
            GRAFANA("Grafana", 3, MISC, 6, 1),
            SAP_HYBRIS("SAP Hybris", 3, MISC, 4, 1),
            WEBSPHERE("WebSphere", 3, MISC, 4, 1),
            ACTIVEMQ("ActiveMQ", 3, MISC, 4, 1),
            GLASS_FISH("GlassFish", 3, MISC, 4, 1);


    public static final int LEVELS = 4;
    public static final int LAYERS = 5;

    private String name;
    private int layer;
    private Skill superSkill;
    private int impactOnSuperSkill; //1-10
    private ArrayList<Skill> subSkills = new ArrayList<>();
    private double easiness;

    private double points;
    private double bruttoPoints;
    private int minutes;
    public SkillsHistories.SkillHistory skillHistory = new SkillsHistories.SkillHistory(this);
    private double[] reqPoints = new double[LEVELS];
    private int occurrences;


    //add comment field
    Skill(String name, int layer, Skill superSkill, int impactOnSuperSkill, double easiness){
        this.name = name;
        this.layer = layer;
        this.superSkill = superSkill;
        this.impactOnSuperSkill = impactOnSuperSkill;
        this.easiness = easiness;
    }


    //add///////////////////////////////////////////////////////////////////////////////////
    public void addSubSkill(Skill subSkill) {
        subSkills.add(subSkill);
    }

    public void addReqPoints(int offerPoints, int offersCount, ReqLvl reqLvl) {
        reqPoints[reqLvl.ordinal()] += (double) offerPoints /(double)offersCount;
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
    }

    public void addOccurrence() {
        this.occurrences++;
    }

    public void addReqPointsBySuperSkill(ReqLvl reqLvl){
        reqPoints[reqLvl.ordinal()] += superSkill.reqPoints[reqLvl.ordinal()] * impactOnSuperSkill / 10 * (100 - reqPoints[reqLvl.ordinal()])/100 * (0.75 + reqLvl.ordinal()/5.);
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
        if (reqPoints[reqLvl.ordinal()] > 99)
            reqPoints[reqLvl.ordinal()] = 99;
    }

    public void addReqPointsBySubSkills(ReqLvl reqLvl) {
        double pointsFromSubSkills = 0;
        for (Skill subSkill : subSkills) {
            pointsFromSubSkills += subSkill.reqPoints[reqLvl.ordinal()] * subSkill.impactOnSuperSkill / subSkills.size() / 10;
        }
        reqPoints[reqLvl.ordinal()] += pointsFromSubSkills * (100 - reqPoints[reqLvl.ordinal()])/100 * (0.1 + reqLvl.ordinal()/5.);;
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
        if (reqPoints[reqLvl.ordinal()] > 99)
            reqPoints[reqLvl.ordinal()] = 99;
    }


    public void increaseSkill(double points, int minutes, Project project, String comment){

        double addedPoints =  points * (100. - this.points)/100.;
        addPointsAndRest(this, addedPoints, minutes, project, comment);

        Skill skill = this;
        int subSkillsImpact;
        double pointsMultiplicator = 1;
        for (int i = layer; i > 0; i--){
            subSkillsImpact = 0;
            for (Skill subSkill : skill.superSkill.subSkills) {
                subSkillsImpact += subSkill.impactOnSuperSkill;
            }
            pointsMultiplicator *= skill.impactOnSuperSkill / (double) subSkillsImpact * (100. - skill.superSkill.points)/100.;
            addedPoints = points * pointsMultiplicator;
            addPointsAndRest(skill.superSkill, addedPoints, minutes, project, comment);
            skill = skill.superSkill;
        }
    }

    private void addPointsAndRest(Skill skill, double addedPoints, int minutes, Project project, String comment){
        skill.points += addedPoints * skill.easiness;
        skill.bruttoPoints += addedPoints * skill.easiness;
        skill.minutes += minutes;
        skill.skillHistory.addMoment(skill, project, comment);
    }


    public void outdatePoints(){
        if (!skillHistory.isEmpty()) {
            int days = (int)(daysFromLast() * easiness);
            points *= SkillCalculator.pointsPercentLeftByDays(days) / 100;
//            System.out.println("points outdated, days: " + days);
        }
    }

    private int daysFromLast(){
        long d1 = skillHistory.get(skillHistory.size() - 1).date.getTime();
        long d2 = new Date().getTime();
        return (int)((d2-d1)/(1000*60*60*24));
    }

    //get/set////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public int getLayer() {
        return layer;
    }

    public double getPoints() {
        return points;
    }

    public double getBruttoPoints() {
        return bruttoPoints;
    }

    public int getMinutes() {
        return minutes;
    }

    public SkillsHistories.SkillHistory getSkillHistory() {
        return skillHistory;
    }

    public void setSkillHistoryAndPoints(SkillsHistories.SkillHistory skillHistory) {
        this.skillHistory = skillHistory;
        points = skillHistory.getLastPoints();
        bruttoPoints = skillHistory.getLastBruttoPoints();
        minutes = skillHistory.getLastMinutes();
    }

    public int getOccurrences() {
        return occurrences;
    }

    public double getReqPointsForLevel(ReqLvl reqLvl) {
        return reqPoints[reqLvl.ordinal()];
    }

    public double[] getReqPoints(){
        return reqPoints;
    }

    public Skill getSuperSkill() {
        return superSkill;
    }
}