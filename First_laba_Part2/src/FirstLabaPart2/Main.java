package FirstLabaPart2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<FirstTable> ft = new ArrayList<>();
        ft.add(new FirstTable("Ivan1",18,10));
        ft.add(new FirstTable("Ivan2",43,12));
        ft.add(new FirstTable("Ivan3",56,32));
        ft.add(new FirstTable("Ivan4",19,45));
        ft.add(new FirstTable("Ivan5",16,27));
        ft.add(new FirstTable("Ivan6",25,67));
        ft.add(new FirstTable("Ivan7",44,38));
        ft.add(new FirstTable("Ivan8",78,80));
        ft.add(new FirstTable("Ivan9",27,83));
        ft.add(new FirstTable("Ivan10",34,41));

        ArrayList<SecondTable> st = new ArrayList<>();
        st.add(new SecondTable(78,"Department9"));
        st.add(new SecondTable(27,"Department10"));
        st.add(new SecondTable(34,"Department6"));
        st.add(new SecondTable(16,"Department3"));
        st.add(new SecondTable(25,"Department8"));
        st.add(new SecondTable(44,"Department5"));
        st.add(new SecondTable(18,"Department1"));
        st.add(new SecondTable(43,"Department2"));
        st.add(new SecondTable(56,"Department4"));
        st.add(new SecondTable(19,"Department7"));


        ft=setLinks(ft,st);
        QuickSortSal(ft,0,ft.size()-1);

        for (FirstTable aFt : ft) {
            System.out.println(aFt.salary);
        }


        for(int i=0;i<st.size();i++){
            System.out.println(st.get(ft.get(i).link).department+" Age: "+ st.get(ft.get(i).link).age);
        }





    }

    public static ArrayList<FirstTable> setLinks(ArrayList<FirstTable> firstTable,ArrayList<SecondTable> secondTable){
        QuickSortAge(secondTable,0,secondTable.size()-1);
        QuickSortAgeFt(firstTable,0,firstTable.size()-1);
        for(int i=0;i<firstTable.size();i++){
            firstTable.get(i).setLink(i);
        }
        return firstTable;
    }

    public static void QuickSortSal(ArrayList<FirstTable> ft,int start,int end ){
        if (ft.size() == 0)
            return;

        if (start >= end)
            return;

        int middle = start + (end - start) / 2;
        int opora = ft.get(middle).salary;

        int i = start, j = end;
        while (i <= j) {
            while (ft.get(i).salary < opora) {
                i++;
            }

            while (ft.get(j).salary > opora) {
                j--;
            }

            if (i <= j) {
                swap(ft,i++,j--);
            }
        }

        if (start < j)
            QuickSortSal(ft, start, j);

        if (end > i)
            QuickSortSal(ft, i, end);
    }

    public static void QuickSortAge(ArrayList<SecondTable> st,int start,int end ){
        if (st.size() == 0)
            return;
        if (start >= end)
            return;

        int middle = start + (end - start) / 2;
        int opora = st.get(middle).age;

        int i = start, j = end;
        while (i <= j) {
            while (st.get(i).age < opora) {
                i++;
            }

            while (st.get(j).age > opora) {
                j--;
            }

            if (i <= j) {
                SecondTable temp = st.get(i);
                st.set(i,st.get(j));
                st.set(j,temp);
                i++;j--;
            }
        }

        if (start < j)
            QuickSortAge(st, start, j);

        if (end > i)
            QuickSortAge(st, i, end);
    }
    public static void QuickSortAgeFt(ArrayList<FirstTable> ft,int start,int end ){
        if (ft.size() == 0)
            return;

        if (start >= end)
            return;

        int middle = start + (end - start) / 2;
        int opora = ft.get(middle).age;

        int i = start, j = end;
        while (i <= j) {
            while (ft.get(i).age < opora) {
                i++;
            }

            while (ft.get(j).age > opora) {
                j--;
            }

            if (i <= j) {
                swap(ft,i++,j--);
            }
        }

        if (start < j)
            QuickSortAgeFt(ft, start, j);

        if (end > i)
            QuickSortAgeFt(ft, i, end);
    }

    public static void swap(ArrayList<FirstTable> ft, int a, int b){
        FirstTable temp = ft.get(a);
        ft.set(a,ft.get(b));
        ft.set(b,temp);

    }

}
