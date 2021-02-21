package com.Main.Controller.Resplonse.UserActionAPI;

import com.Main.Model.MySqlConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;

@RestController
@RequestMapping(value = "/user")
public class DeleteFavoriteMovieAPI {

    @DeleteMapping(value = "/delete-favorite-movie/{id}")
    public void deleteFavoriteMovie(@PathVariable(value = "id") @RequestParam(name = "id") int id, int user_id){
        String sql = "delete * from favorite_movie_list where user_id = " + user_id + " and id = " + id + ";";
        try{
            Statement statement = MySqlConnection.getInstanceConnection().createStatement();
            statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
