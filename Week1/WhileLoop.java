public class WhileLoop {
    public static void main(String[] args){
        int i = 0;
        System.out.println("WhileLoop:");
        while(i < 5){
            System.out.println("i = "+ i);
            i++;
        }
        i = 0 ;
        System.out.println("Do-WhileLoop:");
        do{
            System.out.println("i = "+ i);
            i++;
        }while(i < 5);
    }
}
