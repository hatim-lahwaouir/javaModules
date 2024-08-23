!/bin/bash
curl https://download.oracle.com/graalvm/22/latest/graalvm-jdk-22_macos-x64_bin.tar.gz --output graalvm-jdk-22_macos-x64_bin.tar.gz

tar -xzf graalvm-jdk-22_macos-x64_bin.tar.gz 

rm -rf graalvm-jdk-22_macos-x64_bin.tar.gz
export JAVA_HOME="$(pwd)/graalvm-jdk-22.0.2+9.1/Contents/Home"
export PATH="$(pwd)/graalvm-jdk-22.0.2+9.1/Contents/Home/bin:$PATH"
echo $PATH


