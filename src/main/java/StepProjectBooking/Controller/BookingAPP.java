package StepProjectBooking.Controller;

public class BookingAPP {

  public void run() {
    Controller controller = new Controller();
    Console cc = new Console();
    while (true) {
      cc.println(controller.showMenu());
      String menuChoice = cc.readline();
      switch (menuChoice) {
        case "1":
          controller.case1OP();
          break;
        case "2":
          controller.case2OP();
          break;
        case "3":
          controller.case3OP();
          break;
        case "4":
          controller.case4OP();
          break;
        case "5":
          controller.case5OP();
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
