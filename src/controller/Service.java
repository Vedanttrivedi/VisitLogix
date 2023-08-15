package controller;

import model.House;
import model.Visitor;
import view.ExitNotAllowed;
import view.HouseDoesNotExist;
import view.VisitorDoesNotExist;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Service
{
    private HashMap<String,Visitor> visitors;
    private long totalVisitors;
    private HashMap<String, House> houses;

    private SimpleDateFormat dateFormat;
    public Service()
    {
        visitors = new HashMap<>();
        houses =new HashMap<>();
        totalVisitors=0;
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    }
    public void addHouse(String houseNo,String contactNo,String houseOwner)
    {
        houses.put(houseNo,new House(houseNo,contactNo,houseOwner));
    }
    public void addVisitor(String visitorName,String contactNo,String houseNo) throws HouseDoesNotExist
    {
        ++totalVisitors;
        if(houses.get(houseNo)==null)
            throw new HouseDoesNotExist("Invalid House Number");
        visitors.put(visitorName,new Visitor(totalVisitors,visitorName,contactNo,houseNo,(String.valueOf(totalVisitors)+"-Aug-2023 8:30:50")));
        approve(visitorName,houseNo);
    }
    public House getHouse(String houseNo) throws HouseDoesNotExist
    {
        if(houses.get(houseNo)==null)
            throw new HouseDoesNotExist("Invalid House Number");
        return houses.get(houseNo);
    }
    public void visitorExit(String visitorName,String houseNo) throws ParseException, ExitNotAllowed
    {
        String entryTime = visitors.get(visitorName).getEntryTime();
        Date date = dateFormat.parse(entryTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int entryHour = calendar.get(Calendar.HOUR_OF_DAY);

        Random random = new Random();
        int randomExitHour = (24 - entryHour + 1) + entryHour;
        randomExitHour = random.nextInt(randomExitHour);
        calendar.set(Calendar.HOUR_OF_DAY, randomExitHour);

        if (disapproval(visitorName, houseNo)) {
            throw new ExitNotAllowed("You cannot exit as the house owner disapproved your exit");
        }

        visitors.get(visitorName).setExitTime(dateFormat.format(calendar.getTime()));
    }
    private boolean disapproval(String visitorName,String houseNo)
    {
        //if this method returns true than it means house owner is not allowing the visitor to exit
        Random random = new Random();
        return random.nextBoolean();
    }
    private void approve(String visitorName,String houseNo)
    {
        //send message to house
        int messageStatus=sendHouseMessage(visitorName,houseNo);
        if(messageStatus==0)
        {
            //visitor is allowed
            visitors.get(visitorName).setVisitStatus(0);
        }
        else if(messageStatus==1)
        {
            //message is not seen or no response,set the status as pending
            visitors.get(visitorName).setVisitStatus(1);
        }
        //house owner declined the visitor approval ,so remove set the visitor status as declined
        visitors.get(visitorName).setVisitStatus(2);
    }
    private int sendHouseMessage(String visitorName,String houseNo)
    {
        Random random = new Random();
        return random.nextInt(3);
    }
    public Visitor getVisitor(String visitorName,String houseNo) throws VisitorDoesNotExist
    {
        if(visitors.get(visitorName)==null)
            throw new VisitorDoesNotExist("Invalid visitor name!");
        return visitors.get(visitorName);
    }

    public List<Visitor> getVisitorsDay(String date) throws ParseException
    {
        Date day = dateFormat.parse(date);
        Calendar dayCalendar = Calendar.getInstance();
        dayCalendar.setTime(day);
        int targetYear = dayCalendar.get(Calendar.YEAR);
        int targetMonth = dayCalendar.get(Calendar.MONTH);
        int targetDay = dayCalendar.get(Calendar.DAY_OF_MONTH);
        ArrayList<Visitor> dayVisitors = new ArrayList<>();
        for(Map.Entry<String,Visitor> visitor:visitors.entrySet())
        {
            Visitor vs = visitor.getValue();
            String entryTime = vs.getEntryTime();
            Date entryDate = dateFormat.parse(entryTime);

            Calendar entryCalendar = Calendar.getInstance();
            entryCalendar.setTime(entryDate);
            int entryYear = entryCalendar.get(Calendar.YEAR);
            int entryMonth = entryCalendar.get(Calendar.MONTH);
            int entryDay = entryCalendar.get(Calendar.DAY_OF_MONTH);

            if (targetYear == entryYear && targetMonth == entryMonth && targetDay == entryDay) {
                dayVisitors.add(vs);
            }
        }
        return dayVisitors;
    }

    public List<Visitor>  getVisitorsPendingApproval(String date_from, String date_to) throws ParseException
    {
        List<Visitor> pendingApprovalVisitors = new ArrayList<>();

        Date startDate = dateFormat.parse(date_from);
        Date endDate = dateFormat.parse(date_to);

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        for (Visitor visitor : visitors.values()) {
            Date entryDate = dateFormat.parse(visitor.getEntryTime());

            Calendar entryCalendar = Calendar.getInstance();
            entryCalendar.setTime(entryDate);

            if (entryCalendar.after(startCalendar) && entryCalendar.before(endCalendar) && visitor.getVisitStatus()==1) {
                pendingApprovalVisitors.add(visitor);
            }
        }
        return pendingApprovalVisitors;
    }


    public List<Visitor>  getVisitorsUnApprovedLeft(String date_from, String date_to) throws ParseException
    {
        List<Visitor> leftVisitors = new ArrayList<>();

        Date startDate = dateFormat.parse(date_from);
        Date endDate = dateFormat.parse(date_to);

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        for (Visitor visitor : visitors.values()) {
            Date entryDate = dateFormat.parse(visitor.getEntryTime());

            Calendar entryCalendar = Calendar.getInstance();
            entryCalendar.setTime(entryDate);

            if (entryCalendar.after(startCalendar) && entryCalendar.before(endCalendar) && visitor.getVisitStatus()==2) {
                leftVisitors.add(visitor);
            }
        }
        return leftVisitors;
    }
}
