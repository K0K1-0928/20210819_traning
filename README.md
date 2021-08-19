# はじめに

2021/08/19 社内研修の課題の一つである、

> Java で DOM 以外の方法で XML を読み込んだ際に、
> XXE 攻撃が発生するか、確認

のために作成した Spring Boot 実行環境です。

以下の説明は、Spring Boot が立ち上がっているものとして記述しています。

## 正常系

localhost:8080/input にアクセスし、記述されている XML 文のまま送信をクリックします。
/output へ遷移し、name と address が正しく表示されたら成功です。

## 異常系

localhost:8080/xxe にアクセスし、記述されている XML 文のまま送信をクリックします。
/output へ遷移し、name が正しく、address に hosts ファイルの情報が表示されていたら攻撃成功です。
