//TODO: Сделать модернизацию графики местности (заменить из Panzer General 1)
//TODO: Сделать модернизацию юнитов (как в Panzer General 1) и убрать ненужные
//TODO: Сделать карты (как в Panzer General 1)
//TODO: Сделать передвижение грузовиков как в PG Forever (автоматическая погрузка при ходе на большее расстояние)

//TODO: Улучшить графику курсора при выводе на экран, а также графику гексового меню
//TODO: Сделать инерцию при смещении экрана курсором
//TODO: Убрать кол-во ходов (вообще избавится)
//TODO: Запретить просачивание сквозь свои войска (запрет на движение около вражеского юнита)
//TODO: Сделать обводку вокруг края карты
//TODO: Заменить уменьшенные значки самолетов и техники над гексами юнитов (мелковатые слишком)

//FIXME: Сместить прицел при наведении на противника во время боя
//FIXME: При рекрутировании техники в конце списка выдает не то всплывающее окно с текстом (текст некорректно написан) - переправить текст
//FIXME: Звездочки нанимаемого офицера сместить (офицер на юните)
//FIXME: Не всегда находит юнита который еще не перемещался
//FIXME: Плохо видно какой не сходил юнит (применить цвета)
//FIXME: На названиях городов не выводятся координаты курсора
//FIXME: Не подсвечивается красным противник в режиме хода (показ подсвеченных гексов возможного хода на карте)
//FIXME: Когда противник рядом с юнитом не подсвечивается желтым пути движения
//FIXME: Реализовать перехват во время движения, когда враг не виден (при движении мимо врага)

//ERROR: Не работает миссия Суэцкий канал в кампании Кампания Фашистского Блока

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import java.lang.Math;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class C extends Canvas implements Runnable {

   static HG a;
   static boolean b = true;
   static boolean c = false;
   static long d;
   static Thread e = null;
   static int f = 0;    //разрешение экрана - ширина (240)
   static int g = 0;    //разрешение экрана - высота (320)
   static int[] h;
   static Font[] i;
   static A j;
   static long k = 0L;
   static Image l = null;
   static int m = 0;
   static boolean n = false;
   static int o = -1;   //режим работы программы
   static int p = -1;   //предыдущий режим работы программы
   static boolean q = false;
   static final byte[][] r = new byte[][]{{(byte)1, (byte)0, (byte)0, (byte)7}, {(byte)2, (byte)0, (byte)3, (byte)13}, {(byte)3, (byte)0, (byte)1, (byte)9}, {(byte)4, (byte)0, (byte)2, (byte)11}, {(byte)6, (byte)0, (byte)6}, {(byte)7, (byte)0, (byte)8}, {(byte)9, (byte)0, (byte)12}, {(byte)8, (byte)0, (byte)14}, {(byte)5, (byte)0, (byte)4, (byte)10}, {(byte)12, (byte)0, (byte)17}, {(byte)11, (byte)0, (byte)18}, {(byte)10, (byte)0, (byte)5}, {(byte)13, (byte)0, (byte)0, (byte)7}, {(byte)14, (byte)0, (byte)3, (byte)13}, {(byte)17, (byte)0, (byte)4, (byte)18, (byte)10}, {(byte)18, (byte)0, (byte)4, (byte)10}, {(byte)20, (byte)0, (byte)17}, {(byte)19, (byte)0, (byte)18}, {(byte)21, (byte)0, (byte)1, (byte)9}, {(byte)22, (byte)0, (byte)2, (byte)11}, {(byte)23, (byte)0, (byte)0, (byte)7}, {(byte)24, (byte)0, (byte)3, (byte)13}, {(byte)25, (byte)0, (byte)0, (byte)1, (byte)9}, {(byte)26, (byte)0, (byte)3, (byte)2, (byte)11}, {(byte)27, (byte)0, (byte)4, (byte)18, (byte)10}, {(byte)28, (byte)0, (byte)17}, {(byte)33, (byte)0, (byte)6}, {(byte)34, (byte)0, (byte)7}, {(byte)35, (byte)0, (byte)8}, {(byte)36, (byte)0, (byte)14}, {(byte)37, (byte)0, (byte)13}, {(byte)38, (byte)0, (byte)12}, {(byte)29, (byte)0, (byte)18}, {(byte)30, (byte)0, (byte)15}, {(byte)31, (byte)0, (byte)16}, {(byte)32, (byte)0, (byte)4, (byte)10}};
   static int s = r.length;
   static byte[] t = new byte[s];
   static byte[] u = new byte[22];
   static byte[] v = new byte[22];
   public static final Random w = new Random(System.currentTimeMillis());
   static Image[] x;
   static boolean[] y = new boolean[180];
   static final int[][] z = new int[][]{{1, 1, 1, 1, 16, 1, 1, 16, 0, 393288}, {1, 2, 1, 1, 16, 1, 1, 16, 0, 393285}, {2, 2, 2, 1, 16, 1, 1, 16, 1, 393283}, {2, 3, 2, 2, 16, 1, 1, 16, 2, 393282}, {2, 3, 3, 3, 16, 1, 1, 16, 3, 393284}, {3, 16, 16, 16, 16, 1, 16, 16, 0, 393286}, {2, 3, 2, 3, 16, 1, 1, 16, 0, 393289}, {1, 3, 1, 2, 16, 1, 1, 16, 0, 393281}, {2, 3, 1, 2, 16, 1, 1, 1, 0, 393278}, {16, 16, 16, 16, 16, 1, 1, 16, 0, 393280}, {16, 16, 16, 16, 1, 1, 16, 1, 0, 393287}, {1, 2, 3, 2, 16, 1, 1, 16, 3, 393277}, {1, 1, 2, 1, 16, 1, 1, 16, 3, 393279}, {16, 16, 16, 16, 16, 16, 16, 16, 0, 5}};   //карта проходимости местности для разных классов юнитов
   static int[][] aa = new int[][]{{55, 0, 0, 1}, {58, 1, 0, 1}, {59, 1, 0, 1}, {60, 1, 0, 1}, {61, 2, 0, 1}, {62, 2, 0, 1}, {63, 2, 0, 1}, {57, 3, 0, 1}, {64, 3, 0, 1}, {65, 3, 0, 1}, {56, 3, 0, 1}, {66, 3, 1, 1}, {69, 4, 1, 1}, {70, 4, 1, 1}, {71, 5, 1, 1}, {72, 5, 1, 1}, {73, 5, 1, 1}, {74, 5, 1, 1}, {75, 6, 1, 1}, {76, 6, 1, 1}, {67, 6, 1, 1}, {68, 6, 1, 1}, {44, 4, 2, 1}, {47, 5, 2, 1}, {48, 5, 2, 1}, {49, 5, 2, 1}, {50, 5, 2, 1}, {51, 5, 2, 1}, {52, 5, 2, 1}, {53, 5, 2, 1}, {54, 5, 2, 1}, {45, 6, 2, 1}, {46, 6, 2, 1}, {41, 0, 3, 1}};   //сценарии (номер сценария, ?, нация игрока (0 - Германия, 1 - СССР, 2 - союзники, 3 - ???), доступность (1 - доступно)
   static final int[] ba = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 6, 1, 1, 1, 1, 1, 9};
   static final int[] ca = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 10, 8, 8, 8, 9, 8, 8, 9, 8, 8, 9, 9, 9, 9, 9, 9, 8, 8, 9, 9, 8, 9, 8, 9, 9, 9, 9, 8, 8, 8, 8, 8, 8};
   static int[][] da = (int[][])null;	//юниты в библиотеке
   static int[][] ea = (int[][])null;
   static Font fa;
   static int ga = -1;
   static int ha = -1;
   static int ia = 0;
   static byte ja = -1;
   static boolean ka;
   static int la = 0;   //смещение эрана в гексах по оси X
   static int ma = 0;   //смещение эрана в гексах по оси Y
   static int na = 0;
   static int oa = 0;
   static int[] pa = new int[2];    //[0]: координаты курсора (в гексах) по оси X
   static int[] qa = new int[2];    //[0]: координаты курсора (в гексах) по оси Y
   static int ra = 0;   //сторона (игрок или компьютер)
   static int sa = 0;   //сторона (игрок или компьютер)
   static int[][] ta = (int[][])null;	//юниты в сценарии у игрока
   static int[][] ua = (int[][])null;
   static int[][] va = (int[][])null;	//юниты в сценарии у противника
   static int[] wa = new int[28];
   static int xa = 0;
   static int ya = 0;
   static int za = 0;
   static int[][] aA = new int[100][4];
   static int[] bA = null;
   static int[] cA = null;
   static int[] dA = null;              //перемещаемый юнит (нажата кнопка на юните)
   static int[] eA = null;
   static int[] fA = null;          	//выбор юнита (при клике мышкой)
   static boolean gA = false;           //юнит двигается
   static final int[] hA = new int[]{25, 60, 125, 200, 300, 500};
   static int iA = 0;
   static int[][] jA = (int[][])null;
   static int[][] kA;
   static int[] lA = new int[3];
   static int mA = 0;
   static int[] nA = new int[5];
   static int oA = 0;   //размер карты по оси X
   static int pA = 0;   //размер карты по оси Y
   static int qA = 0;
   static int[][][] rA = new int[3][][];    //[2][][]: 0 - шоссе, 5, 10 - река
   static int sA = 0;
   static boolean tA = true;
   static int[] uA = new int[2];
   static int[][] vA;   //местность горы
   static int[][] wA;   //города для захвата
   static int[][] xA;   //аэродромы свои
   static int[][] yA;   //аэродромы вражеские
   static int zA = 0;
   static int ab = 0;
   static int bb = 0;
   static int cb = 0;	//год сценария
   static int db = 0;   //ход игрока (0) или противника (1...)
   static int eb = 1;
   static int fb = 0;
   static boolean[] gb = new boolean[11];
   static byte[] hb = new byte[17];
   static byte ib;
   static int jb = -1;
   static int[][] kb = new int[3][];
   static int[] lb = new int[5];
   static byte mb;
   static byte nb;
   static byte ob;
   static boolean pb = true;
   static boolean qb = false;
   static boolean rb = false;
   static int sb = -1;
   public static boolean tb = false;
   static int ub = -1;
   static int[][] vb;   //массив пути гексы: 0 - черный, 1 - серый, 2 - прозрачный, 3 - красный
   static int[][] vb2;   //массив пути (дополнительно) гексы: 0 - черный, 1 - серый, 2 - прозрачный, 3 - красный, 4 - желтым (vb = 1), 5 - желтым (vb = 2)
   static int[][] vb3;   //массив вражеских юнитов сухопутная техника
   static int[][] vb4;   //массив вражеских юнитов воздушная техника
   static int wb = -1;
   static int[] xb;
   static int[][] yb;
   static int[] zb;
   static int[] aB;
   static int[][] bB;
   static int[][] cB;
   static int[] dB;
   static int[][] eB;
   static int[] fB;
   static int[] gB;
   static int hB;   //1 - разрешено перемещение, 2 - запрещено перемещение, 3 - ?
   static int iB;   //расстояние перемещения
   static int jB;   //затраты очков перемещения
   static boolean kB = false;   //true - идет перемещение, false - нет
   static int[][] lB = new int[50][10];
   static int[] mB = new int[40];
   static final int[][] nB = new int[][]{{-1, -1, 0, -1, 1, -1, 1, 0, 0, 1, -1, 0}, {-1, 0, 0, -1, 1, 0, 1, 1, 0, 1, -1, 1}};   //карта координат прохождения по кругу вокруг гекса X, Y
   static final int[][] oB = new int[][]{{17, 10, 14, 3, 16, 15}, {18, 6, 11, 16, 4, 19}, {20, 7, 12, 15, 19, 5}, {0, 8, 13, 17, 18, 20}, {8, 1, 9, 10, 6, 7}, {13, 9, 2, 14, 11, 12}};
   static final int[] pB = new int[]{0, 0, 1, 1, 1, 0};
   public static short[] qB = new short[]{(short)37, (short)38, (short)39, (short)40, (short)41, (short)42, (short)43, (short)44, (short)45, (short)46, (short)47, (short)48, (short)49, (short)50, (short)51, (short)52, (short)53, (short)54, (short)55, (short)56, (short)57, (short)58, (short)59, (short)60, (short)61, (short)62, (short)63, (short)64, (short)65, (short)66, (short)67, (short)68, (short)69, (short)70, (short)71, (short)72, (short)73, (short)74, (short)75, (short)76, (short)77, (short)78, (short)79, (short)80, (short)81, (short)82, (short)83, (short)84, (short)85, (short)86, (short)87, (short)88, (short)89, (short)90, (short)91, (short)92, (short)93, (short)94, (short)95, (short)96, (short)97, (short)98, (short)99, (short)100, (short)101, (short)102, (short)103, (short)104, (short)105, (short)106, (short)107, (short)108, (short)109, (short)110, (short)111, (short)112, (short)113, (short)114, (short)115, (short)116, (short)117, (short)118, (short)119, (short)120, (short)121, (short)122, (short)123, (short)124, (short)125, (short)126, (short)127, (short)128, (short)129, (short)130, (short)131, (short)132, (short)133, (short)134, (short)135, (short)136, (short)137, (short)138, (short)139, (short)140, (short)141, (short)142, (short)143, (short)144, (short)145, (short)146, (short)147, (short)148, (short)149, (short)150, (short)151, (short)152, (short)153, (short)154, (short)155, (short)156, (short)157, (short)158, (short)159, (short)160, (short)161, (short)162, (short)163, (short)164, (short)165, (short)166, (short)167, (short)168, (short)169, (short)170, (short)171, (short)172, (short)173, (short)174, (short)175, (short)176, (short)177, (short)178, (short)179, (short)175, (short)140, (short)84, (short)92};
   public static String[] rB = new String[1];	//название выбранного юнита
   static int[][] sB = (int[][])null;
   static byte[][] tB = (byte[][])null;
   static int[][] uB = new int[4][12];
   static int vB = 0;
   public static int[] wB = new int[8];
   public static int xB;
   public static int yB = -20;
   public static boolean zB = false;
   public static boolean ac = false;
   static int sens_x = 0;
   static int sens_y = 0;
   static int sens_x2 = 0;
   static int sens_y2 = 0;
   static int sens_x3 = 0;
   static int sens_y3 = 0;   
   static int ras_x = 0;
   static int ras_y = 0;
   static int ras_x2 = 0;
   static int ras_y2 = 0;
   static int ras_x3 = 0;
   static int ras_y3 = 0;   
   static int min_x = 0;
   static int min_y = 0;
   static int min_x2 = 0;
   static int min_y2 = 0;
   static int curs_x = 0;
   static int curs_y = 0;
   static boolean buld = false;
   static int key = 0;
   static int offset_x = 0;
   static int offset_y = 0;
   static boolean alpha_pos = false;
   static boolean enemy_pos = false;
   static boolean way = false;
   static int test = 0;
   static boolean test_boolean = false;

//------ Режим работы программы
   public static void A(int var0) {
      p = o;
      B();
      o = var0;
      A();
   }
//------ Исполнение режимов работы программы
   public static void A() {
      q = false;
      if(fa != null) {
         ia = fa.getHeight();
      }

      HG.A(0, 10, 120, 120, 80, 16);
      HG.A(0, 1, 0, true);
      switch(o) {
      case 1:
         j = new A(HG.pb, 1, 12);
         fa = Font.getDefaultFont();
         break;
      case 11:
         if(!n) {
            HG.AA(0);   //HG.AA(1); - выводить меню звук при старте игры
            n = true;
         } else {
            HG.AA(0);
         }
         break;
      case 13:
         F();
         l = null;
         if((zA & 15) == 3) {
            uA[0] = 10000;
            uA[1] = 10000;
            HG.eb[1][2] = 18;
         } else {
            HG.eb[1][2] = 81;
            uA[0] = 1500;
         }

         HG.fb = false;
         if(ja >= 0) {
            A(15);
         } else {
            ta = (int[][])null;
            A(14);
         }
         break;
      case 14:
         HG.aA.removeAllElements();
         if((zA & 15) != 1) {
            ta = (int[][])null;
         }

         j = new A(HG.pb, 2, 16);
         break;
      case 15:
         A(18);
         break;
      case 16:
         A(18);
         if(ab >= 3) {
            B(17, 0, 0, true);
         } else {
            B(17, 0, 0, false);
         }

         B(21, 0, 0, false);
      case 17:
      default:
         break;
      case 18:
         HG.A(6, -1, 0, true);
         HG.A(3, -1, 0, true);
         if(p != 20 && p != 17 && !ka) {
            gA = false;
            za = 0;
            int[][] var6 = db == 0?ta:va;

            for(iA = 0; iA < var6.length; ++iA) {
               if(var6[iA][19] >= 0 && da[var6[iA][0]][5] != 5) {
                  if(da[var6[iA][0]][5] == 7) {
                     if(rA[2][var6[iA][2]][var6[iA][1]] == 8) {
                        var6[iA][0] = var6[iA][19];
                        var6[iA][19] = -1;
                     }
                  } else {
                     var6[iA][0] = var6[iA][19];
                     var6[iA][19] = -1;
                  }
               }

               var6[iA][23] = 1;
               var6[iA][7] = 0;
            }

            for(iA = 0; iA < aA.length; ++iA) {
               if(aA[iA][3] == db) {
                  aA[iA][0] = 0;
               }
            }

            fA = A(pa[db], qa[db], 1, 3);
            var6 = db == 0?va:ta;
            A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории

            for(iA = 0; iA < var6.length; ++iA) {
               var6[iA][15] = 0;
               var6[iA][7] = 0;
               if(vb[var6[iA][2]][var6[iA][1]] == 2) {
                  var6[iA][23] = 1;
               } else {
                  if(fA == var6[iA]) {
                     fA = null;
                  }

                  var6[iA][23] = 0;
               }
            }

            iA = 0;
         }

         ka = false;
         break;
      case 22:
         if(p != 20) {
            iA = 0;
            gA = false;
            fA = null;
            za = 0;
            int[][] var2 = db == 0?ta:va;
            int[][] var1 = db == 0?va:ta;

            for(jA = new int[var2.length][3]; iA < jA.length; ++iA) {
               jA[iA][0] = iA;
               var2[iA][7] = 0;
               var2[iA][16] = 0;
               var2[iA][26] = 0;
               if(var2[iA][21] == 2 && var2[iA][19] >= 0 && da[var2[iA][0]][5] != 5 && da[var2[iA][0]][5] != 7) {
                  var2[iA][0] = var2[iA][19];
                  var2[iA][19] = -1;
               }

               for(int var0 = 0; var0 < var1.length; ++var0) {
                  if(H(var2[iA], var1[var0]) > 0) {
                     A(db ^ 1, var2[iA]);
                     A(var2[iA], var1[var0]);
                     jA[iA][1] += var1[var0][15] & 15;
                     jA[iA][2] += var1[var0][15] >> 4 & 15;
                  }
               }
            }

            for(iA = 0; iA < aA.length; ++iA) {
               if(aA[iA][3] == db) {
                  aA[iA][0] = 0;
               }
            }

            iA = 0;

            boolean var3;
            do {
               var3 = true;

               for(int var5 = 1; var5 < jA.length; ++var5) {
                  if(jA[var5 - 1][1] - jA[var5 - 1][2] > jA[var5][1] - jA[var5][2]) {
                     int[] var4 = jA[var5 - 1];
                     jA[var5 - 1] = jA[var5];
                     jA[var5] = var4;
                     var3 = false;
                  }
               }
            } while(!var3);

            A(db ^ 1, (int[])null); //A(db ^ 1, null);
         }
      }

      q = true;
   }

   public static void B() {
      q = false;
      switch(o) {
      case 4:
      case 5:
         HG.H();
         break;
      case 17:
         wb = -1;
         break;
      case 22:
         int[][] var3 = db == 0?va:ta;

         int var1;
         for(iA = 0; iA < var3.length; ++iA) {
            int var2 = var3[iA][20];
            var1 = var3[iA][21];

            for(int var0 = 0; var0 < var3.length; ++var0) {
               if(var3[var0][20] == var2) {
                  var3[var0][21] = var1;
               }
            }

            var3[iA][16] = 0;
            var3[iA][7] = 0;
            var3[iA][26] = 0;
            var3[iA][15] = 0;
         }

         gB = new int[pA * oA * 2];
         ++eb;
         Q();

         for(var1 = 0; var1 < 2; ++var1) {
            int[][] var4 = var1 == 0?vA:wA;

            for(iA = 0; iA < var4.length; ++iA) {
               if(var4[iA][4] != var1) {
                  uA[var1] += 100;   //???
               }
            }

            var4 = var1 == 0?xA:yA;

            for(iA = 0; iA < var4.length; ++iA) {
               if(var4[iA][4] != var1) {
                  uA[var1] += 50;   //???
               }
            }
         }
      }

   }
//------ Работа программы ???
   public static void C() {
      if(q) {
         R();
         int[][] var1;
         int var3;
         switch(o) {
         case 12:
            if(System.currentTimeMillis() >= k + 3000L || k == 0L) {
               k = System.currentTimeMillis();

               try {
                  l = Image.createImage("/" + ('\ufde8' + m) + ".png");
                  ++m;
               } catch (Exception var2) {
                  if(m >= 5) {
                     A(11); //Вызов меню звука при старте
                     HG.ta = true;
                  } else {
                     k = 0L;
                     ++m;
                  }
               }
            }
            break;
         case 18:
            if(kB) {
               U();
            }

            if(fb > 0) {
               fb = 0;
               HG.GA(23);
               HG.fb = true;
            } else {
               var3 = H(dA);
               if(var3 == 1) {
                  if(dA != null) {
                     int[] var4 = A(dA[1], dA[2], 1, db == 0?2:1);
                     if(var4 != null && da[dA[0]][4] == 12) {
                        var3 = var4[8] - 1;
                        if(var3 < -2) {
                           var3 = -2;
                        }

                        var4[8] = var3;
                     }
                  }

                  A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории
                  M(dA);
               } else if(var3 == -1 && dA != null) {
                  if(dA[16] > 0) {
                     dA[7] = 1;
                     A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории
                     gA = false;
                     HG.L(14);
                     HG.GA(18);
                     HG.fb = true;
                  } else if(dA[7] == 0) {
                     if(!HG.ta) {
                        HG.eb[4][0] = 393300;
                        HG.eb[4][1] = 18;
                        HG.GA(4);
                        HG.fb = true;
                     }
                  } else {
                     HG.eb[4][0] = 393300;
                     HG.eb[4][1] = 2;
                  }
               }
            }
            break;
         case 20:
            if(HG.ob[7] != null) {
               ++HG.na;
               HG.na %= HG.ob[7][1];
            }

            if(kB) {
               U();
            } else {
               lB = new int[50][10];
               if(cA != null && bA != null) {
                  A(cA, cA[15] & 15);
                  A(bA, cA[15] >> 4 & 15);
                  if(bA != null) {
                     bA[7] = 1;
                     var3 = bA[12] - 1;
                     if(var3 < 0) {
                        var3 = 0;
                     }

                     bA[12] = var3;
                  }

                  A(db ^ 1, (int[])null);
                  A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории
                  var1 = db == 0?va:ta;
                  if(eA == null && bA != null) {
                     for(var3 = 0; var3 < var1.length; ++var3) {
                        if(var1[var3] != cA && var1[var3] != bA && var1[var3][7] == 0 && (da[var1[var3][0]][4] == 5 && da[bA[0]][5] != 5 || da[var1[var3][0]][4] == 11 && da[bA[0]][5] == 5) && B(cA[1], cA[2], var1[var3][1], var1[var3][2]) <= 1 && var1[var3][26] == 0 && var1[var3][12] > 0 && H(var1[var3], bA) > 0) {
                           eA = var1[var3];
                           if(cA[16] == 0) {
                              cA = bA;
                              bA = eA;
                           }

                           if(B(bA, cA) > 0) {
                              var1[var3][26] = 1;
                              var1[var3][7] = 1;
                              break;
                           }
                        }
                     }

                     if(eA != null) {
                        HG.L(14);
                        HG.GA(22);
                        HG.fb = true;
                     } else {
                        bA = null;
                        cA = null;
                     }
                  } else {
                     eA = null;
                     bA = null;
                     cA = null;
                  }
               }

               lB = new int[50][10];
               A(db, (int[])null);
               A(db ^ 1, (int[])null);
               hB = 2;
               if(p == 22) {
                  A(vA, xA, ta, (int[])null);    //определение видимой территории
                  var1 = db == 0?ta:va;

                  for(var3 = 0; var3 < var1.length; ++var3) {
                     if(vb[var1[var3][2]][var1[var3][1]] == 2) {
                        var1[var3][23] = 1;
                     } else {
                        var1[var3][23] = 0;
                     }
                  }
               }

               o = p;
            }
            break;
         case 22:
            if(HG.ob[7] != null) {
               ++HG.na;
               HG.na %= HG.ob[7][1];
            }

            if(kB) {
               U();
            }

            int[][] var0 = db == 0?ta:va;
            if(iA >= var0.length && !gA) {
               B(34, 0, 0, true);
               var1 = db == 0?ta:va;

               for(var3 = 0; var3 < var1.length; ++var3) {
                  var1[var3][7] = 0;
               }

               dA = null;
               fA = null;
               db ^= 1;
               A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории
               if(zB) {
                  A(22);
               } else {
                  A(18);
               }

               HG.L(12);
               pb = false;
               HG.AA(37);
               HG.ta = true;
               B(33, 0, 0, true);
               B(18, eb, 0, true);
            } else {
               label471: {
                  if(gA && dA != null) {
                     var3 = H(dA);
                     if(var3 == 1 || var3 == -1) {
                        if(dA[16] > 0) {
                           dA[7] = 1;
                           gA = false;
                           HG.L(14);
                           HG.GA(18);
                           HG.fb = true;
                           break label471;  //break
                        }

                        M(dA);
                     }

                     if(dA != null && dA[3] == 0 && dA[4] == 0 && o == 22 && vb[dA[2]][dA[1]] == 2) {
                        qa[db] = dA[2];
                        pa[db] = dA[1];
                        la = pa[db] - ((f - 25) / 45 + 2) / 2;
                        ma = qa[db] - (g / 50 + 1) / 2;
                        H();
                     }
                  } else {
                     if(iA < jA.length && iA >= 0 && jA[iA][0] >= 0 && jA[iA][0] < var0.length) {
                        dA = var0[jA[iA][0]];
                        J(db);
                        A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, dA);    //определение видимой территории
                        var1 = db == 0?va:ta;

                        for(var3 = 0; var3 < var1.length; ++var3) {
                           if(vb[var1[var3][2]][var1[var3][1]] == 2) {
                              var1[var3][23] = 1;
                           } else {
                              var1[var3][23] = 0;
                           }
                        }

                        N(dA);  //алгоритм построения возможных ходов врага
                     }

                     ++iA;
                  }

                  if(o == 22) {
                     A(db ^ 1, (int[])null);
                     A(db == 0?wA:vA, db == 0?yA:xA, db == 0?va:ta, (int[])null);    //определение видимой территории
                     var1 = db == 0?ta:va;

                     for(var3 = 0; var3 < var1.length; ++var3) {
                        if(vb[var1[var3][2]][var1[var3][1]] == 2) {
                           var1[var3][23] = 1;
                        } else {
                           var1[var3][23] = 0;
                        }
                     }
                  }
               }
            }
         }

         if(!HG.fb && rb) { //нет входа в гексовое меню
            pb = false;
            HG.AA(sb);  //загрузка сообщения
            HG.aA.removeAllElements();
            HG.ta = true;   //режим Главного Меню
            if(ha >= 0) {
               HG.sa[35][7] = ha;
               HG.sa[35][8] = ga;
               sb = 35;
               ga = -1;
               ha = -1;
            } else if(ha == 0) {
               HG.sa[36][4] = ga;
               sb = 36;
               ga = -1;
               ha = -1;
            } else if(ga >= 0) {
               HG.sa[34][4] = ga;
               sb = 34;
               ga = -1;
            } else {
               rb = false;
            }
         }
      }

   }
   
protected void pointerPressed(int var_x, int var_y) {
     sens_x3 = var_x;
     sens_y3 = var_y;
 
}   
//------ Перемещение курсора или сенсорного ввода
protected void pointerDragged(int var_x, int var_у) {
  if(!HG.fb && !HG.popup_menu) {
    if(HG.ta) {
     if(var_у - sens_y2 <= (-1)) {  //прибавить смещение в гексах по оси y
         if(HG.pa > HG.fA) {
           HG.oa -= 2;
           if(HG.oa < -(HG.pa - HG.fA)) {
            HG.oa = -(HG.pa - HG.fA);
           }
         }
     } else if(var_у - sens_y2 >= 1 ) {   //убавить смещение в гексах по оси y
      HG.oa += 2;
       if(HG.oa > 0) {
         HG.oa = 0;
       }
     }    
    } else {
    //Смещение игрового поля
    if(var_x - sens_x2 <= (-1)) {
      offset_x = offset_x - 2;   //прибавить смещение по оси x
      if(offset_x <= (-45)) {
      offset_x = 0;
      ++la;
      }
    } else if(var_x - sens_x2 >= 1) {
      offset_x = offset_x + 2;   //убавить смещение по оси x
      if(offset_x >= 45) {
      offset_x = 0;
      --la;
      }      
    }
    if(var_у - sens_y2 <= (-1)) {
      offset_y = offset_y - 2;   //прибавить смещение по оси y
      if(offset_y <= (-50)) {
      offset_y = 0;
      ++ma;
      }      
    } else if(var_у - sens_y2 >= 1 ) {
      offset_y = offset_y + 2;   //убавить смещение  по оси y
      if(offset_y >= 50) {
      offset_y = 0;
      --ma;
      }       
    }
    H();   //проверка смещения экрана на выход из размеров карты
    }
    sens_x2 = var_x;
    sens_y2 = var_у;
  }
}
        
//------ Нажатие мыши или сенсорного ввода   
protected void pointerReleased(int var_x, int var_y) {
  sens_x = var_x;
  sens_y = var_y;
  curs_x = pa[db];
  curs_y = qa[db];
  int var_x2 = min_x;
  int var_y2 = min_y;
  double dist; //расстояние между точкой и цетром окружности
  double e_dist = 1000; //минимальное расстояние между точкой и цетром окружности    
//Выход из режима пауза
if(c) {
  c = false;  
}  
//Выбор гекса указания курсора
if(!HG.fb && !HG.ta && !HG.popup_menu && sens_x != sens_x2 && sens_y != sens_y2) {
 //way = false;   
 if(var_y >= 18 && var_y <= g - 34) {  //не реагировать на верхнюю и нижнюю информационную полоску 
  for( int var_i = 0; var_i <= (f - 25) / 45 + 1; ++var_i) {    //перебор гексов по x
     for( int var_j = 0; var_j <= g / 50 + 1; ++var_j) {    //перебор гексов по y
          ras_x = var_i * 45 + 7 + offset_x;  
        //ras_x = var_i * 25 + -8;
          if ((la % 2) == 0 & (var_i % 2) != 0) {
            ras_y = var_j * 50 + 25 + 9 + offset_y;  
          //ras_y = var_j * 32 + 16 + -1;
          } else if ((la % 2) == 0 & (var_i % 2) == 0) {
            ras_y = var_j * 50 + 9 + offset_y;   
          //ras_y = var_j * 32 + -1;
          } else if ((la % 2) != 0 & (var_i % 2) != 0) {
            ras_y = var_j * 50 + 9 + offset_y;   
          //ras_y = var_j * 32 + -1;
          } else {
            ras_y = var_j * 50 + 25 + 9 + offset_y;  
          //ras_y = var_j * 32 + 16 + -1;
          }
          dist = Math.sqrt((sens_x - ras_x) * (sens_x - ras_x) + (sens_y - ras_y) * (sens_y - ras_y));
          if (dist < e_dist) {
            min_x = var_i + la;
            min_y = var_j + ma;
            ras_x2 = ras_x;
            ras_y2 = ras_y;      
            e_dist = dist;
          }
     } 
  }
 } 
  pa[db] = min_x;
  qa[db] = min_y;
  buld = true; //показывать названия юнитов при клике мышки

//  if(dA != null && dA[7] == 0) {
//     buld = true;
//  }
  if(o != 12 && o != 1 && j == null && var_y <= 18) { //нажат левый софт (вход в следующее меню) верхняя полоска в игре
        u[18] = 20;
        v[18] = 0;
     } else {
        k = 0L;
     }   
  if(o != 12 && o != 1 && j == null && var_y >= g - 34) { //нажат 0 (стратегическая карта) нижняя полоска в игре
        u[5] = 20;
        v[5] = 0;
        test_boolean = false;
     } else {
        k = 0L;
  }
  if(o != 12 && o != 1 && j == null && min_x == var_x2 && min_y == var_y2 && var_y >= 18 && var_y <= g - 34) { //нажата центральная кнопка
    u[4] = 20;
    v[4] = 0;
    way = true;
  } else {
    k = 0L;
  }  
 }
//Выбор пунктов гексового меню 1..6
  if(HG.fb && !HG.ta && !HG.popup_menu) {   // && HG.gb <= 8
      
      ras_x3 = ras_x2;
      ras_y3 = ras_y2;
     
     //1 пункт меню
     dist = Math.sqrt((sens_x3 - (ras_x3 - 45)) * (sens_x3 - (ras_x3 - 45)) + (sens_y3 - (ras_y3 - 25)) * (sens_y3 - (ras_y3 - 25)));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 0;
     }
     //2 пункт меню
     dist = Math.sqrt((sens_x3 - ras_x3) * (sens_x3 - ras_x3) + (sens_y3 - (ras_y3 - 50)) * (sens_y3 - (ras_y3 - 50)));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 1;
     }
     //3 пункт меню
     dist = Math.sqrt((sens_x3 - (ras_x3 - (-45))) * (sens_x3 - (ras_x3 - (-45))) + (sens_y3 - (ras_y3 - 25)) * (sens_y3 - (ras_y3 - 25)));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 2;
     }
     //4 пункт меню
     dist = Math.sqrt((sens_x3 - (ras_x3 - (-45))) * (sens_x3 - (ras_x3 - (-45))) + (sens_y3 - (ras_y3 - (-25))) * (sens_y3 - (ras_y3 - (-25))));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 3;
     }
     //5 пункт меню
     dist = Math.sqrt((sens_x3 - ras_x3) * (sens_x3 - ras_x3) + (sens_y3 - (ras_y3 - (-50))) * (sens_y3 - (ras_y3 - (-50))));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 4;
     }
     //6 пункт меню
     dist = Math.sqrt((sens_x3 - (ras_x3 - 45)) * (sens_x3 - (ras_x3 - 45)) + (sens_y3 - (ras_y3 - (-25))) * (sens_y3 - (ras_y3 - (-25))));
     if (dist < e_dist) {
       e_dist = dist;
       HG.hb = 5;
     }
     HG.T();    //выбор пункта меню по часовой стрелке
     if(o != 12 && o != 1 && j == null) { //нажат левый софт && ras_x3 == var_x2 && ras_y3 == var_y2
        u[18] = 20;
        v[18] = 0;
        
     } else {
        k = 0L;
     }  
 } 
//Выбор пунктов главного меню
  if(HG.ta && !HG.fb && !HG.popup_menu) {   // && HG.gb <= 1
  //int var_pos_x1 = 52;  //позиция по оси X невыделенного пункта меню
  //int var_pos_x2 = 40;  //позиция по оси X выделенного пункта меню
  //int var_pos_y;  //позиция по оси Y первого пункта меню
  int var_menu = HG.va;
    //if(HG.ua == 0 || HG.ua == 33) {    //главное меню, меню паузы (номер позиции меню)
       //var_pos_y = HG.bA;  //позиция по оси Y первого пункта меню
     if(HG.xa != (-1)) {  
      if(HG.ua < 30 || HG.ua > 32) { 
        for( int var_i = 0; var_i <= HG.xa; ++var_i) {
          
          if(var_y >= HG.sens_menu[var_i] && var_y <= HG.sens_menu[var_i] + 20) {
            HG.va = var_i + 1; 
          }
//          var_pos_y = var_pos_y + 28 + HG.oa;
        }
      } else { //нет пунктов меню (нечего выбирать)
        for( int var_i = 0; var_i <= HG.xa; ++var_i) {  //for( int var_i = HG.wa; var_i <= HG.xa; ++var_i) {
          if(var_y >= HG.sens_menu[var_i] && var_y <= HG.sens_menu[var_i] + 38) {
            HG.va = var_i + 1; 
          }
        }
//          var_pos_y = var_pos_y + 45 + HG.oa;
      }    
     }
//    if(HG.ua == 1 || HG.ua == 2 || HG.ua == 3 || HG.ua == 5 || (HG.ua >= 12 && HG.ua <= 24)) {    //подменю (номер позиции меню)
//       var_pos_y = 96;  //позиция по оси Y первого пункта меню
//        for( int var_i = HG.wa; var_i <= HG.xa; ++var_i) {
//          if(var_y >= var_pos_y && var_y <= var_pos_y + 20) {
//            HG.va = var_i; 
//          }
//          var_pos_y = var_pos_y + 28;
//        }
//    }    
//    if(HG.ua == 16) {    //меню сценария (номер позиции меню)
//        var_pos_y = 130;  //позиция по оси Y первого пункта меню
//        for( int var_i = HG.wa; var_i <= HG.xa; ++var_i) {
//          if(var_y >= var_pos_y && var_y <= var_pos_y + 20) {
//            HG.va = var_i; 
//          }
//          var_pos_y = var_pos_y + 28;
//        }
//    }
//    if(sens_x != sens_x2 && sens_y != sens_y2) {  //если не экраны скролинга, то выбор пункта меню
     HG.M();    //выбор пункта меню движение вниз
//    }
     if(o != 12 && o != 1 && j == null && var_x <= 20 && var_y >= g - 20) { //нажат левый софт (вход в следующее меню)
        u[18] = 20;
        v[18] = 0;
     } else {
        k = 0L;
     }      
     
     if(o != 12 && o != 1 && j == null && var_x >= f - 20 && var_y >= g - 20) { //нажат правый софт (выход в предыдущее меню)
        u[17] = 20;
        v[17] = 0;
     } else {
        k = 0L;
     } 
          
     if(o != 12 && o != 1 && j == null && var_menu == HG.va && !(var_x <= 20 && var_y >= g - 20) && !(var_x >= f - 20 && var_y >= g - 20)) { //нажата центральная кнопка
        u[4] = 20;
        v[4] = 0;
     } else {
        k = 0L;
     }
     
 } 
 //Выбор пунктов всплывающего меню
  if(HG.popup_menu) {   //if(HG.gb == 14 || HG.gb == 25 || HG.gb == 29) { 

     if(o != 12 && o != 1 && j == null && (var_x >= HG.checkmark_x && var_x <= HG.checkmark_x + 20) && (var_y >= HG.checkmark_y && var_y <= HG.checkmark_y + 20)) { //нажат левый софт (подтверждение)
        u[18] = 20;
        v[18] = 0;
        HG.popup_menu = false;
     } else {
        k = 0L;
     }      
     
     if(o != 12 && o != 1 && j == null && (var_x >= HG.cross_x && var_x <= HG.cross_x + 20) && (var_y >= HG.cross_y && var_y <= HG.cross_y + 20)) { //нажат правый софт (отмена)
        u[17] = 20;
        v[17] = 0;
        HG.popup_menu = false;
     } else {
        k = 0L;
     } 
          
 
 }  
}   

//------ Обработка клавиш клавиатуры (нажатых)
   protected void keyPressed(int var1) {	//обработка нажатий клавиш
      key =  var1;
      D();
      byte var2 = -1;
      switch(var1) {
      case -7:  //правый софт
         var2 = 17;
         break;
      case -6:  //левый софт
         var2 = 18;
         break;
      case -5:  //огонь/центр
         var2 = 4;
         break;
      case -2:  //вниз
         var2 = 3;
         break;
      case -4:  //вправо
         var2 = 2;
         break;
      case -3:  //влево
         var2 = 1;
         break;
      case -1:  //вверх
         var2 = 0;
         break;
      case 35:  //клавиша #
         var2 = 15;
         break;
      case 42:  //клавиша *
         var2 = 16;
         break;
      case 48:  //клавиша 0
         var2 = 5;
         break;
      case 49:  //клавиша 1
         var2 = 6;  
         break;
      case 50:  //клавиша 2
         var2 = 7;
         break;
      case 51:  //клавиша 3
         var2 = 8;
         break;
      case 52:  //клавиша 4
         var2 = 9;
         break;
      case 53:  //клавиша 5
         var2 = 10;
         break;
      case 54:  //клавиша 6
         var2 = 11;
         break;
      case 55:  //клавиша 7
         var2 = 12;
         break;
      case 56:  //клавиша 8
         var2 = 13;
         break;
      case 57:  //клавиша 9
         var2 = 14;
      }

      if(var2 >= 0) {
         if(o != 12 && o != 1 && j == null) {
            u[var2] = 20;
            v[var2] = 0;
         } else {
            k = 0L;
         }
      }

      if(c && o > 13) {
         yB = var1;
      }

   }

   protected void keyRepeated(int var1) {}
//------ Обработка клавиш клавиатуры (отпущенных)
   protected void keyReleased(int var1) {	//обработка опускания клавиш
      switch(var1) {
      case -7:  //правый софт
         v[17] = 10;
         break;
      case -6:  //левый софт
         v[18] = 10;
         break;
      case -5:  //огонь/центр
         v[4] = 10;
         break;
      case -2:  //вниз
         v[3] = 10;
         break;
      case -4:  //вправо
         v[2] = 10;
         break;
      case -3:  //влево
         v[1] = 10;
         break;
      case -1:  //вверх
         v[0] = 10;
         break;
      case 35:  //клавиша #
         v[15] = 10;
         break;
      case 42:  //клавиша *
         v[16] = 10;
         break;
      case 48:  //клавиша 0
         v[5] = 10;
         break;
      case 49:  //клавиша 1
         v[6] = 10;
         break;
      case 50:  //клавиша 2
         v[7] = 10;
         break;
      case 51:  //клавиша 3
         v[8] = 10;
         break;
      case 52:  //клавиша 4
         v[9] = 10;
         break;
      case 53:  //клавиша 5
         v[10] = 10;
         break;
      case 54:  //клавиша 6
         v[11] = 10;
         break;
      case 55:  //клавиша 7
         v[12] = 10;
         break;
      case 56:  //клавиша 8
         v[13] = 10;
         break;
      case 57:  //клавиша 9
         v[14] = 10;
      }

   }

   static void D() {
      for(int var0 = 0; var0 < 22; ++var0) {
         v[var0] = 10;
      }

   }

   static void E() {
      for(int var7 = 0; var7 < s; ++var7) {
         byte[] var6 = r[var7];
         byte var0 = var6[0];
         int var5 = B(var0);
         
         int var4 = var6.length;
         boolean var3 = false;
         boolean var2 = false;

         for(int var1 = 2; var1 < var4; ++var1) {
            var0 = var6[var1];
            if(u[var0] == 20) {
               var2 = true;
            } else if(u[var0] == 30) {
               var3 = true;
            }
         }

         if(var2) {
            t[var5] = 5;
         } else if(var3) {
            t[var5] = 10;
         } else {
            t[var5] = 0;
         }
      }

      for(int var8 = 0; var8 < 22; ++var8) {
         if(u[var8] == 20) {
            u[var8] = 30;
         }

         if(v[var8] == 10 || v[var8] == 40) {
            u[var8] = 10;
            v[var8] = 0;
         }
      }

   }

   static int B(int var0) {
      int var3 = -1;

      for(int var2 = s - 1; var2 >= 0; --var2) {
         byte[] var1 = r[var2];
         if(var0 == var1[0]) {
            var3 = var2;
            break;
         }
      }

      return var3;
   }
//------ Обработка нажатий клавиш (для навигации по меню)
   static boolean C(int var0) {
      boolean var1 = false;
      var0 = B(var0);
      if(var0 >= 0) {
         var1 = t[var0] == 5;
         if(!var1) {
            byte[] var2 = r[var0];
            boolean var3 = t[var0] == 10;
            if(var3) {
               var3 = var2[1] == 15;
               var1 = var3;
            }
         }
      }

      return var1;
   }

   static void F() {
      int var1 = t.length;

      for(int var0 = 0; var0 < var1; ++var0) {
         t[var0] = 0;
      }

   }

   public static final int A(int var0, int var1) {
      if(var1 == var0) {
         return var0;
      } else {
         if(var1 < var0) {
            int var2 = var0;
            var0 = var1;
            var1 = var2;
         }

         var1 = var1 - var0 + 1;
         return var0 + (w.nextInt() & Integer.MAX_VALUE) % var1;
      }
   }

   public static final int D(int var0) {
      return var0 <= 0?0:(w.nextInt() & Integer.MAX_VALUE) % var0;
   }

   public static int E(int var0) {
      int var2 = 0;
      int var1 = '\u8000';

      do {
         var2 ^= var1;
         if(var2 * var2 > var0) {
            var2 ^= var1;
         }
      } while((var1 >>= 1) != 0);

      return var2;
   }
//------ Определение размера отдельной картинки из файла ".png"
   public static void G() {
      if(x == null) {
         x = new Image[180];
      }

      for(int var2 = 0; var2 < 180; ++var2) {
         try {
            if(y[var2]) {
               if(x[var2] == null) {
                  String var1 = "/";
                  short var0 = HG.ob[var2][0];
                  if(var0 < 10) {
                     var1 = "/" + "00";
                  } else if(var0 < 100) {
                     var1 = "/" + "0";
                  }

                  String var4 = var1 + var0 + ".png";
                  x[var2] = Image.createImage(var4);
                  if(x[var2] != null) {
                     int var6 = HG.ob[var2][1] & 255;
                     int var5 = HG.ob[var2][2] & 255;
                     HG.ob[var2][3] = (short)(x[var2].getWidth() / var6);
                     HG.ob[var2][4] = (short)(x[var2].getHeight() / var5);
                  }
               }
            } else {
               x[var2] = null;
            }
         } catch (Exception var3) {
            ;
         }
      }

   }
//------ Вывод курсора на холст, заставки, очистка экрана
   public static void A(Graphics var0, int var1, int var2, int var3, int var4) {
      if(var1 < 180) {
         if(x[var1] != null) {
            Image var11 = x[var1];
            if(var11 != null) {
               byte var10 = 0;
               byte var9 = 0;
               int var6;
               int var7;
               int var12;
               if(var2 >= 0) {
                  short[] var5 = HG.ob[var1];
                  int var8 = var5[1] & 255;
                  var7 = var5[2] & 255;
                  var6 = var5[3] & 255;
                  var12 = var5[4] & 255;
                  if(var1 >= 37) {
                     var10 = (byte)((60 - var6) / 2);   //определение вывода юнита по центру оси x   var10 = (byte)((32 - var6) / 2);
                     var9 = (byte)((50 - var12) / 2);   //определение вывода юнита по центру оси y   var9 = (byte)((32 - var12) / 2);
                  }

                  if(var6 == 0 || var12 == 0) {
                     return;
                  }

                  var1 = var3 + var10;
                  var4 += var9;
                  var3 = var1 - var2 % var8 * var6;
                  var7 = var4 - var2 / var8 % var7 * var12;
               } else {
                  var6 = var11.getWidth();
                  var12 = var11.getHeight();
                  var2 = var6 >> 1;
                  var3 -= var2;
                  var7 = var4 - var12;
                  var1 = var3;
                  var4 = var7;
               }

               var0.setClip(var1, var4, var6, var12);
               var0.drawImage(var11, var3, var7, 20);
            }

         }
      }
   }
//------ Вывод трапецеидальных кнопок меню (главное меню)
   public static void A(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      if(var1 < 180) {
         if(x[var1] != null) {
            Image var15 = x[var1];
            if(var15 != null) {
               byte var14 = 0;
               byte var13 = 0;
               if(var2 >= 0) {
                  short[] var9 = HG.ob[var1];
                  int var12 = var9[1] & 255;
                  int var11 = var9[2] & 255;
                  int var10 = var9[3] & 255;
                  int var16 = var9[4] & 255;
                  if(var1 >= 37) {
                     var14 = (byte)((32 - var10) / 2);  //перемещение на клетку??? var14 = (byte)((32 - var10) / 2);
                     var13 = (byte)((32 - var16) / 2);  //перемещение на клетку??? var13 = (byte)((32 - var16) / 2);
                  }

                  if(var10 == 0 || var16 == 0) {
                     return;
                  }

                  var1 = var3 + var14;
                  var4 += var13;
                  var3 = var1 - var2 % var12 * var10;
                  var2 = var4 - var2 / var12 % var11 * var16;
                  if(var1 < var5) {
                     var10 -= var5 - var1;
                     var1 = var5;
                  }

                  if(var4 < var6) {
                     var16 -= var6 - var4;
                     var4 = var6;
                  }

                  if(var1 + var10 > var5 + var7) {
                     var10 -= var1 + var10 - (var5 + var7);
                  }

                  if(var4 + var16 > var6 + var8) {
                     var16 -= var4 + var16 - (var6 + var8);
                  }

                  if(var10 > 0 && var16 > 0) {
                     var0.setClip(var1, var4, var10, var16);
                     var0.drawImage(var15, var3, var2, 20);
                  }
               }
            }

         }
      }
   }
//------ Прорисовка местности в игре
   public static void B(Graphics var0, int var1, int var2, int var3, int var4) {
      int var8 = var3;
      int var9;
      //int var10;
      var0.setColor(h[29]);
      var0.setClip(0, 0, f, g);
      var0.fillRect(0, 0, f, g);

      for(int var7 = var1; var7 <= var1 + (f - 25) / 45 + 2 && var7 < oA - 1 && var7 >= 0; ++var7) {    
    //for(int var7 = var1; var7 <= var1 + (f - 16) / 25 + 2 && var7 < oA - 1 && var7 >= 0; ++var7) {
         int var6 = (var7 & 1) == 1?var4 + 25:var4; 
       //int var6 = (var7 & 1) == 1?var4 + 16:var4;

         for(int var5 = var2; var5 <= var2 + g / 50 + 1 && var5 < pA - 1 && var5 >= 0; ++var5) {    
       //for(int var5 = var2; var5 <= var2 + g / 32 + 1 && var5 < pA - 1 && var5 >= 0; ++var5) {
            var0.setColor(h[29]);
            var0.setClip(0, 0, f, g);
            if(var5 < pA && rA[2][var5][var7] != -1) {
               var3 = vb[var5][var7];   //новое состояние закраски гексов
               var9 = vb2[var5][var7];  //предыдущее состояние закраски гексов (состоит из значения 4)
               //var10 = yb[var5][var7];  //состояние закраски городов
               if(ac) {
                  var3 = 2;
               }

               if(var3 != 0) {  //если незапрещено движение
                  if(rA[0][var5][var7] != -1) {
                     A(var0, sA, rA[0][var5][var7], var8 + offset_x, var6 + offset_y);
                  }

                  if(rA[1][var5][var7] != -1) {
                     A(var0, 3, rA[1][var5][var7], var8 + offset_x, var6 + offset_y);
                  }
                  
                  if(var3 == 1 && var9 != 4 && var9 != 5) {
                     A(var0, 6, 1, var8 + offset_x, var6 + offset_y);    //закраска серым где ходить (на черных гексах), кроме городов и нет закраски
                  }
                  
                  if(var3 == 3) {
                     A(var0, 6, 3, var8 + offset_x, var6 + offset_y);   //закраска красным, расстановка купленной техники
                  }
                  if(var3 == 1 && var9 == 5) {                          //территория около городов
                     A(var0, 6, 2, var8 + offset_x, var6 + offset_y);   //закраска желтым, на видимых гексах и городах (заместо серых гексов)
                  }
                  if(var3 == 1 && var9 == 4) {
                     A(var0, 6, 2, var8 + offset_x, var6 + offset_y);   //закраска желтым, если нет закраски и серая закраска
                  }
               }
               //Закрашивание черными квадратами сторон карты местности
               if(var7 == 1) {
                  var0.fillRect(var8 + offset_x, var6 + offset_y, 45, 50);    //закрашивание левой части карты      
                //var0.fillRect(var8, var6, 25, 32);
               } else if(var7 == oA - 2) {
                  var0.fillRect(var8 + 15 + offset_x, var6 + offset_y, 50, 50);  //закрашивание правой части карты    
                //var0.fillRect(var8 + 7, var6, 32, 32);
               }

               if(var5 == 1) {
                  if((var7 & 1) == 0) {
                     var0.fillRect(var8 + offset_x, var6 + offset_y, 60, 25);   //закрашивание верхней части карты     
                   //var0.fillRect(var8, var6, 32, 16);
                  }
               } else if(var5 == pA - 2 && (var7 & 1) == 1) {
                  var0.fillRect(var8 + offset_x, var6 + 25 + offset_y, 60, 50); //закрашивание нижней части карты
                //var0.fillRect(var8, var6 + 16, 32, 32);
               }
            }

            var6 += 50; //y координаты  var6 += 32;
         }

         var8 += 45;    //x координаты  var8 += 25;
      }
    
   }
//------Построение видимой территории юнитами, городами и аэропортами
   static void A(int[][] var0, int[][] var1, int[][] var2, int[] var3) {
      vb = new int[pA][oA];
      vb2 = new int[pA][oA];
      vb3 = new int[pA][oA];
      vb4 = new int[pA][oA];
      int var4;
      int var5;
      int var6;
      int var7;
      int var8;
      int var11;
      int[][] var12; //вражеские юниты
      alpha_pos = false;
      enemy_pos = false;
      if (var3 != null)  {
        alpha_pos = true; 
      }
      for(var11 = 0; var11 < var0.length; ++var11) {
         yb = new int[pA][oA];
         F(var0[var11][1], var0[var11][2]);   //видимая территория вокруг своих городов
      }
      
      for(var11 = 0; var11 < var1.length; ++var11) {
         yb = new int[pA][oA];
         F(var1[var11][1], var1[var11][2]);   //видимая территория вокруг своих аэродромов
      }
      
     
      var12 = db == 0?va:ta;
        for(var8 = 0; var8 < var12.length; ++var8) {
         if(var3 != var12[var8]) {
            var7 = var12[var8][1];  //координата X
            var6 = var12[var8][2];  //координата Y
            var5 = da[var12[var8][0]][5];
            yb = new int[pA][oA];
            if (var12[var8][23] == 1) {
                A(var7, var6, 1, 5, var5); //территория вокруг видимых вражеских юнитов
            } else {
                A(var7, var6, 1, 4, var5); //территория вокруг скрытых вражеских юнитов    
            }
         }
      }
       
      
      for(var8 = 0; var8 < var2.length; ++var8) {
         if(var3 != var2[var8]) {
            var7 = var2[var8][1];
            var6 = var2[var8][2];
            var5 = da[var2[var8][0]][5];
            var4 = da[var2[var8][0]][19];
            if(var2[var8][25] != -1) {
               var4 += ea[var2[var8][25]][7];
            }

            if(var5 != 5) {
               if(rA[2][var6][var7] == 3) {
                  --var4;
               } else if(rA[2][var6][var7] == 4) {
                  ++var4;
               }
            }

            yb = new int[pA][oA];
            A(var7, var6, var4, 2, var5); //видимая территория вокруг своих юнитов
         }
      }
      
      if(var3 != null) {
         var8 = var3[1];
         var7 = var3[2];
         var6 = da[var3[0]][5];     //тип юнита
         var4 = da[var3[0]][18];    //запас хода юнита
         var5 = B(var4, var3);
         var4 = da[var3[0]][19];    //зрение юнита
         if(var3[25] != -1) {
            var4 += ea[var3[25]][7];
         }

         if(var6 != 5) {    //в зависимости от погоды зрение юнита меняется (кроме самолетов)
            if(rA[2][var7][var8] == 3) {
               --var4;
            } else if(rA[2][var7][var8] == 4) {
               ++var4;
            }
         }
         yb = new int[pA][oA];
         A(var8, var7, var4, 2, var6);  //видимая территория на ближайших гексах около юнита (радиус 1 - 4 клетки)

         yb = new int[pA][oA]; 
         A(var8, var7, var5, 1, var6);  //серая территория на черных гексах в пределах хода юнита
         
      }



      


//Всю оставшуюся территорию (аэродромы и т.д.) сделать видимыми
//      int var10 = 0;
//      for(var11 = 0; var11 < var1.length; ++var11) {
//         int var9;
//         for(var9 = var1[var11][1] + -2; var9 <= var1[var11][1] + 2; ++var9) {
//            for(var10 = var1[var11][2] + -1; var10 <= var1[var11][2] + 1; ++var10) {
//               if(var9 > 0 && var9 < oA - 1 && var10 > 0 && var10 < pA - 1) {
//                  vb[var10][var9] = 2;
//               }
//            }
//         }
//
//         for(var9 = var1[var11][1] + -1; var9 <= var1[var11][1] + 1; ++var9) {
//            if(var9 > 0 && var9 < oA - 1 && var10 > 0 && var10 < pA - 1) {
//               vb[var10][var9] = 2;
//            }
//         }
//
//         for(var9 = var1[var11][1] + -1; var9 <= var1[var11][1] + 1; ++var9) {
//            if(var9 > 0 && var9 < oA - 1 && var10 > 0 && var10 < pA - 1) {
//               vb[var10][var9] = 2;
//            }
//         }
//      }

   }

   static void H() {
      if(la > oA - ((f - 25) / 45 + 2) - 1) {
         la = oA - ((f - 25) / 45 + 2) - 1;
      } else if(la < 0) {
         la = 0;
      }

      if(ma > pA - (g / 50 + 1)) {
         ma = pA - (g / 50 + 1);
      } else if(ma < 0) {
         ma = 0;
      }

   }

   static int A(int var0, int var1, int var2, boolean var3) {
      int[][] var7 = var1 == 0?vA:wA;
      int[][] var6 = var1 == 0?wA:vA;
      int[][] var5 = new int[var6.length + 1][5];
      int[][] var4;
      if(var7.length - 1 <= 0) {
         var4 = new int[0][5];
      } else {
         var4 = new int[var7.length - 1][5];
      }

      var5[0][1] = var7[var0][1];
      var5[0][2] = var7[var0][2];
      var5[0][0] = var2;
      var5[0][3] = var7[var0][3];
      var5[0][4] = var7[var0][4];
      var7[var0][1] = -1;
      int var8 = 0;

      for(var2 = 0; var8 < var7.length; ++var8) {
         if(var7[var8][1] != -1) {
            System.arraycopy(var7[var8], 0, var4[var2], 0, 5);
            ++var2;
         }
      }

      for(var2 = 0; var2 < var6.length; ++var2) {
         System.arraycopy(var6[var2], 0, var5[var2 + 1], 0, 5);
      }

      if(var1 == 0) {
         vA = var4;
         wA = var5;
      } else {
         vA = var5;
         wA = var4;
      }

      if(var3) {
         if(var1 == 0) {
            B(5, var7[var0][3], 0, true);
         } else {
            if(dA != null) {
               B(3, var7[var0][3], da[dA[0]][4], true);
            }

            B(2, var7[var0][3], 0, true);
         }
      }

      return 1;
   }

   static int A(int[] var0) {
      if(da[var0[0]][5] != 5) {
         int[][] var2 = db == 0?wA:vA;
         int var1 = var0[1];
         int var3 = var0[2];

         for(int var4 = 0; var4 < var2.length; ++var4) {
            if(var1 == var2[var4][1] && var3 == var2[var4][2]) {
               return var4;
            }
         }
      }

      return -1;
   }

   static String B(int var0, int var1) {
      int var2;
      for(var2 = 0; var2 < vA.length; ++var2) {
         if(var1 == vA[var2][1] && var0 == vA[var2][2]) {
            return HG.H(vA[var2][3]);
         }
      }

      for(var2 = 0; var2 < wA.length; ++var2) {
         if(var1 == wA[var2][1] && var0 == wA[var2][2]) {
            return HG.H(wA[var2][3]);
         }
      }

      return null;
   }

   static String C(int var0, int var1) {
      int var2;
      for(var2 = 0; var2 < xA.length; ++var2) {
         if(var1 == xA[var2][1] && var0 == xA[var2][2]) {
            return HG.H(xA[var2][3]);
         }
      }

      for(var2 = 0; var2 < yA.length; ++var2) {
         if(var1 == yA[var2][1] && var0 == yA[var2][2]) {
            return HG.H(yA[var2][3]);
         }
      }

      return null;
   }

   static int A(int var0, int var1, int var2) {
      int[][] var6 = var1 == 0?xA:yA;
      int[][] var5 = var1 == 0?yA:xA;
      int[][] var4 = new int[var5.length + 1][5];
      int[][] var3;
      if(var6.length - 1 <= 0) {
         var3 = new int[0][5];
      } else {
         var3 = new int[var6.length - 1][5];
      }

      var4[0][1] = var6[var0][1];
      var4[0][2] = var6[var0][2];
      var4[0][0] = var2;
      var4[0][3] = var6[var0][3];
      var4[0][4] = var6[var0][4];
      var6[var0][1] = -1;
      int var7 = 0;

      for(var2 = 0; var7 < var6.length; ++var7) {
         if(var6[var7][1] != -1) {
            System.arraycopy(var6[var7], 0, var3[var2], 0, 5);
            ++var2;
         }
      }

      for(var2 = 0; var2 < var5.length; ++var2) {
         System.arraycopy(var5[var2], 0, var4[var2 + 1], 0, 5);
      }

      if(var1 == 0) {
         xA = var3;
         yA = var4;
      } else {
         xA = var4;
         yA = var3;
      }

      if(var1 != 0) {
         B(4, var6[var0][3], 0, true);
      }

      return 1;
   }

   static int B(int[] var0) {
      if(da[var0[0]][5] != 5) {
         int[][] var2 = db == 0?yA:xA;
         int var1 = var0[1];
         int var3 = var0[2];

         for(int var4 = 0; var4 < var2.length; ++var4) {
            if(var1 == var2[var4][1] && var3 == var2[var4][2]) {
               return var4;
            }
         }
      }

      return -1;
   }

   static boolean D(int var0, int var1) {
      int[][] var6 = db == 0?xA:yA;

      for(int var5 = 0; var5 < var6.length; ++var5) {
         int var3 = var6[var5][1];
         int var2 = var6[var5][2];
         if(var3 == var0 && var2 == var1) {
            return true;
         }

         for(int var4 = 6; var4 < 12; var4 += 2) {
            var3 = var6[var5][1] + nB[var6[var5][1] & 1][var4];
            var2 = var6[var5][2] + nB[var6[var5][1] & 1][var4 + 1];
            if(var3 == var0 && var2 == var1) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean F(int var0) {
      if(H(var0)) {
         ByteArrayInputStream var2 = null;
         DataInputStream var1 = null;

         try {
            byte[] var15 = HG.C(var0);
            var2 = new ByteArrayInputStream(var15);
            var1 = new DataInputStream(var2);
            ab = var1.readByte();
            bb = var1.readByte();
            uA[0] = var1.readShort();
            uA[1] = var1.readShort();
            if(var1.readBoolean()) {
               short var3 = var1.readShort();

               for(var0 = 0; var0 < 11; ++var0) {
                  gb[var0] = (var3 >> var0 & 1) == 1;
               }

               lb[0] = var1.readByte();
               lb[1] = var1.readByte();
               lb[2] = var1.readByte();
               lb[3] = var1.readShort();
               lb[4] = var1.readByte();
               ua = A(var1, false, 0);
            }

            vB = var1.readShort();
            if(!var1.readBoolean()) {
               A(bb, false);
               vA = (int[][])null;
               xA = (int[][])null;
               ta = (int[][])null;
               wA = (int[][])null;
               yA = (int[][])null;
               va = (int[][])null;
               eb = var1.readByte();
               db = var1.readByte();
               A(var1, false);
               byte var6 = var1.readByte();
               byte var5 = var1.readByte();
               byte var4 = var1.readByte();
               byte var17 = var1.readByte();
               byte var16 = var1.readByte();
               gA = var1.readBoolean();
               if(var6 != -1) {
                  bA = ((var6 & 64) == 0?ta:va)[var6 & 63];
               }

               if(var5 != -1) {
                  cA = ((var5 & 64) == 0?ta:va)[var5 & 63];
               }

               if(var4 != -1) {
                  dA = ((var4 & 64) == 0?ta:va)[var4 & 63];
               }

               if(var17 != -1) {
                  eA = ((var17 & 64) == 0?ta:va)[var17 & 63];
               }

               if(var16 != -1) {
                  fA = ((var16 & 64) == 0?ta:va)[var16 & 63];
               }

               int var18 = var1.readInt();

               for(var0 = 0; var0 < sB.length; ++var0) {
                  sB[var0][1] = var18 >> var0 & 1;
               }

               for(int var20 = 0; var20 < 4; ++var20) {
                  int[] var19 = uB[var20];
                  if(var1.readBoolean()) {
                     var19[0] = 1;
                     var19[1] = var1.readByte();
                     var19[2] = var1.readByte();
                     var19[3] = var1.readShort();
                     var19[5] = var1.readShort();
                     var19[6] = var1.readShort();
                     var19[7] = var1.readByte();

                     for(var0 = 8; var0 <= var19[7]; ++var0) {
                        var19[var0] = var1.readShort();
                     }
                  } else {
                     var19[0] = 0;
                  }
               }

               for(var0 = 0; var0 < 3; ++var0) {
                  lA[var0] = var1.readByte();
               }

               jb = var1.readShort();
               ib = var1.readByte();

               for(var0 = 0; var0 < hb.length; ++var0) {
                  hb[var0] = var1.readByte();
               }

               if(db == 0) {
                  A(vA, xA, ta, dA);    //определение видимой территории
               } else {
                  A(wA, yA, va, dA);    //определение видимой территории
               }
            }

            ka = true;
            return true;
         } catch (Exception var13) {
            ;
         } finally {
            try {
               var1.close();
               var2.close();
            } catch (Exception var12) {
               ;
            }

         }

         return false;
      } else {
         return false;
      }
   }

   private static void A(DataInputStream var0, boolean var1) throws IOException {
      int[][][] var4 = new int[2][][];
      int[][][] var3 = new int[2][][];
      int[][][] var2 = new int[2][][];

      int var7;
      for(var7 = 0; var7 < 2; ++var7) {
         pa[var7] = var0.readByte();
         qa[var7] = var0.readByte();
         byte var6 = var0.readByte();
         var4[var7] = new int[var6][5];

         int var5;
         for(var5 = 0; var5 < var6; ++var5) {
            var4[var7][var5][1] = var0.readByte();
            var4[var7][var5][2] = var0.readByte();
            var4[var7][var5][0] = var0.readByte();
            var4[var7][var5][3] = var0.readByte() << 16 | var0.readShort();
            var4[var7][var5][4] = var1?var7:var0.readByte();
         }

         var6 = var0.readByte();
         var3[var7] = new int[var6][5];

         for(var5 = 0; var5 < var6; ++var5) {
            var3[var7][var5][1] = var0.readByte();
            var3[var7][var5][2] = var0.readByte();
            var3[var7][var5][0] = var0.readByte();
            var3[var7][var5][3] = var0.readByte() << 16 | var0.readShort();
            var3[var7][var5][4] = var1?var7:var0.readByte();
         }

         var2[var7] = A(var0, var1, var7);
      }

      int var8;
      int var10;
      if(var1 && ta != null) {
         if(ua == null) {
            Vector var9 = new Vector();

            for(var8 = 0; var8 < ta.length; ++var8) {
               if(ta[var8][14] == 1) {
                  if(ta[var8][19] >= 0) {
                     ta[var8][0] = ta[var8][19];
                     ta[var8][19] = -1;
                  }

                  var9.addElement(ta[var8]);
               }
            }

            ua = new int[var9.size()][28];

            for(var8 = 0; var8 < ua.length; ++var8) {
               ua[var8] = (int[])var9.elementAt(var8);
            }
         }

         var7 = 0;
         if(bb > 0) {
            for(int var12 = 0; var12 < var2[0].length; ++var12) {
               if(var2[0][var12][14] == 1) {
                  if(var7 < ua.length && var7 < 10) {
                     var2[0][var12][0] = ua[var7][0];
                     if(bb > 0) {
                        var10 = da[ua[var7][0]][24];
                        if(var10 >= 0 && cb >= da[var10][2]) {
                           var8 = -1;
                           switch(ab) {
                           case 0:
                           case 3:
                              var8 = da[var10][7];
                              break;
                           case 1:
                              var8 = da[var10][8];
                              break;
                           case 2:
                              var8 = da[var10][9];
                           }

                           if(var8 != -1 && bb >= var8) {
                              var2[0][var12][0] = var10;
                           }
                        }
                     }

                     var2[0][var12][9] = ua[var7][9];
                     var2[0][var12][10] = ua[var7][10];
                     var2[0][var12][13] = ua[var7][13];
                     var2[0][var12][25] = var2[0][var12][13];
                     var2[0][var12][12] = da[var2[0][var12][0]][23];
                     if(var2[0][var12][13] != -1) {
                        var2[0][var12][8] = 1;
                     } else {
                        var2[0][var12][8] = 0;
                     }

                     if(da[var2[0][var12][0]][27] > 0) {
                        var2[0][var12][11] = da[da[var2[0][var12][0]][27]][22];
                     } else {
                        var2[0][var12][11] = da[var2[0][var12][0]][22];
                     }
                  } else {
                     int[][] var11 = new int[var2[0].length - 1][28];
                     var10 = 0;

                     for(var8 = 0; var8 < var2[0].length; ++var8) {
                        if(var8 != var12) {
                           System.arraycopy(var2[0][var8], 0, var11[var10], 0, 28);
                           ++var10;
                        }
                     }

                     --var12;
                     var2[0] = var11;
                     int[][] var10000 = (int[][])null;
                  }

                  ++var7;
               }
            }
         }
      }

      vA = var4[0];
      xA = var3[0];
      ta = var2[0];
      wA = var4[1];
      yA = var3[1];
      va = var2[1];
      if((zA & 15) == 3) {
         var10 = (vA.length + wA.length) / 2;
         if(vA.length < 1) {
            wA[0][4] = 0;
            A(0, 1, ra, false);
         }

         for(var8 = 1; var8 < wA.length; ++var8) {
            if(A(0, 1) == 1 && vA.length <= var10) {
               wA[var8][4] = 0;
               A(var8, 1, ra, false);
            }
         }

         for(var8 = 1; var8 < vA.length; ++var8) {
            if(A(0, 1) == 1 && wA.length <= var10) {
               vA[var8][4] = 1;
               A(var8, 0, sa, false);
            }
         }

         pa[0] = vA[0][1];
         qa[0] = vA[0][2];
         pa[1] = wA[1][1];
         qa[1] = wA[1][2];
      }

      la = pa[db] - ((f - 25) / 45 + 2) / 2;
      ma = qa[db] - (g / 50 + 1) / 2;
      if(la < 0) {
         la = 0;
      }

      if(ma < 0) {
         ma = 0;
      }

   }

   private static int[][] A(DataInputStream var0, boolean var1, int var2) throws IOException {
      byte var6 = var0.readByte();
      int[][] var5 = new int[var6][28];

      for(int var4 = 0; var4 < var6; ++var4) {
         var5[var4][1] = var0.readByte();
         var5[var4][2] = var0.readByte();
         var5[var4][0] = var0.readByte() & 255;
         var5[var4][9] = var0.readByte();
         var5[var4][5] = var0.readByte();
         var5[var4][10] = var1?hA[var0.readByte()]:var0.readShort();
         var5[var4][13] = var0.readByte();
         var5[var4][25] = var1?var5[var4][13]:var0.readByte();
         var5[var4][20] = var0.readByte();
         var5[var4][21] = var0.readByte();
         byte var3 = var0.readByte();
         var5[var4][14] = var3 & 1;
         if(var1) {
            var5[var4][6] = 10;
            if(var5[var4][13] != -1) {
               var5[var4][8] = 1;
            } else {
               var5[var4][8] = 0;
            }

            if(da[var5[var4][0]][27] > 0) {
               var5[var4][11] = da[da[var5[var4][0]][27]][22];
            } else {
               var5[var4][11] = da[var5[var4][0]][22];
            }

            var5[var4][12] = da[var5[var4][0]][23];
            var5[var4][19] = -1;
            var5[var4][22] = A(0, 100);
            var5[var4][23] = var2 ^ 1;
         } else {
            var5[var4][6] = var0.readByte();
            var5[var4][8] = var0.readByte();
            var5[var4][11] = var0.readByte();
            var5[var4][12] = var0.readByte();
            var5[var4][19] = var0.readByte();
            var5[var4][22] = var0.readByte();
            var5[var4][23] = var0.readByte();
            var5[var4][7] = var0.readByte();
            var5[var4][15] = var0.readByte();
            var5[var4][16] = var0.readByte();
            var5[var4][17] = var0.readByte();
            var5[var4][18] = var0.readByte();
            var5[var4][24] = var0.readByte();
            var5[var4][26] = var0.readByte();
         }
      }

      return var5;
   }

   public static boolean I() {
      boolean var2 = (zA & 16) == 16;
      boolean var1 = true;
      switch(zA & 15) {
      case 1:
         int var0 = 2 + ab;
         if(var2 && bb >= 11) {
            I(var0);
         } else {
            var1 = G(var0);
         }
         break;
      case 2:
         if(ab < 3) {
            if(var2) {
               I(5);
            } else {
               var1 = G(5);
            }
         }
         break;
      case 3:
         if(var2) {
            I(6);
         } else {
            var1 = G(6);
         }
      }

      return var1;
   }

   public static boolean G(int var0) {
      ByteArrayOutputStream var12 = null;
      DataOutputStream var11 = null;

      try {
         var12 = new ByteArrayOutputStream();
         var11 = new DataOutputStream(var12);
         boolean var1 = (zA & 15) == 1;
         boolean var3 = (zA & 16) == 16;
         var11.writeByte(ab);
         var11.writeByte(bb);
         var11.writeShort(uA[0]);
         var11.writeShort(uA[1]);
         var11.writeBoolean(var1);
         int var2;
         int var21;
         if(var1) {
            var2 = 0;

            for(var21 = 0; var21 < 11; ++var21) {
               if(gb[var21]) {
                  var2 |= 1 << var21;
               }
            }

            var11.writeShort(var2);
            var11.writeByte(lb[0]);
            var11.writeByte(lb[1]);
            var11.writeByte(lb[2]);
            var11.writeShort(lb[3]);
            var11.writeByte(lb[4]);
            A(var11, ua);
         }

         var11.writeShort(vB);
         var11.writeBoolean(var3);
         if(!var3) {
            int[][][] var10 = new int[][][]{ta, va};
            int[][][] var4 = new int[][][]{vA, wA};
            int[][][] var22 = new int[][][]{xA, yA};
            var11.writeByte(eb);
            var11.writeByte(db);

            for(var2 = 0; var2 < 2; ++var2) {
               var11.writeByte(pa[var2]);
               var11.writeByte(qa[var2]);
               var11.writeByte(var4[var2].length);

               for(var21 = 0; var21 < var4[var2].length; ++var21) {
                  var11.writeByte(var4[var2][var21][1]);
                  var11.writeByte(var4[var2][var21][2]);
                  var11.writeByte(var4[var2][var21][0]);
                  var11.writeByte(var4[var2][var21][3] >> 16);
                  var11.writeShort(var4[var2][var21][3] & '\uffff');
                  var11.writeByte(var4[var2][var21][4]);
               }

               var11.writeByte(var22[var2].length);

               for(var21 = 0; var21 < var22[var2].length; ++var21) {
                  var11.writeByte(var22[var2][var21][1]);
                  var11.writeByte(var22[var2][var21][2]);
                  var11.writeByte(var22[var2][var21][0]);
                  var11.writeByte(var22[var2][var21][3] >> 16);
                  var11.writeShort(var22[var2][var21][3] & '\uffff');
                  var11.writeByte(var22[var2][var21][4]);
               }

               A(var11, var10[var2]);
            }

            byte var9 = -1;
            byte var8 = -1;
            byte var7 = -1;
            byte var6 = -1;
            byte var5 = -1;

            int[] var23;
            int var25;
            for(int var26 = 0; var26 < 2; ++var26) {
               var25 = var10[var26].length;

               while(true) {
                  --var25;
                  if(var25 < 0) {
                     break;
                  }

                  var23 = var10[var26][var25];
                  byte var24 = (byte)(var26 << 6 | var25 & 63);
                  if(var23 == bA) {
                     var9 = var24;
                  }

                  if(var23 == cA) {
                     var8 = var24;
                  }

                  if(var23 == dA) {
                     var7 = var24;
                  }

                  if(var23 == eA) {
                     var6 = var24;
                  }

                  if(var23 == fA) {
                     var5 = var24;
                  }
               }
            }

            var11.writeByte(var9);
            var11.writeByte(var8);
            var11.writeByte(var7);
            var11.writeByte(var6);
            var11.writeByte(var5);
            var11.writeBoolean(gA);
            var2 = 0;

            for(var21 = 0; var21 < sB.length; ++var21) {
               var2 |= sB[var21][1] << var21;
            }

            var11.writeInt(var2);

            for(var25 = 0; var25 < 4; ++var25) {
               var23 = uB[var25];
               if(var23[0] == 1) {
                  var11.writeBoolean(true);
                  var11.writeByte(var23[1]);
                  var11.writeByte(var23[2]);
                  var11.writeShort(var23[3]);
                  var11.writeShort(var23[5]);
                  var11.writeShort(var23[6]);
                  var11.writeByte(var23[7]);

                  for(var21 = 8; var21 <= var23[7]; ++var21) {
                     var11.writeShort(var23[var21]);
                  }
               } else {
                  var11.writeBoolean(false);
               }
            }

            for(var21 = 0; var21 < 3; ++var21) {
               var11.writeByte(lA[var21]);
            }

            var11.writeShort(jb);
            var11.writeByte(ib);

            for(var21 = 0; var21 < hb.length; ++var21) {
               var11.writeByte(hb[var21]);
            }
         }

         byte[] var27 = var12.toByteArray();
         HG.A(var0, var27);
         return true;
      } catch (Exception var19) {
         ;
      } finally {
         try {
            var11.close();
            var12.close();
         } catch (Exception var18) {
            ;
         }

      }

      return false;
   }

   private static void A(DataOutputStream var0, int[][] var1) throws IOException {
      if(var1 == null) {
         var0.writeByte(0);
      } else {
         var0.writeByte(var1.length);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeByte(var1[var2][1]);
            var0.writeByte(var1[var2][2]);
            var0.writeByte(var1[var2][0]);
            var0.writeByte(var1[var2][9]);
            var0.writeByte(var1[var2][5]);
            var0.writeShort(var1[var2][10]);
            var0.writeByte(var1[var2][13]);
            var0.writeByte(var1[var2][25]);
            var0.writeByte(var1[var2][20]);
            var0.writeByte(var1[var2][21]);
            var0.writeByte(var1[var2][14]);
            var0.writeByte(var1[var2][6]);
            var0.writeByte(var1[var2][8]);
            var0.writeByte(var1[var2][11]);
            var0.writeByte(var1[var2][12]);
            var0.writeByte(var1[var2][19]);
            var0.writeByte(var1[var2][22]);
            var0.writeByte(var1[var2][23]);
            var0.writeByte(var1[var2][7]);
            var0.writeByte(var1[var2][15]);
            var0.writeByte(var1[var2][16]);
            var0.writeByte(var1[var2][17]);
            var0.writeByte(var1[var2][18]);
            var0.writeByte(var1[var2][24]);
            var0.writeByte(var1[var2][26]);
         }

      }
   }

   public static boolean H(int var0) {
      return HG.A(var0);
   }

   public static void I(int var0) {
      if(H(var0)) {
         HG.B(var0);
      }

   }

   public static boolean A(int var0, boolean var1) {
      InputStream var4 = null;

      try {
         HG.k[1] = false;
         HG.k[2] = false;
         HG.k[3] = false;
         HG.k[4] = false;
         HG.k[1 + ab] = true;
         HG.F();
         var4 = HG.qb.getClass().getResourceAsStream("/d");
         DataInputStream var3 = new DataInputStream(var4);
         var3.readShort();
         short var2 = var3.readShort();
         int var19 = 0;
         if(var0 >= var2) {
            return false;
         }

         if(var0 >= 0) {
            int var5;
            for(var5 = var0 + ab * 11; var19 != var5; ++var19) {
               var3.skip((long)var3.readShort());
            }

            var3.readShort();
            oA = var3.readShort();
            pA = var3.readShort();
            qA = f > g - i[1].getHeight() - 5?(g - i[1].getHeight() - 5) / (pA > oA?pA - 2:oA - 2):f / (pA > oA?pA - 2:oA - 2);
            if((qA & 1) == 1) {
               --qA;
            }

            if(qA < 2) {
               qA = 2;
            }

            sA = var3.readByte();
            byte var6 = var3.readByte();

            for(var5 = 0; var5 < var6; ++var5) {
               rA[var5] = A(var3);
            }

            rA[2] = new int[pA][oA];

            int var21;
            for(int var7 = 0; var7 < pA; ++var7) {
               for(var21 = 0; var21 < oA; ++var21) {
                  if(var21 != 0 && var21 != oA - 1 && var7 != 0 && var7 != pA - 1) {
                     var5 = rA[0][var7][var21];
                     if(var5 != -1) {
                        rA[2][var7][var21] = ba[var5];
                     }

                     var5 = rA[1][var7][var21];
                     if(var5 != -1) {
                        rA[2][var7][var21] = ca[var5];
                     }
                  } else {
                     rA[2][var7][var21] = 13;
                  }
               }
            }

            if(var0 < aa.length) {
               cb = aa[var0][1] + 1939;
            }

            ra = var3.readByte();
            sa = var3.readByte();
            mb = var3.readByte();
            nb = var3.readByte();
            ob = var3.readByte();
            mA = var3.readByte();
            lA[0] = var3.readByte();
            lA[1] = var3.readByte();
            lA[2] = var3.readByte();
            nA[0] = var3.readByte();
            nA[1] = var3.readByte();
            nA[2] = var3.readByte();
            nA[3] = var3.readByte();
            nA[4] = var3.readByte();
            A(var3, true);
            byte var23 = var3.readByte();
            sB = new int[var23][5];

            for(var21 = 0; var21 < var23; ++var21) {
               int[] var22 = sB[var21];
               int var17 = var22[0] = var3.readByte();
               var22[2] = var22[3] = 0;
               var22[1] = 1;
               switch(var17) {
               case 1:
                  var22[2] = var3.readByte();
                  var22[3] = var3.readByte();
                  break;
               case 2:
                  var22[2] = var3.readByte() << 16 | var3.readShort();
                  break;
               case 3:
                  var22[2] = var3.readByte() << 16 | var3.readShort();
                  var22[3] = var3.readByte();
                  break;
               case 4:
                  var22[2] = var3.readByte() << 16 | var3.readShort();
                  break;
               case 5:
                  var22[2] = var3.readByte() << 16 | var3.readShort();
               case 17:
               case 21:
               case 33:
               case 34:
               case 82:
               default:
                  break;
               case 18:
                  var22[2] = var3.readByte();
                  break;
               case 98:
                  var22[2] = var3.readByte();
               }

               var22[4] = var3.readByte();
            }

            var6 = var3.readByte();
            tB = new byte[var6][];

            for(var5 = 0; var5 < var6; ++var5) {
               short var18 = var3.readShort();
               tB[var5] = new byte[var18];
               var3.read(tB[var5], 0, var18);
            }

            A(vA, xA, ta, dA);    //определение видимой территории
            ++var19;
         }

         if(da == null || ea == null) {
            while(var19 != var2) {
               var3.skip((long)var3.readShort());
               ++var19;
            }

            var2 = var3.readShort();
            da = new int[var2][28];

            for(var19 = 0; var19 < var2; ++var19) {
               var0 = var3.readByte() & 255;
               da[var0][0] = qB[var3.readByte() & 255]; //порядковый номер
               da[var0][1] = var3.readByte();
               da[var0][2] = var3.readByte();
               da[var0][3] = var3.readByte();   //0
               da[var0][4] = var3.readByte();   //0
               da[var0][5] = var3.readByte();   //0
               da[var0][6] = var3.readByte();   //численность отряда
               da[var0][7] = var3.readByte();   //0 - неходил, 1 - ходил
               da[var0][8] = var3.readByte();   //0
               da[var0][9] = var3.readByte();   //0
               da[var0][10] = var3.readByte() << 16 | var3.readShort();
               da[var0][11] = var3.readShort(); //топливо
               da[var0][12] = var3.readByte();  //боеприпасы
               da[var0][13] = var3.readByte();
               da[var0][14] = var3.readByte();
               da[var0][15] = var3.readByte();   //0
               da[var0][16] = var3.readByte();   //0
               da[var0][17] = var3.readByte();   //0
               da[var0][18] = var3.readByte();   //0
               da[var0][19] = var3.readByte();
               da[var0][20] = var3.readByte();
               da[var0][21] = var3.readByte();
               da[var0][22] = var3.readByte();
               da[var0][23] = var3.readByte();
               da[var0][24] = var3.readShort();   //0
               da[var0][25] = var3.readShort();
               da[var0][26] = var3.readShort();   //0
               da[var0][27] = var3.readShort();   //0
            }

            short var20 = var3.readShort();
            ea = new int[var20][11];

            for(var0 = 0; var0 < var20; ++var0) {
               ea[var0][0] = var3.readByte() << 16 | var3.readShort();
               ea[var0][1] = var3.readByte();
               ea[var0][2] = var3.readShort();
               ea[var0][3] = var3.readByte();
               ea[var0][4] = var3.readByte();
               ea[var0][5] = var3.readByte();
               ea[var0][6] = var3.readByte();
               ea[var0][7] = var3.readByte();
               ea[var0][8] = var3.readByte();
               ea[var0][9] = var3.readByte();
               ea[var0][10] = var3.readByte();
            }

            qB = null;
         }

         return true;
      } catch (Exception var15) {
         ;
      } finally {
         try {
            var4.close();
         } catch (Exception var14) {
            ;
         }

      }

      return false;
   }

   public static int[][] A(DataInputStream var0) throws IOException {
      short var6 = var0.readShort();
      short var5 = var0.readShort();
      byte var2 = var0.readByte();
      int[][] var10000 = (int[][])null;
      int var1;
      int[][] var4;
      int var7;
      switch(var2) {
      case 0:
         var4 = new int[var5][var6];

         for(var7 = 0; var7 < var5; ++var7) {
            for(var1 = 0; var1 < var6; ++var1) {
               var4[var7][var1] = var0.readByte();
            }
         }

         return var4;
      case 1:
         var4 = new int[var5][var6];
         int var3 = 0;
         var7 = 0;

         while(var7 < var5) {
            var1 = var0.readByte();
            if(var1 < 0) {
               while(true) {
                  ++var1;
                  if(var1 > 0) {
                     break;
                  }

                  var4[var7][var3] = -1;
                  ++var3;
                  if(var3 >= var6) {
                     var3 = 0;
                     ++var7;
                  }
               }
            } else {
               while(true) {
                  --var1;
                  if(var1 < 0) {
                     break;
                  }

                  var4[var7][var3] = var0.readByte();
                  ++var3;
                  if(var3 >= var6) {
                     var3 = 0;
                     ++var7;
                  }
               }
            }
         }

         return var4;
      default:
         throw new IOException();
      }
   }

   public static void J() {
      ByteArrayOutputStream var2 = null;
      DataOutputStream var1 = null;

      try {
         var2 = new ByteArrayOutputStream();
         var1 = new DataOutputStream(var2);

         for(int var0 = 0; var0 < 3; ++var0) {
            if(kb[var0] != null) {
               var1.writeBoolean(true);
               var1.writeByte(kb[var0][0]);
               var1.writeByte(kb[var0][1]);
               var1.writeByte(kb[var0][2]);
               var1.writeShort(kb[var0][3]);
               var1.writeByte(kb[var0][4]);
            } else {
               var1.writeBoolean(false);
            }
         }

         byte[] var11 = var2.toByteArray();
         HG.A(0, var11);
      } catch (Exception var9) {
         ;
      } finally {
         try {
            var1.close();
            var2.close();
         } catch (Exception var8) {
            ;
         }

      }

   }

   public static void K() {
      kb = new int[3][];

      int var0;
      for(var0 = 0; var0 < kb.length; ++var0) {
         kb[var0] = null;
      }

      if(HG.A(0)) {
         ByteArrayInputStream var2 = null;
         DataInputStream var1 = null;

         try {
            byte[] var11 = HG.C(0);
            var2 = new ByteArrayInputStream(var11);
            var1 = new DataInputStream(var2);

            for(var0 = 0; var0 < 3; ++var0) {
               if(var1.readBoolean()) {
                  kb[var0] = new int[5];
                  kb[var0][0] = var1.readByte();
                  kb[var0][1] = var1.readByte();
                  kb[var0][2] = var1.readByte();
                  kb[var0][3] = var1.readShort();
                  kb[var0][4] = var1.readByte();
               } else {
                  kb[var0] = null;
               }
            }
         } catch (Exception var9) {
            ;
         } finally {
            try {
               var1.close();
               var2.close();
            } catch (Exception var8) {
               ;
            }

         }
      }

   }
//------ Вывод юнитов на карту местности
   public static void A(Graphics var0, int var1) {
      if((var1 & 1) == 1) {
         for(int var2 = 0; var2 < ta.length; ++var2) {
            if(dA != ta[var2]) {
               A(var0, ta[var2]);   //юниты в сценарии у игрока
            }
         }
      }

      if((var1 & 2) == 2) {
         for(var1 = 0; var1 < va.length; ++var1) {
            if(dA != va[var1]) {
               A(var0, va[var1]);   //юниты в сценарии у противника
            }
         }
      }

      if(dA != null) {
         A(var0, dA);       //перемещаемый юнит (нажата кнопка на юните)
      }

   }
//------ Вывод юнитов на карту местности
   static void A(Graphics var0, int[] var1) {
      int var3 = var1[1];
      int var2 = var1[2];
      if(ac) {
         vb[var2][var3] = 2;
      }

      if(vb[var2][var3] > 1 || vb2[var2][var3] > 3 || var1[23] == 1) { //выводить юнит (исключение: он на черном гексе (или серый на черном гексе в режиме передвижения юнита) или невиден)
         int var6 = var3 - la;
         int var5 = var2 - ma;
         int var4 = var6 * 45 + -23 + offset_x;    
       //int var4 = var6 * 25 + -23;
         var3 = var5 * 50 + ((var1[1] & 1) == 1?25:0) + -16 + offset_y;    
       //var3 = var5 * 32 + ((var1[1] & 1) == 1?16:0) + -16;
         if(var1[18] == 0) {
            var2 = da[var1[0]][0];
            if(var1[15] > 0 && o != 20) {
               A(var0, 6, 3, var6 * 45 + -23 + offset_x, var5 * 50 + ((var1[1] & 1) == 1?25:0) + -16 + offset_y); //вывод красной картинки на враге при атаке
             //A(var0, 6, 3, var6 * 25 + -23, var5 * 32 + ((var1[1] & 1) == 1?16:0) + -16);
            }
            //Вывод юнитов на карту местности
            A(var0, var2, pB[var1[5]], var4 + var1[3], var3 + var1[4]); //юниты
            if(!gA || dA != var1) {
               var5 = var1[14] == 1?0:1;
               var2 = var1[14] == 1?9:11;
               //Вывод кол-ва под картинкой юнита
               if(var1[7] == 1) {
                  A(var0, 13, 10, var4 + 16, var3 + 40);    //фон под цифрами кол-ва
                //A(var0, 13, 10, var4 + 2, var3 + 24);
                  C(var0, var1[6], var4 + 24, var3 + 41, var5); //цифры кол-ва
                //C(var0, var1[6], var4 + 10, var3 + 25, var5);
               } else {
                  A(var0, 13, var2, var4 + 16, var3 + 40);  //фон под цифрами кол-ва
                //A(var0, 13, var2, var4 + 2, var3 + 24);
                  C(var0, var1[6], var4 + 24, var3 + 41, var5); //цифры кол-ва
                //C(var0, var1[6], var4 + 10, var3 + 25, var5);
                  
               }
               //Вывод состояния и флага под картинкой юнита
               A(var0, 13, var1[9], var4 + 30, var3 + 40);  //флаг нации
               //A(var0, 13, var1[9], var4 + 15, var3 + 24);
               A(var0, 15, 0, var4 + 16, var3 + 36);        //состояние (3 черных точки)
               //A(var0, 15, 0, var4 + 2, var3 + 20);
               for(var2 = 0; var2 < da[var1[0]][3] + 1; ++var2) {
                  var0.setClip(0, 0, f, g);
                  var0.setColor(h[10]);
                  var0.fillRect(var4 + 17 + var2 * 3, var3 + 37, 2, 2); //состояние (1 светлая точка)
                //var0.fillRect(var4 + 3 + var2 * 3, var3 + 21, 2, 2);
               }
               //Вывод разных значков
               if(var1[12] == 0 && da[var1[0]][23] > 0) {
                  A(var0, 14, 4, var4 + 21, var3 + 17);
               }

               if(da[var1[0]][5] != 6 && var1[11] == 0 && da[var1[0]][22] > 0) {
                  A(var0, 14, 6, var4 + 13, var3 + 16);
               }

               if(var1[13] >= 0) {
                  for(var2 = 0; var2 < (ea[var1[13]][2] == 4000?1:(ea[var1[13]][2] == 6000?2:3)); ++var2) {
                     A(var0, 14, 7, var4 + 3 + var2 * HG.ob[14][3], var3 + 13);
                  }
               }
            }
         } else {
            var2 = 1;
            if(da[var1[0]][5] == 5) {
               var2 = 0;
            }

            if(var1[15] > 0 && o != 20) {
               var2 += 2;
            }

            A(var0, 14, var2, var4 + 23, var3 + 0);
         }
      }

   }

   static void A(Graphics var0, int var1, int var2, int[] var3) {
      if(var3 != null) {
         int var4 = var1;

         for(var1 = 1; var1 < 6; ++var1) {
            var4 += HG.ob[22][3] + 2;
            if(var3[10] >= hA[var1]) {
               A(var0, 22, 0, var4, var2);
            } else {
               A(var0, 22, 1, var4, var2);
            }
         }
      }

   }
//Получить выбранный юнит (выбрать среди юнитов) 
   static int[] A(int var0, int var1, int var2, int var3) { //коорд X, коорд Y, режим (1-выбор 1 юнита, 2-выбор 2 юнита, 3-...), сторона (1-игрок, 2...-противник)
      int[][] var6 = var3 == 1?ta:va;

      for(int var5 = 1; var5 < 3; ++var5) {
         if((var3 & var5) == var5) {
            if(var3 > 2) {
               var6 = var5 == 1?(db == 0?ta:va):(db == 0?va:ta);
            }

            for(int var4 = 0; var4 < var6.length; ++var4) {
               if(var0 == var6[var4][1] && var1 == var6[var4][2]) { //проверка координат на совпадение с юнитом
                  if((var2 & 1) == 1 && var6[var4][18] == 0) {  //выбор юнита (если на клетке 1 юнит)
                     return var6[var4];
                  }

                  if((var2 & 2) == 2 && var6[var4][18] == 1) {  //выбор воздушного юнита (если на клетке 2 юнита)
                     return var6[var4];
                  }
               }
            }
         }
      }

      return null;
   }

   static boolean A(int var0, int[] var1) {
      boolean var3 = false;
      int[][] var2 = var0 == 0?ta:va;
      int var4;
      int var5;
      int var6;
      if(var1 == null) {
         for(var6 = 0; var6 < var2.length; ++var6) {
            var5 = var2[var6][1];
            var4 = var2[var6][2];
            if(var2[var6][18] == 0) {
               var1 = A(var5, var4, 1, var0 == 0?2:1);
               if(var1 != null) {
                  var2[var6][18] = 1;
               }
            }

            var2[var6][15] = 0;
            if(vb[var4][var5] == 2) {
               var2[var6][23] = 1;
            } else {
               var2[var6][23] = 0;
            }
         }
      } else {
         int var9 = var1[1];
         int var8 = var1[2];
         if(lA[0] == 2 && da[var1[0]][5] == 5) {
            return false;
         }

         for(int var7 = 0; var7 < var2.length; ++var7) {
            var2[var7][15] = 0;
            var6 = var2[var7][1];
            var5 = var2[var7][2];
            if(lA[0] != 2 || da[var1[0]][4] != 7 || da[var2[var7][0]][5] != 5) {
               var4 = 0;
               if(var1[25] != -1) {
                  var4 = 0 + ea[var1[25]][7];
               }

               if(da[var1[0]][5] != 5) {
                  if(rA[2][var1[2]][var1[1]] == 3) {
                     --var4;
                  } else if(rA[2][var1[2]][var1[1]] == 4) {
                     ++var4;
                  }
               }

               var0 = B(var9, var8, var6, var5);
               if(vb[var5][var6] != 2 && var0 > da[var1[0]][19] + var4) {
                  var2[var7][23] = 0;
               } else if((da[var2[var7][0]][6] != 2 || da[var1[0]][14] != 0) && (da[var2[var7][0]][6] != 3 || da[var1[0]][15] != 0) && (da[var2[var7][0]][6] != 1 || da[var1[0]][12] != 0) && (da[var2[var7][0]][6] != 0 || da[var1[0]][13] != 0) && (da[var1[0]][5] != 5 || da[var2[var7][0]][5] == 5 || var0 <= 0) && (da[var1[0]][5] == 5 || da[var2[var7][0]][5] != 5 || var0 <= 0) && var0 >= da[var1[0]][20]) {
                  var4 = da[var1[0]][21];
                  if(var1[25] != -1) {
                     var4 += ea[var1[25]][8];
                  }

                  if(var0 <= var4) {
                     var2[var7][23] = 1;
                     A(var1, var2[var7], var0);
                     if(var2[var7][18] == 1) {
                        int[] var10 = A(var2[var7][1], var2[var7][2], 1, 3);
                        if(var10 != null) {
                           var10[18] = 1;
                           var2[var7][18] = 0;
                           if(var2[var7][15] > 0) {
                              hB = 3;
                           }
                        }
                     }

                     var3 = true;
                  }
               }
            }
         }
      }

      return var3;
   }

   static int B(int var0, int var1, int var2, int var3) {
      int var5 = var0 - var2 < 0?0 - (var0 - var2):var0 - var2;
      int var4 = var5 / 2;
      if((var0 & 1) == 0 && (var2 & 1) == 1) {
         ++var4;
      }

      var2 = var1 - var4;
      var1 = var2 + var5;
      var0 = 0;
      if(var3 < var2) {
         var0 = var2 - var3;
      }

      if(var3 > var1) {
         var0 = var3 - var1;
      }

      return var5 + var0;
   }

   static int C(int[] var0) {
      if(var0 == null) {
         return 0;
      } else {
         int var1 = var0[10];
         byte var2;
         if(var1 >= 500) {
            var2 = 5;
         } else if(var1 >= 300) {
            var2 = 4;
         } else if(var1 >= 200) {
            var2 = 3;
         } else if(var1 >= 125) {
            var2 = 2;
         } else if(var1 >= 60) {
            var2 = 1;
         } else {
            var2 = 0;
         }

         return var2;
      }
   }

   static int D(int[] var0) {
      int var4 = 0;
      if(var0 != null) {
         int var3 = da[var0[0]][4];
         int var2 = var0[1];
         int var1 = var0[2];
         switch(rA[2][var1][var2]) {
         case 1:
            if(var3 == 4) {
               var4 = 1;
            }
            break;
         case 2:
            if(var3 == 1 || var3 == 5 || var3 == 6 || var3 == 15) {
               var4 = 1;
            }
            break;
         case 3:
            if(var3 != 1 && var3 != 3 && var3 != 6 && var3 != 15) {
               if(var3 == 4) {
                  var4 = -1;
               }
            } else {
               var4 = 1;
            }
            break;
         case 4:
            if(var3 != 1 && var3 != 5 && var3 != 7 && var3 != 15) {
               if(var3 == 4) {
                  var4 = -1;
               }
            } else {
               var4 = 1;
            }
            break;
         case 5:
            if(var3 == 1 || var3 == 2 || var3 == 3 || var3 == 5 || var3 == 6 || var3 == 7 || var3 == 4 || var3 == 15) {
               var4 = -1;
            }
            break;
         case 6:
            if(var3 == 1 || var3 == 4) {
               var4 = -1;
            }
         case 7:
         case 9:
         case 10:
         default:
            break;
         case 8:
            if(var3 == 1 || var3 == 2 || var3 == 3 || var3 == 4) {
               var4 = -1;
            }
            break;
         case 11:
         case 12:
            if(var3 != 1 && var3 != 5 && var3 != 6 && var3 != 7 && var3 != 15) {
               if(var3 == 4) {
                  var4 = -1;
               }
            } else {
               var4 = 1;
            }
         }

         if(var0[25] != -1) {
            ++var4;
         }
      }

      return var4;
   }

   static void A(int[] var0, int[] var1, int var2) {
      int var8 = var0[25];
      int var7 = var1[25];
      int var6 = 0;
      int var5 = da[var0[0]][16];
      int var4 = 0;
      int var3 = da[var1[0]][16];
      if(var7 != -1) {
         var3 += ea[var7][5];
      }

      if(var8 != -1) {
         var5 += ea[var8][5];
      }

      switch(da[var1[0]][6]) {
      case 0:
         var6 = da[var0[0]][13];
         if(var8 != -1) {
            var6 += ea[var8][4];
         }
         break;
      case 1:
         var6 = da[var0[0]][12];
         if(var8 != -1) {
            var6 += ea[var8][3];
         }
         break;
      case 2:
         var6 = da[var0[0]][14];
         var5 = da[var0[0]][17];
         if(var8 != -1) {
            var5 += ea[var8][6];
            var6 += ea[var8][9];
         }
         break;
      case 3:
         var6 = da[var0[0]][15];
      }

      switch(da[var0[0]][6]) {
      case 0:
         var4 = da[var1[0]][13];
         if(var7 != -1) {
            var4 += ea[var7][4];
         }
         break;
      case 1:
         var4 = da[var1[0]][12];
         if(var7 != -1) {
            var4 += ea[var7][3];
         }
         break;
      case 2:
         var4 = da[var1[0]][14];
         var3 = da[var1[0]][17];
         if(var7 != -1) {
            var3 += ea[var7][6];
            var4 += ea[var7][9];
         }
         break;
      case 3:
         var4 = da[var1[0]][15];
      }

      var7 = C(var0);
      var6 = (var7 + 10) * var6 / 10;
      var7 = (var7 * 3 + 10) * var5 / 10;
      var5 = C(var1);
      var4 = (var5 + 10) * var4 / 10;
      var3 = (var5 * 3 + 10) * var3 / 10;
      var5 = var6;
      var6 = var7;
      var7 = var3;
      if(var3 <= 0) {
         var7 = 1;
      }

      if(var6 <= 0) {
         var6 = 1;
      }

      var3 = 0;
      if(da[var0[0]][4] != 5) {
         var3 = E(var4 * var4 * 200 / (var6 * 3)) / 10;
      }

      var4 = E(var5 * var5 * 200 / (var7 * 3)) * 125;
      var4 /= 1000;
      if(var3 != 0) {
         ++var3;
      }

      if(var4 != 0) {
         ++var4;
      }

      if(var3 < 0) {
         var3 = 0;
      }

      if(var4 < 0) {
         var4 = 0;
      }

      if(var0[12] == 0) {
         var4 = 0;
      }

      if(var1[12] == 0) {
         var3 = 0;
      }

      var5 = da[var1[0]][21];
      if(var1[25] != -1) {
         var5 += ea[var1[25]][8];
      }

      if(var2 < da[var1[0]][20] || var2 > var5) {
         var3 = 0;
      }

      var5 = var0[24];
      var2 = var1[24];
      if(da[var0[0]][5] != 5) {
         var5 += z[rA[2][var0[2]][var0[1]]][8] - mA;
         if(var5 < 5 && var0[25] != -1) {
            ++var5;
         } else if(var5 < 0) {
            var5 = 0;
         }
      }

      if(da[var1[0]][5] != 5) {
         var2 += z[rA[2][var1[2]][var1[1]]][8] - mA;
         if(var2 < 5 && var1[25] != -1) {
            ++var2;
         } else if(var2 < 0) {
            var2 = 0;
         }
      }

      switch(var5) {
      case 1:
         var5 = var3 * 100 / 8;
         break;
      case 2:
         var5 = var3 * 100 / 6;
         break;
      case 3:
         var5 = var3 * 100 / 3;
         break;
      case 4:
         var5 = var3 * 100 / 2;
         break;
      case 5:
         var5 = var3 * 200 / 3;
      }

      switch(var2) {
      case 1:
         var2 = var4 * 100 / 8;
         break;
      case 2:
         var2 = var4 * 100 / 6;
         break;
      case 3:
         var2 = var4 * 100 / 3;
         break;
      case 4:
         var2 = var4 * 100 / 2;
         break;
      case 5:
         var2 = var4 * 200 / 3;
      }

      var3 = (var3 * 100 - var5) / 100;
      var2 = (var4 * 100 - var2) / 100;
      int var9 = var1[6] - var0[6];
      var9 /= 3;
      if(var3 != 0) {
         var3 += var9;
      }

      if(var2 != 0) {
         var2 -= var9;
      }

      if(var3 < 0) {
         var3 = 0;
      }

      if(var2 < 0) {
         var2 = 0;
      }

      var1[15] = 256;
      var1[15] &= -241;
      var1[15] |= var3 << 4;
      var1[15] &= -16;
      var1[15] |= var2;
   }

   static void A(int[] var0, int[] var1) {
      int var5 = var1[15] >> 4 & 15;
      int var4 = var1[15] & 15;
      int var2;
      int var3;
      if(var5 != 0) {
         var3 = D(var1);
         var2 = var1[8];
         var5 += var3 + var2;
      }

      if(var4 != 0) {
         var3 = D(var0);
         var2 = var0[8];
         var4 += var3 + var2;
      }

      if(var5 < 0) {
         var5 = 0;
      }

      if(var4 < 0) {
         var4 = 0;
      }

      if(var1[26] > 0 && var1[16] > 0) {
         var5 /= 2;
         var1[26] = 0;
         var1[16] = 0;
      }

      if(var1[16] > 0 || var0[26] > 0) {
         var5 = 0;
         if(var4 == 0) {
            var4 = 1;
         }
      }

      if(var4 > var1[6]) {
         var4 = var1[6];
      }

      if(var5 > var0[6]) {
         var5 = var0[6];
      }

      if(var5 == 0 && var4 == 0) {
         var1[15] = 0;
      } else {
         var1[15] = 256;
         var1[15] &= -241;
         var1[15] |= var5 << 4;
         var1[15] &= -16;
         var1[15] |= var4;
      }

   }

   static void A(int[] var0, int var1) {
      if(var0 != null) {
         var1 = var0[6] - var1;
         if(var1 <= 0) {
            byte var4 = -1;
            var1 = 0;

            int var3;
            for(var3 = 0; var1 < ta.length; ++var1) {
               if(var0 == ta[var1]) {
                  var4 = 0;
                  var3 = var1;
                  break;
               }
            }

            if(var4 == -1) {
               for(var1 = 0; var1 < va.length; ++var1) {
                  if(var0 == va[var1]) {
                     var4 = 1;
                     var3 = var1;
                     break;
                  }
               }
            }

            for(int var2 = 0; var2 < aA.length; ++var2) {
               if(aA[var2][0] == 0) {
                  aA[var2][0] = var0[1];
                  aA[var2][1] = var0[2];
                  aA[var2][3] = var4;
                  var1 = pB[var0[5]];
                  switch(da[var0[0]][5]) {
                  case 0:
                  case 4:
                  case 7:
                     var1 = 0;
                     break;
                  case 1:
                     var1 += 2;
                     break;
                  case 2:
                     var1 += 6;
                     break;
                  case 3:
                     var1 += 4;
                     break;
                  case 5:
                     var1 += 14;
                     break;
                  case 6:
                     switch(da[var0[0]][4]) {
                     case 5:
                        var1 += 8;
                        break;
                     case 6:
                        var1 += 10;
                        break;
                     case 7:
                        var1 += 12;
                        break;
                     default:
                        var1 = 1;
                     }
                  }

                  aA[var2][2] = var1;
                  break;
               }
            }

            if(var0 == bA) {
               bA = null;
            }

            E(var3, var4);
            if(db == var4 && o != 22 && p != 22) {
               M();
            } else if((o == 22 || p == 22) && var4 == 0) {
               if(ta != null && ta.length >= 1) {
                  pa[0] = ta[0][1];
                  qa[0] = ta[0][2];
               } else if(vA != null && vA.length > 0) {
                  pa[0] = vA[0][1];
                  qa[0] = vA[0][2];
               }

               la = pa[0] - ((f - 25) / 45 + 2) / 2;
               ma = qa[0] - (g / 50 + 1) / 2;
               H();
            }
         } else {
            var0[6] = var1;
         }

      }
   }

   static boolean L() {
      int[][] var1 = db == 0?ta:va;
      int[][] var0 = db == 0?vA:wA;
      return var1.length == 0 && var0.length == 0;
   }

   static void M() {
      int[][] var2 = db == 0?ta:va;
      int var1 = za + 1;
      if(var1 >= var2.length) {
         var1 = 0;
      }

      boolean var0 = false;

      for(var1 = var1; var1 < var2.length; ++var1) {
         if(var2[var1][7] == 0) {
            dA = null;
            fA = var2[var1];
            if(var2[var1][18] == 1) {
               int[] var3 = A(var2[var1][1], var2[var1][2], 1, 3);
               if(var3 != null) {
                  var3[18] = 1;
                  var2[var1][18] = 0;
               }
            }

            hB = 2; //запрещено движение
            pa[db] = fA[1];
            qa[db] = fA[2];
            la = pa[db] - ((f - 25) / 45 + 2) / 2;
            ma = qa[db] - (g / 50 + 1) / 2;
            H();
            A(db == 0?vA:wA, db == 0?xA:yA, var2, (int[])null);    //определение видимой территории
            za = var1;
            return;
         }

         if(var1 >= var2.length - 1 && !var0) {
            var1 = 0;
            var0 = true;
         }
      }

   }

   static int B(int var0, boolean var1) {
      int[][] var2 = db == 0?ta:va;
      if(var1) {
         if(uA[db] < ea[var0][2]) {
            return 30;
         } else {
            for(var0 = 0; var0 < var2.length; ++var0) {
               if(var2[var0][13] == -1 && var2[var0][14] == 1) {
                  return 16;
               }
            }

            return 33;
         }
      } else {
         int[][] var8 = db == 0?xA:yA;
         int[][] var7 = db == 0?vA:wA;
         if(da[var0][5] == 5 && var8.length == 0) {
            return 32;
         } else if(da[var0][5] != 5 && var8.length == 0 && var7.length == 0) {
            return 32;
         } else {
            boolean var6 = false;

            int var3;
            int var4;
            int var9;
            for(int var5 = 0; var5 < var8.length; ++var5) {
               var3 = var8[var5][1];
               var9 = var8[var5][2];
               if(var3 > 1 && var3 < oA - 2 && (var9 > 1 || var9 == 1 && (var3 & 1) == 1) && (var9 < pA - 2 || var9 == pA - 2 && (var3 & 1) == 0) && A(var3, var9, 3, 3) == null && rA[2][var9][var3] != 10 && rA[2][var9][var3] != 5) {
                  var6 = true;
                  break;
               }

               for(var4 = 6; var4 < 12; var4 += 2) {
                  var3 = var8[var5][1] + nB[var8[var5][1] & 1][var4];
                  var9 = var8[var5][2] + nB[var8[var5][1] & 1][var4 + 1];
                  if(var3 > 1 && var3 < oA - 2 && (var9 > 1 || var9 == 1 && (var3 & 1) == 1) && (var9 < pA - 2 || var9 == pA - 2 && (var3 & 1) == 0) && A(var3, var9, 3, 3) == null && rA[2][var9][var3] != 10 && rA[2][var9][var3] != 5) {
                     var6 = true;
                     break;
                  }
               }
            }

            for(var4 = 0; var4 < var7.length; ++var4) {
               var3 = var7[var4][1] + -2;

               while(var3 <= var7[var4][1] + 2) {
                  var9 = var7[var4][2] + -1;

                  while(true) {
                     if(var9 <= var7[var4][2] + 1) {
                        if(var3 <= 1 || var3 >= oA - 2 || var9 <= 1 && (var9 != 1 || (var3 & 1) != 1) || var9 >= pA - 2 && (var9 != pA - 2 || (var3 & 1) != 0) || A(var3, var9, 3, 3) != null || rA[2][var9][var3] == 10 || rA[2][var9][var3] == 5) {
                           ++var9;
                           continue;
                        }

                        var6 = true;
                     }

                     ++var3;
                     break;
                  }
               }
            }

            if(!var6) {
               return 32;
            } else if(uA[db] < da[var0][11]) {
               return 30;
            } else {
               var9 = 1;

               for(var0 = 0; var0 < var2.length; ++var0) {
                  var9 += var2[var0][14];
               }

               if(var9 > 10) {
                  return 31;
               } else {
                  return 14;
               }
            }
         }
      }
   }
//------ Построение видимой территории - окрашивание места растановки техники красным (рекрутирование)
   static int C(int var0, boolean var1) {
      int[][] var9 = db == 0?ta:va;
      int var8 = var1?var9.length:0;
      int[][] var7 = new int[var9.length + 1][28];
      int var2;
      if(var1) {
         for(var2 = 0; var2 < var9.length; ++var2) {
            System.arraycopy(var9[var2], 0, var7[var2], 0, 28);
         }

         var7[var8][1] = pa[db];
         var7[var8][2] = qa[db];
         fA = var7[var8];
      } else {
         if(uA[db] < da[var0][11]) {
            return 2;
         }

         int var6 = 0;
         var7[0][0] = var0;

         int var3;
         int var4;
         int[][] var5;
         for(var5 = db == 0?xA:yA; var6 < var5.length; ++var6) {
            var3 = var5[var6][1];
            var2 = var5[var6][2];
            if(var3 > 1 && var3 < oA - 2 && (var2 > 1 || var2 == 1 && (var3 & 1) == 1) && (var2 < pA - 2 || var2 == pA - 2 && (var3 & 1) == 0) && A(var3, var2, 3, 3) == null && rA[2][var2][var3] != 10 && rA[2][var2][var3] != 5) {
               vb[var2][var3] = 3;
               var7[0][1] = var3;
               var7[0][2] = var2;
            }

            for(var4 = 6; var4 < 12; var4 += 2) {
               var3 = var5[var6][1] + nB[var5[var6][1] & 1][var4];
               var2 = var5[var6][2] + nB[var5[var6][1] & 1][var4 + 1];
               if(var3 > 1 && var3 < oA - 2 && (var2 > 1 || var2 == 1 && (var3 & 1) == 1) && (var2 < pA - 2 || var2 == pA - 2 && (var3 & 1) == 0) && A(var3, var2, 3, 3) == null && rA[2][var2][var3] != 10 && rA[2][var2][var3] != 5) {
                  vb[var2][var3] = 3;
                  var7[0][1] = var3;
                  var7[0][2] = var2;
               }
            }
         }

         var5 = db == 0?vA:wA;

         for(var4 = 0; var4 < var5.length; ++var4) {
            for(var3 = var5[var4][1] + -2; var3 <= var5[var4][1] + 2; ++var3) {
               for(var2 = var5[var4][2] + -1; var2 <= var5[var4][2] + 1; ++var2) {
                  if(var3 > 1 && var3 < oA - 2 && (var2 > 1 || var2 == 1 && (var3 & 1) == 1) && (var2 < pA - 2 || var2 == pA - 2 && (var3 & 1) == 0) && A(var3, var2, 3, 3) == null && rA[2][var2][var3] != 10 && rA[2][var2][var3] != 5) {
                     vb[var2][var3] = 3;
                     var7[0][1] = var3;
                     var7[0][2] = var2;
                  }
               }
            }
         }

         if(var7[0][1] == 0) {
            return 3;
         }

         var3 = 1;

         for(var2 = 0; var2 < var9.length; ++var2) {
            var3 += var9[var2][14];
         }

         if(var3 > 10) {
            return 1;
         }

         for(var2 = 0; var2 < var9.length; ++var2) {
            System.arraycopy(var9[var2], 0, var7[var2 + 1], 0, 28);
         }
      }

      var7[var8][0] = var0;
      var7[var8][9] = db == 0?ra:sa;
      var7[var8][5] = 0;
      var7[var8][10] = 25;
      var7[var8][7] = var1?0:1;
      var7[var8][14] = var1?0:1;
      var7[var8][13] = -1;
      var7[var8][25] = -1;
      var7[var8][23] = 1;
      var7[var8][6] = 10;
      if(da[var7[var8][0]][27] > 0) {
         var7[var8][11] = da[da[var7[var8][0]][27]][22];
      } else {
         var7[var8][11] = da[var0][22];
      }

      var7[var8][12] = da[var0][23];
      var7[var8][19] = -1;
      if(db == 0) {
         ta = var7;
      } else {
         va = var7;
      }

      if(!var1) {
         uA[db] -= da[var0][11];
      }

      return -1;
   }

   static int E(int var0, int var1) {
      int[][] var3 = var1 == 0?ta:va;
      if(var3[var0][13] != -1) {
         E(var3[var0]);
      }

      if(var3.length < 1) {
         if(var1 == 0) {
            ta = new int[0][28];
         } else {
            va = new int[0][28];
         }

         return 1;
      } else {
         int[][] var2 = new int[var3.length - 1][28];
         var3[var0][0] = -1;
         if(dA == var3[var0]) {
            dA = null;
         }

         if(fA == var3[var0]) {
            fA = null;
         }

         int[] var5 = A(var3[var0][1], var3[var0][2], 2, 3);
         if(var5 != null) {
            var5[18] = 0;
         }

         int var4 = 0;

         for(var0 = 0; var0 < var3.length; ++var0) {
            if(var3[var0][0] != -1) {
               System.arraycopy(var3[var0], 0, var2[var4], 0, 28);
               ++var4;
            }
         }

         if(o == 22) {
            for(var0 = iA; var0 < jA.length; ++var0) {
               --jA[var0][0];
            }

            --iA;
         }

         if(var1 == 0) {
            ta = var2;
         } else {
            va = var2;
         }

         if(var1 != 0) {
            B(82, 0, 0, true);
         }

         return -1;
      }
   }

   static int E(int[] var0) {
      int var6 = var0[13];
      if(var6 >= 0) {
         int[][] var3 = db == 0?ta:va;
         int var2 = 0;
         int var5 = var0[1];

         int var1;
         int var4;
         for(var4 = var0[2]; var2 < var3.length; ++var2) {
            if(B(var3[var2][1], var3[var2][2], var5, var4) <= ea[var6][10]) {
               var1 = var3[var2][8] - 1;
               if(var1 < -2) {
                  var1 = -2;
               }

               var3[var2][8] = var1;
               var3[var2][25] = -1;
            }
         }

         var3 = db == 0?va:ta;

         for(var2 = 0; var2 < var3.length; ++var2) {
            if(B(var3[var2][1], var3[var2][2], var5, var4) <= ea[var6][10]) {
               var1 = var3[var2][8] + 1;
               if(var1 > 2) {
                  var1 = 2;
               }

               var3[var2][8] = var1;
            }
         }
      }

      var0[13] = -1;
      if(db == 0) {
         B(98, var6, 0, true);
      }

      return -1;
   }

   static void F(int[] var0) {
      int var2 = var0[13];
      int var6 = var0[1];
      int var5 = var0[2];
      int[][] var4 = db == 0?ta:va;
      int[][] var1 = db == 0?va:ta;
      int var3;
      int var8;
      if(var2 >= 0) {
         int var7;
         for(var3 = 0; var3 < var1.length; ++var3) {
            if(B(var1[var3][1], var1[var3][2], var6, var5) <= ea[var2][10]) {
               var7 = var1[var3][8] - 1;
               if(var7 < -2) {
                  var7 = -2;
               }

               var1[var3][8] = var7;
            }
         }

         for(var8 = 0; var8 < var4.length; ++var8) {
            if(var4[var8][25] == var2) {
               if(B(var4[var8][1], var4[var8][2], var6, var5) > ea[var2][10]) {
                  var7 = var4[var8][8] - 1;
                  if(var7 < -2) {
                     var7 = -2;
                  }

                  var4[var8][8] = var7;
                  var4[var8][25] = -1;
               }
            } else if(B(var4[var8][1], var4[var8][2], var6, var5) <= ea[var2][10]) {
               var7 = var4[var8][8] + 1;
               if(var7 > 2) {
                  var7 = 2;
               }

               var4[var8][8] = var7;
               if(var4[var8][25] > 0 && var4[var8][25] != var2) {
                  if((ea[var4[var8][25]][2] == 4000?1:(ea[var4[var8][25]][2] == 6000?2:3)) < (ea[var2][2] == 4000?1:(ea[var2][2] == 6000?2:3))) {
                     var4[var8][25] = var2;
                  }
               } else {
                  var4[var8][25] = var2;
               }
            }
         }
      } else {
         var3 = var0[25];
         if(var3 >= 0) {
            for(var2 = 0; var2 < var4.length; ++var2) {
               if(var4[var2][13] == var3 && B(var4[var2][1], var4[var2][2], var6, var5) > ea[var3][10]) {
                  var8 = var0[8] - 1;
                  if(var8 < -2) {
                     var8 = -2;
                  }

                  var0[8] = var8;
                  var0[25] = -1;
               }
            }
         }
      }

   }

   static void N() {
      int[][] var6 = db == 0?va:ta;
      int[][] var5 = db == 0?ta:va;

      for(int var4 = 0; var4 < var6.length; ++var4) {
         int var3 = 0;
         int var2 = var6[var4][1];
         int var1 = var6[var4][2];

         int var0;
         for(var0 = 0; var0 < var5.length; ++var0) {
            if((da[var6[var4][0]][5] == 5 && da[var5[var0][0]][5] == 5 || da[var6[var4][0]][5] != 5) && B(var5[var0][1], var5[var0][2], var2, var1) <= 1) {
               ++var3;
            }
         }

         if(var3 > 3) {
            var0 = var6[var4][8] - 1;
            if(var0 < -2) {
               var0 = -2;
            }

            var6[var4][8] = var0;
         }
      }

   }

   static int G(int[] var0) {
      int var1 = da[var0[0]][11];
      var1 /= 20;
      var1 *= var0[6];
      int var2 = C(var0);
      var2 = var1 + 50 * var2 * var2;
      return var2;
   }

   static final void O() {
      xb = new int[pA * oA + 2];    //набор координат X, Y
      yb = new int[pA][oA];         //карта маршрутов
      zb = new int[pA * oA + 2];    //присвоение новой координаты X юнита (хранится с учетом расстояния)
      aB = new int[pA * oA + 2];    //присвоение новой координаты Y юнита (хранится с учетом расстояния)
      bB = new int[pA + 1][oA + 1]; //запись координаты X (ссылка на предыдущую точку - начальная)
      cB = new int[pA + 1][oA + 1]; //запись координаты Y (ссылка на предыдущую точку - начальная)
      dB = new int[pA * oA + 2];    //расстояние до конечной точки
      eB = new int[pA][oA];         //расстояние от начальной точки
      fB = new int[pA * oA + 2];    //расчет расстояния на пути к конечнной точке маршрута
      gB = new int[pA * oA * 2];    //коорд. юнита по оси X, Y (промежуточная на маршруте)
   }
//------ Алгоритм определения пути
   static final void A(int[] var_Unit, int var_X, int var_Y) {  //построение пути перемещения: юнит, координата X, координата Y
      int var_X1 = var_Unit[1];   //координата X начальная юнита
      int var_Y1 = var_Unit[2];   //координата Y начальная юнита
      hB = 0;
      int var11 = 0;    //счетчик
      iB = 0;
      jB = 0;
      gB = new int[pA * oA * 2];    //задание массива координат
      //vb3 [var_Y][var_X] = 0;       //точка соприкосновения с врагом (разрешено туда ходить)
      int var3;
      if(o == 22) { //режим программы
         var3 = 2;
         if(vb[var_Y][var_X] == 2) {    //если на пути юнита прозрачный гекс (разрешено ходить)
            var3 = 3;
         }

         fA = A(var_X, var_Y, 1, var3);   //получение выбранного юнита (противника) в месте указания курсора
      }
      if(da[var_Unit[0]][18] == 0) {   //если движение перемещаемого юнита равно 0 (сходил), то непроизводится расчет пути
         if(fA != null && fA[15] > 0) { //есть противник и юнит может ходить (не ходил еще)
            hB = 3; //не производить расчет пути
         } else {
            hB = 2; //запрещено движение
         }
         return;
      } 
      if(var_X1 == var_X && var_Y1 == var_Y) {   //расчет пути не производится, если координаты перемещения совпадают с начальными
         hB = 1;    //разрешено движение
         return;
      } 
      if(dA != null && fA != null && ((da[dA[0]][5] == 5 && da[fA[0]][5] == 5) || A(var_X, var_Y, 2, 3) != null)) {    //если на пути самолета другой самолет или вражеский самолет или проверка выявила что на одной клетке будут находится два одинаковых класса юнита(сухопутный - сухопутный), то запрещено движение (анализ на противника)
         if(fA[15] > 0) {   //юнит может ходить (не ходил еще)
            hB = 3;
         } else {
            hB = 2; //запрещено движение
         }
        return;
      }
      int[] var15;
      if(dA != null && vb[var_Y][var_X] == 2) {    //на пути юнит и прозрачный гекс
            var15 = A(var_X, var_Y, 1, 3);   //получение выбранного юнита на месте перемещения (если есть)
            if(var15 != null && ((da[dA[0]][5] == 5 && da[var15[0]][5] == 5) || (da[dA[0]][5] != 5 && da[var15[0]][5] != 5))) {   //если на пути самолета другой самолет или наземный юнит и наземный юнит, то запрещено движение
               if(var15[15] > 0) {   //юнит может ходить (не ходил еще)
                  hB = 3; //не производить расчет пути
               } else {
                  hB = 2; //запрещено движение
               }

               return;
            }
      }

      yb = new int[pA][oA];  //задание массива в зависимости от размера карты (Y, X)
      eB[var_Y1][var_X1] = 0;//начальное расстояние равно 0 (в точке нахождения юнита)
      int var10 = 1;  //счетчик расстояния пути???
      xb[1] = 1;      //первый набор координат
      zb[1] = var_X1;   //начальная координата X юнита
      aB[1] = var_Y1;   //начальная координата Y юнита
         
      while(true) {          //начало цикла расчета пути (задание начальных координат)
            while (var10 != 0) {    //если будет счетчик равен 0, то запрещено движение
            int var_X2 = zb[xb[1]];   //присвоение новой координаты X юнита (хранится с учетом расстояния)
            int var_Y2 = aB[xb[1]];   //присвоение новой координаты Y юнита (хранится с учетом расстояния)
            yb[var_Y2][var_X2] = 2;     //установка точки маршрута (на карте маршрута)
            --var10;                //уменьшение счетчика пройденного юнитом пути???
            xb[1] = xb[var10 + 1];  //следующий набор координат
            int var5 = 1;

            while(true) {  //продолжение цикла расчета пути (расчет следующих точек)
               int var4 = var5; //обратить внимание на эти переменные!!!
               if(2 * var4 + 1 <= var10) {
                  if(dB[xb[var4]] >= dB[xb[2 * var4]]) {    //сравнение расстояния точек (пришли ли в конечную точку маршрута)
                     var5 = 2 * var4;   //задание места под новые координаты (следующей точки)
                  }

                  if(dB[xb[var5]] >= dB[xb[2 * var4 + 1]]) {    //сравнение расстояния точек (пришли ли в конечную точку маршрута)
                     var5 = 2 * var4 + 1;   //задание места под новые координаты (следующей точки)
                  }
               } 
               else if(2 * var4 <= var10 && dB[xb[var4]] >= dB[xb[2 * var4]]) {
                  var5 = 2 * var4;   //задание места под новые координаты (следующей точки)
               }
               if(var4 == var5) {
                       break;
               }
               int var16 = xb[var4]; //Расстояние пути
               xb[var4] = xb[var5];
               xb[var5] = var16;               
               
            }
                  for(int var7 = 0; var7 < 12; var7 += 2) { //цикл проверки ближайшей точки (проход точек по кругу - по часовой)
                     int var_X3 = var_X2 + nB[var_X2 & 0x1][var7];   //получение новой координаты X (гексоганальная система по кругу 6 направлен.)
                     int var_Y3 = var_Y2 + nB[var_X2 & 0x1][var7 + 1];   //получение новой координаты Y (гексоганальная система по кругу 6 направлен.)
                     if(var_X3 > 1 && var_X3 < oA - 2 && (var_Y3 > 1 || (var_Y3 == 1 && (var_X3 & 0x1) == 0x1)) && (var_Y3 < pA - 2 || (var_Y3 == pA - 2 && (var_X3 & 0x1) == 0x0)) && yb[var_Y3][var_X3] != 2 && z[rA[2][var_Y3][var_X3]][da[var_Unit[0]][5]] != 16) { //координата X не меньше 1 и не больше размера карты (также Y), если точка на маршруте не ставилась и у юнита нет 16
                        int[] var17 = null;
                        if(vb[var_Y3][var_X3] == 2) {   //новая точка равна 2, то место видимо на карте
                           var17 = A(var_X3, var_Y3, 3, (db == 0)?2:1);   //получение выбранного юнита противника (если 0) или своего
                           if(var17 != null && ((da[dA[0]][5] == 5 && da[var17[0]][5] != 5) || (da[dA[0]][5] != 5 && da[var17[0]][5] == 5))) {    //если на пути самолета  наземный юнит или наземный юнит и на пути самолет, то разрешено движение
                              var17 = null; //юнита противника или своего нет на пути
                           }
                        }

                        if(var17 == null) { //никого нет на пути
                          if(da[dA[0]][5] == 5 && vb4[var_Y3][var_X3] == 0 && vb[var_Y3][var_X3] != 2
                                  || da[dA[0]][5] == 5 && vb4[var_Y3][var_X3] == 4 && vb[var_Y3][var_X3] == 1
                                  || da[dA[0]][5] == 5 && vb4[var_Y3][var_X3] == 5 && vb[var_Y3][var_X3] == 1
                                  || da[dA[0]][5] != 5 && vb3[var_Y3][var_X3] == 0 && vb[var_Y3][var_X3] != 2
                                  || da[dA[0]][5] != 5 && vb3[var_Y3][var_X3] == 4 && vb[var_Y3][var_X3] == 1
                                  || da[dA[0]][5] != 5 && vb3[var_Y3][var_X3] == 5 && vb[var_Y3][var_X3] == 1) {
                          //если нет рядом противника (суша) и доступность движения (с учетом местности) или тоже, но с самолетом
                              
                           if(fA == null || (da[dA[0]][5] == 5 && da[fA[0]][5] != 5) || (da[dA[0]][5] != 5 && da[fA[0]][5] == 5)) {    //если на пути наземного юнита самолет или самолет и на его пути наземный юнит, то разрешено движение (анализ на наличие противника)

                           if(yb[var_Y3][var_X3] != 1) { //все разрешено (если только точка не расчитывалась) - можно расчитывать путь
                              ++var11;  //счетчик расчитанных точек (координат)
                              int var18 = var10 + 1;
                              xb[var18] = var11;
                              zb[var11] = var_X3;   //новая координата X
                              aB[var11] = var_Y3;   //новая координата Y
                              int var19;
                              if(Math.abs(var_X3 - var_X2) == 1 && Math.abs(var_Y3 - var_Y2) == 1) {
                                 var19 = 10; //10 первая ближайщая точка маршрута на пути к конечнной точке маршрута (расчет от начальной точки)
                              } else {
                                 var19 = 14; //14 последующие точки
                              }
                              
                              if (vb3[var_Y3][var_X3] == 5 && da[var_Unit[0]][5] != 5 || vb4[var_Y3][var_X3] == 5 && da[var_Unit[0]][5] == 5) {
                              eB[var_Y3][var_X3] = eB[var_Y2][var_X2] + (var19 + 16 * 100);  //не надо рисовать стрелку маршрута рядом с противником
                              } else {
                              eB[var_Y3][var_X3] = eB[var_Y2][var_X2] + (var19 + z[rA[2][var_Y3][var_X3]][da[var_Unit[0]][5]] * 100);  
                              //предыдущее расстояние складывается с новым
                              }
                              
                              fB[xb[var18]] = 10 * (Math.abs(var_X3 - var_X) + Math.abs(var_Y3 - var_Y)); //расчет расстояния на пути к конечнной точке маршрута
                              dB[xb[var18]] = eB[var_Y3][var_X3] + fB[xb[var18]]; //расстояние до конечной точки
                              bB[var_Y3][var_X3] = var_X2;       //запись координаты X (ссылка на предыдущую точку - начальная)
                              cB[var_Y3][var_X3] = var_Y2;       //запись координаты Y (ссылка на предыдущую точку - начальная)
                            //Перевести координаты в номер клетки с поправкой на четный/нечетный столбец
                              while( var18 != 1 && dB[xb[var18]] <= dB[xb[var18 / 2]] ) {
                                 int var20 = xb[var18 / 2];
                                 xb[var18 / 2] = xb[var18];
                                 xb[var18] = var20;  //запись расстояния
                                 var18 /= 2;
                              }

                              ++var10;
                              yb[var_Y3][var_X3] = 1;   //обследовано, присваивается 1 (на карте маршрута)
                           } 
                           else {
                              int var20;
                              int var21;
                           
                              if(Math.abs(var_X3 - var_X2) == 1 && Math.abs(var_Y3 - var_Y2) == 1) {
                                 var20 = 10; //10 первая ближайщая точка маршрута на пути к конечнной точке маршрута (расчет от начальной точки)
                              } else {
                                 var20 = 14; //14 последующие точки
                              }
                              
                              if (vb3[var_Y3][var_X3] == 5 && da[var_Unit[0]][5] != 5 || vb4[var_Y3][var_X3] == 5 && da[var_Unit[0]][5] == 5) {
                              var21 = eB[var_Y2][var_X2] + (var20 + 16 * 100);  //не надо рисовать стрелку маршрута рядом с противником
                              } else {
                              var21 = eB[var_Y2][var_X2] + (var20 + z[rA[2][var_Y3][var_X3]][da[var_Unit[0]][5]] * 100);    //территория + самолет
                              }
                              
                              
                              if(var21 < eB[var_Y3][var_X3]) {  //выбор самого короткого маршрута из 2
                                 bB[var_Y3][var_X3] = var_X2;     //коорд. юнита по оси X (промежуточная на маршруте) записать - временная
                                 cB[var_Y3][var_X3] = var_Y2;     //коорд. юнита по оси Y (промежуточная на маршруте) записать - временная
                                 eB[var_Y3][var_X3] = var21;    //расстояние (промежуточная на маршруте) записать

                                 for(int var22 = 1; var22 <= var10; ++var22) {
                                    if(zb[xb[var22]] == var_X3 && aB[xb[var22]] == var_Y3) {
                                       dB[xb[var22]] = eB[var_Y3][var_X3] + fB[xb[var22]];
                                       //var4 = var16;

                                       for (int var23 = var22; var23 != 1 && dB[xb[var23]] < dB[xb[var23 / 2]]; var23 /= 2) {

                                          int var24 = xb[var23 / 2];
                                          xb[var23 / 2] = xb[var23];
                                          xb[var23] = var24;
                                          
                                       }
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                           else if(var17 == null) {
                              if(fA != null && fA[15] > 0) {
                              hB = 3; //не производить расчет пути
                              } 
                              else {
                              hB = 1; //запрещено движение
                              }
                              gB = new int[pA * oA * 2];
                              return;
                            }
                          }
                        }
                     }
                  }

      if(yb[var_Y][var_X] == 1) { //если не пришли в конечную точку, то продолжить построение маршрута
      hB = 1; //разрешено движение

         if(hB == 1) {
            int var25 = var_X;
            int var26 = var_Y;

            do {
               //do {
                  int var27 = bB[var26][var25];   //коорд. юнита по оси X (промежуточная на маршруте)
                  var26 = cB[var26][var25];    //коорд. юнита по оси Y (промежуточная на маршруте)
                  var25 = var27;
                  ++iB;                 //определение расстояния перемещения юнита
               
            } while(var25 != var_X1 || var26 != var_Y1);     //пока не совпадет с начал. коорд. юнита по оси X и  Y

            int var28 = var_Y;
            int var29 = iB * 2;

            do {
                  var29 -= 2;
                  gB[var29] = var_X;        //коорд. юнита по оси X (промежуточная на маршруте)
                  gB[var29 + 1] = var28;     //коорд. юнита по оси Y (промежуточная на маршруте)
                  jB += z[rA[2][var28][var_X]][da[var_Unit[0]][5]];    //счетчик очков движения
                  var_Y = bB[var28][var_X];
                  var28 = cB[var28][var_X];
                  var_X = var_Y;
            } while(var_Y != var_X1 || var28 != var_Y1);

            if(da[var_Unit[0]][4] != 6 && da[var_Unit[0]][22] > 0 && var_Unit[11] == 0) {
               gB = new int[pA * oA * 2];
               hB = 2; //запрещено движение
            }

            var_X = da[var_Unit[0]][18];   //очки перемещения у юнита в библиотеке
            var_Y = B(var_X, var_Unit); //погода влияет на расстояние перемещения???
            if(jB > var_Y) {   //если расстояние перемещения превысило дозволенное, то запрещено движение
               hB = 2; //запрещено движение
               if(o == 22) {
                  var_X = jB;
                  int var30 = 0;

                  do {
                     var30 += 2;
                  } while(var30 < gB.length - 2 && gB[var30 + 2] != 0);

                  if(var30 < gB.length - 2 && var30 > 0 && var_Y > 1) {
                     int var31 = da[var_Unit[0]][5];
                     for(int var32 = var30; var32 >= 0; var32 -= 2) {
                        int var33 = gB[var32];
                        int var34 = gB[var32 + 1];
                        var_X -= z[rA[2][var34][var33]][var31];
                        if(var_X <= var_Y && A(var33, var34, 3, 3) == null) {
                           hB = 1; //разрешено движение
                           break;
                        }

                        hB = 2; //запрещено движение
                        gB[var32] = 0;
                        gB[var32 + 1] = 0;
                     }
                  }
               } 
               else {
                  gB = new int[pA * oA * 2];
               }
            }
         }
         return;
      }
    }
         hB = 2;          //запрещено движение
         break;
         
      }
   }

   static int C(int var0, int var1, int var2, int var3) {
      int[] var4 = nB[var0 & 1];
      var0 = var2 - var0;
      var2 = var3 - var1;

      for(var1 = 0; var1 < var4.length; var1 += 2) {
         if(var4[var1] == var0 && var4[var1 + 1] == var2) {
            return var1 / 2;
         }
      }

      return 0;
   }
//------ Ставит видимую территорию
   static void A(int var0, int var1, int var2, int var3, int var4) {    //коорд. юнита X, Y, зрение юнита или ход, цвет видимости (0 - черный, 1- серый, 2 - невидим., 3 - красный), тип юнита
      if(var2 >= 0) {
         if (var3 != 4 && var3 != 5) {
         vb[var1][var0] = var3;
         }
         if (var3 == 2 && alpha_pos == true) {
            vb2[var1][var0] = 4;    
         }
         if (var3 == 2 && alpha_pos == false) {
            vb2[var1][var0] = 0;    
         }
         if (var3 == 4 && var4 != 5) {
            vb3[var1][var0] = 4;    //территория вокруг наземного вражеского юнита (радиус 1 клетка)
            }
         if (var3 == 5 && var4 != 5) {
            vb3[var1][var0] = 5;    //территория вокруг наземного вражеского юнита (радиус 1 клетка)
            }
         if (var3 == 4 && var4 == 5) {   
            vb4[var1][var0] = 4;    //территория вокруг воздушного вражеского юнита (радиус 1 клетка)
         }
         if (var3 == 5 && var4 == 5) {   
            vb4[var1][var0] = 5;    //территория вокруг воздушного вражеского юнита (радиус 1 клетка)
            
         }
         
//         if (vb3[var1][var0] == 5 && vb[var1][var0] == 1) { //первое соприкосновение с врагом (разрешено ходить) 
//            vb3[var1][var0] = 0;
//         }
         for(int var8 = 0; var8 < 12; var8 += 2) {  //радиус расчета (по кругу вокруг гекса)
             
            int var7 = var0 + nB[var0 & 1][var8];   //координата X
            int var6 = var1 + nB[var0 & 1][var8 + 1];   //координата Y
            if(var7 > 0 && var7 < oA - 1 && var6 > 0 && var6 < pA - 1 && var2 > yb[var6][var7]) {   //проверка выхода за пределы карты или видимости,хода  юнита
                int var5;    
                if (vb3[var1][var0] == 5 && var4 != 5 || vb4[var1][var0] == 5 && var4 == 5) {        //территория вокруг вражеского юнита видимого
                    var5 = var3 == 1?var2 - 16:var2 - 1;        //если серый произвести расчет (коорд. X, Y, очки хода), иначе уменьшение цвета видимости
                } else if (vb3[var1][var0] == 4 && var4 != 5 || vb4[var1][var0] == 4 && var4 == 5) { //территория вокруг вражеского юнита скрытого
                    var5 = var3 == 1?var2 - z[rA[2][var6][var7]][var4]:var2 - 1;        //если серый произвести расчет (коорд. X, Y, очки хода), иначе уменьшение цвета видимости
                } else {
                    var5 = var3 == 1?var2 - z[rA[2][var6][var7]][var4]:var2 - 1; //если серый произвести расчет (коорд. X, Y, очки хода), иначе уменьшение цвета видимости
                }

                yb[var6][var7] = var5;   //карта в области видимости юнита
                A(var7, var6, var5, var3, var4);
            }
         }

      }
   }
//------ Ставит видимую территорию вокруг занятых городов
   static void F(int var0, int var1) {
      vb[var1][var0] = 2;
      vb2[var1][var0] = 5;
      yb[var1][var0] = 1;

      for(int var4 = 0; var4 < 12; var4 += 2) {
         int var3 = var0 + nB[var0 & 1][var4];
         int var2 = var1 + nB[var0 & 1][var4 + 1];
         vb[var2][var3] = 2;
         vb2[var2][var3] = 5;
         if(var3 > 0 && var3 < oA - 1 && var2 > 0 && var2 < pA - 1 && rA[2][var2][var3] == 12 && yb[var2][var3] != 1) {
            F(var3, var2);
            yb[var2][var3] = 1;
         }
      }

   }
   
   static int B(int[] var0, int[] var1) {
      if(H(var0, var1) < 0) {
         return -1;
      } else {
         int var6 = 0;
         int var5 = 0;

         for(int var4 = 0; var4 < 2; ++var4) {
            int[][] var3 = var4 == 0?ta:va;

            for(int var2 = 0; var2 < var3.length; ++var2) {
               if(var0 == var3[var2]) {
                  var6 = var4;
               }

               if(var1 == var3[var2]) {
                  var5 = var4;
               }
            }
         }

         if(var6 == var5) {
            return -1;
         } else {
            return 1;
         }
      }
   }

   static int C(int[] var0, int[] var1) {
      if(B(var0, var1) < 0) {
         return -1;
      } else {
         var0[21] = 1;
         int var2;
         if(var1[6] - (var1[15] & 15) <= 0) {
            var2 = var0[8] + 1;
            if(var2 > 2) {
               var2 = 2;
            }

            var0[8] = var2;
         }

         bA = var0;
         cA = var1;
         if(o == 22 && cA != null) {
            A(db == 0?wA:vA, db == 0?yA:xA, db == 0?va:ta, (int[])null);    //определение видимой территории
            qa[db] = cA[2];
            pa[db] = cA[1];
            la = pa[db] - ((f - 25) / 45 + 2) / 2;
            ma = qa[db] - (g / 50 + 1) / 2;
            H();
         }

         A(0, var0, var1, 5);
         A(1, var1, var0, 10);
         if(HG.D(4)) {
            int var4 = 0;
            int var3 = da[var0[0]][6] == 1?5:7;
            boolean var10000;
            if(var3 == 5) {
               var10000 = true;
            } else {
               var10000 = true;
            }

            if((var1[15] & 15) > 0) {
               while(var4 < var0[6]) {
                  A(var3, var0, var1, 18);
                  ++var4;
               }
            }

            var3 = da[var1[0]][6] == 1?6:8;
            if(var3 == 6) {
               var10000 = true;
            } else {
               var10000 = true;
            }

            if((var1[15] >> 4 & 15) > 0) {
               for(var2 = 0; var2 < var1[6]; ++var2) {
                  A(var3, var1, var0, 26);
               }
            }

            if((var1[15] & 15) > 0) {
               for(var2 = 0; var2 < (var1[15] & 15) && var2 < var1[6]; ++var2) {
                  A(4, var1, var0, 34);
               }
            }

            if((var1[15] >> 4 & 15) > 0) {
               for(var2 = 0; var2 < (var1[15] >> 4 & 15) && var2 < var0[6]; ++var2) {
                  A(3, var0, var1, 41);
               }
            }
         }

         bA[7] = 1;
         bA[18] = 1;
         if(A(bA[1], bA[2], 1, 3) == null) {
            bA[18] = 0;
         }

         p = o;
         if(p == 22) {
            A(vA, xA, ta, (int[])null);    //определение видимой территории
         }

         o = 20;
         return 1;
      }
   }

   static int H(int[] var0) {
      if(gA && var0 != null) {
         if(var0[18] == 1) {
            var0[18] = 0;
         }

         int var5 = gB[var0[17]];
         int var4 = gB[var0[17] + 1];
         if(var0[3] == 0 && var0[4] == 0 && (var5 == 0 || var4 == 0)) {
            gB = new int[pA * oA * 2];
            dA = var0;
            var0[24] = 0;
            N();
            if(var0[13] >= 0 || var0[25] >= 0) {
               F(var0);
            }

            if(dA[16] > 0) {
               A(db ^ 1, (int[])null);
               return -1;
            }

            if(A(db ^ 1, dA)) {
               if(o == 22) {
                  return -1;
               }

               M(dA);
               hB = 3;
            } else if(P()) {
               fA = A(pa[db], qa[db], 1, 3);
               return 1;
            }

            fA = A(pa[db], qa[db], 1, 3);
            return -1;
         }

         int[] var3 = null;
         if(var0[3] == 0 && var0[4] == 0) {
            int var1 = db == 0?2:1;
            var3 = A(var5, var4, 3, var1);
            if(var3 != null && (da[var0[0]][5] == 5 && da[var3[0]][5] != 5 || da[var0[0]][5] != 5 && da[var3[0]][5] == 5)) {
               var3 = null;
            }

            if(var3 != null && var3[23] == 0 && var0[17] > 1) {
               if((da[var0[0]][6] != 2 || da[var3[0]][14] != 0) && (da[var0[0]][6] != 3 || da[var3[0]][15] != 0) && (da[var0[0]][6] != 1 || da[var3[0]][12] != 0) && (da[var0[0]][6] != 0 || da[var3[0]][13] != 0) && var3[12] > 0 && H(var3, var0) > 0) {
                  var0[16] = 1;
               }

               int var2 = gB[var0[17] - 2];
               var1 = gB[var0[17] - 1];
               if(da[var3[0]][5] == 5 && da[var0[0]][5] != 5 || da[var3[0]][5] != 5 && da[var0[0]][5] == 5) {
                  var2 = var5;
                  var1 = var4;
               }

               if(var2 != 0) {
                  var0[1] = var2;
                  var0[2] = var1;
               }

               dA = var0;
               fA = var3;
               var3[23] = 1;
               var0[17] = gB.length - 3;
            } else {
               var3 = null;
               if(da[var0[0]][22] > 0 && var0[11] > 0) {
                  --var0[11];
               }

               var0[5] = C(var0[1], var0[2], var5, var4);
            }
         }

         if(var3 == null) {
            if(HG.D(5)) {
               var0[3] = 0;
               var0[4] = 0;
               if(vb[var0[2]][var0[1]] > 0) {
                  A(2, var0, (int[])null, 10);
               }
            } else {
               if(var0[3] == 0 && var0[4] == 0 && vb[var0[2]][var0[1]] > 0) {
                  A(2, var0, (int[])null, 10);
               }

               switch(var0[5]) {
               case 0:
                  var0[3] -= 8;
                  var0[4] -= 4;
                  break;
               case 1:
                  var0[4] -= 8;
                  break;
               case 2:
                  var0[3] += 8;
                  var0[4] -= 4;
                  break;
               case 3:
                  var0[3] += 8;
                  var0[4] += 4;
                  break;
               case 4:
                  var0[4] += 8;
                  break;
               case 5:
                  var0[3] -= 8;
                  var0[4] += 4;
               }

               if(var0[3] > 24 || var0[3] < -24) {
                  var0[3] = 0;
                  var0[4] = 0;
               }

               if(var0[4] > 24 || var0[4] < -24) {
                  var0[3] = 0;
                  var0[4] = 0;
               }
            }

            if(var0[3] == 0 && var0[4] == 0) {
               var0[17] += 2;
               if(var5 != 0 && var4 != 0) {
                  if(var0[1] != var5 && var0[2] != var4) {
                     var0[5] = C(var0[1], var0[2], var5, var4);
                  }

                  var0[1] = var5;
                  var0[2] = var4;
                  if(vb[var0[2]][var0[1]] > 0) {
                     qa[db] = var4;
                     pa[db] = var5;
//                     if(pa[db] > la + ((f - 25) / 45 + 2 - 1)) {
//                        ++la;
//                     } else if(pa[db] < la + 2) {
//                        --la;
//                     }
//
//                     if(qa[db] > ma + (g / 50 + 1 - 3)) {
//                        ++ma;
//                     } else if(qa[db] < ma + 2) {
//                        --ma;
//                     }

                     H();
                  }
               }
            }
         }
      }

      return 0;
   }

   static int I(int[] var0) {
      int[][] var10 = db == 0?xA:yA;
      int[] var9 = new int[2];
      int var8 = Integer.MAX_VALUE;

      for(int var7 = 0; var7 < var10.length; ++var7) {
         int var6 = var10[var7][1];
         int var5 = var10[var7][2];
         int var4 = B(var0[1], var0[2], var6, var5);
         if(var4 < var8) {
            if(A(var6, var5, 3, 3) == null) {
               var8 = var4;
               var9[0] = var6;
               var9[1] = var5;
            } else {
               for(int var3 = 0; var3 < 12; var3 += 2) {
                  if(var3 >= 6 && var3 <= 10) {
                     int var2 = var6 + nB[var6 & 1][var3];
                     int var1 = var5 + nB[var6 & 1][var3 + 1];
                     if(A(var2, var1, 3, 3) == null) {
                        var8 = var4;
                        var9[0] = var2;
                        var9[1] = var1;
                        break;
                     }
                  }
               }
            }
         }
      }

      if(var9[0] != 0) {
         A(var0, var9[0], var9[1]);
         if(hB == 1) {
            gA = true;  //юнит перемещается
            var0[17] = 0;
            return 1;
         }
      }

      return 0;
   }

   static int D(int[] var0, int[] var1) {
      int var8 = Integer.MIN_VALUE;
      int var7 = -1;
      int var6 = -1;
      if(da[var0[0]][5] == 5 && da[var1[0]][5] != 5 && da[var1[0]][4] != 7 && A(var1[1], var1[2], 1, db == 0?1:2) == null) {
         var7 = var1[1];
         var6 = var1[2];
      } else {
         yb = new int[pA][oA];
         vb = new int[pA][oA];
         int var2 = da[var0[0]][18];
         var2 = B(var2, var0);
         A(var0[1], var0[2], var2, 1, da[var0[0]][5]);
         int var5 = var0[1];
         int var4 = var0[2];
         int var3 = var1[1];
         var2 = var1[2];
         if(var3 < var5) {
            var3 = var5;
            var5 = var1[1];
         }

         if(var2 < var4) {
            var2 = var4;
            var4 = var1[2];
         }

         for(int var9 = var5; var9 <= var5 + var3; ++var9) {
            for(int var10 = var4; var10 <= var4 + var2; ++var10) {
               if(var9 > 1 && var9 < oA - 2 && (var10 > 1 || var10 == 1 && (var9 & 1) == 1) && (var10 < pA - 2 || var10 == pA - 2 && (var9 & 1) == 0) && kA[var10][var9] > var8 && kA[var10][var9] <= 0 && var9 != var0[1] && var10 != var0[2] && vb[var10][var9] == 1 && A(var9, var10, 1, 3) == null) {
                  var8 = kA[var10][var9];
                  var7 = var9;
                  var6 = var10;
                  if(A(0, 100) < var0[22]) {
                     var10 = pA;
                     var9 = oA;
                  }
               }
            }
         }
      }

      if(var7 > 0) {
         var0[21] = 3;
         fA = A(var7, var6, 1, 3);
         A(var0, var7, var6);
         if(hB == 1 && (da[var0[0]][22] == 0 || var0[11] > 0)) {
            gA = true;
            var0[17] = 0;
            return 1;
         }
      }

      return -1;
   }

   static int E(int[] var0, int[] var1) {
      int var6 = 0;
      int var5 = -1;
      int var4 = -1;
      int var7 = da[var0[0]][18];
      if(da[var0[0]][27] > 0 && var0[11] > 0 && da[var0[0]][18] <= 1) {
         var7 = da[da[var0[0]][27]][18];
      }

      int var3 = B(var7, var0);
      yb = new int[pA][oA];
      vb = new int[pA][oA];
      vb[var0[2]][var0[1]] = 2;
      A(var0[1], var0[2], var3, 1, da[var0[0]][5]);

      for(int var2 = var0[1] - var3; var2 < var0[1] + var3; ++var2) {
         for(var7 = var0[2] - var3; var7 < var0[2] + var3; ++var7) {
            if(var2 > 1 && var2 < oA - 2 && (var7 > 1 || var7 == 1 && (var2 & 1) == 1) && (var7 < pA - 2 || var7 == pA - 2 && (var2 & 1) == 0) && var2 != var0[1] && var7 != var0[2] && kA[var7][var2] > var6 && vb[var7][var2] == 1 && A(var2, var7, 1, 3) == null) {
               var6 = kA[var7][var2];
               var5 = var2;
               var4 = var7;
               if(A(0, 100) < var0[22]) {
                  var7 = pA;
                  var2 = oA;
               }
            }
         }
      }

      if(var5 > 0) {
         if(da[var0[0]][27] > 0 && var0[11] > 0 && da[var0[0]][18] <= 1) {
            var0[19] = var0[0];
            var0[0] = da[var0[0]][27];
         }

         var0[21] = 4;
         fA = A(var5, var4, 1, 3);
         A(var0, var5, var4);
         if(hB == 1) {
            gA = true;
            var0[17] = 0;
            return 1;
         }

         if(var0[19] >= 0) {
            var0[0] = var0[19];
            var0[19] = -1;
         }
      }

      return -1;
   }

   static int J(int[] var0) {
      int var6 = -1;
      int var5 = -1;
      if(da[var0[0]][27] > 0 && var0[11] > 0 && da[var0[0]][18] <= 1) {
         var0[19] = var0[0];
         var0[0] = da[var0[0]][27];
      }

      int var1 = da[var0[0]][18];
      int var4 = B(var1, var0);
      yb = new int[pA][oA];
      vb = new int[pA][oA];
      vb[var0[2]][var0[1]] = 2;
      A(var0[1], var0[2], var4, 1, da[var0[0]][5]);
      int var3 = kA[var0[2]][var0[1]] + (100 - var0[22]) / 10;

      for(int var2 = var0[1] - var4; var2 < var0[1] + var4; ++var2) {
         for(var1 = var0[2] - var4; var1 < var0[2] + var4; ++var1) {
            if(var2 > 1 && var2 < oA - 2 && (var1 > 1 || var1 == 1 && (var2 & 1) == 1) && (var1 < pA - 2 || var1 == pA - 2 && (var2 & 1) == 0) && var2 != var0[1] && var1 != var0[2] && kA[var1][var2] <= var3 && kA[var1][var2] >= 0 && A(var2, var1, 1, 3) == null) {
               var6 = var2;
               var5 = var1;
               if(A(0, 100) < 5) {
                  var1 = pA;
                  var2 = oA;
               }
            }
         }
      }

      if(var6 > 0) {
         var0[21] = 2;
         fA = A(var6, var5, 1, 3);
         A(var0, var6, var5);
         if(hB == 1) {
            gA = true;
            var0[17] = 0;
            return 1;
         }
      }

      if(var0[19] >= 0) {
         var0[0] = var0[19];
         var0[19] = -1;
      }

      return -1;
   }

   static int K(int[] var0) {
      int var1 = 10 - var0[6];

      while(var1 > 0 && uA[db] >= 10) {
         if(var1 > 0) {
            ++var0[6];
            --var1;
            uA[db] -= 10;
         }
      }

      return 1;
   }

   static int L(int[] var0) {
      int var5 = da[var0[0]][23] - var0[12];
      int var4 = da[var0[0]][22] - var0[11];
      int var3 = 5 * ib / 100;
      int var2 = 1 * ib / 100;
      int var1 = uA[db];

      while((var5 > 0 || var4 > 0) && var1 > 0) {
         if(var1 >= var3 && var5 > 0) {
            ++var0[12];
            --var5;
            var1 -= var3;
         }

         if(var1 >= 1 && var4 > 0) {
            ++var0[11];
            --var4;
            var1 -= var2;
         }
      }

      int var6 = (uA[db] - var1) * ib / 100;
      uA[db] -= var6;
      return 1;
   }

   static void M(int[] var0) {
      if(var0 != null) {
         if((zA & 15) == 4 && db == 0 && var0[7] == 0) {
            B(1, var0[1], var0[2], true);
         }

         int var3 = A(var0);
         int var1;
         int[][] var2;
         if(var3 >= 0) {
            var2 = db == 0?wA:vA;
            var1 = 393279;
            if(var2 != null && var3 < var2.length) {
               var1 = var2[var3][3];
            }

            A(var3, db ^ 1, var0[9], true);
            if(o == 22) {
               HG.L(11);
               HG.eb[19][0] = var1;
               HG.eb[19][4] = 393327;
            } else {
               HG.L(10);
               HG.eb[19][0] = var1;
               HG.eb[19][4] = 393326;
            }

            HG.GA(19);
            HG.fb = true;
         } else {
            var3 = B(var0);
            if(var3 >= 0) {
               var2 = db == 0?yA:xA;
               var1 = 393277;
               if(var2 != null && var3 < var2.length) {
                  var1 = var2[var3][3];
               }

               A(var3, db ^ 1, var0[9]);
               if(o == 22) {
                  HG.L(11);
                  HG.eb[20][0] = var1;
                  HG.eb[20][4] = 393327;
               } else {
                  HG.L(10);
                  HG.eb[20][0] = var1;
                  HG.eb[20][4] = 393326;
               }

               HG.GA(20);
               HG.fb = true;
            }
         }

         var0[7] = 1;
         var0[18] = 1;
         var0[24] = 0;
         int[] var4 = A(pa[db], qa[db], 1, 3);
         if(var4 == null) {
            var0[18] = 0;
         } else {
            var0 = var4;
         }

         for(var3 = 0; var3 < 2; ++var3) {
            var2 = var3 == 0?ta:va;

            for(var1 = 0; var1 < var2.length; ++var1) {
               if(var2[var1][18] == 1 && A(var2[var1][1], var2[var1][2], 1, 3) == null) {
                  var2[var1][18] = 0;
               }
            }
         }

         hB = 2; //запрещено движение
         gA = false;
         if(o == 22 && var0[21] != 1) {
            var2 = db == 0?va:ta;
            var4 = A(var0, var2);
            if(var4[1] >= 0) {
               C(var0, var2[var4[1]]);
               return;
            }
         }
      }

   }
//------ Алгоритм построения возможных ходов врага
   static void N(int[] var0) {
      int[][] var4 = db == 0?va:ta;
      int[][] var3 = db == 0?wA:vA;
      int[][] var2 = db == 0?yA:xA;
      int[] var1 = A(var0, var4);
      if(var0[20] > 9) {
         if(var1[5] >= 0 && da[var0[0]][5] != 5 && da[var0[0]][6] != 3) {
            if(var1[1] >= 0) {
               var0[24] = 0;
               C(var0, var4[var1[1]]);
               return;
            }

            if(var1[5] < var3.length) {
               A(var0, var3[var1[5]][1], var3[var1[5]][2]);
               if(hB == 1) {
                  gA = true;
                  var0[17] = 0;
                  var0[21] = 3;
                  return;
               }
            }
         } else if(var1[4] >= 0 && da[var0[0]][5] != 5 && da[var0[0]][6] != 3) {
            if(var1[1] >= 0) {
               var0[24] = 0;
               C(var0, var4[var1[1]]);
               return;
            }

            if(var1[4] < var2.length) {
               A(var0, var2[var1[4]][1], var2[var1[4]][2]);
               if(hB == 1) {
                  gA = true;
                  var0[17] = 0;
                  var0[21] = 3;
                  return;
               }
            }
         }
      } else if(var1[5] >= 0) {
         var2 = var1[6] == 0?vA:wA;
         if(var1[5] < var2.length && da[var0[0]][5] != 5 && da[var0[0]][6] != 3) {
            A(var0, var2[var1[5]][1], var2[var1[5]][2]);
            if(hB == 1) {
               gA = true;
               var0[17] = 0;
               var0[21] = 0;
               return;
            }
         }
      } else if(var1[4] >= 0) {
         var2 = var1[6] == 0?xA:yA;
         if(var1[4] < var2.length && da[var0[0]][5] != 5 && da[var0[0]][6] != 3) {
            A(var0, var2[var1[4]][1], var2[var1[4]][2]);
            if(hB == 1) {
               gA = true;
               var0[17] = 0;
               var0[21] = 0;
               return;
            }
         }
      }

      switch(var0[21]) {
      case 0:
         if(var1[1] >= 0) {
            var0[24] = 0;
            C(var0, var4[var1[1]]);
            return;
         }

         if(var0[20] > 9) {
            if(var1[3] >= 0) {
               if(F(var0, var4[var1[3]]) > 0) {
                  D(var0, var4[var1[3]]);
               } else {
                  E(var0, var4[var1[3]]);
               }
               break;
            }

            if(var1[1] == -1 && var1[2] == -1 && P(var0) < 0 && O(var0) < 0) {
               J(var0);
               break;
            }
         }

         if(P(var0) > 0) {
            if(da[var0[0]][5] == 5 && rA[2][var0[2]][var0[1]] != 11) {
               if(I(var0) > 0) {
                  ;
               }
            } else if(Q(var0)) {
               if(var1[3] >= 0) {
                  E(var0, var4[var1[3]]);
               }
            } else {
               K(var0);
            }
         } else if(O(var0) > 0) {
            if(da[var0[0]][5] == 5 && rA[2][var0[2]][var0[1]] != 11) {
               if(I(var0) > 0) {
                  ;
               }
            } else if(Q(var0)) {
               if(var1[3] >= 0) {
                  E(var0, var4[var1[3]]);
               }
            } else {
               L(var0);
            }
         } else {
            var0[24] = 1;
         }
         break;
      case 1:
         if(var1[1] >= 0) {
            C(var0, var4[var1[1]]);
            return;
         }

         if(var0[20] <= 9 || var1[3] < 0) {
            var0[21] = 0;
            N(var0);  //алгоритм построения возможных ходов врага
            return;
         }

         if(F(var0, var4[var1[3]]) > 0) {
            D(var0, var4[var1[3]]);
         } else {
            E(var0, var4[var1[3]]);
         }
         break;
      case 2:
         if(var1[1] >= 0) {
            C(var0, var4[var1[1]]);
            return;
         }

         if(var1[3] >= 0) {
            if(da[var0[0]][5] == 5 && (da[var4[var1[3]][0]][5] != 5 || da[var4[var1[3]][0]][4] != 7)) {
               D(var0, var4[var1[3]]);
            }

            var0[21] = 0;
            N(var0);
            return;
         }

         if(var1[2] == -1) {
            J(var0);
         }
         break;
      case 3:
         if(var1[1] >= 0) {
            C(var0, var4[var1[1]]);
            return;
         }

         if(var1[3] >= 0) {
            if(F(var0, var4[var1[3]]) > 0) {
               D(var0, var4[var1[3]]);
            } else {
               E(var0, var4[var1[3]]);
            }
         }
         break;
      case 4:
         if(var1[1] >= 0) {
            C(var0, var4[var1[1]]);
            return;
         }

         if(var1[3] >= 0 && H(var0, var4[var1[3]]) > 0) {
            E(var0, var4[var1[3]]);
         } else {
            if(var1[3] < 0) {
               var0[21] = 0;
               N(var0);  //алгоритм построения возможных ходов врага
               return;
            }

            if(da[var0[0]][5] != 5 || da[var4[var1[3]][0]][5] == 5 && da[var4[var1[3]][0]][4] == 7) {
               var0[21] = 0;
               N(var0);  //алгоритм построения возможных ходов врага
               return;
            }

            D(var0, var4[var1[3]]);
         }
      }

      var0[7] = 1;
      var0[18] = 1;
      if(A(var0[1], var0[2], 1, 3) == null) {
         var0[18] = 0;
      }

   }

   public static void J(int var0) {
      int var3 = 0;
      kA = new int[pA][oA];

      int var1;
      int[][] var2;
      for(var2 = var0 == 0?ta:va; var3 < var2.length; ++var3) {
         yb = new int[pA][oA];
         var1 = da[var2[var3][0]][21] + 1;
         if(var2[var3][25] != -1) {
            var1 += ea[var2[var3][25]][8];
         }

         D(var2[var3][1], var2[var3][2], var1 - da[var2[var3][0]][20], var1);
      }

      var2 = var0 == 0?va:ta;

      for(var1 = 0; var1 < var2.length; ++var1) {
         yb = new int[pA][oA];
         var0 = da[var2[var1][0]][21] + 1;
         if(var2[var1][25] != -1) {
            var0 += ea[var2[var1][25]][8];
         }

         D(var2[var1][1], var2[var1][2], -(var0 - da[var2[var1][0]][20]), -var0);
      }

   }

   static void D(int var0, int var1, int var2, int var3) {
      if(var2 > 0) {
         if(var3 < 0) {
            return;
         }

         if(var3 <= var2) {
            kA[var1][var0] += var3;
         }
      } else {
         if(var3 > 0) {
            return;
         }

         if(var3 >= var2) {
            kA[var1][var0] += var3;
         }
      }

      for(int var7 = 0; var7 < 12; var7 += 2) {
         int var6 = var0 + nB[var0 & 1][var7];
         int var5 = var1 + nB[var0 & 1][var7 + 1];
         if(var6 > 1 && var6 < oA - 2 && (var5 > 1 || var5 == 1 && (var6 & 1) == 1) && (var5 < pA - 2 || var5 == pA - 2 && (var6 & 1) == 0) && (var3 & 255) > yb[var5][var6]) {
            int var4 = var3 > 0?var3 - 1:var3 + 1;
            yb[var5][var6] = var4 & 255;
            D(var6, var5, var2, var4);
         }
      }

   }
//------ Построение пути (надо проверить)
   static int[] A(int[] var0, int[][] var1) {
      int[] var2 = new int[]{0, -1, -1, -1, -1, -1, -1};

      int var3;
      for(var3 = 0; var3 < var1.length; ++var3) {
         if(G(var0, var1[var3]) > 0) {
            if(var2[3] == -1) {
               var2[3] = var3;
            } else if(F(var0, var1[var2[3]]) > 0) {
               var2[3] = var3;
            }
         }

         if(H(var0, var1[var3]) > 0) {
            ++var2[0];
            if(vb[var1[var3][2]][var1[var3][1]] > 0 && (lA[0] != 2 || da[var0[0]][5] != 5)) {
               if(F(var0, var1[var3]) > 0) {
                  if(var2[1] < 0) {
                     var2[1] = var3;
                  } else if((var1[var2[1]][15] & 15) - (var1[var2[1]][15] >> 4 & 15) < (var1[var3][15] & 15) - (var1[var3][15] >> 4 & 15)) {
                     var2[1] = var3;
                  } else {
                     var2[2] = var3;
                  }
               } else {
                  var2[2] = var3;
               }
            }
         }
      }

      int var4;
      int var8;
      if(var0[20] > 9) {
         if(da[var0[0]][5] != 5) {
            int[][] var5 = db == 0?yA:xA;

            for(var4 = 0; var4 < var5.length; ++var4) {
               var3 = var5[var4][1];
               var8 = var5[var4][2];
               if(A(var3, var8, 3, 3) == null) {
                  A(var0, var3, var8);
                  if(hB == 1) {
                     gB = new int[pA * oA * 2];
                     hB = 2; //запрещено движение
                     var2[4] = var4;
                     break;
                  }
               }
            }

            var5 = db == 0?wA:vA;

            for(var4 = 0; var4 < var5.length; ++var4) {
               var3 = var5[var4][1];
               var8 = var5[var4][2];
               if(A(var3, var8, 3, 3) == null) {
                  A(var0, var3, var8);
                  if(hB == 1) {
                     gB = new int[pA * oA * 2];
                     hB = 2; //запрещено движение
                     var2[5] = var4;
                     break;
                  }
               }
            }
         }
      } else if(da[var0[0]][5] != 5) {
         int[][] var6;
         int var7;
         int var9;
         for(var7 = 0; var7 < 2; ++var7) {
            var2[6] = var7;
            var6 = var7 == 0?vA:wA;

            for(var9 = 0; var9 < var6.length; ++var9) {
               var4 = var6[var9][1];
               var3 = var6[var9][2];
               var8 = B(var0[1], var0[2], var4, var3);
               if(var8 < da[var0[0]][18] && A(var4, var3, 3, 3) == null) {
                  A(var0, var4, var3);
                  if(hB == 1) {
                     gB = new int[pA * oA * 2];
                     hB = 2; //запрещено движение
                     var2[5] = var9;
                     return var2;
                  }
               }
            }
         }

         for(var7 = 0; var7 < 2; ++var7) {
            var2[6] = var7;
            var6 = var7 == 0?xA:yA;

            for(var9 = 0; var9 < var6.length; ++var9) {
               var4 = var6[var9][1];
               var3 = var6[var9][2];
               var8 = B(var0[1], var0[2], var4, var3);
               if(var8 < da[var0[0]][18] && A(var4, var3, 3, 3) == null) {
                  A(var0, var4, var3);
                  if(hB == 1) {
                     gB = new int[pA * oA * 2];
                     hB = 2; //запрещено движение
                     var2[4] = var9;
                     return var2;
                  }
               }
            }
         }
      }

      return var2;
   }

   static int F(int[] var0, int[] var1) {
      if(var0[12] > 0 && A(db ^ 1, var0)) {
         if((var1[15] >> 4 & 15) <= (var1[15] & 15) + 1) {
            return 1;
         }

         if(var1[6] - (var1[15] & 15) <= 1) {
            return 1;
         }
      }

      return -1;
   }

   static int G(int[] var0, int[] var1) {
      int var2 = B(var0[1], var0[2], var1[1], var1[2]);
      int var3 = 0;
      if(var0[25] != -1) {
         var3 = 0 + ea[var0[25]][7];
      }

      if(da[var0[0]][5] != 5) {
         if(rA[2][var0[2]][var0[1]] == 3) {
            --var3;
         } else if(rA[2][var0[2]][var0[1]] == 4) {
            ++var3;
         }
      }

      return var2 <= da[var0[0]][19] + var3?1:-1;
   }

   static int H(int[] var0, int[] var1) {
      if(var0 != null && var1 != null) {
         int var3 = B(var0[1], var0[2], var1[1], var1[2]);
         int var2 = da[var0[0]][21];
         if(var0[25] != -1) {
            var2 += ea[var0[25]][8];
         }

         if(da[var0[0]][5] == 5 && da[var1[0]][5] != 5 && var3 != 0) {
            return -1;
         }

         if(var3 >= da[var0[0]][20] && var3 <= var2) {
            byte var4 = -1;
            switch(da[var1[0]][6]) {
            case 0:
               if(da[var0[0]][13] > 0) {
                  var4 = 1;
               }
               break;
            case 1:
               if(da[var0[0]][12] > 0) {
                  var4 = 1;
               }
               break;
            case 2:
               if(da[var0[0]][14] > 0) {
                  var4 = 1;
               }
               break;
            case 3:
               if(da[var0[0]][15] > 0) {
                  var4 = 1;
               }
            }

            return var4;
         }
      }

      return -1;
   }

   static int O(int[] var0) {
      return var0[12] < 1 && da[var0[0]][23] > 0?1:(var0[11] < 1 && da[var0[0]][22] > 0?1:-1);
   }

   static int P(int[] var0) {
      return var0[6] < 10?1:-1;
   }

   static boolean Q(int[] var0) {
      int[][] var3 = db == 0?va:ta;

      for(int var2 = 0; var2 < var3.length; ++var2) {
         int var1 = B(var0[1], var0[2], var3[var2][1], var3[var2][2]);
         if(var1 <= 1) {
            return true;
         }
      }

      return false;
   }

   static boolean P() {
      int[][] var1 = db == 0?va:ta;

      for(int var0 = 0; var0 < var1.length; ++var0) {
         if(var1[var0][15] > 0) {
            return true;
         }
      }

      return false;
   }
//------ Вывод курсора с координатами Y(var2), X(var3) и смещение от начала экрана (var4, var5)
   public static void A(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      if(dA != null && !gA && !HG.fb) {
       A(var0, 6, 2, (dA[1] - la) * 45 + -23 + offset_x, (dA[2] - ma) * 50 + ((dA[1] & 1) == 1?25:0) + -16 + offset_y);
       //A(var0, 6, 2, (dA[1] - la) * 25 + -23, (dA[2] - ma) * 32 + ((dA[1] & 1) == 1?16:0) + -16);
      }

      var2 += var4;
      var3 += var5;
      if(dA != null && hB == 2 && dA != fA) {
         A(var0, 6, 4, var2, var3); //вывод курсора на карте
      } else {
         A(var0, 9, var1, var2, var3); //вывод курсора на карте
      }

   }
//------ Вывод стрелки при построении маршрута перемещения юнита
   static void A(Graphics var0) {
      int var7 = 0;

      for(int var6 = 0; var6 < gB.length; var6 += 2) {  //перемещение юнита
         int var5 = gB[var6];
         int var4 = gB[var6 + 1];
         if(var5 == 0 || var4 == 0) {
            return;
         }

         int var3;
         if(var6 == 0) {
            var3 = C(dA[1], dA[2], var5, var4);
         } else {
            var3 = var7;
         }

         int var2 = gB[var6 + 2];
         int var1 = gB[var6 + 3];
         if(var2 != 0 && var1 != 0) {
            var7 = C(var5, var4, var2, var1);
            var1 = oB[var7][var3];
         } else {
            var1 = var3;
         }

         var0.setClip(0, 0, f, g);
         var2 = (var4 - ma) * 50 + ((var5 & 1) == 1?25:0) + -16 + offset_y;
         //var2 = (var4 - ma) * 32 + ((var5 & 1) == 1?16:0) + -16;
         var3 = (var5 - la) * 45 + -23 + offset_x;
         //var3 = (var5 - la) * 25 + -23;
         A(var0, 4, var1, var3, var2);  //вывод стрелки маршрута пути
      }

   }

   static void A(Graphics var0, int[] var1, int[] var2, int var3, int var4) {
      if(var2 != null && var2[15] >> 8 == 1) {
         var4 = (f - HG.ob[24][3]) / 2;
         A(var0, 24, 0, var4, var3);
         if(var1 != null) {
            A(var0, 23, var1[9] + 9, var4 + 7 - HG.ob[23][3], var3);
         }

         A(var0, 23, var2[9], var4 + HG.ob[24][3] - 7, var3);
         C(var0, var2[15] >> 4 & 15, var4 + 17, var3 + 7, 1);
         C(var0, var2[15] & 15, var4 + HG.ob[24][3] - 17 - HG.ob[11][3], var3 + 7, 1);
      }

   }
//------ Вывод нижней информационной панели на карте
   static void B(Graphics var0) {
      var0.setClip(0, 0, f, g);
      int var5 = 33 / HG.ob[18][4] + 1;
      int var4 = g - 32 - 1 + 0;

      int var1;
      int var2;
      int var3;
      for(var3 = 0; var3 < var5; ++var3) {
         var2 = 0;

         for(var1 = 0; var1 <= f / HG.ob[18][3]; ++var1) {
            A(var0, 18, 13, var2, var4);        //вывод песочного цвета фона
            A(var0, 18, 13, var2, var4 - 0);    //вывод песочного цвета фона
            var2 += HG.ob[18][3];
         }

         var4 += HG.ob[18][4];
      }

      var0.setClip(0, 0, f, g);
      var0.setColor(h[12]);
      var0.fillRect(0, g + 0 - 32 - 2, f, 1);
      var0.drawLine(34, g + 0 - 32 - 1, 34, g);
      var0.drawLine(34, g + 0 - 16, f, g + 0 - 16);
      if(vb[qa[db]][pa[db]] == 0) {
//         A(var0, 6, 0, 1, g - 32 + 0);  //вывод черного гекса (когда курсор на скрытой территории) в инфопанели
      } else {
         if(rA[0][qa[db]][pa[db]] != -1) {
//            A(var0, sA, rA[0][qa[db]][pa[db]], 1, g - 32 + 0);  //вывод гекса местности в инфопанели
         }

         if(rA[1][qa[db]][pa[db]] != -1) {
//            A(var0, 3, rA[1][qa[db]][pa[db]], 1, g - 32 + 0);  //вывод гекса дороги и реки в инфопанели
         }
      }

//      A(var0, 9, 0, 1, g - 32 + 0);  //вывод гекса курсора в инфопанели
      String var6;
      if(o != 17 && fA != null && fA[23] == 1) {
//         A(var0, da[fA[0]][0], 3, 3, g - 32 + 0);  //вывод гекса юнита в инфопанели
         var4 = g - 32 + 0;
         var6 = HG.H(da[fA[0]][10]);
         var6 = var6 + " " + "(" + pa[db] + "," + qa[db] + ")";   //вывод координат курсора
         String var8 = null;
         if(rA[2][qa[db]][pa[db]] != 12 && (rA[1][qa[db]][pa[db]] < 22 || rA[1][qa[db]][pa[db]] > 26)) {
            if(rA[2][qa[db]][pa[db]] == 11) {
               var8 = C(qa[db], pa[db]);
            }
         } else {
            var8 = B(qa[db], pa[db]);
         }
         
         if(var6 != rB[0]) {
            rB[0] = var6;
            var2 = K(2) + 4;
            i[2].stringWidth(rB[0]);
            HG.A(3, 0, var4 - var2, f - 0, var2 - 0, 20);
            if(var8 != null) {
               HG.A(6, 0, var4 - 2 * var2, f - 0, var2 - 0, 20);
            }
         }
         
         if(rB != null) {
            HG.A(rB, 3, 2, var0, 3, sA == 2?h[47]:h[45], sA == 2?h[45]:h[46]);    //вывод названия техники внизу над нижней инфопанели
         }

         if(var8 != null) {
            HG.A(var8, 6, 2, var0, 3, sA == 2?h[47]:h[45], sA == 2?h[45]:h[46]);    //вывод названия города
         }

         var5 = g - 32 + 0;
         A(var0, 17, 3, 35, var5);
         var1 = 35 + HG.ob[17][3] + 0 + 10;
         A(var0, 17, 1, var1, var5);
         var1 += HG.ob[17][3] + 0 + 10;
         A(var0, 17, 2, var1, var5);
         var1 += HG.ob[17][3] + 0 + 10;
         A(var0, 17, 0, var1, var5);
         var1 += HG.ob[17][3] + 0 + 10;
         A(var0, 17, 4, var1, var5);
         var2 = f - 2 * HG.ob[22][3] - 6;
         byte var9 = 9;
         switch(D(fA)) {
         case -1:
            var9 = 7;
            break;
         case 1:
            var9 = 8;
         }

         A(var0, 22, var9, var2, var5);
         var2 += HG.ob[22][3] + 3;
         var9 = 5;
         if(fA[8] > 0) {
            var9 = 4;
         } else if(fA[8] < 0) {
            var9 = 6;
         }

         A(var0, 22, var9, var2, var5);
         var4 = 36 + (HG.ob[17][3] - HG.ob[11][3]);
         var3 = da[fA[0]][19];
         byte var10 = 1;
         if(fA[25] != -1) {
            var1 = ea[fA[25]][7];
            if(var1 > 0) {
               var10 = 2;
               var3 += var1;
            }
         }

         if(da[fA[0]][5] != 5) {
            if(rA[2][qa[db]][pa[db]] == 3) {
               --var3;
               var10 = 2;
               A(var0, 11, 10 + 2 * HG.ob[11][1], var4 - HG.ob[11][3], var5 + 1);
            } else if(rA[2][qa[db]][pa[db]] == 4) {
               ++var3;
               var10 = 2;
               A(var0, 11, 11 + 2 * HG.ob[11][1], var4 - HG.ob[11][3], var5 + 1);
            }
         }

         C(var0, var3, var4 - 1 + 10 + 6 - 10, var5 + 1, var10);
         var3 = var4 + HG.ob[17][3] + 0;
         var10 = 1;
         var1 = da[fA[0]][18];
         var1 = B(var1, fA);
         if(lA[0] == 2) {
            var10 = 2;
         }

         C(var0, var1, var3 - 1 + 10 + 6, var5 + 1, var10);
         var1 = var3 + HG.ob[17][3] + 0 + 10;
         if(da[fA[0]][22] > 0) {
            C(var0, fA[11], var1 - 1 + 10 + 6, var5 + 1, 1);
         } else {
            A(var0, 11, 10 + 1 * HG.ob[11][1], var1 - 1 + 10 + 6, var5 + 1);
         }

         var1 += HG.ob[17][3] + 0 + 10;
         C(var0, fA[12], var1 - 1 + 10 + 6, var5 + 1, 1);
         var1 += HG.ob[17][3] + 0 + 10;
         var1 -= 3 * HG.ob[11][3];
         C(var0, da[fA[0]][20], var1 + 10, var5 + 8, 1);
         var1 += HG.ob[11][3];
         A(var0, 11, 10 + 1 * HG.ob[11][1], var1 + 10, var5 + 8);
         var3 = var1 + HG.ob[11][3];
         var2 = da[fA[0]][21];
         var9 = 1;
         if(fA[25] != -1 && ea[fA[25]][8] > 0) {
            var2 += ea[fA[25]][8];
            var9 = 2;
         }

         C(var0, var2, var3 + 10, var5 + 8, var9);
         var4 = var5 + HG.ob[17][4] + 2;
         A(var0, 35, var4 - 0, fA);
         var3 = f - (5 * HG.ob[22][3] - 3);
         var2 = fA[24];
         if(da[fA[0]][5] != 5) {
            var2 += z[rA[2][fA[2]][fA[1]]][8] - mA;
         }

         if(fA[25] != -1) {
            ++var2;
         }

         for(var1 = 0; var1 < 5; ++var1) {
            if(var2 > var1) {
               A(var0, 22, 2, var3 + var1 * (HG.ob[22][3] - 2), var4 - 0);
            } else {
               A(var0, 22, 3, var3 + var1 * (HG.ob[22][3] - 2), var4 - 0);
            }
         }
      } else {
         var0.setClip(0, 0, f, g);
         var4 = g - 32 + 0;
         String var7 = HG.H(z[rA[2][qa[db]][pa[db]]][9]);
         var7 = var7 + " " + "(" + pa[db] + "," + qa[db] + ")";   //вывод координат курсора
         if(rA[2][qa[db]][pa[db]] != 12 && (rA[1][qa[db]][pa[db]] < 22 || rA[1][qa[db]][pa[db]] > 26)) {
            if(rA[2][qa[db]][pa[db]] == 11) {
               var6 = C(qa[db], pa[db]);
               if(var6 != null) {
                  var7 = var6;
               }
            }
         } else {
            var6 = B(qa[db], pa[db]);
            if(var6 != null) {
               var7 = var6;
            }
         }

         if(var7 != rB[0]) {
            rB[0] = var7;
            var2 = K(2) + 4;
            i[2].stringWidth(rB[0]);
            HG.A(3, 0, var4 - var2 - -3 + 0, f - 0, var2 - 0, 20);
         }

         HG.A(rB, 3, 2, var0, 3, sA == 2?h[47]:h[45], sA == 2?h[45]:h[46]);    //вывод названия местности внизу над нижней инфопанели
         var3 = f - 6 * (HG.ob[22][3] - 3);
         var2 = z[rA[2][qa[db]][pa[db]]][8] - mA;

         for(var1 = 0; var1 < 5; ++var1) {
            if(var2 > var1) {
               A(var0, 22, 2, var3 + var1 * (HG.ob[22][3] - 2), var4);
            } else {
               A(var0, 22, 3, var3 + var1 * (HG.ob[22][3] - 2), var4);
            }
         }

         var3 = g - HG.ob[8][4];
         A(var0, 8, 0, 35, var3);
         var2 = 35 + HG.ob[8][3];
         var1 = z[rA[2][qa[db]][pa[db]]][0];
         C(var0, var1 >= 16?0:var1, var2, var3, 1);
         var1 = var2 + HG.ob[11][3];
         A(var0, 8, 1, var1, var3);
         var2 = var1 + HG.ob[8][3];
         var1 = z[rA[2][qa[db]][pa[db]]][1];
         C(var0, var1 >= 16?0:var1, var2, var3, 1);
         var1 = var2 + HG.ob[11][3];
         A(var0, 8, 2, var1, var3);
         var2 = var1 + HG.ob[8][3];
         var1 = z[rA[2][qa[db]][pa[db]]][2];
         C(var0, var1 >= 16?0:var1, var2, var3, 1);
         var1 = var2 + HG.ob[11][3];
         A(var0, 8, 3, var1, var3);
         var2 = var1 + HG.ob[8][3];
         var1 = z[rA[2][qa[db]][pa[db]]][3];
         C(var0, var1 >= 16?0:var1, var2, var3, 1);
      }

   }
//------ Вывод верхней информационной панели на карте
   static void C(Graphics var0) {
      var0.setClip(0, 0, f, g);
      int var5 = (HG.ob[12][4] + 1) / HG.ob[18][4] + 1;
      int var4 = HG.ob[12][4] - HG.ob[18][4] + 1;

      int var1;
      for(int var3 = 0; var3 < var5; ++var3) {
         int var2 = 0;

         for(var1 = 0; var1 <= f / HG.ob[18][3]; ++var1) {
            A(var0, 18, 13, var2, var4);    //вывод песочного цвета фона
            var2 += HG.ob[18][3];
         }

         var4 -= HG.ob[18][4];
      }

      var0.setClip(0, 0, f, g);
      var0.setColor(h[12]);
      var0.fillRect(0, HG.ob[12][4] + 1, f, 1);
      if((zA & 15) == 3) {
         A(var0, 23, 9 + (db == 0?ra:sa), 5, 0);
      } else {
         A(var0, 33, 1, 0, 0);     //вывод значка календаря в инфопанели
         C(var0, eb, 12, 5, 1);    //вывод дня календаря в инфопанели
      }

      var1 = lA[0];
      if(var1 == 2 && mA == 1) {
         ++var1;
      }

      A(var0, 12, var1, f / 2 - 13, 1);      //вывод значка погоды в инфопанели
      C(var0, uA[db], f - (2 + HG.ob[33][3] + HG.ob[11][3] + 2), 5, 1); //вывод очков престижа в инфопанели
      A(var0, 33, 0, f - HG.ob[33][3] - 2, 0);    //вывод венка около очков престижа в инфопанели
   }
//------ Вывод флагов нации на гексах
   static void D(Graphics var0) {
      int var4 = 8 - HG.ob[5][4];

      int var1;
      int var2;
      int var3;
      for(var3 = 0; var3 < vA.length; ++var3) {
         var2 = (vA[var3][1] - la) * 45 + offset_x;    
       //var2 = (vA[var3][1] - la) * 25;
         var1 = (vA[var3][2] - ma) * 50 + ((vA[var3][1] & 1) == 1?25:0) + offset_y;    
       //var1 = (vA[var3][2] - ma) * 32 + ((vA[var3][1] & 1) == 1?16:0);
         A(var0, 5, vA[var3][0], var2 + -11, var1 + var4);
      }

      for(var3 = 0; var3 < xA.length; ++var3) {
         var2 = (xA[var3][1] - la) * 45 + offset_x;    
       //var2 = (xA[var3][1] - la) * 25;
         var1 = (xA[var3][2] - ma) * 50 + ((xA[var3][1] & 1) == 1?25:0) + offset_y;    
       //var1 = (xA[var3][2] - ma) * 32 + ((xA[var3][1] & 1) == 1?16:0);
         A(var0, 5, xA[var3][0], var2 + -11, var1 + var4);
      }

      for(var3 = 0; var3 < wA.length; ++var3) {
         var2 = (wA[var3][1] - la) * 45 + offset_x;    
       //var2 = (wA[var3][1] - la) * 25;
         var1 = (wA[var3][2] - ma) * 50 + ((wA[var3][1] & 1) == 1?25:0) + offset_y;    
       //var1 = (wA[var3][2] - ma) * 32 + ((wA[var3][1] & 1) == 1?16:0);
         A(var0, 5, wA[var3][0], var2 + -11, var1 + var4);
      }

      for(var3 = 0; var3 < yA.length; ++var3) {
         var2 = (yA[var3][1] - la) * 45 + offset_x;    
       //var2 = (yA[var3][1] - la) * 25;
         var1 = (yA[var3][2] - ma) * 50 + ((yA[var3][1] & 1) == 1?25:0) + offset_y;    
       //var1 = (yA[var3][2] - ma) * 32 + ((yA[var3][1] & 1) == 1?16:0);
         A(var0, 5, yA[var3][0], var2 + -11, var1 + var4);
      }

   }

   static void E(Graphics var0) {
      var0.setClip(0, 0, f, g);
      var0.setColor(h[35]);
      var0.fillRect(0, 0, f, g);
      if((o == 18 || o == 17) && rA != null) {
         Font var2 = i[1];
         int var1 = g - var2.getHeight() - 5;
         B(var0, var1);                 //вывод стратегической карты в режиме паузы
         HG.A(2, 0, var1, f, 50, 20);   //вывод надписи - пауза
         var0.setColor(h[35]);
         var0.fillRect(0, var1, f, var2.getHeight() - 5);
      } else {
         HG.A(2, 0, g / 2, f, 50, 20);
      }

      var0.setColor(h[36]);
      HG.A(HG.H(78) + " - " + HG.H(79) + ": " + HG.H(327707), 2, 1, var0, 0, h[36], h[37]);
   }
//------ Вывод силы юнита на иконке
   static void C(Graphics var0, int var1, int var2, int var3, int var4) {
      if(var1 < 0) {
         var1 *= -1;
         A(var0, 11, 10 + var4 * HG.ob[11][1], var2, var3);
         var2 += HG.ob[11][3] + 2;
      }

      byte[] var5 = String.valueOf(var1).getBytes();

      for(var1 = 0; var1 < var5.length; ++var1) {
         var0.setClip(0, 0, f, g);
         A(var0, 11, var5[var5.length - 1 - var1] - 48 + var4 * HG.ob[11][1], var2 - var1 * HG.ob[11][3] - var1 * 2, var3);
      }

   }

   static void A(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      if(var1 < 0) {
         var1 *= -1;
         A(var0, 11, 10 + var4 * HG.ob[11][1], var2, var3, var5, var6, var7, var8);
         var2 += HG.ob[11][3] + 2;
      }

      byte[] var10 = String.valueOf(var1).getBytes();

      for(var1 = 0; var1 < var10.length; ++var1) {
         var0.setClip(0, 0, f, g);
         if(var9 == 2) {
            A(var0, 11, var10[var10.length - 1 - var1] - 48 + var4 * HG.ob[11][1], var2 - var1 * HG.ob[11][3] - var1 * 2, var3, var5, var6, var7, var8);
         } else {
            A(var0, 11, var10[var1] - 48 + var4 * HG.ob[11][1], var2 + var1 * HG.ob[11][3] + var1 * 2, var3, var5, var6, var7, var8);
         }
      }

   }

   static int A(Graphics var0, int var1, int var2, int var3) {
      int var9 = var2;
      int var5 = HG.ob[18][4] << 1;
      A(var0, 18, HG.ob[18][1] * var3, var1, var2);
      int var6 = var1 + HG.ob[18][3];
      int var4 = (f - 2 * var1 - 2 * HG.ob[18][3]) / HG.ob[18][3] + 1;
      short var8 = HG.ob[18][3];
      int var7 = f - var1 - var8;

      for(var6 = var6; var6 < var7; var6 += var8) {
         if(var6 + var8 > var7) {
            var6 = var7 - var8;
         }

         A(var0, 18, HG.ob[18][1] * var3 + 1, var6, var9);
      }

      var6 = f - var1 - HG.ob[18][3];
      A(var0, 18, HG.ob[18][1] * var3 + 2, var6, var9);
      var6 = var9 + HG.ob[18][4];
      int var12;
      if(var3 == 2) {
         var12 = 0;

         for(short var11 = HG.ob[18][4]; i[2].getHeight() + 0 >= var11 + var12; var12 += 3) {
            A(var0, 18, 10, var1, var6);
            var3 = f - var1 - HG.ob[18][3];
            A(var0, 18, 10, var3, var6);
            var6 += 3;
         }

         var5 += var12;
         var0.setColor(h[13]);
         var0.setClip(0, 0, f, g);
         var0.fillRect(var1 + HG.ob[18][3], var2 + 1, f - 2 * var1 - 2 * HG.ob[18][3], var5 - 1);
      }

      A(var0, 18, 9, var1, var6);
      var7 = var1 + HG.ob[18][3];

      for(var3 = 0; var3 < var4; ++var3) {
         A(var0, 18, 10, var7 + var3 * HG.ob[18][3], var6);
      }

      var3 = f - var1 - HG.ob[18][3];
      A(var0, 18, 11, var3, var6);
      int var10 = var1;
      var9 = var6 + HG.ob[18][4];
      var12 = (g - 2 * var2 - var5 - HG.ob[18][4]) / HG.ob[18][4];

      for(var7 = 0; var7 <= var4 + 1; ++var7) {
         byte var13 = 13;
         if(var7 == 0) {
            var13 = 12;
         } else if(var7 == var4 + 1) {
            var13 = 14;
            var10 = f - var1 - HG.ob[18][3];
         }

         for(var3 = 0; var3 <= var12; ++var3) {
            A(var0, 18, var13, var10, var9 + var3 * HG.ob[18][4]);
         }

         var10 += HG.ob[18][3];
      }

      var6 = g - var2 - HG.ob[18][4];
      A(var0, 18, 1, var1, var6);
      var3 = var1 + HG.ob[18][3];

      for(var2 = 0; var2 < var4; ++var2) {
         A(var0, 18, 1, var3 + var2 * HG.ob[18][3], var6);
      }

      var1 = f - var1 - HG.ob[18][3];
      A(var0, 18, 1, var1, var6);
      return var5;
   }
//------ Вывод стратегической карты
   static void B(Graphics var0, int var1) {
      var0.setClip(0, 0, f, g);
      var0.setColor(h[11]);
      int var3 = (f - (oA - 1) * qA) / 2;
      int var2 = (g - (pA - 1) * qA) / 2;
      if((pA - 2) * qA > var1) {
         var2 = var1 - (pA - 2) * qA;
      }

      switch(sA) {
      case 0:
         var0.setColor(h[22]);
         break;
      case 2:
         var0.setColor(h[20]);
         break;
      default:
         var0.setColor(h[21]);
      }

      var0.fillRect(var3, var2, (oA - 1) * qA, (pA - 1) * qA);
    //Горы
      var0.setColor(h[16]);

      int var4;
      int var5;
      for(var5 = 0; var5 < vA.length; ++var5) {
         var4 = vA[var5][1] * qA;
         var1 = vA[var5][2] * qA;
         if((vA[var5][2] & 1) == 0) {
            var1 -= qA / 2;
         }

         var0.fillRect(var3 + var4 - qA, var2 + var1 - qA, qA * 3, qA * 3);
      }
    //Аэродром свой 
      var0.setColor(h[18]);
     
      for(var5 = 0; var5 < xA.length; ++var5) {
         var4 = xA[var5][1] * qA;
         var1 = xA[var5][2] * qA;
         if((xA[var5][2] & 1) == 0) {
            var1 -= qA / 2;
         }

         var0.fillRect(var3 + var4 - qA / 2, var2 + var1 - qA / 2, qA * 2, qA * 2);
      }
    //Города для захвата 
      var0.setColor(h[17]);
     
      for(var5 = 0; var5 < wA.length; ++var5) {
         var4 = wA[var5][1] * qA;
         var1 = wA[var5][2] * qA;
         if((wA[var5][2] & 1) == 0) {
            var1 -= qA / 2;
         }

         var0.fillRect(var3 + var4 - qA, var2 + var1 - qA, qA * 3, qA * 3);
      }
      //Аэродром вражеский
      var0.setColor(h[19]);

      for(var5 = 0; var5 < yA.length; ++var5) {
         var4 = yA[var5][1] * qA;
         var1 = yA[var5][2] * qA;
         if((yA[var5][2] & 1) == 0) {
            var1 -= qA / 2;
         }

         var0.fillRect(var3 + var4 - qA / 2, var2 + var1 - qA / 2, qA * 2, qA * 2);
      }

      for(var5 = 1; var5 < pA - 1; ++var5) {
         for(var4 = 1; var4 < oA - 1; ++var4) {
            if(var4 > 1 && var4 < oA - 2 && (var5 > 1 || var5 == 1 && (var4 & 1) == 1) && (var5 < pA - 2 || var5 == pA - 2 && (var4 & 1) == 0)) {
               if(rA[2][var5][var4] == 0) {
                  //Шоссе
                  var0.setColor(h[24]);
                  var1 = var5 * qA;
                  if((var4 & 1) == 0) {
                     var1 -= qA / 2;
                  }

                  var0.fillRect(var3 + var4 * qA, var2 + var1, qA, qA);
               } else if(rA[2][var5][var4] == 10 || rA[2][var5][var4] == 5) {
                  var1 = var5 * qA;
                  if((var4 & 1) == 0) {
                     var1 -= qA / 2;
                  }
                  //Река
                  var0.setColor(h[23]);
                  var0.fillRect(var3 + var4 * qA, var2 + var1, qA, qA);
               }
            }
         }
      }
      //Свои юниты
      var0.setColor(h[14]);

      for(var5 = 0; var5 < ta.length; ++var5) {
         if(vb[ta[var5][2]][ta[var5][1]] > 0) {
            var4 = ta[var5][1] * qA;
            var1 = ta[var5][2] * qA;
            if((ta[var5][1] & 1) == 0) {
               var1 -= qA / 2;
            }

            var0.fillRect(var3 + var4, var2 + var1, qA, qA);
         }
      }
      //Вражеские юниты
      var0.setColor(h[15]);

      for(var5 = 0; var5 < va.length; ++var5) {
         if(vb[va[var5][2]][va[var5][1]] > 0) {
            var4 = va[var5][1] * qA;
            var1 = va[var5][2] * qA;
            if((va[var5][1] & 1) == 0) {
               var1 -= qA / 2;
            }

            var0.fillRect(var3 + var4, var2 + var1, qA, qA);
         }
      }

      var0.setColor(h[3]);
      var1 = qa[db] * qA;
      if((pa[db] & 1) == 0) {
         var1 -= qA / 2;
      }

      var0.fillRect(var3 + pa[db] * qA, var2 + var1, qA, qA);
   }

   public static int K(int var0) {
      return i[var0].getHeight();
   }
//------ Вывод строки текста в меню, брифинге и игре
   public static void A(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) { //вывод текста из var1
      if(var1 != null) {
         var3 -= 3;
         switch(var5 & 240) {
         case 16:
            var3 -= K(var4) >> 1;
         default:
            Font var9 = i[var4];
            var0.setFont(var9); //установка шрифта
            var3 += (var9.getHeight() - (var9.getBaselinePosition() + 0)) / 2;
            switch(var5 & 15) {
            case 1:
               var2 -= var9.stringWidth(var1) >> 1;
               break;
            case 2:
               var2 -= var9.stringWidth(var1);
            }

            switch(var6) {
            case 1:
               var0.setColor(var8); //установка цвета
               var0.drawString(var1, var2 + 1, var3 + 1, 20);
               var0.setColor(var7); //установка цвета
               var0.drawString(var1, var2, var3, 20);
               break;
            case 2:
               var0.setColor(var7); //установка цвета
               var0.drawString(var1, var2 + 1, var3 + 1, 20);
               var0.setColor(var8); //установка цвета
               var0.drawString(var1, var2, var3, 20);
               break;
            case 3:
               var0.setColor(var8); //установка цвета
               var0.drawString(var1, var2 - 1, var3 - 1, 20);
               var0.drawString(var1, var2 + 1, var3 - 1, 20);
               var0.drawString(var1, var2 + 1, var3 + 1, 20);
               var0.drawString(var1, var2 - 1, var3 + 1, 20);
               var0.drawString(var1, var2, var3 - 1, 20);
               var0.drawString(var1, var2, var3 + 1, 20);
               var0.drawString(var1, var2 - 1, var3, 20);
               var0.drawString(var1, var2 + 1, var3, 20);
               var0.setColor(var7); //установка цвета
               var0.drawString(var1, var2, var3, 20);
               break;
            default:
               var0.setColor(var7); //установка цвета
               var0.drawString(var1, var2, var3, 20);
            }

         }
      }
   }

   static void F(Graphics var0) {
      for(int var3 = 0; var3 < aA.length; ++var3) {
         if(aA[var3][0] != 0 && vb[aA[var3][1]][aA[var3][0]] == 2 && aA[var3][0] >= la && aA[var3][0] <= la + (f - 25) / 45 + 2 && aA[var3][1] >= ma && aA[var3][1] <= ma + g / 50 + 1) {
          //if(aA[var3][0] != 0 && vb[aA[var3][1]][aA[var3][0]] == 2 && aA[var3][0] >= la && aA[var3][0] <= la + (f - 16) / 25 + 2 && aA[var3][1] >= ma && aA[var3][1] <= ma + g / 32 + 1) {
            int var2 = (aA[var3][0] - la) * 45 + -23 + offset_x;
          //int var2 = (aA[var3][0] - la) * 25 + -23;
            int var1 = (aA[var3][1] - ma) * 50 + ((aA[var3][0] & 1) == 1?25:0) + -16 + offset_y;
          //int var1 = (aA[var3][1] - ma) * 32 + ((aA[var3][0] & 1) == 1?16:0) + -16;
            A(var0, 36, aA[var3][2], var2, var1);
         }
      }

   }

   static void Q() {
      int var1 = A(0, 100);

      int var0;
      for(var0 = 0; var0 < lA.length - 1; ++var0) {
         lA[var0] = lA[var0 + 1];
      }

      switch(lA[1]) {
      case 0:
         if(var1 < nA[0]) {
            lA[2] = 1;
         } else {
            lA[2] = 0;
         }
         break;
      case 1:
         if(var1 < nA[2]) {
            lA[2] = 0;
         } else {
            var0 = A(0, 100);
            if(var0 < nA[1]) {
               lA[2] = 2;
            } else {
               lA[2] = 1;
            }
         }
         break;
      case 2:
         if(var1 < nA[3]) {
            lA[2] = 1;
         } else {
            var0 = A(0, 100);
            if(var0 < nA[4]) {
               lA[2] = 0;
            } else {
               lA[2] = 2;
            }
         }
      }

   }
//------ Расчет хода юнита в зависимости от погоды
   static int B(int var0, int[] var1) { //запас хода, текущий юнит
      var0 = var0;
      if(var1 != null) {
         if(da[var1[0]][4] == 16) {
            return 0;
         }

         if(mA == 1) {  //погода???
            --var0;
            if(var0 < 1) {
               var0 = 1;
            }
         }

         if(lA[0] == 2) {  //погода???
            var0 = var0 * 3 / 4;
         }
      }

      return var0;
   }

   public static final boolean B(int var0, int var1, int var2, boolean var3) {
      if((zA & 15) == 3) {
         return false;
      } else {
         boolean var8 = false;
         int var7 = sB.length;

         while(true) {
            --var7;
            if(var7 < 0) {
               return var8;
            }

            int[] var6 = sB[var7];
            if(var6[0] == var0 && var6[2] == var1 && var6[3] == var2 && var6[1] == 1) {
               int[] var5 = null;
               int var4 = 4;

               while(true) {
                  --var4;
                  if(var4 >= 0) {
                     if(uB[var4][0] != 0) {
                        continue;
                     }

                     var5 = uB[var4];
                  }

                  if(var5 == null) {
                     return var8;
                  }

                  var5[0] = 1;
                  var5[1] = var7;
                  var5[2] = var6[4];
                  var5[3] = 0;
                  var5[4] = 0;
                  var5[7] = 8;
                  if(var3) {
                     R(var5);
                  }

                  var8 = true;
                  break;
               }
            }
         }
      }
   }

   public static final void R() {
      if(sB != null && sB.length > 0 && (zA & 15) != 3) {
         int var1 = 4;

         while(true) {
            --var1;
            if(var1 < 0) {
               break;
            }

            int[] var0 = uB[var1];
            if(var0[0] != 0) {
               R(var0);
            }
         }
      }

   }

   public static final void R(int[] var0) {
      int[] var13 = sB[var0[1]];
      byte[] var12 = tB[var0[2]];
      int var11 = var0[3];
      int var10 = var0[4];
      int var9 = var0[5];
      int var8 = var0[6];
      int var7 = var0[7];
      boolean var6 = false;
      boolean var5 = false;

      do {
         if(var10 == 0) {
            if(var11 >= var12.length) {
               var6 = true;
               break;
            }

            var10 = var12[var11++];
            switch(var10) {
            case 1:
            case 49:
            case 52:
            case 53:
            case 55:
            case 66:
            case 67:
            case 85:
            case 86:
            case 88:
            case 89:
               var9 = var12[var11++];
               break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 34:
            case 84:
               var9 = var12[var11++] << 8 & '\uff00' | var12[var11++] & 255;
               break;
            case 40:
            case 41:
               var9 = var12[var11++] << 16 & 16711680 | var12[var11++] << 8 & '\uff00' | var12[var11++] & 255;
               var8 = var12[var11++];
               break;
            case 50:
            case 51:
            case 54:
            case 56:
            case 57:
            case 87:
               var9 = var12[var11++];
               var8 = var12[var11++];
               break;
            case 81:
               var9 = var12[var11++] << 16 & 16711680 | var12[var11++] << 8 & '\uff00' | var12[var11++] & 255;
               break;
            }
         }

         boolean var4;
         var4 = true;
         int var2;
         int var14;
         int[][] var15;
         byte var16;
         label257:
         switch(var10) {
         case 1:
            --var9;
            if(var9 >= 0) {
               var4 = false;
               var5 = true;
            }
            break;
         case 17:
            var11 = var9;
            break;
         case 18:
            --var7;
            if(var0[var7] != 0) {
               var11 = var9;
            }
            break;
         case 19:
            --var7;
            if(var0[var7] == 0) {
               var11 = var9;
            }
            break;
         case 20:
            --var7;
            if(var0[var7] < 0) {
               var11 = var9;
            }
            break;
         case 21:
            --var7;
            if(var0[var7] > 0) {
               var11 = var9;
            }
            break;
         case 33:
            --var7;
            var9 = var0[var7];
         case 34:
            --var7;
            var8 = var0[var7];
            byte var17 = 0;
            if(var8 > var9) {
               var17 = 1;
            } else if(var8 < var9) {
               var17 = -1;
            }

            var0[var7++] = var17;
            break;
         case 40:
            var15 = var8 == 0?xA:yA;
            var16 = 0;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  break;
               }

               if(var15[var14][3] == var9) {
                  var16 = 1;
                  break;
               }
            }

            var0[var7++] = var16;
            break;
         case 41:
            var15 = var8 == 0?vA:wA;
            var16 = 0;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  break;
               }

               if(var15[var14][3] == var9) {
                  var16 = 1;
                  break;
               }
            }

            var0[var7++] = var16;
            break;
         case 42:
            var0[var7++] = gb[bb]?1:0;
            break;
         case 49:
            var14 = 0;
            switch(var9) {
            case 0:
               var14 = eb;
               break;
            case 1:
               var14 = uA[0];
               break;
            case 2:
               var14 = vB;
            }

            var0[var7++] = var14;
            break;
         case 50:
            switch(var9) {
            case 0:
               eb = var8;
               break label257;
            case 1:
               uA[0] = var8;
               break label257;
            case 2:
               vB = var8;
            default:
               break label257;
            }
         case 51:
            switch(var9) {
            case 0:
               eb += var8;
               break label257;
            case 1:
               uA[0] += var8;
               break label257;
            case 2:
               vB += var8;
            default:
               break label257;
            }
         case 52:
            var0[var7++] = (var9 == 0?xA:yA).length;
            break;
         case 53:
            var0[var7++] = (var9 == 0?vA:wA).length;
            break;
         case 54:
            var15 = var9 == 0?ta:va;
            var2 = 0;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  var0[var7++] = var2;
                  break label257;
               }

               if(da[var15[var14][0]][4] == var8) {
                  ++var2;
               }
            }
         case 55:
            var15 = var9 == 0?ta:va;
            var2 = 0;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  var0[var7++] = var2;
                  break label257;
               }

               if(var15[var14][13] != -1) {
                  ++var2;
               }
            }
         case 56:
            var15 = var9 == 0?ta:va;
            var2 = 0;
            var8 &= 255;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  var0[var7++] = var2;
                  break label257;
               }

               if(var15[var14][0] == var8) {
                  ++var2;
               }
            }
         case 57:
            var15 = var9 == 0?ta:va;
            var2 = 0;
            var14 = var15.length;

            while(true) {
               --var14;
               if(var14 < 0) {
                  var0[var7++] = var2;
                  break label257;
               }

               if(var15[var14][9] == var8) {
                  ++var2;
               }
            }
         case 65:
            var13[1] = 0;
            break;
         case 66:
            sB[var9][1] = 0;
            break;
         case 67:
            sB[var9][1] = 1;
            break;
         case 81:
            if(HG.ta) {
               var4 = false;
            } else {
               pb = false;
               sb = 34;
               if(rb) {
                  ga = var9;
               } else {
                  HG.sa[34][4] = var9;
                  ga = -1;
               }

               rb = true;
            }

            var5 = true;
            break;
         case 82:
            if(HG.ta) {
               var4 = false;
               var5 = true;
            } else {
               S();
            }
            break;
         case 83:
            T();
            break;
         case 84:
            uA[0] += var9;
            break;
         case 85:
            ++lb[4];
            gb[var9] = true;
            break;
         case 86:
            ib = (byte)var9;
            break;
         case 87:
            hb[var9] = (byte)var8;
            break;
         case 88:
            if(ta.length > 0) {
               int[] var3 = null;
               var2 = ta.length;

               while(true) {
                  --var2;
                  if(var2 < 0) {
                     if(var3 != null) {
                        var3[0] = da[var3[0]][24];
                        y[da[var3[0]][0]] = true;
                        G();
                     }
                     break;
                  }

                  int[] var1 = ta[var2];
                  if(da[var1[0]][4] == var9 && (var3 == null || var1[10] > var3[10])) {
                     var3 = var1;
                  }
               }
            }
            break;
         case 89:
            var9 &= 255;
            jb = var9;
            C(jb, true);
            y[da[jb][0]] = true;
            G();
         }

         if(var4) {
            var10 = 0;
         }
      } while(!var5);

      if(var6) {
         if(qb) {
            HG.A(false);
            HG.fb = true;
            pb = true;
            qb = false;
         }

         var0[0] = 0;
      } else {
         var0[3] = var11;
         var0[4] = var10;
         var0[5] = var9;
         var0[6] = var8;
      }

   }

   public static void S() {
      int var1 = 0;
      byte var0;
      if(eb <= mb) {
         ++lb[0];
         var0 = 2;
      } else if(eb <= nb) {
         ++lb[1];
         var0 = 1;
      } else {
         if(eb > ob) {
            T();
            return;
         }

         ++lb[2];
         var0 = 0;
      }

      lb[3] += eb;
      switch(ab) {
      case 0:
         var1 = 65630 + var0;
         break;
      case 1:
         var1 = 131139 + var0;
         break;
      case 2:
         var1 = 196715 + var0;
      }

      if(rb) {
         ga = var1;
         ha = var0 + 1;
      } else {
         HG.sa[35][7] = var0 + 1;
         HG.sa[35][8] = var1;
         sb = 35;
      }

      rb = true;
      if(bb < 11) {
         aa[ab * 11 + bb][3] = 1;
         if(HG.E(6 + ab) < bb) {
            HG.A(6 + ab, bb);
            HG.D();
         }
      }

      ++bb;
      if(var0 != 2 && (ab == 0 && bb == 7 || ab == 1 && bb == 9 || ab == 2 && bb == 9)) {
         ++bb;
      }

      int[][] var2 = db == 0?ta:va;

      for(var1 = 0; var1 < var2.length; ++var1) {
         int var3 = var2[var1][27];
         var2[var1][10] += var3;
         var2[var1][27] = 0;
      }

      zA |= 16;
      I();
   }

   public static void T() {
      int var0 = 0;
      switch(ab) {
      case 0:
         var0 = 65629;
         break;
      case 1:
         var0 = 131072;
         break;
      case 2:
         var0 = 196714;
      }

      if(rb) {
         ga = var0;
         ha = 0;
      } else {
         HG.sa[36][4] = var0;
         sb = 36;
      }

      rb = true;
      zA |= 16;
   }

   public static void A(int var0, int[] var1, int[] var2, int var3) {
      int var4;
      for(var4 = 0; var4 < 50 && lB[var4][9] != 0; ++var4) {
         ;
      }

      if(var4 < 50) {
         kB = true;
         lB[var4][0] = var0;
         lB[var4][1] = 0;
         lB[var4][2] = var1[1];
         lB[var4][3] = var1[2];
         lB[var4][4] = (lB[var4][2] - la) * 45 + -23 + offset_x;
       //lB[var4][4] = (lB[var4][2] - la) * 25 + -23;
         lB[var4][5] = (lB[var4][3] - ma) * 50 + ((lB[var4][2] & 1) == 1?25:0) + -16 + offset_y;
       //lB[var4][5] = (lB[var4][3] - ma) * 32 + ((lB[var4][2] & 1) == 1?16:0) + -16;
         lB[var4][6] = 0;
         lB[var4][7] = 0;
         lB[var4][8] = var1[0];
         lB[var4][9] = var3;
         switch(var0) {
         case 5:
         case 6:
         case 7:
         case 8:
            lB[var4][1] = A(0, HG.ob[34][1]);
            lB[var4][4] = A(lB[var4][4], lB[var4][4] - HG.ob[34][3] + 32);
            lB[var4][5] = A(lB[var4][5], lB[var4][5] - HG.ob[34][4] + 32);
            lB[var4][6] = var2[1] - var1[1];
            lB[var4][7] = var2[2] - var1[2];
            lB[var4][6] /= var0 != 5 && var0 != 6?9:9;
            lB[var4][7] /= var0 != 5 && var0 != 6?9:9;
            lB[var4][8] = pB[var1[5]];
         default:
            switch(var0) {
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
               break;
            case 2:
               lB[var4][1] = A(0, HG.ob[31][1]);
               lB[var4][4] = A(lB[var4][4] + 16 - HG.ob[31][3], lB[var4][4] + 16);
               lB[var4][5] = A(lB[var4][5] + 16 - HG.ob[31][4], lB[var4][5] + 16);
               break;
            case 3:
            case 4:
               lB[var4][4] = A(lB[var4][4], lB[var4][4] - HG.ob[32][3] + 32);
               lB[var4][5] = A(lB[var4][5], lB[var4][5] - HG.ob[32][4] + 32);
            }
         }
      }

   }

   public static void U() {
      kB = false;

      for(int var1 = 0; var1 < 50; ++var1) {
         if(lB[var1][9] > 0) {
            --lB[var1][9];
            kB = true;
            byte var0;
            int var2;
            switch(lB[var1][0]) {
            case 0:
            case 1:
            default:
               break;
            case 2:
               ++lB[var1][1];
               lB[var1][1] %= HG.ob[31][1];
               break;
            case 3:
               if(lB[var1][9] == 9) {
                  if(bA != null && da[bA[0]][4] == 1) {
                     if(!tb) {
                        tb = true;
                        HG.L(17);
                     }
                  } else if(!tb) {
                     tb = true;
                     HG.L(18);
                  }
               }

               if(HG.D(2) && lB[var1][9] == 9 && HG.D(2)) {
                  Display.getDisplay(a).vibrate(600);
               }

               ++lB[var1][1];
               lB[var1][1] %= HG.ob[32][1];
               if(lB[var1][9] == 9 && o == 20 && cA != null && bA != null) {
                  var2 = cA[15] >> 4 & 15;
                  if(var2 > 0) {
                     --bA[6];
                     --var2;
                     cA[15] &= -241;
                     cA[15] |= var2 << 4;
                  }
               }
               break;
            case 4:
               if(lB[var1][9] == 9) {
                  if(cA != null && da[cA[0]][4] == 1) {
                     if(!tb) {
                        tb = true;
                        HG.L(17);
                     }
                  } else if(!tb) {
                     tb = true;
                     HG.L(18);
                  }
               }

               if(HG.D(2) && lB[var1][9] == 9 && HG.D(2)) {
                  Display.getDisplay(a).vibrate(600);
               }

               ++lB[var1][1];
               lB[var1][1] %= HG.ob[32][1];
               if(lB[var1][9] == 9 && o == 20 && cA != null) {
                  var2 = cA[15] & 15;
                  if(var2 > 0) {
                     --cA[6];
                     --var2;
                     cA[15] &= -16;
                     cA[15] |= var2;
                  }
               }
               break;
            case 5:
            case 6:
               if(lB[var1][9] == 8) {
                  var0 = 16;
                  if(lB[var1][0] == 5 && bA != null && da[bA[0]][4] == 1) {
                     var0 = 15;
                  } else if(lB[var1][0] == 6 && cA != null && da[cA[0]][4] == 1) {
                     var0 = 15;
                  }

                  if(!tb) {
                     tb = true;
                     HG.L(var0);
                  }
               }

               ++lB[var1][1];
               lB[var1][1] %= HG.ob[35][1];
               lB[var1][1] += lB[var1][8] * HG.ob[35][1];
               lB[var1][4] += lB[var1][6];
               lB[var1][5] += lB[var1][7];
               break;
            case 7:
            case 8:
               if(lB[var1][9] == 8) {
                  var0 = 16;
                  if(lB[var1][0] == 7 && bA != null && da[bA[0]][4] == 1) {
                     var0 = 15;
                  } else if(lB[var1][0] == 8 && cA != null && da[cA[0]][4] == 1) {
                     var0 = 15;
                  }

                  if(!tb) {
                     tb = true;
                     HG.L(var0);
                  }
               }

               ++lB[var1][1];
               lB[var1][1] %= HG.ob[34][1];
               lB[var1][1] += lB[var1][8] * HG.ob[34][1];
               lB[var1][4] += lB[var1][6];
               lB[var1][5] += lB[var1][7];
            }
         } else {
            tb = false;
         }
      }

   }

   public static void G(Graphics var0) {
      var0.setClip(0, 0, f, g);

      for(int var1 = 0; var1 < 50; ++var1) {
         if(lB[var1][9] > 0) {
            switch(lB[var1][0]) {
            case 0:
               if(lB[var1][9] <= 5) {
                  A(var0, 30, 1, lB[var1][4], lB[var1][5]);
               }
               break;
            case 1:
               if(lB[var1][9] <= 5) {
                  A(var0, 30, 0, lB[var1][4], lB[var1][5]);
               }
               break;
            case 2:
               A(var0, 31, lB[var1][1], lB[var1][4], lB[var1][5]);
               break;
            case 3:
            case 4:
               if(lB[var1][9] <= 9) {
                  A(var0, 32, lB[var1][1], lB[var1][4], lB[var1][5]);
               }
               break;
            case 5:
            case 6:
               if(lB[var1][9] <= 8) {
                  A(var0, 35, lB[var1][1], lB[var1][4], lB[var1][5]);
               }
               break;
            case 7:
            case 8:
               if(lB[var1][9] <= 8) {
                  A(var0, 34, lB[var1][1], lB[var1][4], lB[var1][5]);
               }
            }
         }
      }

   }
//------ Рисование на картинке разных линий
   public static void H(Graphics var0) {
      if(lA[0] == 2) {
         var0.setClip(0, 0, f, g);

         for(int var5 = 0; var5 < 20; ++var5) {
            int var4 = var5 << 1;
            int var3 = var4 + 1;
            int var2 = mB[var4];
            int var1 = mB[var3];
            if(var2 < 0 || var1 > g) {
               var2 = mB[var4] = (w.nextInt() & '\uffff') % (f + g);
               var1 = mB[var3] = -((w.nextInt() & '\uffff') % g);
            }

            if(mA == 0) {
               var0.setColor(14737632);
               int var10001 = var2;
               int var10002 = var1;
               var2 += 2;
               var1 -= 4;
               var0.drawLine(var10001, var10002, var2, var1);
               var0.setColor(9474272);
               var10001 = var2;
               var10002 = var1;
               var2 += 3;
               var1 -= 6;
               var0.drawLine(var10001, var10002, var2, var1);
               var0.setColor(2105584);
               var10001 = var2;
               var10002 = var1;
               var2 += 4;
               var1 -= 8;
               var0.drawLine(var10001, var10002, var2, var1);
               mB[var4] -= 10;
               mB[var3] += 20;
            } else {
               var0.setColor(16777215);
               var0.drawLine(var2, var1 - 1, var2, var1 + 1);
               var0.drawLine(var2 - 1, var1, var2 + 1, var1);
               mB[var4] += (w.nextInt() & 3) - 4;
               mB[var3] += 3;
            }
         }
      }

   }

   static final void L(int var0) {
      xB = (xB + 1) % 8;
      wB[xB] = 889855;

      for(int var2 = 0; var2 < 8; ++var2) {
         byte var1;
         switch(var0) {
         case -6:
            var1 = 10;
            break;
         case -5:
            var1 = 12;
            break;
         case -2:
            var1 = 11;
            break;
         case -1:
            var1 = 9;
            break;
         case 49:
            var1 = 0;
            break;
         case 50:
            var1 = 1;
            break;
         case 51:
            var1 = 2;
            break;
         case 52:
            var1 = 3;
            break;
         case 53:
            var1 = 4;
            break;
         case 54:
            var1 = 5;
            break;
         case 55:
            var1 = 6;
            break;
         case 56:
            var1 = 7;
            break;
         case 57:
            var1 = 8;
            break;
         default:
            return;
         }

         wB[var2] = (wB[var2] << 4 ^ var1) & -536870913;
         int var3;
         boolean var4;
         switch(wB[var2]) {
         case -1811991727:
            for(var3 = 0; var3 < ta.length; ++var3) {
               ta[var3][7] = 0;
            }

            var4 = true;
            break;
         case -751745272:
            for(var3 = 0; var3 < aa.length; ++var3) {
               aa[var3][3] = 1;
            }

            for(var3 = 0; var3 < 3; ++var3) {
               HG.A(6 + var3, 10);
            }

            HG.D();
            var4 = true;
            break;
         case -751741578:
            ib = 50;

            for(var3 = 0; var3 < hb.length; ++var3) {
               hb[var3] = 50;
            }

            jb = 4;
            C(jb, true);
            y[da[jb][0]] = true;
            G();
            var4 = true;
            break;
         case -751598768:
            eb = mb;
            S();
            var4 = true;
            break;
         case -751598767:
            eb = nb;
            S();
            var4 = true;
            break;
         case -751598766:
            eb = ob;
            S();
            var4 = true;
            break;
         case -550297262:
            uA[db] += 10000;
            var4 = true;
            break;
         case -550297210:
            ++eb;
            var4 = true;
            break;
         case 287782176:
            for(var3 = 0; var3 < ta.length; ++var3) {
               ta[var3][6] = 1;
               ta[var3][11] = 0;
               ta[var3][12] = 0;
            }

            var4 = true;
            break;
         case 287782177:
            for(var3 = 0; var3 < va.length; ++var3) {
               va[var3][6] = 1;
               va[var3][11] = 0;
               va[var3][12] = 0;
            }

            var4 = true;
            break;
         case 319906342:
            if(fA != null) {
               fA[10] += 100;
            }

            var4 = true;
            break;
         case 320041810:
            zB ^= true;
            var4 = true;
            break;
         case 322196834:
            T();
            var4 = true;
            break;
         case 325149221:
            if(ac) {
               ac = false;
            } else {
               ac = true;
            }

            var4 = true;
            break;
         case 355885909:
            eb = mb;
            bb = 9;
            S();
            var4 = true;
            break;
         case 357725010:
            for(var3 = 0; var3 < ta.length; ++var3) {
               ta[var3][6] = 10;
               ta[var3][11] = da[ta[var3][0]][22];
               ta[var3][12] = da[ta[var3][0]][23];
            }

            for(var3 = 0; var3 < va.length; ++var3) {
               va[var3][6] = 10;
               va[var3][11] = da[va[var3][0]][22];
               va[var3][12] = da[va[var3][0]][23];
            }

            var4 = true;
            break;
         case 536032885:
            for(var3 = 0; var3 < ta.length; ++var3) {
               da[ta[var3][0]][18] = 25;
            }

            var4 = true;
            break;
         default:
            var4 = false;
         }

         if(var4) {
            if(HG.D(2) && HG.D(2)) {
               Display.getDisplay(a).vibrate(100);
            }

            for(var0 = 0; var0 < 8; ++var0) {
               wB[var0] = 889855;
            }

            return;
         }
      }

   }

   public C(HG var1) {
      a = var1;
      this.setFullScreenMode(true); //выставление полноэкранного режима
      f = this.getWidth();
      g = this.getHeight();
   }
//------ Вывод картинки на заставке
   public void paint(Graphics var1) {   //рисование картинки игры
      int var2;
      int var3;
      int var16;
      if(q) {
         int var6;
         int var7;
         int var8;
         int var14;
         if(j != null) {
            var8 = j.b;
            var7 = j.d;
            var6 = 0;
            String var5 = null;
            Image var4 = null;
            switch(var8) {
            case 12:
               var3 = h[30];
               var2 = h[31];
               var14 = h[32];
               if(l != null) {
                  var6 = g + l.getHeight() >> 1;
                  var4 = l;
               }
               break;
            default:
               var6 = g / 3;
               var3 = h[3];
               var2 = h[0];
               var14 = h[0];
               var5 = HG.H(29);
            }

            var1.setColor(var3);
            var1.fillRect(0, 0, f, g);
            switch(var8) {
            case 12:
               if(var4 != null) {
                  var1.drawImage(var4, f / 2, g / 2, 3);
               }
               break;
            case 16:
            case 18:
               if(HG.ob[7] != null) {
                  A(var1, 7, HG.na, f - HG.ob[7][3] >> 1, g / 3 - (HG.ob[7][3] >> 1));
                  var1.setClip(0, 0, f, g);
                  ++HG.na;
                  HG.na %= HG.ob[7][1];
               }
            }

            if(var5 != null) {
               A(var1, var5, f >> 1, g >> 1, 1, 1, 0, var14, h[6]);
            }

            var14 = f >> 1;
            var3 = (g - var6) / 2 + var6 + 8;
            var1.setColor(var2);
            var1.drawRect(var14 - 37, var3, 75, 8);
            var1.fillRect(var14 + 2 - 37, var3 + 2, 72 * var7 / 100, 5);
         } else if(HG.ta && HG.ua >= 0 && HG.ua < 33) {
            if(var1 != null && HG.ua <= 27) {
               int[][][] var15 = HG.ma;
               if(var15 != null && x[2] != null) {
                  int[][] var10 = var15[0]; //местность
                  int[][] var9 = var15[1];  //реки и дороги
                  var8 = var10[0].length;
                  var7 = var10.length;
                  var6 = 0;

                  for(int var17 = -25; var17 < g + 25; var17 += 50) { //y координаты  
                //for(int var17 = -16; var17 < g + 16; var17 += 32) {
                     var16 = 0;

                     for(var3 = -15; var3 < f + 15; var3 += 45) { //x координаты  
                   //for(var3 = -7; var3 < f + 7; var3 += 16) { вместо 16: 50
                        var2 = (var16 & 1) == 1?25:0;   
                      //var2 = (var16 & 1) == 1?16:0;
                        var16 %= var8;
                        var6 %= var7;
                        if((var14 = var10[var6][var16]) != -1) {
                           A(var1, 2, var14, var3, var17 + var2);   //вывод на карте местности
                        }

                        if((var14 = var9[var6][var16]) != -1) {
                           A(var1, 3, var14, var3, var17 + var2);   //вывод на карте рек и дорог
                        }

                        ++var16;
                     }

                     ++var6;
                  }
               } else {
                  var1.setColor(h[6]);
                  var1.fillRect(0, 0, f, g);
               }
            }
         } else {
            switch(o) {
            case 1:
               if(j != null) {
                  var16 = f >> 1;
                  var14 = i[1].getHeight();
                  var3 = j.d;
                  if(var3 > 100) {
                     var3 = 100;
                  }

                  var1.setClip(0, 0, f, g);

                  try {
                     var1.setColor(h[49 + m]);
                  } catch (Exception var13) {
                     var1.setColor(16777215);
                  }

                  var1.fillRect(0, 0, f, g);
                  if(l != null) {
                     var1.drawImage(l, f / 2, g / 2, 3);
                     var2 = (g + l.getHeight()) / 2;
                     var2 += (g - var2) / 2;
                     var1.setColor(0);

                     try {
                        var2 -= 3;
                        var1.drawString(HG.H(29), var16, var2 - var14, 17);    //вывод текста
                     } catch (Exception var12) {
                        ;
                     }

                     var2 += 8;
                     var1.drawRect(var16 - 37, var2, 75, 8);
                     var1.fillRect(var16 + 2 - 37, var2 + 2, 72 * var3 / 100, 5);
                  }
               }
               break;
            case 12:
               var1.setClip(0, 0, f, g);

               try {
                  var1.setColor(h[49 + m]);
               } catch (Exception var11) {
                  var1.setColor(16777215);
               }

               var1.fillRect(0, 0, f, g);
               if(l != null) {
                  var1.drawImage(l, f / 2, g / 2, 3);
               }
               break;
            case 17:
               if(c) {
                  E(var1);  //вывод стратегической карты
                  return;
               }

               B(var1, la, ma, -23, -16);   //местность на карту
               F(var1);
               if(!HG.fb) {
                A(var1, 0, (pa[db] - la) * 45, (qa[db] - ma) * 50 + ((pa[db] & 1) == 1?25:0), -23 + offset_x, -16 + offset_y);
                //A(var1, 0, (pa[db] - la) * 25, (qa[db] - ma) * 32 + ((pa[db] & 1) == 1?16:0), -23, -16);
               }

               A(var1, 3);  //вывод юнитов на карту
               H(var1); //разные линии на карту
               if(wb >= 0) {
                  var3 = (pa[db] - la) * 45 + -23 + offset_x;
                //var3 = (pa[db] - la) * 25 + -23;
                  var2 = (qa[db] - ma) * 50 + ((pa[db] & 1) == 1?25:0) + -16 + offset_y;
                //var2 = (qa[db] - ma) * 32 + ((pa[db] & 1) == 1?16:0) + -16;
                  for(var14 = 0; var14 < (ea[wb][2] == 4000?1:(ea[wb][2] == 6000?2:3)); ++var14) {
                     A(var1, 14, 7, var3 + 3 + var14 * HG.ob[14][3], var2 + 13);
                  }
               }

               if(tA) {
                  B(var1);
               }
               break;
            case 18:
               if(c) {
                  E(var1);  //вывод стратегической карты
                  return;
               }

               B(var1, la, ma, -23, -16);   //местность на карту
               F(var1);
               if(!HG.fb && !gA) {
                A(var1, 0, (pa[db] - la) * 45, (qa[db] - ma) * 50 + ((pa[db] & 1) == 1?25:0), -23 + offset_x, -16 + offset_y);//вывод курсора на экран с координатами Y, X и смещение от начала экрана -23, -16
                //A(var1, 0, (pa[db] - la) * 25, (qa[db] - ma) * 32 + ((pa[db] & 1) == 1?16:0), -23, -16);  
               }

               if(dA != null && !gA) {
                  A(var1);  //Вывод стрелки при построении маршрута перемещения юнита
               }

               A(var1, 3);    //вывод юнитов на карту
               D(var1);     //вывод флагов нации на карту
               if(kB) {
                  G(var1);
               }

               H(var1); //разные линии на карту
               A(var1, dA, fA, tA && !HG.fb?HG.ob[12][4]:0, 0);
               if(!HG.fb && !HG.ta && tA) {
                  B(var1);  //вывод нижней инфопанели
                  C(var1);  //вывод верхней инфопанели
               }
               break;
            case 20:
               if(c) {
                  E(var1);  //вывод стратегической карты
                  return;
               }

               B(var1, la, ma, -23, -16);   //местность на карту
               F(var1);
               A(var1, 3);  //вывод юнитов на карту
               if(p == 22 && HG.ob[7] != null) {
                  var1.setClip(0, 0, f, g);
                  A(var1, 7, HG.na, f - HG.ob[7][3], 32);
               }

               D(var1);     //вывод флагов нации на карту
               if(kB) {
                  G(var1);
               }

               H(var1); //разные линии на карту

               for(var14 = 0; var14 <= f / HG.ob[26][3]; ++var14) {
                  A(var1, 26, 1, var14 * HG.ob[26][3], 0);
               }

               A(var1, bA, cA, 0, 0);
               if(bA != null) {
                  A(var1, 6, 5, 5, 2);
                  A(var1, 9, 0, 5, 2);
                  A(var1, da[bA[0]][0], 3, 5, 2);   //гекс атакующий в верхней инфопанели во время боя
               }

               if(cA != null) {
                  A(var1, 6, 5, f - 32 - 35, 2);
                  A(var1, 9, 0, f - 32 - 35, 2);
                  A(var1, da[cA[0]][0], 0, f - 32 - 35, 2); //гекс защитник в верхней инфопанели во время боя
               }
               break;
            case 22:
               if(c) {
                  E(var1);  //вывод стратегической карты
                  return;
               }

               B(var1, la, ma, -23, -16);   //местность на карту
               F(var1);
               if(kB) {
                  G(var1);
               }

               A(var1, 3);  //вывод юнитов на карту
               D(var1);     //вывод флагов нации на карту
               H(var1); //разные линии на карту
               var1.setClip(0, 0, f, g);
               A(var1, 7, HG.na, f - HG.ob[7][3], 32);
            }
         }
      }

      if(c) {   //пауза в игре, вывод сообщений
         var16 = f >> 1;
         var3 = g >> 1;
         var2 = K(2);
         HG.A(var1, (String)null, 16, var3 - 3 * var2, f - 16, var3 + 3 * var2, 15, -1, false);
         var1.setClip(0, 0, f, g);
         A(var1, HG.H(78), var16, var3 - 2 * var2, 2, 17, 0, 0, 0);
         A(var1, HG.H(79), var16, var3, 2, 17, 0, 0, 0);
         A(var1, HG.H(327707), var16, var3 + 2 * var2, 2, 17, 0, 0, 0);
      } else {
         if(HG.ta) {
            HG.B(var1); //рисование пунктов меню
         }

         if(HG.fb) {
            HG.C(var1); //рисование курсора гексового меню
         }
      }

   }
//------ Переход по пунктам в игровом меню
   void V() {
      if(C(10)) {
         if(c) {
            W();    //в режиме паузы (выход из режима паузы)
         } else {
            X();    //пауза в игре (переход в режим паузы)
         }
      }

      if(!c) {      //в режиме паузы не переходить в пункты меню
         if(HG.fb) {    //если истина, значит открыть пункты меню
            if(C(33)) { //выбор меню
               HG.DA(0);
            } else if(C(34)) {
               HG.DA(1);
            } else if(C(35)) {
               HG.DA(2);
            } else if(C(36)) {
               HG.DA(3);
            } else if(C(37)) {
               HG.DA(4);
            } else if(C(38)) {
               HG.DA(5);
            } else if(C(26)) {  //переместится по меню вправо
               HG.T();
            } else if(C(25)) {  //переместится по меню влево
               HG.U();
            } else if(C(27)) {  //нажать цетральную кнопку (подтвердить)
               HG.V();
            } else if(C(28)) {
               if(HG.gb == 29) {//нажать кнопку отмена
                  HG.BA(40);
               } else if(HG.gb != 28) {
                  if(HG.gb != 0 && HG.gb != 9 && HG.gb != 6) {
                     if(HG.gb == 4 && dA != null && dA[7] == 0) {
                        HG.BA(18);
                     }
                  } else {
                     HG.ib.removeAllElements();
                  }

                  if(HG.gb <= 9) {
                     HG.A(false);
                  } else {
                     HG.A(true);
                  }
               }
            }
         } else {
            boolean var3;
            if(HG.ta) {
               if(C(14)) {
                  HG.M();   //перемещение по меню со скроллингом вниз
               }

               if(C(13)) {
                  HG.N();   //перемещение по меню со скроллингом вверх
               }
               if(C(26)) {
                  HG.M1();   //перемещение по меню со скроллингом вниз
               }

               if(C(25)) {
                  HG.N1();   //перемещение по меню со скроллингом вверх
               }
               var3 = false;
               if(C(32) && HG.va >= 0 && HG.sa[HG.ua][HG.va * 4 + 2] == 102) {
                  HG.Q();
                  var3 = true;
               }

               if(!var3 && C(17)) {
                  if(HG.lA != -1) { //выбор звука в игре
                     HG.T(HG.lA);   //настройки звука
                  } else {
                     HG.Q();
                  }
               }

               if(C(20)) {
                  if(HG.mA != -1) {
                     HG.T(HG.mA);
                  } else {
                     HG.S();
                  }
               }

               if(C(31)) {
                  switch(HG.ua) { //проверить переход, возможно старт сценария
                  case 29:
                     HG.T(36);
                     break;
                  case 30:
                     if(HG.sa[30][(HG.va << 2) + 2] == 120) {
                        fA = (db == 0?ta:va)[HG.sa[30][(HG.va << 2) + 3]];
                        HG.aA.push(new Integer(HG.ua));
                        HG.aA.push(new Integer(HG.va));
                        HG.aA.push(new Integer(HG.oa));
                        HG.AA(29);
                     }
                     break;
                  case 31:
                     if(HG.sa[31][(HG.va << 2) + 2] == 120) {
                        wa[0] = HG.sa[31][(HG.va << 2) + 3];
                        wa[9] = db == 0?ra:sa;
                        wa[13] = -1;
                        wa[19] = -1;
                        fA = wa;
                        HG.aA.push(new Integer(HG.ua));
                        HG.aA.push(new Integer(HG.va));
                        HG.aA.push(new Integer(HG.oa));
                        HG.AA(29);
                     }
                     break;
                  }
               }
            } else if(C(12) && o == 18) {
               if(dA != null && !gA) {
                  pa[db] = dA[1];
                  qa[db] = dA[2];
                  la = pa[db] - ((f - 25) / 45 + 2) / 2;
                  ma = qa[db] - (g / 50 + 1) / 2;
                  H();
                  dA = null;
                  A(db ^ 1, (int[])null);
                  A(db == 0?vA:wA, db == 0?xA:yA, db == 0?ta:va, (int[])null);    //определение видимой территории
               } else {
                  HG.aA.removeAllElements();
                  HG.AA(33);
                  HG.ta = true;
               }
            } else if(!gA || o != 18) {
               switch(o) {
               case 2:
               case 3:
               case 4:
               case 6:
               case 8:
               case 9:
                  if(C(2)) {
                     ia -= fa.getHeight();
                  }

                  if(C(1)) {
                     ia += fa.getHeight();
                  }
                  break;
               case 17:
               case 18:
                  var3 = false;
                  
                  if(C(5)) {    //нажата клавиша огонь //if(C(5)) {
                      
                     byte var2 = 0;
                     if(o == 17) {
                        var2 = 6;
                        if(vb[qa[db]][pa[db]] == 3) {
                           HG.eb[6][6] = 81;
                        } else {
                           HG.eb[6][6] = 18;
                        }
                     } else {
                        int[][] var1 = db == 0?ta:va;
                        if(dA != null) {
                           var2 = -1;
                           if(hB == 1) {
                              gA = true;
                              dA[17] = 0;
                              na = la;
                              oa = ma;
                              xa = dA[1];
                              ya = dA[2];
                              if(dA[18] == 1) {
                                 dA[18] = 0;
                              }

                              fA = A(pa[db], qa[db], 1, db == 0?2:1);
                           } else if(hB == 3) {
                              fA = A(pa[db], qa[db], 1, db == 0?2:1);
                              if(fA != null && fA[15] > 0) {
                                 if(da[dA[0]][4] == 15) {
                                    var2 = 9;
                                    if(dA[20] != 99) {
                                       HG.eb[26][10] = 81;
                                    } else {
                                       HG.eb[26][10] = 18;
                                    }

                                    if(dA[10] >= hA[2] && dA[20] != 99) {
                                       HG.eb[26][14] = 81;
                                    } else {
                                       HG.eb[26][14] = 18;
                                    }

                                    if(dA[10] >= hA[4] && dA[20] != 99) {
                                       HG.eb[26][18] = 81;
                                    } else {
                                       HG.eb[26][18] = 18;
                                    }
                                 } else {
                                    var2 = 5;
                                    if(dA[12] > 0) {
                                       HG.eb[5][6] = 81;
                                    } else {
                                       HG.eb[5][6] = 18;
                                    }
                                 }
                              } else {
                                 fA = A(pa[db], qa[db], 1, db == 0?1:2);
                                 if(fA == dA) {
                                    var2 = 4;
                                    if(dA[7] == 0) {
                                       HG.eb[4][0] = 393300;
                                       HG.eb[4][1] = 18;
                                    } else {
                                       HG.eb[4][0] = 393300;
                                       HG.eb[4][1] = 2;
                                    }
                                 }
                              }
                           }
                        }

                        if(var2 != 4 && var2 != 5) {
                           for(int var4 = 0; var4 < var1.length; ++var4) {
                              if(pa[db] == var1[var4][1] && qa[db] == var1[var4][2] && var1[var4][7] == 0 && var1[var4][18] == 0) {
                                 gA = false;
                                 var2 = -1;
                                 if(dA == var1[var4]) {
                                    var2 = 3;
                                    if((da[dA[0]][27] > 0 || dA[19] >= 0) && rA[2][dA[2]][dA[1]] != 10) {
                                       var2 = 7;
                                       if(dA[19] >= 0) {
                                          HG.eb[7][16] = 393309;
                                       } else {
                                          HG.eb[7][16] = 393312;
                                       }
                                    }

                                    int[] var5 = A(pa[db], qa[db], 2, 3);
                                    if(rA[2][dA[2]][dA[1]] == 11 && da[dA[0]][25] > 0 && D(dA[1], dA[2])) {
                                       var2 = 8;
                                       HG.eb[8][20] = 393313;
                                       HG.eb[8][21] = 29;
                                       HG.eb[8][22] = 81;
                                       HG.eb[8][23] = 14;
                                       if(da[dA[0]][27] < 0) {
                                          HG.eb[8][18] = 18;
                                       } else {
                                          HG.eb[8][18] = 81;
                                       }

                                       if(var5 != null) {
                                          HG.eb[8][22] = 18;
                                       } else {
                                          HG.eb[8][22] = 81;
                                       }
                                    } else if(dA[19] >= 0 && da[dA[0]][5] == 5 && rA[2][dA[2]][dA[1]] != 10) {
                                       var2 = 8;
                                       HG.eb[8][20] = 393310;
                                       HG.eb[8][21] = 29;
                                       HG.eb[8][22] = 81;
                                       HG.eb[8][23] = 14;
                                       if(da[dA[0]][27] < 0) {
                                          HG.eb[8][18] = 18;
                                       } else {
                                          HG.eb[8][18] = 81;
                                       }

                                       if(var5 != null) {
                                          HG.eb[8][22] = 18;
                                       } else {
                                          HG.eb[8][22] = 81;
                                          if(da[dA[19]][4] != 1 && (rA[2][dA[2]][dA[1]] != 11 || rA[2][dA[2]][dA[1]] == 11 && !D(dA[1], dA[2]))) {
                                             HG.eb[8][22] = 18;
                                          }
                                       }
                                    } else if(rA[2][dA[2]][dA[1]] == 8 && (da[dA[0]][26] >= 0 || dA[19] >= 0 && da[dA[19]][26] >= 0)) {
                                       var2 = 8;
                                       if(dA[19] >= 0) {
                                          HG.eb[8][20] = 393311;
                                       } else {
                                          HG.eb[8][20] = 393314;
                                       }

                                       HG.eb[8][21] = 30;
                                       HG.eb[8][22] = 81;
                                       HG.eb[8][23] = 15;
                                       if(da[dA[0]][27] < 0) {
                                          HG.eb[8][18] = 18;
                                       } else {
                                          HG.eb[8][18] = 81;
                                       }
                                    }

                                    if(dA[6] != 10 && uA[db] >= 10) {
                                       if((da[dA[0]][5] != 5 || rA[2][dA[2]][dA[1]] == 11) && !Q(dA)) {
                                          HG.eb[var2][14] = 81;
                                       } else {
                                          HG.eb[var2][14] = 18;
                                       }
                                    } else {
                                       HG.eb[var2][14] = 18;
                                    }

                                    if(((dA[11] < da[dA[0]][22] || da[dA[0]][22] <= 0) && da[dA[0]][22] != 0 || (dA[12] < da[dA[0]][23] || da[dA[0]][23] <= 0) && da[dA[0]][23] != 0) && uA[db] >= 5 * ib / 100) {
                                       if((da[dA[0]][5] != 5 || rA[2][dA[2]][dA[1]] == 11) && !Q(dA)) {
                                          HG.eb[var2][10] = 81;
                                       } else {
                                          HG.eb[var2][10] = 18;
                                       }
                                    } else {
                                       HG.eb[var2][10] = 18;
                                    }
                                 } else {
                                    if(dA != null && (da[var1[var4][0]][5] == 5 && da[dA[0]][5] != 5 || da[dA[0]][5] == 5 && da[var1[var4][0]][5] != 5) && hB == 1) {
                                       gA = true;
                                       if(dA[18] == 1) {
                                          dA[18] = 0;
                                       }
                                       break;
                                    }

                                    HG.L(13);
                                    dA = var1[var4];
                                    hB = 1;
                                    gA = false;
                                    gB = new int[pA * oA * 2];
                                    if(A(db ^ 1, dA)) {
                                       fA = A(dA[1], dA[2], 3, db == 0?2:1);
                                       if(fA != null) {
                                          hB = 3;
                                       }
                                    }
                                 }

                                 A(db == 0?vA:wA, db == 0?xA:yA, var1, dA);    //определение видимой территории
                                 break;
                              }
                           }
                        }
                     }
                     
                     if(var2 >= 0) {
                        fA = A(pa[db], qa[db], 1, 3);
                        if(fA != null && fA[23] == 0) {
                           fA = null;
                        }

                        HG.GA(var2);
                        HG.fb = true;
                     } else if(gA && dA != null) {
                        switch(da[dA[0]][5]) {
                        case 0:
                           HG.L(19);
                           break;
                        case 1:
                           HG.L(23);
                           break;
                        case 2:
                        case 3:
                           HG.L(20);
                           break;
                        case 4:
                        case 7:
                           HG.L(22);
                           break;
                        case 5:
                           HG.L(21);
                           break;
                        case 6:
                           switch(da[dA[0]][4]) {
                           case 5:
                           case 6:
                           case 7:
                              HG.L(23);
                           }
                        }
                     }

                     if(var2 == 0 && fA == null && dA == null) {
                        HG.eb[0][14] = 18;
                     } else {
                        HG.eb[0][14] = 81;
                     }
                  } else {
                     if(C(3)) {
                        buld = true;    //курсор влево
                        --pa[db];
                     } else if(C(1)) {  //курсор вверх
                        buld = true;
                        --qa[db];
                     } else if(C(4)) {  //курсор вправо
                        buld = true;
                        ++pa[db];
                     } else if(C(2)) {  //курсор вниз
                        buld = true;
                        ++qa[db];
                     } else if(C(6)) {
                        buld = true;
                        --pa[db];
                        if((pa[db] & 1) == 1) {
                           --qa[db];
                        }
                     } else if(C(9)) {
                        buld = true;
                        --pa[db];
                        if((pa[db] & 1) == 0) {
                           ++qa[db];
                        }
                     } else if(C(8)) {
                        buld = true;
                        ++pa[db];
                        if((pa[db] & 1) == 0) {
                           ++qa[db];
                        }
                     } else if(C(7)) {
                        buld = true;
                        ++pa[db];
                        if((pa[db] & 1) == 1) {
                           --qa[db];
                        }
                     } else if(C(29)) { //нажата кнопка LSoft
                        dA = null;
                        A(db ^ 1, (int[])null);
                        M();
                     } else if(C(31)) { //нажата кнопка
                        if(fA != null || dA != null) {
                           HG.fb = false;
                           HG.aA.removeAllElements();
                           HG.AA(29);
                           HG.ta = true;
                        }
                     } else if(C(30)) { //нажата кнопка
                        tA ^= true;
                     }
                     
                     
                     
                    //Перемещение гекса выбора по игровому полю
                     if(pa[db] < 2) {
                        pa[db] = 2;
                     } else if(pa[db] > oA - 2 - 1) {
                        pa[db] = oA - 2 - 1;
                     }

                     if(qa[db] < 2) {
                        if((pa[db] & 1) == 1) {
                           qa[db] = 1;
                        } else {
                           qa[db] = 2;
                        }
                     } else if(qa[db] > pA - 2 - 1) {
                        if((pa[db] & 1) == 1) {
                           qa[db] = pA - 2 - 1;
                        } else {
                           qa[db] = pA - 1 - 1;
                        }
                     }
                     //Смещение игрового поля
//                     if(pa[db] > la + ((f - 25) / 45 + 2 - 2)) {//вычитание и деление из ширины экрана 
//                     //(pa[db] > la + ((f - 16) / 25 + 2 - 1))
//                        ++la;   //прибавить смещение в гексах по оси x
//                        
//                     } else if(pa[db] < la + 2) {
//                        --la;   //убавить смещение в гексах по оси x
//                     }
//
//                     if(qa[db] > ma + (g / 50 + 1 - 3)) {//деление из высоты экрана
//                        ++ma;   //прибавить смещение в гексах по оси y
//                       
//                     } else if(qa[db] < ma + 2) {
//                        --ma;   //убавить смещение в гексах по оси y
//                     }

                     H();   //проверка смещения экрана на выход из размеров карты
                     if(o == 17) {
                        if(wb >= 0) {
                           fA = A(pa[db], qa[db], 1, 3);
                        } else if(fA != null) {
                           fA[1] = pa[db];  //координата X
                           fA[2] = qa[db];  //координата Y
                        }
                     } else if(buld) {  //} else if(var3) {
                        fA = A(pa[db], qa[db], 1, 3);
                        if(fA != null && fA[23] == 0) {
                           fA = null;
                        }

                        if(dA != null && dA[7] == 0 && u[4] != 20) {   //u[4] != 20 - если не нажата клавиша огонь
                            
                           A(dA, pa[db], qa[db]);  //построение пути перемещения: юнит, координата X, координата Y
                            
                        }
                        buld = false;   //временно
                     }
                  }
               }
            }
         }
      }

      if(yB != -20 && o > 13) { //клавиши
         L(yB);
         yB = -20;
      }

      E();  //обработка клавиш
   }

   public void hideNotify() {
      X();
   }

   public void showNotify() {}
//------ Выход из режима паузы
   public static void W() {
      c = false;    //вышел из режима паузы
      HG.K();
   }
//------ Переход в режим паузы
   public static void X() {
      if(o != 12 && o != 1) {
         HG.A(2, -1, 0, true);
         c = true;  //в режиме паузы
      }

      if(HG.D(0)) {
         HG.J();
      } else {
         HG.H();
      }

   }
//------ Игровой цикл прорисовки
   public void run() {
      while (b) { //for(; b; d = System.currentTimeMillis()) {
          
         this.V();  //исполнение событий программы
         if(!c) {   //не переходить в режиме паузы игры
            if(HG.ta) {    //триггер главного пункта меню
               HG.R(); //пусто
            } else if(!HG.fb) {
               C(); //выбор пунктов меню???
            }
         }

         if(HG.ea) {    //триггер звука
            HG.I(); //воспроизведение звука в игре
         }

         try {
            this.repaint(); //повтор прорисовки
            this.serviceRepaints();
         } catch (Exception var4) {
             ;
         }

         long var1 = System.currentTimeMillis() - d;
         if(var1 < 75L) {
            try {
               Thread.sleep(75L - var1);
            } catch (Exception var3) {
                ;
            }
         }
       d = System.currentTimeMillis();  
      }
//Конец игрового цикла
      for(int var_i = 0; var_i < s; ++var_i) {
         byte[] var5 = r[var_i];
         byte var6 = var5[0];
         int var7 = B(var6);
         if(t[var7] == 5) {
            t[var7] = 10;
         }
      }

   }
//------ Изменение размера экрана
   public void sizeChanged(int var1, int var2) {
      f = var1;
      g = var2;
   }
//------ Создание потоков
   public void G(int var1, int var2) {
      Runtime.getRuntime();
      HG.E();
      String var3 = System.getProperty("microedition.locale");
      if(var3 != null) {
         var3 = var3.toUpperCase();
      }

      HG.B(var3);
      int var4;
      switch(var1) {
      case 1:
         Thread.yield();
         j.d = 0;
         Thread.yield();
         j.d = 20;
         Thread.yield();

         for(var4 = 0; var4 < y.length; ++var4) {
            if(var4 >= 3 && var4 < 37) {
               y[var4] = true;
            } else {
               y[var4] = false;
            }
         }

         y[16] = true;
         y[17] = true;
         y[22] = true;
         y[2] = true;
         y[3] = true;
         G();
         j.d = 70;
         Thread.yield();
         K();
         A(aa.length, false);
         HG.ma = new int[][][]{rA[0], rA[1]};
         rA[0] = (int[][])null;
         rA[1] = (int[][])null;
         j.d = 90;
         Thread.yield();
         HG.G();
         HG.W();
         break;
      case 2:
      case 3:
      case 4:
      case 5:
         dA = null;
         fA = null;
         aA = new int[100][4];
         label223:
         switch(var1) {
         case 2:
            A(bb, false);
            if(zA != 3) {
               uA[1] = uA[0];
            }

            db = 0;
            eb = 1;
            var4 = hb.length;

            while(true) {
               --var4;
               if(var4 < 0) {
                  ib = 100;
                  jb = -1;
                  break label223;
               }

               hb[var4] = 100;
            }
         case 3:
            F(2 + ab);
            break;
         case 4:
            F(5);
            break;
         case 5:
            F(6);
         }

         zA &= 15;
         j.d = 30;
         Thread.yield();
         O();
         j.d = 50;
         Thread.yield();

         for(var4 = 37; var4 < y.length; ++var4) {
            y[var4] = false;
         }

         for(var2 = 0; var2 < va.length; ++var2) {
            y[da[va[var2][0]][0]] = true;
            var4 = va[var2][19];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
               if(da[var4][27] >= 0) {
                  y[da[da[var4][27]][0]] = true;
               }

               if(da[var4][25] >= 0) {
                  y[da[da[var4][25]][0]] = true;
               }

               if(da[var4][26] >= 0) {
                  y[da[da[var4][26]][0]] = true;
               }
            }

            var4 = da[va[var2][0]][27];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
            }

            var4 = da[va[var2][0]][26];
            if(var4 >= 0) {
               if(rA[2][va[var2][2]][va[var2][1]] == 10) {
                  va[var2][19] = va[var2][0];
                  va[var2][0] = var4;
               }

               y[da[var4][0]] = true;
            }

            var4 = da[va[var2][0]][25];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
            }
         }

         j.d = 55;
         Thread.yield();

         for(var2 = 0; var2 < ta.length; ++var2) {
            y[da[ta[var2][0]][0]] = true;
            var4 = ta[var2][19];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
               if(da[var4][27] >= 0) {
                  y[da[da[var4][27]][0]] = true;
               }

               if(da[var4][25] >= 0) {
                  y[da[da[var4][25]][0]] = true;
               }

               if(da[var4][26] >= 0) {
                  y[da[da[var4][26]][0]] = true;
               }
            }

            var4 = da[ta[var2][0]][27];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
            }

            var4 = da[ta[var2][0]][26];
            if(var4 >= 0) {
               if(rA[2][ta[var2][2]][ta[var2][1]] == 10) {
                  ta[var2][19] = ta[var2][0];
                  ta[var2][0] = var4;
               }

               y[da[var4][0]] = true;
            }

            var4 = da[ta[var2][0]][25];
            if(var4 >= 0) {
               y[da[var4][0]] = true;
            }
         }

         j.d = 60;
         Thread.yield();
//Отбор техники по критериям для показа в меню рекрутировать
         for(var2 = 0; var2 < da.length; ++var2) {
//Временно отключено условие, так как не видно некоторых юнитов при покупке
//            if((((ra == 2)?12:((ra == 6 || ra == 0)?10:11)) == ((da[var2][1] == 2)?12:((da[var2][1] == 6 || da[var2][1] == 0)?10:11)) || da[var2][1] == 9 || ((zA & 15) == 3 && ((sa == 2)?12:((sa == 6 || sa != 0)?10:11)) == ((da[var2][1] == 2)?12:((da[var2][1] == 6 || da[var2][1] == 0)?10:11)))) && cb >= da[var2][2]) {
          //if(((ra == 2?true:(ra != 6 && ra != 0?true:true)) == (da[var2][1] == 2?true:(da[var2][1] != 6 && da[var2][1] != 0?true:true)) || da[var2][1] == 9 || (zA & 15) == 3 && (sa == 2?true:(sa != 6 && sa != 0?true:true)) == (da[var2][1] == 2?true:(da[var2][1] != 6 && da[var2][1] != 0?true:true))) && cb >= da[var2][2]) {
               var4 = -1;
               switch(ab) {
               case 0:
               case 3:
                  var4 = da[var2][7];
                  break;
               case 1:
                  var4 = da[var2][8];
                  break;
               case 2:
                  var4 = da[var2][9];
                  break;
               }

               if(var4 != -1 && bb >= var4) {
                  y[da[var2][0]] = true;
                  var4 = da[var2][27];
                  if(var4 >= 0) {
                     y[da[var4][0]] = true;
                  }

                  var4 = da[var2][26];
                  if(var4 >= 0) {
                     y[da[var4][0]] = true;
                  }

                  var4 = da[var2][25];
                  if(var4 >= 0) {
                     y[da[var4][0]] = true;
                  }
               }
//            }
         }

         j.d = 65;
         Thread.yield();
         y[0] = false;
         y[1] = false;
         y[2] = false;
         y[sA] = true;
         y[3] = true;
         y[2] = true;
         y[3] = true;
         G();   //изображения юнитов
         j.d = 80;
         Thread.yield();
         if((zA & 15) == 3 && var1 != 5) {
            ta = new int[0][28];
            va = new int[0][28];
         }

         HG.H();
         HG.L(2);
         break;
      }

   }

}
