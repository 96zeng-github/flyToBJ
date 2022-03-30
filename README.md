[TOC]
# fly
# 第一步：生成基本目录结构
### 生成子项目maven结构
- mvn archetype:generate 根据原型创建项目,固定格式
- DinteractiveMode 是否使用交互模式，设置maven是否从服务器获取catalog
- DgroupId 组织标识（包名）
- DartifactId 项目名
- Dversion 版本号
- DarchetypeArtifactId 指定ArchetypeId（原型），maven-archetype-quickstart（创建一个Java项目），maven-archetype-webapp（创建一个web项目）

```
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-assemble -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-web -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-facade -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-biz -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-dal -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-integration -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-common -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
mvn archetype:generate -DinteractiveMode=false -DgroupId=com.zeng.fly -DartifactId=fly-test -Dversion=0.0.1-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
```
### 项目添加maven支持
- 在pom.xml 文件上右键 Add as Maven Project。

### 项目结构
- `fly-parent` 父项目
- `fly-web` 向外暴露http接口， 依赖`fly_facade`
- `fly-facade` 向外暴露rpc远程调用接口
- `fly-biz` 业务处理，依赖`fly-facade, fly-dal, fly-integration`
- `fly-dal` 数据处理
- `fly-integration` 调用外部rpc接口
- `fly-common` 工具
- `fly-test` 测试，依赖`fly-facade`
### 忽略
```
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*
/.idea/
*.iml
/*/target/
```
                
