#!/bin/bash

set -e

echo "🔧 Descargando Maven..."
curl -fsSL https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz -o maven.tar.gz

echo "📦 Extrayendo Maven..."
tar -xzf maven.tar.gz
sudo mv apache-maven-3.9.6 /opt/maven
rm maven.tar.gz

echo "🛠️ Configurando variables de entorno..."
echo 'export MAVEN_HOME=/opt/maven' >> ~/.bashrc
echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> ~/.bashrc
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH

echo "✅ Maven instalado correctamente"