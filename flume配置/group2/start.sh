# hadoop102
bin/flume-ng agent --conf conf/ --name a3 --conf-file job/group2/flume-netcat-console2.conf -Dflume.root.logger=INFO,console
bin/flume-ng agent --conf conf/ --name a2 --conf-file job/group2/flume-netcat-console1.conf -Dflume.root.logger=INFO,console
bin/flume-ng agent --conf conf/ --name a1 --conf-file job/group2/flume-netcat-flume.conf -Dflume.root.logger=INFO,console

# 使用netcat 
nc localhost 44444
# 查看flume2 ,flume3 的控制台打印日志
# 将flume2 kill,c观察flume3的控制台打印情况