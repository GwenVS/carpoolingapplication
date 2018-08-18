package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.services.declaration.IAuthenticationHelperService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IStorageService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest controller that handles all file uploads & file retrievals
 */

@RestController
public class FileRestController implements HandlerExceptionResolver{
    private static final Logger logger = LogManager.getLogger(FileRestController.class);
    private final IUserService userService;
    private final IAuthenticationHelperService authenticationHelperService;
    private final IStorageService storageService;

    @Autowired
    public FileRestController(IUserService userService, IAuthenticationHelperService authenticationHelperService, IStorageService storageService) {
        this.userService = userService;
        this.authenticationHelperService = authenticationHelperService;
        this.storageService = storageService;
    }

    //Upload a profilepicture
    @PostMapping(value = "/api/private/users/{username}/uploadImage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity uploadProfilePicture(@PathVariable String username, @RequestBody MultipartFile uploadFile, HttpServletRequest request){
        User requestUser = userService.findUserByUsername(username);

        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };

        if(requestUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        storageService.store(uploadFile);
        String filename = StringUtils.cleanPath(uploadFile.getOriginalFilename());
        //Place the name of the file in the user object
        requestUser.setProfilePictureFileName(filename);

        userService.updateUserNoPassword(requestUser);
        return ResponseEntity.ok().build();
    }

    //Get a profilepicture
    @GetMapping("/api/private/users/{username}/picture")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Resource> serveFile(@PathVariable String username, HttpServletRequest request){
        User requestUser = userService.findUserByUsername(username);

        if(requestUser.getProfilePictureFileName() != null){
            Resource file = storageService.loadAsResource(requestUser.getProfilePictureFileName());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        else{
            return ResponseEntity.ok().build();
        }
    }
/*
    //Upload a gameSessionImage
    @PostMapping(value = "/api/private/users/{username}/sessions/{id}/uploadImage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity uploadProfilePicture(@PathVariable String username, @PathVariable Long id, @RequestBody MultipartFile uploadFile, HttpServletRequest request){
        User requestUser = userService.findUserByUsername(username);

        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };

        if(requestUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        storageService.store(uploadFile);
        String filename = StringUtils.cleanPath(uploadFile.getOriginalFilename());

        GameSession gameSession = gameSessionService.getGameSessionWithId(id);
        gameSession.setImage(filename);
        gameSessionService.updateGameSession(gameSession);
        return ResponseEntity.ok().build();
    }

    //Get a gameSessionImage from a session
    @GetMapping("/api/private/users/{username}/sessions/{id}/image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Resource> serveFile(@PathVariable String username, @PathVariable Long id, HttpServletRequest request){
        GameSession gameSession = gameSessionService.getGameSessionWithId(id);

        Resource file = storageService.loadAsResource(gameSession.getImage());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    //Get a gameSessionImage from a session
    @GetMapping("/api/private/sessions/{id}/image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Resource> serveFile(@PathVariable Long id, HttpServletRequest request){
        GameSession gameSession = gameSessionService.getGameSessionWithId(id);

        if(gameSession.getImage() != null){
            Resource file = storageService.loadAsResource(gameSession.getImage());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        else{
            return ResponseEntity.ok().build();
        }

    }*/

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //error handeling for file uploads
        return null;
    }

}
