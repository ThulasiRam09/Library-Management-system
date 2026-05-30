package libraryProject;

import java.util.*;

//Library Management System In Java

abstract class Library{
    abstract void addBook(Book book);
    abstract void removeBook(int bookId);
    abstract void issueBook(int bookId, String userName);
    abstract void returnBook(int bookId);
    abstract void displayBooks();
}
class MainLibrary extends Library {
    Scanner sc = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();//to Add Book Objects
    HashMap<String,User> users = new HashMap<>();//to Add username and user objects into hashmap
    HashMap<Integer, String> issuedBooks = new HashMap<>();//store key pair value of books and users

    //To Register
    public void registerUser(User user){
        if(users.containsKey(user.userName)){ // user already exits in hashmap(database)
            System.out.println("User Name already exits!🤔");
            return;
        }
        users.put(user.userName,user);// add new user into the database
        System.out.println("*******************************");
        System.out.println("User Registered Successfully🎉");
        System.out.println("*******************************");
    }

    //Login
    User login(String username,String password){
        User user = users.get(username);
        if(user != null && user.passWord.equals(password)){
            System.out.println("*********************");
            System.out.println("Login SuccessFull😄\nWelcome! \""+username+"\".");
            System.out.println("*********************");
            return user;
        }else{
            System.out.println("Invalid Username or PassWord!❌");
            return null;
        }
    }

    //  Adding new Book into the Library
    @Override
    public void addBook(Book book){
        books.add(book);
        System.out.println("Book has added Successfully :)📔");
    }

    //  Removing Book from Library
    @Override
    public void removeBook(int bookId){
        books.removeIf(book -> book.bookId == bookId);
        issuedBooks.remove(bookId);
        System.out.println("Book Successfully Removed.✅");
    }

    //  Issuing a Book to the customer if it is in the library or not
    @Override
    public void issueBook(int bookId, String userName){
        for(Book book : books){
            if(book.bookId == bookId && !book.isIssued ){
                book.isIssued=true;
                issuedBooks.put(bookId, userName);
                System.out.println("Book issued Successfully..📖");
                return;
            }
        }
        System.out.println("Book Not Available.❌");
    }

    @Override
    public void returnBook(int bookId){
        if(!issuedBooks.containsKey(bookId)){
            System.out.println("Book Was Not Issued!✖️"); //if book is not issued to customer from library
            return;
        }
        for(Book book : books){
            if(book.bookId == bookId){
                book.isIssued=false;
                break;
            }
        }
        issuedBooks.remove(bookId); //removing from library(hash map)
        System.out.println("BOOK successfully Returned :)✅😄");
    }

    @Override
    public void displayBooks(){
        System.out.println("\nBooks List: ");
        for(Book b : books){
            System.out.println(b.getBookId() + ".) " +b.getTitle()+".\nAuthor of the Book is: "+b.getAuthor() + " || Issued: " +b.isIssued+"\n-------------------------------------------------------------" );
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library lib = new MainLibrary();
        MainLibrary mainLibrary = new MainLibrary();
        User logginedUser=null;
        while(true) {
            System.out.println("--------------------------------------");
            System.out.println("WELCOME TO NATIONAL LIBRARY..📚📖📚📖");
            System.out.println("--------------------------------------");
            while(logginedUser == null){
                //to register and login
                System.out.println("-----Login Menu-----");
                System.out.println("1. Register\n2. Login\n3. Exit ");
                System.out.println("Click 1 if you are a new user \n2 If you are an existing user. and \n3 To EXIT from the Library System.\nThank You..");
                System.out.println("-------------------------------------" );

                System.out.println("\nEnter your choice:");
                int choice1=0;
                try {
                    choice1 = scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException e){
                    System.out.println("Enter only Choices.."+e);
                    return;
                }

                int uid=0;
                String uname="";
                String un="";
                String pw="";
                if(choice1 == 1){
                    try {
                        System.out.print("Enter User ID: ");
                        uid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\nEnter your Name: ");
                        uname = scanner.nextLine();
                        System.out.print("\nEnter your UserName: ");
                        un = scanner.nextLine();
                        System.out.print("\nEnter your correct Password: ");
                        pw = scanner.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println("Enter Correct Details!!"+ e);
                        return;
                    }

                    User user = new User(uid,uname,un,pw);
                    mainLibrary.registerUser(user);
                    //mainLibrary.registerUser(new User(uid,uname,un,pw));
                }else if(choice1 == 2){
                    try {
                        System.out.print("Enter your UserName: ");
                        un = scanner.nextLine();
                        System.out.print("Enter your correct Password: ");
                        pw = scanner.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println("Enter Valid Details Only!!");
                    }

                    logginedUser=mainLibrary.login(un,pw);

                }else if(choice1 == 3){
                    System.out.println("EXISTING SYSTEM..🚶");
                    System.exit(0);
                }
            }

            System.out.println("------Library Menu------\n");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. LogOut");
            System.out.println("------------------");
            System.out.print("Enter choice: ");

            int choice2 = scanner.nextInt();

            int bid=0,uid=0;
            String title="";
            String author="";
            //Enhanced Switch
            switch (choice2){
                case 1->{
                    try {
                        System.out.print("\nEnter Book Id: ");
                        bid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\nEnter Title of the Book: ");
                        title = scanner.nextLine();
                        System.out.print("\nEnter Author of the Book: ");
                        author = scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Enter Suitable Details! "+e);
                        return;
                    }

                    lib.addBook(new Book(bid,title,author));
                }
                case 2 ->{
                    try {
                        System.out.print("Enter Book ID to remove: ");
                        bid = scanner.nextInt();
                    }catch (InputMismatchException e) {
                        System.out.println("Enter only ID! " + e);
                        return;
                    }
                    lib.removeBook(bid);
                }
                case 3->{
                    try {
                        System.out.print("Enter Book ID: ");
                        bid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\nEnter the Name of the Book: ");
                        title = scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Please Enter Correct details "+e);
                        return;
                    }

                    lib.issueBook(bid,title);
                }
                case 4 ->{
                    try {
                        System.out.print("Enter Book ID: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter only ID! +e");
                    }

                    lib.returnBook(scanner.nextInt());
                }
                case 5 -> lib.displayBooks();
                case 6 -> {
                            logginedUser = null;
                            System.out.println("Logged Out Successfully 👋 ");
                }
                default -> System.out.println("Invalid Option");
            }
        }
    }
}
