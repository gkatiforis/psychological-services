package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.ChatService;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service("conferenceService")
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TherapistDao daoTherapist;

    @Autowired
    private WorkingHourDao daoWorkingHours;

    @Autowired
    private PersonDao daoAdvisedPerson;

    @Autowired
    private ConferenceDao conferenceDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PaymentDetailsDao paymentDetailsDao;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ChatService chatService;

    public Conference findById(Integer conferenceId){
        return conferenceDao.findById(conferenceId);
    }

    public Conference findConferencesByIdFull(Integer conferenceId) {
        return conferenceDao.findByIdFull(conferenceId);
    }

    public void saveConference(Conference conference){
        conferenceDao.save(conference);
    }

    public void updateConference(Conference conference){


        Conference entity = conferenceDao.findById(conference.getId());
        if(entity!=null){
            entity.setExpertJoinedDate(conference.getExpertJoinedDate());
            entity.setPersonJoinedDate(conference.getPersonJoinedDate());
            entity.setCompletedDate((conference.getCompletedDate()));
            entity.setConferencestatus(conference.getConferencestatus());
        }

    }

    public Integer setConferenceCompleted(Integer userId, Integer conferenceId) {
        User user = userDao.findById(userId);
        Conference conference = conferenceDao.findById(conferenceId);
        Integer isCompleted = new Integer(0);


        Therapist therapist = daoTherapist.findById(user.getId());
        Date now = new Date(Calendar.getInstance().getTime().getTime());
        Order order = null;
        if(conference != null){



           if(conference.getTherapist().getId() == user.getId() &&
                   conference.getExpertJoinedDate() != null &&
                   conference.getPersonJoinedDate() != null){

               order = orderDao.findById(conference.getOrder().getId());

               Integer conferenceCountUsed = order.getConferenceCountUsed();
               Integer conferenceCount = order.getService().getConferenceCount();

               PaymentDetails paymentdatails = paymentDetailsDao.findById(1);

               BigDecimal profit = therapist.getAmount();

               com.katiforis.spring.model.Service service = order.getService();
               BigDecimal serviceAmount =service.getAmount();
               BigDecimal conferencePrice = serviceAmount.divide(new BigDecimal(service.getConferenceCount()), RoundingMode.HALF_DOWN);

               BigDecimal myProfit = conferencePrice.multiply(paymentdatails.getProfitPercentage()).divide(new BigDecimal(100));
               BigDecimal therapistProfit = conferencePrice.subtract(myProfit);

               BigDecimal conferenceProfit = profit.add(therapistProfit);

               therapist.setAmount(conferenceProfit);

                 conference.setCompletedDate(now);
                 conference.setConferencestatus(new Conferencestatus(2));


               if(conferenceCount == conferenceCountUsed){
                   order.setOrderStatus(new Orderstatus(2));
               }else{
                   order.setOrderStatus(new Orderstatus(1));
               }

               updateConference(conference);
               notificationService.notifComplteConference(therapist, conference.getAdvisedPerson());

               if(conferenceCountUsed < conferenceCount)
                   notificationService.notifBookNextConference(conference.getAdvisedPerson());

                 isCompleted = 1;

            }



        }

        return isCompleted;
    }

    public Integer setConferenceCanceled(Integer userId, Integer conferenceId) {
       Integer isCanceled =  new Integer(0);
       Conference conference = conferenceDao.findById(conferenceId);
       Date now = new Date(Calendar.getInstance().getTime().getTime());

        if(conference != null){
            if(conference.getAdvisedPerson().getId() == userId &&
             (conference.getExpertJoinedDate() == null || conference.getPersonJoinedDate() == null)){
                Order order = orderDao.findById(conference.getOrder().getId());
                order.setConferenceCountUsed(order.getConferenceCountUsed() - 1);
                order.setOrderStatus(new Orderstatus(1));
                conference.setCompletedDate(now);
                conference.setConferencestatus(new Conferencestatus(3));
                isCanceled = new Integer(1);

            }

            updateConference(conference);
            notificationService.notifCancelConference(conference.getAdvisedPerson(), conference.getTherapist());
        }


        return isCanceled;
    }

    public Integer cancelOrder(Integer userId, Integer orderId) {
        Integer isCanceled = new Integer(0);
        User advisedPerson = daoAdvisedPerson.findById(userId);
        Order order = orderDao.findById(orderId);
        Date now = new Date(Calendar.getInstance().getTime().getTime());

        Integer canceledCounter = new Integer(0);
        if(order.getAdvisedPerson().getId() == advisedPerson.getId()){
                Set<Conference> conferences = order.getConferences();
                for(Conference conference : conferences){
                    if((conference.getExpertJoinedDate() == null || conference.getPersonJoinedDate() == null)
                            && conference.getCompletedDate() == null) {
                        conference.setCompletedDate(now);
                        conference.setConferencestatus(new Conferencestatus(3));
                        updateConference(conference);
                        canceledCounter++;
                    }

                }
            Integer usedConferencesCount = order.getConferenceCountUsed() - canceledCounter;
            order.setConferenceCountUsed(usedConferencesCount);

            Integer sumConferenceCount = order.getService().getConferenceCount();
            Integer unsedConferencesCount = sumConferenceCount - usedConferencesCount;

            BigDecimal serviceAmount = order.getService().getAmount();
            BigDecimal serviceAmountPerConference = serviceAmount.divide(new BigDecimal(sumConferenceCount), RoundingMode.HALF_DOWN);
            BigDecimal moneyBack = serviceAmountPerConference.multiply(new BigDecimal(unsedConferencesCount));

            BigDecimal currentAmount = advisedPerson.getAmount();
            BigDecimal sumAmount = currentAmount.add(moneyBack);
            advisedPerson.setAmount(sumAmount);

            order.setOrderStatus(new Orderstatus(3));
            isCanceled = 1;

            notificationService.notifCancelOrder(advisedPerson, order.getTherapist());
        }

        return  isCanceled;
    }

    public List<Integer> getConferenceCountPerDayInMounth(Integer userId){
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        List<Integer> counts = new ArrayList<>();
        int count = 30;
        int i;
        for(i = 0; i<count; i++){
            List<Conference> counter = conferenceDao.findConferenceCountPerDay(userId, date);
            counts.add(counter.size());
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

        return counts;
    }

    @Transactional
    public void bookConference(Integer userId, Integer therapistId, Date date, Integer workingHourId, String descr, Integer type, Integer orderId){
        AdvisedPerson advisedPerson = daoAdvisedPerson.findById(userId);
        Order order = orderDao.findById(orderId);
        Set<Order> personOrders = advisedPerson.getOrders();

        if(personOrders.contains(order) && order.getOrderStatus().getId() == 1){
            com.katiforis.spring.model.Service service = order.getService();
            Therapist therapist = daoTherapist.findById(therapistId);
            WorkingHour workingHour = daoWorkingHours.findById(workingHourId);
            Conferencestype conferencesType = new Conferencestype(type);
            Conferencestatus conferencestatus = new Conferencestatus(1);


            String roomName = "roomName";

            Integer conferenceCountUsed = order.getConferenceCountUsed();
            Integer conferenceCount = service.getConferenceCount();
            order.setOrderStatus(new Orderstatus(4));
            order.setConferenceCountUsed(conferenceCountUsed + 1);

            String title ="dff";// service.getTitle() + " (" + service.getConferenceCount() +")";

            Conference conference = new Conference(date, workingHour.getWorkingTime(), title, descr, roomName,
                   therapist, advisedPerson, workingHour,  conferencesType, conferencestatus, order);

            Set<User> users = new HashSet<User>();
            users.add(advisedPerson);
            users.add(therapist);

           conferenceDao.save(conference);
           chatService.addConversation(users);
           notificationService.notifAddConference(advisedPerson, therapist);

       }
    }

    @Transactional
    public List<Order> findConferencesByCritiria(Integer userId, Integer orderstatus){

        //  List<Conference> conferences = conferenceDao.findConferenceByCritiria(userId, conferencestatus);

        List<Order> orders = null;
        if(orderstatus == 1){
            orders = orderDao.findUserOrdersLiveOrders(userId);
        }else  if(orderstatus == 2){
            orders = orderDao.findUserOrdersCompletedOrders(userId);
        }


        for (Iterator<Order> i = orders.iterator(); i.hasNext();) {
            Order order= i.next();
            Set<Conference> cons = order.getConferences();
            for (Iterator<Conference> ii = cons.iterator(); ii.hasNext();) {
                Conference con = ii.next();
                Calendar c1 = Calendar.getInstance(); // today

                Calendar c2 = Calendar.getInstance();
                c2.setTime(con.getDate());

                if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
                    con.setDateWhen("Σήμερα");
                }else {
                    c2.add(Calendar.DAY_OF_YEAR, -1); // yesterday
                    if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
                        con.setDateWhen("Αύριο");
                    }else{
                        con.setDateWhen((c2.get(Calendar.DAY_OF_MONTH) + 1) +"/"+ (c2.get(Calendar.MONTH) + 1));
                    }
                }
            }
        }
        return orders;
    }
}
