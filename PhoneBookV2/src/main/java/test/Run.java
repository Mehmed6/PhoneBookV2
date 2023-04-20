package test;

import category.Family;
import category.ICategory;
import category.Social;
import category.Work;
import enums.Privilege;
import person.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Run {
    private static HashMap<Integer, Person> persons = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int personCount = 0;
    public static void run()
    {


        System.out.println("Test Simulasyonuna Hosgeldiniz..");
        String islemler = "1. Creat\n" +
                          "2. Read\n" +
                          "3. Uptade\n" +
                          "4. Delete\n" +
                          "5. ShowAllPersons\n" +
                          "6. CallAnotherPerson\n" +
                          "7. Quit\n";

        while (true) {

            System.out.println("********* Uygun İşlemler *********");
            System.out.print(islemler);
            System.out.print("Lütfen Yapmak İstediğiniz İşlemi Seçiniz: ");
            String islem = scanner.nextLine();

            switch (islem) {
                case "1" -> creat();
                case "2" -> read();
                case "3" -> uptade();
                case "4" -> delete();
                case "5" -> showAllPersons();
                case "6" -> callAnotherPerson();
                case "7" -> {
                    System.out.println("Simulasyondan çıkılıyor..");
                    return;
                }
                default -> System.out.println("Yanlış İşlem Girdiniz.. Tekrar Deneyiniz!!");
            }

            System.out.println("-------------------------------------------------------");
        }

    }
    public static void creat()
    {
        System.out.print("Eklemek istediğiniz kisinin adını giriniz: ");
        String name = scanner.nextLine();
        System.out.print("Eklemek istediğiniz kisinin ID'sini giriniz: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Map.Entry<Integer, Person> entry : persons.entrySet())
            while (id == entry.getValue().getId()) {
                System.out.print(id + " ID'sine sahip eleman zaten bulunuyor. Lütfen baska bir ID ekleyiniz: ");
                id = Integer.parseInt(scanner.nextLine());
            }

        System.out.print("Eklemek istediğiniz kisinin numarasını giriniz: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.print("Eklemek istediğiniz kisiyi hangi Categoriye eklemek istersiniz('Family', 'Work', 'Social'): ");
        String categori = scanner.nextLine();
        ICategory category;

        if (categori.equals("Family") || categori.equals("family"))
            category = new Family();
        else if (categori.equals("Work") || categori.equals("work"))
            category = new Work();
        else
            category = new Social();

        System.out.print("Eklemek istediğiniz kisinin Önceliğini Belirleyiniz: ('Family', 'Work', 'Social'): ");
        String priv = scanner.nextLine();
        Privilege privilege;
        if (priv.equals("Family") || priv.equals("family"))
            privilege = Privilege.FAMILY;
        else if (priv.equals("Work") || priv.equals("work"))
            privilege = Privilege.WORK;
        else
            privilege = Privilege.SOCIAL;


        personCount++;
        persons.put(personCount, new Person(name, id, number, category, privilege));
        System.out.println("Kisi Basarıyle Eklendi...");


    }
    public static void read()
    {
        if (isEmpty(persons))
            return;

        boolean flag = false;
        System.out.print("Görmek istediğiniz kisinin ID'sini giriniz: ");
        int readId = Integer.parseInt(scanner.nextLine());

        for (Map.Entry<Integer, Person> entry : persons.entrySet()) {
            if (entry.getValue().getId() == readId) {
                System.out.println(entry.getValue());
                flag = true;
            }

        }

        if (!flag)
            System.out.println(readId + " idsine sahip bir kisi bulunmuyor!!!");

    }
    public static void uptade()
    {
        if (isEmpty(persons))
            return;

        boolean flag = false;
        System.out.print("Güncellemek istediğiniz kisinin ID'sini giriniz: ");
        int uptadeId = Integer.parseInt(scanner.nextLine());

        for (Map.Entry<Integer, Person> entry : persons.entrySet())
            if (entry.getValue().getId() == uptadeId) {
                System.out.print("Kisinin adını güncellemek ister misiniz? (Yes or No): ");
                String name = scanner.nextLine();
                if (name.equals("Yes") ||name.equals("yes")) {
                    System.out.print("Yeni ismi giriniz: ");
                    entry.getValue().setName(scanner.nextLine());
                }
                System.out.print("Kisinin ID'sini güncellemek ister misiniz? (Yes or No): ");
                String newId = scanner.nextLine();
                if (newId.equals("Yes") ||newId.equals("yes")) {
                    System.out.print("Yeni ID'yi giriniz: ");
                    entry.getValue().setId(Integer.parseInt(scanner.nextLine()));
                }
                System.out.print("Kisinin numarasını güncellemek ister misiniz? (Yes or No): ");
                String newNumber = scanner.nextLine();
                if (newNumber.equals("Yes") || newNumber.equals("yes")) {
                    System.out.print("Yeni numara giriniz: ");
                    entry.getValue().setNumber(Integer.parseInt(scanner.nextLine()));
                }
                System.out.print("Kisinin Kategorisini güncellemek ister misiniz? (Yes or No): ");
                String newCategory = scanner.nextLine();
                if (newCategory.equals("Yes") || newCategory.equals("yes")) {
                    System.out.print("Yeni Kategori giriniz: ");
                    String cate = scanner.nextLine();

                    switch (cate) {
                        case "family", "Family" -> entry.getValue().setCategory(new Family());
                        case "work", "Work" -> entry.getValue().setCategory(new Work());
                        case "social", "Social" -> entry.getValue().setCategory(new Social());
                    }
                }
                System.out.print("Kisinin Önceliğini güncellemek ister misiniz? (Yes or No): ");
                String newPriv = scanner.nextLine();
                if (newPriv.equals("Yes") || newPriv.equals("yes")) {
                    System.out.print("Yeni Öncelik giriniz: ");
                    String priv = scanner.nextLine();

                    switch (priv) {
                        case "family", "Family" -> entry.getValue().setPrivilege(Privilege.FAMILY);
                        case "work", "Work" -> entry.getValue().setPrivilege(Privilege.WORK);
                        case "social", "Social" -> entry.getValue().setPrivilege(Privilege.SOCIAL);
                    }
                }

                flag = true;
            }

        if (!flag)
            System.out.println(uptadeId + " ID'sine sahip kisi bulunmuyor..");

        System.out.println("Kisi Basarıyle Güncellendi...");

    }
    public static void delete()
    {
        if (isEmpty(persons))
            return;

        boolean flag = false;

        System.out.print("Silmek istediğiniz kisinin ID'sini giriniz: ");
        int deleteId = Integer.parseInt(scanner.nextLine());

        for (Map.Entry<Integer, Person> entry : persons.entrySet()) {
            if (entry.getValue().getId() ==  deleteId) {
                persons.remove(entry.getKey());
                flag = true;
                break;
            }

        }

        if (!flag)
            System.out.println(deleteId + " ID'sine sahip kisi bulunmuyor..");

        System.out.println("Kisi Basarıyle Silindi...");

    }
    public static void showAllPersons()
    {
        if (isEmpty(persons))
            return;

        for (Map.Entry<Integer, Person> entry : persons.entrySet())
            System.out.println(entry.getValue() + " | " + entry.getKey() + ". sırada eklendi");

    }

    public static boolean isEmpty(HashMap<Integer, Person> persons)
    {
        if (persons.isEmpty()) {
            System.out.println("Suan Kişi Listesi Bos..");
            return true;
        }

        return false;
    }

    public static void callAnotherPerson()
    {
        if (isEmpty(persons))
            return;
        boolean callerFlag = false;
        boolean receiverFlag = false;


        System.out.print("Arama yapmak isteyen kisinin ID'sini giriniz: ");
        int callerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Aranmak istenen kisinin ID'sini giriniz: ");
        int receiverId = Integer.parseInt(scanner.nextLine());
        Person callerPerson = null;
        Person receiverPerson = null;

        for (Map.Entry<Integer, Person> entry : persons.entrySet()) {
            if (entry.getValue().getId() == callerId) {
                callerPerson = entry.getValue();
                callerFlag = true;
            }


            if (entry.getValue().getId() == receiverId) {
                receiverPerson = entry.getValue();
                receiverFlag = true;
            }


        }

        if (!callerFlag)
            System.out.println("Arayan kisinin ID'sine sahip kisi bulunamadı..");
        if (!receiverFlag)
            System.out.println("Aranan kisinin ID'sine sahip kisi bulunamadı..");

        if (!callerFlag || !receiverFlag)
            return;

        callerPerson.callPerson(receiverPerson);

    }




}
