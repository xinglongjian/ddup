CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `real_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '中文名称',
  `en_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '英文名称',
  `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码',
  `telephone` VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `from_source` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '来源',
  `created_by` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '由谁添加',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name`(`user_name`) USING BTREE ,
  UNIQUE KEY `idx_telephone`(`telephone`) USING BTREE ,
  UNIQUE KEY `idx_email`(`email`) USING BTREE,
  KEY `idx_status`(`status`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `user_data` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `sex` VARCHAR(255) NOT NULL DEFAULT 'SECRECY' COMMENT '性别',
  `birthday` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '生日',
  `head_image` TEXT COMMENT '头像',
  `id_card_type` VARCHAR(255) NOT NULL DEFAULT 'IDCARD' COMMENT '证件类型',
  `id_card_no` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号',
  `country` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '国家',
  `province` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '省/直辖市',
  `city` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '市',
  `district` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '区',
  `address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '其他地址',
  `introduce` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '个人介绍',
  `weixin` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信',
  `qq` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id`(`user_id`) USING BTREE ,
  UNIQUE KEY `idx_id_card_no`(`id_card_no`) USING BTREE,
  KEY `idx_birthday`(`birthday`) USING BTREE,
  KEY `idx_sex`(`sex`) USING BTREE,
  KEY `idx_province_city_district`(`province`,`city`,`district`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户资料表';

CREATE TABLE IF NOT EXISTS `school` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `en_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '英文名称',
  `code` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '编码',
  `telephone` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '电话',
  `head_image` TEXT COMMENT '头像',
  `province` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '省/直辖市',
  `city` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '市',
  `district` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '区',
  `address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '其他地址',
  `zip_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮编',
  `introduce` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '介绍',
  `enabled` TINYINT NOT NULL DEFAULT 1 COMMENT '是否可用',
  `build_year` INT NOT NULL DEFAULT 0 COMMENT '创办年份',
  `parent_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父ID',
  `type` VARCHAR(255) NOT NULL DEFAULT 'SCHOOL' COMMENT '类型',
  `created_user_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '该记录创建人',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE ,
  UNIQUE KEY `idx_code`(`code`) USING BTREE ,
  KEY `idx_province_city_district`(`province`,`city`,`district`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '学校表';

CREATE TABLE IF NOT EXISTS `teacher_school_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '教师ID',
  `school_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学校ID',
  `type` VARCHAR(255) NOT NULL DEFAULT 'OTHER' COMMENT '管理员类型',
  `alias` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '别名',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_teacher_id`(`teacher_id`) USING BTREE,
  KEY `idx_school_id`(`school_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '老师学校关系表';

CREATE TABLE IF NOT EXISTS `student_parent_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学生ID',
  `parent_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '家长ID',
  `relation` VARCHAR(255) NOT NULL DEFAULT 'MOTHER' COMMENT '关系类型',
  `alias` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '别名',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_student_id`(`student_id`) USING BTREE,
  KEY `idx_parent_id`(`parent_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '学生家长关系表';

CREATE TABLE IF NOT EXISTS `student_grade_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学生ID',
  `grade_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `duty` VARCHAR(255) NOT NULL DEFAULT 'NONE' COMMENT '职务',
  `alias` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '别名',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_student_id`(`student_id`) USING BTREE,
  KEY `idx_grade_id`(`grade_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '学生班级关系表';

CREATE TABLE IF NOT EXISTS `teacher_grade_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '教师ID',
  `grade_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `type` VARCHAR(255) NOT NULL DEFAULT 'MAIN' COMMENT '关系类型',
  `alias` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '别名',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_teacher_id`(`teacher_id`) USING BTREE,
  KEY `idx_grade_id`(`grade_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '老师班级关系表';

CREATE TABLE IF NOT EXISTS `grade` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `school_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学校ID',
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `year` INT NOT NULL DEFAULT 0 COMMENT '哪年级',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建者',
  `status` VARCHAR(255) NOT NULL DEFAULT 'INITIALIZATION' COMMENT '初始化',
  `is_need_validate` TINYINT NOT NULL DEFAULT 1 COMMENT '是否需要验证',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_school_id`(`school_id`) USING BTREE,
  KEY `idx_created_user_id`(`created_user_id`) USING BTREE,
  KEY `idx_year`(`year`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '班级表';

CREATE TABLE IF NOT EXISTS `grade_validate_message` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `send_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '发送消息UserID',
  `student_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学生ID',
  `message` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '消息',
  `validate_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '验证消息UserID',
  `grade_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `result` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '验证结果',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_send_user_id`(`send_user_id`) USING BTREE ,
  KEY `idx_validate_user_id`(`validate_user_id`) USING BTREE,
  KEY `idx_grade_id`(`grade_id`) USING BTREE,
  KEY `idx_result`(`result`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '班级验证消息表';

CREATE TABLE IF NOT EXISTS `habit` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '习惯名称',
  `code` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '习惯编码',
  `habit_type_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '类型ID',
  `description` TEXT COMMENT '描述',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE ,
  UNIQUE KEY `idx_code`(`code`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '习惯表';

CREATE TABLE IF NOT EXISTS `habit_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `code` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '编码',
  `description` TEXT COMMENT '描述',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE ,
  UNIQUE KEY `idx_code`(`code`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '习惯类型表';

CREATE TABLE IF NOT EXISTS `user_habit_record` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `habit_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '习惯ID',
  `score` INT NOT NULL DEFAULT 0 COMMENT '分数',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '谁打分',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户习惯分数表';

CREATE TABLE IF NOT EXISTS `user_habit_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `habit_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '习惯ID',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '谁加入',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户习惯关系表';

CREATE TABLE IF NOT EXISTS `question_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '类型名称',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '问题类型表';

CREATE TABLE IF NOT EXISTS `question` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` TEXT COMMENT '内容',
  `type_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '问题类型ID',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建者',
  `status` varchar(20) NOT NULL DEFAULT 'NEW' COMMENT '名称',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_type_id`(`type_id`) USING BTREE ,
  KEY `idx_status`(`status`) USING BTREE ,
  UNIQUE KEY `idx_created_user_id`(`created_user_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '问题表';

CREATE TABLE IF NOT EXISTS `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` int(255) DEFAULT NULL COMMENT '区号',
  `name` varchar(255) DEFAULT NULL,
  `parent_no` int(255) DEFAULT NULL COMMENT '父-区号',
  `area_code` varchar(255) DEFAULT NULL COMMENT '电话区号',
  `area_level` int(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `abbreviate` varchar(10) DEFAULT NULL COMMENT '简写',
  `post_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `pinyin` varchar(255) DEFAULT NULL COMMENT '拼音',
  `pinyin_brief` varchar(255) DEFAULT NULL COMMENT '拼音缩写',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区划表';