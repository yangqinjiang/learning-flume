# hadoop104
bin/flume-ng agent --conf conf/ --name a3 --conf-file job/group3/flume3-flume-logger.conf -Dflume.root.logger=INFO,console
# hadoop102
bin/flume-ng agent --conf conf/ --name a1 --conf-file job/group3/flume1-logger-flume.conf  -Dflume.root.logger=INFO,console

#hadoop103
bin/flume-ng agent --conf conf/ --name a2 --conf-file job/group3/flume2-netcat-flume.conf

#5．在 hadoop103 上向/opt/module 目录下的 group.log 追加内容 
#echo 'hello' > group.log
#6．在 hadoop102 上向 44444 端口发送数据 
#telnet hadoop102 44444
#7.检查 hadoop104 上数据
