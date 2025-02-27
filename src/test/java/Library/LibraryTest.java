package Library;

import org.testng.annotations.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryTest {
    Library newLibrary = makeTestLibrary();

    private Library makeTestLibrary() {
        Library newLibrary = new Library();
        newLibrary.add(new Book("Гарри Поттер и философский камень", "Дж.К.Роулинг", "Роман", "А1"));
        newLibrary.add(new Book("Гарри Поттер и Тайная комната", "Дж.К.Роулинг", "Роман", "А2"));
        newLibrary.add(new Book("Гарри Поттер и дары Смерти", "Дж.К.Роулинг", "Роман", "А3"));
        newLibrary.add(new Book("Голодные игры", "С.Коллинз", "Фантастика", "А4"));
        newLibrary.add(new Book("Десять негритят", "Агата Кристи", "Детектив", "А5"));
        newLibrary.add(new Book("Вельд", "Р.Брэдбери", "Фантастика", "А4"));
        newLibrary.add(new Book("Мастер и Маргарита", "М.Булгакова", "Роман", "А6"));
        newLibrary.add(new Book("Цветы для Элджернона", "Дэниела Киза", "Рассказ", "Б1"));
        newLibrary.add(new Book("451 градус по Фаренгейту", "Р.Брэдбери", "Роман", "А4"));
        newLibrary.add(new Book("Улыбка", "Р.Брэдбери", "Рассказ", "Б3"));
        newLibrary.add(new Book("Герой нашего времени", "М.Ю.Лермонтов", "Роман", "Б3"));
        return newLibrary;
    }

    @Test
    void add() {
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.add(null));
        newLibrary.add(new Book("Каждый сам миллионер", "А.Грин", "Рассказ", "А8"));
        assertTrue(newLibrary.contains(
                       new Book("Каждый сам миллионер", "А.Грин", "Рассказ", "А8")));
        newLibrary.add(new Book("Шерлок Холмс", "А.К.Дойл", "Детектив", "А9"));
        assertTrue(newLibrary.contains(
                new Book("Шерлок Холмс", "А.К.Дойл", "Детектив", "А9")));
        assertFalse(newLibrary.contains(
                new Book("Три мушкетёра", "А.Дюма", "Роман", "Б1")));
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.add(new Book("", "Ф.Достоевский", "Роман", "Б2")));
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.add(new Book("Преступление и наказание", "Ф.Достоевский", "Роман", "Б*")));
    }

    @Test
    void delete() {
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.delete(null));
        newLibrary.delete(new Book("Гарри Поттер и философский камень", "Дж.К.Роулинг", "Роман", "А1"));
        assertFalse(newLibrary.contains(
                new Book("Гарри Поттер и философский камень", "Дж.К.Роулинг", "Роман", "А1")));
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.delete(new Book("Преступление и наказание", "", "Роман", "Б2")));
    }

    @Test
    void change() {
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.change(null, null));
        newLibrary.change(new Book("Гарри Поттер и Тайная комната", "Дж.К.Роулинг", "Роман", "А2"),
                new Book("Гарри Поттер и узник Азкабана", "Дж.К.Роулинг", "Роман", "А3"));
        assertTrue(newLibrary.contains(
                new Book("Гарри Поттер и узник Азкабана", "Дж.К.Роулинг", "Роман", "А3")));
        assertFalse(newLibrary.contains(
                new Book("Гарри Поттер и Тайная комната", "Дж.К.Роулинг", "Роман", "А2")));
        assertThrows(IllegalArgumentException.class, () -> newLibrary.change(new Book("АА", "АА", "АА", "А2"),
                new Book("Каждый сам миллионер", "А.Грин", "Рассказ", "А8")));
    }

    @Test
    void replaceCode() {
        assertThrows(IllegalArgumentException.class, () ->
                newLibrary.replaceCode(null, "А1"));
        newLibrary.replaceCode(new Book("Голодные игры", "С.Коллинз", "Фантастика", "А4"), "А2");
        assertTrue(newLibrary.contains(
                new Book("Голодные игры", "С.Коллинз", "Фантастика", "А2")));
        assertFalse(newLibrary.contains(
                new Book("Голодные игры", "С.Коллинз", "Фантастика", "А4")));
        assertThrows(IllegalArgumentException.class, () -> newLibrary.replaceCode(
                new Book("АА", "АА", "АА", "А2"), "А1"));
    }

    @Test
    void search() {

        assertEquals(List.of(new Book("Улыбка", "Р.Брэдбери", "Рассказ", "Б3"),
                        new Book("Герой нашего времени", "М.Ю.Лермонтов", "Роман", "Б3")),
                newLibrary.search(null, null, null, "Б3", null)
        );
        assertEquals(List.of(new Book("Герой нашего времени", "М.Ю.Лермонтов", "Роман", "Б3")),
                newLibrary.search(null, null, null, null, "Герой")
        );
        assertEquals(List.of(new Book("Вельд", "Р.Брэдбери", "Фантастика", "А4"),
                        new Book("451 градус по Фаренгейту", "Р.Брэдбери", "Роман", "А4"),
                        new Book("Улыбка", "Р.Брэдбери", "Рассказ", "Б3")),
                newLibrary.search(null, "Р.Брэдбери", null, null, null)
        );
    }

}