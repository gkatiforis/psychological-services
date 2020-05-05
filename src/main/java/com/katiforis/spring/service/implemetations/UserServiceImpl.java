package com.katiforis.spring.service.implemetations;

import java.math.BigDecimal;
import java.util.*;

import com.katiforis.spring.Respones.JsonRespone;
import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.NotificationService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao daoUser;

    @Autowired
    private OrderDao daoOrder;

    @Autowired
    private DisorderDao daoDisorders;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NotificationService notificationService;


    public User findById(int id) {
        return daoUser.findById(id);
    }

    public User findBySSO(String sso) {
        User user = daoUser.findBySSO(sso);
        return user;
    }

    public User findByEmail(String email) {
        User user = daoUser.findByEmail(email);
        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getLast_name() == null){
            user.setAmount(new BigDecimal(0.0));
        }
        daoUser.save(user);
    }


    public JsonRespone registerUser(User user){
        Map<String, String> errors  = new HashMap<String, String>();
        JsonRespone respone = new JsonRespone(true);

        if(  !isUserSSOUnique(user.getId(), user.getSsoId())){
            respone.setValidated(false);
            errors.put("ssoId", "Ο χρήστης υπάρχει ήδη");
        }
        if( !isUserEmailUnique(user.getId(), user.getEmail())){
            respone.setValidated(false);
            errors.put("email", "Ο χρήστης υπάρχει ήδη");
        }

        respone.setErrorMessages(errors);

        if(respone.isValidated()){
            saveUser(user);
            respone.setValidated(true);
            respone.setUser(user);

            notificationService.notifWelcome(user);

        }

        return respone;
    }


    public List<User> findAllUsers() {
        return daoUser.findAllUsers();
    }



    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }


    public boolean isUserEmailUnique(Integer id, String email) {
        User user = findByEmail(email);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }



    public List<Disorder> findAllDisorders(){
        return daoDisorders.findAllDisorders();
    }
    public void addDisordersToUser(Integer userId, List<Integer> disordersIds) {
        User user = daoUser.findById(userId);
        disordersIds.forEach(id -> user.getDisorders().add(new Disorder(id,"")) );
    }
    

    public void removeDisordersFromUser(Integer userId, List<Integer> disordersIds) {
        User user = daoUser.findById(userId);
        disordersIds.forEach(id -> user.getDisorders().removeIf(disorder -> disorder.getId() == id));
    }





    public List<Order> findUserOrders(Integer userId){
        List<Order> orders =  daoOrder.findUserOrdersLiveOrders(userId);
        return orders;
    }


    public List<Disorder> getDisordersHave(Integer userId, Integer have){

        if(have == 0){
            return daoDisorders.findDisordersUserNotHave(userId);
        }
        else if(have == 1){
            return daoDisorders.findDisordersUserHave(userId);
        }

        return null;
    }


}
