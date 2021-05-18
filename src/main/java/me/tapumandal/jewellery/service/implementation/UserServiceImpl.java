package me.tapumandal.jewellery.service.implementation;

import me.tapumandal.jewellery.entity.dto.ConsumerUserDto;
import me.tapumandal.jewellery.repository.RefCodeRepository;
import me.tapumandal.jewellery.repository.UserRepository;
import me.tapumandal.jewellery.util.ApplicationPreferences;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.security.crypto.password.PasswordEncoder;
import me.tapumandal.jewellery.domain.address.Address;
import me.tapumandal.jewellery.domain.address.AddressDto;
import me.tapumandal.jewellery.domain.address.AddressRepository;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.domain.ref_code.RefCodeReward;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.entity.dto.UserDto;
import me.tapumandal.jewellery.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private static final String CONSUMER_USER_PASSWORD = "12345abcde!@#$%";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ApplicationPreferences applicationPreferences;

    @Autowired
    RefCodeRepository refCodeRepository;

    private User user;

    public UserServiceImpl(){}

    public UserServiceImpl(User user){
        this.user = user;
    }


    @Override
    public ConsumerUserDto createUser(User user) {

//        True for ecommerce fron app.
//        Make it conditional when mobile app is ready

        return createUserAccount(user);
//        if(userDto.getUserTokenId() != null){
//        }else{
//            return createAdminAccount(user);
//        }
    }

    @Override
    public ConsumerUserDto createAdminAccount(User utmp) {

        User u = new User(utmp);
        u.setRole("ADMIN");
        u.setWorkTitle("Admin");
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setActive(false);
        u = this.checkUsernameType(u);


        Optional<User> user;
//        try{
        int userId = userRepository.create(u);

        applicationPreferences.saveUserByUsername(u.getUsername());

        user = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }

        if(user.isPresent()){
//            return user.get();
            return null;
        }else{
            return null;
        }
    }

    private ConsumerUserDto createUserAccount(User user) {
        return null;
    }

    private String generateRefCode(User u) {

//        String nameTmp  = u.getName();
//        nameTmp = nameTmp.toUpperCase();
//        nameTmp = nameTmp.replace(".", "" );
//        nameTmp = nameTmp.replace("_", "" );
//        nameTmp = nameTmp.replace("MD", "" );
//        nameTmp = nameTmp.trim();
//
//        String[] nameArray = nameTmp.split(" ");
//        String refCode = "";
//
//        if(nameArray.length > 2){
//            refCode = nameArray[1];
//        }else if(nameArray.length > 1){
//            refCode = nameArray[0];
//        }else if(nameArray.length > 0){
//            refCode = nameArray[0];
//        }
//
//        if(refCode.length()>8){
//            refCode = refCode.substring(0,8);
//        }
//        if(refCode.length()<3){
//            int left = 48;
//            int right = 57;
//            Random random = new Random();
//            refCode = refCode+random.ints(left, right+1)
//                    .limit(4-refCode.length())
//                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                    .toString();
//        }
//        refCode = generateExtraCode(refCode);
//
//
//        while (!refCodeRepository.getByCode(refCode).isEmpty()){
//            refCode = refCode.substring(0, (refCode.length()-3));
//            refCode = generateExtraCode(refCode);
//        }
//
//        return refCode;
        return "";
    }

    private String generateExtraCode(String refCode) {
//        Random random = new Random();
//
//        int left = 48;
//        int right = 57;
//
//        refCode = refCode+random.ints(left, right+1)
//                .limit(1)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//
//        left = 65;
//        right = 90;
//        refCode = refCode+random.ints(left, right+1)
//                .limit(2)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//
//        return refCode;
        return "";
    }

    @Override
    public ConsumerUserDto create(User u) {
        return null;
    }

    @Override
    public ConsumerUserDto update(User u) {

//        List<Address> children = new ArrayList<>();
//
//        ConsumerUserDto user = getById(u.getId());
//
//        for (AddressDto dto : u.getAddresses()) {
//            Address child;
//            if (dto.getId() == 0) {
//                //CREATE MODE: create new child
//                child = new Address();
////                child.setUser(user); //associate parent
//            } else {
//                //UPDATE MODE : fetch by id
//                child = addressRepository.getById(dto.getId());
//            }
//
//            BeanUtils.copyProperties(dto, child);//copy properties from dto
//            children.add(child);
//        }
//        user.getAddresses().clear();
//        user.getAddresses().addAll(children);
//
//        Optional<User> userReturn;
//        try{
//            int userId = userRepository.update(user);
//            userReturn = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }
//
//        if(userReturn.isPresent()){
//            return userReturn.get();
//        }else{
//            return null;
//        }
        return null;
    }

    @Override
    public List<ConsumerUserDto> getAll(Pageable pageable, ListFilter listFilter) {
        Optional<List<User>> products = Optional.ofNullable(userRepository.getAll(pageable, listFilter));

        if(products.isPresent()){
//            return products.get();
            return null;
        }else{
            return null;
        }
    }


    @Override
    public ConsumerUserDto getById(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));

        if(user.isPresent()){
//            return user.get();
            return null;
        }else{
            return null;
        }

    }

    @Override
    public ConsumerUserDto getUserByUserName(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserByUserName(username));
        if(user.isPresent()){
//            return user.get();
            return null;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return userRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public ConsumerUserDto getByValue(String key, String value) {
        User user = userRepository.getByKeyAndValue(key, value).get(0);

//        return user;
        return null;
    }

    @Override
    public List<ConsumerUserDto> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));
        if(user.isPresent()){
            if(user.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return user.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return userRepository.getPageable(pageable);
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return 0;
    }

    public boolean isUserExist(String userName){
        return userRepository.isUserExist(userName);
    }

    protected User checkUsernameType(User u){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(u.getUsername());
        if(mat.matches()){
            u.setUsernameType("EMAIL");
        }else{
            u.setUsername(u.getUsername().replace("+88", ""));
            u.setUsername(u.getUsername().replace("88", ""));

//            Pattern pattern2 = Pattern.compile("\\d{11}$");
//            Matcher mat2 = pattern2.matcher(u.getUsername());
//            if(mat.matches()) {
            u.setUsernameType("MOBILE");
//            }
        }
        return u;
    }
}