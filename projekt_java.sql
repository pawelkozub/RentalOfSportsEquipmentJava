-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 19 Cze 2014, 09:53
-- Wersja serwera: 5.5.21-log
-- Wersja PHP: 5.4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `projekt_java`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategorie`
--

CREATE TABLE IF NOT EXISTS `kategorie` (
  `ID_Kategorie` int(8) NOT NULL AUTO_INCREMENT,
  `Nazwa` varchar(30) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`ID_Kategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=17 ;

--
-- Zrzut danych tabeli `kategorie`
--

INSERT INTO `kategorie` (`ID_Kategorie`, `Nazwa`) VALUES
(1, 'Rower'),
(2, 'Narty'),
(3, 'Sanki'),
(4, 'Snowboard'),
(5, 'Kaski zimowe'),
(6, 'Kaski rowerowe'),
(7, 'Kijki'),
(8, 'Łyżwy'),
(9, 'Buty narciarskie'),
(10, 'Buty Snowboardwe'),
(11, 'Sprzęt Golfowe'),
(12, 'Windsurfing'),
(13, 'Skimboard'),
(14, 'Trikke'),
(15, 'Sprzęt Nurkowania'),
(16, 'Rolki');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE IF NOT EXISTS `klient` (
  `ID_klient` int(5) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `ulica` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `nr` varchar(10) COLLATE utf8_polish_ci NOT NULL,
  `kod` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `miasto` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`ID_klient`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=10015 ;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`ID_klient`, `imie`, `nazwisko`, `ulica`, `nr`, `kod`, `miasto`) VALUES
(10001, 'Paweł', 'Kozub', 'Reymonta', '17/220B', '30-059', 'Kraków'),
(10002, 'Jan', 'Nowak', 'Mickiewicza', '30', '30-123', 'Kraków'),
(10003, 'Monika', 'Wójcikł', 'Spokojna', '12', '33-533', 'Nowy Sącz'),
(10004, 'Monika', 'Koziołek', 'Wielopole', '345', '23-455', 'Myślenice'),
(10005, 'Magda', 'Woskowicz', 'os. Sportowe', '12/2', '23-222', 'Kraków'),
(10006, 'Karol', 'Stoch', 'Cisowa', '1', '33-231', 'Chrzanów'),
(10007, 'Piotr', 'Woskowicz', 'Spokojna', '23', '42-432', 'Kraków'),
(10008, 'Mateusz', 'Mucha', 'Mickiewicza', '12', '12-132', 'Skawina'),
(10009, 'Szymon', 'Gastoł', 'Sułuszowa', '23', '33-233', 'Sułuszowa'),
(10010, 'Mateusz', 'Konopka', 'Sienkiewicza', '123', '24-123', 'Wieliczka'),
(10011, 'Paweł', 'Panek', 'WIśniowa', '23', '23-234', 'Przeworsk'),
(10012, 'Małgorzata', 'Zalewska', 'Majowa', '13', '32-132', 'Słomniki'),
(10013, 'Paweł', 'Kozioł', 'Żwirki i Wigury', '234', '42-244', 'Wrocław'),
(10014, 'Michał', 'Kołodziej', 'Słowackiego', '13', '33-132', 'Kraków');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `magazyn`
--

CREATE TABLE IF NOT EXISTS `magazyn` (
  `ID_Magazyn` int(8) NOT NULL AUTO_INCREMENT,
  `ID_Kategorie` int(8) NOT NULL,
  `Producent` varchar(20) COLLATE utf8_polish_ci NOT NULL,
  `Nazwa` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `Model` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `Opis` text COLLATE utf8_polish_ci NOT NULL,
  `Cena` varchar(10) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`ID_Magazyn`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=10000014 ;

--
-- Zrzut danych tabeli `magazyn`
--

INSERT INTO `magazyn` (`ID_Magazyn`, `ID_Kategorie`, `Producent`, `Nazwa`, `Model`, `Opis`, `Cena`) VALUES
(10000001, 1, 'Romex', 'Rower Miejskie Damskie', 'EXG83454', '-rozmiar koła: 26"\r\n-bagażnik przedni i tylny', '20'),
(10000002, 1, 'Cross', 'Rower Górskie', 'Voltage ER-234', '-Profesjonalny Amoryzator\r\n-Koła 26" ', '120'),
(10000003, 2, 'Blizzard', 'Narty zjazdowe', 'ER-13E334', '-dla zaawansowany\r\n-wys 170 cm', '100'),
(10000004, 2, 'Volki', 'Narty biegowe', 'Progressor 80043', '-dla średnich\r\n-wys 140 cm', '60'),
(10000005, 1, 'KTM', 'Rower Górskie', 'Ultra 1964 LTD 27 2004', '- amortyzatory\r\n- bieg 3x8\r\n- koła 27"\r\n- wszystkie element są Shimano', '75'),
(10000006, 1, 'KTM', 'Rower Górskie', 'Lycan 2013', '- rama aluminiowa\r\n- koła 26"\r\n- biegi 3x7\r\n- jest amortyzator tylny\r\n ', '100'),
(10000007, 1, 'Scott', 'Rowe Górskie', 'Aspect 20 2012', '- rama stalowa\r\n- koła 26"\r\n- hamulec hydrauliczna dwu tarczowa przedni\r\n- hamulec hydrauliczna jedno tarczowa tylny\r\n', '150'),
(10000008, 1, 'Scott', 'Rower Crossowe', 'Sportster 60 2012', '- rama aluminiowa\r\n- koła 30"\r\n- jest koszyk na bindol ', '130'),
(10000009, 1, 'Romet', 'Rower Składane', 'Wigry 3 2012', '- koła 20"\r\n- bieg 3\r\n- są błotnik i bagażnik tylny, oświetlenia\r\n', '30'),
(10000010, 1, 'Dahon', 'Rower Składane', 'BoardWalk D8 2013', '- Bieg 8\r\n- Koła 20"\r\n- Dopasowany wzrost 142-193 cm\r\n- brak bagażnik i oświetlenia', '25'),
(10000011, 2, 'Fischer', 'Narty zjazdowe', 'Progressor 800 2014', '- wys. 165 cm\r\n- najnowszy model\r\n- ulepszenia jazda\r\n- ostry zakrętu\r\n- dla męskie', '180'),
(10000012, 2, 'Head', 'Narty zjazdowe', 'Supershape Magnum 2014', '- ulepszeni alpejskiego narciarstwa\r\n- wys. 149 cm', '180'),
(10000013, 16, 'Solex', 'Rolki Rekreacyjne', 'Ribon', '-rozmiar 42\r\n-kolor czarny', '40');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wypoczalnia`
--

CREATE TABLE IF NOT EXISTS `wypoczalnia` (
  `ID_wypocz` int(4) NOT NULL AUTO_INCREMENT,
  `ID_Klient` int(5) NOT NULL,
  `ID_Magazyn` int(8) NOT NULL,
  `Data_wypocz` date NOT NULL,
  `Data_zwrot` date NOT NULL,
  PRIMARY KEY (`ID_wypocz`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `wypoczalnia`
--

INSERT INTO `wypoczalnia` (`ID_wypocz`, `ID_Klient`, `ID_Magazyn`, `Data_wypocz`, `Data_zwrot`) VALUES
(1, 10009, 10000002, '2014-06-05', '2014-06-14');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
