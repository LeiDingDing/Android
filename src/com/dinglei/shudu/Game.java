package com.dinglei.shudu;


import android.R.integer;
import android.util.Log;



public class Game {
	public String str1= "360000000004230800000004200"  
            + "070460003820000014500013020" + "001900000007048300000000045";
	public String str2= "007000400053028009420300000"  
            + "700046000000000000000280006" + "000004058100960270004000100";
	public String str3="807010500050306000000008007"
            +"080400050095000420030009070"+"600200000000701060009080105";
	public String[]sorts={str1,str2,str3};
	
	public static int pos=0;
	public int maxpos=sorts.length-1;
	MainActivity mainActivity=new MainActivity();
	public int[]data=new int[9*9];
	public int[]firstdata=new int[9*9];
	int[][][] usedAll = new int[9][9][];
	public Game(){
		 data=getData(sorts[pos]);
		 calculateAllUsedTiles(); 
		 firstdata=getData(sorts[pos]);
	}
	public Game(int pos){
		 data=getData(sorts[pos]);
		 calculateAllUsedTiles(); 
		 firstdata=getData(sorts[pos]);
	}
	 public int getfirstTitle(int x, int y)  
	    {  
	        return firstdata[x+y*9];  
	    }  
	 public int getTitle(int x, int y)  
	    {  
	        return data[x+y*9];  
	    }  
	 public String getTitleString(int x, int y)  
	    {  
	        int v = getTitle(x, y);  
	        if (v == 0)  
	        {  
	            return "";  
	        } else  
	        {  
	            return String.valueOf(v);  
	        }  
	    }  
	  
	    // ���巽�������ַ�������ת��Ϊ���ִ��������У���Ϸ��ʼ������  
	    private int[] getData(String strData)  
	    {  
	        int[] sudo = new int[strData.length()];  
	        for (int i = 0; i < sudo.length; i++)  
	        {  
	            sudo[i] = strData.charAt(i) - '0';  
	        }  
	        return sudo;  
	    }  
	    // ȡ�����е�Ԫ���֣������õ�����  
	    public void calculateAllUsedTiles()  
	    {  
	        for (int i = 0; i < 9; i++)  
	            for (int j = 0; j < 9; j++)  
	            {  
	                usedAll[i][j] = calculateUsedTile(i, j);  
	            }  
	    }  
	  
	    // ȡ��ĳ��Ԫ���У������õ�����  
	    public int[] getUsedTile(int x, int y)  
	    {  
	        return usedAll[x][y];  
	    }  
	  
	    // ����ĳһ��Ԫ���в����õ�����  
	    public int[] calculateUsedTile(int x, int y)  
	    {  
	        int[] c = new int[9];  
	  
	        // ����õ�Ԫ�������У�����������  
	        for (int i = 0; i < 9; i++)  
	        {  
	            if (i == y)  
	                continue;  
	            int t = getTitle(x, i);  
	            if (t != 0)  
	            {  
	                c[t - 1] = t;  
	            }  
	        }  
	  
	        // ����õ�Ԫ������У�����������  
	        for (int i = 0; i < 9; i++)  
	        {  
	            if (i == x)  
	                continue;  
	            int t = getTitle(i, y);  
	            if (t != 0)  
	                c[t - 1] = t;  
	        }  
	  
	        // ����С�Ź����в����õ�����  
	        int startx = (x / 3) * 3;  
	        int starty = (y / 3) * 3;  
	        for (int i = startx; i < startx + 3; i++)  
	            for (int j = starty; j < starty + 3; j++)  
	            {  
	                if (i == x && j == y)  
	                    continue;  
	                int t = getTitle(i, j);  
	                if (t != 0)  
	                    c[t - 1] = t;  
	            }  
	  
	        int numused = 0;  
	        for (int t : c)  
	        {  
	            if (t != 0)  
	                numused++;  
	        }  
	  
	        int[] c1 = new int[numused];  
	        numused = 0;  
	        for (int t : c)  
	        {  
	            if (t != 0)  
	                c1[numused++] = t;  
	        }  
	        return c1;  
	    }  
	  //����KeyDialog�е��������  
	    public boolean setTileIfValid(int x, int y, int value)  
	    {  
	        int[] tiles = getUsedTiles(x, y);//�õ������õ�����  
	        for(int i = 0; i<tiles.length; i++)  
	            Log.i("TAG", "tiles["+i+"]"+tiles[i]);  
	          
	        if (value != 0)  
	        {  
	            for (int t : tiles)  
	            {  
	                if (t == value) 
	                    return false;  
	            }  
	        }  
	        setTile(x, y, value);//����Ӧ��ֵvalue������xy��Ӧ�ĸ�����  
	        calculateAllUsedTiles();//���¼������и��ӵĲ���������  
	  
	        return true;  
	    }  
	  
	  
	    public int[] getUsedTiles(int x, int y)  
	    {  
	        return usedAll[x][y];  
	    }  
	  
	      
	    public void setTile(int x, int y, int value)  
	    {  
	        data[y * 9 + x] = value;  
	    }  
}
