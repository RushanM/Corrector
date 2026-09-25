package deflecat.mothra;

import java.io.InputStream; // Для открытия файлов и чтения их в ОЗУ
import java.util.Properties; // Для форматирования ключей и значений
import net.minecraftforge.fml.common.Mod; // Для пометки

@Mod("mothra") // Пометка для Forge, что это начальный класс

// Объявление начального класса
public class Start {
    // Переменные для осуществления поддержки формата версионирования 40А.
    // У них проставлены значения по умолчанию по той причине, что если доступ к mothra.properties потеряется, игра упадёт, если здесь не будет значений.
    public static String semverVersion = "0.1.0";
    public static String sorokaVersion = "A1";

    // static — это блок, что срабатывает в момент первой загрузки класса.
    // Я бы мог вместо него использовать конструктор, но я выбрал static, так как в будущем возможно буду обращаться сюда через миксины, чтобы получить версию мода в формате 40А.
    static {
        // Как бы я не хотел не использовать здесь try, на это жалуется ИСР… ведь работа с файлами опа$$$на.
        // В скобках try я создаю поток из текста mothra.properties.
        // От применения try, конечно, есть некоторая польза. Он автоматически закрывает поток по завершению чтения и при ошибке. Но, я, что, как будто не мог… вручную первое прописать?
        try (InputStream stream = Start.class.getResourceAsStream("/mothra.properties")) {
            // Это читалка для ранее загруженного как поток mothra.properties
            Properties props = new Properties();

            // Загрузка mothra.properties в props для чтения
            props.load(stream);

            // И загрузка значений с mothra.properties в местные переменные
            semverVersion = props.getProperty("semver_version", semverVersion);
            sorokaVersion = props.getProperty("40a_version", sorokaVersion);
        } catch (Exception ignored) {}
    }
}