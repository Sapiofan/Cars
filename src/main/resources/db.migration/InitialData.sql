insert into cars(id, name, brand, type, year, price, speed, engine_speed,
                 gearbox, seats, fuel_consumption, fuel_type, pledge, image)
values (1, '7 Series', 'BMV', 'sedan', 2023, 25000, 350, 9400, 'automatic', 4, 3.9, 'oil', 15000, 'https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/09/2023_Mercedes-Benz_Sclass_Gallery1.jpg'),
(2, 'S-Class', 'Mercedes-Benz', 'sedan', 2023, 40000, 390, 9900, 'automatic', 4, 3.2, 'gas', 15000, 'https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/07/2023_BMW_7_Series_Gallery1.jpg'),
(3, 'G-90', 'Genesis', 'sedan', 2023, 30000, 370, 8900, 'manual', 4, 3.9, 'oil', 15000, 'https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/08/2023_Genesis_G90_Gallery1.jpg'),
(4, 'S-90', 'Volvo', 'sedan', 2022, 20000, 310, 8500, 'automatic', 4, 3.5, 'oil', 15000, 'https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Volvo_S90_Gallery1.jpg'),
(5, 'Panamera', 'Porsche', 'sedan', 2022, 45000, 380, 9700, 'automatic', 4, 4.2, 'oil', 15000, 'https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Porsche_Panamera_Gallery1.jpg');

insert into preferences(id, preference)
values (1, 'Страхування'),
       (2, 'Дитяче крісло'),
       (3, 'wi-fi роутер'),
       (4, 'Холодильник'),
       (5, 'GPS-навігатор'),
       (6, 'Напої/закуски');