package me.loki2302.be.be;

import me.loki2302.be.be.readmodel.userview.UserView;
import me.loki2302.be.be.readmodel.userview.UserViewMaterializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryHandler {
    @Autowired
    private UserViewMaterializer userViewMaterializer;

    public UserView findUser(long userId) {
        return userViewMaterializer.findUser(userId);
    }

    public List<UserView> findUsers() {
        return userViewMaterializer.findAll();
    }
}
