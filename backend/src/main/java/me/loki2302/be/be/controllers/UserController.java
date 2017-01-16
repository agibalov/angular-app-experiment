package me.loki2302.be.be.controllers;

import me.loki2302.be.be.CommandHandler;
import me.loki2302.be.be.QueryHandler;
import me.loki2302.be.be.commands.CreateUserCommand;
import me.loki2302.be.be.readmodel.userview.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class UserController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserAttributesDto userAttributesDto) {
        CreateUserCommand command = new CreateUserCommand(userAttributesDto.username);
        long userId = commandHandler.handle(command);
        URI location = fromMethodCall(on(UserController.class).getUser(userId)).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        List<UserView> userViews = queryHandler.findUsers();
        return ResponseEntity.ok(userViews);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("userId") long userId) {
        UserView userView = queryHandler.findUser(userId);
        if(userView == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userView);
    }
}