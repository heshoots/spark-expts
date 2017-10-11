docker run -it -v $(pwd):/app/ -v ~/.ivy2:/root/.ivy2 -v ~/.sbt:/root/.sbt --rm williamyeh/sbt clean compile package
cp ./target/scala-2.11/myproject_2.11-1.0.jar ../cluster-expts/submit/build.jar
