# SHIFT-FilterUtil

SHIFT-FilterUtil - это утилита командной строки, предназначенная для фильтрации и обработки данных из файлов. Она использует библиотеку [args4j](http://args4j.kohsuke.org/) для обработки аргументов командной строки.

## Установка

### Сборка из исходников

Для сборки проекта из исходников вам потребуется установить следующее:

- Java Development Kit (JDK) версии 21.
- Apache Maven.

#### Шаги для сборки проекта:

1. **Клонирование репозитория**:
   Клонируйте репозиторий проекта на ваш локальный компьютер.

   ```bash
   git clone https://github.com/username/SHIFT-FilterUtil.git
   ```

2. **Переход в директорию проекта**:
   Перейдите в директорию проекта, где находится файл `pom.xml`.

   ```bash
   cd SHIFT-FilterUtil
   ```

3. **Сборка проекта**:
   Используйте Maven для сборки проекта.

   ```bash
   mvn clean install
   ```

   Эта команда очистит предыдущие сборки, выполнит сборку проекта и установит все зависимости.

4. **Проверка сборки**:
   После успешной сборки вы должны увидеть сообщение о том, что сборка выполнена успешно. Выходной JAR-файл будет находиться в директории `target`.

   ```bash
   ls target
   ```

   Вы должны увидеть файл `SHIFT-FilterUtil-<version>-jar-with-dependencies.jar`, где `<version>` - это версия вашего проекта.

5. **Запуск JAR-файла**:
   Вы можете запустить JAR-файл, используя следующую команду:

   ```bash
   java -jar target/SHIFT-FilterUtil-<version>.jar
   ```

   Замените `<version>` на фактическую версию вашего проекта.

Теперь у вас есть собранный JAR-файл, который вы можете использовать для запуска утилиты SHIFT-FilterUtil.

## Использование

Для запуска утилиты используйте следующую команду:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar [параметры]
```

### Параметры

- `-o`, `--output`: Указывает путь к выходному файлу.
- `-p`, `--prefix`: Префикс для выходных файлов.
- `-a`, `--append`: Добавляет данные в существующие файлы.
- `-s`, `--short`: Выводит краткую статистику.
- `-f`, `--full`: Выводит полную статистику.
- `-h`, `--help`: Выводит справку.

### Примеры

Фильтрация данных из файла `input.txt` и сохранение результатов в файлы с префиксом `output_`:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar -p output_ input.txt
```

Фильтрация данных из файла `input.txt` и добавление результатов в существующие файлы:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar -p output_ input.txt -a
```

Фильтрация данных из файла `input.txt` и выводит полную статистику:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar -p output_ input.txt -f
```
Фильтрация данных из файла `input1.txt input2.txt` и выводит полную статистику:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar -p output_ input1.txt input2.txt -f
```

Фильтрация данных из файла `input1.txt input2.txt`, сохраняет результат в папку `out_dir` и выводит полную статистику:

```bash
java -jar SHIFT-FilterUtil-1.0-SNAPSHOT-jar-with-dependencies.jar -p output_ -o out_dir input1.txt input2.txt -f
```