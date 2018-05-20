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
  `level` VARCHAR(255) NOT NULL DEFAULT 'INFANT' COMMENT '类型级别',
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

CREATE TABLE IF NOT EXISTS `student_school_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学生ID',
  `school_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学校ID',
  `start_date` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '入学时间',
  `end_date` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '毕业时间',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_student_id`(`student_id`) USING BTREE,
  KEY `idx_school_id`(`school_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '学生学校关系表';

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
  `level` VARCHAR(255) NOT NULL DEFAULT 'BOTTOM' COMMENT '级别',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建者',
  `status` VARCHAR(255) NOT NULL DEFAULT 'INITIALIZATION' COMMENT '初始化',
  `is_need_validate` TINYINT NOT NULL DEFAULT 1 COMMENT '是否需要验证',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_school_id`(`school_id`) USING BTREE,
  KEY `idx_created_user_id`(`created_user_id`) USING BTREE,
  KEY `idx_level`(`level`) USING BTREE,
  KEY `idx_year`(`year`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '班级表';

CREATE TABLE IF NOT EXISTS `grade_album` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `grade_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `info` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建者',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_grade_id`(`grade_id`) USING BTREE,
  KEY `idx_created_user_id`(`created_user_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '班级相册表';

CREATE TABLE IF NOT EXISTS `grade_album_upload` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `album_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '相册ID',
  `position` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '位置',
  `info` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
  `created_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '上传者',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_album_id`(`album_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '相册上传表';

CREATE TABLE IF NOT EXISTS `grade_album_item` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `album_upload_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '相册上传ID',
  `path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '路径',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_album_upload_id`(`album_upload_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '相册上传照片表';

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