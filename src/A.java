//------ Старт игры
public class A extends Thread {

   C a;
   int b = -1;
   int c = 0;
   int d = 0;


   public A(C var1, int var2, int var3) {
      this.a = var1;
      this.b = var3;
      this.c = var2;
      this.start();
   }

   public void run() {
      synchronized(this) {
         this.a.G(this.c, this.b);
         this.d = 100;

         try {
            Thread.sleep(100L);
         } catch (Exception var3) {
            ;
         }

         this.d = 101;
         C.A(this.b);
         C.j = null;
      }
   }
}
