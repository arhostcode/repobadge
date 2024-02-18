![Repository statistics](https://repo.ardyc.ru/?repository=arhostcode/repobadge&stroke=false&background-color=000501&opacity=1&text-color=79C7C5)

# Repository Badge
A dynamic project that effortlessly creates visually appealing repository cards in SVG format. Customize your GitHub project showcase with this intuitive tool, highlighting key details like stars, forks, and programming language, all in a sleek and shareable design.

## Example
![Example](https://repo.ardyc.ru/?repository=ktorio/ktor&stroke=false&background-color=0D1321&opacity=1&text-color=FFEDDF)
![Example](https://repo.ardyc.ru/?repository=JetBrains/kotlin&stroke=false&background-color=360568&opacity=1&text-color=A5E6BA)
![Example](https://repo.ardyc.ru/?repository=TheAlgorithms/Java&stroke=false&background-color=FFFFFF&opacity=1&text-color=000000)

## Usage
Copy and paste this into your markdown, and that's it. Simple!
```
https://repo.ardyc.ru/?repository=ktorio/ktor&stroke=false&background-color=000501&opacity=1&text-color=79C7C5
```

### Options
1. `background-color` option used to customize background of panel. Usage background-color=000000. The color is set in hexadecimal format without #
2. `stroke` option used to on or off stroke of main panel
3. `opacity` option used to change opacity of main panel
4. `text-color` option used to customize text color. Usage background-color=000000. The color is set in hexadecimal format without #

## Run

1. To run the application use Docker.

```bash
docker run -p 80:8080 ghcr.io/arhostcode/repo-badge:latest
````

2. Or you can download latest release on page [Releases](https://github.com/arhostcode/repobadge/releases)
And run
```bash
java -jar repo-badge-all.jar
```

## Build

1. Clone the Project:

Open your terminal and navigate to the desired directory. Clone the Ktor project repository using Git:

```bash
git clone https://github.com/arhostcode/repobadge
```

2. Navigate to Project Directory:

Change into the project directory:

```bash
cd repobadge
```

3. Run the Application:

```bash
./gradlew run
```

4. Access the Application:

Once the application is running, open a web browser and navigate to the specified address http://localhost:8080 to access the application.
