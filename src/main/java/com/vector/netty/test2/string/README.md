### 打印String服务

发送过来的数据，解码成String，打印出来


### 运行

1. 运行StringServer
2. 使用 telnet 127.0.0.1 8080 连接
3. 发送数据，并查看控制台的输出信息

![运行结果](../../../../../../../../images/test2_stringserver.png)

```
String msgStr = (String) msg;
```
不抛出移除，说明消息成功转为String