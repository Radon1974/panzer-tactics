import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Stack;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotFoundException;

public class HG extends MIDlet implements CommandListener, PlayerListener {

   public static char[] a = new char[]{'\n', ' ', ',', '?', '-', ':', ';', '/', '.', '!'};
   static int b = -1;
   static int c = 0;
   private static byte[] d = new byte[9];
   static short[] e = new short[78];
   static short[] f = new short[39];
   static short[] g = new short[13];
   static String[] h = new String[1];	//выбранные на карте названия городов, аэропортов...
   static String[] i = new String[]{"MAIN", "CAMPAIGN_1", "CAMPAIGN_2", "CAMPAIGN_3", "CAMPAIGN_4", "HELP", "INGAME"};
   static byte[] j = new byte[]{(byte)1, (byte)2, (byte)2, (byte)2, (byte)2, (byte)4, (byte)1};
   static boolean[] k = new boolean[i.length];
   static String[][] l = new String[i.length][];	//название меню, события, названия параметров, офицеры, местность, название техники
   static byte[] m;
   static byte n = 0;
   static String o = "";
   static String[][] p = (String[][])null;
   static String q = "";
   public static int[][] r = (int[][])null;
   public static Object[] s;
   public static int[][] t;
   public static byte[] u;
   public static int[] v;
   public static long[] w;
   public static short x = 100;
   public static short y = 100;
   public static final String[] z = new String[]{".mid", ".amr", ".wav", ".mp3", ".ott", ".mmf", ".spf"};
   public static final String[] aa = new String[]{"audio/midi", "audio/amr", "audio/x-wav", "audio/mpeg", "", "audio/mmf", ""};
   public static int ba = 0;
   public static B ca;
   static boolean da = true;
   static boolean ea = true;
   static boolean fa = true;
   static int ga = 0;
   public static int ha = 0;
   static boolean ia = false;
   static int ja = -1;
   static boolean ka = false;
   static long la = 0L;
   public static int[][][] ma = (int[][][])null;
   public static int na = 0;
   static int oa = 0;           //прокрутка меню
   static int pa = 0;
   public static int qa = 0;    //таймер (задержка между переключениями пунктами меню) 10..0
   public static int ra = 0;
   static int[][] sa = new int[][]{{-1, -1, 81, -1, 9, 12, 100, -1, 31, 16, 100, -1, 23, 20, 100, -1, 41, 44, 101, 33, 18, 24, 100, -1, 28, 3, 100, -1, 17, 5, 100, -1, 20, 4, 100, -1, 12, 2, 101, -1}, {-1, -1, 81, -1, 37, -1, 53, -1, 43, 11, 101, -1, 24, 12, 101, -1}, new int[0], {-1, -1, 81, -1, 34, -1, 50, -1, 36, 0, 102, 1, 38, 2, 102, 1, 42, -1, 50, -1, 27, 3, 102, 2, 25, 4, 102, 2, 14, -1, 50, -1, 27, 7, 102, 4, 25, 8, 102, 4, 13, -1, 50, -1, 27, 9, 102, 5, 25, 10, 102, 5, -1, 16, 67, 0, -1, 17, 66, 1}, {-1, -1, 81, -1, 20, -1, 53, -1, -1, -1, 80, -1, -1, -1, 90, -1, -1, 1, 199, -1}, {-1, -1, 81, -1, 17, -1, 53, -1, 0, 6, 100, -1, 15, 7, 100, -1, 19, 8, 100, -1, 11, 9, 100, -1}, {-1, -1, 81, -1, 0, -1, 53, -1, -1, -1, 80, -1, -1, -1, 90, -1, 327696, -1, 198, -1}, {-1, -1, 81, -1, 15, -1, 53, -1, -1, -1, 80, -1, -1, -1, 90, -1, 327680, -1, 198, -1}, {-1, -1, 81, -1, 19, -1, 53, -1, -1, -1, 80, -1, -1, -1, 90, -1, 327695, 773, 188, -1, -1, -1, 196, 10, 327691, 1035, 188, -1, -1, -1, 196, 10, 327694, 256, 188, -1, -1, -1, 196, 10, 327681, 591879, 189, -1, -1, -1, 196, 10, 327689, 16712450, 189, -1, -1, -1, 196, 10, 327690, 16711936, 189, -1, -1, -1, 196, 10, 327693, 394500, 189, -1, -1, -1, 196, 10, 327682, 1, 188, -1, -1, -1, 196, 10, 327692, 522, 188, -1, -1, -1, 196, 10, 327686, '\uff02', 188, -1, -1, -1, 196, 10, 327684, '\uff07', 188, -1, -1, -1, 196, 10, 327683, '\uff0c', 188, -1, -1, -1, 196, 10, 327685, '\uff03', 188, -1, -1, -1, 196, 10, 327688, '\uff08', 188, -1, -1, -1, 196, 10, 327687, '\uff0d', 188, -1}, {-1, -1, 81, -1, 11, -1, 53, -1, -1, -1, 80, -1, -1, -1, 90, -1, -1, 2, 199, -1}, {-1, -1, 81, -1, 22, -1, 50, -1, 77, -1, 50, -1}, {-1, -1, 81, -1, 30, 3, 101, -1, 28, 3, 100, -1, 20, 4, 100, -1, 12, 4, 101, -1}, {-1, -1, 81, -1, 9, -1, 53, -1, 7, 41, 101, 0, 39, 42, 101, 1, 6, 43, 101, 2}, {-1, -1, 81, -1, 7, -1, 53, -1, 10, 28, 101, 0, 40, 1, 101, 0}, {-1, -1, 81, -1, 39, -1, 53, -1, 10, 28, 101, 1, 40, 1, 101, 1}, {-1, -1, 81, -1, 6, -1, 53, -1, 10, 28, 101, 2, 40, 1, 101, 2}, {-1, -1, 81, -1, 31, -1, 53, -1, 10, 29, 101, 0, 7, 17, 100, 0, 39, 18, 100, 0, 6, 19, 100, 0}, {-1, -1, 81, -1, 7, -1, 53, -1, 55, 38, 107, 0, 58, 38, 107, 1, 59, 38, 107, 2, 60, 38, 107, 3, 61, 38, 107, 4, 62, 38, 107, 5, 63, 38, 107, 6, 57, 38, 107, 7, 64, 38, 107, 8, 65, 38, 107, 9, 56, 38, 107, 10}, {-1, -1, 81, -1, 39, -1, 53, -1, 66, 38, 107, 11, 69, 38, 107, 12, 70, 38, 107, 13, 71, 38, 107, 14, 72, 38, 107, 15, 73, 38, 107, 16, 74, 38, 107, 17, 75, 38, 107, 18, 76, 38, 107, 19, 67, 38, 107, 20, 68, 38, 107, 21}, {-1, -1, 81, -1, 6, -1, 53, -1, 44, 38, 107, 22, 47, 38, 107, 23, 48, 38, 107, 24, 49, 38, 107, 25, 50, 38, 107, 26, 51, 38, 107, 27, 52, 38, 107, 28, 53, 38, 107, 29, 54, 38, 107, 30, 45, 38, 107, 31, 46, 38, 107, 32}, {-1, -1, 81, -1, 23, -1, 53, -1, 10, 30, 101, 0, 7, 21, 100, 0, 39, 22, 100, 0, 6, 23, 100, 0}, {-1, -1, 81, -1, 7, -1, 53, -1, 55, 39, 101, 0, 58, 39, 101, 1, 59, 39, 101, 2, 60, 39, 101, 3, 61, 39, 101, 4, 62, 39, 101, 5, 63, 39, 101, 6, 57, 39, 101, 7, 64, 39, 101, 8, 65, 39, 101, 9, 56, 39, 101, 10}, {-1, -1, 81, -1, 39, -1, 53, -1, 66, 39, 101, 11, 69, 39, 101, 12, 70, 39, 101, 13, 71, 39, 101, 14, 72, 39, 101, 15, 73, 39, 101, 16, 74, 39, 101, 17, 75, 39, 101, 18, 76, 39, 101, 19, 67, 39, 101, 20, 68, 39, 101, 21}, {-1, -1, 81, -1, 6, -1, 53, -1, 44, 39, 101, 22, 47, 39, 101, 23, 48, 39, 101, 24, 49, 39, 101, 25, 50, 39, 101, 26, 51, 39, 101, 27, 52, 39, 101, 28, 53, 39, 101, 29, 54, 39, 101, 30, 45, 39, 101, 31, 46, 39, 101, 32}, {-1, -1, 81, -1, 18, -1, 53, -1, 7, 34, 101, 0, 39, 34, 101, 1, 6, 34, 101, 2}, {-1, -1, 81, -1, 18, -1, 53, -1, -1, -1, 90, -1, -1, -1, 80, -1, 83, -1, 198, 256}, {-1, -1, 81, -1, 18, -1, 53, -1, -1, -1, 90, -1, -1, -1, 80, -1, 87, -1, 198, 256, -1, -1, 196, 10, -1, 0, 195, 3, -1, 0, 195, 2, -1, 0, 195, 1, -1, -1, 196, 20, -1, 6, 199, -1, -1, -1, 196, 20, -1, 7, 199, -1}, {-1, -1, 81, -1}, {18, -1, 92, -1, -1, -1, 190, -1, -1, -1, 190, -1, -1, -1, 190, -1, 87, -1, 198, 256, -1, -1, 196, 10, -1, 0, 195, 3, -1, 0, 195, 2, -1, 0, 195, 1, -1, -1, 196, 20, -1, 6, 199, -1, -1, -1, 196, 20, -1, 7, 199, -1, -1, -1, 196, 20, 84, -1, 198, 256, -1, 33, 67, 0, -1, 4, 66, 2}, {393319, -1, 92, -1, -1, -1, 194, -1, -1, 36, 67, 0, -1, 37, 66, 2}, new int[0], new int[0], new int[0], {-1, -1, 81, -1, 10, 24, 101, -1, 2, 4, 101, -1, 81, 31, 101, -1, 28, 3, 100, -1, 17, 5, 100, -1, 20, 4, 100, -1, -1, 24, 66, 2}, {-1, 28, 91, -1, -1, -1, 198, -1, -1, 35, 67, 0, -1, 35, 66, 2}, {-1, 28, 91, -1, -1, -1, 191, 0, -1, -1, 198, -1, -1, 26, 67, 0, -1, 22, 66, 2}, {-1, 28, 91, -1, -1, -1, 198, -1, -1, 40, 67, 0, -1, 40, 66, 1}, {393319, -1, 92, -1, 393334, -1, 198, 256, -1, -1, 196, 10, -1, 0, 192, 3, -1, 0, 192, 2, -1, 0, 192, 1, -1, -1, 196, 20, 393262, -1, 198, 256, -1, -1, 196, 10, 393269, -1, 198, 0, 0, 0, 193, -1, 0, -1, 198, 0, -1, -1, 197, 10, 393270, -1, 198, 0, 0, 0, 193, -1, 0, -1, 198, 0, -1, -1, 197, 10, 393264, -1, 198, 0, 0, 0, 193, -1, 0, -1, 198, 0, -1, 35, 67, 0, -1, 35, 66, 2}, {-1, 28, 91, -1, 393328, -1, 198, -1, -1, 32, 67, 0, -1, 32, 66, 1}, {-1, 28, 91, -1, 393293, -1, 198, -1, -1, 32, 67, 0, -1, 32, 66, 1}};
   static boolean ta = false;   //триггер меню в виде списка (главное меню, рекрутирование, офицеры, подразделения...)
   static int ua = -1;          //номер меню
   static int va = 0;           //номер пункта в меню (главное меню, рекрутирование, офицеры, подразделения...)
   static int wa = 0;           //количество пунктов в меню сценариев
   static int xa = 0;           //количество пунктов в главном меню
   static String[] ya = null;   //наименование пунктов меню
   static boolean za = false;
   static Stack aA = new Stack();   //стек пунктов меню
   static int bA = 0;
   static int cA = 0;
   static int dA = 0;
   static int eA = 0;
   static int fA = 0;
   static int gA = 0;
   static int hA = 0;
   static int iA = 0;   //прокрутка в тексте
   static int jA = 0;
   static int kA = 0;   //прокрутка в тексте
   static int lA = -1;
   static int mA = -1;
   static int nA = -1;
   static int oA = -1;
   static String pA = null;
   static String qA = null;                 //название кнопки меню
   static String[] rA = null;               //название пунктов меню
   static String[][] sA = (String[][])null; //вывод меню
   static int tA = 0;
   static byte[] uA;
   static int vA = 0;
   static int wA = 0;
   static int xA = 0;
   static int yA = 0;
   static int zA = 0;
   static int ab = 0;   //открытое или закрытое меню
   static int bb = 0;   //при покупке офицеров
   static int cb = 0;
   static int db = 0;
   static int[][] eb = new int[][]{{393316, 33, 84, 22, 393306, 3, 81, 4, 393307, 1, 80, 0, 393308, 21, 81, 1, 393323, 28, 81, 2, 393300, 2, 81, 3, -1, -1, 17, -1}, {393301, 10, 81, 8, 393300, 0, 80, 3, 393317, 31, 84, 5, 393324, 30, 84, 6, 393302, 32, 84, 7, 393319, 37, 84, 11, -1, -1, 17, -1}, new int[0], {393304, 13, 81, 3, 393325, 14, 81, 10, 393322, 15, 81, 20, 393318, 16, 81, 18, -1, -1, 17, -1}, {393300, 18, 81, 3, 393305, 17, 81, 10, 393308, 21, 81, 1, 393323, 28, 81, 2, -1, -1, 17, -1}, {393300, 2, 81, 3, 393299, 19, 81, 12, 393308, 21, 81, 1, 393323, 28, 81, 2, 393305, 17, 81, 10, -1, -1, 17, -1}, {393300, 2, 81, 3, 393303, 22, 81, 19, -1, -1, 17, -1}, {393304, 13, 81, 3, 393325, 14, 81, 10, 393322, 15, 81, 20, 393318, 16, 81, 18, 393312, 24, 81, 16, -1, -1, 17, -1}, {393304, 13, 81, 3, 393325, 14, 81, 10, 393322, 15, 81, 20, 393318, 16, 81, 18, 393312, 24, 81, 16, 393313, 29, 81, 14, -1, -1, 17, -1}, {393300, 2, 81, 3, 393299, 26, 80, 12, 393308, 21, 81, 1, 393323, 28, 81, 2, 393305, 17, 81, 10, -1, -1, 17, -1}, new int[0], new int[0], new int[0], new int[0], {393317, 20, 10, 3, 393331, -1, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393324, 25, 10, 3, 393261, -1, 14, 3, -1, 8, 15, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393302, 26, 10, 3, 393275, -1, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393324, 27, 10, 3, 393260, -1, 14, 3, -1, 9, 15, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393328, 2, 10, 5, 393296, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {-1, 2, 10, 5, 393326, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {-1, 2, 10, 5, 393326, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393300, 18, 81, 3, 393305, 17, 81, 10, 393314, 30, 81, 15, -1, -1, 17, -1}, {393328, 2, 10, 5, 393333, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393297, 2, 10, 5, -1, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393328, -1, 10, 5, -1, -1, 14, 3, -1, -1, 12, 2}, {-1, -1, 10, 3, -1, -1, 14, 3, -1, -1, 13, 0, -1, -1, 12, 1}, {393300, 2, 81, 3, 393299, 19, 81, 12, 393315, 31, 81, 17, 393320, 32, 81, 21, 393298, 33, 81, 13, 393305, 17, 81, 10, -1, -1, 17, -1}, {-1, 39, 84, 22, 393328, 2, 10, 3, 393290, 2, 14, 3, -1, -1, 13, 0, -1, -1, 12, 2}, {393328, 34, 10, 5, 393292, 34, 14, 3, -1, 34, 13, 0}, {393328, 35, 10, 5, 88, -1, 14, 3, -1, 35, 13, 0, -1, 40, 12, 1}, {393328, 41, 10, 3, 393291, -1, 14, 3, -1, -1, 12, 2}, {393328, 41, 10, 3, 393329, -1, 14, 3, -1, -1, 12, 2}, {393328, 41, 10, 3, 393330, -1, 14, 3, -1, -1, 12, 2}, {393328, 41, 10, 3, 393276, -1, 14, 3, -1, -1, 12, 2}};  //заголовок сообщения (например: Внимание)
   static boolean fb = false;   //сообщения
   static int gb = -1;
   static int hb = 0;
   static Stack ib = new Stack();
   static String[] jb = null;
   static String[][] kb = (String[][])null;
   static int lb = 0;
   static int mb = 0;
   public static String nb;
   //формат переменной "ob": 100 - имя рисунка, 9 - количество столбцов, 4 - количество строк, 0 - ?, 0 - ?
   static short[][] ob = new short[][]{{(short)100, (short)9, (short)4, (short)0, (short)0}, {(short)200, (short)9, (short)4, (short)0, (short)0}, {(short)300, (short)9, (short)4, (short)0, (short)0}, {(short)400, (short)10, (short)6, (short)0, (short)0}, {(short)804, (short)7, (short)3, (short)0, (short)0}, {(short)805, (short)1, (short)10, (short)0, (short)0}, {(short)806, (short)1, (short)6, (short)0, (short)0}, {(short)807, (short)6, (short)1, (short)0, (short)0}, {(short)808, (short)4, (short)1, (short)0, (short)0}, {(short)809, (short)2, (short)1, (short)0, (short)0}, {(short)810, (short)6, (short)4, (short)0, (short)0}, {(short)811, (short)12, (short)3, (short)0, (short)0}, {(short)812, (short)4, (short)1, (short)0, (short)0}, {(short)813, (short)12, (short)1, (short)0, (short)0}, {(short)814, (short)8, (short)1, (short)0, (short)0}, {(short)815, (short)1, (short)1, (short)0, (short)0}, {(short)816, (short)16, (short)1, (short)0, (short)0}, {(short)817, (short)5, (short)1, (short)0, (short)0}, {(short)818, (short)3, (short)6, (short)0, (short)0}, {(short)819, (short)5, (short)1, (short)0, (short)0}, {(short)835, (short)1, (short)1, (short)0, (short)0}, {(short)837, (short)1, (short)1, (short)0, (short)0}, {(short)820, (short)10, (short)1, (short)0, (short)0}, {(short)821, (short)9, (short)2, (short)0, (short)0}, {(short)822, (short)1, (short)1, (short)0, (short)0}, {(short)823, (short)1, (short)1, (short)0, (short)0}, {(short)824, (short)1, (short)3, (short)0, (short)0}, {(short)825, (short)3, (short)1, (short)0, (short)0}, {(short)826, (short)3, (short)1, (short)0, (short)0}, {(short)827, (short)1, (short)1, (short)0, (short)0}, {(short)828, (short)2, (short)1, (short)0, (short)0}, {(short)829, (short)8, (short)1, (short)0, (short)0}, {(short)830, (short)9, (short)1, (short)0, (short)0}, {(short)831, (short)2, (short)1, (short)0, (short)0}, {(short)832, (short)8, (short)2, (short)0, (short)0}, {(short)833, (short)8, (short)2, (short)0, (short)0}, {(short)834, (short)2, (short)8, (short)0, (short)0}, {(short)500, (short)2, (short)1, (short)0, (short)0}, {(short)501, (short)2, (short)1, (short)0, (short)0}, {(short)502, (short)2, (short)1, (short)0, (short)0}, {(short)503, (short)2, (short)1, (short)0, (short)0}, {(short)504, (short)2, (short)1, (short)0, (short)0}, {(short)505, (short)2, (short)1, (short)0, (short)0}, {(short)506, (short)2, (short)1, (short)0, (short)0}, {(short)507, (short)2, (short)1, (short)0, (short)0}, {(short)508, (short)2, (short)1, (short)0, (short)0}, {(short)509, (short)2, (short)1, (short)0, (short)0}, {(short)510, (short)2, (short)1, (short)0, (short)0}, {(short)511, (short)2, (short)1, (short)0, (short)0}, {(short)512, (short)2, (short)1, (short)0, (short)0}, {(short)513, (short)2, (short)1, (short)0, (short)0}, {(short)514, (short)2, (short)1, (short)0, (short)0}, {(short)515, (short)2, (short)1, (short)0, (short)0}, {(short)516, (short)2, (short)1, (short)0, (short)0}, {(short)517, (short)2, (short)1, (short)0, (short)0}, {(short)518, (short)2, (short)1, (short)0, (short)0}, {(short)519, (short)2, (short)1, (short)0, (short)0}, {(short)520, (short)2, (short)1, (short)0, (short)0}, {(short)521, (short)2, (short)1, (short)0, (short)0}, {(short)522, (short)2, (short)1, (short)0, (short)0}, {(short)523, (short)2, (short)1, (short)0, (short)0}, {(short)524, (short)2, (short)1, (short)0, (short)0}, {(short)525, (short)2, (short)1, (short)0, (short)0}, {(short)526, (short)2, (short)1, (short)0, (short)0}, {(short)527, (short)2, (short)1, (short)0, (short)0}, {(short)528, (short)2, (short)1, (short)0, (short)0}, {(short)529, (short)2, (short)1, (short)0, (short)0}, {(short)530, (short)2, (short)1, (short)0, (short)0}, {(short)531, (short)2, (short)1, (short)0, (short)0}, {(short)532, (short)2, (short)1, (short)0, (short)0}, {(short)533, (short)2, (short)1, (short)0, (short)0}, {(short)534, (short)2, (short)1, (short)0, (short)0}, {(short)535, (short)2, (short)1, (short)0, (short)0}, {(short)536, (short)2, (short)1, (short)0, (short)0}, {(short)537, (short)2, (short)1, (short)0, (short)0}, {(short)538, (short)2, (short)1, (short)0, (short)0}, {(short)539, (short)2, (short)1, (short)0, (short)0}, {(short)540, (short)2, (short)1, (short)0, (short)0}, {(short)541, (short)2, (short)1, (short)0, (short)0}, {(short)542, (short)2, (short)1, (short)0, (short)0}, {(short)543, (short)2, (short)1, (short)0, (short)0}, {(short)544, (short)2, (short)1, (short)0, (short)0}, {(short)545, (short)2, (short)1, (short)0, (short)0}, {(short)546, (short)2, (short)1, (short)0, (short)0}, {(short)547, (short)2, (short)1, (short)0, (short)0}, {(short)548, (short)2, (short)1, (short)0, (short)0}, {(short)549, (short)2, (short)1, (short)0, (short)0}, {(short)550, (short)2, (short)1, (short)0, (short)0}, {(short)551, (short)2, (short)1, (short)0, (short)0}, {(short)552, (short)2, (short)1, (short)0, (short)0}, {(short)553, (short)2, (short)1, (short)0, (short)0}, {(short)554, (short)2, (short)1, (short)0, (short)0}, {(short)555, (short)2, (short)1, (short)0, (short)0}, {(short)556, (short)2, (short)1, (short)0, (short)0}, {(short)557, (short)2, (short)1, (short)0, (short)0}, {(short)558, (short)2, (short)1, (short)0, (short)0}, {(short)559, (short)2, (short)1, (short)0, (short)0}, {(short)560, (short)2, (short)1, (short)0, (short)0}, {(short)561, (short)2, (short)1, (short)0, (short)0}, {(short)562, (short)2, (short)1, (short)0, (short)0}, {(short)563, (short)2, (short)1, (short)0, (short)0}, {(short)564, (short)2, (short)1, (short)0, (short)0}, {(short)565, (short)2, (short)1, (short)0, (short)0}, {(short)566, (short)2, (short)1, (short)0, (short)0}, {(short)567, (short)2, (short)1, (short)0, (short)0}, {(short)568, (short)2, (short)1, (short)0, (short)0}, {(short)569, (short)2, (short)1, (short)0, (short)0}, {(short)570, (short)2, (short)1, (short)0, (short)0}, {(short)571, (short)2, (short)1, (short)0, (short)0}, {(short)572, (short)2, (short)1, (short)0, (short)0}, {(short)573, (short)2, (short)1, (short)0, (short)0}, {(short)574, (short)2, (short)1, (short)0, (short)0}, {(short)575, (short)2, (short)1, (short)0, (short)0}, {(short)576, (short)2, (short)1, (short)0, (short)0}, {(short)577, (short)2, (short)1, (short)0, (short)0}, {(short)578, (short)2, (short)1, (short)0, (short)0}, {(short)579, (short)2, (short)1, (short)0, (short)0}, {(short)580, (short)2, (short)1, (short)0, (short)0}, {(short)581, (short)2, (short)1, (short)0, (short)0}, {(short)582, (short)2, (short)1, (short)0, (short)0}, {(short)583, (short)2, (short)1, (short)0, (short)0}, {(short)584, (short)2, (short)1, (short)0, (short)0}, {(short)585, (short)2, (short)1, (short)0, (short)0}, {(short)586, (short)2, (short)1, (short)0, (short)0}, {(short)587, (short)2, (short)1, (short)0, (short)0}, {(short)588, (short)2, (short)1, (short)0, (short)0}, {(short)589, (short)2, (short)1, (short)0, (short)0}, {(short)590, (short)2, (short)1, (short)0, (short)0}, {(short)591, (short)2, (short)1, (short)0, (short)0}, {(short)592, (short)2, (short)1, (short)0, (short)0}, {(short)593, (short)2, (short)1, (short)0, (short)0}, {(short)594, (short)2, (short)1, (short)0, (short)0}, {(short)595, (short)2, (short)1, (short)0, (short)0}, {(short)596, (short)2, (short)1, (short)0, (short)0}, {(short)597, (short)2, (short)1, (short)0, (short)0}, {(short)598, (short)2, (short)1, (short)0, (short)0}, {(short)599, (short)2, (short)1, (short)0, (short)0}, {(short)600, (short)2, (short)1, (short)0, (short)0}, {(short)601, (short)2, (short)1, (short)0, (short)0}, {(short)602, (short)2, (short)1, (short)0, (short)0}, {(short)603, (short)2, (short)1, (short)0, (short)0}, {(short)604, (short)2, (short)1, (short)0, (short)0}, {(short)605, (short)2, (short)1, (short)0, (short)0}, {(short)606, (short)2, (short)1, (short)0, (short)0}, {(short)607, (short)2, (short)1, (short)0, (short)0}, {(short)608, (short)2, (short)1, (short)0, (short)0}, {(short)609, (short)2, (short)1, (short)0, (short)0}, {(short)610, (short)2, (short)1, (short)0, (short)0}, {(short)611, (short)2, (short)1, (short)0, (short)0}, {(short)612, (short)2, (short)1, (short)0, (short)0}, {(short)613, (short)2, (short)1, (short)0, (short)0}, {(short)614, (short)2, (short)1, (short)0, (short)0}, {(short)615, (short)2, (short)1, (short)0, (short)0}, {(short)616, (short)2, (short)1, (short)0, (short)0}, {(short)617, (short)2, (short)1, (short)0, (short)0}, {(short)618, (short)2, (short)1, (short)0, (short)0}, {(short)619, (short)2, (short)1, (short)0, (short)0}, {(short)620, (short)2, (short)1, (short)0, (short)0}, {(short)621, (short)2, (short)1, (short)0, (short)0}, {(short)622, (short)2, (short)1, (short)0, (short)0}, {(short)623, (short)2, (short)1, (short)0, (short)0}, {(short)624, (short)2, (short)1, (short)0, (short)0}, {(short)625, (short)2, (short)1, (short)0, (short)0}, {(short)626, (short)2, (short)1, (short)0, (short)0}, {(short)627, (short)2, (short)1, (short)0, (short)0}, {(short)628, (short)2, (short)1, (short)0, (short)0}, {(short)629, (short)2, (short)1, (short)0, (short)0}, {(short)630, (short)2, (short)1, (short)0, (short)0}, {(short)631, (short)2, (short)1, (short)0, (short)0}, {(short)632, (short)2, (short)1, (short)0, (short)0}, {(short)633, (short)2, (short)1, (short)0, (short)0}, {(short)634, (short)2, (short)1, (short)0, (short)0}, {(short)635, (short)2, (short)1, (short)0, (short)0}, {(short)636, (short)2, (short)1, (short)0, (short)0}, {(short)637, (short)2, (short)1, (short)0, (short)0}, {(short)638, (short)2, (short)1, (short)0, (short)0}, {(short)639, (short)2, (short)1, (short)0, (short)0}, {(short)640, (short)2, (short)1, (short)0, (short)0}, {(short)641, (short)2, (short)1, (short)0, (short)0}, {(short)642, (short)2, (short)1, (short)0, (short)0}};
   static C pb = null;
   static HG qb = null;
   static int cross_x = 0;
   static int cross_y = 0;
   static int checkmark_x = 0;
   static int checkmark_y = 0;
   static boolean popup_menu = false;   //триггер всплывающего меню

   public static InputStream A(String var0) throws NullPointerException {
      DataInputStream var1 = null;

      try {
         InputStream var3 = qb.getClass().getResourceAsStream(var0);
         if(var3 != null) {
            var1 = new DataInputStream(var3);
         }
      } catch (Exception var2) {
         ;
      }

      if(var1 == null) {
         throw new NullPointerException();
      } else {
         return var1;
      }
   }

   public static String[] A(String var0, Font var1, int var2, int var3) {
      var0.length();
      int var11 = 0;
      int var10 = 0;
      String var9 = "";
      String var8 = "";
      if(var3 > 0) {
         var2 -= var3 * var1.charWidth(' ');
      }

      while(var0.length() > 0) {
         int var4 = var0.length();

         int var5;
         int var6;
         for(var6 = 0; var6 < a.length; ++var6) {
            var5 = var0.indexOf(a[var6]);
            if(var5 < var4 && var5 != -1) {
               var4 = var5;
            }
         }

         String var7;
         if(var4 == var0.length()) {
            var7 = var0.substring(0, var4);
            var0 = "";
         } else {
            var7 = var0.substring(0, var4 + 1);
            var0 = var0.substring(var4 + 1, var0.length());
         }

         if(var7.length() <= 1) {
            var6 = var1.charsWidth(var7.toCharArray(), 0, var7.length());
         } else {
            var6 = var1.charsWidth(var7.trim().toCharArray(), 0, var7.trim().length());
         }

         var5 = 0;
         if(var7.trim().length() != var7.length()) {
            var5 = var1.charWidth(' ');
         }

         if(var11 + var6 < var2 && var9.indexOf(10) == -1) {
            var9 = var9 + var7;
            var11 += var6 + var5;
         } else {
            var4 = 0;
            if(var6 > var2) {
               while(var4 < var7.length()) {
                  if(var11 + var1.charWidth(var7.charAt(var4)) < var2) {
                     var11 += var1.charWidth(var7.charAt(var4));
                     ++var4;
                  } else {
                     if(var4 > 1) {
                        var9 = var9 + var7.substring(0, var4 - 1);
                        var7 = var7.substring(var4 - 1, var7.length());
                        var6 = var1.charsWidth(var7.trim().toCharArray(), 0, var7.trim().length());
                     }

                     if(var6 < var2) {
                        if(var9.indexOf(10) == 0) {
                           ++var10;
                        }

                        var8 = var8 + var9;
                        if(var9.indexOf(10) == -1 || var9.indexOf(10) == 0) {
                           var8 = var8 + '\n';
                        }

                        var9 = var7;
                        var11 = var6 + var5;
                        ++var10;
                        break;
                     }

                     if(var9.indexOf(10) == 0) {
                        ++var10;
                     }

                     var8 = var8 + var9;
                     if(var9.indexOf("\n") == -1 || var9.indexOf(10) == 0) {
                        var8 = var8 + '\n';
                     }

                     var9 = "";
                     ++var10;
                     var11 = 0;
                     var4 = 0;
                  }
               }
            } else {
               if(var9.length() > 0) {
                  var8 = var8 + var9;
                  if(var9.indexOf(10) == -1 && var7.indexOf(10) != 0) {
                     var8 = var8 + '\n';
                  }

                  if(var7.indexOf(10) != 0 || var0.indexOf(10) == 0 && var7.indexOf(10) == 0 || var9.indexOf(10) != -1) {
                     ++var10;
                  }
               }

               var9 = var7;
               var11 = var6 + var5;
            }
         }

         if(var0.length() == 0 && var7.length() > 0) {
            var8 = var8 + var9;
         }
      }

      String[] var15 = new String[var10 + 1];
      String var14 = "";
      if(var3 > 0) {
         for(int var13 = 0; var13 < var3; ++var13) {
            var14 = var14 + ' ';
         }
      }

      var2 = 0;

      int var12;
      for(var12 = 0; var2 != -1 && var10 + 1 != var12; ++var12) {
         var2 = var8.indexOf("\n");
         if(var2 == -1) {
            var0 = var8.substring(0, var8.length());
         } else {
            var0 = var8.substring(0, var2);
         }

         var15[var12] = var0.trim();
         if(var3 > 0) {
            var15[var12] = var14 + var15[var12];
         }

         if(var2 >= var8.length() || var2 == -1) {
            var8 = "";
            break;
         }

         var8 = var8.substring(var2 + 1, var8.length());
      }

      if(var10 > 0 && var8.length() > 0) {
         if(var3 > 0) {
            var15[var12] = var14 + var8;
         } else {
            var15[var12] = var8;
         }
      }

      return var15;
   }

   public static int[] A() {
      int[] var2 = null;

      try {
         DataInputStream var1 = new DataInputStream(A("c"));
         b = var1.readInt();
         var2 = new int[b];

         for(int var0 = 0; var0 < b; ++var0) {
            var2[var0] = var1.readInt();
         }
      } catch (Exception var3) {
         ;
      }

      return var2;
   }

   public static Font[] B() {
      Font[] var7 = new Font[4];

      int var0;
      for(var0 = 0; var0 < 4; ++var0) {
         var7[var0] = Font.getDefaultFont();
      }

      try {
         DataInputStream var6 = new DataInputStream(A("f"));
         short var5 = var6.readShort();

         for(int var4 = 0; var4 < var5; ++var4) {
            short var3 = var6.readShort();
            byte var2 = 0;
            byte var1 = 0;
            var0 = 0;
            if((var3 >> 0 & 1) == 1) {
               var2 = 32;
            } else if((var3 >> 2 & 1) == 1) {
               var2 = 64;
            }

            if((var3 >> 4 & 1) == 1) {
               var1 = 8;
            } else if((var3 >> 5 & 1) == 1) {
               var1 = 16;
            }

            if((var3 >> 6 & 1) == 1) {
               var0 = 1;
            }

            if((var3 >> 7 & 1) == 1) {
               var0 |= 2;
            }

            if((var3 >> 8 & 1) == 1) {
               var0 |= 4;
            }

            var7[var4] = Font.getFont(var2, var0, var1);
         }
      } catch (Exception var8) {
         ;
      }

      return var7;
   }

   public static final boolean A(int var0) {
      RecordStore var1;
      try {
         var1 = RecordStore.openRecordStore("RECSTR" + var0, false);
      } catch (Exception var3) {
         return false;
      }

      if(var1 != null) {
         try {
            var1.closeRecordStore();
         } catch (Exception var2) {
            ;
         }
      }

      return true;
   }

   public static final void B(int var0) {
      if(A(var0)) {
         try {
            RecordStore var1 = RecordStore.openRecordStore("RECSTR" + var0, false);
            if(var1.getNumRecords() == 1) {
               var1.deleteRecord(1);
            }

            var1.closeRecordStore();
            RecordStore.deleteRecordStore("RECSTR" + var0);
         } catch (Exception var2) {
            ;
         }

      }
   }

   public static final byte[] C(int var0) {
      c = -1;
      RecordStore var1 = null;

      byte[] var4;
      try {
         var1 = RecordStore.openRecordStore("RECSTR" + var0, false);
         var4 = var1.getRecord(1);
         c = 0;
      } catch (Exception var3) {
         var4 = null;
         c = -1;
      }

      if(var1 != null) {
         try {
            var1.closeRecordStore();
         } catch (Exception var2) {
            ;
         }
      }

      return var4;
   }

   public static final int A(int var0, byte[] var1) {
      c = 0;
      RecordStore var3 = null;
      boolean var2 = false;

      try {
         var3 = RecordStore.openRecordStore("RECSTR" + var0, true);
         if(var3.getNumRecords() < 1) {
            var3.addRecord(var1, 0, 1);
            var2 = true;
         }

         if(var3.getSizeAvailable() < var1.length * 2) {
            c = -2;
         } else {
            var3.setRecord(1, var1, 0, var1.length);
            var2 = false;
         }
      } catch (RecordStoreNotFoundException var5) {
         c = -2;
      } catch (Exception var6) {
         c = -1;
      }

      if(var3 != null) {
         try {
            var3.closeRecordStore();
            if(var2) {
               B(var0);
            }
         } catch (Exception var4) {
            ;
         }
      }

      return c;
   }

   public static void C() {
      if(A(1)) {
         d = C(1);

         for(int var1 = 0; var1 < 3; ++var1) {
            if(d[6 + var1] > -1) {
               for(int var0 = 0; var0 <= d[6 + var1]; ++var0) {
                  C.aa[var1 * 11 + var0][3] = 1;
               }
            }
         }
      } else {
         d[0] = 0;
         d[1] = 1;
         d[2] = 1;
         d[3] = 0;
         d[4] = 1;
         d[5] = 0;
         d[6] = -1;
         d[7] = -1;
         d[8] = -1;
         D();
      }

   }

   public static void D() {
      A(1, d);
   }

   public static void A(int var0, int var1) {
      if(var0 >= -1 && var0 < d.length) {
         if(var0 == 0 && var1 == 1) {
            d[0] = 1;
            d[1] = 0;
         } else if(var0 == 1 && var1 == 1) {
            d[0] = 0;
            d[1] = 1;
         } else if(var0 == -1 && var1 == 1) {
            d[0] = 0;
            d[1] = 0;
         } else {
            d[var0] = (byte)var1;
         }

      }
   }

   public static boolean D(int var0) {
      return var0 >= -1 && var0 < d.length?(var0 == -1?d[0] == 0 && d[1] == 0:d[var0] == 1):false;
   }

   public static byte E(int var0) {
      return (byte)(var0 >= -1 && var0 < d.length?(var0 == -1?(d[0] == 0 && d[1] == 0?0:1):d[var0]):0);
   }
//
   public static void A(String var0, int var1, int var2, int var3, Graphics var4, int var5, int var6, int var7, int var8) {
      short var12 = e[var3 * 6 + 4];
      short var11 = e[var3 * 6 + 0];
      short var10 = e[var3 * 6 + 1];
      short var9 = e[var3 * 6 + 2];
      short var13 = e[var3 * 6 + 3];
      if((var12 & 256) == 256) {
         var4.clipRect(var11, var10, var9, var13);
      } else {
         var4.setClip(var11, var10, var9, var13);
      }

      C.A(var4, var0, var1 + 1, var2, var5, 0, var6, var7, var8);
   }

   public static void A(int var0, boolean var1) {
      if(var1) {
         e[var0 * 6 + 5] = 1;
      } else {
         e[var0 * 6 + 5] = 0;
      }

   }

   public static void A(int var0, int var1, int var2, int var3, int var4, int var5) {
      var4 -= 3;
      e[var0 * 6 + 0] = (short)var1;
      e[var0 * 6 + 1] = (short)var2;
      e[var0 * 6 + 2] = (short)var3;
      e[var0 * 6 + 3] = (short)var4;
      e[var0 * 6 + 4] = (short)var5;
      A(var0, true);
   }

   public static void A(int var0, int var1, int var2, boolean var3) {
      f[var0 * 3 + 0] = (short)var1;
      f[var0 * 3 + 1] = (short)var2;
      if(var3) {
         f[var0 * 3 + 2] = 0;
      }

   }
//
   public static void A(String var0, int var1, int var2, Graphics var3, int var4, int var5, int var6) {
      if(e[var1 * 6 + 5] == 1) {
         short var7 = e[var1 * 6 + 4];
         boolean var8 = (var7 & 32) == 32;
         String[] var9;
         if(var8) {
            var9 = A(var0, C.i[var2], e[var1 * 6 + 2], 0);
         } else {
            var9 = h;
            var9[0] = var0;
         }

         A(var9, var1, var2, var3, var4, var5, var6);
      }

   }

   public static void A(String[] var0, int var1, int var2, Graphics var3, int var4, int var5, int var6) {
      if(e[var1 * 6 + 5] == 1) {
         short var12 = e[var1 * 6 + 4];
         short var11 = e[var1 * 6 + 0];
         short var10 = e[var1 * 6 + 1];
         short var9 = e[var1 * 6 + 2];
         short var8 = e[var1 * 6 + 3];
         int var7 = C.K(var2);
         String[] var27 = var0;
         int var26 = var0.length * var7;
         int var25 = C.i[var2].stringWidth(var0[0]);
         boolean var24 = (var12 & 1) == 1;
         boolean var23 = (var12 & 2) == 2;
         boolean var22 = (var12 & 4) == 4;
         boolean var28 = (var12 & 16) == 16;
         boolean var21 = (var12 & 64) == 64;
         boolean var10000;
         if((var12 & 32) == 32) {
            var10000 = true;
         } else {
            var10000 = false;
         }

         int var20 = 0;
         boolean var19 = f[var1 * 3 + 0] != 0;
         int var18 = 0;
         short var17 = f[var1 * 3 + 0];
         short var16 = f[var1 * 3 + 1];
         boolean var15 = false;
         int var14 = -1;
         int var32;
         if(!var21) {
            if(var28) {
               int var13 = var25 - var9 + 20;
               var32 = f[var1 * 3 + 2];
               if(var32 < 0) {
                  var32 = -var32;
               }

               if(var25 > var9) {
                  var15 = true;
                  if(var32 >= var13) {
                     var14 = var25 + 20;
                  }
               } else if(var16 == 0) {
                  f[var1 * 3 + 2] = 0;
               }

               if(var26 > var8) {
                  var15 = true;
                  if(var26 <= var32) {
                     f[var1 * 3 + 2] = (short)var8;
                  }
               } else if(var17 == 0) {
                  f[var1 * 3 + 2] = 0;
               }
            }
         } else if(var28) {
            if(var25 > var9) {
               var15 = true;
            } else if(var16 == 0) {
               f[var1 * 3 + 2] = 0;
            }

            if(var26 > var8) {
               var15 = true;
            } else if(var17 == 0) {
               f[var1 * 3 + 2] = 0;
            }
         }

         int var31;
         if(var21 && var15) {
            short var29 = f[var1 * 3 + 2];
            var31 = var8 - var26;
            g[var1 * 1 + 0] = 0;
            if(var29 < var31) {
               f[var1 * 3 + 2] = (short)var31;
               g[var1 * 1 + 0] = 1;
            }

            if(var29 > 0) {
               f[var1 * 3 + 2] = 0;
               g[var1 * 1 + 0] = 2;
            }
         }

         for(var32 = 0; var32 < var27.length; ++var32) {
            int var30 = C.i[var2].stringWidth(var27[var32]);
            if(var30 > var18) {
               var18 = var30;
            }

            if(var24) {
               var20 = var11;
            } else if(var23) {
               var20 = var11 + (var9 - var30);
            } else if(var22) {
               var20 = var11 + (var9 - var30 >> 1);
               if(var20 < e[var1 * 6 + 0]) {
                  var20 = e[var1 * 6 + 0];
               }
            }

            var31 = var10;
            if(var19) {
               var17 = f[var1 * 3 + 0];
               if(var17 != 0) {
                  var20 += f[var1 * 3 + 2];
                  if(var30 > var9 && var20 + var14 <= e[var1 * 6 + 0] && var14 != -1) {
                     f[var1 * 3 + 2] = 0;
                  }
               }
            } else {
               var31 = var10 + f[var1 * 3 + 2];
            }

            A(var27[var32], var20, var31 + var32 * var7, var1, var3, var2, var4, var5, var6);
            if(var14 > 0) {
               A(var27[var32], var20 + var14, var31 + var32 * var7, var1, var3, var2, var4, var5, var6);
            }
         }

         if(var15) {
            C.i[var2].stringWidth(var27[0]);
            if(!var21) {
               if(var17 != 0) {
                  f[var1 * 3 + 2] += var17;
               } else {
                  f[var1 * 3 + 2] += var16;
               }
            }
         }
      }

   }

   public static int E() {
      InputStream var3;
      try {
         var3 = qb.getClass().getResourceAsStream("/ldf");
      } catch (Exception var11) {
         return -1;
      }

      if(var3 == null) {
         return -1;
      } else {
         try {
            var3.read();
            n = (byte)var3.read();
            byte var0 = (byte)var3.read();
            byte[] var14 = new byte[var0];
            var3.read(var14);
            o = new String(var14);
            if(n == 3) {
               byte var2 = (byte)var3.read();
               p = new String[var2][2];

               for(int var1 = 0; var1 < var2; ++var1) {
                  var0 = (byte)var3.read();
                  var14 = new byte[var0];
                  var3.read(var14);
                  p[var1][0] = new String(var14);
                  var0 = (byte)var3.read();
                  var14 = new byte[var0];
                  var3.read(var14);
                  p[var1][1] = new String(var14);
               }
            }

            return 0;
         } catch (Exception var12) {
            ;
         } finally {
            try {
               var3.close();
            } catch (Exception var10) {
               return -1;
            }
         }

         return -1;
      }
   }

   public static int B(String var0) {
      int var3 = 0;
      switch(n) {
      case 0:
         var3 = -1;
         break;
      case 1:
         q = o;
         break;
      case 2:
         q = var0;
         break;
      case 3:
         int var2 = 0;

         for(boolean var1 = true; var2 < p.length && var1; ++var2) {
            if(p[var2][0].compareTo(var0) == 0) {
               q = p[var2][1];
               var1 = false;
            }
         }
      }

      if(var3 != -1) {
         var3 = F();
      }

      return var3;
   }

   public static int F() {
      byte var1 = 0;

      for(int var0 = 0; var0 < i.length; ++var0) {
         if(l[var0] == null) {
            if((j[var0] == 1 || j[var0] != 0 && k[var0]) && F(var0) == -1) {
               var1 = -1;
               break;
            }
         } else if(j[var0] != 1 && !k[var0]) {
            l[var0] = null;
         }
      }

      return var1;
   }

   public static int F(int var0) {
      InputStream var3 = null;

      try {
         if(q == null) {
            q = o;
         }

         String var1 = "/" + q + "." + i[var0] + "." + "lng";
         var3 = qb.getClass().getResourceAsStream(var1);
         if(var3 == null) {
            return -1;
         }

         byte[] var15 = new byte[2];
         var3.read(var15, 0, 2);
         int var2 = ((var15[1] & 255) << 8 | var15[0] & 255) - 2;
         if(var2 >= 0) {
            var15 = new byte[var2];
            if(var3.read(var15) != var2) {
               l[var0] = new String[0];
               return -1;
            }

            var2 = (var15[2] & 255) << 8 | var15[1] & 255;
            m = var15;
            l[var0] = new String[var2];

            for(int var16 = 0; var16 < var2; ++var16) {
               l[var0][var16] = G(var16);
            }

            return 0;
         }
      } catch (Exception var13) {
         return 0;
      } finally {
         try {
            var3.close();
         } catch (Exception var12) {
            ;
         }

         m = null;
      }

      return -1;
   }

   public static String G(int var0) {
      String var1 = "";
      if(n == 0) {
         return "BAADFOOD";
      } else {
         ++var0;
         var0 *= 2;
         if(var0 >= 0 && var0 < m.length) {
            int var2 = (m[var0 + 4] & 255) << 8 | m[var0 + 3] & 255;
            var0 = (m[var0 + 2] & 255) << 8 | m[var0 + 1] & 255;
            if(var0 >= m.length) {
               return "";
            } else {
               byte[] var6 = new byte[var2 - var0];
               System.arraycopy(m, var0, var6, 0, var6.length);
               switch(m[0]) {
               case 0:
                  var1 = (new String(var6, 0, var6.length)).replace('\r', '\n');
                  break;
               case 1:
                  StringBuffer var5 = new StringBuffer(var6.length / 2);

                  for(var0 = 0; var0 < var6.length - 1; var0 += 2) {
                     var5.append((char)((var6[var0 + 1] & 255) << 8 | var6[var0] & 255));
                  }

                  var1 = var5.toString().replace('\r', '\n');
                  break;
               case 2:
                  byte[] var4 = new byte[var6.length + 2];
                  var4[0] = (byte)(var6.length >>> 8);
                  var4[1] = (byte)var6.length;
                  System.arraycopy(var6, 0, var4, 2, var6.length);

                  try {
                     var1 = (new DataInputStream(new ByteArrayInputStream(var4))).readUTF().replace('\r', '\n');
                  } catch (Exception var3) {
                     ;
                  }
               }

               return var1;
            }
         } else {
            return "";
         }
      }
   }
//Извлечение сообщений из библиотеки
   public static String H(int var0) {
      String var3 = "";
      int var2 = var0 & '\uffff';
      int var1 = (var0 & 16711680) >> 16;
      if(l != null && var1 >= 0 && var1 < l.length) {
         if(l[var1] == null) {
            if(j[var1] == 4) {
               F(var1);
               if(l[var1] != null) {
                  var3 = H(var0);
               }
            }
         } else if(var2 >= 0 && var2 < l[var1].length && l[var1][var2] != null) {
            var3 = l[var1][var2];
         }
      }

      return var3;
   }

   public static void I(int var0) {
      if(var0 >= 0) {
         int var5 = r[var0][0];
         int var4 = r[var0][1];
         int var2 = r[var0][2];
         boolean var3 = true;
         if(t[var0][1] != -1) {
            var3 = false;
         }

         int var1;
         if(var3) {
            for(var1 = 0; var1 < ba; ++var1) {
               if(t[var1][1] == var4) {
                  var3 = false;
                  t[var0][0] = var5;
                  t[var0][1] = t[var1][1];
                  t[var0][2] = var1;
                  break;
               }
            }
         }

         if(var3) {
            String var12 = "";
            if(var4 < 10) {
               var12 = "0";
            }

            var12 = "/" + var12 + var4 + z[var2];
            String var13 = aa[var2];
            InputStream var15 = null;

            try {
               var15 = qb.getClass().getResourceAsStream(var12);
            } catch (Exception var11) {
               ;
            }

            try {
               var15.available();
               s[var0] = Manager.createPlayer(var15, var13);
            } catch (Exception var10) {
               ;
            }

            Player var14 = (Player)s[var0];

            try {
               var14.realize();
            } catch (Exception var9) {
               ;
            }

            try {
               var14.prefetch();
            } catch (Exception var8) {
               ;
            }

            if(!fa) {
               try {
                  var14.addPlayerListener(qb);
               } catch (Exception var7) {
                  ;
               }
            }

            s[var0] = var14;
            t[var0][0] = var5;
            t[var0][2] = var0;
            t[var0][1] = var4;

            try {
               var15.close();
            } catch (Exception var6) {
               ;
            }
         }

         if(s[t[var0][2]] == null) {
            for(var1 = 0; var1 < ba; ++var1) {
               if(t[var1][1] == t[var0][1]) {
                  t[var1][2] = -1;
                  t[var1][1] = -1;
                  t[var1][0] = -1;
               }
            }

            ++ga;
            if(ga <= 2) {
               I(var0);
            }
         }
      }

   }
//Звук
   public static void J(int var0) {
      Player var5 = null;
      int var4 = Q(var0);
      int var3 = Integer.MIN_VALUE;
      if(var4 >= 0) {
         int var1;
         int var2;
         for(var2 = 0; var2 < ba; ++var2) {
            if(u[var2] == 0) {
               var1 = r[var2][3];
               if(var3 < var1) {
                  var3 = var1;
               }
            }
         }

         boolean var10 = false;
         if(var4 >= 0) {
            var1 = r[var4][3];
            var10 = var1 > var3;
         }

         if(var10) {
            M(var0);
            var2 = t[var4][2];

            for(var10 = false; !var10 && ha < 3; ++ha) {
               if(var2 < 0) {
                  I(var4);
                  var2 = t[var4][2];
               }

               if(var2 >= 0 && s[var2] != null) {
                  var5 = (Player)s[var2];
                  if(da) {
                     var10 = var5.getState() == 200 || var5.getState() == 300;
                  } else {
                     var10 = var5.getState() == 300;
                  }

                  if(!var10) {
                     H();
                  }
               }
            }

            ha = 0;
            if(var10) {
               try {
                  var0 = var5.getState();
                  if(var0 != 300 && var0 != 0) {
                     var5.prefetch();
                  }
               } catch (Exception var8) {
                  return;
               }

               try {
                  var5.setLoopCount(1);
                  var5.start();
                  u[var4] = 0;
               } catch (Exception var7) {
                  ;
               }

               try {
                  VolumeControl var11 = (VolumeControl)var5.getControl("VolumeControl");
                  var0 = r[var4][4];
                  boolean var9 = (var0 >> 5 & 1) == 1;
                  if(var9) {
                     var0 = 0 + 255 * y / 125;
                     var11.setLevel(var0);
                  } else {
                     var0 = 0 + 255 * x / 125;
                     var11.setLevel(var0);
                  }
               } catch (Exception var6) {
                  ;
               }

               if(fa) {
                  var0 = r[var4][7];
                  if(var0 > 0) {
                     w[var4] = System.currentTimeMillis() + (long)var0;
                  } else {
                     w[var4] = System.currentTimeMillis() + var5.getDuration() / 1000L;
                  }
               }
            }
         }
      }

   }

   public static void K(int var0) {
      if(var0 >= 0) {
         int var2 = Q(var0);
         var0 = t[var2][2];
         if(var0 >= 0) {
            Player var1 = (Player)s[var0];

            try {
               if(var1.getState() != 0) {
                  var1.stop();
               }
            } catch (Exception var4) {
               ;
            }

            u[var2] = 1;
            if(da) {
               try {
                  if(var1.getState() != 0) {
                     var1.deallocate();
                  }
               } catch (Exception var3) {
                  ;
               }
            }

            var0 = r[var2][4];
            boolean var6 = (var0 >> 1 & 1) != 1;
            if(var6) {
               try {
                  if(var1.getState() == 300) {
                     var1.deallocate();
                  }

                  if(var1.getState() == 200 || var1.getState() == 100) {
                     var1.close();
                  }
               } catch (Exception var5) {
                  ;
               }

               s[var2] = null;
               t[var2][0] = -1;
               t[var2][1] = -1;
               t[var2][2] = -1;
               u[var2] = -1;
            }
         }
      }

   }

   public void playerUpdate(Player var1, String var2, Object var3) {
      if(!fa && !ka) {
         int var6 = -1;

         int var4;
         for(var4 = 0; var4 < ba; ++var4) {
            Player var8 = (Player)s[var4];
            if(var1 == var8) {
               var6 = var4;
               break;
            }
         }

         int var5 = -1;
         var4 = -1;

         for(int var9 = 0; var9 < ba; ++var9) {
            int var7 = t[var9][2];
            if(var6 == var7 && u[var9] == 0) {
               var5 = t[var9][0];
               var4 = Q(var5);
               break;
            }
         }

         if(var4 >= 0) {
            if(var2 == "stopped") {
               N(var5);
            } else if(var2 == "endOfMedia") {
               N(var5);
               u[var4] = 2;
               O(var4);
            }
         }
      }

   }

   public static void G() {
      L();
      ba = r.length;
      if(s == null) {
         s = new Object[ba];
         t = new int[ba][4];
         u = new byte[ba];
         v = new int[ba];
         w = new long[ba];
      }

      int var0;
      for(var0 = 0; var0 < ba; ++var0) {
         t[var0][0] = -1;
         t[var0][1] = -1;
         t[var0][2] = -1;
         u[var0] = -1;
         v[var0] = -1;
         w[var0] = -1L;
         s[var0] = null;
      }

      System.gc();
      boolean var3 = false;

      for(int var2 = 0; var2 < ba; ++var2) {
         var0 = r[var2][4];
         boolean var1 = (var0 >> 2 & 1) == 1;
         boolean var4 = (var0 >> 0 & 1) == 1;
         var4 = var4 && s[var2] == null;
         if(var4) {
            I(var2);
         }

         if(var1 || fa) {
            var3 = true;
         }

         C.j.d = ba * 10 / ba + 90;
         Thread.yield();
      }

      if(var3) {
         if(ca == null) {
            ca = new B();
         }

         ca.start();
      }

      ia = true;
   }

   public static void L(int var0) {
      if(var0 >= 0) {
         int var2 = Q(var0);
         if(var2 >= 0) {
            int var1 = r[var2][4];
            if((var1 >> 3 & 1) == 1 && !D(0) || (var1 >> 3 & 1) != 1 && !D(1) && var2 != 0 || D(-1)) {
               return;
            }

            boolean var7 = (var1 >> 2 & 1) == 1 || fa;
            int var6 = var0;
            if(r[var2][5] >= 0) {
               Vector var5 = new Vector(1, 1);
               int var4 = r[var2][5];

               for(int var3 = 0; var3 < ba; ++var3) {
                  if(r[var2][5] >= 0) {
                     var0 = r[var3][5];
                     if(var0 == var4) {
                        var5.addElement(new Integer(r[var3][0]));
                     }
                  }
               }

               var0 = var5.size();
               if(var0 > 1) {
                  var6 = ((Integer)var5.elementAt(C.D(var0))).intValue();
               }
            }

            if(var7) {
               if(!B.b) {
                  ca = new B();
                  ca.start();
               }

               B.A(var6);
            } else {
               P(var6);
            }

            if(u[var2] != 0) {
               u[var2] = 4;
            }
         }
      }

   }

   public static void M(int var0) {
      int var1 = Q(var0);
      if(var1 >= 0) {
         int var7 = r[var1][3];
         var1 = r[var1][4];
         boolean var6 = (var1 >> 3 & 1) == 1;

         for(int var5 = 0; var5 < ba; ++var5) {
            boolean var8 = r[var5][3] < var7;
            if(u[var5] != 0 && u[var5] != 4) {
               var8 = false;
            }

            if(u[var5] == 2) {
               var8 = true;
            }

            if(var8) {
               int var4 = t[var5][0];
               var1 = r[var5][4];
               boolean var3 = (var1 >> 3 & 1) == 1;
               boolean var2 = (var1 >> 4 & 1) == 1;
               var8 = false;
               if(var2) {
                  if(var3 && var6 || !var3 && !var6) {
                     var8 = true;
                  }
               } else {
                  var8 = true;
               }

               if(var8) {
                  B(var4, var0);
               }
            }
         }
      }

   }

   public static void B(int var0, int var1) {
      var1 = Q(var0);
      if(var1 > 0) {
         if(ea) {
            H();
         } else {
            u[var1] = 1;
            N(var0);
         }
      }

   }

   public static void N(int var0) {
      if(var0 >= 0) {
         int var1 = Q(var0);
         var1 = r[var1][4];
         boolean var2 = (var1 >> 2 & 1) == 1;
         if(!var2 && !fa) {
            K(var0);
         } else {
            B.B(var0);
         }
      }

   }

   public static boolean O(int var0) {
      boolean var3 = false;
      int var6 = t[var0][0];
      if(var0 >= 0) {
         int var1 = r[var0][4];
         boolean var2 = (var1 >> 3 & 1) == 1;
         boolean var7 = u[var0] == 2;
         if(r[var0][6] > 0) {
            u[var0] = 2;
            L(r[var0][6]);
            return true;
         }

         if(var2 && var7) {
            int var5 = var6;
            if(r[var0][5] >= 0) {
               int var4 = r[var0][5];
               Vector var9 = new Vector(1, 1);

               for(int var8 = 0; var8 < ba; ++var8) {
                  if(r[var0][5] >= 0) {
                     var1 = r[var8][5];
                     if(var1 == var4 && var6 != r[var8][0]) {
                        var9.addElement(new Integer(r[var8][0]));
                     }
                  }
               }

               var1 = var9.size();
               if(var1 > 1) {
                  while(var5 == var6) {
                     var5 = ((Integer)var9.elementAt(C.D(var1))).intValue();
                  }
               }
            }

            if(var5 < 0) {
               var5 = r[var0][0];
            }

            u[var0] = 2;
            L(var5);
            var3 = true;
         }
      }

      return var3;
   }

   public static void H() {
      for(int var1 = 0; var1 < ba; ++var1) {
         int var0 = t[var1][0];
         N(var0);
      }

   }

   public static void P(int var0) {
      if(!ka) {
         if(ea) {
            boolean var4 = true;
            if(ja >= 0) {
               int var2 = Q(ja);
               int var1 = Q(var0);
               int var3 = r[var2][3];
               var2 = r[var1][3];
               boolean var5 = var2 <= var3;
               if(var5) {
                  var4 = false;
               }
            }

            if(var4) {
               ja = var0;
            }
         } else {
            J(var0);
         }

         Q(ja);
      }

   }
//Воспроизведение звука
   public static void I() {
      if(ja >= 0) {
         Q(ja);
         J(ja);
         ja = -1;
      }

   }

   public static void J() {
      if(!ka) {
         la = System.currentTimeMillis();
      }

      ka = true;

      for(int var1 = 0; var1 < ba; ++var1) {
         if(u[var1] == 0 || u[var1] == 4) {
            int var0 = t[var1][0];
            v[var1] = var0;
            N(var0);
            u[var1] = 3;
         }
      }

   }

   public static void K() {
      ka = false;

      for(int var3 = 0; var3 < ba; ++var3) {
         if(u[var3] == 3) {
            int var2 = v[var3];
            if(fa) {
               long var0 = System.currentTimeMillis();
               var0 -= la;
               if(w[var3] > 0L) {
                  w[var3] += var0;
               }
            }

            L(var2);
         }
      }

   }

   public static int Q(int var0) {
      int var2 = -1;

      int var1;
      for(var1 = 0; var1 < ba; ++var1) {
         if(t[var1][0] == var0) {
            var2 = var1;
            break;
         }
      }

      if(var2 == -1) {
         for(var1 = 0; var1 < ba; ++var1) {
            if(r[var1][0] == var0) {
               var2 = var1;
               break;
            }
         }
      }

      return var2;
   }

   public static int R(int var0) {
      int var1 = Q(var0);
      byte var2 = -1;
      if(var1 >= 0) {
         var2 = u[var1];
      }

      return var2;
   }

   private static void L() {
      int var4 = 0;
      int[][] var3 = new int[][]{{0, 0, 0, 0, 4, -1, 1, 23997}, {1, 1, 0, 0, 12, -1, -1, '\u97fa'}, {2, 2, 0, 0, 12, 1, -1, '\ub2cc'}, {3, 3, 0, 0, 12, 1, -1, 31990}, {4, 4, 0, 0, 12, 1, -1, '\uc501'}, {5, 5, 0, 0, 12, 1, -1, '\ua3cd'}, {6, 6, 0, 0, 12, 1, -1, '\ub638'}, {7, 7, 0, 0, 12, 1, -1, '\ub020'}, {8, 10, 0, 9, 4, -1, -1, 6981}, {9, 11, 0, 9, 4, -1, -1, 4273}, {10, 20, 0, 7, 4, -1, -1, 1461}, {11, 21, 0, 7, 4, -1, -1, 1754}, {12, 22, 0, 8, 4, -1, -1, 746}, {13, 23, 0, 6, 4, -1, -1, 258}, {14, 24, 0, 5, 4, -1, -1, 2826}, {15, 30, 0, 3, 4, -1, -1, 859}, {16, 31, 0, 3, 4, -1, -1, 1207}, {17, 40, 0, 4, 4, -1, -1, 1318}, {18, 41, 0, 4, 4, -1, -1, 2581}, {19, 50, 0, 2, 4, -1, -1, 3107}, {20, 51, 0, 2, 4, -1, -1, 1232}, {21, 52, 0, 2, 4, -1, -1, 1598}, {22, 53, 0, 2, 4, -1, -1, 1695}, {23, 54, 0, 2, 4, -1, -1, 1599}};
      int[] var2 = new int[var3.length];

      int var1;
      for(var1 = 0; var1 < var3.length; ++var1) {
         String var0;
         if(var3[var1][1] < 10) {
            var0 = "/0" + var3[var1][1] + z[var3[var1][2]];
         } else {
            var0 = "/" + var3[var1][1] + z[var3[var1][2]];
         }

         InputStream var5 = "".getClass().getResourceAsStream(var0);
         if(var5 == null) {
            var2[var1] = 0;
         } else {
            var2[var1] = 1;
            ++var4;
         }
      }

      r = new int[var4][8];
      var1 = 0;

      for(int var6 = 0; var6 < var3.length; ++var6) {
         if(var2[var6] == 1) {
            System.arraycopy(var3[var6], 0, r[var1], 0, var3[var6].length);
            ++var1;
         }
      }

   }

   public static int A(Graphics var0, int var1, int var2, String var3, int var4, int var5, int var6, boolean var7, boolean var8) {
      if(var0 != null) {
         if(!var7) {
            var8 = false;
         }

         int var9 = C.h[var8?8:7];
         var1 = C.h[var8?9:7];
         int var15 = var8?3:0;
         int var14 = 10;
         if(var2 == 50) {
            var9 = C.h[8];
            var1 = C.h[9];
            var15 = 3;
         }

         if(var7) {
            if(za) {
               qa = 10;
            } else if(qa > 0) {
               --qa;
            }

            var14 = qa;
            var5 += var14;
         }

         C.i[2].stringWidth(var3);
         int var13 = C.i[2].getHeight() + 2 + 2;
         short var12 = ob[26][3];
         int var11 = var5;
         int var10 = dA - var12 + var14;
         C.i[2].stringWidth(var3);
         var5 += var12 >> 1;
         C.A(var0, 26, 0, var11, var6, hA, iA, jA, kA);

         while((var11 += var12) < var10) {
            C.A(var0, 26, 1, var11, var6, hA, iA, jA, kA);
         }

         C.A(var0, 26, 2, dA - var12 + var14, var6, hA, iA, jA, kA);
         switch(var2) {
         case 50:
            A(8 + ra, var5 + 4, var6, dA - var5 - 4, var13 << 1, 273);
            var0.setClip(hA, iA, jA, kA);
            A(var3, 8 + ra++, 2, var0, var15, var9, var1);
            return ob[26][4] + 7;
         case 102:
            if(uA[var4] == 2) {
               C.A(var0, 19, 1, var5 + 4, var6 + (ob[26][4] - ob[19][4] >> 1), hA, iA, jA, kA);
            } else {
               C.A(var0, 19, 0, var5 + 4, var6 + (ob[26][4] - ob[19][4] >> 1), hA, iA, jA, kA);
            }

            var5 += ob[19][3] + 3;
         }

         if(var7 && !za && qa == 0) {
            if(ya == null) {
               ya = new String[]{var3};
               A(1, var5 + 4 - 1, var6, dA - var5 - 4, var13 << 1, 17);
               A(1, -1, 0, true);
            }

            var0.setClip(hA, iA, jA, kA);
            A(ya, 1, 2, var0, var15, var9, var1);
         } else {
            var0.setClip(hA, iA, jA, kA);
            C.A(var0, var3, var5 + 4, var6, 2, 0, var15, var9, var1);
         }
      }

      return ob[26][4] + 7;
   }

   private static void A(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      var0.setColor(C.h[var3]);
      C.A(var0, 16, var4, var1, var2, hA, iA, jA, kA);
      var0.setClip(hA, iA, jA, kA);
      var3 = var1 + ob[16][3] + 2;

      for(var1 = 0; var1 < 15; ++var1) {
         if(var1 < var5) {
            var0.fillRect(var3 + var1 * 3, var2, 2, ob[16][4]);
         } else {
            var0.setColor(C.h[12]);
            var0.drawRect(var3 + var1 * 3, var2, 1, ob[16][4] - 1);
         }
      }

   }

   public static int A(Graphics var0, int var1, int var2, String var3, int var4, int var5, boolean var6, int var7, int var8) {
      int var9 = 0;
      int var10;
      int var11;
      int var12;
      int var13;
      int var14;
      int var15;
      short var19;
      short var21;
      short var22;
      int var23;
      String[] var24;
      Font var25;
      short var27;
      short var29;
      int var31;
      byte var34;
      byte var35;
      switch(var2) {
      case 50:
      case 100:
      case 101:
      case 102:
      case 107:
      case 108:
      case 109:
         var11 = 33;
         var9 = ob[26][4] + 7;
         boolean var51 = var0 != null && var5 > bA - var9 && var5 <= eA;
         boolean var40 = true;
         if(var6) {
            A(var5, var9, va);
         }

         if(var51) {
            switch(var2) {
            case 50:
               break;
            case 102:
               var11 = 33 + 10 + ob[19][3];
               break;
            case 107:
               var40 = C.aa[var8][3] == 1;
            default:
               var11 += 10;
            }

            if(!var6) {
               var11 += 10;
            }

            var8 = cA + var11;
            A(var0, var1, var2, var3, var4, var8, var5, var6, var40);
         }
         break;
      case 53:
         if(var0 == null) {
            sa[ua][(var1 << 2) + 3] = bA;
            bA += ob[26][4] + 7;
            e[28] = -1;
         } else {
            var0.setClip(0, 0, C.f, C.g);
            C.i[2].stringWidth(var3);
            C.i[2].getHeight();
            var27 = ob[26][3];
            var4 = 33;
            var2 = dA - var27;
            C.i[2].stringWidth(var3);
            var1 = 33 + (var27 >> 1);
            var31 = var8;
            C.A(var0, 26, 0, 33, var8);

            while((var4 += var27) < var2) {
               C.A(var0, 26, 1, var4, var31);
            }

            C.A(var0, 26, 2, dA - var27, var31);
            var0.setClip(0, 0, C.f, C.g);
            if(e[28] == -1) {
               A(4, var1 + 4, var31, dA - (var1 + 4), C.i[2].getHeight() << 1, 17);
               A(4, -1, 0, true);
            }

            A(var3, 4, 2, var0, 3, C.h[8], C.h[9]);
         }
         break;
      case 65:
         var22 = ob[var7][3];
         var21 = ob[var7][4];
         if(var0 != null && var5 > bA - var21 && var5 < eA) {
            var0.setClip(cA, bA, gA, fA);
            C.A(var0, var7, var8, (gA - var22 >> 1) + cA, var5);
         }

         var9 = 0 + var21 + 7;
         break;
      case 80:
         if(C.x[27] != null) {
            short var36 = ob[27][4];
            var27 = ob[27][3];
            var4 = eA - var36;
            var23 = bA + 16;
            var2 = eA + 0;
            var1 = bA;
            if(var0 != null) {
               C.A(var0, 27, 2, 0, var1);

               while((var1 += var36) < var4) {
                  C.A(var0, 27, 1, 0, var1);
               }

               C.A(var0, 27, 0, 0, eA - var36);
               if(pa > fA) {
                  var29 = ob[29][4];
                  C.A(var0, 29, 0, 6, -oa * (var2 - var23 - var29) / (pa - fA) + var23);
               }
            } else {
               cA += 10 + var27;
            }
         }
         break;
      case 81:
         var22 = ob[21][3];
         var21 = ob[21][4];
         if(var0 != null) {
            C.A(var0, 21, 0, C.f - var22 >> 1, 4);
         } else {
            bA += var9 = var21 + 4 + 8;
         }
         break;
      case 90:
         if(var0 == null) {
            var21 = ob[18][3];
            var19 = ob[18][4];
            bA += var19;
            cA += var21;
         } else {
            A(var0, (String)null, cA, bA, dA, eA, 9, -1, false);
         }
         break;
      case 91:
         var21 = 0;
         if(ob[var7] != null) {
            var21 = ob[var7][4];
         }

         if(var0 == null) {
            var22 = ob[18][3];
            var19 = ob[18][4];
            bA = C.g / 2;
            cA += var22;
            if(C.x[27] != null) {
               dA -= var22 + ob[27][3];
            } else {
               dA -= var22;
            }

            eA -= var19;
            if(bA < var21) {
               bA = var21;
            }
         } else {
            A(var0, (String)null, cA, bA, dA, eA, 15, 0, true);
            C.A(var0, var7, var8 == -1?C.ab:var8, cA, bA - var21 - ob[18][4]);
         }
         break;
      case 92:
         if(var0 == null) {
            var22 = ob[18][3];
            var21 = ob[18][4];
            int var18 = 0;

            for(bA += var21 << 1; C.i[2].getHeight() + 0 >= var21 + var18; var18 += 3) {
               ;
            }

            bA += var18;
            cA += var22;
            if(C.x[27] != null) {
               dA -= var22 + ob[27][3];
            } else {
               dA -= var22;
            }

            eA -= var21;
         } else {
            A(var0, var3, cA, bA, dA, eA, 15, 2, true);
         }
         break;
      case 120:
         var25 = C.i[2];
         int[] var30 = null;
         int var17 = var8;
         if(ua == 30) {
            var30 = C.db == 0?C.ta[var8]:C.va[var8];
         }

         if(var30 != null) {
            var17 = var30[0];
         }

         boolean var52 = ua != 31 && var30[13] >= 0;
         var25.stringWidth(var3);
         var15 = var25.getHeight();
         var7 = var52?var15 << 1:var15;
         var29 = ob[25][4];
         var14 = var5 + var7 + 4 + 2;
         var9 = var7 + var29 + 4 + 2 + 2;
         if(var0 != null) {
            var0.setFont(var25);
            byte var49;
            if(var6) {
               A(var5, var9, va);
               var0.setColor(C.h[6]);
               var0.fillRect(cA, var5, gA, var9);
               var0.setColor(C.h[28]);
               var0.drawRect(cA, var5, gA, var9);
               var13 = C.h[8];
               var12 = C.h[9];
               var49 = 1;
            } else {
               var13 = C.h[7];
               var12 = C.h[7];
               var49 = 0;
            }

            var0.setColor(C.h[27]);
            var0.drawLine(cA, var5 + var9 + 3, dA, var5 + var9 + 3);
            C.A(var0, C.da[var17][0], 3, cA + 4, var5 + 2, hA, iA, jA, kA);
            C.A(var0, 15, 0, cA + 4 + 9, var5 + 2, hA, iA, jA, kA);
            short var38;
            if(ua == 31) {
               var10 = C.da[var17][11];
               var8 = C.uA[C.db] < var10?2:1;
               var38 = ob[33][3];
               var29 = ob[33][4];
               var2 = dA - var38 * 3 / 2;
               if(C.da[var17][4] >= 0 && C.da[var17][4] < C.hb.length && C.hb[C.da[var17][4]] != 100) {
                  var10 = var10 * C.hb[C.da[var17][4]] / 100;
               }

               C.A(var0, var10, var2 + var38 - ob[11][3], var14 + (var29 - ob[11][4] >> 1), var8, hA, iA, jA, kA, 2);
            } else {
               var8 = ob[22][3] + 2;
               var7 = dA - 6 * var8 + 2;
               var4 = var30[10];

               for(var2 = 1; var2 < 6; ++var2) {
                  C.A(var0, 22, var4 >= C.hA[var2]?0:1, var7, var14, hA, iA, jA, kA);
                  var7 += var8;
               }

               if(var52) {
                  var38 = ob[25][3];
                  var4 = cA + 4;

                  for(var2 = 0; var2 < (C.ea[var30[13]][2] == 4000?1:(C.ea[var30[13]][2] == 6000?2:3)); ++var2) {
                     C.A(var0, 25, 0, var4 + var2 * var38, var14, hA, iA, jA, kA);
                  }
               }
            }

            var0.setClip(hA, iA, jA, kA);

            for(var2 = 0; var2 < C.da[var17][3] + 1; ++var2) {
               var0.setColor(C.h[10]);
               var0.fillRect(cA + 4 + 10 + var2 * 3, var5 + 2 + 1, 2, 2);
            }

            if(var6 && !za) {
               if(ya == null) {
                  if(var52) {
                     ya = new String[]{var3, H(C.ea[var30[13]][0])};
                  } else {
                     ya = new String[]{var3};
                  }

                  A(1, cA + 32 + 7 - 1, var5 + 2, gA - 32 - 7 - 4, var15 * 2, 17);
                  A(1, -1, 0, true);
               }

               A(ya, 1, 2, var0, var49, var13, var12);
            } else {
               C.A(var0, var3, cA + 32 + 7, var5 + 2, 2, 0, var49, var13, var12);
               if(var52) {
                  C.A(var0, H(C.ea[var30[13]][0]), cA + 32 + 7, var5 + 2 + var15, 2, 0, var49, var13, var12);
               }
            }
         }

         var9 += 7;
         break;
      case 121:
         var25 = C.i[2];
         var25.stringWidth(var3);
         var1 = var25.getHeight();
         var9 = var1 + ob[25][4] + 4 + 2 + 2;
         if(var0 != null) {
            var0.setFont(var25);
            var14 = var8;
            short var47 = ob[25][3];
            short var43 = ob[16][3];
            byte var50;
            if(var6) {
               A(var5, var9, va);
               var0.setColor(C.h[6]);
               var0.fillRect(cA, var5, gA, var9);
               var0.setColor(C.h[28]);
               var0.drawRect(cA, var5, gA, var9);
               var11 = C.h[8];
               var10 = C.h[9];
               var50 = 1;
            } else {
               var11 = C.h[7];
               var10 = C.h[7];
               var50 = 0;
            }

            var0.setColor(C.h[27]);
            var0.drawLine(cA, var5 + var9 + 3, dA, var5 + var9 + 3);
            var4 = dA - 3 * var47;

            for(var2 = 0; var2 < (C.ea[var14][2] == 4000?1:(C.ea[var14][2] == 6000?2:3)); ++var2) {
               C.A(var0, 25, 0, var4 + var2 * var47, var5 + 2, hA, iA, jA, kA);
            }

            var7 = var5 + var9 - ob[16][4] - 2;
            var4 = cA + 4;
            var2 = var7 + (ob[16][4] - ob[11][4] >> 1);
            C.A(var0, 16, 14, var4, var7, hA, iA, jA, kA);
            C.A(var0, 1, var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            var4 += 20;
            C.A(var0, 16, 15, var4, var7, hA, iA, jA, kA);
            C.A(var0, C.ea[var14][10], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            if(C.ea[var14][7] > 0) {
               var4 += 20;
               C.A(var0, 16, 5, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][7], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][9] > 0) {
               var4 += 20;
               C.A(var0, 16, 12, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][9], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][4] > 0) {
               var4 += 20;
               C.A(var0, 16, 2, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][4], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][3] > 0) {
               var4 += 20;
               C.A(var0, 16, 7, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][3], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][8] > 0) {
               var4 += 20;
               C.A(var0, 16, 11, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][8], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][6] > 0) {
               var4 += 20;
               C.A(var0, 16, 13, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][6], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            if(C.ea[var14][5] > 0) {
               var4 += 20;
               C.A(var0, 16, 8, var4, var7, hA, iA, jA, kA);
               C.A(var0, C.ea[var14][5], var4 + var43, var2, 1, hA, iA, jA, kA, 2);
            }

            C.A(var0, C.ea[var14][2], dA - var47, var2, C.uA[C.db] < C.ea[var14][2]?2:1, hA, iA, jA, kA, 2);
            if(var6 && !za) {
               if(ya == null) {
                  ya = new String[]{var3};
                  A(1, cA + 4 - 1, var5 + 2, gA - 4 * var47 - 4, var1 * 2, 17);
                  A(1, -1, 0, true);
               }

               A(ya, 1, 2, var0, var50, var11, var10);
            } else {
               var0.setClip(hA, iA, jA - 4 * var47, kA);
               C.A(var0, var3, cA + 4, var5 + 2, 2, 0, var50, var11, var10);
            }
         }

         var9 += 7;
         break;
      case 188:
         var1 = C.i[2].getHeight();
         var24 = sA[var4];
         if(var24 == null) {
            var9 = 0;
         } else {
            var23 = var24.length;
            var23 = (var1 + 0) * var23;
            byte var28 = (byte)(var7 & 255);
            byte var45 = (byte)(var7 >> 8 & 255);
            var34 = (byte)ob[16][3];
            var35 = (byte)ob[16][4];
            byte var32 = (byte)ob[17][4];
            var9 = var23 + var32 + 0;
            if(var0 != null) {
               var23 = cA;
               if(var28 < 255) {
                  C.A(var0, 16, var28, var23, var5 + (var32 - var35 >> 1), hA, iA, jA, kA);
                  var23 += var34 + 5;
               }

               if(var45 < 255) {
                  C.A(var0, 17, var45, var23, var5, hA, iA, jA, kA);
               }

               var0.setClip(hA, iA, jA, kA);
               var1 = var5 + var32;
               A(var0, var24, var1, var8, 2);
            }
         }
         break;
      case 189:
         var1 = C.i[2].getHeight();
         var24 = sA[var4];
         if(var24 == null) {
            var9 = 0;
         } else {
            var23 = var24.length;
            var23 = (var1 + 0) * var23;
            byte[] var26 = new byte[]{(byte)(var7 & 255), (byte)(var7 >> 8 & 255), (byte)(var7 >> 16 & 255)};
            var34 = (byte)ob[22][3];
            var35 = (byte)ob[22][4];
            var9 = var23 + var35 + 0;
            if(var0 != null) {
               var4 = cA;

               for(byte var46 = 0; var46 < var26.length; ++var46) {
                  if(var26[var46] < 255) {
                     C.A(var0, 22, var26[var46], var4, var5, hA, iA, jA, kA);
                     var4 += var34 + 5;
                  }
               }

               var0.setClip(hA, iA, jA, kA);
               var1 = var5 + var35;
               A(var0, var24, var1, var8, 2);
            }
         }
         break;
      case 191:
         var29 = ob[25][3];
         short var41 = ob[25][4];
         if(var0 != null) {
            var2 = var29 >> 1;
            var1 = (gA - (var8 * var29 + (var8 - 1) * var2) >> 1) + cA;
            var5 += var41 >> 1;
            var31 = var8;

            while(true) {
               --var31;
               if(var31 < 0) {
                  break;
               }

               C.A(var0, 25, 0, var1, var5, hA, iA, jA, kA);
               var1 += var29 + var2;
            }
         }

         var9 = var41 << 1;
         break;
      case 192:
      case 195:
         short var42 = ob[25][3];
         short var44 = ob[25][4];
         var9 = cA + (var42 << 2);
         var31 = var5;
         var4 = 0;
         if(var2 == 195) {
            if(C.i[2].stringWidth(H(87) + ": 99") > gA - (cA + (var42 << 2))) {
               var9 = cA;
               var31 = var5 + var44 + 2;
               var4 = C.i[2].getHeight() + 2;
            }
         } else if(var2 == 192 && C.i[2].stringWidth("99 " + H(393258)) > gA - (cA + (var42 << 2))) {
            var9 = cA;
            var31 = var5 + var44 + 2;
            var4 = C.i[2].getHeight() + 2;
         }

         if(var0 != null) {
            var23 = cA;

            for(var1 = 0; var1 < var8; ++var1) {
               C.A(var0, 25, 0, var23, var5 + 0, hA, iA + 0, jA, kA);
               var23 += var42;
            }

            var0.setClip(hA, iA, jA, kA);
            if(var2 == 195) {
               C.A(var0, H(87) + ": " + var7, var9, var31, 2, 0, 0, C.h[7], 0);
            } else if(var2 == 192) {
               C.A(var0, var7 + " " + H(393258), var9, var31, 2, 0, 0, C.h[7], 0);
            }
         }

         var9 = var44 + var4 + 7;
         break;
      case 193:
         var22 = ob[12][3];
         var21 = ob[12][4];
         if(var0 != null) {
            C.A(var0, 12, var7, cA, var5 + 0, hA, iA + 0, jA, kA);
            var0.setClip(hA, iA, jA, kA);
            C.A(var0, var3, cA + var22 * 3 / 2, var5, 2, 0, 0, C.h[7], 0);
         }

         var9 = var21 + 7;
         break;
      case 194:
         Font var37 = C.i[2];
         int[] var33 = null;
         int var16 = var8;
         if(C.fA != null) {
            var33 = C.fA;
         } else if(C.dA != null) {
            var33 = C.dA;
         }

         if(var33 != null) {
            var16 = var33[0];
         }

         var6 = ua != 31 && var33[13] >= 0;
         var37.stringWidth(var3);
         var1 = var37.getHeight();
         var4 = var1;
         var21 = ob[22][4];
         var15 = var5 + var1 + 4 + 2;
         var14 = var1 + var21 + 4 + 2 + 2;
         var2 = (ob[16][3] + 13) * 5 - 13;
         var1 = (ob[16][3] + 13) * 4 - 13;
         if(var0 != null) {
            var0.setFont(var37);
            var13 = C.h[7];
            var12 = C.h[7];
            var0.setColor(C.h[27]);
            var0.drawLine(cA, var5 + var14 + 3, dA, var5 + var14 + 3);
            var11 = ob[22][3] + 2;
            var10 = dA - 6 * var11 + 2;
            C.A(var0, C.da[var16][0], 3, cA + 4, var5 + 2, hA, iA, jA, kA);
            C.A(var0, 15, 0, cA + 4 + 9, var5 + 2, hA, iA, jA, kA);
            var9 = var33[10];

            for(var8 = 1; var8 < 6; ++var8) {
               C.A(var0, 22, var9 >= C.hA[var8]?0:1, var10, var15, hA, iA, jA, kA);
               var10 += var11;
            }

            if(var2 > gA) {
               var8 = cA + (gA - ((ob[16][3] + 13) * 3 - 13)) >> 1;
            } else {
               var8 = cA + (gA - var2) >> 1;
            }

            var11 = var5 + var14 + 7;
            C.A(var0, 16, 0, var8, var11, hA, iA, jA, kA);
            C.A(var0, C.da[var33[0]][18], var8 + ob[16][3] + 1, var11, 1, hA, iA, jA, kA, 0);
            var8 += ob[16][3] + 13;
            C.A(var0, 16, 5, var8, var11, hA, iA, jA, kA);
            C.A(var0, C.da[var33[0]][19], var8 + ob[16][3] + 1, var11, 1, hA, iA, jA, kA, 0);
            if(var2 > gA) {
               var11 += ob[16][4] + 2;
               var8 = cA + (gA - ((ob[16][3] + 13) * 3 - 13)) >> 1;
            } else {
               var8 += ob[16][3] + 13;
            }

            C.A(var0, 16, 10, var8, var11, hA, iA, jA, kA);
            if(C.da[var33[0]][22] > 0) {
               C.A(var0, C.da[var33[0]][22], var8 + ob[16][3] + 1, var11, 1, hA, iA, jA, kA, 0);
            } else {
               C.A(var0, 11, 10 + 1 * ob[11][1], var8 + ob[16][3] + 1, var11, hA, iA, jA, kA);
            }

            var8 += ob[16][3] + 13;
            C.A(var0, 16, 1, var8, var11, hA, iA, jA, kA);
            C.A(var0, C.da[var33[0]][23], var8 + ob[16][3] + 1, var11, 1, hA, iA, jA, kA, 0);
            var8 += ob[16][3] + 13;
            C.A(var0, 16, 11, var8, var11, hA, iA, jA, kA);
            C.A(var0, C.da[var33[0]][20], var8 + ob[16][3] + 1, var11, 1, hA, iA, jA, kA, 0);
            var10 = C.da[var33[0]][21];
            byte var39 = 1;
            if(var33[25] != -1 && C.ea[var33[25]][8] > 0) {
               var10 += C.ea[var33[25]][8];
               var39 = 2;
            }

            var8 += ob[11][3];
            C.A(var0, 11, 10 + 1 * ob[11][1], var8 + ob[16][3] + 1, var11, hA, iA, jA, kA);
            var8 += ob[11][3];
            C.A(var0, var10, var8 + ob[16][3] + 1, var11, var39, hA, iA, jA, kA, 0);
            var8 = cA + (gA >> 2) - (ob[16][3] + 2 + 45 >> 1);
            var9 = var11 + ob[16][4] + 5;
            A(var0, var8, var9, 41, 7, C.da[var33[0]][12]);
            var9 += ob[16][4] + 2;
            A(var0, var8, var9, 41, 2, C.da[var33[0]][13]);
            var9 += ob[16][4] + 2;
            A(var0, var8, var9, 41, 12, C.da[var33[0]][14]);
            var9 += ob[16][4] + 2;
            A(var0, var8, var9, 41, 3, C.da[var33[0]][15]);
            var8 = cA + gA / 4 * 3 - (ob[16][3] + 2 + 45 >> 1);
            var9 -= 3 * (ob[16][4] + 2);
            A(var0, var8, var9, 40, 8, C.da[var33[0]][16]);
            var9 += ob[16][4] + 2;
            A(var0, var8, var9, 40, 13, C.da[var33[0]][17]);
            var10 = var9 + (ob[16][4] + 2) * 3;
            if(e[10] == -1) {
               var9 = ob[22][3] + 2;
               var8 = dA - 6 * var9 + 2;
               A(1, var8, var5 + 2, 6 * var9 - 2, C.i[2].getHeight() << 1, 273);
               A(1, -1, 0, true);
            } else {
               e[7] = (short)(var5 + 2);
            }

            var0.setClip(hA, iA, jA, kA);
            A(var3, 1, 2, var0, 0, var13, var12);
            var0.setClip(hA, iA, jA, kA);
            if(var6) {
               var0.setColor(C.h[27]);
               var0.drawLine(cA, var10 + 3, dA, var10 + 3);
               var10 += 7;
               if(e[34] == -1) {
                  A(5, cA, var10, gA, C.i[2].getHeight() << 1, 276);
                  A(5, -1, 0, true);
               } else {
                  e[31] = (short)var10;
               }

               A(H(C.ea[var33[13]][0]), 5, 2, var0, 0, var13, var12);
               short var48 = ob[25][3];
               var23 = cA + (gA - var48 * (C.ea[var33[13]][2] == 4000?1:(C.ea[var33[13]][2] == 6000?2:3)) >> 1);
               var10 += var4 + 2;

               for(var9 = 0; var9 < (C.ea[var33[13]][2] == 4000?1:(C.ea[var33[13]][2] == 6000?2:3)); ++var9) {
                  C.A(var0, 25, 0, var23 + var9 * var48, var10, hA, iA, jA, kA);
               }

               if(var1 > gA) {
                  var23 = cA + (gA - ((ob[16][3] + 13 << 1) - 13) >> 1);
               } else {
                  var23 = cA + (gA - var1 >> 1);
               }

               var9 = var10 + ob[25][4] + 7;
               var8 = ob[16][3] + 1;
               var7 = var33[13];
               C.A(var0, 16, 14, var23, var9, hA, iA, jA, kA);
               C.A(var0, 1, var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               var23 += ob[16][3] + 13;
               C.A(var0, 16, 15, var23, var9, hA, iA, jA, kA);
               C.A(var0, C.ea[var7][10], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               if(var1 > gA) {
                  var9 += ob[16][4] + 2;
                  var23 = cA + (gA - ((ob[16][3] + 13) * 2 - 13)) >> 1;
                  var23 -= ob[16][3] + 13;
               }

               if(C.ea[var7][7] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 5, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][7], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][9] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 12, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][9], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][4] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 2, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][4], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][3] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 7, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][3], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][8] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 11, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][8], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][6] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 13, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][6], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }

               if(C.ea[var7][5] > 0) {
                  var23 += ob[16][3] + 13;
                  C.A(var0, 16, 8, var23, var9, hA, iA, jA, kA);
                  C.A(var0, C.ea[var7][5], var23 + var8, var9, 1, hA, iA, jA, kA, 2);
               }
            }

            var0.setClip(hA, iA, jA, kA);

            for(var23 = 0; var23 < C.da[var16][3] + 1; ++var23) {
               var0.setColor(C.h[10]);
               var0.fillRect(cA + 4 + 10 + var23 * 3, var5 + 2 + 1, 2, 2);
            }
         }

         var9 = var14 + 7 + ob[16][4] + 5 + (ob[16][4] + 2) * 5;
         if(var2 > gA) {
            var9 += ob[16][4] + 2;
         }

         if(var6) {
            var9 += 7 + var4 + 2 + ob[25][4] + 2 + ob[16][4];
            if(var1 > gA) {
               var9 += ob[16][4] + 2;
            }
         }
         break;
      case 197:
         if(var0 != null) {
            var0.drawLine(cA, var5 + 0 + (var8 >> 1), dA, var5 + 0 + (var8 >> 1));
         }
      case 196:
         var9 = var8;
         break;
      case 198:
      case 199:
         var2 = C.i[2].getHeight();
         String[] var20 = sA[var4];
         if(var20 == null) {
            var9 = 0;
         } else {
            var23 = var20.length;
            var9 = var2 * var23 + 0;
            if(var0 != null) {
               A(var0, var20, var5, var8, 2);
            }
         }
      }

      return var9;
   }

   public static void A(Graphics var0, String[] var1, int var2, int var3, int var4) {
      int var10 = var1.length;
      int var9 = C.i[var4].getHeight();

      for(int var8 = 0; var8 < var10; ++var8) {
         if(var2 < eA && var2 > 0) {
            int var7 = var3 >> 8 & 255;
            int var6 = var3 & 255;
            int var5 = cA;
            if(var7 == 1) {
               var5 += dA - cA >> 1;
            }

            C.A(var0, var1[var8], var5, var2, var4, var7, var6, C.h[3], C.h[3]);
         }

         var2 += var9 + 0;
      }

   }

   public static void A(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if(C.x[18] != null) {
         short var11 = ob[18][3];
         short var10 = ob[18][4];
         int var9 = 0;
         var3 -= var10;
         int var12 = var2 - var11;
         if(var7 >= 0) {
            if(C.x[27] != null) {
               var4 += ob[27][3];
            }

            var3 -= var10;
            if(var7 == 2) {
               while(C.i[2].getHeight() + 0 >= var10 + var9) {
                  var9 += 3;
               }

               var3 -= var9;
               var9 = 0;
               var0.setColor(C.h[26]);
               var0.setClip(0, 0, C.f, C.g);
               var0.fillRect(var12, var3, var4 - var12 + var11, var10);

               while(C.i[2].getHeight() + 0 >= var10 + var9) {
                  C.A(var0, 18, 10, var12, var3 + var10 + var9);
                  C.A(var0, 18, 10, var4, var3 + var10 + var9);
                  var9 += 3;
               }

               var0.setClip(0, 0, C.f, C.g);
               var0.fillRect(var12 + var11, var3, var4 - var12 - var11, var10 + var9);
            }

            for(var2 = var12 + var11; var2 <= var4; var2 += var11) {
               if(var2 + var11 > var4) {
                  var2 = var4 - var11 + 1;
               }

               C.A(var0, 18, var7 * 3 + 1, var2, var3);
            }

            C.A(var0, 18, var7 * 3 + 0, var12, var3);
            C.A(var0, 18, var7 * 3 + 2, var4, var3);
            if(var1 != null) {
               var0.setClip(0, 0, C.f, C.g);
               var2 = C.i[2].getHeight();
               var0.fillRect(var12 + ob[18][3], var3 + (var10 + var9 - var2 >> 1), var4 - ob[18][3], 2 * var2);
               A(4, var12 + ob[18][3], var3 + (var10 + var9 - var2 >> 1), var4 - ob[18][3], 2 * var2, 20);
               A(4, -1, 0, false);
               A(var1, 4, 2, var0, 0, C.h[8], 0);
            }

            var3 += var10 + var9;
         }

         if((var6 & 9) != 0) {
            C.A(var0, 18, 9, var12, var3);
         }

         int var13;
         if((var6 & 1) != 0) {
            for(var13 = var12 + var11; var13 < var4; var13 += var11) {
               C.A(var0, 18, 10, var13, var3);
            }
         }

         if((var6 & 3) != 0) {
            C.A(var0, 18, 11, var4, var3);
         }

         for(var2 = var3 + var10; var2 < var5; var2 += var10) {
            if((var6 & 8) != 0) {
               C.A(var0, 18, 12, var12, var2);
            }

            for(var13 = var12 + var11; var13 < var4; var13 += var11) {
               C.A(var0, 18, 13, var13, var2);
            }

            if((var6 & 2) != 0) {
               C.A(var0, 18, 14, var4, var2);
            }
         }

         if((var6 & 12) != 0) {
            C.A(var0, 18, 15, var12, var5);
         }

         if((var6 & 4) != 0) {
            for(var13 = var12 + var11; var13 < var4; var13 += var11) {
               C.A(var0, 18, 16, var13, var5);
            }
         }

         if((var6 & 6) != 0) {
            C.A(var0, 18, 17, var4, var5);
         }

         if(ob[27] == null || ob[29] == null || C.x[29] == null || C.x[27] == null) {
            var8 = false;
         }

         if(var8) {
            short var14 = ob[27][4];
            short var15 = ob[27][3];
            var13 = bA - var10;
            var6 = eA + var10;
            var5 = var6 - var14;
            var4 = C.f - var15;

            for(var3 = var13; var3 < var5; var3 += var14) {
               C.A(var0, 27, 1, var4, var3);
            }

            C.A(var0, 27, 0, var4, var6 - var14);
            if(pa > fA) {
               var15 = ob[29][4];
               C.A(var0, 29, 0, var4 + 6, -oa * (var6 - var13 - var15) / (pa - fA) + var13);
            }
         }
      }

   }

   public static void S(int var0) {
      int var1;
      int var2;
      int var4;
      int[] var7;
      int[] var9;
      
      
      //byte var13;
      switch(var0) {
      case 3:
         for(int var14 = 8; var14 <= 12; ++var14) {
            A(var14, -1, 0, true);
         }

         ra = 0;
         break;
      case 13:
      case 14:
      case 15:
         sa[var0][10] = C.H(var0 - 13 + 2)?101:190;
         break;
      case 16:
         sa[var0][10] = C.H(5)?101:190;
         break;
      case 20:
         sa[var0][10] = C.H(6)?101:190;
         break;
      case 25:
         int var13 = 0;
         switch(C.ab) {
         case 0:
            var13 = 7;
            break;
         case 1:
            var13 = 39;
            break;
         case 2:
            var13 = 6;
            break;
         }

         sa[var0][4] = var13;
         break;
      case 26:
         int var18 = 0;
         switch(C.ab) {
         case 0:
            var18 = 7;
            break;
         case 1:
            var18 = 39;
            break;
         case 2:
            var18 = 6;
            break;
         }

         sa[var0][4] = var18;
      case 28:
         sa[var0][25] = C.lb[0];
         sa[var0][29] = C.lb[1];
         sa[var0][33] = C.lb[2];
         sa[28][58] = C.kb[C.ab] == null?190:198;
         sa[28][67] = C.kb[C.ab] == null?-1:2;
         sa[28][65] = C.kb[C.ab] == null?-1:4;
         break;
      case 29:
         fb = false;
         int[] var19;
         var19 = null;
         if(C.fA != null) {
            var19 = C.fA;
         } else if(C.dA != null) {
            var19 = C.dA;
         }

         sa[var0][0] = C.da[var19[0]][10];
         sa[var0][4] = 393334 + C.da[var19[0]][4];
         sa[var0][7] = -1;
         e[10] = -1;
         e[34] = -1;
         break;
      case 30:
         int[][] var8 = C.db == 0?C.ta:C.va;
         int[] var20;
         var20 = sa[30] = new int[(var8.length + 2) * 4];
         if(var8.length > 0) {
            var1 = 8;

            for(int var21 = 0; var21 < var8.length; ++var21) {
               if(var8[var21][14] == 1) {
                  var20[var1++] = C.da[var8[var21][0]][10];
                  if(var8[var21][13] >= 0) {
                     var20[var1++] = 17;
                  } else {
                     var20[var1++] = 15;
                  }

                  var20[var1++] = 120;
                  var20[var1++] = var21;
               }
            }
         }

         var20[0] = 393324;
         var20[2] = 92;
         var20[5] = 27;
         var20[6] = 66;
         var20[7] = 2;
         break;
      case 31:
         var2 = 0;
         var0 = C.db == 0?C.ra:C.sa;
         boolean var10 = var0 == 2?true:(var0 != 6 && var0 != 0?true:true);

         Vector var12;
         for(var12 = new Vector(); var2 < C.da.length; ++var2) {
            if(var10 == (C.da[var2][1] == 2?true:(C.da[var2][1] != 6 && C.da[var2][1] != 0?true:true)) && C.cb >= C.da[var2][2]) {
               var0 = -1;
               switch(C.ab) {
               case 0:
               case 3:
                  var0 = C.da[var2][7];
                  break;
               case 1:
                  var0 = C.da[var2][8];
                  break;
               case 2:
                  var0 = C.da[var2][9];
                  break;
               }

               if(var0 != -1 && C.bb >= var0) {
                  int[][] var10000;
                  if(C.db == 0) {
                     var10000 = C.xA;
                  } else {
                     var10000 = C.yA;
                  }

                  if(C.db == 0) {
                     var10000 = C.vA;
                  } else {
                     var10000 = C.wA;
                  }

                  Integer var11 = new Integer(var2);
                  var12.addElement(var11);
               }
            }
         }

         var4 = var12.size();
         var7 = sa[31] = new int[(var4 + 2) * 4];
         if(var4 > 0) {
            var2 = 8;

            for(var1 = 0; var1 < var4; ++var1) {
               var0 = ((Integer)var12.elementAt(var1)).intValue();
               var7[var2++] = C.da[((Integer)var12.elementAt(var1)).intValue()][10];
               var7[var2++] = C.B(var0, false);
               var7[var2++] = 120;
               var7[var2++] = var0;
            }
         }

         var7[0] = 393317;
         var7[2] = 92;
         var7[5] = 27;
         var7[6] = 66;
         var7[7] = 2;
         break;
      case 32:  //офицеры
         var1 = 0;
         var4 = (C.db == 0)?C.ra:C.sa;
         int[][] var6 = (C.db == 0)?C.ta:C.va;
         int[] var11 = new int[3];;
         
         while (var1 < var6.length) {
            if(var6[var1][13] >= 0) {
              var11[((C.ea[var6[var1][13]][2] == 4000) ? 1 : ((C.ea[var6[var1][13]][2] == 6000) ? 2 : 3)) - 1] = 1;  
            } 
            ++var1;
         }
         //for(var7 = new int[3]; var1 < var6.length; ++var1) {
         //   if(var6[var1][13] >= 0) {
         //      var7[(C.ea[var6[var1][13]][2] == 4000?1:(C.ea[var6[var1][13]][2] == 6000?2:3)) - 1] = 1;
         //   }
         //}

         var0 = 10 - (var11[0] * 4 + var11[1] * 3 + var11[2] * 3);
         int[][] var16 = HG.sa;
         int var14 = 32;
         var9 = new int[(var0 + 2) * 4]; //var9 = sa[32] = new int[(var0 + 2) * 4];
         var16[var14] = var9;
         int[] var15 = var9;
         int var17 = 8;
         if(var0 > 0) {
            for(var0 = 0; var0 < C.ea.length; ++var0) {
               if(((C.ea[var0][1] == 2)? 12:((C.ea[var0][1] == 6 || C.ea[var0][1] == 0)? 10 : 11)) == ((var4 == 2)? 12 :((var4 == 6 || var4 == 0) ? 10 : 11)) && var11[((C.ea[var0][2] == 4000) ? 1 : ((C.ea[var0][2] == 6000) ? 2 : 3)) - 1] == 0) {  //if((C.ea[var0][1] == 2?true:(C.ea[var0][1] != 6 && C.ea[var0][1] != 0?true:true)) == (var4 == 2?true:(var4 != 6 && var4 != 0?true:true)) && var7[(C.ea[var0][2] == 4000?1:(C.ea[var0][2] == 6000?2:3)) - 1] == 0) {
                  var15[var17++] = C.ea[var0][0];
                  var15[var17++] = C.B(var0, true);
                  var15[var17++] = 121;
                  var15[var17++] = var0;
               }
            }
         }
//вместо var9 поставлено var15
         var15[0] = 393302;
         var15[2] = 92;
         var15[5] = 27;
         var15[6] = 66;
         var15[7] = 2;
         break;
      case 33:
         sa[var0][14] = (C.zA & 15) == 3?190:101;
         break;
      case 35:
         L(9);
         break;
      case 36:
         L(8);
         break;
      case 37:
         sa[var0][13] = C.eb > C.mb?0:C.mb - C.eb;
         sa[var0][17] = C.eb > C.nb?0:C.nb - C.eb;
         sa[var0][21] = C.eb > C.ob?0:C.ob - C.eb;

         for(int var5 = 0; var5 < 3; ++var5) {
            var4 = (var5 << 2) + 9;
            int var3 = C.lA[var5];
            var2 = 0;
            var1 = -1;
            switch(var3) {
            case 0:
               var2 = 393268;
               break;
            case 1:
               var2 = 393263;
               break;
            case 2:
               if(C.mA == 0) {
                  var2 = 393265;
               } else {
                  var2 = 393267;
               }

               var1 = 393266;
            }

            sa[var0][(var4 + 1 << 2) + 0] = var2;
            sa[var0][(var4 + 1 << 2) + 1] = var3;
            if(var1 != -1) {
               sa[var0][(var4 + 2 << 2) + 0] = var1;
               sa[var0][(var4 + 2 << 2) + 2] = 198;
            } else {
               sa[var0][(var4 + 2 << 2) + 2] = 190;
            }
         }

         return;
      case 38:
      case 39:
         var0 = C.db == 0?C.ra:C.sa;
         var0 = var0 == 2?12:(var0 != 6 && var0 != 0?11:10);
         sa[ua][4] = C.db == 0?393293:393295;
         sa[ua][3] = var0;
         C.zA |= 16;
      }

   }

   public static String[] A(int var0, Font var1, int var2) {
      String[] var3 = null;
      int[] var4;
      String var5;
      switch(var0) {
      case 1:
         char[] var6 = (new String("05-04-2007")).toCharArray();
         var5 = "(c) 20" + var6[8] + var6[9] + ",\n" + H(89) + "\n" + H(0) + "\n" + "v." + qb.getAppProperty("MIDlet-Version");
         var5 = var5 + "\n" + H(4);
         var3 = A(var5, var1, var2, 0);
         break;
      case 2:
         var5 = H(327704) + ", " + H(327711) + ": " + H(327734) + ", " + H(327726) + "\n\n" + H(327705) + ", " + H(327713) + ": " + H(327735) + ", " + H(327727) + "\n\n" + H(327706) + ", " + H(327709) + ": " + H(327736) + ", " + H(327728) + "\n\n" + H(327703) + ", " + H(327715) + ": " + H(327731) + ", " + H(327723) + "\n\n" + H(327708) + ": " + H(327737) + ", " + H(327729) + "\n\n" + H(327710) + ": " + H(327738) + ", " + H(327730) + "\n\n" + H(327714) + ": " + H(327733) + ", " + H(327724) + "\n\n" + H(327716) + ": " + H(327732) + ", " + H(327725) + "\n\n\n" + H(327712) + ": " + H(32) + ", " + H(327722) + "\n\n" + H(327718) + ": " + H(327698) + "\n\n" + H(327717) + ": " + H(327697) + "\n\n" + H(327707) + ": " + H(327699) + "\n\n" + H(327720) + ": " + H(327700) + "\n\n" + H(327721) + ": " + H(2);
         var3 = A(var5, var1, var2, 0);
      case 3:
      case 4:
      case 5:
      default:
         break;
      case 6:
         var5 = H(86) + ": " + C.lb[3];
         var3 = A(var5, var1, var2, 0);
         break;
      case 7:
         var5 = H(85) + ": " + C.lb[4];
         var3 = A(var5, var1, var2, 0);
         break;
      case 8:
         var0 = eb[15][3];
         var4 = C.db == 0?C.ta[var0]:C.va[var0];
         var5 = H(393351) + ": " + C.G(var4);
         var3 = A(var5, var1, var2, 0);
         break;
      case 9:
         var0 = eb[17][3];
         var4 = C.db == 0?C.ta[var0]:C.va[var0];
         var5 = H(393351) + ": " + C.ea[var4[13]][2] / 2;
         var3 = A(var5, var1, var2, 0);
      }

      return var3;
   }

   public static int A(Graphics var0, String var1, int var2, int var3) {
      int var5 = C.i[1].getHeight();
      int var4 = var5 + 7;
      if(var0 != null) {
         var0.setClip(0, 0, C.f, C.g);
         var0.setClip(0, 0, C.f, C.g);
         A(7, var2 + 1, var3 + 1, C.f - 2 * (var2 + 2), var5 + 2 - 0, 20);
         A(var1, 7, 1, var0, 3, C.h[43], C.h[44]);
         A(7, -1, 0, false);
      }

      return var4;
   }
//Заставка
   public static int A(Graphics var0) {
      int var8;
      int var9;
      if(C.x[19] != null) {
         var8 = ob[26][4] - 5;
         if(var0 != null) {
            short var7 = ob[20][3];
            short var1 = ob[20][4];
            int var6 = var7 - ob[19][3] >> 1;
            int var5 = var1 - ob[19][4] >> 1;
            short var4 = ob[26][3];
            short var3 = ob[26][4];
            int var2 = C.g - var1;
            var0.setClip(0, 0, C.f, C.g);

            for(var9 = 0; var9 < C.f; var9 += var4) {
               C.A(var0, 26, 1, var9, C.g - var3 + 5);
            }

            if(lA != -1 && nA != -1) {
               C.A(var0, 20, 0, 0, var2);               //вывод квадратной кнопки
               C.A(var0, 19, nA, var6, var2 + var5);    //вывод значка галочки на кнопке в главном меню
               
            }

            if(mA != -1 && oA != -1) {
               C.A(var0, 20, 0, C.f - var7, var2);                      //вывод квадратной кнопки
               C.A(var0, 19, oA, C.f - var7 + var6, var2 + var5);       //вывод значка возврат на кнопке в главном меню
               
            }
         }
      } else {
         var8 = C.i[2].getHeight();
         var9 = C.g - var8;
         if(var0 != null) {
            var0.setClip(0, 0, C.f, C.g);
            var0.setColor(C.h[6]);
            var0.fillRect(0, var9, C.f, var8);
            if(lA != -1 && pA != null) {
               C.A(var0, pA, 0, var9, 2, 0, 3, 0, 6);
            }

            if(mA != -1 && qA != null) {
               C.A(var0, qA, C.f, var9, 2, 2, 3, 0, 6);
            }
         }
      }

      return var8;
   }
//Обработка пунктов меню
   static void T(int var0) {
      int var1;
      switch(var0) {
      case 1:
         C.ab = sa[ua][(va << 2) + 3];
         if(A(2 + C.ab)) {
            C(393328, 82, 37);
            A(4, -1, 0, true);
            break;
         } else {
            if(C.c) {
               C.W();
            }

            C.ab = sa[ua][(va << 2) + 3];
            C.bb = 0;
            C.zA = 1;
            C.lb = new int[5];
            var0 = C.gb.length;

            while(true) {
               --var0;
               if(var0 < 0) {
                  C.A(13);
                  ta = false;
                  return;
               }

               C.gb[var0] = false;
            }
         }
      case 2:
         H();
         qb.destroyApp(false);
         break;
      case 3:   //удалить пункты меню на экране (показать игровое поле)
         C.W();
         aA.removeAllElements();
         ta = false;
         C.pb = true;
         break;
      case 4:
         C.I();
         var0 = 4;

         while(true) {
            --var0;
            if(var0 < 0) {
               aA.removeAllElements();
               AA(0);
               H();
               L(1);
               break;
            }

            C.uB[var0][0] = 0;
         }
      case 5:
      case 6:
      case 7:
      case 8:
      case 10:
      case 14:
      case 15:
      case 18:
      case 19:
      case 20:
      case 21:
      default:
         break;
      case 9:
         S();
         break;
      case 11:  //звук в игре???
         if(D(-1) && D(-1)) {
            A(1, 1);
            D();
         }

         AA(0);    //обнуление сообщений меню
         L(0);
         break;
      case 12:
         A(-1, 1);
         D();
         AA(0);
         break;
      case 13:
         if(nb != null) {
            if(X()) {
               C.A(7);
            } else {
               AA(0);    //обнуление сообщений меню
               H();
               L(1);
            }
         } else {
            AA(10);
         }
         break;
      case 16:
         D();
         if(D(-1)) {
            H();
         } else if(R(0) != 0) {
            if(D(0)) {
               if(((Integer)aA.elementAt(aA.size() - 3)).intValue() == 0) {
                  L(1);
               } else {
                  L(2);
               }
            } else {
               H();
            }
         }

         S();
         break;
      case 17:
         C();
         S();
         break;
      case 22:
         aA.push(new Integer(ua));
         aA.push(new Integer(va));
         aA.push(new Integer(oa));
         AA(33);
         break;
      case 23:  //выбор пунктов меню
         Q();
         break;
      case 24:
         if(!S()) {
            T(3);
         }
         break;
      case 25:
         ta = false;
         C.A(14);
         break;
      case 26:
         if((C.zA & 15) == 1) {
            if(C.bb < 11) {
               C.ua = (int[][])null;
               ta = false;
               C.A(14);
            } else {
               AA(28);
            }
         } else {
            aA.removeAllElements();
            AA(0);
            H();
            L(1);
         }
         break;
      case 27:
         ta = false;    //нажата кнопка отмена
         A(false);
         fb = true;
         break;
      case 28:
         ta = false;
         aA.removeAllElements();
         eb[1][2] = 81;
         C.zA = 1;
         C.ab = sa[ua][(va << 2) + 3];
         C.j = new A(pb, 3, 18);
         break;
      case 29:
         ta = false;
         aA.removeAllElements();
         eb[1][2] = 81;
         C.zA = 2;
         C.j = new A(pb, 4, 18);
         break;
      case 30:
         ta = false;
         aA.removeAllElements();
         eb[1][2] = 18;
         C.zA = 3;
         C.j = new A(pb, 5, 18);
         break;
      case 31:
         C(81, 88, 35);
         A(4, -1, 0, true);
         break;
      case 32:
         C.I();
         aA.removeAllElements();
         AA(0);
         H();
         L(1);
         break;
      case 33:
         C.kb[C.ab] = C.lb;
         C.J();
         aA.removeAllElements();
         AA(0);
         break;
      case 34:
         C.K();
         C.ab = sa[ua][(va << 2) + 3];
         C.lb = C.kb[C.ab];
         aA.push(new Integer(ua));
         aA.push(new Integer(va));
         aA.push(new Integer(oa));
         if(C.lb == null) {
            AA(25);
         } else {
            AA(26);
         }
         break;
      case 35:
         C.W(); //перед стартом сценария (вывод брифинга)
         aA.removeAllElements();
         ta = false;
         if(C.pb) {
            GA(0);
            A(false);
            fb = true;
         }

         C.pb = true;
         break;
      case 36:
         int[] var2 = null;
         if(C.fA != null) {
            var2 = C.fA;
         } else if(C.dA != null) {
            var2 = C.dA;
         }

         var0 = C.db == 0?C.ra:C.sa;
         boolean var4 = var0 == 2?true:(var0 != 6 && var0 != 0?true:true);
         var0 = var2[19];
         if(var0 >= 0 && C.ub == -1 && (C.da[var0][1] == 2?true:(C.da[var0][1] != 6 && C.da[var0][1] != 0?true:true)) == var4) {
            C.ub = var2[0];
         }

         if(var0 >= 0 && C.ub == var2[0]) {
            if(C.ub >= 0) {
               var2[19] = var2[0];
               var2[0] = var0;
               AA(29);
            }
         } else {
            T(37);
         }
         break;
      case 37:
         if(C.ub >= 0) {
            int[] var3 = null;
            if(C.fA != null) {
               var3 = C.fA;
            } else if(C.dA != null) {
               var3 = C.dA;
            }

            if(C.ub != var3[0]) {
               var3[19] = var3[0];
               var3[0] = C.ub;
            }

            C.ub = -1;
         }

         T(24);
         break;
      case 38:
      case 39:  //сценарий???
         var1 = sa[ua][(va << 2) + 3];
         C.ab = C.aa[var1][2];
         if(var0 == 38) {
            if(C.aa[var1][3] == 1 && A(5)) {
               C(393328, 82, 38);
               A(4, -1, 0, true);
               break;
            }
         } else if(A(6)) {
            C(393328, 82, 39);
            A(4, -1, 0, true);
            break;
         }
      case 40:
         ta = false;
         ib.removeAllElements();
         GA(29);
         fb = true;
         break;
      case 41:
         if(C.H(2)) {
            aA.push(new Integer(ua));
            aA.push(new Integer(va));
            aA.push(new Integer(oa));
            AA(13);
         } else {
            T(1);
         }
         break;
      case 42:
         if(C.H(3)) {
            aA.push(new Integer(ua));
            aA.push(new Integer(va));
            aA.push(new Integer(oa));
            AA(14);
         } else {
            T(1);
         }
         break;
      case 43:
         if(C.H(4)) {
            aA.push(new Integer(ua));
            aA.push(new Integer(va));
            aA.push(new Integer(oa));
            AA(15);
         } else {
            T(1);
         }
         break;
      case 44:
         var1 = sa[ua][(va << 2) + 3];
         if(var0 != 38 || C.aa[var1][3] == 1) {
            if(C.c) {
               C.W();
            }

            C.ab = C.aa[var1][2];
            C.bb = var1 % 11;
            if(var0 == 38) {
               C.zA = 2;
            } else if(var0 == 44) {
               C.zA = 4;
            } else {
               C.zA = 3;
            }

            C.A(13);
            ta = false;
         }
      }

   }

   public static byte U(int var0) {
      byte var1 = 2;
      switch(var0) {
      case 0:
         if(D(-1)) {
            var1 = 1;
         }
         break;
      case 1:
         if(D(0)) {
            var1 = 1;
         }
         break;
      case 2:
         if(D(1)) {
            var1 = 1;
         }
         break;
      case 3:
         if(D(2)) {
            var1 = 1;
         }
         break;
      case 4:
         if(!D(2)) {
            var1 = 1;
         }
      case 5:
      case 6:
      default:
         break;
      case 7:
         if(D(4)) {
            var1 = 1;
         }
         break;
      case 8:
         if(!D(4)) {
            var1 = 1;
         }
         break;
      case 9:
         if(D(5)) {
            var1 = 1;
         }
         break;
      case 10:
         if(!D(5)) {
            var1 = 1;
         }
      }

      return var1;
   }

   public static void C(int var0, int var1) {
      switch(var0) {
      case 0:
         if(var1 == 1) {
            A(-1, 1);
         }
         break;
      case 1:
         if(var1 == 1) {
            A(0, 1);
         }
         break;
      case 2:
         if(var1 == 1) {
            A(1, 1);
         }
         break;
      case 3:
         if(var1 == 1) {
            A(2, 1);
         }
         break;
      case 4:
         if(var1 == 1) {
            A(2, 0);
         }
      case 5:
      case 6:
      default:
         break;
      case 7:
         if(var1 == 1) {
            A(4, 1);
         }
         break;
      case 8:
         if(var1 == 1) {
            A(4, 0);
         }
         break;
      case 9:
         if(var1 == 1) {
            A(5, 1);
         }
         break;
      case 10:
         if(var1 == 1) {
            A(5, 0);
         }
      }

   }

   static int V(int var0) {
      int var1 = 0;
      int[] var5 = sa[var0];
      int var4 = var5.length / 4;
      int var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         var0 = var5[var3 + 2];
         var3 += 4;
         if(var0 > 49 && var0 < 200) {
            ++var1;
         }
      }

      return var1;
   }

   static int W(int var0) {
      int var1 = 0;
      int[] var5 = sa[var0];
      int var4 = var5.length / 4;
      int var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         var0 = var5[var3 + 2];
         var3 += 4;
         if(var0 > 99 && var0 < 150) {
            ++var1;
         }
      }

      return var1;
   }
//Извлечение текстовых сообщений из библиотеки
   static String D(int var0, int var1) {
      int[] var2 = sa[var0];
      var0 = var2[(var1 << 2) + 0];
      String var3 = H(var0);    //извлечение текстовых сообщений из библиотеки
      return var3;
   }

   static int X(int var0) {
      int var1 = -1;
      V(var0);
      int[] var4 = sa[var0];
      boolean var3 = false;

      for(int var2 = 0; !var3; ++var2) {
         var0 = (var2 << 2) + 2;
         if(var0 <= var4.length) {
            var0 = var4[var0];
            if(var0 > 99 && var0 < 150) {
               var1 = var2;
               var3 = true;
            }
         } else {
            var3 = true;
         }
      }

      return var1;
   }

   static int E(int var0, int var1) {
      if(var0 >= 0 && var0 < sa.length) {
         int[] var2 = sa[var0];

         for(var0 = 0; var0 < var2.length; var0 += 4) {
            if(var2[var0 + 0] == var1) {
               return var0 / 4;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   static void B(int var0, int var1, int var2, int var3, int var4, int var5) {
      if(var0 >= 0 && var0 < sa.length) {
         int[] var6 = sa[var0];
         int var7 = var1 * 4;
         if(var7 >= 0 && var7 <= var6.length) {
            int[] var8 = new int[var6.length + 4];
            if(var7 == 0) {
               System.arraycopy(var6, 0, var8, 4, var6.length);
            } else if(var7 == var6.length) {
               System.arraycopy(var6, 0, var8, 0, var6.length);
            } else {
               System.arraycopy(var6, 0, var8, 0, var7);
               System.arraycopy(var6, var7, var8, var7 + 4, var6.length - var7);
            }

            var8[var7 + 0] = var2;
            var8[var7 + 1] = var3;
            var8[var7 + 2] = var4;
            var8[var7 + 3] = var5;
            sa[var0] = var8;
         }
      }
   }
//Перемещение по Главному Меню вниз
   static void M() {
      boolean var0 = W(ua) > 0;
      if(var0) {
         int[] var1 = sa[ua];
         // ++va; для сенсорного меню
         if(va > xa) {
            va = xa;    //пункт меню не должен превышать доступное количество пунктов
         } else {
            ya = null;  //текст пункта меню обнуляется
            za = true;
         }

         int var2 = var1[va * 4 + 2];
         if(var2 <= 99 || var2 >= 150) {
            M();    //пункт меню недоступен, перейти на следующий пункт меню
            ++va; //для сенсорного меню
         }
      } else {
         O();   //скролинг экрана вниз
      }

   }
//Перемещение по Главному Меню вверх
   static void N() {
      boolean var0 = W(ua) > 0;
      if(var0) {
         int[] var1 = sa[ua];
         --va;
         if(va < wa) {
            va = wa;
         } else {
            ya = null;
            za = true;
         }

         int var2 = var1[va * 4 + 2];
         if(var2 <= 99 || var2 >= 150) {
            N();    //пункт меню недоступен, перейти на следующий пункт меню
         }
      } else {
         P();   //скролинг экрана вверх
      }

   }
//Перемещение по Главному Меню вниз
   static void M1() {
      boolean var0 = W(ua) > 0;
      if(var0) {
         int[] var1 = sa[ua];
         ++va;
         if(va > xa) {
            va = xa;    //пункт меню не должен превышать доступное количество пунктов
         } else {
            ya = null;  //текст пункта меню обнуляется
            za = true;
         }

         int var2 = var1[va * 4 + 2];
         if(var2 <= 99 || var2 >= 150) {
            M();    //пункт меню недоступен, перейти на следующий пункт меню
         }
      } else {
         O();   //скролинг экрана вниз
      }

   }
//Перемещение по Главному Меню вверх
   static void N1() {
      boolean var0 = W(ua) > 0;
      if(var0) {
         int[] var1 = sa[ua];
         --va;
         if(va < wa) {
            va = wa;
         } else {
            ya = null;
            za = true;
         }

         int var2 = var1[va * 4 + 2];
         if(var2 <= 99 || var2 >= 150) {
            N();    //пункт меню недоступен, перейти на следующий пункт меню
         }
      } else {
         P();   //скролинг экрана вверх
      }

   }   
//Скролинг экрана вниз
   public static void O() {
      if(pa > fA) {
         oa -= 40;
         if(oa < -(pa - fA)) {
            oa = -(pa - fA);
         }
      }

   }
//Скролинг экрана вверх
   public static void P() {
      oa += 40;
      if(oa > 0) {
         oa = 0;
      }

   }
//Скролинг экрана вниз
   public static void O1() {
      if(pa > fA) {
         oa -= 2;
         if(oa < -(pa - fA)) {
            oa = -(pa - fA);
         }
      }

   }
//Скролинг экрана вверх
   public static void P1() {
      oa += 2;
      if(oa > 0) {
         oa = 0;
      }

   }   
//Пункты меню (определение)
   static void Q() {
      if(va >= 0) { //пункты меню
         int[] var2 = sa[ua];
         int var1 = va * 4;
         int var7 = var2[var1 + 1];
         int var0 = var2[var1 + 3];
         var1 = var2[var1 + 2];
         switch(var1) {
         case 100:
            aA.push(new Integer(ua));
            aA.push(new Integer(va));
            aA.push(new Integer(oa));
            AA(var7);
            break;
         case 101:
         case 107:
            T(var7);
            break;
         case 102:
            int var6 = -1;

            int[] var8;
            int var9;
            for(var9 = 0; var9 <= va; ++var9) {
               var8 = sa[ua];
               var1 = var8[(var9 << 2) + 2];
               if(var1 == 102) {
                  ++var6;
               }
            }

            int var5 = V(ua);
            int var4 = var0;
            if(var0 >= 0) {
               int var3 = 0;

               for(var9 = 0; var9 < var5; ++var9) {
                  var8 = sa[ua];
                  var0 = var8[(var9 << 2) + 2];
                  if(var0 == 102) {
                     var0 = var8[(var9 << 2) + 3];
                     if(var0 == var4) {
                        uA[var3] = 2;
                     }

                     ++var3;
                  }
               }
            }

            uA[var6] = 1;
            C(var7, 1);
            break;
         case 108:
            ta = false;
         case 109:
            GA(var7);
            fb = true;
            break;
         case 120:
         case 121:
         case 194:
            eb[var7][3] = var0;
            GA(var7);
            fb = true;
         }
      }

   }
//Загрузка в меню пунктов (рекрутировать)
   static void Y(int var0) {
      oa = 0;
      bA = 0;
      cA = 0;
      dA = C.f - 0;
      eA = C.g - 0;
      mA = -1;
      lA = -1;
      oA = -1;
      nA = -1;
      qA = null;
      pA = null;
      xa = -1;
      wa = -1;
      ya = null;
      S(var0);
      int[] var8 = sa[ua];
      tA = V(var0);
      if(rA == null || rA.length != tA) {
         rA = new String[tA];
      }

      int var4 = 0;
      int var3 = 0;

      int var1;
      int var2;
      for(var2 = 0; var2 < tA; ++var2) {
         var1 = var8[(var2 << 2) + 2];
         switch(var1) {
         case 102:
            ++var3;
            break; //должен быть выход
         case 188:
         case 189:
         case 198:
         case 199:
            ++var4;
            break;
         default:
            rA[var2] = D(var0, var2);   //выводимые на экран сообщения
         }

         if(var1 > 99 && var1 < 150) {
            if(wa == -1) {
               wa = var2;
            }

            xa = var2;
         }
      }

      if(var3 > 0) {
         if(uA == null || uA.length != var3) {
            uA = new byte[var3];
         }

         int var10 = 0;

         for(var2 = 0; var2 < tA; ++var2) {
            var1 = var8[(var2 << 2) + 2];
            if(var1 == 102) {
               var1 = var8[(var2 << 2) + 1];
               uA[var10] = U(var1);
               ++var10;
            }
         }
      }

      pa = 0;
      if(var4 > 0) {
         sA = new String[var4][];
      }

      int var7 = 0;
      int var6 = 0;
      int var12 = 0;
      for(int var5 = 0; var5 < tA; ++var5) {    //загрузка сообщений
         var12 = var8[(var5 << 2) + 2];
         boolean var10 = var5 == va;
         int var11 = 0;
         String var9 = null;
         if(rA != null && rA[var5] != null) {
            var9 = rA[var5];
         }

         switch(var12) {
         case 66:
            qA = H(var8[(var5 << 2) + 0]);
            mA = var8[(var5 << 2) + 1];
            oA = var8[(var5 << 2) + 3];
            break;
         case 67:
            pA = H(var8[(var5 << 2) + 0]);
            lA = var8[(var5 << 2) + 1];
            nA = var8[(var5 << 2) + 3];
            break;
         case 102:
            var11 = var6++;
            break;
         case 188:
         case 189:
         case 198:
            sA[var7] = A(D(var0, var5), C.i[2], dA - cA, 0);
            var11 = var7++;
            break;
         case 199:
            var11 = var8[(var5 << 2) + 1];
            sA[var7] = A(var11, C.i[2], dA - cA);
            var11 = var7++;
            break;
         }

         pa += A((Graphics)null, var5, var12, var9, var11, 0, var10, var8[(var5 << 2) + 1], var8[(var5 << 2) + 3]);
      }

      eA -= A((Graphics)null);
      fA = eA - bA;
      gA = dA - cA;
      oa = 0;
      va = X(var0);
      if(lA == -1 && W(ua) > 0) {
         lA = 23;
         pA = H(26);
         nA = 0;
      }

      if(mA == -1 && aA.size() > 0) {
         mA = 9;
         qA = H(8);
         oA = 2;
      }

   }
//Обнуление сообщений меню
   static void Z(int var0) {
      uA = null;
      rA = null;
      sA = (String[][])null;
      tA = 0;
   }

   static void R() {}
//Заставка для меню игры (вывод фона карты из игры)
   static void B(Graphics var0) {
      if(ua != -1) {
         int[] var10 = sa[ua];
         int var9 = tA;
         int var8 = oa + bA;
         int var7 = 0;
         int var6 = 0;

         for(int var5 = 0; var5 < var9; ++var5) {
            int var4 = var10[(var5 << 2) + 2];
            boolean var3 = var5 == va;
            int var2 = 0;
            String var1 = null;
            if(rA != null && rA[var5] != null) {
               var1 = rA[var5];
            }

            switch(var4) {
            case 102:
               var2 = var6++;
               break;
            case 188:
            case 189:
            case 198:
            case 199:
               var2 = var7++;
            }

            hA = cA - 1;
            iA = (var8 < bA?bA:var8) - 1;
            jA = gA + 2;
            kA = eA - iA + 2;
            var0.setClip(hA, iA, jA, kA);
            var8 += A(var0, var5, var4, var1, var2, var8, var3, var10[(var5 << 2) + 1], var10[(var5 << 2) + 3]);
         }

         ra = 0;
         A(var0);
         za = false;
      }
   }
//Проверка и обнуление скролинга экрана
   public static void A(int var0, int var1, int var2) {
      var2 = pa - fA;
      var0 = bA + fA + oa - var1 - var0;
      if(var0 < -var2) {
         var0 = -var2;
      }

      if(var0 > 0) {
         var0 = 0;
      }

      var0 = oa - var0 >> 1;
//      oa -= var0; //чтобы скролинг не становился 0
      za = za || var0 != 0;
   }

   static boolean S() {
      if(!aA.empty()) {
         int var2 = ((Integer)aA.pop()).intValue();
         int var1 = ((Integer)aA.pop()).intValue();
         int var0 = ((Integer)aA.pop()).intValue();
         AA(var0);
         va = var1;
         oa = var2;
         return true;
      } else {
         return false;
      }
   }

   static void AA(int var0) {
      Z(ua);    //обнуление сообщений меню
      ua = var0;
      Y(var0);
   }

   public static void A(Graphics var0, int var1, int var2, int var3, boolean var4) {
      var0.setClip(0, 0, C.f, C.g);
      C.A(var0, 10, var3, var1, var2);
      if(var4) {
         C.A(var0, 6, 1, var1, var2);
      }

   }
//Гексовое меню в игре
   public static int[] B(int var0, int var1, int var2) {
      int[] var3 = new int[]{var0, var1};
      switch(var2) {
      case 0:
         var3[0] = var0 - 90;   //var3[0] = var0 - 50;
         break;
      case 1:
         var3[0] = var0 - 45;   //var3[0] = var0 - 25;
         var3[1] = var1 - 25;   //var3[1] = var1 - 16;
      case 2:
      default:
         break;
      case 3:
         var3[1] = var1 + 50;   //var3[1] = var1 + 32;
         break;
      case 4:
         var3[0] = var0 - 45;   //var3[0] = var0 - 25;
         var3[1] = var1 + 50 + 25;  //var3[1] = var1 + 32 + 16;
         break;
      case 5:
         var3[0] = var0 - 90;   //var3[0] = var0 - 50;
         var3[1] = var1 + 50;   //var3[1] = var1 + 32;
      }

      return var3;
   }

   public static int A(Graphics var0, String[] var1, int var2) {
      if(var1 == null) {
         return 0;
      } else {
         Font var3 = C.i[2];
         int var7 = var3.getHeight();
         int var6 = var1.length;
         int var5 = var7 * var6;
         if(var0 != null) {
            int var4 = var2;
            var0.setColor(C.h[34]);
            var0.setFont(var3);

            for(int var8 = 0; var8 < var6; ++var8) {
               if(var2 < C.g && var4 > 0) {
                  var2 -= 3;
                  var0.drawString(var1[var8], 5 + ob[18][3], var4, 20);
               }

               var4 += var7;
            }
         }

         return var5;
      }
   }
//Показ пунктов меню
   static void BA(int var0) {
      int var1;
      int var2;
      int[][] var6;
      int[] var9;
      switch(var0) {
      case 1:
         ib.removeAllElements();
         A(true);
         aA.removeAllElements();
         C.A(11);
         ta = true;
         break;
      case 2:
         if(gb != 0 && gb != 9 && gb != 6) {
            A(false);
         } else {
            ib.removeAllElements();
            A(true);
         }
         break;
      case 3:
         boolean var10 = false;
         var6 = C.db == 0?C.ta:C.va;

         for(var0 = 0; var0 < var6.length; ++var0) {
            if(var6[var0][7] == 0) {
               var10 = true;
               break;
            }
         }

         if(var10) {
            C(393328, 393259, 36);
            break;
         }

        
      case 36:  //закончить ход
         ib.removeAllElements();
         A(true);   //очистить меню
         var6 = C.db == 0?C.ta:C.va;    //выбор стороны
         if((C.zA & 15) == 3 && (var6 == null || var6.length == 0)) {
            GA(27);
            fb = true;
         } else {
            for(var0 = 0; var0 < var6.length; ++var0) {
               var6[var0][7] = 0;
               var6[var0][16] = 0;
               var6[var0][26] = 0;
               var6[var0][20] = 0;
            }

            if((C.eb + 1 > C.ob || C.L()) && ((C.zA & 15) == 3 || C.db == 0)) {
               C.T();
            } else {
               if(C.db == 1) {
                  C.o = 22;
                  C.B();
                  C.o = 18;
               }

               C.db ^= 1;
               C.dA = null;
               C.fA = null;
               C.A(C.db, (int[])null);
               if((C.zA & 15) == 3) {
                  eb[28][4] = C.db == 0?393292:393294;
                  GA(28);
                  fb = true;
               } else {
                  C.A(22);
               }
            }
         }
         break;        
//      case 4:
//      case 8:
//      case 9:
//      case 11:
//      case 12:
//      case 41:
//      default:
//         break;
      case 5:
         ib.removeAllElements();
         A(true);
         aA.removeAllElements();
         AA(0);
         H();
         L(1);
         break;
      case 6:
         ib.removeAllElements();
         A(true);
         aA.removeAllElements();
         AA(5);
         break;
      case 7:
         ib.removeAllElements();
         A(true);
         aA.removeAllElements();
         AA(3);
         break;
      case 10:
         C.qb = true;
         ib.push(new Integer(gb));
         ib.push(new Integer(hb));
         fb = false;
         C.B(21, 0, 0, false);
         break;
      case 13:
         ib.removeAllElements();
         A(true);
         C.dA = null;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 14:
         ib.removeAllElements();
         A(true);
         if(C.dA != null) {
            C.dA[21] = 0;
            if(C.dA != null) {
               C.M(C.dA);
            }

            C.dA[24] = 1;
         }

         C.dA = null;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 15:
         ib.removeAllElements();
         A(true);
         if(C.dA != null) {
            C.L(C.dA);
            C.dA[7] = 1;
            C.dA[21] = 0;
            C.dA[24] = 0;
         }

         C.dA = null;
         break;
      case 16:
         ib.removeAllElements();
         A(true);
         if(C.dA != null) {
            C.K(C.dA);
            C.dA[7] = 1;
            C.dA[21] = 0;
            C.dA[24] = 0;
         }

         C.dA = null;
         break;
      case 17:
         ib.removeAllElements();
         A(true);
         if(C.dA != null) {
            C.M(C.dA);
         }

         for(var2 = 0; var2 < 2; ++var2) {
            var6 = var2 == 0?C.ta:C.va;

            for(var0 = 0; var0 < var6.length; ++var0) {
               if(var6[var0][18] == 1 && C.A(var6[var0][1], var6[var0][2], 1, 3) == null) {
                  var6[var0][18] = 0;
               }
            }
         }

         C.gA = false;
         C.dA = null;
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         C.A(C.db ^ 1, (int[])null);
         break;
      case 18:
         C.la = C.na;
         C.ma = C.oa;
         C.pa[C.db] = C.xa;
         C.qa[C.db] = C.ya;
         if(C.xa != C.pa[C.db] && C.ya != C.qa[C.db]) {
            var9 = C.A(C.xa, C.ya, 1, 3);
            if(var9 != null) {
               var9[18] = 1;
            }
         }

         C.dA[1] = C.xa;
         C.dA[2] = C.ya;
         if(C.dA[19] >= 0 && C.da[C.dA[0]][5] != 5 && C.da[C.dA[0]][5] != 7) {
            C.dA[0] = C.dA[19];
            C.dA[19] = -1;
         }

         C.dA = null;
         C.gA = false;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         A(false);
         break;
      case 19:
         if(gb != 18 && gb != 22) {
            ib.removeAllElements();
            A(true);
         }

         if(C.dA != null && C.fA != null) {
            if(C.dA[16] > 0) {
               var0 = C.B(C.fA[1], C.fA[2], C.dA[1], C.dA[2]);
               C.A(C.fA, C.dA, var0);
               C.A(C.fA, C.dA);
               C.C(C.fA, C.dA);
            } else {
               C.A(C.dA, C.fA);
               var0 = C.C(C.fA);
               var0 = 100 + var0 * 30;
               var0 = (C.fA[15] & 15) * C.da[C.fA[0]][11] * 2 * var0 / 10000;
               C.uA[C.db] += var0;
               C.dA[27] = C.dA[27] + var0 / 20 + 5;
               C.C(C.dA, C.fA);
            }

            C.M(C.dA);
         }

         for(var2 = 0; var2 < 2; ++var2) {
            var6 = var2 == 0?C.ta:C.va;

            for(var0 = 0; var0 < var6.length; ++var0) {
               if(var6[var0][18] == 1 && C.A(var6[var0][1], var6[var0][2], 1, 3) == null) {
                  var6[var0][18] = 0;
               }
            }
         }

         C.dA = null;
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 20:
         var0 = eb[14][3];
         var0 = C.C(var0, false);
         if(var0 == -1) {
            ib.removeAllElements();
            A(true);
            C.dA = null;
            C.fA = C.db == 0?C.ta[0]:C.va[0];
            C.hB = 2;
            C.pa[C.db] = C.fA[1];
            C.qa[C.db] = C.fA[2];
            C.la = C.pa[C.db] - ((C.f - 25) / 45 + 2) / 2;
            C.ma = C.qa[C.db] - (C.g / 50 + 1) / 2;
            C.H();
            C.wb = -1;
            C.A(17);
            ta = false;
         } else {
            switch(var0) {
            case 1:
               CA(393329);
               return;
            case 2:
               CA(393291);
               return;
            case 3:
               CA(393330);
            }
         }
         break;
      case 21:
         if(C.fA != null) {
            aA.removeAllElements();
            AA(29);
            ta = true;
         }
         break;
      case 22:
         ib.removeAllElements();
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, C.dA);
         A(true);
         if(C.wb >= 0 && C.fA != null) {
            C.fA[13] = C.wb;
            C.fA[25] = C.wb;
            C.F(C.fA);
            C.uA[C.db] -= C.ea[C.wb][2];
         }

         C.A(18);
         break;
      case 23:
         ib.removeAllElements();
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, C.dA);
         A(true);
         C.A(18);
         break;
      case 24:
         var0 = C.da[C.fA[0]][27];
         if(var0 > 0) {
            C.fA[19] = C.fA[0];
            C.fA[0] = var0;
         } else {
            C.fA[0] = C.fA[19];
            C.fA[19] = -1;
         }

         ib.removeAllElements();
         C.gA = false;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, C.dA);
         A(true);
         break;
      case 25:
         var1 = eb[15][3];
         var9 = C.db == 0?C.ta[var1]:C.va[var1];
         C.uA[C.db] += C.G(var9);
         var0 = C.E(var1, C.db);
         if(var0 == -1) {
            A(true);
            AA(ua);
         }
         break;
      case 26:
         int var5 = eb[16][3];
         byte var4 = -1;
         if(C.ea[var5][2] > C.uA[C.db]) {
            var4 = 1;
         }

         if(var4 == -1) {
            int[][] var8 = C.db == 0?C.ta:C.va;

            for(var2 = 0; var2 < var8.length; ++var2) {
               var1 = var8[var2][1];
               var0 = var8[var2][2];
               if(var8[var2][13] == -1 && var8[var2][14] == 1) {
                  var4 = 0;
                  C.vb[var0][var1] = 3;
               }
            }

            if(var4 == -1) {
               ib.removeAllElements();
               A(true);
               CA(393276);
            } else {
               C.wb = var5;
               C.A(17);
               ta = false;
               ib.removeAllElements();
               A(true);
            }
         } else if(var4 == 1) {
            ib.removeAllElements();
            A(true);
            CA(393291);
         }
         break;
      case 27:
         var0 = eb[17][3];
         var9 = C.db == 0?C.ta[var0]:C.va[var0];
         C.uA[C.db] += C.ea[var9[13]][2] / 2;
         C.E(var9);
         A(true);
         AA(ua);
         break;
      case 28:
         ib.removeAllElements();
         A(true);
         var9 = null;
         if(C.fA != null) {
            var9 = C.A(C.pa[C.db], C.qa[C.db], 2, 3);
            if(var9 != null) {
               var9[18] = 0;
               C.fA[18] = 1;
               C.fA = var9;
            }
         }

         if(C.fA == null || var9 == null) {
            for(int var3 = 1; var3 < C.oA - 1; ++var3) {
               for(var2 = 1; var2 < C.pA - 1; ++var2) {
                  if(C.vb[var2][var3] == 2) {
                     int[] var7 = C.A(var3, var2, 2, 3);
                     if(var7 != null) {
                        var9 = C.A(var3, var2, 1, 3);
                        if(var9 != null) {
                           var9[18] = 1;
                           var7[18] = 0;
                        }
                     }
                  }
               }
            }
         }
         break;
      case 29:
         var0 = C.da[C.fA[0]][25];
         if(var0 > 0) {
            C.fA[19] = C.fA[0];
            C.fA[0] = var0;
         } else if(C.fA != null) {
            C.fA[0] = C.fA[19];
            C.fA[19] = -1;
            C.A(C.db ^ 1, C.dA);
         }

         ib.removeAllElements();
         C.gA = false;
         C.A(C.db ^ 1, C.dA);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, C.dA);
         A(true);
         break;
      case 30:
         var0 = C.da[C.fA[0]][26];
         if(var0 > 0) {
            C.fA[19] = C.fA[0];
            C.fA[0] = var0;
         } else {
            C.fA[0] = C.fA[19];
            C.fA[19] = -1;
         }

         ib.removeAllElements();
         C.gA = false;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, C.dA);
         A(true);
         break;
      case 31:
         ib.removeAllElements();
         A(true);
         if(C.dA != null && C.fA != null) {
            var0 = C.fA[24] - 2;
            if(var0 < 0) {
               var0 = 0;
            }

            C.fA[24] = var0;
            C.fA[16] = 1;
            C.fA[26] = 1;
            C.M(C.dA);
            C.dA[7] = 0;
            C.dA[20] = 99;
         }

         for(var2 = 0; var2 < 2; ++var2) {
            var6 = var2 == 0?C.ta:C.va;

            for(var0 = 0; var0 < var6.length; ++var0) {
               if(var6[var0][18] == 1 && C.A(var6[var0][1], var6[var0][2], 1, 3) == null) {
                  var6[var0][18] = 0;
               }
            }
         }

         C.dA = null;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 32:
         ib.removeAllElements();
         A(true);
         if(C.dA != null && C.fA != null) {
            C.fA[11] = 0;
            C.fA[12] = 0;
            C.M(C.dA);
            C.dA[7] = 0;
            C.dA[20] = 99;
         }

         for(var2 = 0; var2 < 2; ++var2) {
            var6 = var2 == 0?C.ta:C.va;

            for(var0 = 0; var0 < var6.length; ++var0) {
               if(var6[var0][18] == 1 && C.A(var6[var0][1], var6[var0][2], 1, 3) == null) {
                  var6[var0][18] = 0;
               }
            }
         }

         C.dA = null;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 33:
         ib.removeAllElements();
         A(true);
         if(C.dA != null && C.fA != null) {
            var0 = C.fA[13];
            if(var0 >= 0) {
               C.E(C.fA);
            }

            C.M(C.dA);
            C.dA[7] = 0;
            C.dA[20] = 99;
         }

         for(var2 = 0; var2 < 2; ++var2) {
            var6 = var2 == 0?C.ta:C.va;

            for(var0 = 0; var0 < var6.length; ++var0) {
               if(var6[var0][18] == 1 && C.A(var6[var0][1], var6[var0][2], 1, 3) == null) {
                  var6[var0][18] = 0;
               }
            }
         }

         C.dA = null;
         C.A(C.db ^ 1, (int[])null);
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         break;
      case 34:
         ib.removeAllElements();
         A(true);
         C.dA = null;
         C.A(C.db == 0?C.vA:C.wA, C.db == 0?C.xA:C.yA, C.db == 0?C.ta:C.va, (int[])null);
         C.A(18);
         break;
      case 35:
         if((C.zA & 15) != 3) {
            ta = false;
            fb = false;
            C.uA[0] = C.uA[1];
            C.A(14);
         }
         break;

      case 37:
         if(C.c) {
            C.W();
         }

         C.ab = sa[ua][(va << 2) + 3];
         C.bb = 0;
         C.zA = 1;
         C.lb = new int[5];
         var0 = C.gb.length;

         while(true) {
            --var0;
            if(var0 < 0) {
               C.A(13);
               ta = false;
               return;
            }

            C.gb[var0] = false;
         }
      case 38:
      case 39:
         var1 = sa[ua][(va << 2) + 3];
         if(var0 != 38 || C.aa[var1][3] == 1) {
            if(C.c) {
               C.W();
            }

            C.ab = C.aa[var1][2];
            C.bb = var1 % 11;
            C.zA = var0 == 38?2:3;
            C.A(13);
            ta = false;
         }
         break;
      case 40:
         var0 = 4;

         while(true) {
            --var0;
            if(var0 < 0) {
               ib.removeAllElements();
               A(true);
               fb = false;
               aA.removeAllElements();
               C.A(11);
               ta = true;
               H();
               L(1);
               break;
            }

            C.uB[var0][0] = 0;
         }
      }

   }

   static int B(int var0, boolean var1) {
      int var2 = 0;
      int[] var6 = eb[var0];
      int var5 = var6.length / 4;
      int var4 = 0;

      for(int var3 = 0; var3 < var5; ++var3) {
         var0 = var6[var4 + 2];
         var4 += 4;
         if(var1) {
            if(var0 > 79) {
               ++var2;
            }
         } else if(var0 >= 9) {
            ++var2;
         }
      }

      return var2;
   }

   static String F(int var0, int var1) {
      int[] var2 = eb[var0];
      var0 = var2[(var1 << 2) + 0];
      String var3 = H(var0);
      return var3;
   }

   static void CA(int var0) {
      eb[24][4] = var0;
      GA(24);
      fb = true;
   }

   static void C(int var0, int var1, int var2) {
      eb[25][1] = var2;
      eb[25][0] = var0;
      eb[25][4] = var1;
      GA(25);
      fb = true;
   }

   static void DA(int var0) {
      if(lb - 1 > var0) {
         int[] var1 = eb[gb];
         int var2 = var1[var0 * 4 + 2];
         if(var2 > 79) {
            hb = var0;
            V();
         }
      }

   }

   static void T() {
      
      boolean var0 = B(gb, true) > 0;
      if(var0) {
         int[] var1 = eb[gb];
         //++hb;   выбор пунктов меню по часовой стрелке (убрать, когда сенсорное управление будет)
         if(hb >= lb) { //если полный круг прошел, то сначала
            hb = 0;
         }

         int var2 = var1[hb * 4 + 2];
         if(var2 > 0 && var2 < 80) {
            
            ++hb; //сделать так(когда сенсорное управление будет): 
            T();
            
         }
      }

      if(db > bb) {
         mb -= 40;
         if(mb < -(db - bb)) {
            mb = -(db - bb);
         }
      }

   }

   static void U() {
      boolean var0 = B(gb, true) > 0;
      if(var0) {
         int[] var1 = eb[gb];
         --hb;          //выбор пунктов меню против часовой стрелки
         if(hb < 0) {   //если полный круг прошел, то сначала
            hb = lb;
            --hb;
         }

         int var2 = var1[hb * 4 + 2];
         if(var2 > 0 && var2 < 80) {
            U();
         }
      }

      mb += 40;
      if(mb > 0) {
         mb = 0;
      }

   }
//Выбор действия, если нажата кнопка в пункте меню
   static void V() {
      if(hb >= 0) {
         int[] var3 = eb[gb];   //пункты меню
         int var2 = hb * 4;     //пункт меню (1..6) умножаем на 4
         int var1 = var3[var2 + 1]; //сообщения: 19 - нападение, 36 - закончить раунд
         int var0 = var3[var2 + 2]; //10 - закончить раунд, 81 - обычный режим, 84 - рекрутировать
         switch(var0) {
         case 10:   //закончить раунд var1=36
            BA(var1);
            break;
         case 18:
            BA(19);
            break;
         case 80:   //штаб, отмена
            if(!ib.empty() && var1 == 0) {  //отмена
               A(false);
            } else {
               ib.push(new Integer(gb));
               ib.push(new Integer(hb));
               GA(var1);
            }
            break;
         case 81:   //закончить раунд, постановка задачи
            BA(var1);
            break;
         case 84:   //подразделения, сводка, рекрутировать, закрыть игру, офицеры
            fb = false;
            ib.push(new Integer(gb));
            ib.push(new Integer(hb));
            AA(var1);
            ta = true;
         }
      }

   }

   static void EA(int var0) {
      vA = (C.pa[C.db] - C.la + 1) * 45 + -23 + C.offset_x;
      //vA = (C.pa[C.db] - C.la + 1) * 25 + -23;
      wA = (C.qa[C.db] - (C.pa[C.db] + 1 & 1) - C.ma) * 50 + ((C.pa[C.db] + 1 & 1) == 1?25:0) + -16 + C.offset_y;
      //wA = (C.qa[C.db] - (C.pa[C.db] + 1 & 1) - C.ma) * 32 + ((C.pa[C.db] + 1 & 1) == 1?16:0) + -16;
      mb = 0;
      int[] var5 = eb[gb];
      lb = B(var0, false);
      if(jb == null || jb.length != lb) {
         jb = new String[lb];
      }

      int var4 = 0;

      int var1;
      int var2;
      for(var2 = 0; var2 < lb; ++var2) {
         var1 = var5[(var2 << 2) + 2];
         switch(var1) {
         case 14:
         case 15:
            ++var4;
            break;
         case 82:
         case 83:
            var1 = var5[(var2 << 2) + 0];
            jb[var2] = H(var1);
            break;
         default:
            jb[var2] = F(var0, var2);
         }
      }

      if(var4 > 0) {
         kb = new String[var4][];
         int var3 = 0;

         for(var2 = 0; var2 < lb; ++var2) {
            var1 = var5[(var2 << 2) + 2];
            if(var1 == 14) {
               kb[var3] = A(F(var0, var2), C.i[2], C.f - 10 - ob[18][3] - ob[27][3], 0);
               ++var3;
               popup_menu = true; //триггер всплывающего меню
            } else if(var1 == 15) {
               var1 = var5[(var2 << 2) + 1];
               kb[var3] = A(var1, C.i[2], C.f - 10 - ob[18][3] - ob[27][3]);
               ++var3;
               popup_menu = true; //триггер всплывающего меню
            }
         }
      }

      hb = 1;
      switch(var0) {
      case 1:
         hb = 2;
      case 2:
      case 3:
      case 4:
      case 7:
      case 8:
      case 9:
      case 10:
      case 21:
      case 26:
      default:
         break;
      case 5:
         if(var5[6] > 0 && var5[6] < 80) {
            hb = 0;
         }
         break;
      case 6:
         hb = 0;
         break;
      case 11:
      case 12:
      case 13:
         hb = 1;
         break;
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 22:
      case 23:
      case 24:
      case 25:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
         vA = 5;
         var2 = 0;

         for(var0 = 0; var0 < kb.length; ++var0) {
            var2 += kb[var0].length;
         }

         var1 = 0;

         short var6;
         for(var6 = ob[18][4]; C.i[2].getHeight() + 0 >= var6 + var1; var1 += 3) {
            ;
         }

         var0 = 2 * var6 + ob[19][4] + var2 * C.i[2].getHeight() + 11 + var1;
         wA = (C.g - var0) / 2;
         if(wA < 0) {
            wA = 0;
         }

         xA = wA + (ob[18][4] << 1) + var1;
         yA = C.g - wA - ob[18][4] - 5;
         zA = vA + ob[18][3];
         ab = C.f - vA - ob[18][3];
         bb = yA - xA;
         cb = ab - zA;
         db = 0;

         for(var1 = 0; var1 < var4; ++var1) {
            String[] var7 = kb[var1];
            db += A((Graphics)null, var7, 0);
         }

         hb = 0;
      }

   }

   static void FA(int var0) {
      switch(var0) {
      case 18:
         BA(19);
         break;
      case 19:
      case 20:
         if(C.dA != null && C.o == 18 && (C.zA & 16) == 0 && C.A(0, 100) < 20) {
            int var1 = C.da[C.dA[0]][23];
            var0 = C.da[C.dA[0]][22];
            if(C.dA[6] < 10) {
               C.dA[6] = 10;
               eb[23][4] = 393273;
               C.fb = 1;
            } else if(var1 > 0 && C.dA[12] < var1) {
               C.dA[12] = var1;
               eb[23][4] = 393271;
               C.fb = 2;
            } else if(var0 > 0 && C.dA[11] < var0) {
               C.dA[11] = var0;
               eb[23][4] = 393272;
               C.fb = 3;
            }
         }
      case 21:
      default:
         break;
      case 22:
         if(C.eA != null && C.cA != null) {
            var0 = C.B(C.eA[1], C.eA[2], C.cA[1], C.cA[2]);
            C.A(C.eA, C.cA, var0);
            C.A(C.eA, C.cA);
            C.C(C.eA, C.cA);
         }
      }

      jb = null;    //очистить названия меню
      kb = (String[][])null;
      lb = 0;
      popup_menu = false;   //триггер всплывающего меню (закрытие окна)
   }

   static void C(Graphics var0) {
      if(gb != -1) {
         int[] var10 = eb[gb];
         int var9 = wA + 0;
         int var8 = 0;

         for(int var7 = 0; var7 < lb; ++var7) {
            String var2 = null;
            if(jb != null && jb[var7] != null) {
               var2 = jb[var7];     //название меню
            }

            int var1 = var10[(var7 << 2) + 2];
            int var3;
            boolean var11;
            int var15;
            switch(var1) {
            case 10:
               var1 = C.A(var0, vA, wA - 0, 2);
               var9 += var1 + 0;
               A(var0, var2, vA + ob[18][3], wA);
               var11 = true;
               if(ob[27] == null || ob[29] == null || C.x[29] == null || C.x[27] == null) {
                  var11 = false;
               }

               if(var11) {
                   
                  short var16 = ob[18][4];
                  short var17 = ob[18][3];
                  short var6 = ob[27][4];
                  short var18 = ob[27][3];
                  int var5 = xA - var16;
                  int var4 = yA + 5;
                  var3 = var4 - var6;
                  var15 = ab + var17 - var18;

                  for(var1 = var5; var1 < var3; var1 += var6) {
                     C.A(var0, 27, 1, var15, var1);
                  }

                  C.A(var0, 27, 0, var15, var4 - var6);
                  if(db > bb) {
                     var16 = ob[29][4];
                     C.A(var0, 29, 0, var15 + 6, -mb * (var4 - var5 - var16) / (db - bb) + var5);
                  }
               }

               checkmark_x = var15 = vA;
               checkmark_y = var1 = C.g - wA - ob[20][4];
               C.A(var0, 20, 0, var15, var1);
               cross_x = var15 = C.f - vA - ob[20][3];
               cross_y = var1 = C.g - wA - ob[20][4];
               C.A(var0, 20, 0, var15, var1);
               
               break;
            case 12:
               var15 = C.f - vA - ob[20][3] + (ob[20][3] - ob[19][3] >> 1);
               var1 = C.g - wA - ob[20][4] + (ob[20][4] - ob[19][4] >> 1);
               C.A(var0, 19, var10[(var7 << 2) + 3], var15, var1);    //вывод значка крестика во всплывающем меню
               
               break;
            case 13:
               var15 = vA + (ob[20][3] - ob[19][3] >> 1);
               var1 = C.g - wA - ob[20][4] + (ob[20][4] - ob[19][4] >> 1);
               C.A(var0, 19, var10[(var7 << 2) + 3], var15, var1);    //вывод значка галочки во всплывающем меню
               
               break;
            case 14:
            case 15:
               
               String[] var13 = kb[var8];
               ++var8;
               var0.setClip(zA, xA, cb, bb);
               var1 = A(var0, var13, var9 + mb);
               var9 += var1;
               break;
            case 17:
               if(jb[hb] != null) {
                  if(jb[hb] != C.rB[0]) {
                     C.rB[0] = jb[hb];
                     var15 = C.K(2) + 4;
                     C.i[2].stringWidth(C.rB[0]);
                     A(3, 0, C.g - 10 - var15, C.f - 0, var15 - 0, 20);
                     A(3, -1, 0, true);
                  }

                  A(C.rB, 3, 2, var0, 3, C.sA == 2?C.h[47]:C.h[45], C.sA == 2?C.h[45]:C.h[46]);
               }

               int[] var12 = B(vA, wA, hb);
               C.A(var0, 1, var12[0], var12[1], 0, 0); //вывод курсора гексового меню на экран
               
               break;
            case 18:
            case 80:
            case 81:
            case 84:
               var3 = var10[(var7 << 2) + 3];
               int[] var14 = B(vA, wA, var7);
               var11 = var1 == 18;
               A(var0, var14[0], var14[1], var3, var11); //вывод гексового меню на экран
               
            }
         }

      }
   }

   static void A(boolean var0) {
      if(!ib.empty() && !var0) {
         int var1 = ((Integer)ib.pop()).intValue();
         int var2 = ((Integer)ib.pop()).intValue();
         GA(var2);
         hb = var1;
      } else {
         fb = false;
         FA(gb);
         gb = -1;
      }

   }
//Всплывающее меню
   static void GA(int var0) {
      FA(gb);
      gb = var0;
      EA(var0);
   }
//Чтение файла /mi
   public static void W() {
      InputStream var3 = null;
      nb = null;

      int var12;
      try {
         var3 = qb.getClass().getResourceAsStream("/mi");
         byte[] var0 = new byte[2];
         var3.read(var0);
         int var2 = (var0[0] & 255) << 8 | var0[1] & 255;
         if(var2 > 0) {
            byte[] var1 = new byte[var2];
            var12 = var3.read(var1);
            if(var12 == var2) {
               nb = new String(var1);
            }
         }
      } catch (Exception var10) {
         ;
      } finally {
         try {
            var3.close();
         } catch (Exception var9) {
            ;
         }

      }

      if(nb != null) {
         var12 = E(0, 17);
         if(var12 == -1) {
            var12 = sa[0].length / 4 - 1;
         }

         B(0, var12 + 1, 22, 13, 101, -1);
      }

   }

   public static boolean X() {
      boolean var1 = false;
      if(nb != null) {
         try {
            var1 = qb.platformRequest(nb);
         } catch (Exception var2) {
            ;
         }
      }

      return var1;
   }
//Загрузка заставки игры
   public HG() {
      qb = this;
      if(pb == null) {
         pb = new C(this);
         C.h = A();
         C.i = B();
         Display.getDisplay(this).setCurrent(pb);

         try {
            C.l = Image.createImage("/65003.png");
         } catch (Exception var1) {
            C.l = null;
         }

         C.k = 0L;
         C();
         C.e = new Thread(pb);
         C.e.start();
         C.A(1);
      }

   }
//выход из игры
   public void destroyApp(boolean var1) {
      this.notifyDestroyed();
   }
//Пауза в игре
   public void pauseApp() {
      if(pb != null) {
         C.X();
      }

   }

   protected void startApp() {}

   public void commandAction(Command var1, Displayable var2) {}

}
