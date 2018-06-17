/*User*/
insert into user(ID,user_name,nick_name,real_name,`password`,en_name,email,from_source,`status`,created_user_id) VALUES (1, 'admin','admin','超级管理员','admin123','admin','admin@xx.com','INIT', 1, 1);

/*School*/
INSERT INTO school(id,name,en_name,code,build_year,telephone,created_user_id) VALUES (1,'九一幼儿园','','',1960,'010-62011220',1);

/*HabitType*/
INSERT INTO habit_type(id,name,description,`status`) VALUES(1,'学习方面','',1);
INSERT INTO habit_type(id,name,description,`status`) VALUES(2,'规则方面','',1);
INSERT INTO habit_type(id,name,description,`status`) VALUES(3,'文明方面','',1);
INSERT INTO habit_type(id,name,description,`status`) VALUES(4,'礼貌方面','',1);
INSERT INTO habit_type(id,name,description,`status`) VALUES(5,'节约方面','',1);

/*Habit*/
INSERT INTO habit(id,name,habit_type_id,description,image,`status`,sex_scope, age_start, age_end) VALUES(1,'做作业', 1, '','',1,0,3,7);
INSERT INTO habit(id,name,habit_type_id,description,image,`status`,sex_scope, age_start, age_end) VALUES(2,'读书', 1, '','',1,0,3,7);