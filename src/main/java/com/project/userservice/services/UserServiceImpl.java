package com.project.userservice.services;

import com.project.userservice.exceptions.InvalidTokenException;
import com.project.userservice.models.Token;
import com.project.userservice.models.User;
import com.project.userservice.repositories.TokenRepository;
import com.project.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    TokenRepository tokenRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
    TokenRepository tokenRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public User signUp(String name, String email, String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) {
        System.out.println(email+ " " + password);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            return null;
        }


        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches( password,user.getPassword())){
            return null;
        }
        Token token = new Token();
        token.setUser(user);
//         generating random string (apache common lang)
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

//        add 30 days to the present date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,30);

        Date date = calendar.getTime();
        token.setExpiryAt(date);

        return tokenRepository.save(token);
    }

    @Override
    public User logout(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findTokenByValueAndDeletedAndExpiryAtGreaterThan(tokenValue,false,new Date());

       if(optionalToken.isEmpty()){
           throw new InvalidTokenException("invalid token or expired token");
       }
       Token token = optionalToken.get();
       token.setDeleted(true);

       return tokenRepository.save(token).getUser();
    }

    @Override
    public User validateToken(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findTokenByValueAndDeletedAndExpiryAtGreaterThan(tokenValue,false,new Date());

//        if (optionalToken.isPresent()) {
//            return optionalToken.get().getUser();
//        }

//            return null;


        return optionalToken.map(Token::getUser).orElse(null);
    }
}
