package main;

import java.util.ArrayList;

public enum Skill {

//o layer
JAVA ("Java", 0, null, 8),
    //1st layer
    LANGUAGE ("Language", 1, JAVA, 10),
        //2nd layer
        BASICS("Basics", 2, LANGUAGE, 8),
            //3rd layer
            LANG_BASICS("Language basics", 3, BASICS, 5),
            OOP_IN_JAVA("OOP in Java", 3, BASICS, 4),
            CLASSES("Classes", 3, BASICS, 6),
            GUI("GUI", 3, BASICS, 2),
        ADVANCED("Advanced", 2, LANGUAGE, 4),
            PATTERNS("Design patterns", 3, ADVANCED, 9),
            GENERICS("Generics", 3, ADVANCED, 8),
            JAVA8("Java 8", 3, ADVANCED, 6),
            COLLECTIONS("Collections", 3, ADVANCED, 7),
            ALGORITHMS("Algorithms", 3, ADVANCED, 8),
            SERVLETS("Servlets", 3, ADVANCED, 5),
        COMMERCIAL("Commercial", 2, LANGUAGE, 6),
            JEE("JEE", 3, COMMERCIAL, 7),
                EJB("EJB", 4, JEE, 5),
            J2EE("J2EE", 3, COMMERCIAL, 9),
            SERVLET("Servlet", 3, COMMERCIAL, 5),
            ANT("Ant", 3, COMMERCIAL, 5),
            JMS("JMS", 3, COMMERCIAL, 6),
            REST("REST", 3, COMMERCIAL, 6),
                REST_ASSURED("REST Assured", 4, REST, 4),
            Graph_QL("GraphQL", 3, COMMERCIAL, 3),
            JPA("JPA", 3, COMMERCIAL, 4),

    FRAMEWORKS ("Frameworks", 1, JAVA, 8),
        SPRING("Spring", 2, FRAMEWORKS, 7),
            SPRING_BOOT("Spring Boot", 3, SPRING, 7),
            SPRING_WEBFLOW("Spring Webflow", 3, SPRING, 5),
            SPRING_WEBSERVICES("Spring WebServices", 3, SPRING, 6),
            SPRING4("Spring 4", 3, SPRING, 6),
        HIBERNATE("Hibernate", 2, FRAMEWORKS, 6),
        JSF("JSF", 2, FRAMEWORKS, 5),
        STRUTS("Struts", 2, FRAMEWORKS, 4),

    TOOLS ("Tools", 1, JAVA, 5),
        VERSION_CONTROL("Version Control", 2, TOOLS, 7),
            GIT("Git", 3, VERSION_CONTROL, 9),
                GIT_FLOW("GitFlow", 4, GIT, 4),
                BITBUCKET("Bitbucket", 4, GIT, 5),
                GIT_LAB("GitLab", 4, GIT, 5),
                GIT_HUB("GitHub", 4, GIT, 5),
            SVN("SVN", 3, VERSION_CONTROL, 4),
        PROJECT_MANAGEMENT("Project Management", 2, TOOLS, 6),
            MAVEN("Maven", 3, PROJECT_MANAGEMENT, 7),
            JIRA("JIRA", 3, PROJECT_MANAGEMENT, 6),
            REDMINE("Redmine", 3, PROJECT_MANAGEMENT, 6),
            CONFLUENCE("Confluence", 3, PROJECT_MANAGEMENT, 5),
        IDE("IDE", 2, TOOLS, 8),
            INTELLIJ("IntelliJ", 3, IDE, 9),
            ECLIPSE("Eclipse", 3, IDE, 3),
            NETBEANS("NetBeans", 3, IDE, 6),
        AUTOMATION("Automation", 2, TOOLS, 6),
            JENKINS("Jenkins", 3, AUTOMATION, 6),
            GRADLE("Gradle", 3, AUTOMATION, 7),
            DOCKER("Docker", 3, AUTOMATION, 8),
            BAMBOO("Bamboo", 3, AUTOMATION, 3),
            HUDSON("Hudson", 3, AUTOMATION, 4),
            KAFKA("Kafka", 3, AUTOMATION, 5),
            POSTMAN("Postman", 3, AUTOMATION, 4),
        OTHER_TOOLS("Other tools", 2, TOOLS, 4),
            WEBLOGIC("WebLogic", 3, OTHER_TOOLS, 6),
            ELASTICSEARCH("Elasticsearch", 3, OTHER_TOOLS, 8),
                KIBANA("Kibana", 4, ELASTICSEARCH, 7),
            LOGSTASH("Logstash", 3, OTHER_TOOLS, 5),
            TOMCAT("TomCat", 3, OTHER_TOOLS, 5),

    TESTS ("Tests", 1, JAVA, 4),
        JUNIT("JUnit", 2, TESTS, 8),
            TEST_NG("TestNG", 3, JUNIT, 7),
        HAMCREST("Hamcrest", 2, TESTS, 6),
        MOCKITO("Mockito", 2, TESTS, 6),
        JMETER("JMeter", 2, TESTS, 6),

    DATABASES ("Data Bases", 1, JAVA, 4),
        SQL("SQL", 2, DATABASES, 6),
            MY_SQL("MySQL", 3, SQL, 6),
            POSTGRE_SQL("PostgreSQL", 3, SQL, 8),
        ORACLE("Oracle", 2, DATABASES, 8),
        JDBC("JDBC", 2, DATABASES, 7),
            MY_BATIS("MyBatis", 3, JDBC, 6),
        MONGO("MongoDB", 2, DATABASES, 5),

    CONCEPTS("Concepts", 1, JAVA, 3),
        HARD_CONCEPTS("Hard Concepts", 2, CONCEPTS, 8),
            OOP("OOP", 3, HARD_CONCEPTS, 8),
            CLEAN_CODE("Clean Code", 3, HARD_CONCEPTS, 7),
            MACHINE_LEARNING("Machine learning", 3, HARD_CONCEPTS, 6),
            FUNCTIONAL_PROGRAMMING("Functional Programming", 3, HARD_CONCEPTS, 4),
            WEB_SERVICES("WebServices", 3, HARD_CONCEPTS, 5),
            MVC("MVC", 3, HARD_CONCEPTS, 6),
        SOFT_CONCEPTS("Soft", 2, CONCEPTS, 6),
            AGILE("Agile", 3, SOFT_CONCEPTS, 6),
                SCRUM("SCRUM", 4, AGILE, 9),
            SOA("SOA", 3, SOFT_CONCEPTS, 7),
            CONTINUOUS_DELIVERY("Continuous Delivery", 3, SOFT_CONCEPTS, 7),
            CONTINUOUS_INTEGRATION("Continuous Integration", 3, SOFT_CONCEPTS, 8),

    OTHER_LANGUAGES("Other languages", 1, JAVA, 2),
        JS("JavaScript", 2, OTHER_LANGUAGES, 8),
            JS_FRAMEWORKS("JS Frameworks", 3, JS, 7),
                ANGULAR("Angular", 4, JS_FRAMEWORKS, 8),
                REACT("React", 4, JS_FRAMEWORKS, 7),
                NODE("Node", 4, JS_FRAMEWORKS, 5),
            JS_TOOLS("JS Tools", 3, JS, 5),
                NPM("npm", 4, JS_TOOLS, 7),
                GWT("GWT", 4, JS_TOOLS, 5),
            JS_OTHER("JS Other", 3, JS, 7),
                JQUERY("JQuery", 4, JS_OTHER, 8),
                TYPESCRIPT("TypeScript", 4, JS_OTHER, 8),
        WEB("HTML/CSS", 2, OTHER_LANGUAGES, 7),
            HTML("HTML", 3, WEB, 7),
                HTML5("HTML 5", 4, HTML, 8),
                THYMELEAF("Thymeleaf", 4, HTML, 6),
                JSP("JSP", 4, HTML, 5),
            CSS("CSS", 3, WEB, 6),
                RWD("RWD", 4, CSS, 5),
            CMS("CMS", 3, WEB, 4),
            XML("XML", 3, WEB, 5),
                SOAP("SOAP", 4, XML, 5),
        OTHER_OTHER_LANGUAGES("Other", 2, OTHER_LANGUAGES, 6),
            PYTHON("Python", 3, OTHER_OTHER_LANGUAGES, 8),
            CPP("C++", 3, OTHER_OTHER_LANGUAGES, 5),
            SCALA("Scala", 3, OTHER_OTHER_LANGUAGES, 6),
            KAREL("KAREL", 3, OTHER_OTHER_LANGUAGES, 2),
        SMALL_LANGUAGES("\"Small\" languages", 2, OTHER_LANGUAGES, 5),
            JSON("JSON", 3, SMALL_LANGUAGES, 6),
            UML("UML", 3, SMALL_LANGUAGES, 9),
            MQ("MQ", 3, SMALL_LANGUAGES, 5),

    OTHER("Other skills", 1, JAVA, 2),
        OS("Operating systems", 2, OTHER, 8),
            WINDOWS("Windows", 3, OS, 6),
                CMD("cmd", 4, WINDOWS, 6),
            MAC("Mac", 3, OS, 5),
            LINUX("Linux", 3, OS, 8),
        BIG_DATA("BigData", 2, OTHER, 6),
            MAP_REDUCE("MapReduce", 3, BIG_DATA, 6),
            SPARK("Spark", 3, BIG_DATA, 6),
        APACHE("Apache", 2, OTHER, 6),
    //paste other
            APACHE_SERVICEMIX("Apache ServiceMix", 3, APACHE, 5),
        MISC("Miscellaneous", 2, OTHER, 5),
            DWH("DWH", 3, MISC, 5),
            SOLR("SOLR", 3, MISC, 5),
            CLOUD("Cloud", 3, MISC, 6),
            GRAFANA("Grafana", 3, MISC, 6),
            SAP_HYBRIS("SAP Hybris", 3, MISC, 4),
            WEBSPHERE("WebSphere", 3, MISC, 4),
            ACTIVEMQ("ActiveMQ", 3, MISC, 4),
            GLASS_FISH("GlassFish", 3, MISC, 4);


    public static final int LEVELS = 4;
    public static final int LAYERS = 5;
    private String name;
    private int layer;
    private double points;
    private double reqPoints[] = new double[LEVELS];
    private int occurrences;
    private Skill superSkill;
    private ArrayList<Skill> subSkills = new ArrayList<>();
    private int impactOnSuperSkill; //1-10


    //add comment field
    Skill(String name, int layer, Skill superSkill, int impactOnSuperSkill){
        this.name = name;
        this.layer = layer;
        this.superSkill = superSkill;
        this.impactOnSuperSkill = impactOnSuperSkill;
    }


    //add///////////////////////////////////////////////////////////////////////////////////
    public void addSubSkill(Skill subSkill) {
        subSkills.add(subSkill);
    }

    public void addReqPoints(int offerPoints, int offersCount, ReqLvl reqLvl) {

        reqPoints[reqLvl.ordinal()] += (double) offerPoints /(double)offersCount;
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
    }

    public void addOccurence() {
        this.occurrences++;
    }

    public void addReqPointsBySuperSkill(ReqLvl reqLvl){
        reqPoints[reqLvl.ordinal()] += superSkill.reqPoints[reqLvl.ordinal()] * impactOnSuperSkill / 10 * (100 - reqPoints[reqLvl.ordinal()])/100 * (1 + reqLvl.ordinal()/10.);
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
        if (reqPoints[reqLvl.ordinal()] > 99)
            reqPoints[reqLvl.ordinal()] = 99;
    }

    public void addReqPointsBySubSkills(ReqLvl reqLvl) {
        double pointsFromSubSkills = 0;
        for (Skill subSkill : subSkills) {
            pointsFromSubSkills += subSkill.reqPoints[reqLvl.ordinal()] * subSkill.impactOnSuperSkill / subSkills.size() / 10;
        }
        reqPoints[reqLvl.ordinal()] += pointsFromSubSkills * (100 - reqPoints[reqLvl.ordinal()])/100;
        reqPoints[reqLvl.ordinal()] = Math.round(reqPoints[reqLvl.ordinal()]);
        if (reqPoints[reqLvl.ordinal()] > 99)
            reqPoints[reqLvl.ordinal()] = 99;
    }


    public void addPoints(double points){
        addPointsToThisAndSuperSkills(points);
    }

    private void addPointsToThisAndSubSkills(double points){
        this.points += points;
        for (Skill subSkill: subSkills) {
            subSkill.addPointsToThisAndSubSkills(points * subSkill.impactOnSuperSkill / 10. * (100 - subSkill.points)/100./subSkills.size());
        }

    }

    private void addPointsToThisAndSuperSkills(double points){

        this.points += points * (100. - this.points)/100.;

        Skill skill = this;
        int subSkillsImpact;
        double pointsMultiplicator = 1;
        for (int i = layer; i > 0; i--){
            if (i > 0) {
                subSkillsImpact = 0;
                for (Skill subSkill : skill.superSkill.subSkills) {
                    subSkillsImpact += subSkill.impactOnSuperSkill;
                }
                pointsMultiplicator *= skill.impactOnSuperSkill / (double) subSkillsImpact * (100. - skill.superSkill.points)/100.;
            }
            skill.superSkill.points += points * pointsMultiplicator;
            skill = skill.superSkill;
        }
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

    public ArrayList<Skill> getSubSkills() {
        return subSkills;
    }
}
