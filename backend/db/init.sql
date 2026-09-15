-- 骨架联调初始化脚本：创建数据库、示例表与种子数据
-- 用法：mysql -uroot < backend/db/init.sql

CREATE DATABASE IF NOT EXISTS skeleton DEFAULT CHARACTER SET utf8mb4;

USE skeleton;

CREATE TABLE IF NOT EXISTS demo_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 幂等种子数据（仅在表为空时插入）
INSERT INTO demo_item (name, description)
SELECT * FROM (
    SELECT '条目一' AS name, 'MySQL 种子数据 1' AS description
    UNION ALL SELECT '条目二', 'MySQL 种子数据 2'
    UNION ALL SELECT '条目三', 'MySQL 种子数据 3'
) AS seeds
WHERE NOT EXISTS (SELECT 1 FROM demo_item LIMIT 1);
