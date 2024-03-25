FROM eclipse-temurin:17-jdk-alpine

ENV VERSION 1.0.0

WORKDIR /app/CheckLoanBalanceAndLimitService/

ADD CheckLoanBalanceAndLimitService-$VERSION.jar $VERSION.jar

EXPOSE 8012

ENTRYPOINT ["java","-jar","1.0.0.jar", "--server"]