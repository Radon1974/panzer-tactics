import java.util.Vector;

public class B extends Thread {

   static Vector a;
   static boolean b = false;
   static boolean c = false;
   static int d = 0;


   public static void A(int var0) {
      if(a == null) {
         a = new Vector(1, 1);
      }

      if(c) {
         ;
      }

      a.addElement(new Integer(var0));
   }

   public static void B(int var0) {
      if(var0 >= 0) {
         HG.K(var0);
      }

   }

   public void run() {
      while(true) {
         try {
            while(true) {
               ++d;
               c = false;
               b = true;
               int var4 = 0;
               if(a != null) {
                  var4 = a.size();
               }

               int var1;
               int var6;
               if(HG.fa) {
                  long var2 = System.currentTimeMillis();

                  for(var1 = 0; var1 < HG.ba; ++var1) {
                     if(HG.w[var1] > 0L && var2 >= HG.w[var1] && HG.u[var1] != 1) {
                        HG.w[var1] = -1L;
                        var6 = HG.t[var1][0];
                        if(HG.u[var1] == 0) {
                           HG.N(var6);
                           HG.u[var1] = 2;
                           HG.O(var1);
                        }
                     }
                  }
               }

               if(var4 > 0 && !HG.ka) {
                  for(var1 = 0; var1 < var4; ++var1) {
                     Integer var7 = (Integer)a.elementAt(0);
                     var6 = var7.intValue();
                     HG.P(var6);
                     HG.Q(var6);
                     a.removeElementAt(0);
                  }
               }

               if(HG.ka) {
                  HG.J();
               }

               c = true;
               Thread.sleep(75L);
            }
         } catch (Exception var5) {
            ;
         }
      }
   }

}
