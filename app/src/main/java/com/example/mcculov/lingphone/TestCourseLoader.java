package com.example.mcculov.lingphone;

public class TestCourseLoader {

    public static CourseDefinition LoadCourseDef() {

        CourseDefinition cd = new CourseDefinition();

        cd.srcCourseName = "English (basic)";
        cd.nativeCourseName = "Курс английского языка";

        CourseDefinition.FileDefinition fd = cd.createFileDefinition(R.raw.intro_part_1);

        fd.splittingPhraseStarts.add( 2500);   //0
        fd.splittingPhraseStarts.add( 4000);
        fd.splittingPhraseStarts.add( 5700);
        fd.splittingPhraseStarts.add( 7300);
        fd.splittingPhraseStarts.add( 9500);
        fd.splittingPhraseStarts.add( 11900);
        fd.splittingPhraseStarts.add( 13800);
        fd.splittingPhraseStarts.add( 15700);
        fd.splittingPhraseStarts.add( 17900);
        fd.splittingPhraseStarts.add( 20200);
        fd.splittingPhraseStarts.add( 23000);   //10
        fd.splittingPhraseStarts.add( 25900);
        fd.splittingPhraseStarts.add( 28100);
        fd.splittingPhraseStarts.add( 30200);
        fd.splittingParts.add(0);
        
        fd.srcLang.partNames.add("INTRODUCTION Part 1");

        fd.srcLang.phrases.add("INTRODUCTION");
        fd.srcLang.phrases.add("Part One.");
        fd.srcLang.phrases.add("Hello.");
        fd.srcLang.phrases.add("Listen, please.");
        fd.srcLang.phrases.add("I'm Mr. Laker.");
        fd.srcLang.phrases.add("I'm English.");
        fd.srcLang.phrases.add("I'm a teacher.");
        fd.srcLang.phrases.add("You're student.");
        fd.srcLang.phrases.add("You aren't  English.");
        fd.srcLang.phrases.add("I'm speaking English.");
        fd.srcLang.phrases.add("You're learning English.");
        fd.srcLang.phrases.add("This is a book.");
        fd.srcLang.phrases.add("This is a cassette.");

        fd.nativeLang.partNames.add("ВВЕДЕНИЕ. Часть 1");

        fd.nativeLang.phrases.add("ВВЕДЕНИЕ");
        fd.nativeLang.phrases.add("Часть 1.");
        fd.nativeLang.phrases.add("Здравствуйте.");
        fd.nativeLang.phrases.add("Слушайте, пожалуйста.");
        fd.nativeLang.phrases.add("Я мистер Лейкер.");
        fd.nativeLang.phrases.add("Я англичанин.");
        fd.nativeLang.phrases.add("Я учитель.");
        fd.nativeLang.phrases.add("Вы учащийся.");
        fd.nativeLang.phrases.add("Вы не англичанин.");
        fd.nativeLang.phrases.add("Я говорю по-английски.");
        fd.nativeLang.phrases.add("Вы изучаете английский язык.");
        fd.nativeLang.phrases.add("Это книга.");
        fd.nativeLang.phrases.add("Это кассета.");

        cd.mediaFiles.add(fd);

        fd = cd.createFileDefinition(R.raw.intro_part_2);

        fd.splittingPhraseStarts.add( 2100);
        fd.splittingPhraseStarts.add( 3800);
        fd.splittingPhraseStarts.add( 6000);
        fd.splittingPhraseStarts.add( 9800);
        fd.splittingPhraseStarts.add( 11500);
        fd.splittingPhraseStarts.add( 14100);
        fd.splittingPhraseStarts.add( 16200);   //20
        fd.splittingPhraseStarts.add( 18300);
        fd.splittingPhraseStarts.add( 20300);
        fd.splittingPhraseStarts.add( 22600);
        fd.splittingPhraseStarts.add( 25200);
        fd.splittingPhraseStarts.add( 27300);
        fd.splittingPhraseStarts.add( 29400);
        fd.splittingPhraseStarts.add( 32200);
        fd.splittingPhraseStarts.add( 34500);
        fd.splittingPhraseStarts.add( 36800);
        fd.splittingPhraseStarts.add( 39100);   //30
        fd.splittingPhraseStarts.add( 40600);
        fd.splittingPhraseStarts.add( 42200);
        fd.splittingPhraseStarts.add( 44000);
        fd.splittingPhraseStarts.add( 45600);
        fd.splittingPhraseStarts.add( 47300);
        fd.splittingParts.add(0);

        fd.nativeLang.partNames.add("ВВЕДЕНИЕ. Часть 2");

        fd.nativeLang.phrases.add("Часть 2.");
        fd.nativeLang.phrases.add("Здравствуйте!");
        fd.nativeLang.phrases.add("Здравствуйте, мистер Лейкер.");
        fd.nativeLang.phrases.add("Как Вы поживаете?");
        fd.nativeLang.phrases.add("Очень хорошо.");
        fd.nativeLang.phrases.add("Как Вы поживаете?");
        fd.nativeLang.phrases.add("Очень хорошо, спасибо.");
        fd.nativeLang.phrases.add("Вы учащийся?");
        fd.nativeLang.phrases.add("Да.");
        fd.nativeLang.phrases.add("Вы англичанин?");
        fd.nativeLang.phrases.add("Нет.");
        fd.nativeLang.phrases.add("Вы изучаете английский язык?");
        fd.nativeLang.phrases.add("Да.");
        fd.nativeLang.phrases.add("У Вас есть книга?");
        fd.nativeLang.phrases.add("Да, у меня есть книга.");
        fd.nativeLang.phrases.add("Это французская книга?");
        fd.nativeLang.phrases.add("Нет.");
        fd.nativeLang.phrases.add("Это английская книга.");
        fd.nativeLang.phrases.add("Хорошо. Где она?");
        fd.nativeLang.phrases.add("Вот она.");
        fd.nativeLang.phrases.add("Где?");
        fd.nativeLang.phrases.add("Она на столе.");

        fd.srcLang.partNames.add("INTRODUCTION Part 2");

        fd.srcLang.phrases.add("Part Two.");
        fd.srcLang.phrases.add("Hello!");
        fd.srcLang.phrases.add("Hello, Mr. Laker.");
        fd.srcLang.phrases.add("How are you?");
        fd.srcLang.phrases.add("I'm very well.");
        fd.srcLang.phrases.add("How are you?");
        fd.srcLang.phrases.add("I'm very well, thank you.");
        fd.srcLang.phrases.add("Are you a student?");
        fd.srcLang.phrases.add("Yes, I am.");
        fd.srcLang.phrases.add("Are you English?");
        fd.srcLang.phrases.add("No, I'm not.");
        fd.srcLang.phrases.add("Are you learning English?");
        fd.srcLang.phrases.add("Yes, I am.");
        fd.srcLang.phrases.add("Have you got a book?");
        fd.srcLang.phrases.add("Yes I've got a book.");
        fd.srcLang.phrases.add("Is it the French book?");
        fd.srcLang.phrases.add("No, it isn't.");
        fd.srcLang.phrases.add("It's the English book.");
        fd.srcLang.phrases.add("Good. Where is it?");
        fd.srcLang.phrases.add("Here it is.");
        fd.srcLang.phrases.add("Where?");
        fd.srcLang.phrases.add("It's on the table.");

        cd.mediaFiles.add(fd);

        return cd;
    }
}
