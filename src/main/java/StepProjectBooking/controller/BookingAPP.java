package StepProjectBooking.controller;

public class BookingAPP {

  public void run() {
    SimpleController simpleController = new SimpleController();
    Console cc = new Console();
    while (true) {
      cc.println(simpleController.showMenu());
      String menuChoice = cc.readline();
      switch (menuChoice) {
        case "1":
          simpleController.case1OP();
          break;
        case "2":
          simpleController.case2OP();
          break;
        case "3":
          simpleController.case3OP();
          break;
        case "4":
          simpleController.case4OP();
          break;
        case "5":
          simpleController.case5OP();
          break;
        case "6":
          cc.println("Good bye!");
          return;
        default:
          cc.println("Wrong Input. Please try again.");
      }
    }
  }
}
