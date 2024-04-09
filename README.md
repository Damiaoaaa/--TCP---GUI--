# 这是一款使用java gui开发的基于TCP的桌面小程序

 代码下载后，可以直接运行

## 操作步骤：
### 1.首先您应该打开项目下载位置的文件夹
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/dic.png)
### 2.右键单击鼠标并使用终端打开
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/terminal.png)
### 3.在终端内输入并按空格运行
~~~
javac ChatClientUI\ChatClientUI.java ChatClientUI\ConnectProgress.java ChatClientUI\DisconnectProgress.java ChatClientUI\run.java MusicPlayer\MusicPlayer.java Server\ClientReaderThread.java Server\Server.java Server\ServerReaderThread.java Server\text.java
~~~
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/code.png)
### 4.使用代码运行服务端**
~~~
java Server.Server
~~~
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/code2.png)

### 5.然后我们再在下载位置的文件夹使用同样的方法打开第二个终端
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/terminal.png)
### 6.输入运行客户端
~~~
java ChatClientUI.run
~~~
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/code3.png)
### 现在软件已经打开了

### 你可以在pitcutres文件夹中更改图片为自己的图片与名字，并在ChatClientUI文件中选择并打开ChatClientUI.java文件修改以下代码:
![image](https://github.com/Damiaoaaa/--TCP---GUI--/blob/main/temp/change.png)

## Finally：

***ChatClientUI 主要用于储存软件的ui界面***

***Server 主要用于储存服务端代码***

***story 主要用于储存故事，你可以更改该文件夹内的文本为自己喜欢的故事***

***temp 不重要 忽略它***

***pictures 主要用于储存图片***

***MusicPlayer 该文件夹用于储存调用音乐的代码 不需要做修改***

***musics 软件运行时播放的背景音乐***
