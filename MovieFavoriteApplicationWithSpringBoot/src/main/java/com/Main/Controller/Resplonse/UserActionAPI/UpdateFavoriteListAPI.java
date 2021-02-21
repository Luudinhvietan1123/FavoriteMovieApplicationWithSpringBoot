package com.Main.Controller.Resplonse.UserActionAPI;

import com.Main.Model.MySqlConnection;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/user")
public class UpdateFavoriteListAPI {

    @PutMapping(value = "update-favorite-movie-list")
    public void updateFavoriteMovieList(int id1, int id2, int user_id){
        String sql1 = "select favorite_movie_order from favorite_movie_list where id = " + id1 + " and user_id = " + user_id + ";";
        String sql2 = "select favorite_movie_order from favorite_movie_list where id = " + id2 + " and user_id = " + user_id + ";";
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            ResultSet resultSet1 = statement.executeQuery(sql1);
            int favorite_movie_order1 = resultSet1.getInt(1);
            ResultSet resultSet2 = statement.executeQuery(sql2);
            int favorite_movie_order2 = resultSet2.getInt(2);
            String sql3 = "update favorite_movie_list set favorite_movie_order = " + favorite_movie_order2 + "where id = " + id1 + "and user_id = " + user_id;
            String sql4 = "update favorite_movie_list set favorite_movie_order = " + favorite_movie_order1 + "where id = " + id2 + "and user_id = " + user_id;
            statement.execute(sql3);
            statement.execute(sql4);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
