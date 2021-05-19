package me.tapumandal.jewellery.controller.v1;

import me.tapumandal.jewellery.entity.LoginResponseModelConsumer;
import me.tapumandal.jewellery.entity.dto.ConsumerUserDto;
import me.tapumandal.jewellery.util.*;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.service.MyUserDetailsService;
import me.tapumandal.jewellery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import me.tapumandal.jewellery.entity.LoginResponseModel;
import me.tapumandal.jewellery.entity.dto.AuthenticationRequest;
import me.tapumandal.jewellery.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController extends ControllerHelper {

    private static final String CONSUMER_USER_PASSWORD = "12345abcde!@#$%";

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myuserDetailsService;
    @Autowired
    UserDetails userDetails;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseModelConsumer> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        userDetails = myuserDetailsService.loadUserByUsername(authenticationRequest.getUsername().toString());

        LoginResponseModelConsumer loginResponseModel = new LoginResponseModelConsumer();
        loginResponseModel.setJwt(jwtUtil.generateToken(userDetails));
//        loginResponseModel.setUser(userService.getUserByUserName(userDetails.getUsername()));

        return ResponseEntity.ok(loginResponseModel);
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>This is the Home Page. </h1> <span>Site is under construction.<span>");
    }

    @PostMapping(path = "/registration")
    public CommonResponseSingle userRegistration(@RequestBody @Valid User u, HttpServletRequest request) {

        if (!userService.isUserExist(u.getUsername())) {
//            if (u.getCompany().getId() != 0) {
//                return response(false, HttpStatus.BAD_REQUEST, "Please check your company information.", (User) null);
//            }
//            User user = userService.createUser(u);
            User user = userService.createAdminAccount(u);

            if (user != null) {
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            } else {
                return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact.", (User) null);
            }

        } else {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }

    @PostMapping(path = "/user/create")
    public CommonResponseSingle userCreate(@RequestBody @Valid User u, HttpServletRequest request) {

        storeUserDetails(request);

        if (!userService.isUserExist(u.getUsername())) {

//            u.setCompany(null);
            User user = userService.createUser(u);

            if (user != null) {
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            } else {
                return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact.", (User) null);
            }

        } else {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }

    @GetMapping(path = "user/{id}")
    public CommonResponseSingle<User> getUser(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        User user = userService.getById(id);

        if (user != null) {
            return response(true, HttpStatus.FOUND, "User by id: " + id, user);
        } else if (user == null) {
            return response(false, HttpStatus.NO_CONTENT, "User not found or deleted", (User) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (User) null);
        }
    }

    @GetMapping(path = "user/list")
    public CommonResponseArray getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<User> products = userService.getAll(pageable, listFilter);

        MyPagenation myPagenation = managePagenation(request, userService.getAllEntityCount(pageable, listFilter), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.OK, "All user list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(true, HttpStatus.NO_CONTENT, "User List is empty", new ArrayList<User>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<User>(), myPagenation);
        }

    }

    @PostMapping(path = "user/update")
    public CommonResponseSingle updateProduct(@RequestBody User u, HttpServletRequest request) {

        storeUserDetails(request);

        User user = userService.update(u);

        if (user != null) {
            return response(true, HttpStatus.OK, "New user inserted successfully", user);
        } else if (user == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (User) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (User) null);
    }

    @DeleteMapping(path = "user/{id}")
    public CommonResponseSingle<User> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (userService.deleteById(id)) {
            return response(true, HttpStatus.OK, "User by id " + id + " is deleted", (User) null);
        } else {
            return response(false, HttpStatus.NOT_FOUND, "User not found or deleted", (User) null);
        }
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    private boolean validateFirebaseTokenID(String tokenID) {
        if(tokenID != null && tokenID.length() > 10){
            return true;
        }else{
            return false;
        }
//        FirebaseOptions options = null;
//        try {
//            options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.getApplicationDefault())
//                    .setDatabaseUrl("https://grocery-ecommerce-845b8.firebaseio.com/")
//                    .build();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        FirebaseApp.initializeApp(options);
//
//        FirebaseToken decodedToken = null;
//        try {
//            decodedToken = FirebaseAuth.getInstance().verifyIdToken(u.getUserTokenId());
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//        }
//        String uid = decodedToken.getUid();
//        System.out.println("Firebase Authentication: "+uid);

    }

    @PostMapping(path = "consumer/registration")
    public CommonResponseSingle<LoginResponseModel> consumerRegistration(@RequestBody @Valid User u, HttpServletRequest request) throws Exception  {

        u.setRole("CONSUMER");

        if (!userService.isUserExist(u.getUsername())) {
            User cUserDto = userService.createUser(u);

            try {
                //System.out.println("3");
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(cUserDto.getUsername(), CONSUMER_USER_PASSWORD)
                );
            } catch (BadCredentialsException e) {
                //System.out.println("4");
                throw new Exception("Incorrect username or tokenId", e);
            }
            //System.out.println("5");
            userDetails = myuserDetailsService.loadUserByUsername(cUserDto.getUsername());
            //System.out.println("USER DETAILS: "+new Gson().toJson(userDetails));
            //System.out.println("JWT: "+jwtUtil.generateToken(userDetails));
            LoginResponseModelConsumer loginResponseModel = new LoginResponseModelConsumer();
            loginResponseModel.setJwt(jwtUtil.generateToken(userDetails));
//            loginResponseModel.setUser(userService.getUserByUserName(userDetails.getUsername()));
            //System.out.println("6");
            return response(true, HttpStatus.OK, "Registration is successful", loginResponseModel);

        } else {
            //System.out.println("7");
            return response(false, HttpStatus.BAD_REQUEST, "The account is already exist. \n Please login.", (LoginResponseModel) null);
        }
    }

    @PostMapping("consumer/authenticate")
    public CommonResponseSingle<LoginResponseModel> consumerAuthenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        if(!validateFirebaseTokenID(authenticationRequest.getUserTokenId())){
            return response(false, HttpStatus.BAD_REQUEST, "This is not a valid request.", (LoginResponseModel) null);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), CONSUMER_USER_PASSWORD)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or tokenId", e);
        }

        userDetails = myuserDetailsService.loadUserByUsername(authenticationRequest.getUsername().toString());

        LoginResponseModelConsumer loginResponseModel = new LoginResponseModelConsumer();
        loginResponseModel.setJwt(jwtUtil.generateToken(userDetails));
//        loginResponseModel.setUser(userService.getUserByUserName(userDetails.getUsername()));
        //System.out.println("LOGIN CONTROLELR: "+new Gson().toJson(userService.getUserByUserName(userDetails.getUsername())));

        return response(true, HttpStatus.OK, "Login is successful", loginResponseModel);
    }


    @PostMapping(path = "consumer/address/update")
    public CommonResponseSingle<User> updateUserAddress(@RequestBody User u, HttpServletRequest request) {

        //System.out.println("CONTROLLER ADDRESS DTO: "+new Gson().toJson(u));
        storeUserDetails(request);

        User user = userService.update(u);
        //System.out.println("CONTROLLER ADDRESS RETURN: "+new Gson().toJson(u));
        if (user != null) {
            return response(true, HttpStatus.OK, "New user inserted successfully", user);
        } else if (user == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (User) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (User) null);
    }

    @PostMapping(path = "consumer/myprofile")
    public CommonResponseSingle<User> getMyProfileConsumer(HttpServletRequest request) {

        storeUserDetails(request);

        int userId = ApplicationPreferences.getUser().getId();
        User user = userService.getById(userId);
        //System.out.println("CONTROLLER MY PROFILE: "+new Gson().toJson(user));
        if (user != null) {
            return response(true, HttpStatus.OK, "New user inserted successfully", user);
        } else if (user == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (User) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (User) null);
    }

    private ConsumerUserDto convertToDto(User user) {
        ConsumerUserDto consumerUserDto = modelMapper.map(user, ConsumerUserDto.class);
        return consumerUserDto;
    }
}