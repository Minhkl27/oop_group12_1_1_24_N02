package Week2.Access;

import Week2.Packages.Cookie2;


public class ChocolateChip2 extends Cookie2 {
 public ChocolateChip2() {
 System.out.println("ChocolateChip2 constructor");
 }
 public void chomp() { bite(); } // Protected method
 public static void main(String[] args) {
 ChocolateChip2 x = new ChocolateChip2();
 x.chomp();
 }
}

