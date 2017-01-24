package me.loki2302.be.controllers;

import me.loki2302.be.QueryHandler;
import me.loki2302.be.readmodel.userview.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private QueryHandler queryHandler;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/me")
    public ResponseEntity getMe(@CurrentUser long userId) {
        UserView userView = queryHandler.findUserById(userId);
        if(userView == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userView);
    }

    @RequestMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody UserAttributesDto userAttributesDto) {
        String username = userAttributesDto.username;
        UserView userView = queryHandler.findUserByName(username);
        if(userView == null) {
            return ResponseEntity.badRequest().build();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userView.userId,
                "",
                AuthorityUtils.NO_AUTHORITIES);
        SecurityContextHolder.getContext().setAuthentication(token);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/sign-out")
    public void signOut() {
        SecurityContextHolder.clearContext();
    }
}
