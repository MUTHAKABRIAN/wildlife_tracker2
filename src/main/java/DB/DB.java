package DB;

import org.sql2o.Sql2o;

public class DB {
    public static Sql2o sql2o  = new Sql2o( "jdbc:postgresql://ec2-34-198-31-223.compute-1.amazonaws.com:5432/d1pg3fm6vnse8l","xosgwzkzwjyqct","a0fbbed62a424ededccff1329253efc108f22d07c59d72d4140b13f7e9976d48");
//    Sql2o sql2o = new Sql2o(connectionString, "xosgwzkzwjyqct", "a0fbbed62a424ededccff1329253efc108f22d07c59d72d4140b13f7e9976d48");
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "Access");
}

