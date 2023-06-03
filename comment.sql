create schema comment;

use comment;

create table comment
(
    comment_id  bigint        not null
        primary key,
    parent_id   bigint        not null comment '父id;type=1:资源id;type=2:回复id',
    type        tinyint       not null comment '1:评论资源2:回复别人',
    reply_to    bigint        null comment '被回复用户的id',
    author_id   bigint        not null comment '评论者id',
    context     varchar(1000) not null,
    update_time datetime      not null,
    create_time datetime      not null
);

