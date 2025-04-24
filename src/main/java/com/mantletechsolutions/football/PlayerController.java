package com.mantletechsolutions.football;

import java.util.List;

import com.mantletechsolutions.football.exceptions.AlreadyExistsException;
import com.mantletechsolutions.football.exceptions.NotFoundException;
import com.mantletechsolutions.football.model.Player;
import com.mantletechsolutions.football.services.FootballService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/players")
@RestController
public class PlayerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
    @ExceptionHandler(NotFoundException.class)
    public void notFoundHandler() {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
    @ExceptionHandler(AlreadyExistsException.class) public void alreadyExistsHandler() {
    }


    private FootballService footballService;

    public PlayerController(FootballService footballService){

        this.footballService = footballService;
    }


    @GetMapping
    public List<Player> listPlayers() {
        return  footballService.listPlayers();
    }


    @PostMapping
    public void  createPlayer(@RequestBody Player player) {
      footballService.addPlayer(player);
    }

    @GetMapping("/{id}")
    public Player readPlayer(@PathVariable String id) {
        return footballService.getPlayer(id);
    }


    @PutMapping("/{name}")
    public void  updatePlayer(@PathVariable String id, @RequestBody Player player) {
   footballService.updatePlayer(player);
    }

    @DeleteMapping("/{name}")
    public void deletePlayer(@PathVariable String id) {
       footballService.deletePlayer(id);
    }
}