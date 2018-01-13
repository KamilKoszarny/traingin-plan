public enum Skill {

    //1st level
    JAVA ("Java", 1, null),
        //2nd level
        BASICS("Basics", 2, JAVA),
            //3rd level
            LANG_BASICS("Language basics", 3, BASICS),
            OOP_IN_JAVA("OOP in Java", 3, BASICS),
            CLASSES("Classes", 3, BASICS),
            GUI("GUI", 3, BASICS),
        ADVANCED("Advanced", 2, JAVA),
            GENERICS("Generics", 3, ADVANCED),
            JAVA8("Java 8", 3, ADVANCED),
            COLLECTIONS("Collections", 3, ADVANCED),
            ALGOTHITMS("Algorithms", 3, ADVANCED),
            SERVLETS("Servlets", 3, ADVANCED),
        COMMERCIAL("Commercial", 2, JAVA),
            JEE("JEE", 3, COMMERCIAL),
                EJB("EJB", 4, JEE),
            J2EE("J2EE", 3, COMMERCIAL),
            SERVLET("Servlet", 3, COMMERCIAL),
            ANT("Ant", 3, COMMERCIAL),
            JMS("JMS", 3, COMMERCIAL),
            REST("REST", 3, COMMERCIAL),
                REST_ASSURED("REST Assured", 3, REST),
            Graph_QL("GraphQL", 3, COMMERCIAL),
            JPA("JPA", 3, COMMERCIAL),

    FRAMEWORKS ("Frameworks", 1, null),
        SPRING("Spring", 2, FRAMEWORKS),
            SPRING_BOOT("Spring Boot", 3, SPRING),
            SPRING_WEBFLOW("Spring Webflow", 3, SPRING),
            SPRING_WEBSERVICES("Spring WebServices", 3, SPRING),
            SPRING4("Spring 4", 3, SPRING),
        HIBERNATE("Hibernate", 2, FRAMEWORKS),
        JSF("JSF", 2, FRAMEWORKS),
        STRUTS("Struts", 2, FRAMEWORKS),

    TOOLS ("Tools", 1, null),
        VERSION_CONTROL("Version Control", 2, TOOLS),
            GIT("Git", 3, VERSION_CONTROL),
                GIT_FLOW("GitFlow", 4, GIT),
                BITBUCKET("Bitbucket", 4, GIT),
                GIT_LAB("GitLab", 4, GIT),
                GIT_HUB("GitHub", 4, GIT),
            SVN("SVN", 3, VERSION_CONTROL),
        PROJECT_MANAGEMENT("Project Management", 2, TOOLS),
            MAVEN("Maven", 3, PROJECT_MANAGEMENT),
            JIRA("JIRA", 3, PROJECT_MANAGEMENT),
            REDMINE("Redmine", 3, PROJECT_MANAGEMENT),
            CONFLUENCE("Confluence", 3, PROJECT_MANAGEMENT),
        IDE("IDE", 2, TOOLS),
            INTELLIJ("IntelliJ", 3, IDE),
            ECLIPSE("Eclipse", 3, IDE),
            NETBEANS("NetBeans", 3, IDE),
        AUTOMATION("Automation", 2, TOOLS),
            JENKINS("Jenkins", 3, AUTOMATION),
            GRADLE("Gradle", 3, AUTOMATION),
            DOCKER("Docker", 3, AUTOMATION),
            BAMBOO("Bamboo", 3, AUTOMATION),
            HUDSON("Hudson", 3, AUTOMATION),
            KAFKA("Kafka", 3, AUTOMATION),
        OTHER_TOOLS("Other tools", 2, TOOLS),
            WEBLOGIC("WebLogic", 3, OTHER_TOOLS),
            ELASTICSEARCH("Elasticsearch", 3, OTHER_TOOLS),
                KIBANA("Kibana", 4, OTHER_TOOLS),
            LOGSTASH("Logstash", 3, OTHER_TOOLS),

    TESTS ("Tests", 1, null),
        JUNIT("JUnit", 2, TESTS),
            TEST_NG("TestNG", 3, JUNIT),
        HAMCREST("Hamcrest", 2, TESTS),
        MOCKITO("Mockito", 2, TESTS),

    DATABASES ("Data Bases", 1, null),
        SQL("SQL", 2, DATABASES),
            MY_SQL("MySQL", 3, SQL),
            POSTGRE_SQL("PostgreSQL", 3, SQL),
        ORACLE("Oracle", 2, DATABASES),
        JDBC("JDBC", 2, DATABASES),
            MY_BATIS("MyBatis", 3, JDBC),

    CONCEPTS("Concepts", 1, null),
        HARD_CONCEPTS("Hard Concepts", 2, CONCEPTS),
            OOP("OOP", 3, HARD_CONCEPTS),
            CLEAN_CODE("Clean Code", 3, HARD_CONCEPTS),
            MACHINE_LEARNING("Machine learning", 3, HARD_CONCEPTS),
            FUNCTIONAL_PROGRAMMING("Functional Programming", 3, HARD_CONCEPTS),
            WEB_SERVICES("WebServices", 3, HARD_CONCEPTS),
            MVC("MVC", 3, HARD_CONCEPTS),
        SOFT_CONCEPTS("Soft", 2, CONCEPTS),
            AGILE("Agile", 3, SOFT_CONCEPTS),
                SCRUM("SCRUM", 4, AGILE),
            SOA("SOA", 3, SOFT_CONCEPTS),
            CONTINOUS_DELIVERY("Continuous Delivery", 3, SOFT_CONCEPTS),
            CONTINOUS_INTEGRATION("Continuous Integration", 3, SOFT_CONCEPTS),

    LANGUAGES("Languages (other than Java)", 1, null),
        JS("JavaScript", 2, LANGUAGES),
            JS_FRAMEWORKS("JS Frameworks", 3, JS),
                ANGULAR("Angular", 4, JS_FRAMEWORKS),
                REACT("React", 4, JS_FRAMEWORKS),
                NODE("Node", 4, JS_FRAMEWORKS),
            JS_TOOLS("JS Tools", 3, JS),
                NPM("npm", 4, JS_TOOLS),
                GWT("GWT", 4, JS_TOOLS),
            JS_OTHER("JS Other", 3, JS),
                JQUERY("JQuery", 4, JS_OTHER),
                TYPESCRIPT("TypeScript", 4, JS_OTHER),
        WEB("HTML/CSS", 2, LANGUAGES),
            HTML("HTML", 3, WEB),
                HTML5("HTML 5", 4, HTML),
                THYMELEAF("Thymeleaf", 4, HTML),
            CSS("CSS", 3, WEB),
                RWD("RWD", 4, CSS),
            CMS("CMS", 3, WEB),
            XML("XML", 3, WEB),
                SOAP("SOAP", 4, XML),
        OTHER_LANGUAGES("Other", 2, LANGUAGES),
            PYTHON("Python", 3, OTHER_LANGUAGES),
            CPP("C++", 3, OTHER_LANGUAGES),
            SCALA("Scala", 3, OTHER_LANGUAGES),
            KAREL("KAREL", 3, OTHER_LANGUAGES),
        SMALL_LANGUAGES("\"Small\" langueages", 2, LANGUAGES),
            JSON("JSON", 3, SMALL_LANGUAGES),
            UML("UML", 3, SMALL_LANGUAGES),
            MQ("MQ", 3, SMALL_LANGUAGES),

    OTHER("Other skills", 1, null),
        OS("Operating systems", 2, OTHER),
            WINDOWS("Windows", 3, OS),
                CMD("cmd", 4, WINDOWS),
            MAC("Mac", 3, OS),
            LINUX("Linux", 3, OS),
        BIG_DATA("BigData", 2, OTHER),
            MAP_REDUCE("MapReduce", 3, BIG_DATA),
            SPARK("Spark", 3, BIG_DATA),
        MISC("Miscellaneous", 2, OTHER),
            DWH("DWH", 3, MISC),
            SOLR("SOLR", 3, MISC),
            CLOUD("Cloud", 3, MISC),
            GRAFANA("Grafana", 3, MISC),
            SAP_HYBRIS("SAP Hybris", 3, MISC);


    private String name;
    private int level;
    private int occurencies;
    private int rank;
    private Skill superSkill;
    private Skill[] subSkills;


    Skill(String name, int level, Skill superSkill){
        this.name = name;
        this.level = level;
        this.superSkill = superSkill;
    }



    //get/set////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOccurencies() {
        return occurencies;
    }

    public void setOccurencies(int occurencies) {
        this.occurencies = occurencies;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Skill getSuperSkill() {
        return superSkill;
    }

    public void setSuperSkill(Skill superSkill) {
        this.superSkill = superSkill;
    }

    public Skill[] getSubSkills() {
        return subSkills;
    }

    public void setSubSkills(Skill[] subSkills) {
        this.subSkills = subSkills;
    }
}
