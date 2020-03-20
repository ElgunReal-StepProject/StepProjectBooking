package StepProjectBooking.controller;

import java.util.Scanner;

public class Console {


  private Scanner scanner = new Scanner(System.in);

  public String readline() {
    return scanner.nextLine();
  }

  public void println(String line) {
    System.out.println(line);
  }

  public void print(String line) {
    System.out.print(line);
  }
}
