1) AWS setup m5.large Cloud9 EC2 instance

2) Install SDKMAN   (https://sdkman.io/)
  curl -s "https://get.sdkman.io" | bash

  source "/home/ec2-user/.sdkman/bin/sdkman-init.sh"

3) Install GraalVM   (https://www.graalvm.org/latest/docs/getting-started/linux/)
   sdk install java 23.0.1-graal

4) Install Native Image  (https://www.graalvm.org/latest/reference-manual/native-image/)

   sudo yum install gcc glibc-devel zlib-devel
  
   sudo dnf install gcc glibc-devel zlib-devel libstdc++-static

5) Install Maven  

   yum install maven 

6) Clone git Repo
  git clone https://github.com/Vadym79/AWSLambdaJavaWithQuarkus.git

7) Build Native Image
mvn package -Dpackaging=docker-native -Dmicronaut.runtime=lambda -Pgraalvm

