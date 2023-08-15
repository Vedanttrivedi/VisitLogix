package view;

import controller.Service;
import model.Visitor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Client
{
    static void printVisitorsData(List<Visitor> visitors)
    {
        System.out.println("Visitor Data ");
        for(Visitor visitor:visitors)
        {
            System.out.println(visitor);
        }
    }
    public static void main(String[] args)
    {
        Service service = new Service();
        //add 5 houses
        service.addHouse("N-304","1234567890","Sanjay Trivedi");
        service.addHouse("D-302"," 0234567822","Sanjay Dave");
        service.addHouse("A-202"," 1111222334","Vandan Patel");
        service.addHouse("C-502"," 2345678912","Mukesh Choudhary");
        service.addHouse("D-104"," 4442221112","Rohan Nair");
        //add some visitors
        try
        {
            service.addVisitor("Suresh"," 2525252525","N-304");
            service.addVisitor("Gambhir"," 2525252525","D-302");
            service.addVisitor("Virat"," 2525252525","N-304");
            service.addVisitor("Sachin"," 2525252525","A-202");
            service.addVisitor("Sehwag"," 2525252525","C-502");
            service.addVisitor("Dhoni"," 2525252525","D-104");
            service.addVisitor("Hardik"," 2525252525","E-303");

            //allow the visitors to exit
            service.visitorExit("Dhoni","D-104");
            service.visitorExit("Sehwag","C-502");
            service.visitorExit("Suresh","N-304");

            //print perticular visitor detail
            System.out.println(service.getVisitor("Sachin","A-202"));

            //print perticular house detail
            System.out.println(service.getHouse("N-304"));

            //List Name, Contact Number, House No for all visitors on a given day
            List<Visitor> thatDayVisitors = service.getVisitorsDay("3-Aug-2023 00:00:00");
            printVisitorsData(thatDayVisitors);
            //List Name, Contact Number, House No for all visitors whose approval is still pending
            //for a given date range.
            List<Visitor> pendings = service.getVisitorsPendingApproval("3-Aug-2023 00:00:00","6-Aug-2023 00:00:00");
            printVisitorsData(pendings);
            //List Name, Contact Number, and House No for all visitors whose approval is
            //declined, and the visitor has left the society
            List<Visitor> left = service.getVisitorsUnApprovedLeft("3-Aug-2023 00:00:00","6-Aug-2023 00:00:00");
            printVisitorsData(left);
        }
        catch (HouseDoesNotExist he)
        {
            he.printStackTrace();
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
        catch (VisitorDoesNotExist ve)
        {
            ve.printStackTrace();
        }
        catch (ExitNotAllowed en)
        {
            en.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
