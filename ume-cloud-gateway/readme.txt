【运行方法(I) - 使用Eclipse启动】
  1 依赖环境
    Eclipse版本4.5或以上; JDK版本1.7或以上; Apache Maven版本3.3.3或以上
  2.运行步骤
    右键选择"src/main/java"子目录中的"Application.java"文件，在弹出菜单选择 [Run As] > [Java Application] 运行。

【运行方法(II) - 命令行执行启动命令】
  1 依赖环境
    JDK版本1.7或以上(可在命令行输入命令 java -version 进行确认)
  2.运行步骤
    打开命令行窗口，
    进入项目的target目录下运行 java -jar {可执行jar文件名}
    或者直接在项目根目录下运行  mvn spring-boot:run

【服务测试】
  输入 
  http://localhost:8088

