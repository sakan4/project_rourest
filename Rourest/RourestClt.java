package Rourest;

import java.io.*;//データストリーム、ファイルシステム入出力用のパッケージの呼び出し
import java.net.*;//ネットワーク・アプリケーションを実装するためのパッケージの呼び出し

public class RourestClt {
    public static final int PORT = 1234; //Well Known 意外の任意のポート番号の指定

    public static void main(String args[]) {//メインクラスの始まり！
        Socket socket = null;				//Socket型のsocketの定義・初期化
        try {
			//ターミナルから入力されたアドレス・ホスト名をもとに
			//socketのインスタンスを生成

            if(args.length == 0){
                System.out.printf("引数に接続先のサーバとポートがありません\n接続先のサーバとポートを指定してください");
                return;

            }else if(args.length == 1){
				System.out.println("ポートの指定がありません。デフォルトのポート番号(13)を使用します");
				socket = new Socket(args[0],1234);
                
			}else{
				socket = new Socket(args[0],Integer.parseInt(args[1]));
			}

			//クライアントがサーバが接続したことをprintlnとサーバのアドレスを
			//getRemoteLocalAddress()を使用してクライアントのターミナルに表示させる
            System.out.println(socket.getLocalAddress() + "に接続しました");

			//サーバからの送信されるデータを読み込むためのBufferedReader型の
			//インスタンス"in"をInputStreamReaderとgetInputStream()を用いて生成
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//サーバからの送信されるデータを書き出すための PrintWriter型の
			//インスタンス"out"をPrintWriterとgetOutputStream()を用いて生成
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			//キーボードから入力されるデータを読み込むための BufferedReader型の
			//インスタンス"KBIn"をBufferedReaderとInputStreamReader(System.in)を用いて生成
            BufferedReader KBin = new BufferedReader(new InputStreamReader(System.in));
			
			//Keyboardから入力された数字を格納するためのdouble型のgenre1を定義 
			double genre1;

            //"あなたの希望を聞かせてください"という文章と選択肢を表示させる
			System.out.println("あなたの希望を聞かせてください\n0:和食\n1:洋食\n2:中華\n3:その他\n4:何でもいい\n5:食べたくない");
			
			//Double.parseDoubleメソッドを用いて，キーボードから
			//入力されたストリーム型の値をDouble型に変換し，genre1に代入
            genre1 = Double.parseDouble(KBin.readLine());
            
            //選択肢意外の値を選択した場合にプログラムを終了するようにする
            if(genre1 < 0){
                System.out.println("ふざけないでください。\n案内を終了します。");
                return;

            }else if(5 <= genre1){
                System.out.println("あなたに私は必要ないみたいです\n今夜は暖かくしておやすみなさい...");
                return;

            }else{
                System.out.println("ご入力ありがとうございます\n" + "検索提案させていただきます。");
                out.println(genre1);
            }

            //入力された値をサーバに送信
            out.println(genre1);

			//Sting型の"answer"を定義し，in.readline()を用いて
			//サーバから受け取った演算結果を"answer"に格納
            String answer = in.readLine();

			//"answer"をクライアントのターミナルに表示
			System.out.println("あなたにおススメのお店は" + answer + "です！\n楽しんでください！");

            out.close(); //outを閉じる
            socket.close();//socketを閉じる

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
