-- Dumping structure for table public.passengers
CREATE TABLE IF NOT EXISTS "passengers" (
	"id" SERIAL PRIMARY KEY,
	"active" BOOLEAN NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"email" VARCHAR(255) NOT NULL UNIQUE,
	"name" VARCHAR(50) NOT NULL,
	"password" VARCHAR(255) NOT NULL,
	"role" VARCHAR(255) NULL DEFAULT NULL,
	"updated_at" TIMESTAMP NULL DEFAULT NULL
);

-- Dumping data for table public.passengers: -1 rows
/*!40000 ALTER TABLE "passengers" DISABLE KEYS */;
INSERT INTO "passengers" ("id", "active", "created_at", "email", "name", "password", "role", "updated_at") VALUES
	(101, 'true', NULL, 'kuro@vale.com', 'Julian', '$2a$10$amNA6GFejRsSd12k5LBmdO2I6uA5.yYPrGYcySRxkuBzbwY30Y2LG', 'ADMIN', NULL),
	(102, 'true', '2022-05-19 23:25:01.722038', 'nnannoni0@tmall.com', 'Nils Nannoni', '$2a$10$ULMUgWEdnBe.BzSFVOKD8uw6oZNjfaLw/JjW1sK7sSIlffq1.9gVa', 'PASSENGER', '2022-05-19 23:25:01.722061'),
	(103, 'true', '2022-05-19 23:25:12.681031', 'ppeperell0@reuters.com', 'Paddie Peperell', '$2a$10$fIBF.dHdgXI5VJ97i8ALzeVnYN3CEuaDVkd76wnhgq1qRMd7LzcjS', 'PASSENGER', '2022-05-19 23:25:12.681045'),
	(104, 'true', '2022-05-19 23:25:20.049981', 'eschimank0@imgur.com', 'Eadie Schimank', '$2a$10$8mUD4qew1XVEG7PWQmfAPuwW5PrBIs//GZZBEX5OTzffW/sX2qMvW', 'PASSENGER', '2022-05-19 23:25:20.05'),
	(105, 'true', '2022-05-19 23:25:28.023581', 'pdurman0@google.com.au', 'Pincus Durman', '$2a$10$gXd8CY/IKC2A1yzm1irjYeCDRuaLr/j8H328KA8kgcY3h9p1zHayS', 'PASSENGER', '2022-05-19 23:25:28.023595'),
	(106, 'true', '2022-05-19 23:26:31.940646', 'gscotchford0@europa.eu', 'Garwin Scotchford', '$2a$10$xf9dn.1yAv/M8yL.vpmAYeKYq31ia5RefMWuZaUwwVPQA87a1G3HC', 'PASSENGER', '2022-05-19 23:26:31.940659'),
	(107, 'true', '2022-05-19 23:26:41.281092', 'gduiged0@blogspot.com', 'Gage Duiged', '$2a$10$X6/9gWRJzDu3NRMQgBG/0O1RzU5JeuG6M00.S8NzhTYR7QcfGOtJy', 'PASSENGER', '2022-05-19 23:26:41.281106'),
	(108, 'true', '2022-05-19 23:26:49.121697', 'bduetsche0@networkadvertising.org', 'Bibbye Duetsche', '$2a$10$YvK5eMUbW6NxYOtzCTpAXeJZ5pTs0hWc1xasEoqJeeP6k5mEb0cYy', 'PASSENGER', '2022-05-19 23:26:49.121713'),
	(109, 'true', '2022-05-19 23:26:57.49354', 'rduguid0@51.la', 'Raul Duguid', '$2a$10$1g4Wefcc6rGkOIjGc4.6C.gO4vnHfCvHTUOg0Pr7MbmawTBddz56C', 'PASSENGER', '2022-05-19 23:26:57.493554'),
	(1010, 'true', '2022-05-19 23:27:04.530881', 'syeliashev0@so-net.ne.jp', 'Sibyl Yeliashev', '$2a$10$fWesO58FoPkkbrlaEHDsEeaiypC0HBcUvcpm4HOrLNKPUx0T08F1e', 'PASSENGER', '2022-05-19 23:27:04.530925'),
	(1011, 'true', '2022-05-19 23:27:12.568293', 'mcharle0@squidoo.com', 'Maxy Charle', '$2a$10$9PDt9E5FLoP9WL3dHovUGuHTVWcU3WrCZLnewFIj6AqICLJ1cpV4a', 'PASSENGER', '2022-05-19 23:27:12.56832'),
	(1012, 'true', '2022-05-19 23:27:19.392876', 'rvicker0@seesaa.net', 'Raddy Vicker', '$2a$10$mLJL6yxIzK8QNNgR4jwEWeuh5fmMavNv7j7KqX61uhkIYn.UDRLmW', 'PASSENGER', '2022-05-19 23:27:19.392897'),
	(1013, 'true', '2022-05-19 23:27:26.669236', 'rmoodie0@instagram.com', 'Rabi Moodie', '$2a$10$jpbHaEfGQFZNrDrP4.24bOAE9ANbmYqee/.eBnXE1TIaysG14E6DS', 'PASSENGER', '2022-05-19 23:27:26.669251'),
	(1014, 'true', '2022-05-19 23:27:33.748072', 'khuccaby0@va.gov', 'Krista Huccaby', '$2a$10$M7j/9AB.Tu7CUGijLLwG7.BM3.LoFKKucCfPGbYlCHNy8GcH75Zj6', 'PASSENGER', '2022-05-19 23:27:33.748093'),
	(1015, 'true', '2022-05-19 23:27:40.558588', 'eyackiminie0@diigo.com', 'Elton Yackiminie', '$2a$10$QXQ47JTAnvIQuTGJfCdvjOf47gNqXSvg8Tcm0.oGp00aZPu9yfxM2', 'PASSENGER', '2022-05-19 23:27:40.558603'),
	(1016, 'true', '2022-05-19 23:27:47.280899', 'etampion0@spiegel.de', 'Evita Tampion', '$2a$10$h7Fl/5KHRVG7VuQ62WCaSu2yD7whFBbq6KHmEA9IpepzSavWqKKIC', 'PASSENGER', '2022-05-19 23:27:47.280914'),
	(1017, 'true', '2022-05-19 23:27:54.067412', 'ejobbings0@slideshare.net', 'Evie Jobbings', '$2a$10$FRioQG3sppFC4ENDIhe17eyHjTwCvQBnP13YwrPHcmVgjnUReHKC.', 'PASSENGER', '2022-05-19 23:27:54.067426'),
	(1018, 'true', '2022-05-19 23:28:01.087526', 'gbrauner0@ocn.ne.jp', 'Giff Brauner', '$2a$10$vUDWUR269MnnAKeFj37Tv.eVuwB7qAxlYyvwzSKI8xB3TdZKqoKLi', 'PASSENGER', '2022-05-19 23:28:01.087546'),
	(1019, 'true', '2022-05-19 23:28:11.052364', 'gnorcock0@sfgate.com', 'Gabriello Norcock', '$2a$10$gGHWKRljSiz.kYiVOnAzfup6xVxSoyZTvJFxalz28GvyjjJiLXvne', 'PASSENGER', '2022-05-19 23:28:11.052378'),
	(1020, 'true', '2022-05-19 23:28:19.051731', 'ldavies0@ehow.com', 'Lonnard Davies', '$2a$10$0hLS.GinpV02DH0Hlp.p0eTFB3Nhl8ynNXkzcVfLNOhRTtyvporoK', 'PASSENGER', '2022-05-19 23:28:19.051753'),
	(1021, 'true', '2022-05-19 23:28:26.877794', 'rtopham0@hostgator.com', 'Ramsay Topham', '$2a$10$0H4XHK0k5kJ3hSAD7SDasucqvk5jLrsw95hG9mC9HZrl9G1AVIZEm', 'PASSENGER', '2022-05-19 23:28:26.877831'),
	(1022, 'true', '2022-05-19 23:28:33.904096', 'qschumacher0@samsung.com', 'Quintina Schumacher', '$2a$10$KSP4dvak6Hea67aiGcFzmOW6CmbM.8fp6qRcYRksNX746Ay3dAPhe', 'PASSENGER', '2022-05-19 23:28:33.904116'),
	(1023, 'true', '2022-05-19 23:28:41.167095', 'lborrett0@umn.edu', 'Laurette Borrett', '$2a$10$F/8Mw1fOg/JKyLXKfMd2R.OAy85jPxj3HxBdthEz/1NM2CbylEBde', 'PASSENGER', '2022-05-19 23:28:41.167111'),
	(1024, 'true', '2022-05-19 23:28:48.382272', 'snorville0@skype.com', 'Seymour Norville', '$2a$10$673IJgoDTz1g0u9r/HhUxeD4B.RPx/KDc4M.BaHDVAa4F4.DXfxI6', 'PASSENGER', '2022-05-19 23:28:48.382287'),
	(1025, 'true', '2022-05-19 23:28:55.367239', 'ifussen0@geocities.com', 'Ingar Fussen', '$2a$10$0wMNx3NCX8hk2zV9AelUVOzZalyheiwTW3jK31r33gP/Cua1CEyra', 'PASSENGER', '2022-05-19 23:28:55.367254'),
	(1026, 'true', '2022-05-19 23:29:02.216959', 'ccluckie0@zimbio.com', 'Carmel Cluckie', '$2a$10$VbfDxfUaAntaJDaYNU64IOD4.HGvubEtc8Ngz44zs5N1cCajRM6C6', 'PASSENGER', '2022-05-19 23:29:02.21698'),
	(1027, 'true', '2022-05-19 23:29:10.161178', 'cmaybury0@pen.io', 'Cecelia Maybury', '$2a$10$6Yygox1I30IGoTqzjdOfO.lX0rocCLwhktP39DMz4UgXmoQSIvYKW', 'PASSENGER', '2022-05-19 23:29:10.161193'),
	(1028, 'true', '2022-05-19 23:29:17.139293', 'ldinnington0@photobucket.com', 'Ly Dinnington', '$2a$10$muh2oCk8uk80ObwDIhsmr.2uDxdToK5EpVcxXOBeC8Cnz5D8kB3O6', 'PASSENGER', '2022-05-19 23:29:17.139315'),
	(1029, 'true', '2022-05-19 23:29:23.407456', 'ishillabeare0@dropbox.com', 'Issiah Shillabeare', '$2a$10$uiempij6yjWys8xHep02e.0rbTj2Kg.p5U3UNM6AX4tTksSV9fZvW', 'PASSENGER', '2022-05-19 23:29:23.40747'),
	(1030, 'true', '2022-05-19 23:29:31.792327', 'mmarler0@cafepress.com', 'Markos Marler', '$2a$10$YuxeMmqk5t7flbHUw79TEOHBrrWUJBfoppxEfAVDuJ3vVF2vvg7LS', 'PASSENGER', '2022-05-19 23:29:31.792341'),
	(1031, 'true', '2022-05-19 23:29:37.993287', 'dspellard0@vk.com', 'Dov Spellard', '$2a$10$c2FgFz7mXmmDZJzpQl8by.gIgwSJZyQcE5ZfxbceM86uis.uKyQ7e', 'PASSENGER', '2022-05-19 23:29:37.993302');
/*!40000 ALTER TABLE "passengers" ENABLE KEYS */;

-- Dumping structure for table public.stations
CREATE TABLE IF NOT EXISTS "stations" (
	"id" SERIAL PRIMARY KEY,
	"active" BOOLEAN NOT NULL,
	"address" VARCHAR(50) NOT NULL UNIQUE,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"name" VARCHAR(50) NOT NULL,
	"phone" VARCHAR(255) NOT NULL UNIQUE,
	"updated_at" TIMESTAMP NULL DEFAULT NULL
);

-- Dumping data for table public.stations: -1 rows
/*!40000 ALTER TABLE "stations" DISABLE KEYS */;
INSERT INTO "stations" ("id", "active", "address", "created_at", "name", "phone", "updated_at") VALUES
	(101, 'true', '73 Anhalt Circle', '2022-05-19 23:32:07.809327', 'Rieder', '(182) 4427094', '2022-05-19 23:32:07.809344'),
	(102, 'true', '3510 Elgar Circle', '2022-05-19 23:32:22.258801', 'Rockefeller', '(912) 2877029', '2022-05-19 23:32:22.258814'),
	(103, 'true', '15586 Montana Parkway', '2022-05-19 23:32:31.464176', 'Lawn', '(140) 4895746', '2022-05-19 23:32:31.464189'),
	(104, 'true', '57 Michigan Pass', '2022-05-19 23:32:39.936432', 'Lotheville', '(911) 4530826', '2022-05-19 23:32:39.93645'),
	(105, 'true', '490 Stuart Lane', '2022-05-19 23:32:56.993801', 'Dorton', '(543) 5454112', '2022-05-19 23:32:56.993814'),
	(106, 'true', '36664 Sage Street', '2022-05-19 23:33:05.723794', '7th', '(302) 1054638', '2022-05-19 23:33:05.723807'),
	(107, 'true', '1 Green Hill', '2022-05-19 23:33:14.014485', 'Sycamore', '(336) 2517137', '2022-05-19 23:33:14.014498'),
	(108, 'true', '86 Anhalt Point', '2022-05-19 23:33:21.471287', 'Transport', '(440) 3664402', '2022-05-19 23:33:21.471306'),
	(109, 'true', '48 Sherman Road', '2022-05-19 23:33:30.08121', 'Tennessee', '(732) 8010887', '2022-05-19 23:33:30.081222'),
	(1010, 'true', '9 Starling Terrace', '2022-05-19 23:33:38.31498', 'Ruskin', '(570) 5292532', '2022-05-19 23:33:38.314992'),
	(1011, 'true', '196 Longview Parkway', '2022-05-19 23:33:45.293031', 'Bunker Hill', '(558) 1542059', '2022-05-19 23:33:45.293048'),
	(1012, 'true', '641 Orin Hill', '2022-05-19 23:33:51.771999', 'Cody', '(906) 4203443', '2022-05-19 23:33:51.772012'),
	(1013, 'true', '68841 Forest Avenue', '2022-05-19 23:33:58.904867', 'Namekagon', '(380) 8073648', '2022-05-19 23:33:58.90488'),
	(1014, 'true', '9061 Sauthoff Center', '2022-05-19 23:34:07.595175', 'Prairieview', '(228) 7514167', '2022-05-19 23:34:07.595189'),
	(1015, 'true', '1122 Buell Circle', '2022-05-19 23:34:15.324142', 'Moose', '(174) 9858990', '2022-05-19 23:34:15.324159'),
	(1016, 'true', '66303 Burrows Drive', '2022-05-19 23:34:22.611725', 'Loftsgordon', '(249) 5287798', '2022-05-19 23:34:22.611757'),
	(1017, 'true', '23 Menomonie Crossing', '2022-05-19 23:34:29.465043', 'John Wall', '(513) 7453555', '2022-05-19 23:34:29.465055'),
	(1018, 'true', '70 Bluestem Drive', '2022-05-19 23:34:37.108315', 'Mitchell', '(400) 8383252', '2022-05-19 23:34:37.108328'),
	(1019, 'true', '39 Melvin Street', '2022-05-19 23:34:45.248728', 'Crownhardt', '(948) 8998603', '2022-05-19 23:34:45.248745'),
	(1020, 'true', '5606 Chinook Way', '2022-05-19 23:34:51.948965', 'Blackbird', '(413) 6573852', '2022-05-19 23:34:51.948979'),
	(1021, 'true', '875 Oneill Terrace', '2022-05-19 23:34:59.87443', 'Montana', '(260) 1789893', '2022-05-19 23:34:59.874444'),
	(1022, 'true', '3542 Coleman Place', '2022-05-19 23:35:08.004187', 'Coolidge', '(725) 9241372', '2022-05-19 23:35:08.004207'),
	(1023, 'true', '0 Butterfield Court', '2022-05-19 23:35:17.461282', 'Fairfield', '(494) 2370968', '2022-05-19 23:35:17.461295'),
	(1024, 'true', '0338 Oriole Place', '2022-05-19 23:35:25.949617', 'Lukken', '(502) 8574108', '2022-05-19 23:35:25.94965'),
	(1025, 'true', '7 Lunder Terrace', '2022-05-19 23:35:33.320283', 'Hooker', '(899) 1350692', '2022-05-19 23:35:33.320296'),
	(1026, 'true', '15 Hanson Trail', '2022-05-19 23:35:41.310393', 'Gulseth', '(464) 1451557', '2022-05-19 23:35:41.31041'),
	(1027, 'true', '51 Burning Wood Center', '2022-05-19 23:35:55.315876', 'Fuller', '(460) 9231782', '2022-05-19 23:35:55.315922'),
	(1028, 'true', '4275 Chinook Court', '2022-05-19 23:36:02.75452', 'Summerview', '(281) 5292029', '2022-05-19 23:36:02.754529'),
	(1029, 'true', '1 Ruskin Plaza', '2022-05-19 23:36:10.221948', 'Hoepker', '(727) 7389507', '2022-05-19 23:36:10.221962'),
	(1030, 'true', '40383 Texas Center', '2022-05-19 23:36:17.275082', 'Dovetail', '(680) 1038549', '2022-05-19 23:36:17.275091');
/*!40000 ALTER TABLE "stations" ENABLE KEYS */;

-- Dumping structure for table public.trains
CREATE TABLE IF NOT EXISTS "trains" (
	"id" SERIAL PRIMARY KEY,
	"active" BOOLEAN NOT NULL,
	"capacity" INTEGER NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"model" VARCHAR(255) NOT NULL,
	"model_year" INTEGER NOT NULL,
	CONSTRAINT "trains_capacity_check" CHECK ((((capacity <= 500) AND (capacity >= 100)))),
	CONSTRAINT "trains_model_year_check" CHECK ((((model_year <= 2022) AND (model_year >= 1980))))
);

-- Dumping data for table public.trains: -1 rows
/*!40000 ALTER TABLE "trains" DISABLE KEYS */;
INSERT INTO "trains" ("id", "active", "capacity", "created_at", "model", "model_year") VALUES
	(101, 'true', 340, '2022-05-19 23:37:35.721676', 'M-Class', 2001),
	(102, 'true', 168, '2022-05-19 23:37:52.043891', 'C-Class', 1998),
	(103, 'true', 197, '2022-05-19 23:38:02.465376', 'Justy', 1990),
	(104, 'true', 455, '2022-05-19 23:38:12.330607', 'TL', 1999),
	(105, 'true', 341, '2022-05-19 23:38:21.639622', 'RX', 2007),
	(106, 'true', 218, '2022-05-19 23:38:29.989399', 'E-350 Super Duty', 2006),
	(107, 'true', 239, '2022-05-19 23:38:37.339819', 'S-Type', 2002),
	(108, 'true', 446, '2022-05-19 23:38:45.174388', 'Camry', 2004),
	(109, 'true', 366, '2022-05-19 23:38:51.855067', 'GLI', 2008),
	(1010, 'true', 441, '2022-05-19 23:38:58.964025', '3 Series', 2001);
/*!40000 ALTER TABLE "trains" ENABLE KEYS */;

-- Dumping structure for table public.travels
CREATE TABLE IF NOT EXISTS "travels" (
	"id" SERIAL PRIMARY KEY,
	"arrival_date" TIMESTAMP NULL DEFAULT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"departure_date" TIMESTAMP NOT NULL,
	"price" INTEGER NOT NULL,
	"status" INTEGER NOT NULL,
	"arrival_station_id" BIGINT NOT NULL,
	"departure_station_id" BIGINT NOT NULL,
	"train_id" BIGINT NOT NULL,
	CONSTRAINT "fk20gukenk3imx4dcwfu4h16csw" FOREIGN KEY ("arrival_station_id") REFERENCES "stations" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fk7txi4esydcoh1uus1egiaqb7r" FOREIGN KEY ("train_id") REFERENCES "trains" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fkx3hxo2uxie88nuu2y36w3ee7" FOREIGN KEY ("departure_station_id") REFERENCES "stations" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "travels_price_check" CHECK ((((price <= 1000) AND (price >= 10))))
);

-- Dumping data for table public.travels: -1 rows
/*!40000 ALTER TABLE "travels" DISABLE KEYS */;
INSERT INTO "travels" ("id", "arrival_date", "created_at", "departure_date", "price", "status", "arrival_station_id", "departure_station_id", "train_id") VALUES
	(101, NULL, '2022-05-19 23:46:37.483998', '2050-01-26 09:59:00', 745, 0, 108, 1029, 109),
	(102, NULL, '2022-05-19 23:47:00.824508', '2050-02-07 15:49:00', 522, 0, 1017, 1026, 107),
	(103, NULL, '2022-05-19 23:47:31.923019', '2050-12-19 11:00:00', 918, 0, 104, 103, 108),
	(104, NULL, '2022-05-19 23:47:47.451136', '2050-01-06 05:49:00', 749, 0, 1029, 1026, 101),
	(105, NULL, '2022-05-19 23:48:00.223157', '2050-09-20 21:56:00', 380, 0, 1030, 103, 1010),
	(106, NULL, '2022-05-19 23:48:14.870054', '2050-12-06 03:32:00', 119, 0, 104, 102, 106),
	(107, NULL, '2022-05-19 23:48:26.209146', '2050-10-03 08:15:00', 750, 0, 107, 1013, 106),
	(108, NULL, '2022-05-19 23:49:22.118077', '2050-07-13 00:28:00', 548, 0, 1022, 1018, 103),
	(109, NULL, '2022-05-19 23:49:41.011875', '2050-08-04 05:07:00', 285, 0, 1025, 107, 103),
	(1010, NULL, '2022-05-19 23:49:52.936097', '2050-08-03 19:32:00', 559, 0, 1015, 1028, 101);
/*!40000 ALTER TABLE "travels" ENABLE KEYS */;

-- Dumping structure for table public.tickets
CREATE TABLE IF NOT EXISTS "tickets" (
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"passenger_id" BIGINT NOT NULL,
	"travel_id" BIGINT NOT NULL,
	PRIMARY KEY ("passenger_id", "travel_id"),
	CONSTRAINT "fk1ds262xq345nkvs5o9ptnftwr" FOREIGN KEY ("passenger_id") REFERENCES "passengers" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fkgpm9km7dqnjw0gxe24ayifxig" FOREIGN KEY ("travel_id") REFERENCES "travels" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
