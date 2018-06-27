package com.example.mcculov.lingphone;

public class TestCourseLoader {

    public static CourseDefinition LoadCourseDef() {

        CourseDefinition cd = new CourseDefinition();

        cd.mediaFileIDs.add(R.raw.intro_part_1);
        cd.mediaFileIDs.add(R.raw.intro_part_2);

        cd.addSplittingPhrase(R.raw.intro_part_1, 2500);   //0
        cd.addSplittingPhrase(R.raw.intro_part_1, 4000);
        cd.addSplittingPhrase(R.raw.intro_part_1, 5700);
        cd.addSplittingPhrase(R.raw.intro_part_1, 7300);
        cd.addSplittingPhrase(R.raw.intro_part_1, 9500);
        cd.addSplittingPhrase(R.raw.intro_part_1, 11900);
        cd.addSplittingPhrase(R.raw.intro_part_1, 13800);
        cd.addSplittingPhrase(R.raw.intro_part_1, 15700);
        cd.addSplittingPhrase(R.raw.intro_part_1, 17900);
        cd.addSplittingPhrase(R.raw.intro_part_1, 20200);
        cd.addSplittingPhrase(R.raw.intro_part_1, 23000);   //10
        cd.addSplittingPhrase(R.raw.intro_part_1, 25900);
        cd.addSplittingPhrase(R.raw.intro_part_1, 28100);
        cd.addSplittingPhrase(R.raw.intro_part_1, 30200);

        cd.addSplittingPhrase(R.raw.intro_part_2, 2100);
        cd.addSplittingPhrase(R.raw.intro_part_2, 3800);
        cd.addSplittingPhrase(R.raw.intro_part_2, 6000);
        cd.addSplittingPhrase(R.raw.intro_part_2, 9800);
        cd.addSplittingPhrase(R.raw.intro_part_2, 11500);
        cd.addSplittingPhrase(R.raw.intro_part_2, 14100);
        cd.addSplittingPhrase(R.raw.intro_part_2, 16200);   //20
        cd.addSplittingPhrase(R.raw.intro_part_2, 18300);
        cd.addSplittingPhrase(R.raw.intro_part_2, 20300);
        cd.addSplittingPhrase(R.raw.intro_part_2, 22600);
        cd.addSplittingPhrase(R.raw.intro_part_2, 25200);
        cd.addSplittingPhrase(R.raw.intro_part_2, 27300);
        cd.addSplittingPhrase(R.raw.intro_part_2, 29400);
        cd.addSplittingPhrase(R.raw.intro_part_2, 32200);
        cd.addSplittingPhrase(R.raw.intro_part_2, 34500);
        cd.addSplittingPhrase(R.raw.intro_part_2, 36800);
        cd.addSplittingPhrase(R.raw.intro_part_2, 39100);   //30
        cd.addSplittingPhrase(R.raw.intro_part_2, 40600);
        cd.addSplittingPhrase(R.raw.intro_part_2, 42200);
        cd.addSplittingPhrase(R.raw.intro_part_2, 44000);
        cd.addSplittingPhrase(R.raw.intro_part_2, 45600);
        cd.addSplittingPhrase(R.raw.intro_part_2, 47300);

        cd.splittingParts.add(0);
        cd.splittingParts.add(14);

        //

        cd.nativeLang.CourseName = "Курс английского языка";

        cd.nativeLang.partNames.add("ВВЕДЕНИЕ. Часть 1");
        cd.nativeLang.partNames.add("ВВЕДЕНИЕ. Часть 2");

        cd.nativeLang.phrases.add("ВВЕДЕНИЕ");
        cd.nativeLang.phrases.add("Часть 1.");
        cd.nativeLang.phrases.add("Здравствуйте.");
        cd.nativeLang.phrases.add("Слушайте, пожалуйста.");
        cd.nativeLang.phrases.add("Я мистер Лейкер.");
        cd.nativeLang.phrases.add("Я англичанин.");
        cd.nativeLang.phrases.add("Я учитель.");
        cd.nativeLang.phrases.add("Вы учащийся.");
        cd.nativeLang.phrases.add("Вы не англичанин.");
        cd.nativeLang.phrases.add("Я говорю по-английски.");
        cd.nativeLang.phrases.add("Вы изучаете английский язык.");
        cd.nativeLang.phrases.add("Это книга.");
        cd.nativeLang.phrases.add("Это кассета.");

        cd.nativeLang.phrases.add("Часть 2.");
        cd.nativeLang.phrases.add("Здравствуйте!");
        cd.nativeLang.phrases.add("Здравствуйте, мистер Лейкер.");
        cd.nativeLang.phrases.add("Как Вы поживаете?");
        cd.nativeLang.phrases.add("Очень хорошо.");
        cd.nativeLang.phrases.add("Как Вы поживаете?");
        cd.nativeLang.phrases.add("Очень хорошо, спасибо.");
        cd.nativeLang.phrases.add("Вы учащийся?");
        cd.nativeLang.phrases.add("Да.");
        cd.nativeLang.phrases.add("Вы англичанин?");
        cd.nativeLang.phrases.add("Нет.");
        cd.nativeLang.phrases.add("Вы изучаете английский язык?");
        cd.nativeLang.phrases.add("Да.");
        cd.nativeLang.phrases.add("У Вас есть книга?");
        cd.nativeLang.phrases.add("Да, у меня есть книга.");
        cd.nativeLang.phrases.add("Это французская книга?");
        cd.nativeLang.phrases.add("Нет.");
        cd.nativeLang.phrases.add("Это английская книга.");
        cd.nativeLang.phrases.add("Хорошо. Где она?");
        cd.nativeLang.phrases.add("Вот она.");
        cd.nativeLang.phrases.add("Где?");
        cd.nativeLang.phrases.add("Она на столе.");

        //

        cd.foreignLang.CourseName = "Курс английского языка";

        cd.foreignLang.partNames.add("INTRODUCTION Part 1");
        cd.foreignLang.partNames.add("INTRODUCTION Part 2");

        cd.foreignLang.phrases.add("INTRODUCTION");
        cd.foreignLang.phrases.add("Part One.");
        cd.foreignLang.phrases.add("Hello.");
        cd.foreignLang.phrases.add("Listen, please.");
        cd.foreignLang.phrases.add("I'm Mr. Laker.");
        cd.foreignLang.phrases.add("I'm English.");
        cd.foreignLang.phrases.add("I'm a teacher.");
        cd.foreignLang.phrases.add("You're student.");
        cd.foreignLang.phrases.add("You aren't  English.");
        cd.foreignLang.phrases.add("I'm speaking English.");
        cd.foreignLang.phrases.add("You're learning English.");
        cd.foreignLang.phrases.add("This is a book.");
        cd.foreignLang.phrases.add("This is a cassette.");

        cd.foreignLang.phrases.add("Part Two.");
        cd.foreignLang.phrases.add("Hello!");
        cd.foreignLang.phrases.add("Hello, Mr. Laker.");
        cd.foreignLang.phrases.add("How are you?");
        cd.foreignLang.phrases.add("I'm very well.");
        cd.foreignLang.phrases.add("How are you?");
        cd.foreignLang.phrases.add("I'm very well, thank you.");
        cd.foreignLang.phrases.add("Are you a student?");
        cd.foreignLang.phrases.add("Yes, I am.");
        cd.foreignLang.phrases.add("Are you English?");
        cd.foreignLang.phrases.add("No, I'm not.");
        cd.foreignLang.phrases.add("Are you learning English?");
        cd.foreignLang.phrases.add("Yes, I am.");
        cd.foreignLang.phrases.add("Have you got a book?");
        cd.foreignLang.phrases.add("Yes I've got a book.");
        cd.foreignLang.phrases.add("Is it the French book?");
        cd.foreignLang.phrases.add("No, it isn't.");
        cd.foreignLang.phrases.add("It's the English book.");
        cd.foreignLang.phrases.add("Good. Where is it?");
        cd.foreignLang.phrases.add("Here it is.");
        cd.foreignLang.phrases.add("Where?");
        cd.foreignLang.phrases.add("It's on the table.");

        return cd;
    }
}
