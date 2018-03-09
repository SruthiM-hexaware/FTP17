package util;

import source.Book;
public class MainClass{

    public static void main(String [] args){
    Book b = new Book();

    b.setBookId(2345);
    b.setBookName("let us C");
    b.setAuthor("Yeshwant Kanitkar");
    b.setSubject("programming");
    b.setTitle("C");
    b.setPublisher("xyz");

    System.out.println(b.getBookId());
    System.out.println(b.getBookName());
    System.out.println(b.getAuthor());
    System.out.println(b.getSubject());
    System.out.println(b.getTitle());
    System.out.println(b.getPublisher());

}

}