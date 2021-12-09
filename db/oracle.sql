create table mg_dict
(
    id          number(20) not null,
    name        varchar2(512) default null,
    value       clob          default null,
    remark      clob,
    create_id   number(20) not null,
    update_id   number(20)    default null,
    create_time date       not null,
    update_time date          default null,
    primary key (id)
);

comment on table mg_dict is '字典表';
comment on column mg_dict.id is '主键';
comment on column mg_dict.name is '名称';
comment on column mg_dict.value is '值';
comment on column mg_dict.remark is '备注';
comment on column mg_dict.create_id is '创建人';
comment on column mg_dict.update_id is '更新人';
comment on column mg_dict.create_time is '创建时间';
comment on column mg_dict.update_time is '更新时间';

create index mg_dict_idx_create_time on mg_dict (create_time);
create index mg_dict_uk_name on mg_dict (name);


create table mg_log_operation
(
    id             number(20) not null,
    operation      varchar2(512) default null,
    request_url    varchar2(512) default null,
    request_params clob,
    request_time   number(11) not null,
    user_agent     varchar(512)  default null,
    ip             varchar(64)   default null,
    status         number(2)  not null,
    create_id      number(20)    default null,
    create_user    varchar2(128) default null,
    create_time    date       not null,
    primary key (id)
);

comment on table mg_log_operation is '操作日志表';
comment on column mg_log_operation.id is '主键';
comment on column mg_log_operation.operation is '用户操作';
comment on column mg_log_operation.request_url is '请求地址';
comment on column mg_log_operation.request_params is '请求参数';
comment on column mg_log_operation.request_time is '请求时长(毫秒)';
comment on column mg_log_operation.user_agent is '用户代理';
comment on column mg_log_operation.ip is 'ip地址';
comment on column mg_log_operation.status is '状态 0失败 1成功';
comment on column mg_log_operation.create_id is '创建人';
comment on column mg_log_operation.create_user is '用户名';
comment on column mg_log_operation.create_time is '创建时间';

create index mg_log_operation_idx_create_time on mg_log_operation (create_time);
create index mg_log_operation_idx_operation on mg_log_operation (operation);
create index mg_log_operation_idx_create_user on mg_log_operation (create_user);


create table mg_menu
(
    id          number(20)               not null,
    pid         number(20)     default 0 not null,
    path        varchar2(512)  default null,
    component   varchar2(512)            not null,
    type        number(4)                not null,
    redirect    varchar2(512)  default null,
    name        varchar2(256)  default null,
    always_show number(1)      default 0 not null,
    permissions varchar2(1024) default null,
    breadcrumb  number(1)      default 1 not null,
    active_menu varchar2(512)  default null,
    title       varchar2(128)            null,
    icon        varchar2(128)            null,
    sort        number(11)     default 0 not null,
    status      number(1)      default 1 not null,
    primary key (id)
);
comment on table mg_menu is '菜单表';
comment on column mg_menu.pid is '父id';
comment on column mg_menu.path is '路由地址';
comment on column mg_menu.component is '组件';
comment on column mg_menu.type is '类型 1菜单 2按钮';
comment on column mg_menu.redirect is '跳转地址';
comment on column mg_menu.name is 'the name is used by <keep-alive> (must set!!!)';
comment on column mg_menu.always_show is '只有一个子菜单时是否显示主菜单';
comment on column mg_menu.permissions is '菜单权限';
comment on column mg_menu.breadcrumb is '0隐藏面包屑 1不隐藏面包屑';
comment on column mg_menu.active_menu is '如果设置，将高亮指定菜单';
comment on column mg_menu.title is '标题';
comment on column mg_menu.icon is '图标';
comment on column mg_menu.sort is '菜单排序';
comment on column mg_menu.status is '状态 0停用 1启用';

create index mg_menu_idx_sort on mg_menu (sort);


create table mg_role
(
    id          number(20)    not null,
    name        varchar2(128) not null,
    remark      varchar2(1024) default null,
    create_id   number(20)    not null,
    update_id   number(20)     default null,
    create_time date          not null,
    update_time date           default null,
    primary key (id)
);
comment on table mg_role is '角色表';
comment on column mg_role.id is '主键';
comment on column mg_role.name is '角色名';
comment on column mg_role.remark is '备注';
comment on column mg_role.create_id is '创建人';
comment on column mg_role.update_id is '更新人';
comment on column mg_role.create_time is '创建时间';
comment on column mg_role.update_time is '更新时间';

create unique index mg_role_uk_name on mg_role (name);


create table mg_role_menu
(
    id          number(20) not null,
    role_id     number(20) not null,
    menu_id     number(20) not null,
    create_id   number(20) not null,
    create_time date       NOT NULL,
    primary key (id)
);
comment on table mg_role_menu is '角色菜单关联表';
comment on column mg_role_menu.id is '主键';
comment on column mg_role_menu.role_id is '角色id';
comment on column mg_role_menu.menu_id is '菜单id';
comment on column mg_role_menu.create_id is '创建人';
comment on column mg_role_menu.create_time is '创建时间';

create index mg_role_menu_idx_role_id on mg_role_menu (role_id);
create index mg_role_menu_idx_menu_id on mg_role_menu (menu_id);


create table mg_user
(
    id            number(20)             not null,
    username      varchar2(128)          not null,
    password      varchar2(128)          not null,
    gender        number(2)    default null,
    mobile        varchar2(64) default null,
    super_manager number(2)    default 0 not null,
    status        number(2)    default 1 not null,
    create_id     number(20)             not null,
    update_id     number(20)   default null,
    create_time   date                   not null,
    update_time   date         default null,
    rm_tag        number(2)    default 0 not null,
    primary key (id)
);
comment on table mg_user is '系统用户';
comment on column mg_user.id is '主键';
comment on column mg_user.username is '用户名';
comment on column mg_user.password is '密码';
comment on column mg_user.gender is '性别 1男 2女 3未知';
comment on column mg_user.mobile is '手机号';
comment on column mg_user.super_manager is '超级管理员 0不是 1是';
comment on column mg_user.status is '状态 0停用 1启用';
comment on column mg_user.create_id is '创建人';
comment on column mg_user.update_id is '更新人';
comment on column mg_user.create_time is '创建时间';
comment on column mg_user.update_time is '更新时间';
comment on column mg_user.rm_tag is '删除标记 0正常 1删除';

create unique index mg_user_uk_username on mg_user (username);
create index mg_user_idx_create_time on mg_user (create_time);


create table mg_user_role
(
    id          number(20) not null,
    user_id     number(20) not null,
    role_id     number(20) not null,
    create_id   number(20) not null,
    create_time date       not null,
    primary key (id)
);
comment on table mg_user_role is '用户角色关联表';
comment on column mg_user_role.id is '主键';
comment on column mg_user_role.user_id is 'user_id';
comment on column mg_user_role.role_id is '角色id';
comment on column mg_user_role.create_id is '创建人';
comment on column mg_user_role.create_time is '创建时间';

create index mg_user_role_idx_role_id on mg_user_role (role_id);
create index mg_user_role_idx_user_id on mg_user_role (user_id);

create table mg_user_token
(
    id          number(20)    not null,
    user_id     number(20)    not null,
    token       varchar2(512) not null,
    expire_time date          not null,
    create_time date          not null,
    primary key (id)
);

comment on table mg_user_token is '用户token表';
comment on column mg_user_token.id is '主键';
comment on column mg_user_token.user_id is '用户id';
comment on column mg_user_token.token is 'token';
comment on column mg_user_token.expire_time is '过期时间';
comment on column mg_user_token.create_time is '创建时间';

create unique index mg_user_token_uk_token on mg_user_token (token);


-- api module tables
-- user table
create table t_user
(
    id          number(20)          not null,
    username    varchar2(128)       not null,
    password    varchar2(128)       not null,
    mobile      varchar2(16)        not null,
    status      number(2) default 1 not null,
    create_time date                not null,
    update_time date                null,
    rm_tag      number(2) default 0 not null,
    primary key (id)
);
comment on table t_user is '用户表';
comment on column t_user.id is '主键';
comment on column t_user.username is '用户名';
comment on column t_user.password is '密码';
comment on column t_user.mobile is '手机号';
comment on column t_user.status is '用户状态 0停用 1启用';
comment on column t_user.create_time is '创建时间';
comment on column t_user.update_time is '修改时间';
comment on column t_user.rm_tag is '删除标记 0正常 1删除';

create index t_user_idx_create_time on t_user (create_time);


create table t_user_token
(
    id          number(20)    not null,
    user_id     number(20)    not null,
    token       varchar2(512) not null,
    expire_time date          not null,
    create_time date          not null,
    primary key (id)
);

comment on table t_user_token is '用户token表';
comment on column t_user_token.id is '主键';
comment on column t_user_token.user_id is '用户id';
comment on column t_user_token.token is 'token';
comment on column t_user_token.expire_time is '过期时间';
comment on column t_user_token.create_time is '创建时间';

create unique index t_user_token_uq_user_id on t_user_token (user_id);
create unique index t_user_token_uq_token on t_user_token (token);


-- data
insert all into mg_menu(id, pid, path, component, type, redirect, name, always_show,
                        permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1, 0, '/system', 'Layout', 1, null, '系统功能', 1, null, 1, null, '系统功能', 'system', 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (2, 1, 'menu', 'menu/index', 1, null, '菜单管理', 0, null, 1, null, '菜单管理', 'menu', 2, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207275475953606658, 1, 'user', 'user/index', 1, null, '用户管理', 0, 'mg:role:list,mg:role:getByUser', 1, null,
        '用户管理', 'user', 1, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207276248561152001, 1, 'role', 'role/index', 1, null, '角色管理', 0, null, 1, null, '角色管理', 'role', 3, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207276483303763969, 1, 'log', 'log/index', 1, null, '日志', 0, 'mg:log:page', 1, null, '日志', 'log', 4, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207276926083854337, 2, null, 'Button', 2, null, '新增根菜单', 0, 'mg:menu:rootadd,mg:menu:add', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207277652847685634, 0, 'http://localhost:9568/manage/swagger-ui.html', 'Layout', 1, null, '接口文档', 0, null, 1,
        null, '接口文档', 'link', 1, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207312840671674369, 1207275475953606658, null, 'Button', 2, null, '查询', 0, 'mg:user:page', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207312948930854913, 1207275475953606658, null, 'Button', 2, null, '新增', 0, 'mg:user:add', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207313094955548673, 1207275475953606658, null, 'Button', 2, null, '修改', 0, 'mg:user:edit', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207313165424050178, 1207275475953606658, null, 'Button', 2, null, '删除', 0, 'mg:user:remove', 1, null, null,
        null, 0,
        1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207315365105811457, 1207276248561152001, null, 'Button', 2, null, '查询', 0, 'mg:role:page', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207315486644158465, 1207276248561152001, null, 'Button', 2, null, '新增', 0,
        'mg:role:add,mg:role:getMenus,mg:menu:getTree', 1, null, null, null, 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207315552452788226, 1207276248561152001, null, 'Button', 2, null, '修改', 0,
        'mg:role:edit,mg:role:getMenus,mg:menu:getTree', 1, null, null, null, 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207315799031726081, 1207276248561152001, null, 'Button', 2, null, '删除', 0, 'mg:role:remove', 1, null, null,
        null, 0,
        1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207474062448738306, 2, null, 'Button', 2, null, '新增', 0, 'mg:menu:add', 1, null, null, null, 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207478281733939201, 2, null, 'Button', 2, null, '修改', 0, 'mg:menu:edit', 1, null, null, null, 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207478488014004226, 2, null, 'Button', 2, null, '删除', 0, 'mg:menu:remove', 1, null, null, null, 0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207480932173357057, 1207276483303763969, null, 'Button', 2, null, '查询', 0, 'mg:log:page', 1, null, null, null,
        0, 1)
into mg_menu(id, pid, path, component, type, redirect, name, always_show,
             permissions, breadcrumb, active_menu, title, icon, sort, status)
values (1207481715275079681, 2, null, 'Button', 2, null, '查询', 0, 'mg:menu:getAll', 1, null, null, null, 0, 1)
SELECT 1
FROM DUAL;

insert into mg_role(id, name, remark, create_id, update_id, create_time, update_time)
values (1207276640430780417, '一般管理员', '具备当前所有菜单', 1, NULL, TO_DATE('2019-12-18 20:29:16', 'yyyy-MM-dd HH24:mi:ss'),
        NULL);

insert into mg_user(id, username, password, gender, mobile, super_manager, status, create_id,
                    update_id, create_time, update_time, rm_tag)
values (1, 'admin', '$2a$10$NZp1NOTR/uR1Oh8IMVlyPemaueL8P.ZlcIvGNKdw7CZRnEfItjO/W', 1, '17332345631', 1, 1, 0, NULL,
        TO_DATE('2019-12-18 20:29:16', 'yyyy-MM-dd HH24:mi:ss'), NULL, 0);

