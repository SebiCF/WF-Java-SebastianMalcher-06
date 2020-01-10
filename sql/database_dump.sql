-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Jan 2020 um 21:31
-- Server-Version: 10.4.10-MariaDB
-- PHP-Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `cr6`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `classes`
--

CREATE TABLE `classes` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `classes`
--

INSERT INTO `classes` (`class_id`, `class_name`) VALUES
(1, '1a'),
(2, '1b'),
(3, '2a'),
(4, '2b');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(55) NOT NULL,
  `student_surname` varchar(55) NOT NULL,
  `student_email` varchar(55) NOT NULL,
  `fk_class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `student_surname`, `student_email`, `fk_class_id`) VALUES
(1, 'Greta', 'Wall', 'gretawall@blabla.com', 2),
(2, 'Mark', 'Pffag', 'markpffag@blabla.com', 1),
(3, 'Benjamin', 'Edreggirt', 'benjaminedreggirt@blabla.com', 1),
(4, 'Manuel', 'Mann', 'manuelamann@blabla.com', 2),
(5, 'Birgitt', 'Irgitt', 'birgittirgitt@blabla.com', 3),
(6, 'Lukas', 'Kappa', 'lukaskappa@blabla.com', 3),
(7, 'Christine', 'Magist', 'christinemagist@blabla.com', 3),
(8, 'Verena', 'Anerev', 'verenaanerev@blabla.com', 4),
(9, 'Will', 'Kith', 'willkith@blabla.com', 4),
(10, 'Luca', 'Müller', 'lucamüller@blabla.com', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teachers`
--

CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL,
  `teacher_name` varchar(55) NOT NULL,
  `teacher_surname` varchar(55) NOT NULL,
  `teacher_email` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `teacher_name`, `teacher_surname`, `teacher_email`) VALUES
(1, 'Helene', 'Grat', 'helenegrat@blabla.com'),
(2, 'Harald', 'Forher', 'haraldfohrer@blabla.com'),
(3, 'Gregor', 'Kratoz', 'gegorkratoz@blabla.com'),
(4, 'Doris', 'Göllermann', 'dorisgöllermann@blabla.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teacher_classes`
--

CREATE TABLE `teacher_classes` (
  `fk_teacher_id` int(11) DEFAULT NULL,
  `fk_class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `teacher_classes`
--

INSERT INTO `teacher_classes` (`fk_teacher_id`, `fk_class_id`) VALUES
(1, 1),
(1, 4),
(2, 2),
(4, 3),
(4, 4),
(2, 1),
(3, 2),
(3, 3);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`class_id`);

--
-- Indizes für die Tabelle `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `class_id` (`fk_class_id`);

--
-- Indizes für die Tabelle `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`teacher_id`);

--
-- Indizes für die Tabelle `teacher_classes`
--
ALTER TABLE `teacher_classes`
  ADD KEY `fk_teacher_id` (`fk_teacher_id`),
  ADD KEY `fk_class_id` (`fk_class_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `classes`
--
ALTER TABLE `classes`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `teachers`
--
ALTER TABLE `teachers`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_class_id` FOREIGN KEY (`fk_class_id`) REFERENCES `classes` (`class_id`);

--
-- Constraints der Tabelle `teacher_classes`
--
ALTER TABLE `teacher_classes`
  ADD CONSTRAINT `teacher_classes_ibfk_1` FOREIGN KEY (`fk_teacher_id`) REFERENCES `teachers` (`teacher_id`),
  ADD CONSTRAINT `teacher_classes_ibfk_2` FOREIGN KEY (`fk_class_id`) REFERENCES `classes` (`class_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
