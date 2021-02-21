package com.Main.Controller.Resplonse.UserActionAPI;

import com.Main.Model.MySqlConnection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping(value = "/user")
public class RateMovieAPI {

    @PostMapping(value = "/rate-movie/{id}")
    public void rateMovie(@PathVariable(value = "id") int id, int user_id, float user_score){
        String sql = "insert into movie_score (id, user_id, user_score) values (" + id + ", " + user_id + ", " + user_score + ");";
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            statement.execute(sql);
            String sql2 = "select AVG(user_score) from movie_score where id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(sql2);
            float new_average_score = resultSet.getFloat(1);
            String sql3 = "update movie_table set user_score = " + new_average_score + " where id = " + id + " and user_id = " + user_id +";";
            statement.execute(sql2);
            statement.execute(sql3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
