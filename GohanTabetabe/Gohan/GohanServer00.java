package Gohan;

import java.util.*;//乱数システムを利用するためのパッケージの呼び出し

import java.io.*; //データストリーム、ファイルシステム入出力用のパッケージの呼び出し
import java.net.*;//ネットワーク・アプリケーションを実装するためのパッケージの呼び出し

public class GohanServer00 {
    public static final int PORT = 1234; //Well Known 意外の任意のポート番号の指定

    public static void main(String args[]) { 	//メインクラスの始まり！
        ServerSocket serverSocket = null;		//ServerSocket型のserverSocketの定義・初期化
        Socket socket = null;					//Socket型のsocketの定義・初期化
        try {
            //serverSocketのinstanceを生成(PORTを引数に)
			serverSocket = new ServerSocket(PORT);
            //サーバが起動したことをprintlnとgetLocalPort()を
			//使用してサーバのターミナルに表示させる
			System.out.println("ごはん決め決めサーバーが起動しました(port=" + serverSocket.getLocalPort() + ")");
			//accept()を用いてクライアントからの要求を待ち続ける
            socket = serverSocket.accept();
			//クライアントがサーバが接続したことをprintlnと
			//getRemoteLocalAddress()を使用してサーバのターミナルに表示させる
            System.out.println("ごはん決め決めサーバーに接続されました" + socket.getLocalAddress());
			//クライアントからの送信されるデータを読み込むためのBufferedReader型の
			//インスタンス"in"をInputStreamReaderとgetInputStream()を用いて生成
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//クライアントへ送信されるデータを書き出すための PrintWriter型の
			//インスタンス"out"をPrintWriterとgetOutputStream()を用いて生成
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			//クライアントから送信された数の格納するdouble型の
			//変数genre1を定義
			double genre1;
			//最終的に提案の値を格納するdouble型の変数sugを定義
            double sug;
            //最終的に提案するお店の情報を格納するString型の変数SugStoreの定義と初期化
            String SugStore;
            SugStore = null;
            //最終的なお店を選択する乱数システムのint型の変数ranを定義
            int ran;

            //乱数を求めてくれる関数の定義
            Random random = new Random();

			//Double.parseDoubleメソッドを用いて，クライアントから
			//送信されたストリーム型の値をDouble型に変換し，input1に代入
            genre1 = Double.parseDouble(in.readLine());

            //if文を用いてgenre1の値を区別、10倍することで乱数決定する幅を増やす
            if(genre1 == 0){
                genre1 = 0;
            }else if(genre1 == 1){
                genre1 = 10;
            }else if(genre1 == 2){
                genre1 = 20;
            }else if(genre1 == 3){
                genre1 = 30;
            }else if(genre1 == 4){
                genre1 = 40;
            }

			System.out.println("OK.Genre");

			//genre1の値をsumに代入
			sug = genre1;
			System.out.println("OK.Sug");

            //if文を用いて入力されたジャンルを基に、
            //乱数を用いることでいくつか登録してあるお店の情報から
            //提案する情報を決定し、格納する
            if(SugStore == null){

                if(sug < 10){  //和食ジャンルで乱数を求める
                    ran = random.nextInt(10);

                }else if(10<= sug && sug < 20) {//洋食ジャンルで乱数を求める
                    ran = random.nextInt(10) + 10;

                }else if(20<= sug && sug < 30) {//中華ジャンルで乱数を求める
                    ran = random.nextInt(10) + 20;

                }else if(30<= sug && sug < 40) {//その他ジャンルで乱数を求める
                    ran = random.nextInt(10) + 30;

                }else //全てのジャンルで乱数を求める（自炊込み）
                    ran = random.nextInt(41);

                //和食ジャンルの選択肢
                    if(ran == 0){
                        SugStore = "お近くの”和食さと”";
                    }else if(ran == 1){
                        SugStore = "お近くの”大戸屋”";
                    }else if(ran == 2){
                        SugStore = "お近くの”夢庵”";
                    }else if(ran == 3){
                        SugStore = "マジでうまい鰻屋”又兵衛　船形店”";
                    }else if(ran == 4){
                        SugStore = "お近くの”とんでん”";
                    }else if(ran == 5){
                        SugStore = "お近くの”しんぱち食堂”";
                    }else if(ran == 6){
                        SugStore = "お近くの”スシロー”";
                    }else if(ran == 7){
                        SugStore = "お近くの”かっぱ寿司”";
                    }else if(ran == 8){
                        SugStore = "渋谷”KINKA”";
                    }else if(ran == 9){
                        SugStore = "千葉県とかによくある”一幸”";

                //洋食ジャンルの選択肢
                    }else if(ran == 10){
                        SugStore = "お近くの”ピザハット”";
                    }else if(ran == 11){
                        SugStore = "お近くの”サイゼリヤ”";
                    }else if(ran == 12){
                        SugStore = "お近くの”ガスト”";
                    }else if(ran == 13){
                        SugStore = "お近くの”ロイヤルホスト”";
                    }else if(ran == 14){
                        SugStore = "お近くの”デニーズ”";
                    }else if(ran == 15){
                        SugStore = "お近くの”ジョナサン”";
                    }else if(ran == 16){
                        SugStore = "お近くの”ペッパーランチ”";
                    }else if(ran == 17){
                        SugStore = "お近くの”ドミノピザ”";
                    }else if(ran == 18){
                        SugStore = "お近くの”バーガーキング”";
                    }else if(ran == 19){
                        SugStore = "お近くの”マック”";

                //中華ジャンルの選択肢
                    }else if(ran == 20){
                        SugStore = "お近くの”バーミヤン”";
                    }else if(ran == 21){
                        SugStore = "お近くの”日高屋”";
                    }else if(ran == 22){
                        SugStore = "お近くの”餃子の王将”";
                    }else if(ran == 23){
                        SugStore = "お近くの”幸楽苑”";
                    }else if(ran == 24){
                        SugStore = "お近くの”珍来”";
                    }else if(ran == 25){
                        SugStore = "お近くの”四川飯店”";
                    }else if(ran == 26){
                        SugStore = "お近くの”大阪王将”";
                    }else if(ran == 27){
                        SugStore = "お近くの”蒙古タンメン　中本”";
                    }else if(ran == 28){
                        SugStore = "横浜中華街にある”皇朝”";
                    }else if(ran == 29){
                        SugStore = "お近くの”ラーメン二郎”";

                //その他ジャンルの選択肢
                    }else if(ran == 30){
                        SugStore = "お近くの”タイ料理”";
                    }else if(ran == 31){
                        SugStore = "お近くの”ココ壱番屋”";
                    }else if(ran == 32){
                        SugStore = "お近くの”ガチカレー屋";
                    }else if(ran == 33){
                        SugStore = "お近くの”本格イタリアン”";
                    }else if(ran == 34){
                        SugStore = "お近くの”パンケーキ屋”";
                    }else if(ran == 35){
                        SugStore = "お近くの”クレープ屋”";
                    }else if(ran == 36){
                        SugStore = "お近くの”タピオカ”";
                    }else if(ran == 37){
                        SugStore = "お近くの”31アイスクリーム”";
                    }else if(ran == 38){
                        SugStore = "お近くの”KFC”";
                    }else if(ran == 39){
                        SugStore = "お近くの”スーパーの惣菜”";
                    }else
                        SugStore = "”自炊”";
                }
                        
			//SugStoreをクライアント結果に返す
            out.println(String.valueOf(SugStore));
			//例外処理
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
