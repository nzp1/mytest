FROM openjdk:8-jdk-alpine

RUN echo -e "https://mirrors.aliyun.com/alpine/v3.14/main/\nhttps://mirrors.aliyun.com/alpine/v3.14/community/" > /etc/apk/repositories \
    && apk update \
    && apk add --no-cache wget ttf-dejavu tzdata \
    && apk upgrade musl \
    && echo "Asia/Shanghai" > /etc/timezone \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub \
    && wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.34-r0/glibc-2.34-r0.apk \
    && apk add --no-cache glibc-2.34-r0.apk

RUN mkdir -p /mytest

WORKDIR /mytest

EXPOSE 8001

#ADD ./target/mytest.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
