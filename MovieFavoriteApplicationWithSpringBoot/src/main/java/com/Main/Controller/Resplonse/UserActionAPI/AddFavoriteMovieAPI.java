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
public class AddFavoriteMovieAPI {

    @PostMapping(value = "/add-favorite-movie/{id}")
    public void addFavoriteMovie(@PathVariable(value = "id") int id, int user_id){
        String sql1= "select Max(favorite_movie_order) from favorite_movie_list;";
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);
            int favorite_movie_order = resultSet.getInt(1) + 1;
            String sql2 = "insert into favorite_movie_order (id, user_id, favorite_movie_order) values (" +
                    id + ", " +
                    user_id + ", " +
                    favorite_movie_order + ");";
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
