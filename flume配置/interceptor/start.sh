# 1,首先将拦截器的jar包放到 lib目录下
# 2 , 启动顺序,先启动a2,a3, 再启动a1
# hadoop104
bin/flume-ng agent --conf conf/ --name a3 --conf-file job/interceptor/3.conf  -Dflume.root.logger=INFO,console
# hadoop103
bin/flume-ng agent --conf conf/ --name a2 --conf-file job/interceptor/2.conf  -Dflume.root.logger=INFO,console
# hadoop102
bin/flume-ng agent --conf conf/ --name a1 --conf-file job/interceptor/1.conf
# 4.分别在 hadoop104，hadoop103,hadoop102 上启动 flume 进程，注意先后顺序。
# 5.在 hadoop102 使用 netcat 向 localhost:44444 发送字母和数字。
# 6.观察 hadoop103 和 hadoop104 打印的日志。
