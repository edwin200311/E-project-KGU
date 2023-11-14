import java.util.ArrayList;
import java.util.Scanner;
public class user implements Manageable{

    String id;
    ArrayList<flower> registeredFlower = new ArrayList<>();

    @Override
    public void read(Scanner scan){
        id=scan.next();
        String flowername;
        flower Flower =null;
        while(true){
            flowername=scan.next();
            if(flowername.equals("0")) break;
            Flower = (flower)store.flowerMgr.find(flowername);
            if(Flower == null) System.out.print("null: "+flowername);
            registeredFlower.add(Flower);
        }
    }

    @Override
    public void print(){
        int costAll=0;
        System.out.printf("%s: ",id);
        for(flower myflower: registeredFlower){
            System.out.printf("%s ",myflower.getname());
            costAll+=myflower.getcost();
        }
        System.out.printf("(총: %d원)",costAll);
        System.out.println();
    }
    @Override
    public boolean matches(String kwd){
        ArrayList<String> flowernameList = new ArrayList<>();
        for(flower myflower : registeredFlower){
            flowernameList.add(myflower.getname());
        }
        if(id.contentEquals(kwd)) return true;
        if(flowernameList.contains(kwd)) return true;
        return false;
    }
}
