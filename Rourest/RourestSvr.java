package Rourest;

import java.util.*;//乱数システムを利用するためのパッケージの呼び出し
import java.io.*; //データストリーム、ファイルシステム入出力用のパッケージの呼び出し
import java.net.*;//ネットワーク・アプリケーションを実装するためのパッケージの呼び出し

public class RourestSvr {

    public static final int PORT = 1234; //Well Known 意外の任意のポート番号の指定

    public static void main(String args[]) { 	//メインクラスの始まり！
        ServerSocket serverSocket = null;		//ServerSocket型のserverSocketの定義・初期化
        Socket socket = null;					//Socket型のsocketの定義・初期化
        
        //店舗情報をジャンル別にリストで管理
        //日本食
        List<String> JPNRestaurants = Arrays.asList("お近くの”和食さと”", "お近くの”大戸屋”",
        "お近くの”夢庵”","マジでうまい鰻屋”又兵衛　船形店”","お近くの”とんでん”","お近くの”しんぱち食堂”",
        "お近くの”スシロー”","お近くの”かっぱ寿司”","渋谷”KNKA”","千葉県とかによくある”一幸”");

        //洋食
        List<String> WestRestaurants = Arrays.asList("お近くの”ピザハット”","お近くの”サイゼリヤ”",
        "お近くの”ガスト”","お近くの”ロイヤルホスト”","お近くの”デニーズ”","お近くの”ジョナサン”",
        "お近くの”ペッパーランチ”","お近くの”ドミノピザ”","お近くの”バーガーキング”","お近くの”マック”");

        //中華
        List<String> CHNRestaurants = Arrays.asList("お近くの”バーミヤン”","お近くの”日高屋”",
        "お近くの”餃子の王将”","お近くの”幸楽苑”","お近くの”珍来”","お近くの”四川飯店”","お近くの”大阪王将”", 
        "お近くの”蒙古タンメン　中本”","横浜中華街にある”皇朝”","お近くの”ラーメン二郎”");

        //その他
        List<String> OtherRestaurants = Arrays.asList("お近くの”タイ料理”","お近くの”ココ壱番屋”",
        "お近くの”ガチカレー屋”","お近くの”本格イタリアン”","お近くの”パンケーキ屋”","お近くの”クレープ屋”", 
        "お近くの”タピオカ”","お近くの”31アイスクリーム”","お近くの”KFC”","お近くの”スーパーの惣菜”","”自炊”");

        //Javaの"Random"クラスを使ってランダムな数値を生成するためのインスタンスを作成
        Random random = new Random();

        try {
		//serverSocketのinstanceを生成(PORTを引数に)
		serverSocket = new ServerSocket(PORT);

                //サーバが起動したことをprintlnとgetLocalPort()を
		//使用してサーバのターミナルに表示させる
		System.out.println("ルーレストサーバーが起動しました(port=" + serverSocket.getLocalPort() + ")");

		//accept()を用いてクライアントからの要求を待ち続ける
                socket = serverSocket.accept();

		//クライアントがサーバが接続したことをprintlnと
		//getRemoteLocalAddress()を使用してサーバのターミナルに表示させる
                System.out.println("ルーレストサーバーに接続されました" + socket.getLocalAddress());

		//クライアントからの送信されるデータを読み込むためのBufferedReader型の
		//インスタンス"in"をInputStreamReaderとgetInputStream()を用いて生成
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		//クライアントへ送信されるデータを書き出すための PrintWriter型の
		//インスタンス"out"をPrintWriterとgetOutputStream()を用いて生成
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		//クライアントから送信された数を読み取り、格納するdouble型の
		//変数genre1を定義
                double genre1 = Double.parseDouble(in.readLine());

                //文字列リストを格納するためのString型の
                //変数selectedListを定義
                List<String> selectedList;

                // ジャンルに基づいてリストを選択
                if (genre1 == 0) {
                    selectedList = JPNRestaurants;

                } else if (genre1 == 1) {
                    selectedList = WestRestaurants;

                } else if (genre1 == 2) {
                    selectedList = CHNRestaurants;

                } else {
                    selectedList = OtherRestaurants;
                }

                // ランダムにお店を選択
                String SugStore = selectedList.get(random.nextInt(selectedList.size()));
                out.println(SugStore);

            } catch (IOException e) {
                e.printStackTrace();
                
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }

                    if (serverSocket != null) {
                        serverSocket.close();
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
