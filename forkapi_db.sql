-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Авг 05 2020 г., 01:25
-- Версия сервера: 5.6.43
-- Версия PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `forkapi_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `bet`
--

CREATE TABLE `bet` (
  `id` bigint(20) NOT NULL,
  `bet_date` date DEFAULT NULL,
  `bet_sum` float NOT NULL,
  `coefficient` float NOT NULL,
  `match_title` varchar(255) DEFAULT NULL,
  `team` varchar(255) DEFAULT NULL,
  `bk_account_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `bet`
--

INSERT INTO `bet` (`id`, `bet_date`, `bet_sum`, `coefficient`, `match_title`, `team`, `bk_account_id`) VALUES
(8, '2020-08-03', 500, 2.3, 'some dota or cs match', 'some team', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `bk_account`
--

CREATE TABLE `bk_account` (
  `id` bigint(20) NOT NULL,
  `balance` float NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `bookmaker_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `bk_account`
--

INSERT INTO `bk_account` (`id`, `balance`, `login`, `password`, `bookmaker_id`, `user_id`) VALUES
(6, 20000, 'bkLogin', 'bkPassword', 5, 3),
(7, 0, 'bkLogin1', 'bkPassword', 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `bookmaker`
--

CREATE TABLE `bookmaker` (
  `id` bigint(20) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `bookmaker`
--

INSERT INTO `bookmaker` (`id`, `link`, `title`) VALUES
(5, 'www.parimatch.com', 'parimatch');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(9),
(9),
(9),
(9),
(9);

-- --------------------------------------------------------

--
-- Структура таблицы `settings`
--

CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL,
  `balance_percent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `settings`
--

INSERT INTO `settings` (`id`, `balance_percent`) VALUES
(2, 5),
(4, 5);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `signup_date` date DEFAULT NULL,
  `subscribe_end_date` date DEFAULT NULL,
  `settings_id` bigint(20) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `signup_date`, `subscribe_end_date`, `settings_id`, `role`, `token`) VALUES
(1, 'test', '098f6bcd4621d373cade4e832627b4f6', '2020-08-01', '2020-09-01', 2, 0, 'token228'),
(3, 'test1', '098f6bcd4621d373cade4e832627b4f6', '2020-08-04', '2020-09-03', 4, 1, '699679f38595e51ed155a181f3270c85');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `bet`
--
ALTER TABLE `bet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2cxaqwqtf21dsma1gp4536dmu` (`bk_account_id`);

--
-- Индексы таблицы `bk_account`
--
ALTER TABLE `bk_account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgrg805giu1ga0t2am60eq1xyy` (`bookmaker_id`),
  ADD KEY `FKnt236qfcb26apflytt230gdf0` (`user_id`);

--
-- Индексы таблицы `bookmaker`
--
ALTER TABLE `bookmaker`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKexjamu52wpct0e0y77vawpcjo` (`settings_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `bet`
--
ALTER TABLE `bet`
  ADD CONSTRAINT `FK2cxaqwqtf21dsma1gp4536dmu` FOREIGN KEY (`bk_account_id`) REFERENCES `bk_account` (`id`);

--
-- Ограничения внешнего ключа таблицы `bk_account`
--
ALTER TABLE `bk_account`
  ADD CONSTRAINT `FKgrg805giu1ga0t2am60eq1xyy` FOREIGN KEY (`bookmaker_id`) REFERENCES `bookmaker` (`id`),
  ADD CONSTRAINT `FKnt236qfcb26apflytt230gdf0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKexjamu52wpct0e0y77vawpcjo` FOREIGN KEY (`settings_id`) REFERENCES `settings` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
