-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Май 31 2013 г., 23:38
-- Версия сервера: 5.5.8
-- Версия PHP: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `facultative`
--

-- --------------------------------------------------------

--
-- Структура таблицы `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(40) NOT NULL,
  `hours` int(11) NOT NULL,
  `ID_Instructor` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Instructor` (`ID_Instructor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `course`
--

INSERT INTO `course` (`ID`, `Name`, `hours`, `ID_Instructor`) VALUES
(1, 'Game theory', 100, 1),
(2, 'Social Media', 150, 2),
(3, 'Русский язык ', 200, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `courseentry`
--

CREATE TABLE IF NOT EXISTS `courseentry` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Course` int(11) NOT NULL,
  `ID_Student` int(11) NOT NULL,
  `Grade` varchar(20) NOT NULL DEFAULT 'IN_PROGRESS',
  PRIMARY KEY (`ID`),
  KEY `ID_Course` (`ID_Course`),
  KEY `ID_Student` (`ID_Student`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Дамп данных таблицы `courseentry`
--

INSERT INTO `courseentry` (`ID`, `ID_Course`, `ID_Student`, `Grade`) VALUES
(1, 1, 1, 'A'),
(2, 1, 2, 'C'),
(3, 2, 2, 'C'),
(6, 1, 3, 'A'),
(7, 2, 3, 'C'),
(8, 2, 1, 'B'),
(9, 3, 12, 'IN_PROGRESS');

-- --------------------------------------------------------

--
-- Структура таблицы `instructor`
--

CREATE TABLE IF NOT EXISTS `instructor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_UserData` int(11) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Surname` varchar(40) NOT NULL,
  `Speciality` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_UserData` (`ID_UserData`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `instructor`
--

INSERT INTO `instructor` (`ID`, `ID_UserData`, `Name`, `Surname`, `Speciality`) VALUES
(1, 6, 'Michael', 'Limitless', 'Theoretical Physics'),
(2, 7, 'Alexander', 'Greywall', 'Social behaviour');

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_UserData` int(11) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Surname` varchar(40) NOT NULL,
  `Institute` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_UserData` (`ID_UserData`),
  KEY `ID_UserData_2` (`ID_UserData`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`ID`, `ID_UserData`, `Name`, `Surname`, `Institute`) VALUES
(1, 3, 'Bob', 'YellowCard', 'New york public school'),
(2, 4, 'Sam', 'Cloverfield', 'North Korean hidden institute'),
(3, 5, 'Nick', 'Smith', 'London private school 222'),
(12, 8, 'Иван', 'Иванов', 'Киевский Политехничный институт');

-- --------------------------------------------------------

--
-- Структура таблицы `userdata`
--

CREATE TABLE IF NOT EXISTS `userdata` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(40) NOT NULL,
  `Password` varchar(40) NOT NULL,
  `isInstructor` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Дамп данных таблицы `userdata`
--

INSERT INTO `userdata` (`ID`, `Login`, `Password`, `isInstructor`) VALUES
(3, 'StudentFirst', 'StudentFirst', 0),
(4, 'StudentSecond', 'StudentSecond', 0),
(5, 'StudentThird', 'StudentSecond', 0),
(6, 'InstructorFirst', 'InstructorFirst', 1),
(7, 'InstructorSecond', 'InstructorSecond', 1),
(8, 'RussianStudent', 'RussianStudent', 0);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`ID_Instructor`) REFERENCES `instructor` (`ID`);

--
-- Ограничения внешнего ключа таблицы `courseentry`
--
ALTER TABLE `courseentry`
  ADD CONSTRAINT `courseentry_ibfk_1` FOREIGN KEY (`ID_Course`) REFERENCES `course` (`ID`),
  ADD CONSTRAINT `courseentry_ibfk_2` FOREIGN KEY (`ID_Student`) REFERENCES `student` (`ID`);

--
-- Ограничения внешнего ключа таблицы `instructor`
--
ALTER TABLE `instructor`
  ADD CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`ID_UserData`) REFERENCES `userdata` (`ID`);

--
-- Ограничения внешнего ключа таблицы `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`ID_UserData`) REFERENCES `userdata` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
