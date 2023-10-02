rm vulnerable.jar 2>/dev/null
./gradlew clean
./gradlew build
cp build/libs/vulnerable-0.0.1-SNAPSHOT.jar vulnerable.jar

git add .
git commit -m'vun app'
git push