CREATE TABLE IF NOT EXISTS `course_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '类型名称',
  `organ_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '机构ID',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE ,
  KEY `idx_organ_id`(`organ_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '课程类型表';

CREATE TABLE IF NOT EXISTS `course` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '课程名称',
  `code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '课程编码',
  `course_type_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '类型ID',
  `introduce` TEXT NOT NULL DEFAULT '' COMMENT '课程介绍',
  `organ_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '机构ID',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name`(`name`) USING BTREE ,
  KEY `idx_organ_id`(`organ_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '课程表';

CREATE TABLE IF NOT EXISTS `classes` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '班级名称',
  `code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '班级编码',
  `introduce` TEXT NOT NULL DEFAULT '' COMMENT '班级介绍',
  `status` VARCHAR(255) NOT NULL DEFAULT 'NOT_START' COMMENT '班级状态',
  `year` INT NOT NULL DEFAULT 0 COMMENT '学年',
  `student_count` INT NOT NULL DEFAULT 0 COMMENT '学生总数',
  `lesson_count` INT NOT NULL DEFAULT 0 COMMENT '课次',
  `lesson_period_min` INT NOT NULL DEFAULT 0 COMMENT '课时(分)',
  `term` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '学期',
  `open_date` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '开课日期',
  `classes_time` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '上课时间',
  `classroom` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '教室',
  `course_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '课程Id',
  `cost` DOUBLE NOT NULL DEFAULT 0.00 COMMENT '费用',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name_course`(`name`,`course_id`) USING BTREE ,
  KEY `idx_course_id`(`course_id`) USING BTREE,
  KEY `idx_year`(`year`) USING BTREE,
  KEY `idx_status`(`status`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '班级表';

CREATE TABLE IF NOT EXISTS `student_classes_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '学生ID',
  `classes_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_student_id`(`student_id`) USING BTREE,
  KEY `idx_classes_id`(`classes_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '学生班级关系表';

CREATE TABLE IF NOT EXISTS `teacher_classes_relation` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '教师ID',
  `classes_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '班级ID',
  `gmt_create` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_teacher_id`(`teacher_id`) USING BTREE,
  KEY `idx_classes_id`(`classes_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '老师班级关系表';
