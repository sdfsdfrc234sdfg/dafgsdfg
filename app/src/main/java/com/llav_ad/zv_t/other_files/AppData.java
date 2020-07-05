package com.llav_ad.zv_t.other_files;

import android.text.Html;

import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.ent.ArtItem;
import com.llav_ad.zv_t.ent.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    public List<SearchItem> searchList = new ArrayList<>();
    public List<ArtItem> articleList = new ArrayList<>();

    public AppData() {

        articleList.add(new ArtItem(
                R.drawable.article1,
                "Сетевые знакомства: зачем и почему",
                Html.fromHtml("Зачем и почему знакомятся в интернете? Давайте попробуем спросить об этом у людей на сайтах знакомств.\n" +
                        "\n" +
                        "Мужчины чаще всего не любят отвечать на этот вопрос, а может и просто не хотят или не считают нужным. :) Можно предположить, что конечно же здесь они надеются встретить девушку своей мечты, на самом деле большинство мечтают именно об этом. Но бывают и исключения, нередко на сайтах знакомств попадаются очень любвеобильные мужчины - с такими надо быть поосторожнее! :)\n" +
                        "\n" +
                        "А вот женщины отвечают по-разному, кто-то зашёл на сайт знакомств просто ради интереса, кто-то таким образом мстит своему бывшему парню (да, бывает и такое :) ), кто-то ищёт здесь недостающее внимание со стороны мужского пола (причем часто это замужние барышни), ну а часть действительно надеются на серьезные отношения.\n" +
                        "\n" +
                        "На сайтах знакомств каждый может найти то, что ищет. Самое главное ничего не бояться и действовать. Ну а если не получилось с первого раза, не стоит отчаиваться, обязательно получится в следующий раз! ;)"),
                Html.fromHtml(
                        "Зачем и почему знакомятся в интернете? Давайте попробуем спросить об этом у людей на сайтах знакомств.")
        ));



        articleList.add(new ArtItem(
                R.drawable.article2,
                "Online-Dating: Warum und warum",
                Html.fromHtml("Warum und warum im Internet treffen? Lassen Sie uns versuchen, Leute auf Dating-Sites danach zu fragen.\n" +
                        "\n" +
                        "Meistens beantworten Männer diese Frage nicht gern, oder sie wollen oder halten sie einfach nicht für notwendig. :) Es ist davon auszugehen, dass sie hier natürlich hoffen, das Mädchen ihrer Träume zu treffen, tatsächlich träumen die meisten davon. Aber es gibt Ausnahmen, oft finden sich auf Dating-Sites sehr liebevolle Männer - mit denen muss man vorsichtig sein! :) :)\n" +
                        "\n" +
                        "Aber Frauen reagieren anders, jemand ist nur zum Spaß auf eine Dating-Seite gegangen, jemand rächt sich auf diese Weise an seinem Ex-Freund (ja, es passiert :)), jemand sucht nach der fehlenden Aufmerksamkeit des Mannes ( oft sind dies verheiratete junge Damen), aber einige von ihnen hoffen wirklich auf eine ernsthafte Beziehung.\n" +
                        "\n" +
                        "Auf Dating-Sites kann jeder finden, wonach er sucht. Das Wichtigste ist, keine Angst zu haben und zu handeln. Wenn es beim ersten Mal nicht geklappt hat, verzweifeln Sie nicht, es wird sich beim nächsten Mal sicherlich herausstellen! ;)"),
                Html.fromHtml("Warum und warum im Internet treffen? Lassen Sie uns versuchen, Leute auf Dating-Sites danach zu fragen.")
        ));

        articleList.add(new ArtItem(
                R.drawable.article3,
                "Online Dating: Why and Why",
                Html.fromHtml("Why and why meet on the Internet? Let's try asking people on dating sites about this.\n" +
                        "\n" +
                        "Most often, men do not like to answer this question, or maybe they simply do not want or do not consider it necessary. :) It can be assumed that, of course, here they hope to meet the girl of their dreams, in fact, most dream of this. But there are exceptions, often very loving men are found on dating sites - you have to be careful with those! :)\n" +
                        "\n" +
                        "But women respond differently, someone went to a dating site just for fun, someone takes revenge on his ex-boyfriend in this way (yes, it happens :)), someone is looking for the missing attention from the male ( often these are married young ladies), but some of them really hope for a serious relationship.\n" +
                        "\n" +
                        "On dating sites, anyone can find what they are looking for. The most important thing is not to be afraid and act. Well, if it didn’t work out the first time, don’t despair, it will surely turn out the next time! ;)"),
                Html.fromHtml("Why and why meet on the Internet? Let's try asking people on dating sites about this.")
        ));

        articleList.add(new ArtItem(
                R.drawable.article4,
                "Incontri online: perché e perché",
                Html.fromHtml("Perché e perché incontrarsi su Internet? Proviamo a chiedere alle persone sui siti di incontri di questo.\n" +
                        "\n" +
                        "Molto spesso, agli uomini non piace rispondere a questa domanda, o forse semplicemente non vogliono o non lo considerano necessario. :) Si può presumere che, ovviamente, qui sperano di incontrare la ragazza dei loro sogni, infatti, molti di loro lo sognano. Ma ci sono eccezioni, spesso si trovano uomini molto affettuosi sui siti di incontri - devi stare attento con quelli! :)\n" +
                        "\n" +
                        "Ma le donne rispondono in modo diverso, qualcuno è andato in un sito di appuntamenti solo per divertimento, qualcuno si vendica del suo ex ragazzo in questo modo (sì, succede :)), qualcuno sta cercando l'attenzione mancante dal maschio ( spesso si tratta di giovani donne sposate), ma alcune sperano davvero in una relazione seria.\n" +
                        "\n" +
                        "Sui siti di incontri, chiunque può trovare ciò che sta cercando. La cosa più importante è non aver paura e agire. Bene, se la prima volta non ha funzionato, non disperare, la prossima volta si rivelerà sicuramente! ;)"
                ),
                Html.fromHtml("Perché e perché incontrarsi su Internet? Proviamo a chiedere alle persone sui siti di incontri di questo.")));

        articleList.add(new ArtItem(
                R.drawable.article4,
                "Rencontres en ligne: pourquoi et pourquoi",
                Html.fromHtml("Pourquoi et pourquoi se rencontrer sur Internet? Essayons de demander aux gens sur des sites de rencontres à ce sujet.\n" +
                        "\n" +
                        "Le plus souvent, les hommes n'aiment pas répondre à cette question, ou peut-être qu'ils ne veulent tout simplement pas ou ne le jugent pas nécessaire. :) On peut supposer que, bien sûr, ici, ils espèrent rencontrer la fille de leurs rêves, en fait, la plupart en rêvent. Mais il y a des exceptions, on trouve souvent des hommes très aimants sur les sites de rencontres - vous devez être prudent avec ceux-ci! :)\n" +
                        "\n" +
                        "Mais les femmes réagissent différemment, quelqu'un est allé sur un site de rencontres juste pour le plaisir, quelqu'un prend sa revanche sur son ex-petit ami (oui, ça arrive :)), quelqu'un cherche l'attention manquante de l'homme ( ce sont souvent des jeunes filles mariées), mais certaines espèrent vraiment une relation sérieuse.\n" +
                        "\n" +
                        "Sur les sites de rencontres, tout le monde peut trouver ce qu'il cherche. La chose la plus importante est de ne pas avoir peur et d'agir. Eh bien, si cela n'a pas fonctionné la première fois, ne désespérez pas, cela se produira sûrement la prochaine fois! ;)"
                ),
                Html.fromHtml("Pourquoi et pourquoi se rencontrer sur Internet? Essayons de demander aux gens sur des sites de rencontres à ce sujet.")));



        searchList.add(new SearchItem(
                R.drawable.f1,
                "Lola",
                "Deutschland, Hamburg",
                19,
                "Ich liebe reife Männer) Meine Herren :)",
                "mit einem Mann im Alter von 30 - 45 Jahren",
                "Freundschaft, Kommunikation, Korrespondenz, die Beziehung, flirten",
                "167 cm",
                "51 kg",
                "gewöhnlich",
                "europäisch",
                "Durchschnitt",
                "Russisch (Russisch)",
                "hetero",
                "rauche nicht",
                "Ich trinke gelegentlich in Unternehmen",
                "04/05/20",
                "Jungfrau",
                "Kein Raucher, kein Bauch und Schnurrbart",
                "Ich lerne",
                "separate Wohnung (vermietet oder eigene)",
                "Nein"
        ));

        searchList.add(new SearchItem(
                R.drawable.f3,
                "Kelly",
                "USA, Houston",
                26,
                "Carlson’s mood? I want sweets and foolishness?",
                "with a guy aged 41 - 80 years old",
                "meeting, date",
                "178 cm",
                "53кг",
                "--",
                "--",
                "--",
                "English",
                "hetero",
                "no smoking",
                "I do not smoke",
                "04/05/20",
                "Aquarius",
                "Carlson’s mood? I want sweets and foolishness?",
                "--",
                "--",
                "--"
        ));

        searchList.add(new SearchItem(
                R.drawable.f2,
                "Lisa",
                "Deutschland, Berlin",
                34,
                "Meine Bescheidenheit erlaubt mir nicht, über mich selbst zu sprechen.",
                "mit einem Mann im Alter von 32-60",
                "Freundschaft, Kommunikation, Sitzung, Datum",
                "170 cm",
                "56 kg",
                "Sport",
                "europäisch",
                "höher",
                "Russisch (Russisch)",
                "hetero",
                "rauche nicht",
                "--",
                "04/05/20",
                "Steinbock",
                "Meine Bescheidenheit erlaubt mir nicht, über mich selbst zu sprechen.",
                "--",
                "--",
                "nein"

        ));

        searchList.add(new SearchItem(
                R.drawable.f4,
                "Jane",
                "USA, Chicago",
                34,
                "I am looking for my man.",
                "with a guy aged 33 to 40 years old",
                "Friendship, communication, relations",
                "162 cm",
                "51 kg",
                "ordinary",
                "--",
                "--",
                "Russian",
                "hetero",
                "eat, live apart",
                "--",
                "04/05/20",
                "Taurus",
                "I am looking for my man.",
                "stable average income",
                "--",
                "--"
        ));

        searchList.add(new SearchItem(
                R.drawable.f52,
                "Kate",
                "USA, Phoenix",
                31,
                "Looking for dad",
                "with a guy aged 25-50",
                " flirt",
                "162 cm",
                "48 kg",
                "sports",
                "european",
                "higher",
                "Russian (Russian)",
                "another",
                "I do not smoke",
                "I rarely drink in companies",
                "04.04.20",
                "Lion",
                "Looking for dad",
                "No work",
                "dorm room or communal room",
                "no"
        ));
        searchList.add(new SearchItem(
                R.drawable.f6,
                "Julia",
                "USA, Los Angeles",
                27,
                "I want to please and pamper :) poor and greedy pass by!",
                "with a guy aged 28 - 45 years old",
                "Friendship, communication, correspondence",
                "173 cm",
                "55 кг",
                "thin",
                "european",
                "basic",
                "Russian (Russian)",
                "hetero",
                "I do not smoke",
                "don't drink at all",
                "04/05/20",
                "Aries",
                "I want to please and pamper :) poor and greedy pass by!",
                "--",
                "--",
                "no"
        ));
    }
}
