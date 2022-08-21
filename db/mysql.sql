create database godfunc;
use godfunc;
drop table if exists mg_config;
create table mg_config
(
    id          bigint(20)   not null comment '主键',
    name        varchar(512) not null comment '名称',
    value       text comment '值',
    remark      text comment '备注',
    create_id   bigint(20)   not null comment '创建人',
    update_id   bigint(20) default null comment '更新人',
    create_time datetime     not null comment '创建时间',
    update_time datetime   default null comment '更新时间',
    primary key (id),
    index idx_name (name),
    index idx_create_time (create_time)
) engine = innodb
  default charset = utf8mb4 comment ='配置表';

drop table if exists mg_log_operation;
create table mg_log_operation
(
    id             bigint(20) not null comment '主键',
    operation      varchar(512) default null comment '用户操作',
    request_url    varchar(512) default null comment '请求地址',
    request_params text comment '请求参数',
    request_time   int(11)    not null comment '请求时长(毫秒)',
    user_agent     varchar(512) default null comment '用户代理',
    ip             varchar(64)  default null comment 'ip地址',
    status         tinyint(2) not null comment '状态 0失败 1成功',
    create_id      bigint(20)   default null comment '创建人',
    create_user    varchar(128) default null comment '用户名',
    create_time    datetime   not null comment '创建时间',
    primary key (id),
    index idx_create_time (create_time),
    index idx_operation (operation),
    index idx_create_user (create_user)
) engine = innodb
  default charset = utf8mb4 comment ='操作日志表';


drop table if exists mg_menu;
create table mg_menu
(
    id          bigint(20)              not null comment '主键',
    pid         bigint(20)    default 0 not null comment '父id',
    path        varchar(512)  default null comment '路由地址',
    component   varchar(512)            not null comment '组件',
    type        tinyint(4)              not null comment '类型 1菜单 2按钮',
    redirect    varchar(512)  default null comment '跳转地址',
    name        varchar(256)  default null comment 'the name is used by <keep-alive> (must set!!!)',
    always_show tinyint(1)    default 0 not null comment '只有一个子菜单时是否显示主菜单',
    permissions varchar(1024) default null comment '菜单权限',
    breadcrumb  tinyint(1)    default 1 not null comment '0隐藏面包屑 1不隐藏面包屑',
    active_menu varchar(512)  default null comment '如果设置，将高亮指定菜单',
    title       varchar(128)            null comment '标题',
    icon        varchar(128)            null comment '图标',
    sort        int(11)       default 0 not null comment '菜单排序',
    status      tinyint(1)    default 1 not null comment '状态 0停用 1启用',
    primary key (id),
    index idx_sort (sort)
) engine = innodb
  default charset = utf8mb4 comment ='菜单表';


drop table if exists mg_role;
create table mg_role
(
    id          bigint(20)   not null comment '主键',
    name        varchar(128) not null comment '角色名',
    remark      varchar(1024) default null comment '备注',
    create_id   bigint(20)   not null comment '创建人',
    update_id   bigint(20)    default null comment '更新人',
    create_time datetime     not null comment '创建时间',
    update_time datetime      default null comment '更新时间',
    primary key (id),
    unique uk_name (name)
) engine = innodb
  default charset = utf8mb4 comment ='角色表';


drop table if exists mg_role_menu;
create table mg_role_menu
(
    id          bigint(20) not null comment '主键',
    role_id     bigint(20) not null comment '角色id',
    menu_id     bigint(20) not null comment '菜单id',
    create_id   bigint(20) not null comment '创建人',
    create_time datetime   not null comment '创建时间',
    primary key (id),
    key idx_role_id (role_id),
    key idx_menu_id (menu_id)
) engine = innodb
  default charset = utf8mb4 comment ='角色菜单关联表';


drop table if exists mg_user;
create table mg_user
(
    id            bigint(20)              not null comment '主键',
    username      varchar(128)            not null comment '用户名',
    password      varchar(128)            not null comment '密码',
    gender        tinyint(2)  default null comment '性别 1男 2女 3未知',
    mobile        varchar(64) default null comment '手机号',
    super_manager tinyint(2)  default 0   not null comment '超级管理员 0不是 1是',
    status        tinyint(2)  default 1   not null comment '状态 0停用 1启用',
    create_id     bigint(20)              not null comment '创建人',
    update_id     bigint(20)  default null comment '更新人',
    create_time   datetime                not null comment '创建时间',
    update_time   datetime    default null comment '更新时间',
    rm_tag        tinyint(2)  default '0' not null comment '删除标记 0正常 1删除',
    primary key (id),
    unique uk_username (username),
    index idx_create_time (create_time)
) engine = innodb
  default charset = utf8mb4 comment ='系统用户';


drop table if exists mg_user_role;
create table mg_user_role
(
    id          bigint(20) not null comment '主键',
    user_id     bigint(20) not null comment '用户id',
    role_id     bigint(20) not null comment '角色id',
    create_id   bigint(20) not null comment '创建人',
    create_time datetime   not null comment '创建时间',
    primary key (id),
    index idx_role_id (role_id),
    index idx_user_id (user_id)
) engine = innodb
  default charset = utf8mb4 comment ='用户角色关联表';


drop table if exists mg_user_token;
create table mg_user_token
(
    id          bigint(20)   not null comment '主键',
    user_id     bigint(20)   not null comment '用户id',
    token       varchar(512) not null comment 'token',
    expire_time datetime     not null comment '过期时间',
    create_time datetime     not null comment '创建时间',
    primary key (id),
    unique uk_token (token)
) engine = innodb
  default charset = utf8mb4 comment ='用户token表';


-- api module tables
-- user table
create table t_user
(
    id          bigint(20)           not null comment '主键',
    username    varchar(128)         not null comment '用户名',
    password    varchar(128)         not null comment '密码',
    mobile      varchar(16)          not null comment '手机号',
    status      tinyint(2) default 1 not null comment '用户状态 0停用 1启用',
    create_time datetime             not null comment '创建时间',
    update_time datetime             null comment '创建时间',
    rm_tag      tinyint(2) default 0 not null comment '删除标记 0正常 1删除',
    primary key (id),
    key idx_create_time (create_time)
) engine = innodb
  default charset = utf8mb4 comment '用户表';


create table t_user_token
(
    id          bigint(20)   not null comment '主键',
    user_id     bigint(20)   not null comment '用户id',
    token       varchar(512) not null comment 'token',
    expire_time datetime     not null comment '过期时间',
    create_time datetime     not null comment '创建时间',
    primary key (id),
    unique index (user_id),
    unique key token (token)
) engine = innodb
  default charset = utf8mb4 comment '用户token表';


insert into `mg_menu`(`id`, `pid`, `path`, `component`, `type`, `redirect`, `name`, `always_show`, `permissions`,
                      `breadcrumb`, `active_menu`, `title`, `icon`, `sort`, `status`)
values (1, 0, '/system', 'Layout', 1, NULL, '系统功能', 1, NULL, 1, NULL, '系统功能', 'system', 0, 1),
       (2, 1, 'menu', 'menu/index', 1, NULL, '菜单管理', 0, NULL, 1, NULL, '菜单管理', 'menu', 2, 1),
       (1207275475953606658, 1, 'user', 'user/index', 1, NULL, '用户管理', 0, 'mg:role:list,mg:role:getByUser', 1, NULL,
        '用户管理', 'user', 1, 1),
       (1207276248561152001, 1, 'role', 'role/index', 1, NULL, '角色管理', 0, NULL, 1, NULL, '角色管理', 'role', 3, 1),
       (1207276483303763969, 1, 'log', 'log/index', 1, NULL, '日志', 0, 'mg:log:page', 1, NULL, '日志', 'log', 5, 1),
       (1207276926083854337, 2, NULL, 'Button', 2, NULL, '新增根菜单', 0, 'mg:menu:rootadd,mg:menu:add', 1, NULL, NULL, NULL,
        0, 1),
       (1207277652847685634, 0, 'http://localhost:9568/manage/swagger-ui.html', 'Layout', 1, NULL, '接口文档', 0, NULL, 1,
        NULL, '接口文档', 'link', 1, 1),
       (1207312840671674369, 1207275475953606658, NULL, 'Button', 2, NULL, '查询', 0, 'mg:user:page', 1, NULL, NULL, NULL,
        0, 1),
       (1207312948930854913, 1207275475953606658, NULL, 'Button', 2, NULL, '新增', 0, 'mg:user:add', 1, NULL, NULL, NULL,
        0, 1),
       (1207313094955548673, 1207275475953606658, NULL, 'Button', 2, NULL, '修改', 0, 'mg:user:edit', 1, NULL, NULL, NULL,
        0, 1),
       (1207313165424050178, 1207275475953606658, NULL, 'Button', 2, NULL, '删除', 0, 'mg:user:remove', 1, NULL, NULL,
        NULL, 0, 1),
       (1207315365105811457, 1207276248561152001, NULL, 'Button', 2, NULL, '查询', 0, 'mg:role:page', 1, NULL, NULL, NULL,
        0, 1),
       (1207315486644158465, 1207276248561152001, NULL, 'Button', 2, NULL, '新增', 0,
        'mg:role:add,mg:role:getMenus,mg:menu:getTree', 1, NULL, NULL, NULL, 0, 1),
       (1207315552452788226, 1207276248561152001, NULL, 'Button', 2, NULL, '修改', 0,
        'mg:role:edit,mg:role:getMenus,mg:menu:getTree', 1, NULL, NULL, NULL, 0, 1),
       (1207315799031726081, 1207276248561152001, NULL, 'Button', 2, NULL, '删除', 0, 'mg:role:remove', 1, NULL, NULL,
        NULL, 0, 1),
       (1207474062448738306, 2, NULL, 'Button', 2, NULL, '新增', 0, 'mg:menu:add', 1, NULL, NULL, NULL, 0, 1),
       (1207478281733939201, 2, NULL, 'Button', 2, NULL, '修改', 0, 'mg:menu:edit', 1, NULL, NULL, NULL, 0, 1),
       (1207478488014004226, 2, NULL, 'Button', 2, NULL, '删除', 0, 'mg:menu:remove', 1, NULL, NULL, NULL, 0, 1),
       (1207480932173357057, 1207276483303763969, NULL, 'Button', 2, NULL, '查询', 0, 'mg:log:page', 1, NULL, NULL, NULL,
        0, 1),
       (1207481715275079681, 2, NULL, 'Button', 2, NULL, '查询', 0, 'mg:menu:getAll', 1, NULL, NULL, NULL, 0, 1),
       (1475720671933751297, 1, 'config', 'config/index', 1, '', '配置管理', 0, '', 1, '', '配置管理', 'nested', 4, 1),
       (1475720734483406850, 1475720671933751297, NULL, 'Button', 2, '', '查询', 0, 'mg:config:page', 1, '', '', '', 0,
        1),
       (1475720833649336321, 1475720671933751297, NULL, 'Button', 2, '', '新增', 0, 'mg:config:add', 1, '', '', '', 0, 1),
       (1475720947457581057, 1475720671933751297, NULL, 'Button', 2, '', '修改', 0, 'mg:config:edit', 1, '', '', '', 0,
        1),
       (1475721014012796930, 1475720671933751297, NULL, 'Button', 2, '', '删除', 0, 'mg:config:remove', 1, '', '', '', 0,
        1);

insert into mg_user(id, username, password, gender, mobile, super_manager, status, create_id,
                    update_id, create_time, update_time, rm_tag)
values (1, 'admin', '$2a$10$NZp1NOTR/uR1Oh8IMVlyPemaueL8P.ZlcIvGNKdw7CZRnEfItjO/W', 1, '17332345631', 1, 1, 0, NULL,
        '2019-12-18 20:29:16', NULL, 0);
