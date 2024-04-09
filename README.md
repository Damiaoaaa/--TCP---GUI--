# 这是一款使用java gui开发的基于TCP的桌面小程序

 代码下载后，可以直接运行

## 操作步骤：
### 1.首先您应该打开项目下载位置的文件夹

### 2.右键单击鼠标并使用终端打开

### 3.在终端内输入并按空格运行
~~~
javac ChatClientUI\ChatClientUI.java ChatClientUI\ConnectProgress.java ChatClientUI\DisconnectProgress.java ChatClientUI\run.java MusicPlayer\MusicPlayer.java Server\ClientReaderThread.java Server\Server.java Server\ServerReaderThread.java Server\text.java
~~~

### 4.使用代码运行服务端**
~~~
java Server.Server
~~~


### 5.然后我们再在下载位置的文件夹使用同样的方法打开第二个终端

### 6.输入运行客户端
~~~
java ChatClientUI.run
~~~

### 现在软件已经打开了