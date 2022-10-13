# Задача : Установка

## Описание
В данной задаче Вы потренируетесь работать с файлами и каталогами в файловой системе, используя язык Java и класс `File`, и смоделируете процесс установки игры на жесткий диск комьютера.

Предварительно вручную создайте папку `Games` в удобном для Вас месте. Имейте ввиду, что у папки `Games` должны быть права на запись и чтение. Например, в операционной системе macOS можно создать папку по следующему пути `/Users/admin/Games`. В ОС Windows можете создать папку на одном из доступных жестких дисков, например `D://Games`.

Установку программы необходимо производить из Java кода с использованием класса `File`. Процесс будет состоять из следующих этапов:
1. В папке `Games` создайте несколько директорий: `src`, `res`, `savegames`, `temp`. 
2. В каталоге `src` создайте две директории: `main`, `test`. 
3. В подкаталоге `main` создайте два файла: `Main.java`, `Utils.java`. 
4. В каталог `res` создайте три директории: `drawables`, `vectors`, `icons`.
5. В директории `temp` создайте файл `temp.txt`.

Файл `temp.txt` будет использован для записиси в него информации об успешноном или неуспешном создании файлов и директорий.  

# Задача 2: Сохранение

## Описание
В данной задаче Вы потренируетесь сериализовывать Java класс, используя интерфейс `Serializable`, записывать сериализованные файлы на жесткий диск, используя класс `FileOutputStream`, и упаковывать их в архив с помощью `ZipOutputStream`.

Для дальнейшей работы потребуется создать класс `GameProgress`, хранящий информацию об игровом процессе. Предлагаем следующую реализацию:
```java
public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}
```

Таким образом, для выполнения задания потребуется проделать следующие шаги:
1. Создать три экземпляра класса `GameProgress`.
2. Сохранить сериализованные объекты `GameProgress` в папку `savegames` из предыдущей задачи.
3. Созданные файлы сохранений из папки `savegames` запаковать в архив `zip`.
4. Удалить файлы сохранений, лежащие вне архива.

