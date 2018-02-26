package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MagicSquare {
  int size;         //魔方陣のサイズ
  int row;          //現在位置の行番号
  int col;          //現在位置の列番号
  int num;          //現在の数値
  int[][] square;   //魔方陣
  
  MagicSquare(int size) throws Exception {
    if (size < 3 || size % 2 == 0) {
      throw new Exception("An input number is invalid.");
    }
    
    this.size = size;
    this.num = 1;
    //真ん中の一つ下にセット
    this.row = size / 2 + 1;
    this.col = size / 2;
    //魔方陣の初期化
    this.square = new int[this.size][];
    for (int row = 0; row < this.size; row++) {
      this.square[row] = new int[this.size];
    }
  }
  
  private void set() {
    //現在位置が0(未設定)
    if(this.square[row][col] == 0) {
      this.square[this.row][this.col] = this.num; //数値を保存
      this.num++;                 //次の数値
      //右斜め下に移動(はみ出したら反対側)
      this.row = (row + 1) % this.size;
      this.col = (col + 1) % this.size;
    //現在位置が0以外(設定済み)
    } else {  
      //左斜め下に移動(はみ出したら反対側)
      this.row = (row + 1) % this.size;
      this.col = (col - 1 + this.size) % this.size;
    }
  }
  
  private boolean isEnd() {
    //全部終わったら終了
    if(this.num > this.size * this.size) {
      return true;
    } else {
      return false;
    }
  }
  
  private void showMagicSquare() {
    //魔方陣を表示 桁数は最大値の桁数にそろえる
    int max_digit = (int)Math.log10(this.size * this.size) + 1;
    
    for(int rowNo = 0; rowNo < this.size; rowNo++) {
      for(int colNo = 0; colNo < this.size; colNo++) {
        showNumber(square[rowNo][colNo], max_digit);
      }
      System.out.println();
    }
  }
  
  private void showNumber(int num, int max_digit) {
    //桁数を最大値にそろえて数字を表示
    System.out.print(" ");
    int digit = (int)Math.log10(num) + 1;
    for (int spaceNo = 0; spaceNo < (max_digit - digit); spaceNo++) {
      System.out.print(" ");
    }
    System.out.print(num);
  }
  
  public void run() {
    while(!this.isEnd()) { //終わるまでセットを繰り返す
      this.set();
    }
    
    this.showMagicSquare();
  }
}

public class Main {
  private static int inputNum(String message) throws IOException {
    System.out.print(message);
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String buf = br.readLine();
    return Integer.parseInt(buf);
  }
    
  public static void main(String[] args) {
    try {
      int size = inputNum("Pleas input an odd number >");
      MagicSquare magicSquare = new MagicSquare(size);
      magicSquare.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

