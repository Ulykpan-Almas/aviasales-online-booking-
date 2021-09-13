package kz.javaee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {


    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_ee_db?useUnicode=true&serverTimezone=UTC","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static boolean addfootballer(Footballers footballer){

             int rows = 0 ; //сколько строк было добавлено

             try {
                 PreparedStatement statement = connection.prepareStatement(""+"INSERT INTO footballer (id,name,surname,salary,club,transferPrice,year )" + "VALUES (NULL,?,?,?,?,?,?)");
                 statement.setString(1,footballer.getName());
                 statement.setString(2,footballer.getSurname());
                 statement.setInt(3,footballer.getSalary());
                 statement.setString(4,footballer.getClub());
                 statement.setInt(5,footballer.getTransferPrice());
                 statement.setLong(6,footballer.getYear().getId());

                 rows=statement.executeUpdate();
                 statement.close();

             }catch (Exception e){
                 e.printStackTrace();
             }
             return rows>0; //если добавлено будет возвращать
    }
    public static ArrayList<Footballers> getFootballers(){
        ArrayList<Footballers> footballers = new ArrayList<>();

        try {
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT fb.id , fb.name , fb.surname , fb.salary , fb.club, fb.transferPrice, fb.year, c.name AS countryName, c.short_name  " +
                    "FROM footballer fb " +
                    "INNER JOIN countries c ON fb.year = c.id " +
                    "ORDER BY fb.salary DESC ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                footballers.add(new Footballers(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("salary"),
                        resultSet.getString("club"),
                        resultSet.getInt("transferPrice"),
                        new Countries(
                                resultSet.getLong("year"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")


                        )
                ));

                }
                statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return footballers;
    }

    public static Footballers getfootballer(Long id){

        Footballers footballers = null;

        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT fb.id , fb.name , fb.surname , fb.salary , fb.club, fb.transferPrice, fb.year, c.name AS countryName, c.short_name  " +
                    "FROM footballer fb " +
                    "INNER JOIN countries c ON c.id = fb.year " +
                    "WHERE fb.id = ? "+
                    "ORDER BY fb.salary DESC " );

            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
               footballers=new Footballers(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("salary"),
                        resultSet.getString("club"),
                        resultSet.getInt("transferPrice"),
                        new Countries(
                               resultSet.getLong("year"),
                               resultSet.getString("countryName"),
                               resultSet.getString("short_name")

                       )
                );

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return footballers;
    }

    public static boolean savefootballer(Footballers footballers){
        int rows = 0;
        try {

            PreparedStatement statement= connection.prepareStatement("UPDATE footballer SET name=?,surname=?,salary=?,club=?,transferPrice=?,year=? WHERE id=? ");

            statement.setString(1,footballers.getName());
            statement.setString(2,footballers.getSurname());
            statement.setInt(3,footballers.getSalary());
            statement.setString(4,footballers.getClub());
            statement.setInt(5,footballers.getTransferPrice());
            statement.setLong(6,footballers.getYear().getId());
            statement.setLong(7,footballers.getId());


            rows=statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static boolean deletefootballer(Footballers footballers){
        int rows = 0;
        try {

            PreparedStatement statement= connection.prepareStatement("DELETE FROM footballer WHERE id = ? ");

            statement.setLong(1,footballers.getId());

            rows=statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<Countries> getCountries(){
        ArrayList<Countries> countries = new ArrayList<>();

        try {
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT * FROM countries ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){

                countries.add(new Countries(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("short_name")
                ));

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }


    public static Countries getCountry (Long id){

        Countries country = null;

        try {
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT * FROM countries WHERE id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){

                country=new Countries(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("short_name")
                );

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return country;
    }





    public static ArrayList<Footballers> searchFootballer(String key, Long countryId, double priceFrom, double priceTo){

        ArrayList<Footballers> footballers = new ArrayList<>();

        try{

            String countryQuery = "";

            if(countryId!=null && countryId!=0L){
                countryQuery = " AND fb.country_id = "+countryId;
            }

            String priceFromQuery = "";
            if(priceFrom!=-1){
                priceFromQuery = " AND fb.salary >= "+priceFrom;
            }

            String priceToQuery = "";
            if(priceTo!=-1){
                priceToQuery = " AND fb.salary <= "+priceTo;
            }

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT fb.id , fb.name , fb.surname , fb.salary , fb.club, fb.transferPrice, fb.year, c.name AS countryName, c.short_name " +
                    "FROM footballer fb " +
                    "INNER JOIN countries c ON c.id = fb.year " +
                    "WHERE UPPER(fb.name) LIKE UPPER(?) " + countryQuery + priceFromQuery + priceToQuery +
                    "ORDER BY fb.salary DESC ");

            statement.setString(1, "%"+key+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                footballers.add(new Footballers(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("salary"),
                        resultSet.getString("club"),
                        resultSet.getInt("transferPrice"),
                        new Countries(
                                resultSet.getLong("year"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")
                        )
                ));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return footballers;

    }



    public static Users getUser(String email){
        Users user = null;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ? ");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user =new Users(
                    resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("full_name")
                );
            }
            statement.close();

        }catch ( Exception e){
            e.printStackTrace();
        }
        return user;
    }


    public  static  boolean addUser(Users user){
        int rows = 0 ;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email,password,full_name) " +
                    "VALUES (?,?,?)");
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullName());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static boolean saveUser(Users users){
        int rows = 0;
        try {

            PreparedStatement statement= connection.prepareStatement("" +
                    "UPDATE users SET full_name = ?  WHERE id=? ");

            statement.setString(1,users.getFullName());
            statement.setLong(2,users.getId());


            rows=statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }



    public static boolean savePassword(Users users){
        int rows = 0;
        try {

            PreparedStatement statement= connection.prepareStatement("" +
                    "UPDATE users SET  password = ? WHERE id=? ");

            statement.setString(1,users.getPassword());
            statement.setLong(2,users.getId());


            rows=statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public  static boolean addBlog(Blogs blog){

        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO blogs (user_id, title, short_content, content, post_date) " +
                    "VALUES (?,?,?,?, NOW()) ");
            statement.setLong(1,blog.getUser().getId());
            statement.setString(2,blog.getTitle());
            statement.setString(3,blog.getShortContent());
            statement.setString(4,blog.getContent());

            rows = statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static ArrayList<Blogs> getAllBlogs(){
        ArrayList<Blogs> blogs = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT b.id, b.title, b.short_content, b.post_date, u.full_name, b.user_id, b.like_amount  " +
                    "FROM blogs b " +
                    "INNER JOIN users u on b.user_id = u.id " +
                    "ORDER BY b.post_date DESC ");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

                blogs.add(new Blogs(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null,null,
                                resultSet.getString("full_name")
                        ),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        null,
                        resultSet.getDate("post_date"),
                        resultSet.getInt("like_amount")

                ));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return blogs;
    }



    public static Blogs getBlog(Long id){
        Blogs blog = null;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT b.id, b.title, b.short_content, b.content, b.post_date, u.full_name, b.user_id, b.like_amount " +
                    "FROM blogs b " +
                    "INNER JOIN users u on b.user_id = u.id " +
                    "WHERE b.id = ?");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                blog = new Blogs(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null,null,
                                resultSet.getString("full_name")
                        ),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getInt("like_amount")
                );

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return blog;
    }




    public  static boolean saveBlog(Blogs blog){

        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE blogs SET title = ?, short_content = ?, content = ? " +
                    "WHERE id = ? ");

            statement.setString(1,blog.getTitle());
            statement.setString(2,blog.getShortContent());
            statement.setString(3,blog.getContent());
            statement.setLong(4,blog.getId());


            rows = statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }





    public  static boolean deleteBlog(Blogs blog){

        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM blogs WHERE id = ? ");


            statement.setLong(1,blog.getId());


            rows = statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }




    public static boolean addComment(Comments comment){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (user_id, blog_id, comment, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUser().getId());
            statement.setLong(2, comment.getBlog().getId());
            statement.setString(3, comment.getComment());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }



    public static ArrayList<Comments> getCommentsByBlogId(Long blogid){
        ArrayList<Comments> comments = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.comment, c.user_id, c.blog_id, c.post_date, u.full_name " +
                    "FROM comments c " +
                    "INNER JOIN users u on c.user_id = u.id " +
                    "WHERE c.blog_id = ? " +
                    "ORDER BY c.post_date DESC ");

            statement.setLong(1,blogid);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

                comments.add(new Comments(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),null,null,
                                resultSet.getString("full_name")
                        ),
                        new Blogs(
                                resultSet.getLong("blog_id"),
                                null,null,null,null,null
                        ),
                        resultSet.getString("comment"),
                        resultSet.getTimestamp("post_date")
                        )
                        );
                }



            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;
    }







    public static int likeBlog(Blogs blog, Users user){

        int likes = 0;
        boolean liked = false;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM likes WHERE blog_id = ? AND user_id = ? ");

            statement.setLong(1, blog.getId());
            statement.setLong(2, user.getId());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                liked = true;
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement;

            if(liked){
                statement = connection.prepareStatement("" +
                        "DELETE FROM likes WHERE user_id = ? AND blog_id = ?");
            }else{
                statement = connection.prepareStatement("" +
                        "INSERT INTO likes (user_id, blog_id) VALUES (?, ?)");
            }

            statement.setLong(1, user.getId());
            statement.setLong(2, blog.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT COUNT(*) AS like_amount FROM likes WHERE blog_id = ? ");

            statement.setLong(1, blog.getId());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                likes = resultSet.getInt("like_amount");
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE blogs SET like_amount = ? WHERE id = ? ");

            statement.setInt(1, likes);
            statement.setLong(2, blog.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return likes;

    }


    public static ArrayList<Airlines> getAirlines(){
        ArrayList<Airlines> airlines = new ArrayList<>();

        try {
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT id, from_city, to_city, when_flight, people_number " +
                    "FROM airlines " +
                    "ORDER BY people_number DESC ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                airlines.add(new Airlines(
                        resultSet.getLong("id"),
                        resultSet.getString("from_city"),
                        resultSet.getString("to_city"),
                        resultSet.getInt("when_flight"),
                        resultSet.getInt("people_number")
                ));


            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return airlines;
    }



    public static boolean addAirline(Airlines airlines){

        int rows = 0 ; //сколько строк было добавлено

        try {
            PreparedStatement statement = connection.prepareStatement(""+"INSERT INTO airlines (id, from_city, to_city, when_flight, people_number)" + "VALUES (NULL,?,?,?,?)" );
            statement.setString(1,airlines.getFrom_city());
            statement.setString(2,airlines.getTo_city());
            statement.setInt(3,airlines.getWhen_flight());
            statement.setInt(4,airlines.getPeople_number());


            rows=statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0; //если добавлено будет возвращать
    }



    public static Airlines getairline(Long id){

        Airlines airlines = null;

        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT id, from_city, to_city, when_flight, people_number " +
                    "FROM airlines " +
                    "WHERE id = ? ");

            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                airlines=new Airlines(
                        resultSet.getLong("id"),
                        resultSet.getString("from_city"),
                        resultSet.getString("to_city"),
                        resultSet.getInt("when_flight"),
                        resultSet.getInt("people_number")

                );

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return airlines;
    }



    public  static boolean saveAir(Airlines airlines){

        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE airlines SET from_city = ?, to_city = ?, when_flight = ?, people_number = ? " +
                    "WHERE id = ? ");

            statement.setString(1,airlines.getFrom_city());
            statement.setString(2,airlines.getTo_city());
            statement.setInt(3,airlines.getWhen_flight());
            statement.setInt(4,airlines.getPeople_number());
            statement.setLong(5,airlines.getId());



            rows = statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }





    public  static boolean deleteair(Airlines airlines){

        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM airlines WHERE id = ? ");


            statement.setLong(1,airlines.getId());


            rows = statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }





    public static ArrayList<Airlines> searchAir(String key,String to){

        ArrayList<Airlines> airlines = new ArrayList<>();

        try{




            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT id, from_city, to_city, when_flight, people_number " +
                    "FROM airlines " +
                    "WHERE UPPER(from_city) LIKE UPPER(?) AND UPPER(to_city) LIKE UPPER(?)  " +
                    "ORDER BY people_number DESC ");

            statement.setString(1, "%"+key+"%");
            statement.setString(2, "%"+to+"%");




            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
               airlines.add(new Airlines(
                       resultSet.getLong("id"),
                       resultSet.getString("from_city"),
                       resultSet.getString("to_city"),
                       resultSet.getInt("when_flight"),
                       resultSet.getInt("people_number")
               ));



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return airlines ;

    }







}

