package zhh_fu.example.syncContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListExample {


    private static void test1(ArrayList<Integer> list){
        for (Integer i : list){
            if (i.equals(3)){
                list.remove(i);
            }
        }
    }

    private static void test2(ArrayList<Integer> list){
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i.equals(3)){
                list.remove(i);
            }
        }
    }

    private static void test3(ArrayList<Integer> list){
        for (int i=0;i<list.size();i++){
            if (list.get(i).equals(3)){
                list.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        /*
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i.equals(3)){
                iterator.remove();
            }
        }*/
        List<Integer> list = new ArrayList<Integer>();

        /*
        for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
            String val = it.next();
            if (val.equals("5")) {
                //list.add(val); //报错
                it.remove();   //报错
            }
        }
        */
        System.out.println(list.size());
        //test1(list);
        for (int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        Iterator<Integer> it = list.iterator();
        for (int i=list.size() - 1;i >= 0;i--){
            System.out.println(list.get(i));
            list.remove(i);

        }
        //list.remove(0);
        //System.out.println(list.get(0));
        System.out.println(list.size());
    }
}
