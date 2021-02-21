package com.Main.Controller.Resplonse;

import com.Main.Model.MySqlConnection;
import com.Main.Model.movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public class GetListAPI {

    @GetMapping(value = "")
    public Object getFullMovieList(){
        return getMovieList("select * from movie_table;");
    }

    @GetMapping(value = "/now-playing")
    public Object getNowPlayingMovie(){
        return getMovieList("select * from movie_table where status = 'now-playing';");
    }

    @GetMapping(value = "/upcoming")
    public Object getUpcomingMovie(){
        return getMovieList("select * from movie_table where status = 'upcoming';");
    }

    @GetMapping(value = "/top-rated")
    public Object getTopRatedMovie(){
        return getMovieList("select * from movie_table order by user_score desc;");
    }

    @GetMapping(value = "/{id}")
    public String getFullMovieInformation(@PathVariable(name = "id") int id){
        String sql = "select * from movie_table where id = " + id + ";";
        List<movie> list = new ArrayList<>();
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            movie thisMovie = new movie(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getFloat(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11));
            list.add(thisMovie);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                MySqlConnection.getInstanceConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list.get(0).toString();
        }
    }

    public Object getMovieList(String sql){
        List<movie> movieList = new ArrayList<>();
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                movie thisMovie = new movie(
                        resultSet.getString(2),
                        resultSet.getString(5),
                        resultSet.getFloat(8)
                );
                movieList.add(thisMovie);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                MySqlConnection.getInstanceConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return movieList;
        }
    }
}
