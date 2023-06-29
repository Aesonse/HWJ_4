        import java.io.FileReader;
        import java.io.BufferedReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.Comparator;
        import java.util.LinkedList;
        import java.util.Scanner;
        import java.util.ArrayList;
/*
1.Организовать вводи и хранение данных пользователей. ФИО возраст и пол
2.вывод в формате Фамилия И.О. возраст пол
3.добавить возможность выхода или вывода списка отсортированного по возрасту!)
*реализовать сортировку по возрасту с использованием индексов
*реализовать сортировку по возрасту и полу с использованием индексов (Не реализована) */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> fioList = new ArrayList<>();
        ArrayList<Integer> ageList = new ArrayList<>();
        ArrayList<String> sexList = new ArrayList<>();

        dataInput("fio.txt", "age.txt", "sex.txt");

        dataRead("fio.txt", fioList);
        dataReadInt("age.txt", ageList);
        dataRead("sex.txt", sexList);
        convertFio(fioList);
        LinkedList<Integer> ageSortedInd = SortedIndexes(ageList);

        dataPrint(fioList, ageList, sexList, ageSortedInd);

    }

    static void dataInput(String fioDataFileName, String ageDataFileName, String sexDataFileName) {
        try (FileWriter fioWriter = new FileWriter(fioDataFileName, true);
        FileWriter ageWriter = new FileWriter(ageDataFileName,true);
        FileWriter sexWriter = new FileWriter(sexDataFileName,true)) {

            String fio = new String();
            Scanner in = new Scanner(System.in);
            while (!fio.toLowerCase().equals("exit")){
                System.out.print("Введите ФИО следующего пользователя или exit для завершения: ");
                fio = in.nextLine();
                if (fio.toLowerCase().equals("exit")) continue;
                fioWriter.write(fio+"\n");
                System.out.print("Введите возраст: ");
                ageWriter.write(in.nextLine()+"\n");
                System.out.print("Введите пол: ");
                sexWriter.write(in.nextLine()+"\n");
            }
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static void dataRead(String DataFileName, ArrayList<String> dataList) {
        BufferedReader bufR = null;
        try {
            bufR = new BufferedReader (new FileReader(DataFileName));
            String line = new String();
            while ((line = bufR.readLine()) != null) {
                dataList.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static void dataReadInt(String DataFileName, ArrayList<Integer> dataList) {
        BufferedReader bufR = null;
        try {
            bufR = new BufferedReader(new FileReader(DataFileName));
            String line = new String();
            while ((line = bufR.readLine()) != null) {
                dataList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static void convertFio(ArrayList<String> fioList) {
        String[] lst = new String[3];
        for (int i = 0; i < fioList.size(); i++) {
            lst = fioList.get(i).split(" ");
            lst[1] = String.valueOf(lst[1].charAt(0)) + ".";
            lst[2] = String.valueOf(lst[2].charAt(0)) + ".";
            fioList.set(i, String.join(" ", lst));
        }
    }

    static LinkedList<Integer> SortedIndexes(ArrayList<Integer> dataList) {
        LinkedList<Integer> indexSort = new LinkedList<>();
        for (int i = 0; i < dataList.size(); i++) {
            indexSort.add(i);
        }
        indexSort.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dataList.get(o1) - dataList.get(o2);
            }
        });
        return indexSort;
    }
    static void dataPrint(ArrayList<String> fioList,
                          ArrayList<Integer> ageList,
                          ArrayList<String> sexList,
                          LinkedList<Integer> indexes) {
        for (int i: indexes) {
            System.out.print(fioList.get(i));
            System.out.print(" " + ageList.get(i) + " ");
            System.out.println(sexList.get(i));
        }
    }
}
